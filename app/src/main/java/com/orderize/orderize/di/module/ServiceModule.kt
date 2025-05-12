package com.orderize.orderize.di.module

import com.orderize.orderize.repository.drink.network.DrinkService
import com.orderize.orderize.repository.login.network.LoginService
import org.koin.core.scope.get
import org.koin.dsl.module

val serviceModule = module {
    single { LoginService(get()) }
    single { DrinkService(get())}
}