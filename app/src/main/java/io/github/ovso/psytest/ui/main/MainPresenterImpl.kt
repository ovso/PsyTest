package io.github.ovso.psytest.ui.main

import android.arch.lifecycle.Lifecycle.Event.ON_DESTROY
import android.arch.lifecycle.OnLifecycleEvent
import io.github.ovso.psytest.R
import io.github.ovso.psytest.R.array
import io.github.ovso.psytest.exts.plusAssign
import io.github.ovso.psytest.ui.main.adapter.MainAdapterDataModel
import io.github.ovso.psytest.ui.main.rvadapter.MainItem
import io.github.ovso.psytest.utils.ResourceProvider
import io.github.ovso.psytest.utils.SchedulersFacade
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction

class MainPresenterImpl(
  private val view: MainPresenter.View,
  private val resourceProvider: ResourceProvider,
  private val adapterDataModel: MainAdapterDataModel,
  private val schedulers: SchedulersFacade
) : MainPresenter {

  private val compositeDisposable = CompositeDisposable()

/*
  init {
    view.setupView()
    view.setupTabLayout()
    view.setupViewPager()
    adapterDataModel.createItems(resourceProvider.getStringArray(R.array.tabs).size)
    view.refresh()
    showTabNames()
  }
*/

  override fun onCreate() {
    view.showTitle(resourceProvider.getString(R.string.app_name))
    view.setupRv()
    view.loadAd()
    fetchList()
  }

  private fun fetchList() {
    val titles = Observable.fromIterable(resourceProvider.getStringArray(array.tabs).toList())
    val queries = Observable.fromIterable(resourceProvider.getStringArray(array.queries).toList())

    fun onSuccess(items: List<MainItem>) {
      view.submitList(items)
    }

    fun onFailure(t: Throwable) {
      println(t)
    }

    compositeDisposable += titles
        .zipWith(
            queries, BiFunction<String, String, MainItem> { name, query -> MainItem(name, query) }
        )
        .toList()
        .subscribe(::onSuccess, ::onFailure)
  }

  @OnLifecycleEvent(ON_DESTROY)
  private fun onDestroy() {
    compositeDisposable.clear()
  }
}