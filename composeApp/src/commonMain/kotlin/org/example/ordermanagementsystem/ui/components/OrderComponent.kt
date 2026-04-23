package org.example.ordermanagementsystem.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.ordermanagementsystem.data.model.Order

@Composable
fun OrderComponent(
    order: Order,
    onProcess: () -> Unit,
    onComplete: () -> Unit,
    onCancel: () -> Unit,
    onReinstate: () -> Unit,
    onWarehouseChange: (Int) -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .padding(16.dp)
    ) {
        OrderDetailsText(order = order)

        Spacer(modifier = Modifier.height(16.dp))

        ItemList(items = order.items)

        Spacer(modifier = Modifier.height(16.dp))

        OrderActionsRow(
            selectedWarehouse = order.warehouseNumber,
            warehouseOptions = listOf(1,2,3),
            onWarehouseChange = onWarehouseChange,
            onProcess = onProcess,
            onComplete = onComplete,
            onReinstate = onReinstate,
            onCancel = onCancel
        )
    }
}