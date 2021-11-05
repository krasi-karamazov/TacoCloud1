package kpk.com.tacocloud.model

import org.hibernate.Hibernate
import java.util.*
import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.Id
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Entity
data class Taco(
    @Id
    @NotBlank val id: Long,
    @NotBlank val name: String,
    @NotEmpty
    @NotNull
    @ElementCollection
    var ingredients: List<String>?,
    var createdAt: Date?
) {
    constructor(): this(-1L, "", null, null, )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Taco

        return id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , name = $name , ingredients = $ingredients , createdAt = $createdAt )"
    }
}
