package org.example.ordermanagementsystem.viewModel

import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.example.ordermanagementsystem.domain.model.Order
import org.example.ordermanagementsystem.data.repository.OrderRepository
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class OrderViewModel (
    private val repository: OrderRepository
) : ViewModel() {
    private val _orders = MutableStateFlow<List<Order>>(emptyList())
    val orders : StateFlow<List<Order>> = _orders

    private val _selectedOrder = MutableStateFlow<Order?>(null)
    val selectedOrder: StateFlow<Order?> = _selectedOrder

    var selectedWarehouseFilter by mutableStateOf<Int?>(null)
        private set

    private val _selectedOrderWarehouse = MutableStateFlow<Int?>(null)
    val selectedOrderWarehouse: StateFlow<Int?> = _selectedOrderWarehouse


    init {
        loadState()

    }
    fun loadState() {
        viewModelScope.launch {
            _orders.value = repository.loadOrders()
        }
    }

    fun loadIncomingOrders() {
        viewModelScope.launch {
            _orders.value = repository.mergeIncomingOrders()
        }
    }

    fun selectOrder(order: Order) {
        _selectedOrder.value = order
    }


   /* private fun updateOrders(transform: (List<Order>) -> List<Order>) {
        val updatedList = transform(_orders.value)

        _orders.value = updatedList

        viewModelScope.launch{
            repository.saveOrders(updatedList)
        }
    }*/

    private fun updateOrder(orderNumber: Int, transform: (Order) -> Order) {
        val updatedList = _orders.value.map { order ->
            if (order.orderNumber == orderNumber) transform(order)
            else order
        }

        _orders.value = updatedList
        _selectedOrder.value = updatedList.firstOrNull { it.orderNumber == orderNumber }

        viewModelScope.launch{repository.saveOrders(updatedList)}
    }

    fun getOrder(orderNumber: Int): Order? {
        return _orders.value.find { it.orderNumber == orderNumber }
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

    fun canProcess(order: Order): Boolean =
        order.stage == "incoming"

    fun canComplete(order: Order): Boolean =
        order.stage == "in progress"

    fun canCancel(order: Order): Boolean =
        order.stage == "incoming" ||
                order.stage == "in progress"

    fun canReinstate(order: Order): Boolean =
        order.stage == "canceled"
    fun setWarehouseFilter(warehouse: Int?) {
        selectedWarehouseFilter = warehouse
    }



}