package org.example.ordermanagementsystem.domain

import org.example.ordermanagementsystem.domain.model.Item
import org.example.ordermanagementsystem.domain.model.Order
import kotlin.test.*
import kotlin.time.Clock

class OrderTest {

    val itemList = listOf(
    Item("Desk", 1, 100.00),
    Item("Mouse", 1, 10.00),
    Item("Keyboard", 1, 12.00)
    )
    val incomingOrder = Order(orderNumber = 9000, type = "ship", items = itemList)
    val inProgressOrder = Order(orderNumber = 9001, type = "ship", stage = "in progress", warehouseNumber = 1, items = itemList)
    val completeOrder = Order(orderNumber = 9002, type = "ship", stage = "complete", warehouseNumber = 1, items = itemList)
    val canceledOrder = Order(orderNumber = 9003, type = "ship", stage = "canceled", warehouseNumber = 1, items = itemList)

    // Reset orderList before each test
    lateinit var orderList: MutableList<Order>
    @BeforeTest
    fun beforeTest() {
        orderList = mutableListOf(incomingOrder, inProgressOrder, completeOrder, canceledOrder)
    }



    // totalPrice
    @Test
    fun totalPrice_ReturnsCorrectOrderTotal() {
        assertEquals(122.00, incomingOrder.totalPrice())
    }


    // process()
    // Test process() runs as expected when Order stage == in progress
    @Test
    fun process_Incoming_SetToInProgress() {
        val newOrder = incomingOrder.process(1)

        assertEquals("in progress", newOrder.stage)
    }
    @Test
    fun process_Incoming_SetWarehouseNumber() {
        val newOrder = incomingOrder.process(1)

        assertEquals(1, newOrder.warehouseNumber)
    }
    // Test process() does NOT change stage or warehouseNumber for when Order != incoming
    @Test
    fun process_NotIncoming_NoStageChange() {
        orderList.remove(incomingOrder)

        // For every order (other than incoming) - check that the newOrder's stage matches the original order's stage (== no stage change)
        for (order in orderList) {
            val newOrder = order.process(1)
            assertEquals(order.stage, newOrder.stage)
            //println("Stage: ${newOrder.stage}")
        }
    }
    @Test
    fun process_NotIncoming_NoWarehouseNumChange() {
        orderList.remove(incomingOrder)

        for (order in orderList) {
            val newOrder = order.process(1)
            assertEquals(order.warehouseNumber, newOrder.warehouseNumber)
            //println("Stage: ${newOrder.stage}")
        }
    }


    // complete()
    // Test complete() updates stage to complete, if stage == in progress
    @Test
    fun complete_InProgress_SetToComplete() {
        val newOrder = inProgressOrder.complete()

        assertEquals("complete", newOrder.stage)
    }
    // Test complete() does NOT change stage, if stage != in progress
    @Test
    fun complete_NotInProgress_NoStageChange() {
        orderList.remove(inProgressOrder)

        for (order in orderList) {
            val newOrder = order.complete()
            assertEquals(order.stage, newOrder.stage)
            //println("Stage: ${newOrder.stage}")
        }
    }


    // cancel()
    // Test cancel() updates stage -> cancel and warehouseNumber -> null, if stage != complete
    @Test
    fun cancel_NotComplete_SetToCanceled() {
        orderList.remove(completeOrder)

        // Check all orders (except complete) are canceled
        for (order in orderList) {
            //println("Stage: ${order.stage}")
            val newOrder = order.cancel()
            assertEquals("canceled", newOrder.stage)
        }
    }
    @Test
    fun cancel_NotComplete_NullifyWarehouseNumber() {
       orderList.remove(completeOrder)

        // Check all orders (except complete) are canceled
        for (order in orderList) {
            //println("Stage: ${order.stage}"); println("w #: ${order.warehouseNumber}")
            val newOrder = order.cancel()
            assertEquals(null, newOrder.warehouseNumber)
        }
    }
    // Test cancel() does NOT update stage or warehouseNumber, if stage == complete
    @Test
    fun cancel_Complete_NoStageChange() {
        val newOrder = completeOrder.cancel()

        assertEquals("complete", newOrder.stage)
    }
    @Test
    fun cancel_Complete_NoWarehouseNumChange() {
        val newOrder = completeOrder.cancel()

        assertEquals(completeOrder.warehouseNumber, newOrder.warehouseNumber)
    }



    // reinstate()

    // Test reinstate() updates stage -> incoming and warehouseNumber -> null, if stage == canceled
    @Test
    fun reinstate_Canceled_SetToIncoming() {
        val newOrder = canceledOrder.reinstate()

        assertEquals("incoming", newOrder.stage)
    }
    @Test
    fun reinstate_Canceled_NullifyWarehouseNumber() {
        val newOrder = canceledOrder.reinstate()

        assertEquals(null, newOrder.warehouseNumber)
    }
    // Test reinstate() does NOT update stage or warehouseNumber, if stage != canceled
    @Test
    fun reinstate_NotCanceled_NoStageChange() {
        orderList.remove(canceledOrder)

        for (order in orderList) {
            val newOrder = order.reinstate()
            assertEquals(order.stage, newOrder.stage)
            //println("Stage: ${newOrder.stage}")
        }
    }
    @Test
    fun reinstate_NotCanceled_NoWarehouseNumChange() {
        orderList.remove(canceledOrder)

        for (order in orderList) {
            val newOrder = order.reinstate()
            assertEquals(order.warehouseNumber, newOrder.warehouseNumber)
            //println("Stage: ${newOrder.stage}")
        }
    }

}