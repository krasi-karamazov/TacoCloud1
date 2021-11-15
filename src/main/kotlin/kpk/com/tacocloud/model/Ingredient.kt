package kpk.com.tacocloud.model

import org.hibernate.Hibernate
import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "ingredients")
data class Ingredient(
    @Id
    var id: Long,
    val ingredientIdentifier: String,
    val name: String,
    val type:String) {
    constructor() : this(0, "", "", IngredientType.WRAP.name)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Ingredient

        return id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , ingredientId = $ingredientIdentifier , name = $name , type = $type )"
    }

}
