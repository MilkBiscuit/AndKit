package com.cheng.httpproject.util

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

fun<T> Observable<T>.applySchedulers(): Observable<T> {
    return this.subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
}

fun<T> Observable<T>.debounceOneSecond(): Observable<T> =
        this.debounce(1L, TimeUnit.SECONDS)

