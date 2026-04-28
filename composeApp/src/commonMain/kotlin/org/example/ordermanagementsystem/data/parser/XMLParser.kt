package org.example.ordermanagementsystem.data.parser

class XMLParser(): OrderParser {
    override fun canParse(fileName: String): Boolean {
        return fileName.endsWith(".xml")
    }

    override fun parse(content: String): ParsedOrder {
        TODO("Not yet implemented")
        //Figure this out pls
    }
}