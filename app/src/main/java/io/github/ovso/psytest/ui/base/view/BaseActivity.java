package io.github.ovso.psytest.ui.base.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.DaggerAppCompatActivity;
import io.github.ovso.psytest.R;

public abstract class BaseActivity extends DaggerAppCompatActivity {
  protected @BindView(R.id.toolbar) Toolbar toolbar;
  private Unbinder bind;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(getLayoutResID());
    bind = ButterKnife.bind(this);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayShowTitleEnabled(isTitle());
  }

  protected abstract int getLayoutResID();

  @Override protected void onDestroy() {
    if (bind != null) {
      bind.unbind();
    }
    super.onDestroy();
  }

  public boolean isTitle() {
    return true;
  }
}