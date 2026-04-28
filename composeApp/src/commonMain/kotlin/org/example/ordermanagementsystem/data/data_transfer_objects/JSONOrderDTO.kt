package org.example.ordermanagementsystem.data.data_transfer_objects

import kotlinx.serialization.Serializable
import org.example.ordermanagementsystem.domain.model.Item

@Serializable
data class JSONOrderDTO(
    val order: OrderPayload
)
@Serializable
data class OrderPayload(
    val type: String,
    val date: Long,
    val items: List<Item>
)