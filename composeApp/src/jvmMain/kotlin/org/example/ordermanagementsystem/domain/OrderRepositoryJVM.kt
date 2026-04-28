package org.example.ordermanagementsystem.domain

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.serialization.encodeToString
import org.example.ordermanagementsystem.domain.model.Order
import org.example.ordermanagementsystem.data.repository.OrderRepository
import java.io.File
import kotlinx.serialization.json.Json
import org.example.ordermanagementsystem.data.data_transfer_objects.JSONOrderDTO
import org.example.ordermanagementsystem.data.mappers.toOrder
import org.example.ordermanagementsystem.data.repository.BaseOrderRepository
import java.nio.file.FileSystems
import java.nio.file.Path
import java.nio.file.StandardWatchEventKinds


class OrderRepositoryJVM: BaseOrderRepository() {


    private val file: File by lazy {
        val directory = File(System.getProperty("user.home"),".order_management_system")
        if (!directory.exists()) directory.mkdirs()
        File(directory, "state.json")
    }

    private val json = Json { prettyPrint = true }


    suspend fun loadInitialOrders() {
        if (!file.exists()) return

        val loaded = json.decodeFromString<List<Order>>(file.readText())
        _orders.value = loaded
    }



    override suspend fun getOrders(): List<Order> {


        if(!file.exists()) return emptyList()
        val loaded = json.decodeFromString<List<Order>>(file.readText())
        return loaded
    }

    override suspend fun saveOrders(orders: List<Order>) {
        file.writeText(json.encodeToString(orders))
    }

    override fun addOrder(order: Order) {
        super.addOrder(order)
        saveSync()
    }

    override fun updateOrder(order: Order) {
        super.updateOrder(order)
        saveSync()
    }


    fun loadJsonOrder(newJSON: String) {
        val dto = json.decodeFromString<JSONOrderDTO>(newJSON)
        val order = dto.toOrder(id = nextId())
    }

    private fun saveSync() {
        file.writeText(json.encodeToString(_orders.value))
    }

    fun startWatchingFolder(onNewFile: (File) -> Unit) {
        val path = file.parentFile.toPath()

        val watchService = FileSystems.getDefault().newWatchService()

        path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY)

        Thread {
            while (true) {
                val key = watchService.take()

                for (event in key.pollEvents()) {
                    val filename = event.context() as Path
                    val newFile = path.resolve(filename).toFile()

                    if(newFile.extension == "json") {
                        onNewFile(newFile)
                    }
                }

                key.reset()
            }
        }.start()
    }

    fun handleNewFile(file: File) {
        val order = json.decodeFromString<Order>(file.readText())

        addOrder(order)
    }
}