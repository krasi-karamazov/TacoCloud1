package kpk.com.tacocloud.repository

import kpk.com.tacocloud.model.Ingredient

interface IngredientsRepository {
    fun findAll(): Map<String, List<Ingredient>>?
    fun findOne(id: String): Ingredient?
    fun save(ingredient: Ingredient): Ingredient?
}