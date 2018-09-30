package io.github.ovso.psytest.ui.main;

import io.github.ovso.psytest.R;
import io.github.ovso.psytest.ui.main.adapter.MainAdapterDataModel;
import io.github.ovso.psytest.utils.ResourceProvider;

public class MainPresenterImpl implements MainPresenter {

  private MainPresenter.View view;
  private ResourceProvider resourceProvider;
  private MainAdapterDataModel adapterDataModel;

  public MainPresenterImpl(MainPresenter.View $view, ResourceProvider $ResourceProvider,
      MainAdapterDataModel $adapterDataModel) {
    view = $view;
    resourceProvider = $ResourceProvider;
    adapterDataModel = $adapterDataModel;
    $view.changeTheme();
  }

  @Override public void onCreate() {
    view.showTitle(resourceProvider.getString(R.string.app_name));
    view.setupView();
    view.setupTabLayout();
    view.setupViewPager();
    adapterDataModel.createItems(resourceProvider.getStringArray(R.array.tabs).length);
    view.refresh();
    showTabNames();
    view.showBannerAd();
  }

  private void showTabNames() {
    String[] tabNames = resourceProvider.getStringArray(R.array.tabs);
    for (int i = 0; i < tabNames.length; i++) {
      view.showTabName(i, tabNames[i]);
    }
  }
}