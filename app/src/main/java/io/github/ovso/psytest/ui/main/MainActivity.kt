package io.github.ovso.psytest.ui.main

import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import com.google.android.gms.ads.AdRequest
import io.github.ovso.psytest.R
import io.github.ovso.psytest.ui.base.view.BaseActivity
import io.github.ovso.psytest.ui.main.adapter.MainItem
import io.github.ovso.psytest.ui.main.adapter.MainRvAdapter
import kotlinx.android.synthetic.main.activity_main.nav_view
import kotlinx.android.synthetic.main.content_main.rv_main
import kotlinx.android.synthetic.main.layout_ads_banner.all_ads_banner
import javax.inject.Inject

class MainActivity : BaseActivity(),
    NavigationView.OnNavigationItemSelectedListener,
    MainPresenter.View {
  override val layoutResID: Int
    get() = R.layout.activity_main

  @Inject
  lateinit var presenter: MainPresenter

  @Inject
  lateinit var adRequest: AdRequest

  @Inject
  lateinit var adapter: MainRvAdapter

  override fun setupView() {
//    val toggle = ActionBarDrawerToggle(
//        this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
//    )
    //drawer.addDrawerListener(toggle);
    //toggle.syncState();

    nav_view.setNavigationItemSelectedListener(this)
  }

  override fun showTitle(title: String) {
    super.setTitle(title)
  }

  override fun setupRv() {
    with(rv_main) {
      addItemDecoration(DividerItemDecoration(baseContext, RecyclerView.VERTICAL))
      adapter = this@MainActivity.adapter
    }
  }

  override fun loadAd() {
    all_ads_banner.loadAd(adRequest)
  }

  override fun submitList(it: List<MainItem>) {
    adapter.submitList(it)
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

    when (id) {
      R.id.nav_camera -> {
        // Handle the camera action
      }
      R.id.nav_gallery -> {

      }
      R.id.nav_slideshow -> {

      }
      R.id.nav_manage -> {

      }
      R.id.nav_share -> {

      }
      R.id.nav_send -> {

      }
    }

    val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
    drawer.closeDrawer(GravityCompat.START)
    return true
  }

}
