package io.github.ovso.psytest.utils

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TestSchedulers @Inject
constructor() {

  fun io(): Scheduler {
    return Schedulers.trampoline()
  }

  fun ui(): Scheduler {
    return return Schedulers.trampoline()
  }
}
