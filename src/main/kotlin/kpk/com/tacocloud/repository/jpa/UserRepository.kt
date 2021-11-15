package kpk.com.tacocloud.repository.jpa

import kpk.com.tacocloud.model.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Long> {
    fun findUserByUserName(userName: String): User?
}