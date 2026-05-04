package org.example.ordermanagementsystem.data.parser

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.example.ordermanagementsystem.data.data_transfer_objects.JSONOrderWrapper
import org.example.ordermanagementsystem.domain.model.Item
import kotlin.test.*


class JSONParserTest () {
   val json = Json {
      ignoreUnknownKeys = true
   }
   var parser = JSONParser(json)

    var content =
    """
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
   fun canParse_JSONFileExtension_ReturnsTrue() {
      val fileName = "order1.json"
      assertTrue(parser.canParse(fileName))
   }

    @Test
    fun parse_ValidContent_ReturnsParsedOrder() {
        var newParsedOrder = parser.parse(content)

        println("ParsedOrder: $newParsedOrder")
    }
    @Test
    fun parse_InvalidContent_ThrowsException() {
        assertFails(
            message = "Invalid Content Argument",
            block = {parser.parse("no_content")})

    }

}