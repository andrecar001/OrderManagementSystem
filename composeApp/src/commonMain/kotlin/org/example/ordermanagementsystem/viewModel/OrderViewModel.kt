package org.example.ordermanagementsystem.viewModel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.example.ordermanagementsystem.data.model.Order
import org.example.ordermanagementsystem.data.repository.OrderRepository

class OrderViewModel (
    private val repository: OrderRepository
) {
    private val _orders = MutableStateFlow<List<Order>>(emptyList())
    val orders : StateFlow<List<Order>> = _orders

    private val _selectedOrder = MutableStateFlow<Order?>(null)
    val selectedOrder: StateFlow<Order?> = _selectedOrder

    fun loadOrders() {
        _orders.value =repository.getOrders()
    }

    fun selectOrder(order: Order) {
        _selectedOrder.value = order
    }

    private fun updateOrder(orderNumber: Int, transform: (Order) -> Order) {
        val updated = _orders.value.map { order ->
            if (order.orderNumber == orderNumber) transform(order)
            else order
        }

        _orders.value = updated
        _selectedOrder.value = updated.firstOrNull { it.orderNumber == orderNumber }
    }
    fun changeWarehouse(orderNumber: Int, newWarehouse: Int) {
        _orders.value = _orders.value.map { order ->
            if(order.orderNumber == orderNumber) {
                order.copy(warehouseNumber = newWarehouse)
            } else order
        }
    }
    fun processOrder(orderNumber: Int, warehouseNumber: Int) {
        updateOrder(orderNumber) { it.process(warehouseNumber) }
    }
    fun completeOrder(orderNumber: Int) {
        updateOrder(orderNumber) { it.complete() }
    }
    fun cancelOrder(orderNumber: Int) {
        updateOrder(orderNumber) { it.cancel() }
    }
    fun reinstateOrder(orderNumber: Int) {
        updateOrder(orderNumber) { it.reinstate() }
    }


/*    fun loadJSON(json: String) {
        _orders.value = loadOrders.fromJSON(json)
    }
    fun loadXML(xml: String) {
        _orders.value = loadOrders.fromXML(xml)
    }*/

}