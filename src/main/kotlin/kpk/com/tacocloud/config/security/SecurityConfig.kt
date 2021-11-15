package kpk.com.tacocloud.config.security

import kpk.com.tacocloud.model.User
import kpk.com.tacocloud.repository.jpa.UserRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig {

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun userDetailsService(userRepository: UserRepository): UserDetailsService {
        return object : UserDetailsService {
            override fun loadUserByUsername(username: String?): UserDetails {
                val user: User? = userRepository.findUserByUserName(username ?: "")
                if (user != null) {
                    return user
                }
                throw UsernameNotFoundException("User by the name $username not found")
            }
        }
    }

    @Bean
    fun filterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        return httpSecurity
            .authorizeRequests()
            .antMatchers("/design").hasRole("USER")
            .antMatchers("/orders").hasRole("USER")
            .antMatchers("/", "/**").permitAll()
            .and()
            .formLogin()
            .loginPage("/login")
            .defaultSuccessUrl("/design", true)
            .and()
            .build()
    }
}
