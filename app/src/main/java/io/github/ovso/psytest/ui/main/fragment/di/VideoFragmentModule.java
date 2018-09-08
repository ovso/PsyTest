package io.github.ovso.psytest.ui.main.fragment.di;

import dagger.Module;
import dagger.Provides;
import io.github.ovso.psytest.data.network.YoutubeRequest;
import io.github.ovso.psytest.ui.main.MainPresenter;
import io.github.ovso.psytest.ui.main.MainPresenterImpl;
import io.github.ovso.psytest.ui.main.fragment.VideoFragment;
import io.github.ovso.psytest.ui.main.fragment.VideoFragmentPresenter;
import io.github.ovso.psytest.ui.main.fragment.VideoFragmentPresenterImpl;
import javax.inject.Singleton;

@Module public class VideoFragmentModule {
  //@Singleton @Provides MainPresenter provideMainPresenter() {
  //  return new MainPresenterImpl(view, resourceProvider, schedulersFacade);
  //}

  @Provides VideoFragmentPresenter provideVideoFragmentPresenter(VideoFragment fragment) {
    return new VideoFragmentPresenterImpl(fragment, new YoutubeRequest());
  }
}