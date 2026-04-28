package org.example.ordermanagementsystem.domain.model

data class ParsedOrder(
    val type: String,
    val date: Long,
    val items: List<Item>,
    val orderNumber: Int? = null
)
