package com.orderize.orderize.di.module

import com.orderize.orderize.ui.login.LoginViewModel
import com.orderize.orderize.ui.orderdetails.OrderDetailsViewModel
import com.orderize.orderize.ui.pizzaiolo_home.PizzaioloHomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { PizzaioloHomeViewModel() }
    viewModel { OrderDetailsViewModel() }
}