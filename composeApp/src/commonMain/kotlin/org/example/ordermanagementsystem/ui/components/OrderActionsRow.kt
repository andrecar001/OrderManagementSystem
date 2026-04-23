package org.example.ordermanagementsystem.ui.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun OrderActionsRow(
    selectedWarehouse: Int,
    warehouseOptions: List<Int>,
    onWarehouseChange: (Int) -> Unit,
    onProcess: () -> Unit,
    onComplete: () -> Unit,
    onCancel: () -> Unit,
    onReinstate: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(false) }

    Row (
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,

    ){
        WarehouseDropDownMenu(warehouseOptions, onWarehouseChange, selectedWarehouse)
        Button(onClick = { onProcess() }) { Text("Process") }
        Button(onClick = { onComplete() }) { Text("Complete") }
        Button(onClick = { onCancel() }) { Text("Cancel") }
        Button(onClick = { onReinstate() }) { Text("Reinstate") }
    }



}

