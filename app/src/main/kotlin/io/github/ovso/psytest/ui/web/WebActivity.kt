package io.github.ovso.psytest.ui.web

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.MenuItem
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import io.github.ovso.psytest.R
import io.github.ovso.psytest.ui.base.view.AdsBaseActivity
import io.github.ovso.psytest.ui.base.view.MyAdView
import kotlinx.android.synthetic.main.content_web.progressbar_web
import kotlinx.android.synthetic.main.content_web.webview_web
import kotlinx.android.synthetic.main.layout_banner_container.framelayout_all_adscontainer
import kotlinx.android.synthetic.main.navigation_web.button_web_back
import kotlinx.android.synthetic.main.navigation_web.button_web_browser
import kotlinx.android.synthetic.main.navigation_web.button_web_forward
import kotlinx.android.synthetic.main.navigation_web.button_web_refresh
import kotlinx.android.synthetic.main.navigation_web.button_web_share

class WebActivity : AdsBaseActivity() {
  override fun getLayoutResID(): Int {
    return R.layout.activity_web
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setupActionBar()
    setupWebView()
    setupNaviEvent()
    showBannerAds();
    webview_web.loadUrl(intent.getStringExtra("url"));
  }

  private fun showBannerAds() {
    framelayout_all_adscontainer.addView(MyAdView.getAdmobAdView(this))
  }

  private fun setupActionBar() {
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    title = intent.getStringExtra("title");
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

    webview_web.webChromeClient = MyWebChromeClient()
    webview_web.webViewClient = MyWebViewClient()
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
      favicon: Bitmap?
    ) {
      progressbar_web.show()
      super.onPageStarted(view, url, favicon)
    }

    override fun onPageFinished(
      view: WebView?,
      url: String?
    ) {
      progressbar_web.hide()
      updateWebNaviButton()
      super.onPageFinished(view, url)
    }
  }

  override fun isTitle(): Boolean {
    return true
  }

  private fun updateWebNaviButton() {
    if (webview_web.canGoBack()) {
      val color = ContextCompat.getColor(this, android.R.color.black)
      button_web_back.setImageTintList(ColorStateList.valueOf(color))
      button_web_back.setClickable(true)
    } else {
      val color = ContextCompat.getColor(this, android.R.color.darker_gray)
      button_web_back.setImageTintList(ColorStateList.valueOf(color))
      button_web_back.setClickable(false)
    }

    if (webview_web.canGoForward()) {
      val color = ContextCompat.getColor(this, android.R.color.black)
      button_web_forward.setImageTintList(ColorStateList.valueOf(color))
      button_web_forward.setClickable(true)
    } else {
      val color = ContextCompat.getColor(this, android.R.color.darker_gray)
      button_web_forward.setImageTintList(ColorStateList.valueOf(color))
      button_web_forward.setClickable(false)
    }
  }

  private fun setupNaviEvent() {
    button_web_back.setOnClickListener {
      if (webview_web.canGoBack()) {
        webview_web.goBack()
      }
    }
    button_web_forward.setOnClickListener {
      if (webview_web.canGoForward()) {
        webview_web.goForward()
      }
    }
    button_web_refresh.setOnClickListener { webview_web.reload() }
    button_web_share.setOnClickListener { navigateToShare() }
    button_web_browser.setOnClickListener { navigateToBrowser() }
  }

  fun navigateToBrowser() {
    val intent = Intent(Intent.ACTION_VIEW)
    intent.data = Uri.parse(webview_web.getUrl())
    startActivity(intent)
  }

  fun navigateToShare() {
    val intent = Intent()
    intent.action = Intent.ACTION_SEND
    intent.putExtra(Intent.EXTRA_TEXT, webview_web.getUrl())
    intent.type = "text/plain"
    startActivity(Intent.createChooser(intent, resources.getString(R.string.web_share)))
  }

  override fun onStop() {
    webview_web.onPause()
    webview_web.pauseTimers()
    super.onStop()
  }

  override fun onResume() {
    webview_web.onResume()
    webview_web.resumeTimers()
    super.onResume()
  }
}