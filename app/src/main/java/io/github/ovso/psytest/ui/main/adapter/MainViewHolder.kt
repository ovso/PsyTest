package io.github.ovso.psytest.ui.main.adapter

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import io.github.ovso.psytest.BR

class MainViewHolder(parent: ViewGroup, @LayoutRes resId: Int) : ViewHolder(
    LayoutInflater.from(parent.context).inflate(resId, parent, false)
) {

  private val binding = DataBindingUtil.bind<ViewDataBinding>(itemView)

  fun onBindViewHolder(item: MainItem) {
    binding?.run {
      setVariable(BR.item, item)
      executePendingBindings()
    }
  }
}