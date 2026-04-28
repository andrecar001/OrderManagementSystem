package org.example.ordermanagementsystem.data.repository

import org.example.ordermanagementsystem.data.FakeOrderData
import org.example.ordermanagementsystem.domain.model.Order

/*
class FakeOrderRepository : OrderRepository {

    private val orders = mutableListOf<Order>()

    override suspend fun loadOrders(): List<Order> = FakeOrderData.orders.toMutableList()
    override suspend fun saveOrders(orders: List<Order>) {
        this.orders.clear()
        this.orders.addAll(orders)
    }
}*/
