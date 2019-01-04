package io.github.ovso.psytest.ui.main

import android.support.design.widget.NavigationView
import android.support.design.widget.TabLayout
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.view.MenuItem
import io.github.ovso.psytest.R
import io.github.ovso.psytest.ui.base.view.BaseActivity
import io.github.ovso.psytest.ui.base.view.MyAdView
import io.github.ovso.psytest.ui.main.adapter.MainAdapterView
import io.github.ovso.psytest.ui.main.adapter.MainPagerAdapter
import kotlinx.android.synthetic.main.activity_main.nav_view
import kotlinx.android.synthetic.main.app_bar_main.tab_layout
import kotlinx.android.synthetic.main.content_main.view_pager
import kotlinx.android.synthetic.main.layout_banner_container.framelayout_all_adscontainer
import javax.inject.Inject

class MainActivity : BaseActivity(),
    NavigationView.OnNavigationItemSelectedListener,
    MainPresenter.View {
  override val layoutResID: Int
    get() = R.layout.activity_main

  var presenter: MainPresenter? = null
    @Inject set
  var pagerAdapter: MainPagerAdapter? = null
    @Inject set
  var adapterView: MainAdapterView? = null
    @Inject set


  override fun setupView() {
//    val toggle = ActionBarDrawerToggle(
//        this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
//    )
    //drawer.addDrawerListener(toggle);
    //toggle.syncState();

    nav_view.setNavigationItemSelectedListener(this)
  }

  override fun setupTabLayout() {
    tab_layout.tabGravity = TabLayout.GRAVITY_CENTER
    tab_layout.tabMode = TabLayout.MODE_SCROLLABLE
  }

  override fun setupViewPager() {
    view_pager.adapter = pagerAdapter
    tab_layout.setupWithViewPager(view_pager)
  }

  override fun showTabName(
    position: Int,
    name: String
  ) {
    if (tab_layout.tabCount > 0) {
      tab_layout.getTabAt(position)!!.text = name
    }
  }

  override fun refresh() {
    adapterView!!.refresh()
  }

  override fun showTitle(title: String) {
    super.setTitle(title)
  }

  override fun showBannerAd() {
    framelayout_all_adscontainer.addView(MyAdView.getAdmobAdView(applicationContext))
  }

  override fun changeTheme() {
    setTheme(R.style.AppTheme_NoActionBar)
  }

  override fun onBackPressed() {
    val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START)
    } else {
      super.onBackPressed()
    }
  }

  override fun onNavigationItemSelected(item: MenuItem): Boolean {
    // Handle navigation view item clicks here.
    val id = item.itemId

    if (id == R.id.nav_camera) {
      // Handle the camera action
    } else if (id == R.id.nav_gallery) {

    } else if (id == R.id.nav_slideshow) {

    } else if (id == R.id.nav_manage) {

    } else if (id == R.id.nav_share) {

    } else if (id == R.id.nav_send) {

    }

    val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
    drawer.closeDrawer(GravityCompat.START)
    return true
  }
}
