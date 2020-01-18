package io.github.ovso.psytest.ui.video.di

import dagger.Binds
import dagger.Module
import io.github.ovso.psytest.ui.video.VideoFragment
import io.github.ovso.psytest.ui.video.VideoFragmentPresenter

@Module
abstract class VideoFragmentViewModule {
  @Binds
  internal abstract fun bindVideoFragmentView(fragment: VideoFragment): VideoFragmentPresenter.View
}