package io.github.ovso.psytest.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import io.github.ovso.psytest.ui.main.MainActivity;
import io.github.ovso.psytest.ui.main.di.MainActivityModule;
import io.github.ovso.psytest.ui.main.di.MainActivityViewModule;
import io.github.ovso.psytest.ui.web.WebActivity;
import javax.inject.Singleton;

@Module(includes = { AndroidSupportInjectionModule.class })
public abstract class ActivityBuilder {
  @Singleton @ContributesAndroidInjector(modules = {
      MainActivityModule.class,
      MainActivityViewModule.class
  })
  abstract MainActivity bindMainActivity();

  @Singleton @ContributesAndroidInjector(modules = {
  })
  abstract WebActivity bindWebActivity();
}
