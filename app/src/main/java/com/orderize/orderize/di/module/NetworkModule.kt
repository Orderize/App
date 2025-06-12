package com.orderize.orderize.di.module

import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.orderize.orderize.BuildConfig
import com.orderize.orderize.repository.drink.network.IDrinkService
import com.orderize.orderize.repository.login.network.ILoginService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single<Interceptor> {
        Interceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhdXRoIiwic3ViIjoia290bGluQGdtYWlsLmNvbSIsInVzZXJJZCI6MSwibmFtZSI6IktvdGxpbmF0b3Igb2YgbWV0YWwiLCJleHAiOjE3NDgwNTc5ODZ9.OcXPi5fVULUdKll6SH8ZkArbUGY3RcCVnA3EjC3WDt0")
            val request = requestBuilder.build()
            chain.proceed(request)
        }
    }

    single {
        val client = OkHttpClient.Builder()
            .addInterceptor(get<Interceptor>())
            .apply {
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

                    addInterceptor(chuckerInterceptor)
                }
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
    single { get<Retrofit>().create(IDrinkService::class.java) }
}