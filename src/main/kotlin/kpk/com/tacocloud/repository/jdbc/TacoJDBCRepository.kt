package kpk.com.tacocloud.repository.jdbc

import kpk.com.tacocloud.model.Taco
import kpk.com.tacocloud.repository.TacoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.PreparedStatementCreatorFactory
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.stereotype.Repository
import java.sql.Timestamp
import java.sql.Types
import java.util.*

@Repository
@Qualifier("TacoJDBCRepository")
class TacoJDBCRepository constructor(@Autowired val jdbcTemplate: JdbcTemplate): TacoRepository {
    override fun save(taco: Taco): Taco? {
        val tacoId = saveTacoInfo(taco)
        taco.id = tacoId
        return if(tacoId == (-1).toLong()) {
            null
        } else {
            taco.ingredients?.forEach {
                jdbcTemplate.update("insert into tacos_ingredients (taco, ingredient) values ( ?, ? )", taco.id, it)
            }

            taco
        }
    }

    private fun saveTacoInfo(taco: Taco): Long {
        val createdAt = Date().time
        val preparedStatementCreatorFactory = PreparedStatementCreatorFactory("insert into tacos (name, CREATEAT) values ( ?, ? )",
            Types.VARCHAR, Types.TIMESTAMP)
        preparedStatementCreatorFactory.setReturnGeneratedKeys(true)
        val preparedStatementCreator = preparedStatementCreatorFactory.newPreparedStatementCreator(listOf(taco.name, Timestamp(createdAt)))
        val keyHolder = GeneratedKeyHolder()
        jdbcTemplate.update(preparedStatementCreator, keyHolder)
        return keyHolder.key?.toLong() ?: -1
    }
}