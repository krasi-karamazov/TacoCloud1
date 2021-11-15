package kpk.com.tacocloud.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotNull

@Entity(name = "users")
class User(
    private val userName: String,
    private val userPassword: String,
    private val fullName: String,
    private val street: String,
    private val city: String,
    private val state: String,
    private val zip: String,
    private val phoneNumber: String,

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private val id: Long = -1


) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = mutableListOf<GrantedAuthority>(SimpleGrantedAuthority("ROLE_USER"))

    override fun getPassword(): String = userPassword

    override fun getUsername(): String = userName

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean  = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}
