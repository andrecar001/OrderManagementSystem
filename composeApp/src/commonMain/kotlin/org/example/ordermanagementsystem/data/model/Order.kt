package org.example.ordermanagementsystem.data.model

//import java.time.LocalDate
data class Order (
    val orderNumber: Int,
    val type: String,
    val stage : String? = "incoming",
    val warehouseNumber : Int,
    val items: List<Item> = emptyList()
//    val date: Date? = null,
){

    fun totalPrice(): Double = items.sumOf { it.price * it.quantity }

    val formattedPrice : String
        get() = "$"
    fun process(w: Int): Order =
        copy(warehouseNumber = w, stage = "in progress")

    fun complete() : Order =
        copy(stage = "complete")

    fun cancel() : Order =
        copy(stage = "canceled")

    fun reinstate() : Order =
        copy(stage = "incoming")


}