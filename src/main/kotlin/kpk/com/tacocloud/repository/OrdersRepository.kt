package kpk.com.tacocloud.repository

import kpk.com.tacocloud.model.Order

interface OrdersRepository {
    fun saveOrder(order: Order): Order
}