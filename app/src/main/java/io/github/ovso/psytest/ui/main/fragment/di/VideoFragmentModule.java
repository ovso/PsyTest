package io.github.ovso.psytest.ui.main.fragment.di;

import dagger.Module;
import dagger.Provides;
import io.github.ovso.psytest.data.network.SearchRequest;
import io.github.ovso.psytest.ui.main.fragment.VideoFragment;
import io.github.ovso.psytest.ui.main.fragment.VideoFragmentPresenter;
import io.github.ovso.psytest.ui.main.fragment.VideoFragmentPresenterImpl;
import io.github.ovso.psytest.utils.ResourceProvider;

@Module public class VideoFragmentModule {

  @Provides VideoFragmentPresenter provideVideoFragmentPresenter(VideoFragment fragment,
      SearchRequest searchRequest, ResourceProvider resourceProvider) {
    return new VideoFragmentPresenterImpl(fragment, searchRequest, resourceProvider);
  }
}