package kpk.com.tacocloud.model

import org.hibernate.Hibernate
import org.hibernate.validator.constraints.CreditCardNumber
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Digits
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

@Entity(name = "orders")
data class Order(
    @NotBlank var deliveryName: String,
    @NotBlank var deliveryStreet: String,
    @NotBlank var deliveryCity: String,
    @NotBlank var deliveryState: String,
    var deliveryZip: String,
    @CreditCardNumber
    var ccNumber: String,
    @Pattern(regexp = "(?:0[1-9]|1[0-2])/[0-9]{2}")
    var ccExpiration: String,
    @Digits(integer = 3, fraction = 0)
    var ccCVV: String,
    @OneToMany(targetEntity = Taco::class)
    var tacos: MutableList<Taco>,
    var placedAt: Date?,
    @ManyToOne
    var user: User? = null,
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long
) {
     constructor() : this("", "", "",
         "", "", "", "",
         "", mutableListOf(), null, null, 0L)
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Order

        return id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , deliveryName = $deliveryName , deliveryStreet = $deliveryStreet , deliveryCity = $deliveryCity , deliveryState = $deliveryState , deliveryZip = $deliveryZip , ccNumber = $ccNumber , ccExpiration = $ccExpiration , ccCVV = $ccCVV , tacos = $tacos , placedAt = $placedAt )"
    }

    @PrePersist
    fun placedAt() {
        placedAt = Date()
    }
}
