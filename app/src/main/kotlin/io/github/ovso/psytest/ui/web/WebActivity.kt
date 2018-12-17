package io.github.ovso.psytest.ui.web

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.github.ovso.psytest.R
import kotlinx.android.synthetic.main.activity_web.toolbar

class WebActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_web)
    setSupportActionBar(toolbar)
  }
}