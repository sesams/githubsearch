package com.drama.domain.executor

import io.reactivex.Scheduler

interface ExecutionThread {
    fun getScheduler(): Scheduler
}