package io.github.ovso.psytest.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import io.github.ovso.psytest.ui.video.VideoFragment
import io.github.ovso.psytest.ui.video.di.VideoFragmentModule
import io.github.ovso.psytest.ui.video.di.VideoFragmentViewModule
import javax.inject.Singleton

@Module(includes = [AndroidSupportInjectionModule::class])
abstract class FragmentBuilder {
  @Singleton
  @ContributesAndroidInjector(
      modules = [VideoFragmentModule::class, VideoFragmentViewModule::class]
  )
  internal abstract fun contributeRepoFragment(): VideoFragment
}