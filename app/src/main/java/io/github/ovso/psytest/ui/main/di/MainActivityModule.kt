package io.github.ovso.psytest.ui.main.di

import android.arch.lifecycle.Lifecycle
import dagger.Module
import dagger.Provides
import io.github.ovso.psytest.ui.main.MainActivity
import io.github.ovso.psytest.ui.main.MainPresenter
import io.github.ovso.psytest.ui.main.MainPresenterImpl
import io.github.ovso.psytest.ui.main.adapter.MainRvAdapter
import io.github.ovso.psytest.utils.ResourceProvider
import io.github.ovso.psytest.utils.SchedulersFacade
import javax.inject.Singleton

@Module
class MainActivityModule {

  @Singleton @Provides internal fun provideMainPresenter(
    view: MainPresenter.View,
    resourceProvider: ResourceProvider,
    lifecycle: Lifecycle,
    schedulers: SchedulersFacade
  ): MainPresenter {
    val presenter = MainPresenterImpl(view, resourceProvider, schedulers)
    lifecycle.addObserver(presenter)
    return presenter
  }

  @Provides internal fun provideMainLifecycle(act: MainActivity): Lifecycle {
    return act.lifecycle
  }

  @Singleton @Provides internal fun provideMainAdapter(): MainRvAdapter {
    return MainRvAdapter()
  }
}