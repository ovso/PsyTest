package io.github.ovso.psytest.ui.video

import android.content.ActivityNotFoundException
import android.os.Bundle
import android.text.TextUtils
import io.github.ovso.psytest.R
import io.github.ovso.psytest.data.KeyName
import io.github.ovso.psytest.data.network.SearchRequest
import io.github.ovso.psytest.data.network.model.Search
import io.github.ovso.psytest.data.network.model.SearchItem
import io.github.ovso.psytest.ui.video.VideoFragmentPresenter.View
import io.github.ovso.psytest.ui.video.adapter.VideoAdapterDataModel
import io.github.ovso.psytest.utils.ResourceProvider
import io.github.ovso.psytest.utils.SchedulersFacade
import io.reactivex.SingleObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import timber.log.Timber
import java.util.Collections.shuffle
import kotlin.random.Random

class VideoFragmentPresenterImpl(
  private val view: View,
  private val searchRequest: SearchRequest,
  private val resourceProvider: ResourceProvider,
  private val schedulersFacade: SchedulersFacade,
  private val adapterDataModel: VideoAdapterDataModel<SearchItem>
) : VideoFragmentPresenter {

  private val compositeDisposable = CompositeDisposable()
  private var nextPageToken: String? = null
  private var q: String? = null
  private var position: Int = 0

  override fun onActivityCreated(args: Bundle) {
    Timber.d("onActivityCreated")
    view.setupRecyclerView()
    view.setupSwipeRefresh()
    view.showLoading()
    view.setupAdListener()
    view.addEvent()
    position = args.getInt(KeyName.POSITION.get())
    q = resourceProvider.getStringArray(R.array.q)[position]
    searchRequest.getResult(q, nextPageToken)
        .map {
          val newItems = it.items?.toMutableList()
          it.items = newItems?.shuffled(Random.Default)
          it
        }
        .subscribeOn(schedulersFacade.io())
        .observeOn(schedulersFacade.ui())
        .subscribe(object : SingleObserver<Search> {
          override fun onSubscribe(d: Disposable) {
            compositeDisposable.add(d)
          }

          override fun onSuccess(search: Search) {
            nextPageToken = search.nextPageToken
            val items = search.items
            adapterDataModel.addAll(items)
            view.refresh()
            view.hideLoading()
          }

          override fun onError(e: Throwable) {
            Timber.e(e)
            view.hideLoading()
          }
        })
  }

  override fun onDestroyView() {
    compositeDisposable.clear()
  }

  override fun onItemClick(data: SearchItem) {
    try {
      view.showVideo(data.id?.videoId)
    } catch (e: ActivityNotFoundException) {
      e.printStackTrace()
      view.showYoutubeUseWarningDialog()
    }

  }

  override fun onLoadMore() {
    if (!TextUtils.isEmpty(nextPageToken) && !TextUtils.isEmpty(q)) {
      searchRequest.getResult(q, nextPageToken)
          .map {
            it.items = it.items?.toMutableList()
                ?.shuffled(Random.Default)
            it
          }
          .subscribeOn(schedulersFacade.io())
          .observeOn(schedulersFacade.ui())
          .subscribe(object : SingleObserver<Search> {
            override fun onSubscribe(d: Disposable) {
              compositeDisposable.add(d)
            }

            override fun onSuccess(search: Search) {
              nextPageToken = search.nextPageToken
              val items = search.items
              adapterDataModel.addAll(items)
              view.refresh()
              view.setLoaded()
            }

            override fun onError(e: Throwable) {
              Timber.e(e)
            }
          })
    }
  }

  @Deprecated("Unsupported")
  override fun onRefresh() {
    adapterDataModel.clear()
    view.refresh()
    nextPageToken = null
    q = resourceProvider.getStringArray(R.array.q)[position]
    searchRequest.getResult(q, nextPageToken)
        .subscribeOn(schedulersFacade.io())
        .observeOn(schedulersFacade.ui())
        .subscribe(object : SingleObserver<Search> {
          override fun onSubscribe(d: Disposable) {
            compositeDisposable.add(d)
          }

          override fun onSuccess(search: Search) {
            nextPageToken = search.nextPageToken
            val items = search.items
            shuffle(items)
            adapterDataModel.addAll(items)
            view.refresh()
            view.setLoaded()
            view.hideLoading()
          }

          override fun onError(e: Throwable) {
            Timber.e(e)
            view.hideLoading()
          }
        })
  }

  override fun onOptionsItemSelected(itemId: Int): Boolean {
    val url =
      Portal.toUrl(
          itemId, q
      )
    val title = Portal.toType(
        itemId
    )
        .toString()
    view.navigateToWeb(url, title)
    return true
  }

  internal enum class Portal(
    private var id: Int,
    private var url: String
  ) {
    GOOGLE(R.id.action_google, "https://google.co.kr/search?q="),
    NAVER(R.id.action_naver, "https://m.search.naver.com/search.naver?where=m_video&query="),
    DAUM(R.id.action_daum, "https://m.search.daum.net/search?w=vclip&q=");

    companion object {

      fun toType(id: Int): Portal {
        for (portal in Portal.values()) {
          if (portal.id == id) {
            return portal
          }
        }

        return GOOGLE
      }

      fun toUrl(
        act_id: Int,
        q: String?
      ): String {
        val portal =
          toType(
              act_id
          )
        return when (portal) {
          GOOGLE -> GOOGLE.url + q!! + "&tbm=vid"
          NAVER -> NAVER.url + q!!
          DAUM -> DAUM.url + q!!
        }
      }
    }
  }
}