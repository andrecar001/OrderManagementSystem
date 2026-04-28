package org.example.ordermanagementsystem

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.serialization.json.Json
import org.example.ordermanagementsystem.data.repository.OrderRepositoryAndroid
import org.example.ordermanagementsystem.ui.dashboards.OrderDashboardForAndroid

import org.example.ordermanagementsystem.viewModel.OrderViewModel

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: OrderViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        viewModel = OrderViewModel(
            repository = OrderRepositoryAndroid(
                context = applicationContext,
                json = Json { ignoreUnknownKeys = true }
            )

        )

        setContent {
            OrderDashboardForAndroid(viewModel = viewModel)
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}