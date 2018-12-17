package io.github.ovso.psytest.ui.web

import android.os.Bundle
import android.webkit.WebView
import io.github.ovso.psytest.R
import io.github.ovso.psytest.ui.base.view.BaseActivity

class WebActivity : BaseActivity() {
  override fun getLayoutResID(): Int {
    return R.layout.activity_web
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    var webView = findViewById(R.id.webview_web) as WebView
    webView.loadUrl(intent.getStringExtra("url"))
  }
}