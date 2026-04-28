package org.example.ordermanagementsystem.ui.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun OrderActionsColumn(
    selectedWarehouse: Int,
    warehouseOptions: List<Int>,

    onWarehouseChange: (Int) -> Unit,
    onProcess: (Int) -> Unit,
    onComplete: () -> Unit,
    onCancel: () -> Unit,
    onReinstate: () -> Unit,

    canProcess: Boolean,
    canComplete: Boolean,
    canCancel: Boolean,
    canReinstate: Boolean,

    modifier: Modifier = Modifier,
) {

    Column (
        modifier = modifier
            .fillMaxWidth(),

    ){
        labeledDropDown(
            label = "Process To",
            options = warehouseOptions,
            selected = selectedWarehouse,
            displayText = {"Warehouse $it" },
            onSelected = onWarehouseChange,
        )
        Spacer(modifier = Modifier.height(16.dp))
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = { onProcess(selectedWarehouse) },
                enabled = canProcess,
            ) { Text("Process") }
            Button(
                onClick = { onComplete() },
                enabled = canComplete,
            ) { Text("Complete") }
            Button(
                onClick = { onCancel() },
                enabled = canCancel,
            ) { Text("Cancel") }
            Button(
                onClick = { onReinstate() },
                enabled = canReinstate,
            ) { Text("Reinstate") }
        }

    }



}

