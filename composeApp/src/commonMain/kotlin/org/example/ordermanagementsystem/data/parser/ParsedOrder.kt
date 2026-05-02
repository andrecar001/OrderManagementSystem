package org.example.ordermanagementsystem.data.parser

import org.example.ordermanagementsystem.domain.model.Item


data class ParsedOrder(
    val type: String,
    val date: Long,
    val items: List<Item>,
    val orderNumber: Int? = null
)