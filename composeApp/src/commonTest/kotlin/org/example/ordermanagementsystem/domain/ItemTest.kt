package org.example.ordermanagementsystem.domain

import org.example.ordermanagementsystem.domain.model.Item
import kotlin.test.*

class ItemTest {
    val item = Item("Desk", 6, 100.00)

    // total
    @Test
    fun total_ReturnCorrectTotal() {
        assertEquals(600.00, item.total)
    }

}