package org.example.ordermanagementsystem


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource

import ordermanagementsystem.composeapp.generated.resources.Res
import ordermanagementsystem.composeapp.generated.resources.compose_multiplatform

@Composable
@Preview
fun App() {
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.Start,
        ) {
//            Button(onClick = { showContent = !showContent }) {
//                Text("Click me!")
//            }
            LoadMainPage()
            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text("Compose: $greeting")
                }
            }
        }
    }
}

@Composable
fun LoadMainPage() {
    var orders by remember {
        mutableStateOf(
            listOf("Incoming", "Incoming", "Incoming", "Outgoing", "Outgoing")
        )
    }
    var selectedOrder by remember {
        mutableStateOf<Int?>(null)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Row(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .safeContentPadding()
                .fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Center,
        ){
            Button(onClick = {
                selectedOrder?.let { index ->
                    val newList = orders.toMutableList()
                    newList[index] = "Processing"
                    orders = newList
                }
            }) { Text("Process") }
            Button(onClick = {
                selectedOrder?.let { index ->
                    val newList = orders.toMutableList()
                    newList[index] = "Complete"
                    orders = newList
                }
            }) { Text("Complete") }
        }


        LazyColumn() {
            itemsIndexed(orders) { index, currentOrder ->
                Text(
                    text = currentOrder,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clickable {selectedOrder = index}
                        .background(
                            if (selectedOrder == index) Color.LightGray
                            else Color.Transparent
                        )
                    ,
                )
                HorizontalDivider()


            }
        }
    }

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {

    }


    Row(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primaryContainer)
            .safeContentPadding()
            .fillMaxWidth()
        ,
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Center,
    ){
        Button(onClick = {val bean = 3}) { Text("Cancel") }
        Button(onClick = {val bean = 3}) { Text("Reinstate") }
    }


}
