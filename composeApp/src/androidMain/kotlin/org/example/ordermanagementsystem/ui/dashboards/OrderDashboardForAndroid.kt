package org.example.ordermanagementsystem.ui.dashboards


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
            .padding(vertical = 32.dp, horizontal = 16.dp)
    ){
        //Header
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column {
                Text(
                    modifier = Modifier,
//                        .padding(8.dp),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    text = "Order"
                )
                Text(
                    modifier = Modifier,
//                        .padding(8.dp),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    text = "Management"
                )
                Text(
                    modifier = Modifier,
//                        .padding(8.dp),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    text = "System"
                )
            }


            Spacer(modifier = Modifier.padding(16.dp))
            val context = LocalContext.current
            var orderIndex by remember { mutableStateOf(0) }

            //Test Button to create a file for testing bc importing is hard on vms
            Button(onClick = {

                val dir = File(context.filesDir, "incoming")
                dir.mkdirs()

                val file = File(dir, "order${orderIndex}.json")
                orderIndex++
                println("Creating file at: ${file.absolutePath}")
                file.writeText("""
            {
              "order": {
                "type": "ship",
                "order_date": 1711111111111,
                "items": [
                  { "name": "Chair", "quantity": 1, "price": 85.99 },
                  { "name": "Boat", "quantity": 7, "price": 9000.51 },
                  { "name": "Bed", "quantity": 2, "price": 153.41 }
                  
                  
                ]
              }
            }
                """.trimIndent())

                println("File exists after write: ${file.exists()}")
            }) {
                Text("Create Test Order Files")
            }

        }
        HorizontalDivider(modifier = Modifier.padding(8.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {


            //Order List
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

            HorizontalDivider()

            //Order Details
            selectedOrder?.let { order ->
                OrderComponent(
                    order = order,
                    onProcess = { warehouse ->
                        viewModel.processOrder(order.orderNumber, warehouse)
                    },
                    onComplete = {viewModel.completeOrder(order.orderNumber)},
                    onCancel = {viewModel.cancelOrder(order.orderNumber)},
                    onReinstate = {viewModel.reinstateOrder(order.orderNumber)},

                    modifier = Modifier.weight(2f)
                )
            } ?: Box(
                modifier = Modifier.weight(0.5f)
            ) {
                Text("Select an order")
            }




        }
    }


}