package kpk.com.tacocloud.repository.jpa

import kpk.com.tacocloud.model.Taco
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
@Qualifier("TacoJPARepository")
interface TacoJPARepository : CrudRepository<Taco, Long>