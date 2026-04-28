package org.example.ordermanagementsystem.domain.model

import kotlinx.serialization.Serializable
import kotlin.time.Clock

//import java.time.LocalDate
@Serializable
data class Order (
    val orderNumber: Int,
    val type: String,
    val stage : String? = "incoming",
    val warehouseNumber : Int? = null,
    val items: List<Item> = emptyList(),
    val date: Long = Clock.System.now().toEpochMilliseconds(),
){

    fun totalPrice(): Double = items.sumOf { it.price * it.quantity }

    fun process(w: Int): Order =
        if (stage == "incoming") {
            copy(warehouseNumber = w, stage = "in progress")
        } else this

    fun complete() : Order =
        if (stage == "in progress") copy(stage = "complete") else this

    fun cancel() : Order =
        if (stage != "complete") copy(stage = "canceled", warehouseNumber = null) else this

    fun reinstate() : Order =
        if (stage == "canceled") {
            copy(stage = "incoming", warehouseNumber = null)
        } else this


}