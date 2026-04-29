package org.example.ordermanagementsystem.data.repository

import android.content.Context
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import org.example.ordermanagementsystem.data.mapper.toOrder
import org.example.ordermanagementsystem.data.parser.JSONParser
import org.example.ordermanagementsystem.data.parser.OrderImporter
import org.example.ordermanagementsystem.data.parser.XMLParser
import org.example.ordermanagementsystem.domain.model.Order
import java.io.File

class OrderRepositoryAndroid(
    private val context: Context,
    private val json: Json,
) : BaseOrderRepository() {
    private val incomingDirectory = File(context.filesDir, "incoming")
    private val loadedDirectory = File(context.filesDir, "loaded")
    private val stateFile = File(context.filesDir, "state.json")

    private val importer = OrderImporter(
        listOf(JSONParser(json))
    )

    init {
        if(!context.filesDir.exists()) context.filesDir.mkdirs()
        if(!stateFile.exists()) stateFile.writeText("[]")
    }

    override suspend fun loadOrders(): List<Order> {
        if(!stateFile.exists()) return emptyList()

        return try {
            json.decodeFromString(
                ListSerializer(Order.serializer()),
                stateFile.readText()
            )
        } catch( e: Exception){
            println("Failed to load state: ${e.message}")
            emptyList()
        }
    }

    override suspend fun saveOrders(orders: List<Order>) {
        stateFile.writeText(
            json.encodeToString(
                ListSerializer(Order.serializer()),
                orders
            )
        )
    }

    override suspend fun loadIncomingOrders(): List<Order> {
        val files = incomingDirectory.listFiles()
            ?.filter { it.extension == "json" }
            ?: return emptyList()

        val current = loadOrders()
        val next = nextId(current)

        val incomingOrders = mutableListOf<Order>()

        println("Loaded ${files.size} incoming orders")
        for (file in files) {
            println("Reading file: ${file.name}")
            try {

                val content = file.readText()
                println("File content: $content")

                val parsed = importer.parse(file.name, content)
                    ?: continue

                println("Parsed result: $parsed")
                val order = parsed.toOrder(next + incomingOrders.size + 1)

                incomingOrders.add(order)

                file.copyTo(File(loadedDirectory, file.name), overwrite = true)
                file.delete()
            } catch( e: Exception){
                println("Failed to load incoming orders: ${e.message}")
            }
        }
        println("Incoming dir: ${incomingDirectory.absolutePath}")
        println("Files found: ${incomingDirectory.listFiles()?.toList()}")
        return incomingOrders
    }
}