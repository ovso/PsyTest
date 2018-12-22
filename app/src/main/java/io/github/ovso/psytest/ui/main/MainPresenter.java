package io.github.ovso.psytest.ui.main;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;

public interface MainPresenter extends LifecycleObserver {

  @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
  void onCreate();

  interface View {
    void setupView();

    void setupTabLayout();

    void setupViewPager();

    void showTabName(int position, String name);

    void refresh();

    void showTitle(String title);

    void showBannerAd();

    void changeTheme();
  }
}
