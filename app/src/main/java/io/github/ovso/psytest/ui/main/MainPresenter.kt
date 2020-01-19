package io.github.ovso.psytest.ui.main

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import io.github.ovso.psytest.ui.main.adapter.MainItem

interface MainPresenter : LifecycleObserver {

  @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
  fun onCreate()

  interface View {
    fun setupView()
    fun showTitle(title: String)
    fun setupRv()
    fun loadAd()
    fun submitList(it: List<MainItem>)
  }
}
