package io.github.ovso.psytest.ui.video

import android.os.Bundle
import android.view.MenuItem
import com.google.android.gms.ads.AdRequest
import io.github.ovso.psytest.R
import io.github.ovso.psytest.ui.base.view.BaseActivity
import kotlinx.android.synthetic.main.layout_ads_banner.all_ads_banner
import javax.inject.Inject

class VideoActivity : BaseActivity() {
  override val layoutResID: Int
    get() = R.layout.activity_video

  @Inject
  lateinit var adRequest: AdRequest

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    if (savedInstanceState == null) {
      supportFragmentManager.beginTransaction()
          .replace(
              R.id.container,
              VideoFragment.newInstance(
                  Bundle().apply {
                    putString("title", intent.getStringExtra("title"))
                    putString("query", intent.getStringExtra("query"))
                  }
              )
          )
          .commitNow()
    }
    all_ads_banner.loadAd(adRequest)
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    finish()
    return super.onOptionsItemSelected(item)
  }

}
