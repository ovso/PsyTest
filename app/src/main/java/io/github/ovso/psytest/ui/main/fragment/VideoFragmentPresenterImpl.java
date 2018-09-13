package io.github.ovso.psytest.ui.main.fragment;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.gson.Gson;
import io.github.ovso.psytest.R;
import io.github.ovso.psytest.data.KeyName;
import io.github.ovso.psytest.data.VideoMode;
import io.github.ovso.psytest.data.network.SearchRequest;
import io.github.ovso.psytest.data.network.model.SearchItem;
import io.github.ovso.psytest.ui.main.fragment.adapter.VideoAdapterDataModel;
import io.github.ovso.psytest.utils.ResourceProvider;
import io.github.ovso.psytest.utils.SchedulersFacade;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import java.util.List;
import org.w3c.dom.Text;
import timber.log.Timber;

import static io.github.ovso.psytest.data.VideoMode.CANCEL;
import static io.github.ovso.psytest.data.VideoMode.LANDSCAPE;
import static io.github.ovso.psytest.data.VideoMode.PORTRAIT;

public class VideoFragmentPresenterImpl implements VideoFragmentPresenter {

  private CompositeDisposable compositeDisposable = new CompositeDisposable();
  private VideoFragmentPresenter.View view;
  private SearchRequest searchRequest;
  private ResourceProvider resourceProvider;
  private SchedulersFacade schedulersFacade;
  private VideoAdapterDataModel<SearchItem> adapterDataModel;
  private String nextPageToken;
  private String q;

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
    q = resourceProvider.getStringArray(R.array.q)[position];
    Disposable disposable = searchRequest.getResult(q, nextPageToken)
        .subscribeOn(schedulersFacade.io())
        .observeOn(schedulersFacade.ui())
        .subscribe(
            search -> {
              nextPageToken = search.getNextPageToken();
              List<SearchItem> items = search.getItems();
              adapterDataModel.addAll(items);
              view.refresh();
              view.setLoaded();
            }, throwable -> {
              Timber.d(throwable);
            });
    compositeDisposable.add(disposable);
  }

  @Override public void onDestroyView() {
    compositeDisposable.clear();
  }

  @Override public void onItemClick(SearchItem data) {
    final DialogInterface.OnClickListener onClickListener = (dialog, which) -> {
      dialog.dismiss();

      try {
        dialog.dismiss();
        String videoId = data.getId().getVideoId();
        switch (VideoMode.toMode(which)) {
          case PORTRAIT:
            view.showPortraitVideo(videoId);
            break;
          case LANDSCAPE:
            view.showLandscapeVideo(videoId);
            break;
          case CANCEL:
            break;
        }
      } catch (ActivityNotFoundException e) {
        e.printStackTrace();
        view.showYoutubeUseWarningDialog();
      }
    };
    view.showVideoTypeDialog(onClickListener);
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
                adapterDataModel.addAll(items);
                view.refresh();
                view.setLoaded();
              }, throwable -> {
                Timber.d(throwable);
              });
      compositeDisposable.add(disposable);
    }
  }
}

