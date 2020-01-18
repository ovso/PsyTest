package io.github.ovso.psytest.ui.main

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent

interface MainPresenter : LifecycleObserver {

  @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
  fun onCreate()

  interface View {
    fun setupView()

    fun setupTabLayout()

    fun setupViewPager()

    fun showTabName(
      position: Int,
      name: String
    )

    fun refresh()

    fun showTitle(title: String)

    fun showBannerAd()

    fun setupRv()
  }
}
