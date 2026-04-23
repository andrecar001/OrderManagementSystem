package org.example.ordermanagementsystem.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.example.ordermanagementsystem.data.model.Order

@Composable
fun OrderDetailsText(
    order: Order,
    modifier: Modifier = Modifier
) {
    val text = """
        Order number: ${order.orderNumber}
        Order type: ${order.type}
        Order stage: ${order.stage}
        Order Date: ${order.totalPrice()}
        Warehouse number: ${order.warehouseNumber}
        Total price: $${order.totalPrice()}
    """.trimIndent()

    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth(),
        value = text,
        onValueChange = {},
        readOnly = true,
        label = {Text("Order Details")}
    )

}