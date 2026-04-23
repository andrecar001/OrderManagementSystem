package org.example.ordermanagementsystem.data.repository

import org.example.ordermanagementsystem.data.model.Order

interface OrderRepository {
    fun getOrders(): List<Order>
    fun updateOrders(newOrders: List<Order>)
}