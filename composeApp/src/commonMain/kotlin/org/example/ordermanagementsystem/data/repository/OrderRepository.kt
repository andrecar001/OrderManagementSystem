package org.example.ordermanagementsystem.data.repository

import org.example.ordermanagementsystem.domain.model.Order

/**
 * Order repository
 *
 * @constructor Create empty Order repository
 */
interface OrderRepository {
    /**
     * Load orders
     *
     * @return
     */
    suspend fun loadOrders(): List<Order>

    /**
     * Save orders
     *
     * @param orders
     */
    suspend fun saveOrders(orders: List<Order>)

    /**
     * Load incoming orders
     *
     * @return
     */
    suspend fun loadIncomingOrders(): List<Order>

    /**
     * Merge incoming orders
     *
     * @return
     */
    suspend fun mergeIncomingOrders(): List<Order>

}