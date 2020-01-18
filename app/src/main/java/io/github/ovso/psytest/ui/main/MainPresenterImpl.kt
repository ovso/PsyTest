package io.github.ovso.psytest.ui.main

import android.arch.lifecycle.Lifecycle.Event.ON_DESTROY
import android.arch.lifecycle.OnLifecycleEvent
import io.github.ovso.psytest.R
import io.github.ovso.psytest.ui.main.adapter.MainAdapterDataModel
import io.github.ovso.psytest.ui.main.rvadapter.MainItem
import io.github.ovso.psytest.utils.ResourceProvider
import io.github.ovso.psytest.utils.SchedulersFacade
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import timber.log.Timber

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
    setItems()
  }

  private fun setItems() {
    val titles = resourceProvider.getStringArray(R.array.q)
    compositeDisposable += Observable.fromIterable(titles.toList())
        .map { MainItem(it) }
        .toList()
        .subscribeOn(schedulers.io())
        .observeOn(schedulers.ui())
        .subscribe({
          view.submitList(it)
        }, {
          Timber.e(it)
        })

//    Single.concatArray(titles)
  }

  private fun showTabNames() {
    val tabNames = resourceProvider.getStringArray(R.array.tabs)
    for (i in tabNames.indices) {
      view.showTabName(i, tabNames[i])
    }
  }

  @OnLifecycleEvent(ON_DESTROY)
  private fun onDestroy() {
    compositeDisposable.clear()
  }
}

private operator fun CompositeDisposable.plusAssign(subscribe: Disposable?) {
  subscribe?.let {
    add(subscribe)
  }
}
