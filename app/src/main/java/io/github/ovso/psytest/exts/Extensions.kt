package io.github.ovso.psytest.exts

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

operator fun CompositeDisposable.plusAssign(subscribe: Disposable?) {
  subscribe?.let {
    add(subscribe)
  }
}