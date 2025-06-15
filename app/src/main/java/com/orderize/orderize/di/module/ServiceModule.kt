package com.orderize.orderize.di.module

import com.orderize.orderize.repository.login.network.LoginService
import com.orderize.orderize.repository.reset_password.network.ResetPasswordService
import org.koin.dsl.module

val serviceModule = module {
    single { LoginService(get()) }
    single { ResetPasswordService(get()) }
}