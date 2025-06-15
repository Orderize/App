package com.orderize.orderize.repository.order

import android.util.Log
import com.orderize.orderize.model.Drink
import com.orderize.orderize.model.Flavor
import com.orderize.orderize.model.NetResource
import com.orderize.orderize.model.Order
import com.orderize.orderize.model.Pizza
import com.orderize.orderize.model.User
import com.orderize.orderize.model.enum.OrderStatus
import com.orderize.orderize.repository.AppDataStore
import com.orderize.orderize.repository.CommonRepository
import com.orderize.orderize.repository.dao.OrderDao
import com.orderize.orderize.repository.entity.toEntity
import com.orderize.orderize.repository.entity.toOrder
import com.orderize.orderize.repository.order.network.OrderService
import com.orderize.orderize.repository.order.network.dto.OrderClientResponseDto
import com.orderize.orderize.repository.order.network.dto.OrderDrinkResponseDto
import com.orderize.orderize.repository.order.network.dto.OrderPizzaResponseDto
import com.orderize.orderize.repository.order.network.dto.OrderResponseDto
import com.orderize.orderize.repository.order.network.dto.PizzaFlavorResponseDto
import java.time.Instant
import java.time.LocalTime
import java.time.ZoneId

class OrderRepository(
    private val dataStore: AppDataStore,
    private val service: OrderService,
    private val dao: OrderDao
): CommonRepository(dataStore) {

    suspend fun putOrderInPreparingStatus(id: Long): NetResource<Order> {
        return try {
            val response = service.putOrderInPreparingStatus(
                id
            )

            if (response.isSuccessful && response.body() != null) {
                NetResource.Success(response.body()!!.toOrder())
            } else {
                var message = response.code().toString()
                if (response.body() != null) {
                    message += response.body()
                }
                if (response.message().isNotBlank()) {
                    message += response.message()
                }
                if (response.errorBody() != null) {
                    message += response.errorBody()
                }
                Log.i("LoginService", message)
                NetResource.Fail(message)
            }
        } catch (exception: Exception) {
            Log.i("LoginService", "${exception.message}")
            NetResource.Fail(exception.message ?: exception.toString())
        }
    }

    suspend fun putOrderInPendingStatus(id: Long): NetResource<Order> {
        return try {
            val response = service.putOrderInPendingStatus(
                id
            )

            if (response.isSuccessful && response.body() != null) {
                NetResource.Success(response.body()!!.toOrder())
            } else {
                var message = response.code().toString()
                if (response.body() != null) {
                    message += response.body()
                }
                if (response.message().isNotBlank()) {
                    message += response.message()
                }
                if (response.errorBody() != null) {
                    message += response.errorBody()
                }
                Log.i("LoginService", message)
                NetResource.Fail(message)
            }
        } catch (exception: Exception) {
            Log.i("LoginService", "${exception.message}")
            NetResource.Fail(exception.message ?: exception.toString())
        }
    }

    suspend fun putOrderInAvailableStatus(id: Long): NetResource<Order> {
        return try {
            val response = service.putOrderInAvailableStatus(
                id
            )

            if (response.isSuccessful && response.body() != null) {
                NetResource.Success(response.body()!!.toOrder())
            } else {
                var message = response.code().toString()
                if (response.body() != null) {
                    message += response.body()
                }
                if (response.message().isNotBlank()) {
                    message += response.message()
                }
                if (response.errorBody() != null) {
                    message += response.errorBody()
                }
                Log.i("LoginService", message)
                NetResource.Fail(message)
            }
        } catch (exception: Exception) {
            Log.i("LoginService", "${exception.message}")
            NetResource.Fail(exception.message ?: exception.toString())
        }
    }

    suspend fun getAllTodayOrders(): NetResource<List<Order>> {
        return try {
            val response = service.getAllTodayOrders()

            if (response.isSuccessful && response.body() != null) {
                NetResource.Success(response.body()!!.map { it.toOrder() })
            } else if (response.isSuccessful) {
                NetResource.Success(data = emptyList())
            } else {
                var message = response.code().toString()
                if (response.body() != null) {
                    message += response.body()
                }
                if (response.message().isNotBlank()) {
                    message += response.message()
                }
                if (response.errorBody() != null) {
                    message += response.errorBody()
                }
                Log.i("LoginService", message)
                NetResource.Fail(message)
            }
        } catch(exception: Exception) {
            Log.i("LoginService", "${exception.message}")
            NetResource.Fail(exception.message ?: exception.toString())
        }
    }

    suspend fun getAllPreparingOrders(): NetResource<List<Order>> {
        return try {
            val response = service.getAllPreparingOrders()

            if (response.isSuccessful && response.body() != null) {
                NetResource.Success(response.body()!!.map { it.toOrder() })
            } else {
                var message = response.code().toString()
                if (response.body() != null) {
                    message += response.body()
                }
                if (response.message().isNotBlank()) {
                    message += response.message()
                }
                if (response.errorBody() != null) {
                    message += response.errorBody()
                }
                Log.i("LoginService", message)
                NetResource.Fail(message)
            }
        } catch(exception: Exception) {
            Log.i("LoginService", "${exception.message}")
            NetResource.Fail(exception.message ?: exception.toString())
        }
    }

    suspend fun getAllPendingOrders(): NetResource<List<Order>> {
        return try {
            val response = service.getAllPendingOrders()

            if (response.isSuccessful && response.body() != null) {
                NetResource.Success(response.body()!!.map { it.toOrder() })
            } else {
                var message = response.code().toString()
                if (response.body() != null) {
                    message += response.body()
                }
                if (response.message().isNotBlank()) {
                    message += response.message()
                }
                if (response.errorBody() != null) {
                    message += response.errorBody()
                }
                Log.i("LoginService", message)
                NetResource.Fail(message)
            }
        } catch(exception: Exception) {
            Log.i("LoginService", "${exception.message}")
            NetResource.Fail(exception.message ?: exception.toString())
        }
    }

    suspend fun getAllAvailableOrders(): NetResource<List<Order>> {
        return try {
            val response = service.getAllAvailableOrders()

            if (response.isSuccessful && response.body() != null) {
                NetResource.Success(response.body()!!.map { it.toOrder() })
            } else {
                var message = response.code().toString()
                if (response.body() != null) {
                    message += response.body()
                }
                if (response.message().isNotBlank()) {
                    message += response.message()
                }
                if (response.errorBody() != null) {
                    message += response.errorBody()
                }
                Log.i("LoginService", message)
                NetResource.Fail(message)
            }
        } catch(exception: Exception) {
            Log.i("LoginService", "${exception.message}")
            NetResource.Fail(exception.message ?: exception.toString())
        }
    }

    suspend fun saveOrdersLocal(orders: List<Order>) {
        dao.saveOrders(orders.map { it.toEntity() })
    }

    suspend fun getOrdersLocal(): List<Order> = dao.getAllOrders().map { it.toOrder() }

    suspend fun saveOrderLocal(order: Order) {
        dao.saveOrder(order.toEntity())
    }

}

private fun OrderResponseDto.toOrder() = Order(
    id = this.id,
    client = this.client.toUser(),
    pizzas = this.pizzas.map { it.toPizza() },
    drinks = this.drinks.map { it.toDrink() },
    date = this.date.toLocalTime(),
    type = this.type,
    price = this.price,
    table = this.table ?: 1L,
    status = this.status ?: OrderStatus.PENDENTE.databaseName,
    lastModified = if (this.lastModified != null) this.lastModified.toLocalTime() else LocalTime.now()
)

private fun OrderClientResponseDto.toUser() = User(
    id = this.id,
    name = this.name,
    email = this.email,
    companyId = 0,
    role = ""
)

private fun OrderPizzaResponseDto.toPizza() = Pizza(
    id = this.id,
    name = this.name,
    price = this.price,
    observations = this.observations,
    flavors = this.flavors.map { it.toFlavor() },
    border = this.border,
    size = this.size,
    mass = this.mass
)

private fun PizzaFlavorResponseDto.toFlavor() = Flavor(
    id = this.id,
    name = this.name,
    description = this.description,
    price = this.price
)

private fun OrderDrinkResponseDto.toDrink() = Drink(
    id = this.id,
    name = this.name,
    description = this.description,
    price = this.price,
    milimeters = this.milimeters
)

fun String.toLocalTime(): LocalTime {
    val instant = Instant.parse(this)
    return instant.atZone(ZoneId.systemDefault()).toLocalTime()
}