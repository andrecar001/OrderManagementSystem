package org.example.ordermanagementsystem.domain

import org.example.ordermanagementsystem.domain.model.Item
import org.example.ordermanagementsystem.domain.model.Order
import kotlin.test.*
import kotlin.time.Clock

class OrderTest {
    var itemList = listOf(
        Item("Desk", 1, 100.00),
        Item("Mouse", 1, 10.00),
        Item("Keyboard", 1, 12.00)
    )
    var incomingOrder = Order(orderNumber = 9000, type = "ship", items = itemList)
    var inprogressOrder = Order(orderNumber = 9001, type = "ship", stage = "in progress", items = itemList)
    var completedOrder = Order(orderNumber = 9002, type = "ship", stage = "completed")
    var cancelledOrder = Order(orderNumber = 9003, type = "ship", stage = "cancelled")

    @Test
    fun totalPrice_ReturnsCorrectOrderTotal() {
        assertEquals(122.00, incomingOrder.totalPrice())
    }

    // Test process() runs as expected when Order stage = in progress
    @Test
    fun process_IncomingStage_ChangeToInProgress() {
        var newOrder = incomingOrder.process(1)

        assertEquals("in progress", newOrder.stage)
    }
    @Test
    fun process_IncomingStage_SetWarehouseNumber() {
        var newOrder = incomingOrder.process(1)

        assertEquals(1, newOrder.warehouseNumber)
    }

    // Test process() does NOT change stage or warehouseNumber for a completed Order
    @Test
    fun process_Completed_NoStageChange() {
        var newOrder = completedOrder.process(1)

        assertEquals("completed", newOrder.stage)
    }
    @Test
    fun process_Completed_NoWarehouseNumChange() {
        var newOrder = completedOrder.process(1)

        assertEquals(null, newOrder.warehouseNumber)
    }

    // Test complete() updates stage to complete, if stage = in progress
    @Test
    fun complete_InProgress_ChangeToComplete() {
        var newOrder = inprogressOrder.complete()

        assertEquals("complete", newOrder.stage)
    }
    // Test complete() does NOT change stage, if stage = cancelled
    @Test
    fun complete_cancelled_NoStageChange() {
        var newOrder = cancelledOrder.complete()

        assertEquals("cancelled", newOrder.stage)
    }


}