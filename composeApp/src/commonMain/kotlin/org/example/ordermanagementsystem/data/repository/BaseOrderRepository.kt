package org.example.ordermanagementsystem.data.repository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.example.ordermanagementsystem.domain.model.Order

abstract class BaseOrderRepository : OrderRepository {



    override suspend fun mergeIncomingOrders(): List<Order> {
        val current = loadOrders()
        val incoming = loadIncomingOrders()

        var nextId = nextId(current)

        val incomingOrders = incoming.map { order ->
            if (order.orderNumber == 0) {
                order.copy(orderNumber = nextId++)
            } else order
        }

        val merged = (current + incomingOrders).distinctBy { it.orderNumber }

        saveOrders(merged)
        return merged
    }
    suspend fun addOrder(order: Order): List<Order> {
        val current = loadOrders()
        val newOrder = order.copy(orderNumber = nextId(current))

        val updated = current + newOrder
        saveOrders(updated)

        return updated
    }

    protected fun nextId(orders: List<Order>): Int =
        (orders.maxOfOrNull { it.orderNumber } ?: -1) + 1
}