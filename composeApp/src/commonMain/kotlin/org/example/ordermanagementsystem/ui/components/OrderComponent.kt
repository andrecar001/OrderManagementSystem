package org.example.ordermanagementsystem.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.ordermanagementsystem.domain.model.Order

@Composable
fun OrderComponent(
    order: Order,
    onProcess: (Int) -> Unit,
    onComplete: () -> Unit,
    onCancel: () -> Unit,
    onReinstate: () -> Unit,

    modifier: Modifier = Modifier
){

    var selectedWarehouse by remember(order.orderNumber) {
        mutableStateOf(order.warehouseNumber)
    }
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {


        OrderActionsColumn(
            selectedWarehouse = selectedWarehouse,
            warehouseOptions = listOf(1,2,3),
            onWarehouseChange = { selectedWarehouse = it },
            onProcess = {
                selectedWarehouse?.let { warehouse ->
                    onProcess(warehouse)
                }
            },
            onComplete = onComplete,
            onReinstate = onReinstate,
            onCancel = onCancel,

            canProcess = order.stage == "incoming",
            canReinstate = order.stage == "canceled",
            canComplete = order.stage == "in progress",
            canCancel = order.stage == "incoming" || order.stage == "in progress",
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))


        OrderDetailsText(order = order)

        Spacer(modifier = Modifier.height(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            ItemList(items = order.items, modifier = Modifier.fillMaxSize())
        }






    }
}