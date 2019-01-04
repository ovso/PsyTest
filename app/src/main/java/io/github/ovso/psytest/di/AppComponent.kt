package io.github.ovso.psytest.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import io.github.ovso.psytest.App

@Component(
    modules = arrayOf(
        AndroidSupportInjectionModule::class, AppModule::class, ActivityBuilder::class,
        FragmentBuilder::class
    )
)
interface AppComponent : AndroidInjector<DaggerApplication> {
  @Component.Builder
  interface Builder {
    @BindsInstance fun application(application: Application): Builder

    fun build(): AppComponent
  }

  fun inject(app: App)
}