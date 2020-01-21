package io.github.ovso.psytest.ui.player

import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.ads.AdRequest
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerFragment
import io.github.ovso.psytest.R
import io.github.ovso.psytest.Security
import io.github.ovso.psytest.ui.base.view.AdsActivity
import kotlinx.android.synthetic.main.activity_fullscreen_video.youtube_fragment
import kotlinx.android.synthetic.main.layout_ads_banner.all_ads_banner

class PlayerActivity : AdsActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_fullscreen_video)
    if (intent.hasExtra("video_id")) {
      (youtube_fragment as YouTubePlayerFragment).initialize(Security.KEY.value,
          object : YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess(
              provider: YouTubePlayer.Provider,
              youTubePlayer: YouTubePlayer,
              b: Boolean
            ) {
              youTubePlayer.loadVideo(intent.getStringExtra("video_id"))
            }

            override fun onInitializationFailure(
              provider: YouTubePlayer.Provider,
              youTubeInitializationResult: YouTubeInitializationResult
            ) {
            }
          })
    } else {
      Toast.makeText(this, R.string.no_videos_found, Toast.LENGTH_SHORT)
          .show()
      finish()
    }

    all_ads_banner.loadAd(AdRequest.Builder().build())

  }
}
