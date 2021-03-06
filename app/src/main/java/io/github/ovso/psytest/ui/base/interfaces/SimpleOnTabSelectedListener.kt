package io.github.ovso.psytest.ui.base.interfaces

import android.support.design.widget.TabLayout

abstract class SimpleOnTabSelectedListener : TabLayout.OnTabSelectedListener {

  override fun onTabUnselected(tab: TabLayout.Tab) {}

  override fun onTabReselected(tab: TabLayout.Tab) {}
}