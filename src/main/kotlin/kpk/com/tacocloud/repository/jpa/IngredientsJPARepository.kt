package kpk.com.tacocloud.repository.jpa

import kpk.com.tacocloud.model.Ingredient
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.jpa.repository.query.Procedure
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

interface IngredientsJPARepository : CrudRepository<Ingredient, String>

