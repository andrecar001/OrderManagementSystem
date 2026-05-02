package org.example.ordermanagementsystem.data.parser

/**
 * Order parser
 *
 * @constructor Create empty Order parser
 */
interface OrderParser {
    fun canParse(fileName: String): Boolean

    fun parse(content: String): ParsedOrder
}