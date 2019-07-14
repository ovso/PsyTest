package io.github.ovso.psytest

enum class Security(val value: String) {
  KEY("AIzaSyDsNxMYr8ytQEucWEr03erQnMgWFkwxJVQ"),

  ADMOB_APP_ID(
      if (BuildConfig.DEBUG)
        "ca-app-pub-3940256099942544~3347511713"
      else
        "ca-app-pub-8679189423397017~4129853213"
  ),

  ADMOB_UNIT_ID(
      if (BuildConfig.DEBUG)
        "ca-app-pub-3940256099942544/6300978111"
      else
        "ca-app-pub-8679189423397017/1419929392"
  ),

  ADMOB_INTERSTITIAL_UNIT_ID(
      if (BuildConfig.DEBUG)
        "ca-app-pub-3940256099942544/1033173712"
      else
        "ca-app-pub-8679189423397017/7143029364"
  )

}