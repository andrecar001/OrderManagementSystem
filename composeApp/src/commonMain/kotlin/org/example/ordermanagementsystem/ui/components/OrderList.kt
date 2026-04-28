package org.example.ordermanagementsystem.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.ordermanagementsystem.domain.model.Order
import org.example.ordermanagementsystem.ui.stageColor
import org.example.ordermanagementsystem.ui.toFormattedDate
import org.example.ordermanagementsystem.ui.toPriceString


@Composable
fun OrderDetailsRow(
    order: Order,
    onClick: () -> Unit
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = stageColor(order.stage)
        )

    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onClick)
                .padding(16.dp)

        ) {
            Text("🏠 ${order.warehouseNumber}, Order #️${order.orderNumber}, Stage: ${order.stage}, Items: ${order.items.size}, Total: 💲${order.totalPrice().toPriceString()}")

            Text("📅 ${order.date.toFormattedDate()}")
        }
    }
}
@Composable
fun OrderList(
    orders: List<Order>,
    onSelect: (Order) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(orders,key = { it.orderNumber}) { order ->
            OrderDetailsRow(
                order = order,
                onClick = { onSelect(order) }
            )
        }
    }
}

@Composable
fun OrderListComponent(
    orders: List<Order>,
    selectedWarehouseFilter: Int?,
    onSelect: (Order) -> Unit,
    onWarehouseChange: (Int?) -> Unit,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
        .padding(16.dp)
    ) {
        Row {
            Text(text = "Orders")
            Spacer(modifier = Modifier.weight(1f).padding(16.dp))

            Button(onClick = onRefresh) {
                Text("Refresh Orders")
            }

            Spacer(modifier = Modifier.weight(1f).padding(16.dp))
            labeledDropDown(
                label = "Filter",
                selected = selectedWarehouseFilter,
                options = listOf(null, 1, 2, 3),  //Possibly make this list parameter
                displayText = {
                    it?.let { "Warehouse $it" } ?: "All"
                },
                onSelected = onWarehouseChange
            )

        }
        OrderList(orders = orders, onSelect = onSelect)
    }
}