package kpk.com.tacocloud.model

import org.hibernate.validator.constraints.CreditCardNumber
import javax.validation.constraints.Digits
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

data class Order(
    @NotBlank var name: String = "",
    @NotBlank var street: String = "",
    @NotBlank var city: String = "",
    @NotBlank var state: String = "",
    var zip: String = "",
    @CreditCardNumber var ccNumber: String = "",
    @Pattern(regexp = "(?:0[1-9]|1[0-2])/[0-9]{2}") var ccExpiration: String = "",
    @Digits(integer = 3, fraction = 0) var ccCVV: String = "",
    var taco: Taco? = null,
    var placedAt: Long = 0L,
    var id: Long = 0L
)
