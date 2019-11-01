package com.itdeveapps.newsly.base.schedulers

import com.itdeveapps.newsly.base.schedulers.BaseSchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

/**
 * Implementation of the {@see BaseSchedulerProvider} making all {@link Scheduler}s execute
 * synchronously so we can easily run assertions in our tests.
 * <p>
 * To achieve this, we are using the {@link io.reactivex.internal.schedulers.TrampolineScheduler} from the {@link Schedulers} class.
 */
class ImmediateSchedulerProvider : BaseSchedulerProvider {
    /**
     * IO thread pool scheduler
     */
    override fun io(): Scheduler {
        return Schedulers.trampoline()
    }

    /**
     * Computation thread pool scheduler
     */
    override fun computation(): Scheduler {
        return Schedulers.trampoline()
    }

    /**
     * Main Thread scheduler
     */
    override fun ui(): Scheduler {
        return Schedulers.trampoline()
    }
}