package org.example.ordermanagementsystem.data.repository

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
