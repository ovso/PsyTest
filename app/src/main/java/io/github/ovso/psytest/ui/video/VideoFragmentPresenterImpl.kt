package io.github.ovso.psytest.ui.video

import android.content.ActivityNotFoundException
import android.os.Bundle
import io.github.ovso.psytest.R
import io.github.ovso.psytest.Security
import io.github.ovso.psytest.data.KeyName
import io.github.ovso.psytest.data.network.SearchRequest
import io.github.ovso.psytest.data.network.model.Search
import io.github.ovso.psytest.data.network.model.SearchItem
import io.github.ovso.psytest.exts.plusAssign
import io.github.ovso.psytest.ui.video.VideoFragmentPresenter.View
import io.github.ovso.psytest.ui.video.adapter.VideoAdapterDataModel
import io.github.ovso.psytest.utils.ResourceProvider
import io.github.ovso.psytest.utils.SchedulersFacade
import io.reactivex.SingleObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import timber.log.Timber
import java.util.Collections.shuffle

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

  private fun getParams(): Map<String, Any> = mapOf(
      KeyName.KEY.get() to Security.KEY.value,
      KeyName.Q.get() to (q ?: resourceProvider.getString(R.string.app_name)),
      KeyName.MAX_RESULTS.get() to 3,
      KeyName.ORDER.get() to "viewCount",
      KeyName.TYPE.get() to "video",
      KeyName.VIDEO_SYNDICATED.get() to "any",
      KeyName.PART.get() to "snippet"
  ).apply {
    if (nextPageToken.isNullOrEmpty().not()) {
      KeyName.PAGE_TOKEN.get() to nextPageToken
    }
  }

  override fun onActivityCreated(args: Bundle) {
    view.setupRecyclerView()
    view.setupSwipeRefresh()
    view.showLoading()
    view.setupAdListener()
    view.addEvent()
    view.showTitle(args.getString("title"))
    position = args.getInt(KeyName.POSITION.get())
    q = args.getString("query")

    reqSearch()
  }

  private fun reqSearch() {
    fun shuffle(search: Search): Search {
      shuffle(search.items)
      return search
    }

    compositeDisposable += searchRequest.getResult2(getParams())
        .map(::shuffle)
        .subscribeOn(schedulersFacade.io())
        .observeOn(schedulersFacade.ui())
        .doOnSubscribe { view.showLoading() }
        .doOnSuccess { view.hideLoading() }
        .doOnError { view.hideLoading() }
        .subscribe(::onSuccess, ::onError)
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

  private fun onSuccess(search: Search) {
    nextPageToken = search.nextPageToken
    val items = search.items
    adapterDataModel.addAll(items)
    view.refresh()
    view.setLoaded()
  }

  private fun onError(t: Throwable) {
    Timber.e(t)
  }

  override fun onLoadMore() {
    reqSearch()
  }

  @Deprecated("Unsupported")
  override fun onRefresh() {
    adapterDataModel.clear()
    view.refresh()
    nextPageToken = null
    q = resourceProvider.getStringArray(R.array.queries)[position]
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
        for (portal in values()) {
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