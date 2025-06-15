package com.orderize.orderize.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orderize.orderize.model.enum.OrderStatus
import com.orderize.orderize.repository.order.OrderRepository
import com.orderize.orderize.repository.profile.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate

class ProfileViewModel(
    private val repository: ProfileRepository,
    private val orderRepository: OrderRepository
): ViewModel() {

    private val _uiState: MutableStateFlow<ProfileScreenUiState> = MutableStateFlow(ProfileScreenUiState())
    val uiState get() = _uiState.asStateFlow()

    init {
        _uiState.update { currentState ->
            currentState.copy(
                logout = { logout() },
                getOrdersData = { getOrdersData() }
            )
        }
        getUserData()
    }

    private fun getUserData() {
        viewModelScope.launch(Dispatchers.IO) {
            val userData = repository.getUserData()

            if (userData != null) {
                _uiState.update { currentState ->
                    currentState.copy(
                        name = userData.name,
                        role = if (userData.role != "SALOON") "Pizzaiolo" else "Atendente",
                        companyName = "Testingzzaria"
                    )
                }
            }
        }
    }

    private fun logout() {
        viewModelScope.launch(Dispatchers.IO) {
            val logout = repository.logout()
            if (logout) {
                _uiState.update { currentState ->
                    currentState.copy(userDisconnected = true)
                }
            }
        }
    }

    private fun getOrdersData() {
        viewModelScope.launch(Dispatchers.IO) {
            val orders = orderRepository.getOrdersLocal().filter { it.creationDate.equals(LocalDate.now()) }
            val pendingOrders = orders.filter { it.status == OrderStatus.PENDENTE.databaseName }.count().toLong()
            val preparingOrders = orders.filter { it.status == OrderStatus.EM_PREPARO.databaseName }.count().toLong()
            val finishedOrders = orders.filter { it.status == OrderStatus.FINALIZADO.databaseName }
            val qttPizzas = finishedOrders.sumOf { it.pizzas.size }.toLong()
            _uiState.update { currentState  ->
                currentState.copy(
                    pendingOrders =  pendingOrders,
                    preparingOrders = preparingOrders,
                    finishedOrders = finishedOrders.count().toLong(),
                    qttPizzas = qttPizzas
                )
            }
        }
    }
}