package kpk.com.tacocloud.repository.jdbc

import com.fasterxml.jackson.databind.ObjectMapper
import kpk.com.tacocloud.model.Order
import kpk.com.tacocloud.repository.OrdersRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.simple.SimpleJdbcInsert
import org.springframework.stereotype.Repository
import java.sql.Timestamp
import java.util.*

@Repository
@Qualifier("OrdersJDBCRepository")
class OrdersJDBCRepository constructor(@Autowired private val jdbcTemplate: JdbcTemplate) : OrdersRepository {
    private val orderInserter = SimpleJdbcInsert(jdbcTemplate)
        .withTableName("orders")
        .usingGeneratedKeyColumns("id")
    private val orderedTacoInserter = SimpleJdbcInsert(jdbcTemplate)
        .withTableName("taco_orders")
    private val objectMapper: ObjectMapper = ObjectMapper()

    override fun saveOrder(order: Order): Order {
        val orderID = saveOrderDetails(order)
        order.id = orderID
        saveOrderedTacos(order)
        return order
    }

    private fun saveOrderedTacos(order: Order) {
        order.tacos.forEach{
            val values = mutableMapOf<String, Long>()
            values["tacoOrder"] = order.id
            values["taco"] = it.id
            orderedTacoInserter.execute(values)
        }
    }

    private fun saveOrderDetails(order: Order): Long {
        val values: MutableMap<String, Any> =
            objectMapper.convertValue(order, Map::class.java) as MutableMap<String, Any>
        values["placedAt"] = Timestamp(order.placedAt?.time ?: 0)
        return orderInserter.executeAndReturnKey(values).toLong()
    }
}