package org.example.ordermanagementsystem

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.example.ordermanagementsystem.data.repository.OrderRepository
//import org.example.ordermanagementsystem.domain.FakeOrderRepository
import org.example.ordermanagementsystem.domain.OrderRepositoryJVM
import org.example.ordermanagementsystem.ui.OrderDashboard
import org.example.ordermanagementsystem.viewModel.OrderViewModel

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Order Management System",
    ) {
        val viewModel = remember {
            OrderViewModel(OrderRepositoryJVM())
        }

        LaunchedEffect(Unit) {
            viewModel.loadState()
        }

        OrderDashboard(viewModel)

    }
}