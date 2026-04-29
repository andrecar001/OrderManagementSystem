package org.example.ordermanagementsystem.data.parser

class XMLParser(): OrderParser {
    override fun canParse(fileName: String): Boolean {
        return fileName.endsWith(".xml")
    }

    override fun parse(content: String): ParsedOrder {
        // extract id from xml
        val id = Regex("<id>(.*?)</id>", RegexOption.DOT_MATCHES_ALL)
            .find(content)
            ?.groupValues?.get(1)
            ?.trim()
            ?.toIntOrNull()

    }
}