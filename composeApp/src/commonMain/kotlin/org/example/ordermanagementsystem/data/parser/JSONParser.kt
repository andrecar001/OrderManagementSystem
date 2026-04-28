package org.example.ordermanagementsystem.data.parser

import kotlinx.serialization.json.Json
import org.example.ordermanagementsystem.data.data_transfer_objects.JSONOrderWrapper
import org.example.ordermanagementsystem.domain.model.Item
import org.example.ordermanagementsystem.data.parser.ParsedOrder

class JSONParser(
    private val json: Json
) : OrderParser {


    override fun canParse(fileName: String) =
        fileName.endsWith(".json")

    override fun parse(fileName: String): ParsedOrder {
        val dto = json.decodeFromString<JSONOrderWrapper>(fileName)

        return ParsedOrder(
            type = dto.order.type,
            date = dto.order.order_date,
            items = dto.order.items.map {
                Item(it.name, it.quantity, it.price)
            }
        )
    }
}