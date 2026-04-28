package org.example.ordermanagementsystem.data.parser

import org.example.ordermanagementsystem.data.parser.ParsedOrder

class OrderImporter (
    private val parsers: List<OrderParser>
){
    fun parse(fileName: String, content: String): ParsedOrder? {
        val parser = parsers.firstOrNull { it.canParse(fileName) }
            ?: return null
        return parser.parse(content)
    }
}