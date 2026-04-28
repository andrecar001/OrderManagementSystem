package org.example.ordermanagementsystem.data

import org.example.ordermanagementsystem.domain.model.Item
import org.example.ordermanagementsystem.domain.model.Order

object FakeOrderData {

    val orders = listOf(
        Order(
            orderNumber = 1,
            type = "Delivery",
            stage = "incoming",
            warehouseNumber = 1,
            date = 1776816000000L,
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
        ),
        Order(
            orderNumber = 3,
            type = "Delivery",
            stage = "complete",
            warehouseNumber = 1,
            date = 1704067200000L,
            items = listOf(
                Item("Laptop", 1, 999.99),
                Item("USB Hub", 2, 24.99)
            )
        ),

        Order(
            orderNumber = 4,
            type = "Pickup",
            stage = "canceled",
            warehouseNumber = 3,
            date = 1710460800000L,
            items = listOf(
                Item("Desk Chair", 1, 149.99)
            )
        ),

        Order(
            orderNumber = 5,
            type = "Delivery",
            stage = "incoming",
            warehouseNumber = 2,
            date = 1720051200000L,
            items = listOf(
                Item("Webcam", 2, 79.99),
                Item("Microphone", 1, 129.99)
            )
        ),

        Order(
            orderNumber = 6,
            type = "Pickup",
            stage = "complete",
            warehouseNumber = 1,
            date = 1735084800000L,
            items = listOf(
                Item("External SSD", 1, 159.99),
                Item("USB Cable", 4, 5.99)
            )
        ),

        Order(
            orderNumber = 7,
            type = "Delivery",
            stage = "reinstate",
            warehouseNumber = 4,
            date = 1780272000000L,
            items = listOf(
                Item("Graphics Card", 1, 499.99)
            )
        ),

        Order(
            orderNumber = 8,
            type = "Pickup",
            stage = "incoming",
            warehouseNumber = 3,
            date = 1776816000000L,
            items = listOf(
                Item("Printer", 1, 199.99),
                Item("Ink Cartridge", 2, 39.99)
            )
        ),

        Order(
            orderNumber = 9,
            type = "Delivery",
            stage = "complete",
            warehouseNumber = 2,
            date = 1735689600000L,
            items = listOf(
                Item("Router", 1, 89.99),
                Item("Ethernet Cable", 5, 7.99)
            )
        ),

        Order(
            orderNumber = 10,
            type = "Pickup",
            stage = "canceled",
            warehouseNumber = 1,
            date = 1704067200000L,
            items = listOf(
                Item("Tablet", 1, 329.99)
            )
        ),

        Order(
            orderNumber = 11,
            type = "Delivery",
            stage = "incoming",
            warehouseNumber = 4,
            date = 1780272000000L,
            items = listOf(
                Item("Smartphone", 2, 699.99),
                Item("Charger", 2, 19.99)
            )
        ),

        Order(
            orderNumber = 12,
            type = "Pickup",
            stage = "complete",
            warehouseNumber = 3,
            date = 1710460800000L,
            items = listOf(
                Item("Desk Lamp", 3, 29.99)
            )
        )
    )
}