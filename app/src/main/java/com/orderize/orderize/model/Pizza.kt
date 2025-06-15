package com.orderize.orderize.model

data class Pizza(
    val id: Long,
    val name: String,
    val price: Double,
    val observations: String,
    val flavors: List<Flavor>,
    val border: String,
    val size: String,
    val mass: String,
    val isChecked: Boolean = false,
)
