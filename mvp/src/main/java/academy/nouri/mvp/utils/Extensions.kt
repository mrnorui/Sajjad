package academy.nouri.mvp.utils

import android.content.Context
import android.net.ConnectivityManager
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun Context.isNetworkAvailable(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connectivityManager.activeNetworkInfo
    return networkInfo != null && networkInfo.isConnected
}

fun <T> Single<T>.applyScheduler(scheduler: Scheduler) = subscribeOn(scheduler).observeOn(AndroidSchedulers.mainThread())
fun <T> Single<T>.applyIoScheduler() = applyScheduler(Schedulers.io())