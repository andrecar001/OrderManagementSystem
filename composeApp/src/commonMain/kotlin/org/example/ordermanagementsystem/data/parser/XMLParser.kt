package org.example.ordermanagementsystem.data.parser

import org.example.ordermanagementsystem.domain.model.Item
import kotlin.time.Clock

class XMLParser() : OrderParser {

    override fun canParse(fileName: String): Boolean {
        return fileName.endsWith(".xml") // checks if this parser should handle the file (used by orderimporter)
    }

    override fun parse(content: String): ParsedOrder {

        // pulling the id from the xml string so we can use it as orderNumber later
        val id = Regex("<Order[^>]*id=\"(\\d+)\"")
            .find(content)
            ?.groupValues
            ?.getOrNull(1)
            ?.trim()
            ?.toIntOrNull()

        // getting the type from xml (same idea as jsonparser, just manually)
        val type = Regex("<OrderType>(.*?)</OrderType>")
            .find(content)
            ?.groupValues
            ?.getOrNull(1)
            ?.trim()
            ?: ""

        // this finds every <item> block inside the xml
        val itemRegex = Regex(
            "<Item[^>]*type=\"(.*?)\">\\s*<Price[^>]*>(.*?)</Price>\\s*<Quantity>(.*?)</Quantity>\\s*</Item>",
        )

        // turning each xml item into an Item object (this is what the system actually uses)
        val items = itemRegex.findAll(content).map { match ->
            Item(
                name = match.groupValues[1].trim(), // name from xml
                quantity = match.groupValues[3].trim().toIntOrNull() ?: 0, // quantity from xml
                price = match.groupValues[2].trim().toDoubleOrNull() ?: 0.0 // price from xml
            )
        }.toList()

        // making a parsedorder (this is the same format jsonparser returns)
        // orderimporter will take this and later convert it into a full Order object
        return ParsedOrder(
            type = type,
            date = Clock.System.now().toEpochMilliseconds(), // xml doesnt have date so use current time
            items = items,
            orderNumber = id
        )
    }
}
