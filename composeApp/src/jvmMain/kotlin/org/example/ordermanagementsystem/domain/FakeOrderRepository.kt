package org.example.ordermanagementsystem.domain

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.example.ordermanagementsystem.data.FakeOrderData
import org.example.ordermanagementsystem.domain.model.Order
import org.example.ordermanagementsystem.data.repository.OrderRepository
import java.io.File

/*
class FakeOrderRepository : OrderRepository {

    private val file: File by lazy {
        val directory = File(System.getProperty("user.home"),".order_management_system")
        if (!directory.exists()) directory.mkdirs()
        File(directory, "orders.json")
    }


    override suspend fun loadOrders(): List<Order> = FakeOrderData.orders.toMutableList()
    override suspend fun saveOrders(orders: List<Order>) {
        val json = Json {prettyPrint = true}
        file.writeText(json.encodeToString(orders))
    }
}*/
