package kpk.com.tacocloud.model

import org.hibernate.Hibernate
import java.io.Serializable
import javax.persistence.*

@Entity(name = "ingredients")
data class Ingredient(
    @Id
    val ingredientIdentifier: String,
    val name: String,
    val type:String) : Serializable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Ingredient

        return ingredientIdentifier == other.ingredientIdentifier
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(ingredientId = $ingredientIdentifier , name = $name , type = $type )"
    }

}
