package io.github.ovso.psytest.ui.main.fragment;

import android.content.ActivityNotFoundException;
import android.os.Bundle;
import android.text.TextUtils;
import io.github.ovso.psytest.R;
import io.github.ovso.psytest.data.KeyName;
import io.github.ovso.psytest.data.network.SearchRequest;
import io.github.ovso.psytest.data.network.model.SearchItem;
import io.github.ovso.psytest.ui.main.fragment.adapter.VideoAdapterDataModel;
import io.github.ovso.psytest.utils.ResourceProvider;
import io.github.ovso.psytest.utils.SchedulersFacade;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import java.util.Collections;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import timber.log.Timber;

public class VideoFragmentPresenterImpl implements VideoFragmentPresenter {

  private CompositeDisposable compositeDisposable = new CompositeDisposable();
  private VideoFragmentPresenter.View view;
  private SearchRequest searchRequest;
  private ResourceProvider resourceProvider;
  private SchedulersFacade schedulersFacade;
  private VideoAdapterDataModel<SearchItem> adapterDataModel;
  private String nextPageToken;
  private String q;
  private int position;

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
    view.setupSwipeRefresh();
    view.showLoading();
    view.setupAdListener();
    position = args.getInt(KeyName.POSITION.get());
    q = resourceProvider.getStringArray(R.array.q)[position];
    Disposable disposable = searchRequest.getResult(q, nextPageToken)
        .subscribeOn(schedulersFacade.io())
        .observeOn(schedulersFacade.ui())
        .subscribe(
            search -> {
              nextPageToken = search.getNextPageToken();
              List<SearchItem> items = search.getItems();
              shuffle(items);
              adapterDataModel.addAll(items);
              view.refresh();
              view.setLoaded();
              view.hideLoading();
            }, throwable -> {
              Timber.d(throwable);
              view.hideLoading();
            });
    compositeDisposable.add(disposable);
  }

  @Override public void onDestroyView() {
    compositeDisposable.clear();
  }

  @Override public void onItemClick(SearchItem data) {
    try {
      view.showVideo(data.getId().getVideoId());
    } catch (ActivityNotFoundException e) {
      e.printStackTrace();
      view.showYoutubeUseWarningDialog();
    }
  }

  @Override public void onLoadMore() {
    if (!TextUtils.isEmpty(nextPageToken) && !TextUtils.isEmpty(q)) {
      Disposable disposable = searchRequest.getResult(q, nextPageToken)
          .subscribeOn(schedulersFacade.io())
          .observeOn(schedulersFacade.ui())
          .subscribe(
              search -> {
                nextPageToken = search.getNextPageToken();
                List<SearchItem> items = search.getItems();
                shuffle(items);
                adapterDataModel.addAll(items);
                view.refresh();
                view.setLoaded();
              }, Timber::d);
      compositeDisposable.add(disposable);
    }
  }

  @Override public void onRefresh() {
    adapterDataModel.clear();
    view.refresh();
    nextPageToken = null;
    q = resourceProvider.getStringArray(R.array.q)[position];
    Disposable disposable = searchRequest.getResult(q, nextPageToken)
        .subscribeOn(schedulersFacade.io())
        .observeOn(schedulersFacade.ui())
        .subscribe(
            search -> {
              nextPageToken = search.getNextPageToken();
              List<SearchItem> items = search.getItems();
              shuffle(items);
              adapterDataModel.addAll(items);
              view.refresh();
              view.setLoaded();
              view.hideLoading();
            }, throwable -> {
              Timber.d(throwable);
              view.hideLoading();
            });
    compositeDisposable.add(disposable);
  }

  @Override public boolean onOptionsItemSelected(int itemId) {
    String url = Portal.toUrl(itemId, q);
    view.navigateToWeb(url);
    return true;
  }

  private <E> void shuffle(List<E> $items) {
    Collections.shuffle($items);
  }

  @Getter @AllArgsConstructor enum Portal {
    GOOGLE(R.id.action_google, "https://google.co.kr/search?q="),
    NAVER(R.id.action_naver, "https://m.search.naver.com/search.naver?where=m_video&query="),
    DAUM(R.id.action_daum, "https://m.search.daum.net/search?w=vclip&q=");

    private int id;
    private String url;

    public static Portal toType(int id) {
      for (Portal portal : Portal.values()) {
        if (portal.id == id) {
          return portal;
        }
      }

      return GOOGLE;
    }

    public static String toUrl(int act_id, String q) {
      Portal portal = toType(act_id);
      switch (portal) {

        case GOOGLE:
          return Portal.GOOGLE.getUrl() + q + "&tbm=vid";
        case NAVER:
          return Portal.NAVER.getUrl() + q;
        case DAUM:
          return Portal.DAUM.getUrl() + q;
        default:
          return Portal.GOOGLE.getUrl() + q + "&tbm=vid";
      }
    }

  }
}