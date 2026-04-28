package org.example.ordermanagementsystem.data.repository

import org.example.ordermanagementsystem.domain.model.Order

interface OrderRepository {
    suspend fun getOrders(): List<Order>
    suspend fun saveOrders(orders: List<Order>)

    fun addOrder(order: Order)

    fun updateOrder(order: Order)

    fun nextId(): Int
}