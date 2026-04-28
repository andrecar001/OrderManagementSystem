package org.example.ordermanagementsystem.data.parser

interface OrderParser {
    fun canParse(fileName: String): Boolean
    fun parse(content: String): ParsedOrder
}