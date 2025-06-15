package com.orderize.orderize.di.module

import com.orderize.orderize.repository.CommonRepository
import com.orderize.orderize.repository.GeminiRepository
import com.orderize.orderize.repository.login.LoginRepository
import com.orderize.orderize.repository.order.OrderRepository
import com.orderize.orderize.repository.profile.ProfileRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { LoginRepository(get(), get(), get()) }
    single { ProfileRepository(get(), get()) }
    single { GeminiRepository() }
    single { OrderRepository(get(), get(), get()) }
}