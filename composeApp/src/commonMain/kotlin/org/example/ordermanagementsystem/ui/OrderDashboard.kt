package org.example.ordermanagementsystem.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.ordermanagementsystem.ui.components.OrderList
import org.example.ordermanagementsystem.ui.components.OrderComponent
import org.example.ordermanagementsystem.ui.components.OrderListComponent
import org.example.ordermanagementsystem.viewModel.OrderViewModel

@Composable
fun OrderDashboard(viewModel: OrderViewModel) {
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
            Button(onClick = {}) {
                Text("Warehouse Stats")
            }
            Spacer(modifier = Modifier.weight(1f).padding(32.dp))
        }

        Row {
            /*OrderList(
                orders,
                onSelect = { viewModel.selectOrder(it) },
                modifier = Modifier.weight(1f),
            )*/
            OrderListComponent(
                orders = orders,
                onSelect = { viewModel.selectOrder(it) },
                onWarehouseChange = { viewModel.changeWarehouse(2, 2)},
                modifier = Modifier.weight(1f)
            )

            selectedOrder?.let {
                OrderComponent(
                    order = it,
                    onProcess = {viewModel.processOrder(it.orderNumber, 1)},
                    onComplete = {viewModel.completeOrder(it.orderNumber)},
                    onCancel = {viewModel.cancelOrder(it.orderNumber)},
                    onReinstate = {viewModel.reinstateOrder(it.orderNumber)},
                    onWarehouseChange = { w ->
                        viewModel.changeWarehouse(it.orderNumber, w)
                    },
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