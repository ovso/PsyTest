package io.github.ovso.psytest.ui.main.di;

import dagger.Module;
import dagger.Provides;
import io.github.ovso.psytest.ui.main.MainPresenter;
import io.github.ovso.psytest.ui.main.MainPresenterImpl;
import io.github.ovso.psytest.utils.ResourceProvider;
import io.github.ovso.psytest.utils.SchedulersFacade;
import javax.inject.Singleton;

@Module public class MainActivityModule {

  @Singleton @Provides MainPresenter provideMainPresenter(MainPresenter.View view,
      ResourceProvider resourceProvider, SchedulersFacade schedulersFacade) {
    return new MainPresenterImpl(view, resourceProvider, schedulersFacade);
  }
}
