package org.example.ordermanagementsystem.data.parser

import org.example.ordermanagementsystem.data.mapper.toOrder
import kotlin.test.*

class XMLParserTest {
    var parser = XMLParser()
    var content = """
        <Orders>
            <Order id="103">
                <OrderType>Delivery</OrderType>
                <Item type="Ice Cream">
                    <Price>4.75</Price>
                    <Quantity>2</Quantity>
                </Item>
            </Order>
        </Orders>"""

    @Test
    fun canParse_XMLFileExtension_ReturnTrue() {
        val fileName = "order1.xml"
        assertTrue(parser.canParse(fileName))
    }

    @Test
    fun parse_ValidContent_ReturnsParsedOrder() {
        var newParsedOrder = parser.parse(content)

        println("ParsedOrder: $newParsedOrder")
        assertTrue(newParsedOrder is ParsedOrder)
    }
    // Note: This test fails, but XMLParser makes an empty ParsedOrder / does not add "no_content" to anything.
    @Test
    fun parse_InvalidContent_ThrowsException() {
        assertFails(
            message = "Invalid Content Argument",
            block = {parser.parse("no_content")})
        //var newParsedOrder = parser.parse("no_content")
        //println("ParsedOrder: $newParsedOrder")

    }


}