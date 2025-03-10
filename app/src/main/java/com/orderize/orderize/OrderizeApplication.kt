package com.orderize.orderize

import android.app.Application
import com.orderize.orderize.di.module.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class OrderizeApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@OrderizeApplication)
            modules(appModule)
        }
    }
}