package org.example.ordermanagementsystem.data.mappers

import org.example.ordermanagementsystem.data.data_transfer_objects.JSONOrderDTO
import org.example.ordermanagementsystem.data.data_transfer_objects.XMLOrderDTO
import org.example.ordermanagementsystem.domain.model.Item
import org.example.ordermanagementsystem.domain.model.Order
import kotlin.time.Clock

fun JSONOrderDTO.toOrder(id: Int, ) = Order (
    orderNumber = id,
    type = order.type,
    stage = "incoming",
    warehouseNumber = null,
    date = order.date,
    items = order.items
)

fun XMLOrderDTO.toOrder(id: Int,) = Order(
    orderNumber = id,
    type = type,
    stage = "incoming",
    warehouseNumber = null,
    date = Clock.System.now().toEpochMilliseconds(),
    items = items.map { Item(it.name, it.quantity, it.price) }

)