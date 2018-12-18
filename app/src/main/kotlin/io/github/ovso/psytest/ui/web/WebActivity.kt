package io.github.ovso.psytest.ui.web

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
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
    setupWebView();
    webview_web.loadUrl(intent.getStringExtra("url"));
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
}

/*


    webView.getSettings().setAppCacheEnabled(true);
    webView.getSettings().setSupportMultipleWindows(true);
    webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
    webView.getSettings().setGeolocationEnabled(true);
    webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
    webView.setWebChromeClient(new MyWebChromeClient());
    webView.setWebViewClient(new MyWebViewClient());


 */