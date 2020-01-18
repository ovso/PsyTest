package io.github.ovso.psytest.di

import android.app.Application
import android.content.Context
import com.google.android.gms.ads.AdRequest
import dagger.Module
import dagger.Provides

@Module
class AppModule {
  @Provides internal fun provideContext(application: Application): Context {
    return application
  }

  @Provides fun provideAdRequest() = AdRequest.Builder().build()
}
