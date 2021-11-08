package kpk.com.tacocloud.repository.jpa

import kpk.com.tacocloud.model.Order
import org.springframework.data.repository.CrudRepository

interface OrderJPARepository : CrudRepository<Order, Long>