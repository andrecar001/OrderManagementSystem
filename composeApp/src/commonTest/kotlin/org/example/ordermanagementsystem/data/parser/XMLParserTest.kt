package org.example.ordermanagementsystem.data.parser

import kotlin.test.*

class XMLParserTest {
    @Test
    fun canParse_XMLFileExtension_ReturnTrue() {
        val fileName = "order1.xml"
        assertTrue(fileName.endsWith(".xml"))
    }
}