package org.example.ordermanagementsystem.data.parser

import kotlinx.serialization.json.Json
import org.example.ordermanagementsystem.data.data_transfer_objects.JSONOrderWrapper
import org.example.ordermanagementsystem.domain.model.Item

class JSONParser(
    private val json: Json
) : OrderParser {


    override fun canParse(fileName: String) =
        fileName.endsWith(".json")

    override fun parse(content: String): ParsedOrder {
        val dto = json.decodeFromString<JSONOrderWrapper>(content)

        return ParsedOrder(
            orderNumber = 0,
            type = dto.order.type,
            date = dto.order.order_date,
            items = dto.order.items.map {
                Item(it.name, it.quantity, it.price)
            }
        )
    }
}