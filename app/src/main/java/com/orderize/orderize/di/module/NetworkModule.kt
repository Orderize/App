package com.orderize.orderize.di.module

import android.util.Log
import com.orderize.orderize.BuildConfig
import com.orderize.orderize.repository.login.LoginService
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.ORDERIZE_BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single { get<Retrofit>().create(LoginService::class.java) }
}