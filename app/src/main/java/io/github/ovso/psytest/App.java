package io.github.ovso.psytest;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import io.github.ovso.psytest.di.DaggerAppComponent;

public class App extends DaggerApplication {

  @Override public void onCreate() {
    super.onCreate();

    AppInitUtils.INSTANCE.logger();
    AppInitUtils.INSTANCE.crashlytics(this);
    AppInitUtils.INSTANCE.ads(this);
  }

  @Override protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
    return DaggerAppComponent.builder().application(this).build();
  }
}