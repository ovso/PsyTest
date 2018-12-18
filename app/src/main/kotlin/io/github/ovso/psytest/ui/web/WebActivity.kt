package io.github.ovso.psytest.ui.web

import android.graphics.Bitmap
import android.os.Bundle
import android.view.MenuItem
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import io.github.ovso.psytest.R
import io.github.ovso.psytest.ui.base.view.BaseActivity
import kotlinx.android.synthetic.main.content_web.progressbar_web
import kotlinx.android.synthetic.main.content_web.webview_web

class WebActivity : BaseActivity() {
  override fun getLayoutResID(): Int {
    return R.layout.activity_web
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setupActionBar()
    setupWebView()
    webview_web.loadUrl(intent.getStringExtra("url"));
  }

  private fun setupActionBar() {
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    finish()
    return super.onOptionsItemSelected(item)
  }

  private fun setupWebView() {
    var settings = webview_web.settings
    settings.javaScriptEnabled = true
    settings.domStorageEnabled = true
    settings.setAppCacheEnabled(true)
    settings.javaScriptCanOpenWindowsAutomatically = true
    settings.setGeolocationEnabled(true)
    settings.cacheMode = WebSettings.LOAD_NO_CACHE

    webview_web.webChromeClient = WebChromeClient()
    webview_web.webViewClient = WebViewClient()
    //webview_web.webChromeClient

  }

  private inner class MyWebChromeClient : WebChromeClient() {
    override fun onProgressChanged(
      view: WebView,
      newProgress: Int
    ) {
      progressbar_web.setProgress(newProgress)
      super.onProgressChanged(view, newProgress)
    }
  }

  private inner class MyWebViewClient : WebViewClient() {

    override fun onPageStarted(
      view: WebView,
      url: String,
      favicon: Bitmap
    ) {
      progressbar_web.show()
      super.onPageStarted(view, url, favicon)
    }

    override fun onPageFinished(
      view: WebView,
      url: String
    ) {
      progressbar_web.hide()
      //updateWebNaviButton()
      super.onPageFinished(view, url)
    }
  }

  override fun isTitle(): Boolean {
    return true
  }
}