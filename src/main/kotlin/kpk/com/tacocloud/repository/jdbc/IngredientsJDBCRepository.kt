package kpk.com.tacocloud.repository.jdbc

import kpk.com.tacocloud.model.Ingredient
import kpk.com.tacocloud.model.IngredientType
import kpk.com.tacocloud.repository.IngredientsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import java.sql.ResultSet
import java.sql.SQLException
import kotlin.jvm.Throws

@Repository
@Qualifier("IngredientsJDBCRepo")
class IngredientsJDBCRepository constructor(@Autowired val jdbcTemplate: JdbcTemplate) : IngredientsRepository {

    override fun findAll(): Map<String, List<Ingredient>>? {
        return try {
            val ingredientsList = jdbcTemplate.query("select * from ingredients", this::mapRowToIngredient)
            return mapListToMap(ingredientsList)

        } catch (e: SQLException) {
            null
        }
    }

    private fun mapListToMap(ingredientsList: List<Ingredient>): Map<String, List<Ingredient>> {
        val map = mutableMapOf<String, MutableList<Ingredient>>()
        ingredientsList.forEach { ingredient ->
            if (map.containsKey(ingredient.type)) {
                map[ingredient.type]?.add(ingredient)
            } else {
                map[ingredient.type] = mutableListOf(ingredient)
            }
        }
        return map
    }

    override fun findOne(id: Long): Ingredient? {
        return try {
            jdbcTemplate.queryForObject("select * from ingredients", this::mapRowToIngredient)
        } catch (e: SQLException) {
            null
        }
    }

    override fun save(ingredient: Ingredient): Ingredient? {
        val rowsAffected = jdbcTemplate.update("insert into ingredients (ingredientId, name, type) values (?, ?, ?)", ingredient.id, ingredient.name, ingredient.type)
        return if (rowsAffected > 0) {
            ingredient
        } else {
            null
        }
    }

    @Throws(SQLException::class)
    private fun mapRowToIngredient(rs: ResultSet, rowNum: Int): Ingredient {
        return Ingredient(rs.getLong("id"),
            rs.getString("ingredientId"),
            rs.getString("name"),
            rs.getString("type")
        )
    }
}