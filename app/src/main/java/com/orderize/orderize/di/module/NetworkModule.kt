package com.orderize.orderize.di.module

import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.orderize.orderize.BuildConfig
import com.orderize.orderize.repository.login.network.ILoginService
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        val client = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val context = androidContext()
            val chuckerCollector = ChuckerCollector(
                context = context,
                showNotification = true,
                retentionPeriod = RetentionManager.Period.ONE_HOUR
            )

            val chuckerInterceptor = ChuckerInterceptor.Builder(context)
                .collector(chuckerCollector)
                .maxContentLength(250_000L)
                .redactHeaders("Authorization")
                .alwaysReadResponseBody(true)
                .createShortcut(true)
                .build()
            client.addInterceptor(chuckerInterceptor)
        }
        client.build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.ORDERIZE_BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single { get<Retrofit>().create(ILoginService::class.java) }
}