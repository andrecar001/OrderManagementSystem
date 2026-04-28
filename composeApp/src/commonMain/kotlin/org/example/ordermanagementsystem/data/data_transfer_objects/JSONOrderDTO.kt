package org.example.ordermanagementsystem.data.data_transfer_objects

import kotlinx.serialization.Serializable
import org.example.ordermanagementsystem.domain.model.Item

@Serializable
data class JSONOrderWrapper(
    val order: JSONOrderDTO
)
@Serializable
data class JSONOrderDTO(
    val type: String,
    val order_date: Long,
    val items: List<Item>
)

@Serializable
data class JSONItemDTO(
    val name: String,
    val quantity: Int,
    val price: Double,
)