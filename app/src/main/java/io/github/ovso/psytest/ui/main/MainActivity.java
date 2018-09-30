package io.github.ovso.psytest.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.ViewGroup;
import butterknife.BindView;
import io.github.ovso.psytest.R;
import io.github.ovso.psytest.ui.base.view.BaseActivity;
import io.github.ovso.psytest.ui.base.view.MyAdView;
import io.github.ovso.psytest.ui.main.adapter.MainAdapterView;
import io.github.ovso.psytest.ui.main.adapter.MainPagerAdapter;
import javax.inject.Inject;

public class MainActivity extends BaseActivity
    implements NavigationView.OnNavigationItemSelectedListener, MainPresenter.View {

  @BindView(R.id.tab_layout) TabLayout tabLayout;
  @BindView(R.id.view_pager) ViewPager viewPager;
  @BindView(R.id.ad_container) ViewGroup adContainer;

  @Inject MainPresenter presenter;
  @Inject MainPagerAdapter pagerAdapter;
  @Inject MainAdapterView adapterView;

  @Override protected int getLayoutResID() {
    return R.layout.activity_main;
  }

  @Override protected void onCreated(@Nullable Bundle savedInstanceState) {
    presenter.onCreate();
  }

  @Override public void setupView() {
    DrawerLayout drawer = findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    //drawer.addDrawerListener(toggle);
    //toggle.syncState();

    NavigationView navigationView = findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);
  }

  @Override public void setupTabLayout() {
    tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
    tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
  }

  @Override public boolean isTitle() {
    return true;
  }

  @Override public void setupViewPager() {
    viewPager.setAdapter(pagerAdapter);
    tabLayout.setupWithViewPager(viewPager);
  }

  @Override public void showTabName(int position, String name) {
    if (tabLayout.getTabCount() > 0) {
      tabLayout.getTabAt(position).setText(name);
    }
  }

  @Override public void refresh() {
    adapterView.refresh();
  }

  @Override public void showTitle(String title) {
    super.setTitle(title);
  }

  @Override public void showBannerAd() {
    adContainer.addView(MyAdView.getAdmobAdView(getApplicationContext()));
  }

  @Override public void changeTheme() {
    setTheme(R.style.AppTheme_NoActionBar);
  }

  @Override
  public void onBackPressed() {
    DrawerLayout drawer = findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @SuppressWarnings("StatementWithEmptyBody")
  @Override
  public boolean onNavigationItemSelected(MenuItem item) {
    // Handle navigation view item clicks here.
    int id = item.getItemId();

    if (id == R.id.nav_camera) {
      // Handle the camera action
    } else if (id == R.id.nav_gallery) {

    } else if (id == R.id.nav_slideshow) {

    } else if (id == R.id.nav_manage) {

    } else if (id == R.id.nav_share) {

    } else if (id == R.id.nav_send) {

    }

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }
}
