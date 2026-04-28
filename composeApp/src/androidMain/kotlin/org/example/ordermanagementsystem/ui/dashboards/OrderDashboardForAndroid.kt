package org.example.ordermanagementsystem.ui.dashboards


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.ordermanagementsystem.ui.components.OrderComponent
import org.example.ordermanagementsystem.ui.components.OrderListComponent
import org.example.ordermanagementsystem.viewModel.OrderViewModel
import java.io.File

@Composable
fun OrderDashboardForAndroid(viewModel: OrderViewModel) {
    val orders by viewModel.orders.collectAsState()
    val selectedOrder by viewModel.selectedOrder.collectAsState()

    Column(
        modifier = Modifier
        .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                modifier = Modifier.weight(1f)
                    .padding(8.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                text = "Order Management System"
            )

            Spacer(modifier = Modifier.weight(1f).padding(32.dp))
            val context = LocalContext.current

            Button(onClick = {

                val dir = File(context.filesDir, "incoming")
                dir.mkdirs()

                val file = File(dir, "order1.json")
                println("Creating file at: ${file.absolutePath}")
                file.writeText("""
            {
              "order": {
                "type": "ship",
                "order_date": 1711111111111,
                "items": [
                  { "name": "Chair", "quantity": 1, "price": 85.99 }
                ]
              }
            }
                """.trimIndent())

                println("File exists after write: ${file.exists()}")
            }) {
                Text("Create Test Order Files")
            }

        }

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