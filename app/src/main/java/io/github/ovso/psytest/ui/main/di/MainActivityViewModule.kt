package io.github.ovso.psytest.ui.main.di

import dagger.Binds
import dagger.Module
import io.github.ovso.psytest.ui.main.MainActivity
import io.github.ovso.psytest.ui.main.MainPresenter

@Module
abstract class MainActivityViewModule {

  @Binds internal abstract fun bindMainView(activity: MainActivity): MainPresenter.View
}