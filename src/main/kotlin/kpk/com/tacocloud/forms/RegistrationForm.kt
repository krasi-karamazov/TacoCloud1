package kpk.com.tacocloud.forms

import kpk.com.tacocloud.model.User
import org.springframework.security.crypto.password.PasswordEncoder

data class RegistrationForm (
    var userName: String = "null",
    var password: String = "null",
    var fullName: String = "null",
    var street: String = "null",
    var city: String = "null",
    var state: String = "null",
    var zip: String = "null",
    var phone: String = "null"
) {
    fun toUser(passwordEncoder: PasswordEncoder): User {
        return User(userName, passwordEncoder.encode(password), fullName, street, city, state, zip, phone)
    }
}