package kpk.com.tacocloud.model

import org.hibernate.validator.constraints.CreditCardNumber
import javax.validation.constraints.Digits
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

data class Order(
    @NotBlank var deliveryName: String = "",
    @NotBlank var deliveryStreet: String = "",
    @NotBlank var deliveryCity: String = "",
    @NotBlank var deliveryState: String = "",
    var deliveryZip: String = "",
    @CreditCardNumber
    var ccNumber: String = "",
    @Pattern(regexp = "(?:0[1-9]|1[0-2])/[0-9]{2}")
    var ccExpiration: String = "",
    @Digits(integer = 3, fraction = 0)
    var ccCVV: String = "",
    @Transient
    var tacos: MutableList<Taco> = mutableListOf(),
    var placedAt: Long = 0L,
    var id: Long = 0L
)
