package com.itdeveapps.newsly.base.schedulers

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by omaraltamimi on 01,November,2019
 */
class SchedulerProvider @Inject constructor() : BaseSchedulerProvider {

    /**
     * IO thread pool scheduler
     */
    override fun io(): Scheduler {
        return Schedulers.io()
    }

    /**
     * Computation thread pool scheduler
     */
    override fun computation(): Scheduler {
        return Schedulers.computation()
    }

    /**
     * Main Thread scheduler
     */
    override fun ui(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}