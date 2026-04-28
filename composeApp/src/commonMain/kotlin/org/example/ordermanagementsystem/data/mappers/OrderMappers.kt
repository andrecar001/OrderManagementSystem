package org.example.ordermanagementsystem.data.mappers

import org.example.ordermanagementsystem.data.data_transfer_objects.JSONOrderDTO
import org.example.ordermanagementsystem.data.data_transfer_objects.JSONOrderWrapper
import org.example.ordermanagementsystem.data.data_transfer_objects.XMLOrderDTO
import org.example.ordermanagementsystem.domain.model.Item
import org.example.ordermanagementsystem.domain.model.Order
import kotlin.time.Clock

fun JSONOrderWrapper.toOrder(id: Int) : Order {
    val o = order
    return Order(
        orderNumber = id,
        type = o.type,
        stage = "incoming",
        warehouseNumber = null,
        date = o.order_date,
        items = o.items.map {
            Item(it.name, it.quantity, it.price)
        }
    )


}

fun XMLOrderDTO.toOrder(id: Int,):  Order {
    return Order(
        orderNumber = id,
        type = type,
        stage = "incoming",
        warehouseNumber = null,
        date = Clock.System.now().toEpochMilliseconds(),
        items = items.map { Item(it.name, it.quantity, it.price) }
    )


}