package org.example.ordermanagementsystem.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Item (
    val name: String,
    val quantity: Int,
    val price: Double
) {
    val total : Double
        get() = quantity * price

}