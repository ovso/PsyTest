package io.github.ovso.psytest

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.github.ovso.psytest.di.DaggerAppComponent

class App : DaggerApplication() {

  override fun onCreate() {
    super.onCreate()

    AppInitUtils.logger()
    AppInitUtils.crashlytics(this)
    AppInitUtils.ads(this)
  }

  override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
    return DaggerAppComponent.builder()
        .application(this)
        .build()
  }
}