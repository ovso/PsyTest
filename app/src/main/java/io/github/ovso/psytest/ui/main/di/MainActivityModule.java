package io.github.ovso.psytest.ui.main.di;

import dagger.Module;
import dagger.Provides;
import io.github.ovso.psytest.ui.main.MainActivity;
import io.github.ovso.psytest.ui.main.MainPresenter;
import io.github.ovso.psytest.ui.main.MainPresenterImpl;
import io.github.ovso.psytest.ui.main.adapter.MainAdapterDataModel;
import io.github.ovso.psytest.ui.main.adapter.MainAdapterView;
import io.github.ovso.psytest.ui.main.adapter.MainPagerAdapter;
import io.github.ovso.psytest.utils.ResourceProvider;
import io.github.ovso.psytest.utils.SchedulersFacade;
import javax.inject.Singleton;

@Module public class MainActivityModule {

  @Singleton @Provides MainPresenter provideMainPresenter(MainPresenter.View view,
      ResourceProvider resourceProvider, SchedulersFacade schedulersFacade,
      MainAdapterDataModel adapterDataModel) {
    return new MainPresenterImpl(view, resourceProvider, schedulersFacade, adapterDataModel);
  }

  @Singleton @Provides MainPagerAdapter provideMainPagerAdapter(MainActivity activity) {
    return new MainPagerAdapter(activity.getSupportFragmentManager());
  }

  @Provides MainAdapterDataModel provideMainAdapterDataModel(MainPagerAdapter adapter) {
    return adapter;
  }

  @Provides MainAdapterView provideMainAdapterView(MainPagerAdapter adapter) {
    return adapter;
  }
}