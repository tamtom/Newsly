package com.itdeveapps.newsly.base.schedulers

import io.reactivex.Scheduler

/**
 * Allow providing different types of {@link Scheduler}s.
 */
interface BaseSchedulerProvider {
    fun computation(): Scheduler

    fun io(): Scheduler

    fun ui(): Scheduler
}