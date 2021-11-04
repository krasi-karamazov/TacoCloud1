package kpk.com.tacocloud.model

import java.util.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class Taco(
    @NotBlank var id: Long = -1,
    @NotBlank val name: String = "",
    @NotEmpty @NotNull val ingredients: List<String>? = null,
    var createdAt: Date? = null
)
