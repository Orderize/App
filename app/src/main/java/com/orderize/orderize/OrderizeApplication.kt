package com.orderize.orderize

import android.app.Application
import android.util.Log
import com.orderize.orderize.di.module.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class OrderizeApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Log.i("Application", "api url = ${BuildConfig.ORDERIZE_BASE_URL}")

        startKoin {
            androidLogger()
            androidContext(this@OrderizeApplication)
            modules(appModule)
        }
    }
}