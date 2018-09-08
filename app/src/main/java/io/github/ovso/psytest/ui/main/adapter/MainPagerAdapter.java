package io.github.ovso.psytest.ui.main.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import io.github.ovso.psytest.ui.main.fragment.VideoFragment;
import java.util.ArrayList;
import java.util.List;

public class MainPagerAdapter extends FragmentPagerAdapter
    implements MainAdapterDataModel, MainAdapterView {
  private List<Fragment> items = new ArrayList<>();

  public MainPagerAdapter(FragmentManager fm) {
    super(fm);
  }

  @Override public Fragment getItem(int index) {
    return items.get(index);
  }

  @Override public int getCount() {
    return getSize();
  }

  @Override public void refresh() {
    notifyDataSetChanged();
  }

  @Override public void createItems(int size) {
    for (int i = 0; i < size; i++) {
      Bundle args = new Bundle();
      args.putInt("position", i);
      items.add(VideoFragment.newInstance(args));
    }
  }

  @Override public int getSize() {
    return items.size();
  }
}