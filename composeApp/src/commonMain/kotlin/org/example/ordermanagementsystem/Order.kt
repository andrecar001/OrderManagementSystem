package org.example.ordermanagementsystem

import com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date
import java.util.Date
class
Order (
    val orderNumber: Int,
    val type: String,
    date: Date){

    val items = mutableListOf<Item>()
    var stage = "incoming"
    var warehouseID = -1


    fun getOrderNumber(): Int {
        return orderNumber
    }

    fun getType(): String {
        return type
    }

    fun getStage(): String {
        return stage
    }

    fun getItems():List<Item>{
        return items
    }

    fun getWarehouseID(): Int {
        return warehouseID
    }

    fun startFulfilling(w: Int) {
        warehouseID = w
        stage = "in progress"
    }

    fun completeOrder() {
        stage = "complete"
    }

    fun cancelOrder() {
        stage = "canceled"
    }

    fun reinstateOrder() {
        stage = "incoming"
    }


}