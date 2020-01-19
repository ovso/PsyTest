package io.github.ovso.psytest.ui.base.view

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.app_bar_main.toolbar
import timber.log.Timber

abstract class BaseActivity : DaggerAppCompatActivity() {

  protected abstract val layoutResID: Int

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layoutResID)
    toolbar?.let { setSupportActionBar(it) }
    Timber.d("toolbar = $toolbar")
    supportActionBar?.setDisplayShowTitleEnabled(true)
  }

}