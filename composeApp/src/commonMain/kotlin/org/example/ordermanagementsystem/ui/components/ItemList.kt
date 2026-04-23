package org.example.ordermanagementsystem.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.ordermanagementsystem.data.model.Item

@Composable
fun ItemDetailsRow(item: Item, index: Int){
    Row (
        modifier = Modifier
        .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text("Name: ${item.name}, Quantity: ${item.quantity}, Price: $${item.price}")
    }
}

@Composable
fun ItemList(
    items: List<Item>,
    modifier: Modifier = Modifier
) {

    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp),
    ) {
        itemsIndexed(items) { index, item ->
            Text("Item ${index + 1}")
            ItemDetailsRow(item, index)
        }
    }
}
