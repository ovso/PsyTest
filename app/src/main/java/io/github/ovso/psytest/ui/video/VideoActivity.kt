package io.github.ovso.psytest.ui.video

import android.os.Bundle
import android.view.MenuItem
import io.github.ovso.psytest.R
import io.github.ovso.psytest.ui.base.view.BaseActivity

class VideoActivity : BaseActivity() {
  override val layoutResID: Int
    get() = R.layout.activity_video

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
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    finish()
    return super.onOptionsItemSelected(item)
  }

}
