package io.github.ovso.psytest.ui.main.fragment;

import android.os.Bundle;
import io.github.ovso.psytest.R;
import io.github.ovso.psytest.data.KeyName;
import io.github.ovso.psytest.data.network.SearchRequest;
import io.github.ovso.psytest.utils.ResourceProvider;
import io.reactivex.disposables.CompositeDisposable;
import timber.log.Timber;

public class VideoFragmentPresenterImpl implements VideoFragmentPresenter {

  private CompositeDisposable compositeDisposable = new CompositeDisposable();
  private VideoFragmentPresenter.View view;
  private SearchRequest searchRequest;
  private ResourceProvider resourceProvider;
  private String pageToken = null;
  public VideoFragmentPresenterImpl(VideoFragmentPresenter.View $view,
      SearchRequest $searchRequest, ResourceProvider $resourceProvider) {
    view = $view;
    searchRequest = $searchRequest;
    resourceProvider = $resourceProvider;
  }

  @Override public void onActivityCreated(Bundle args) {
    Timber.d("onActivityCreated");
    int position = args.getInt(KeyName.POSITION.get());
    String q = resourceProvider.getStringArray(R.array.tabs)[position];
    searchRequest.getResult(q, pageToken);
  }

  @Override public void onDestroyView() {
    compositeDisposable.clear();
  }
}
