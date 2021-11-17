package kpk.com.tacocloud.repository.jpa

import kpk.com.tacocloud.model.Order
import kpk.com.tacocloud.model.User
import org.springframework.data.repository.CrudRepository

interface OrderJPARepository : CrudRepository<Order, Long> {
    fun findByUserOrderByPlacedAtDesc(user: User): List<Order>
}