package com.orderize.orderize.di.module

import com.orderize.orderize.repository.login.network.LoginService
import org.koin.dsl.module

val serviceModule = module {
    single { LoginService(get()) }
}