package io.github.ovso.psytest.ui.main.di;

import android.arch.lifecycle.Lifecycle;
import dagger.Module;
import dagger.Provides;
import io.github.ovso.psytest.ui.main.MainActivity;
import io.github.ovso.psytest.ui.main.MainPresenter;
import io.github.ovso.psytest.ui.main.MainPresenterImpl;
import io.github.ovso.psytest.ui.main.adapter.MainAdapterDataModel;
import io.github.ovso.psytest.ui.main.adapter.MainAdapterView;
import io.github.ovso.psytest.ui.main.adapter.MainPagerAdapter;
import io.github.ovso.psytest.utils.ResourceProvider;
import javax.inject.Singleton;

@Module public class MainActivityModule {

  @Singleton @Provides MainPresenter provideMainPresenter(MainPresenter.View view,
      ResourceProvider resourceProvider, MainAdapterDataModel adapterDataModel,
      Lifecycle lifecycle) {
    MainPresenter presenter =
        new MainPresenterImpl(view, resourceProvider, adapterDataModel);
    lifecycle.addObserver(presenter);
    return presenter;
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

  @Provides Lifecycle provideMainLifecycle(MainActivity act) {
    return act.getLifecycle();
  }
}