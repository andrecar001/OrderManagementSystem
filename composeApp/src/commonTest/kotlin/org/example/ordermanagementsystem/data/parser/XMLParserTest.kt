package org.example.ordermanagementsystem.data.parser

import kotlin.test.*

class XMLParserTest {
    var parser = XMLParser()

    @Test
    fun canParse_XMLFileExtension_ReturnTrue() {
        val fileName = "order1.xml"
        assertTrue(parser.canParse(fileName))
        //assertTrue(fileName.endsWith(".xml"))
    }
}