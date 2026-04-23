package org.example.ordermanagementsystem.data

import org.example.ordermanagementsystem.data.model.Item
import org.example.ordermanagementsystem.data.model.Order
import kotlin.time.Clock

object FakeOrderData {

    val orders = listOf(
        Order(
            orderNumber = 1,
            type = "Delivery",
            stage = "incoming",
            warehouseNumber = 1,
            items = listOf(
                Item("Keyboard", 2, 49.99),
                Item("Mouse", 1, 19.99)
            )
        ),
        Order(
            orderNumber = 2,
            type = "Pickup",
            stage = "incoming",
            warehouseNumber = 2,
            items = listOf(
                Item("Monitor", 1, 199.99),
                Item("HDMI Cable", 3, 9.99)
            )
        )
    )
}