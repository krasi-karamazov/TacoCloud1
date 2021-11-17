package kpk.com.tacocloud.repository.jpa

import kpk.com.tacocloud.model.Taco
import org.springframework.data.repository.CrudRepository

interface TacoJPARepository : CrudRepository<Taco, Long>