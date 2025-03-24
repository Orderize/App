package com.orderize.orderize.di.module

import org.koin.dsl.module

val appModule = module {
    includes(
        viewModelModule,
        networkModule
    )
}