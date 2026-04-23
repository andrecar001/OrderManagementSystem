package org.example.ordermanagementsystem.data.repository

import org.example.ordermanagementsystem.data.FakeOrderData
import org.example.ordermanagementsystem.data.model.Order

class FakeOrderRepository : OrderRepository {

    private val orders = mutableListOf<Order>()

    override fun getOrders(): List<Order> = FakeOrderData.orders.toMutableList()
    override fun updateOrders(newOrders: List<Order>) {
        this.orders.clear()
        this.orders.addAll(orders)
    }
}