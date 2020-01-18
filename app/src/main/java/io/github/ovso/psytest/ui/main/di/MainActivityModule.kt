package io.github.ovso.psytest.ui.main.di

import android.arch.lifecycle.Lifecycle
import dagger.Module
import dagger.Provides
import io.github.ovso.psytest.ui.main.MainActivity
import io.github.ovso.psytest.ui.main.MainPresenter
import io.github.ovso.psytest.ui.main.MainPresenterImpl
import io.github.ovso.psytest.ui.main.adapter.MainAdapterDataModel
import io.github.ovso.psytest.ui.main.adapter.MainAdapterView
import io.github.ovso.psytest.ui.main.adapter.MainPagerAdapter
import io.github.ovso.psytest.ui.main.rvadapter.MainRvAdapter
import io.github.ovso.psytest.utils.ResourceProvider
import io.github.ovso.psytest.utils.SchedulersFacade
import javax.inject.Singleton

@Module
class MainActivityModule {

  @Singleton @Provides internal fun provideMainPresenter(
    view: MainPresenter.View,
    resourceProvider: ResourceProvider,
    adapterDataModel: MainAdapterDataModel,
    lifecycle: Lifecycle, schedulers: SchedulersFacade
  ): MainPresenter {
    val presenter = MainPresenterImpl(view, resourceProvider, adapterDataModel,schedulers)
    lifecycle.addObserver(presenter)
    return presenter
  }

  @Singleton @Provides
  internal fun provideMainPagerAdapter(activity: MainActivity): MainPagerAdapter {
    return MainPagerAdapter(activity.supportFragmentManager)
  }

  @Provides
  internal fun provideMainAdapterDataModel(adapter: MainPagerAdapter): MainAdapterDataModel {
    return adapter
  }

  @Provides internal fun provideMainAdapterView(adapter: MainPagerAdapter): MainAdapterView {
    return adapter
  }

  @Provides internal fun provideMainLifecycle(act: MainActivity): Lifecycle {
    return act.lifecycle
  }

  @Singleton @Provides internal fun provideMainAdapter(): MainRvAdapter {
    return MainRvAdapter()
  }
}