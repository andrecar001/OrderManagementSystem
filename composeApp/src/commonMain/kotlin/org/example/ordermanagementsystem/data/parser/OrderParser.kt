package org.example.ordermanagementsystem.data.parser

import org.example.ordermanagementsystem.domain.model.Order
import org.example.ordermanagementsystem.domain.model.ParsedOrder

interface OrderParser {
    fun canParse(fileName: String): Boolean
    fun parse(fileName: String): ParsedOrder
}