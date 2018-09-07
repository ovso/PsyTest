package io.github.ovso.psytest.ui.main;

import io.github.ovso.psytest.utils.ResourceProvider;
import io.github.ovso.psytest.utils.SchedulersFacade;
import io.reactivex.disposables.CompositeDisposable;

public class MainPresenterImpl implements MainPresenter {

  private MainPresenter.View view;
  private ResourceProvider resourceProvider;
  private CompositeDisposable compositeDisposable = new CompositeDisposable();
  private SchedulersFacade schedulers;

  public MainPresenterImpl(MainPresenter.View $view, ResourceProvider $ResourceProvider,
      SchedulersFacade $schedulers) {
    view = $view;
    resourceProvider = $ResourceProvider;
    schedulers = $schedulers;
  }

  @Override public void onCreate() {
    view.setupView();
    view.setupTabLayout();
  }
}