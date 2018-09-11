package io.github.ovso.psytest.ui.main.fragment;

import android.os.Bundle;
import io.github.ovso.psytest.R;
import io.github.ovso.psytest.data.KeyName;
import io.github.ovso.psytest.data.network.SearchRequest;
import io.github.ovso.psytest.data.network.model.Search;
import io.github.ovso.psytest.data.network.model.SearchItem;
import io.github.ovso.psytest.ui.main.fragment.adapter.VideoAdapterDataModel;
import io.github.ovso.psytest.utils.ResourceProvider;
import io.github.ovso.psytest.utils.SchedulersFacade;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import java.util.List;
import timber.log.Timber;

public class VideoFragmentPresenterImpl implements VideoFragmentPresenter {

  private CompositeDisposable compositeDisposable = new CompositeDisposable();
  private VideoFragmentPresenter.View view;
  private SearchRequest searchRequest;
  private ResourceProvider resourceProvider;
  private String pageToken = null;
  private SchedulersFacade schedulersFacade;
  private VideoAdapterDataModel<SearchItem> adapterDataModel;

  public VideoFragmentPresenterImpl(VideoFragmentPresenter.View $view,
      SearchRequest $searchRequest, ResourceProvider $resourceProvider,
      SchedulersFacade $schedulersFacade, VideoAdapterDataModel<SearchItem> $adapterDataModel) {
    view = $view;
    searchRequest = $searchRequest;
    resourceProvider = $resourceProvider;
    schedulersFacade = $schedulersFacade;
    adapterDataModel = $adapterDataModel;
  }

  @Override public void onActivityCreated(Bundle args) {
    Timber.d("onActivityCreated");
    view.setupRecyclerView();
    int position = args.getInt(KeyName.POSITION.get());
    String q = resourceProvider.getStringArray(R.array.tabs)[position];
    q = "심리테스트";
    Disposable disposable = searchRequest.getResult(q, pageToken)
        .subscribeOn(schedulersFacade.io())
        .observeOn(schedulersFacade.ui())
        .subscribe(
            search -> {
              Timber.d("search = " + search.toString());
              List<SearchItem> items = search.getItems();
              int size = items.size();
              Timber.d("size = " + size);
              adapterDataModel.addAll(items);
              view.refresh();
            }, throwable -> {
              Timber.d(throwable);
            });
    compositeDisposable.add(disposable);
  }

  @Override public void onDestroyView() {
    compositeDisposable.clear();
  }
}
