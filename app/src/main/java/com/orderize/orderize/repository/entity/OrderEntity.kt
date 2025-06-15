package com.orderize.orderize.repository.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.orderize.orderize.model.Drink
import com.orderize.orderize.model.Order
import com.orderize.orderize.model.Pizza
import com.orderize.orderize.model.User
import java.sql.Timestamp
import java.time.LocalDate
import java.time.LocalTime

@Entity(tableName = "Order")
data class OrderEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val client: User,
    val companyName: String,
    val pizzas: List<Pizza>,
    val drinks: List<Drink>,
    val date: LocalTime,
    val type: String,
    val price: Double,
    val table: Long,
    val status: String,
    val lastModified: LocalTime,
    val creationDate: LocalDate = LocalDate.now()
)

fun OrderEntity.toOrder() = Order(
    id = this.id,
    client = this.client,
    pizzas = this.pizzas,
    drinks = this.drinks,
    date = this.date,
    type = this.type,
    price = this.price,
    table = this.table,
    status = this.status,
    lastModified = this.lastModified,
)

fun Order.toEntity() = OrderEntity(
    id = this.id,
    client = this.client,
    pizzas = this.pizzas,
    drinks = this.drinks,
    date = this.date,
    type = this.type,
    price = this.price,
    table = this.table,
    status = this.status,
    companyName = "",
    lastModified = this.lastModified
)
