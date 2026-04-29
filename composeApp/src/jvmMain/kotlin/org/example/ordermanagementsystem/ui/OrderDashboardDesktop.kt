package org.example.ordermanagementsystem.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.ordermanagementsystem.ui.components.OrderComponent
import org.example.ordermanagementsystem.ui.components.OrderListComponent
import org.example.ordermanagementsystem.viewModel.OrderViewModel


/**
 * Order dashboard desktop
 *
 * @param viewModel
 */
@Composable
fun OrderDashboardDesktop(viewModel: OrderViewModel) {
    val orders by viewModel.orders.collectAsState()
    val selectedOrder by viewModel.selectedOrder.collectAsState()

    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                modifier = Modifier.weight(1f)
                    .padding(8.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                text = "Order Management System"
            )
            Spacer(modifier = Modifier.weight(1f).padding(16.dp))

        }

        Row {
            val filteredOrders = orders.filter {
                viewModel.selectedWarehouseFilter == null ||
                        it.warehouseNumber == viewModel.selectedWarehouseFilter
            }
            OrderListComponent(
                orders = filteredOrders,
                selectedWarehouseFilter = viewModel.selectedWarehouseFilter,
                onSelect = { viewModel.selectOrder(it) },
                onRefresh = { viewModel.loadIncomingOrders() },
                onWarehouseChange = { viewModel.setWarehouseFilter(it) },
                modifier = Modifier.weight(1f)
            )

            VerticalDivider(thickness = 2.dp)
            selectedOrder?.let { order ->
                OrderComponent(
                    order = order,
                    onProcess = { warehouse ->
                        viewModel.processOrder(order.orderNumber, warehouse)
                    },
                    onComplete = {viewModel.completeOrder(order.orderNumber)},
                    onCancel = {viewModel.cancelOrder(order.orderNumber)},
                    onReinstate = {viewModel.reinstateOrder(order.orderNumber)},

                    modifier = Modifier.weight(1f),

                    )
            } ?: Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text("Select an order")
            }
        }
    }

}