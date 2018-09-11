package io.github.ovso.psytest.ui.main.fragment.di;

import dagger.Module;
import dagger.Provides;
import io.github.ovso.psytest.data.network.SearchRequest;
import io.github.ovso.psytest.data.network.model.SearchItem;
import io.github.ovso.psytest.ui.main.fragment.VideoFragment;
import io.github.ovso.psytest.ui.main.fragment.VideoFragmentPresenter;
import io.github.ovso.psytest.ui.main.fragment.VideoFragmentPresenterImpl;
import io.github.ovso.psytest.ui.main.fragment.adapter.VideoAdapter;
import io.github.ovso.psytest.ui.main.fragment.adapter.VideoAdapterDataModel;
import io.github.ovso.psytest.ui.main.fragment.adapter.VideoAdapterView;
import io.github.ovso.psytest.utils.ResourceProvider;
import io.github.ovso.psytest.utils.SchedulersFacade;
import javax.inject.Singleton;

@Module public class VideoFragmentModule {

  @Singleton @Provides VideoFragmentPresenter provideVideoFragmentPresenter(
      VideoFragmentPresenter.View view,
      SearchRequest searchRequest, ResourceProvider resourceProvider,
      SchedulersFacade schedulersFacade, VideoAdapterDataModel<SearchItem> adapterDataModel) {
    return new VideoFragmentPresenterImpl(view, searchRequest, resourceProvider, schedulersFacade,
        adapterDataModel);
  }

  @Singleton
  @Provides VideoAdapter provideVideoAdapter() {
    return new VideoAdapter();
  }

  @Provides VideoAdapterView provideVideoAdapterView(VideoAdapter adapter) {
    return adapter;
  }

  @Provides VideoAdapterDataModel<SearchItem> provideVideoAdapterDataModel(VideoAdapter adapter) {
    return adapter;
  }
}