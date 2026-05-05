package org.example.ordermanagementsystem.data.mapper

import org.example.ordermanagementsystem.data.data_transfer_objects.JSONOrderWrapper
import org.example.ordermanagementsystem.data.data_transfer_objects.XMLOrderDTO
import org.example.ordermanagementsystem.data.mapper.toOrder
import org.example.ordermanagementsystem.data.parser.ParsedOrder
import org.example.ordermanagementsystem.domain.model.Item
import org.example.ordermanagementsystem.domain.model.Order
import kotlin.time.Clock

import kotlin.test.*

class OrderMappersTest {
    // !! Need to test this functionality works
    //var next = nextId(current)

    var itemList = mutableListOf<Item>(
        Item(name = "Ice Cream", quantity = 2, price = 4.75),
        Item(name = "Malt", quantity = 3, price = 6.00)
    )
    var parsedOrder = ParsedOrder(type = "Delivery", date = 1777940306283, items = itemList, orderNumber = 997)

    val order = parsedOrder.toOrder(997)

    // Not sure how to handle testing JSONOrderWrapper.toOrder vs XMLOrderWrapper.toOrder.
    // Using code in e.g. OrderRepositoryJVM as a point of ref for creating test objects
    // Add code to test whether order object's data is correct
    @Test
    fun toOrder_ParsedOrder_ReturnsOrder() {
        var order = parsedOrder.toOrder(997)

        println("order: $order")
        assertTrue(order is Order)
    }
}

