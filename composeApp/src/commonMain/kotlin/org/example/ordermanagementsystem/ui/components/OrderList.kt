package org.example.ordermanagementsystem.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.ordermanagementsystem.data.model.Order


@Composable
fun OrderDetailsRow(
    order: Order,
    onClick: () -> Unit
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),

    ) {
        Column (
            modifier = Modifier
                .clickable { onClick() }
                .padding(8.dp)
        ) {
            Text("Order #${order.orderNumber} Stage: ${order.stage} Items: ${order.items.size}")
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
    onSelect: (Order) -> Unit,
    onWarehouseChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
        .padding(16.dp)
    ) {
        Row {
            Text(text = "Orders")
            Spacer(modifier = Modifier.weight(1f).padding(16.dp))

            WarehouseDropDownMenu(listOf(1,2,3), onWarehouseChange, 1 )
        }
        OrderList(orders = orders, onSelect = onSelect)
    }
}