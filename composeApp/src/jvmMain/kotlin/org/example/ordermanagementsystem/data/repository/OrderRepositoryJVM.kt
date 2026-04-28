package org.example.ordermanagementsystem.data.repository

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.example.ordermanagementsystem.data.data_transfer_objects.JSONOrderWrapper
import org.example.ordermanagementsystem.data.mapper.toOrder
import org.example.ordermanagementsystem.domain.model.Order
import java.io.File

class OrderRepositoryJVM: BaseOrderRepository() {


    private var cache: List<Order>? = null
    private val baseDirectory = File(
        System.getProperty("user.home"),
        ".order_management_system"
    )

    private val stateFile = File(baseDirectory, "state.json")
    private val incomingDirectory = File(baseDirectory, "incoming")
    private val loadedDirectory = File(baseDirectory, "loaded")

    private val json = Json {
        prettyPrint = true
        ignoreUnknownKeys = true
    }

    init {
        confirmDirectory()
    }

    private fun confirmDirectory() {
        if(!baseDirectory.exists()) baseDirectory.mkdirs()

        if(!incomingDirectory.exists()) incomingDirectory.mkdirs()
        if(!loadedDirectory.exists()) loadedDirectory.mkdirs()


        if(!stateFile.exists()) stateFile.writeText("[]")

        println("Confirming order storage created")
    }

    override suspend fun loadOrders(): List<Order> {
        if (cache != null) return cache!!
        if(!stateFile.exists()) return emptyList()

        val loaded = json.decodeFromString<List<Order>>(stateFile.readText())
        cache = loaded
        return loaded
    }


    override suspend fun saveOrders(orders: List<Order>) {
        cache = orders
        stateFile.writeText(json.encodeToString(orders))
    }

    override suspend fun loadIncomingOrders(): List<Order> {
        val current = loadOrders()
        var next = nextId(current)

        val files = incomingDirectory.listFiles()
            ?.filter { it.extension in listOf("json", "xml") }
            ?: return emptyList()

        val result = mutableListOf<Order>()

        for (file in files) {
            try {
                val order = when (file.extension.lowercase()) {
                    "json" -> parseJson(file, next++)
                    else -> null
                }

                if (order != null) {
                    result.add(order)

                    moveFileToLoaded(file)
                }
            } catch (e: Exception) {
                println("Failed to load order ${file.name}: ${e.message}")
            }
        }
        return result
    }

    private fun moveFileToLoaded(file: File) {
        val target = File(loadedDirectory, file.name)
        file.copyTo(target, overwrite = true)
        file.delete()
    }

    private fun parseJson(file: File, id: Int) : Order {
        val dto = json.decodeFromString<JSONOrderWrapper>(file.readText())
        return dto.toOrder(id)
    }


}