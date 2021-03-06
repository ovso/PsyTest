package io.github.ovso.psytest.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import io.github.ovso.psytest.ui.main.MainActivity
import io.github.ovso.psytest.ui.main.di.MainActivityModule
import io.github.ovso.psytest.ui.main.di.MainActivityViewModule
import io.github.ovso.psytest.ui.video.VideoActivity
import io.github.ovso.psytest.ui.video.di.VideoActivityModule
import io.github.ovso.psytest.ui.video.di.VideoActivityViewModule
import io.github.ovso.psytest.ui.web.WebActivity
import javax.inject.Singleton

@Module(includes = [AndroidSupportInjectionModule::class])
abstract class ActivityBuilder {
  @Singleton
  @ContributesAndroidInjector(
      modules = [MainActivityModule::class, MainActivityViewModule::class]
  )
  internal abstract fun bindMainActivity(): MainActivity

  @Singleton
  @ContributesAndroidInjector(
      modules = [VideoActivityModule::class, VideoActivityViewModule::class]
  )
  internal abstract fun bindVideoActivity(): VideoActivity

  @Singleton
  @ContributesAndroidInjector(modules = arrayOf())
  internal abstract fun bindWebActivity(): WebActivity
}
