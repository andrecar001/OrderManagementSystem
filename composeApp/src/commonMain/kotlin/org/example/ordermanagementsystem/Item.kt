package org.example.ordermanagementsystem

data class Item (
    val name: String,
    val quantity: Int,
    val price: Double
) {
    val total : Double
        get() = quantity * price

}