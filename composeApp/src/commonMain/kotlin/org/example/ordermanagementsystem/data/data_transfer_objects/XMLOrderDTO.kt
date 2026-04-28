package org.example.ordermanagementsystem.data.data_transfer_objects

data class XMLOrdersDTO(
    val orders: List<XMLOrderDTO>
)

data class XMLOrderDTO(
    val id: Int,
    val type: String,
    val items: List<XMLItemDTO>
)

data class XMLItemDTO(
    val name: String,
    val price: Double,
    val quantity: Int
)