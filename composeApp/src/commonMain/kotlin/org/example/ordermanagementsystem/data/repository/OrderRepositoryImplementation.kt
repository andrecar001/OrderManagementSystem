package org.example.ordermanagementsystem.data.repository

import org.example.ordermanagementsystem.data.model.Order
import org.example.ordermanagementsystem.data.parser.JSONHandler
import org.example.ordermanagementsystem.data.parser.XMLHandler

class OrderRepositoryImplementation : OrderRepository{
    private val orders = mutableListOf<Order>()

    override fun getOrders(): List<Order> = orders

    override fun updateOrders(newOrders: List<Order>) {
        orders.clear()
        orders.addAll(newOrders)
    }

  /*
    fun fromJSON(json: String): List<Order> =
        JSONHandler.import(json)
    fun fromXML(xml: String): List<Order> =
        XMLHandler.import(xml)

    */
}