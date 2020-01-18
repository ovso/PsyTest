package io.github.ovso.psytest.ui.video.di

import dagger.Module
import dagger.Provides
import io.github.ovso.psytest.data.network.SearchRequest
import io.github.ovso.psytest.data.network.model.SearchItem
import io.github.ovso.psytest.ui.video.VideoFragmentPresenter
import io.github.ovso.psytest.ui.video.VideoFragmentPresenterImpl
import io.github.ovso.psytest.ui.video.adapter.VideoAdapter
import io.github.ovso.psytest.ui.video.adapter.VideoAdapterDataModel
import io.github.ovso.psytest.ui.video.adapter.VideoAdapterView
import io.github.ovso.psytest.utils.ResourceProvider
import io.github.ovso.psytest.utils.SchedulersFacade
import javax.inject.Singleton

@Module
class VideoFragmentModule {

  @Singleton @Provides internal fun provideVideoFragmentPresenter(
    view: VideoFragmentPresenter.View,
    searchRequest: SearchRequest,
    resourceProvider: ResourceProvider,
    schedulersFacade: SchedulersFacade,
    adapterDataModel: VideoAdapterDataModel<SearchItem>
  ): VideoFragmentPresenter {
    return VideoFragmentPresenterImpl(
        view, searchRequest, resourceProvider, schedulersFacade,
        adapterDataModel
    )
  }

  @Singleton
  @Provides
  internal fun provideVideoAdapter(): VideoAdapter {
    return VideoAdapter()
  }

  @Provides internal fun provideVideoAdapterView(adapter: VideoAdapter): VideoAdapterView {
    return adapter
  }

  @Provides
  internal fun provideVideoAdapterDataModel(adapter: VideoAdapter): VideoAdapterDataModel<SearchItem> {
    return adapter
  }
}