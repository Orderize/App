package com.orderize.orderize.di.module

import com.orderize.orderize.repository.AppDataStore
import org.koin.dsl.module


val dataStoreModule = module {
    single { AppDataStore(get()) }
}