package org.example.ordermanagementsystem.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.ordermanagementsystem.domain.model.Item

@Composable
fun ItemDetailsRow(item: Item, index: Int){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),

        ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("[ITEM ${index+1}]")
            Text("Name: ${item.name}, Quantity: ${item.quantity}, Price: $${item.price}")
        }
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
            .fillMaxHeight()
            .height(200.dp),
    ) {
        itemsIndexed(items) { index, item ->
            ItemDetailsRow(item, index)
        }
    }
}
