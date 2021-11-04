package kpk.com.tacocloud.repository.jdbc

import com.fasterxml.jackson.databind.ObjectMapper
import kpk.com.tacocloud.model.Order
import kpk.com.tacocloud.repository.OrdersRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.simple.SimpleJdbcInsert
import java.util.*

class OrdersJDBCRepository constructor(@Autowired private val jdbcTemplate: JdbcTemplate) : OrdersRepository {
    private val jdbcInserter = SimpleJdbcInsert(jdbcTemplate)
        .withTableName("orders")
        .usingGeneratedKeyColumns("id")
    private val objectMapper: ObjectMapper = ObjectMapper()

    override fun saveOrder(order: Order): Order {
        order.placedAt = Date().time
        val orderID = saveOrderDetails(order)
        order.id = orderID
        return order
    }

    private fun saveOrderDetails(order: Order): Long {
        val values = objectMapper.convertValue(order, Map::class.java).toMutableMap()
        values["placedAt"] = order.placedAt
    }
}