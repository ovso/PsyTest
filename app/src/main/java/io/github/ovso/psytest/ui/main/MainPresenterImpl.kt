package io.github.ovso.psytest.ui.main

import io.github.ovso.psytest.R
import io.github.ovso.psytest.ui.main.adapter.MainAdapterDataModel
import io.github.ovso.psytest.utils.ResourceProvider

class MainPresenterImpl(
  private val view: MainPresenter.View,
  private val resourceProvider: ResourceProvider,
  private val adapterDataModel: MainAdapterDataModel
) : MainPresenter {

  init {
    view.changeTheme()
  }

  override fun onCreate() {
    view.showTitle(resourceProvider.getString(R.string.app_name))
    view.setupView()
    view.setupTabLayout()
    view.setupViewPager()
    adapterDataModel.createItems(resourceProvider.getStringArray(R.array.tabs).size)
    view.refresh()
    showTabNames()
    view.showBannerAd()
  }

  private fun showTabNames() {
    val tabNames = resourceProvider.getStringArray(R.array.tabs)
    for (i in tabNames.indices) {
      view.showTabName(i, tabNames[i])
    }
  }
}