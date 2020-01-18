package io.github.ovso.psytest.ui.base.view

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.app_bar_main.toolbar

abstract class BaseActivity : DaggerAppCompatActivity() {

  protected abstract val layoutResID: Int

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layoutResID)
    setSupportActionBar(toolbar)
    supportActionBar?.setDisplayShowTitleEnabled(true)
  }

}