package com.cheng.httpproject.util

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

fun<T> Observable<T>.applySchedulers(): Observable<T> {
    return this.subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
}

fun<T> Observable<T>.debounceHalfSecond(): Observable<T> =
        this.debounce(500L, TimeUnit.MILLISECONDS)

