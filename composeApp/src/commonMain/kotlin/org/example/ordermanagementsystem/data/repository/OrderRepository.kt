package org.example.ordermanagementsystem.data.repository

import org.example.ordermanagementsystem.domain.model.Order

interface OrderRepository {
    suspend fun loadOrders(): List<Order>
    suspend fun saveOrders(orders: List<Order>)
    suspend fun loadIncomingOrders(): List<Order>
    suspend fun mergeIncomingOrders(): List<Order>

}