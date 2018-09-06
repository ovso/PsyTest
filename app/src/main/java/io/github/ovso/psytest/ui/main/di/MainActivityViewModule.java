package io.github.ovso.psytest.ui.main.di;

import dagger.Binds;
import dagger.Module;
import io.github.ovso.psytest.ui.main.MainActivity;
import io.github.ovso.psytest.ui.main.MainPresenter;

@Module public abstract class MainActivityViewModule {

  @Binds abstract MainPresenter.View bindMainView(MainActivity activity);
}