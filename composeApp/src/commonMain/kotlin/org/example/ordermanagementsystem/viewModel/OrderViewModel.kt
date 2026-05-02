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

/**
 * Order view model
 *
 * @property repository
 * @constructor loads the state from the repository
 */
class OrderViewModel (
    private val repository: OrderRepository
) : ViewModel() {

    //List of orders that can be updated and used in a UI
    private val _orders = MutableStateFlow<List<Order>>(emptyList())
    val orders : StateFlow<List<Order>> = _orders

    //Selected order for view model that can be called by a UI
    private val _selectedOrder = MutableStateFlow<Order?>(null)
    val selectedOrder: StateFlow<Order?> = _selectedOrder

    var selectedWarehouseFilter by mutableStateOf<Int?>(null)
        private set

    private val _selectedOrderWarehouse = MutableStateFlow<Int?>(null)
    val selectedOrderWarehouse: StateFlow<Int?> = _selectedOrderWarehouse


    init {
        loadState()

    }

    /**
     * Load state from the instance's repository
     *
     */
    fun loadState() {
        viewModelScope.launch {
            _orders.value = repository.loadOrders()
        }
    }

    /**
     * Load incoming orders from repository
     *
     */
    fun loadIncomingOrders() {
        viewModelScope.launch {
            _orders.value = repository.mergeIncomingOrders()
        }
    }

    /**
     * Set the view models selected order
     *
     * @param order
     */
    fun selectOrder(order: Order) {
        _selectedOrder.value = order
    }

    /**
     * Update order and save changes to the repository
     *
     * @param orderNumber
     * @param transform function from Order to apply change
     * @receiver
     */
    private fun updateOrder(orderNumber: Int, transform: (Order) -> Order) {
        val updatedList = _orders.value.map { order ->
            if (order.orderNumber == orderNumber) transform(order)
            else order
        }

        _orders.value = updatedList
        _selectedOrder.value = updatedList.firstOrNull { it.orderNumber == orderNumber }

        viewModelScope.launch{repository.saveOrders(updatedList)}
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

    fun setWarehouseFilter(warehouse: Int?) {
        selectedWarehouseFilter = warehouse
    }

    /**
     * Filter orders by the instances currently selected warehouse
     *
     * @param orders
     * @return
     */
    fun filterOrders(orders: List<Order>) : List<Order> {
        return orders.filter {
            selectedWarehouseFilter == null ||
                    it.warehouseNumber == selectedWarehouseFilter
        }
    }



}