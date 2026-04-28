package org.example.ordermanagementsystem.data.parser

expect class XMLParser(): OrderParser {
    override fun canParse(fileName: String): Boolean
    override fun parse(content: String): ParsedOrder
}