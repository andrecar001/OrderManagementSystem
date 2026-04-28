package org.example.ordermanagementsystem.data.repository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.example.ordermanagementsystem.domain.model.Order

abstract class BaseOrderRepository : OrderRepository {
    protected val _orders = MutableStateFlow<List<Order>>(emptyList())
    val orders = _orders.asStateFlow()

    override fun addOrder(order: Order) {
        _orders.value += order
    }

    override fun updateOrder(order: Order) {
        _orders.value = _orders.value.map {
            if (it.orderNumber == order.orderNumber) order else it
        }
    }

    override fun nextId(): Int =
        (_orders.value.maxOfOrNull { it.orderNumber } ?: -1) + 1
}