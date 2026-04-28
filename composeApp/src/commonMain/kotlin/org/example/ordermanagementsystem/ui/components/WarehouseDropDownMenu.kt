package org.example.ordermanagementsystem.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> labeledDropDown(
    label: String,
    selected: T,
    options: List<T>,
    displayText: (T) -> String,
    onSelected: (T) -> Unit,
){
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
    ) {
        OutlinedTextField(
            value = displayText(selected),
            onValueChange = {},
            readOnly = true,
            label = { Text(text = label) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded)
            },
            modifier = Modifier
                .menuAnchor(),
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(displayText(option)) },
                    onClick = {
                        onSelected(option)
                        expanded = false
                    }
                )
            }
        }
    }

}
@Composable
fun WarehouseDropDownMenu(
    warehouseOptions: List<Int>,
    onWarehouseChange: (Int) -> Unit,
    selectedWarehouse: Int,
    additionalString: String? = null,
) {

    val isDropDownExpanded = remember {
        mutableStateOf(false)
    }

    val itemPosition = remember {
        mutableStateOf(0)
    }


    Box {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable {
                isDropDownExpanded.value = true
            }
        ) {
            Text(text = "Warehouse ${warehouseOptions[itemPosition.value]}" )
            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = null,
            )
        }
        DropdownMenu(
            expanded = isDropDownExpanded.value,
            onDismissRequest = {
                isDropDownExpanded.value = false
            }) {



            warehouseOptions.forEachIndexed { index, warehouse ->
                DropdownMenuItem(text = {
                    Text(text = "Warehouse $warehouse")
                },
                    onClick = {
                        isDropDownExpanded.value = false
                        itemPosition.value = index
                    })
            }
        }
    }


}