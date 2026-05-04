package org.example.ordermanagementsystem.data.parser

import kotlinx.serialization.json.Json
import kotlin.collections.listOf
import kotlin.test.*

class OrderImporterTest {
    // Set-up
    var fileName = "order1.json"
    val json = Json {
        ignoreUnknownKeys = true
    }

    val importer = OrderImporter(
        listOf(JSONParser(json), XMLParser()))

    var content = """
    {
        "order": {
        "type": "ship",
        "order_date": 1711111111111,
        "items": [
        {
            "name": "Desk",
            "quantity": 1,
            "price": 199.99
        }
        ]
    }
    }"""

    @Test
    fun parse_ValidFileContent_ReturnsParsedOrder() {
        var newParsedOrder = importer.parse(fileName, content)

        println("Parsed Order: $newParsedOrder")
    }
    @Test
    fun parse_TxtFile_ReturnsNull() {
        var newParsedOrder = importer.parse("order1.txt", content)

        assertNull(newParsedOrder)
    }
    @Test
    fun parse_InvalidContent_ThrowsException() {
        assertFails(
            message = "Invalid Content Argument",
            block = {importer.parse(fileName, "no_content")})
    }

}