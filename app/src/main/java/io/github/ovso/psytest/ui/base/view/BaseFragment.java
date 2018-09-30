package io.github.ovso.psytest.ui.base.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.InterstitialAd;
import dagger.android.support.DaggerFragment;

public abstract class BaseFragment extends DaggerFragment {
  private Unbinder unbinder;
  protected InterstitialAd interstitialAd;

  @Nullable @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(getLayoutResID(), container, false);
  }

  @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    onActivityCreate(savedInstanceState);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    unbinder = ButterKnife.bind(this, view);
    interstitialAd = MyAdView.getAdmobInterstitialAd(getContext());
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    unbinder.unbind();
  }

  protected abstract int getLayoutResID();

  protected abstract void onActivityCreate(Bundle savedInstanceState);
}