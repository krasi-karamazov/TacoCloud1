package kpk.com.tacocloud.model

import org.hibernate.Hibernate
import org.hibernate.validator.constraints.CreditCardNumber
import javax.persistence.Entity
import javax.persistence.Id
import javax.validation.constraints.Digits
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

@Entity
data class Order(
    @NotBlank val deliveryName: String,
    @NotBlank val deliveryStreet: String,
    @NotBlank val deliveryCity: String,
    @NotBlank val deliveryState: String,
    val deliveryZip: String,
    @CreditCardNumber
    val ccNumber: String,
    @Pattern(regexp = "(?:0[1-9]|1[0-2])/[0-9]{2}")
    val ccExpiration: String,
    @Digits(integer = 3, fraction = 0)
    val ccCVV: String,
    @Transient
    val tacos: MutableList<Taco>,
    val placedAt: Long,
    @Id
    val id: Long
) {
     constructor() : this("", "", "",
         "", "", "", "",
         "", mutableListOf(), 0L, 0L)
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
}
