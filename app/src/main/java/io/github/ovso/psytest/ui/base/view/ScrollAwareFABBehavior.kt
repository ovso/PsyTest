package io.github.ovso.psytest.ui.base.view

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.FloatingActionButton
import android.support.v4.view.NestedScrollingChild
import android.util.AttributeSet
import android.view.View
import timber.log.Timber

class ScrollAwareFABBehavior(
  context: Context,
  attrs: AttributeSet
) : FloatingActionButton.Behavior(context, attrs) {

  override fun onStartNestedScroll(
    coordinatorLayout: CoordinatorLayout,
    child: FloatingActionButton,
    directTargetChild: View,
    target: View,
    axes: Int,
    type: Int
  ): Boolean {
//    return axes == ViewCompat.SCROLL_AXIS_VERTICAL ||
//        super.onStartNestedScroll(
//            coordinatorLayout, child, directTargetChild, target,
//            axes, type
//        )
    return true
  }

  override fun onStopNestedScroll(
    coordinatorLayout: CoordinatorLayout,
    child: FloatingActionButton,
    target: View,
    type: Int
  ) {
    super.onStopNestedScroll(coordinatorLayout, child, target, type)
    Timber.d("onStopNestedScroll")
  }

  override fun layoutDependsOn(
    parent: CoordinatorLayout,
    child: FloatingActionButton,
    dependency: View
  ): Boolean {
    return super.layoutDependsOn(parent, child, dependency) || dependency is NestedScrollingChild;
  }

  override fun onNestedScroll(
    coordinatorLayout: CoordinatorLayout,
    child: FloatingActionButton,
    target: View,
    dxConsumed: Int,
    dyConsumed: Int,
    dxUnconsumed: Int,
    dyUnconsumed: Int,
    type: Int
  ) {
    super.onNestedScroll(
        coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type
    )

    Timber.d("dyConsumed = $dyConsumed")
    Timber.d("dyUnconsumed = $dyUnconsumed")
    Timber.d("child = $child")
    if (dyConsumed > 0 && child.visibility == View.VISIBLE) {
      child.hide()
    } else if (dyConsumed < 0 && child.visibility != View.VISIBLE) {
      child.show()
    }
  }
}