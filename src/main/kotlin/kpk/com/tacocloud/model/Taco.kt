package kpk.com.tacocloud.model

import org.hibernate.Hibernate
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Entity(name = "tacos")
data class Taco(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotBlank
    var id: Long,

    @NotBlank
    @Size(min = 5, message = "Your name must be atleast 5 chars long")
    val name: String,
    @Size(min = 1, message = "Choose at least 1 ingredient")

    @OneToMany(targetEntity = Ingredient::class)
    var ingredients: List<Ingredient>?,
    var createdAt: Date?
) {
    constructor(): this(-1L, "", null, null, )

    @PrePersist
    fun createdAt() {
        createdAt = Date()
    }

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
