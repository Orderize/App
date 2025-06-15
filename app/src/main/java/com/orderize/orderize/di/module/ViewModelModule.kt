package com.orderize.orderize.di.module

import com.orderize.orderize.ui.common.SharedOrderViewModel
import com.orderize.orderize.ui.drinks.DrinkViewModel
import com.orderize.orderize.ui.forget_password.ForgetPasswordViewModel
import com.orderize.orderize.ui.gemini.GeminiViewModel
import com.orderize.orderize.ui.history.HistoryViewModel
import com.orderize.orderize.ui.login.LoginViewModel
import com.orderize.orderize.ui.order.OrderViewModel
import com.orderize.orderize.ui.orderdetails.OrderDetailsViewModel
import com.orderize.orderize.ui.pizzaiolo_home.PizzaioloHomeViewModel
import com.orderize.orderize.ui.profile.ProfileViewModel
import com.orderize.orderize.ui.writeOrder.WriteOrderViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { PizzaioloHomeViewModel(get()) }
    viewModel { OrderDetailsViewModel(get()) }
    viewModel { ProfileViewModel(get(),get()) }
    viewModel { OrderViewModel() }
    viewModel { DrinkViewModel() }
    viewModel { GeminiViewModel(get()) }
    viewModel { WriteOrderViewModel() }
    viewModel { SharedOrderViewModel() }
    viewModel { HistoryViewModel(get()) }
    viewModel { ForgetPasswordViewModel(get()) }
}