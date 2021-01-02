package com.moichsan.consumerapp.util

import io.reactivex.Scheduler

interface SchedulerProviders {
    fun ui(): Scheduler
    fun computation(): Scheduler
    fun trampoline(): Scheduler
    fun newThread(): Scheduler
    fun io(): Scheduler
}