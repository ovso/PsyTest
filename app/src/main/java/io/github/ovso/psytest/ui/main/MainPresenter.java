package io.github.ovso.psytest.ui.main;

public interface MainPresenter {

  void onCreate();

  void onTabSelected(int position);

  interface View {
    void setupView();

    void setupTabLayout();

    void setupViewPager();

    void showTabName(int position, String name);

    void refresh();

    void showTitle(String title);

    void showBannerAd();

    void changeTheme();

    void showInterstitalAd();
  }
}
