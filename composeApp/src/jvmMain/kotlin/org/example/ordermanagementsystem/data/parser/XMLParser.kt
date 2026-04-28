package org.example.ordermanagementsystem.data.parser

actual class XMLParser : OrderParser {
    actual override fun canParse(fileName: String): Boolean {
        return fileName.endsWith(".xml")
    }

    actual override fun parse(fileName: String): ParsedOrder {
        TODO("Not yet implemented")
        //Figure this out pls
    }
}