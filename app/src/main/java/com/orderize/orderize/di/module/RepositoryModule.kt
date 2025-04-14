package com.orderize.orderize.di.module

import com.orderize.orderize.repository.CommonRepository
import com.orderize.orderize.repository.login.LoginRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { LoginRepository(get(), get(), get()) }
}