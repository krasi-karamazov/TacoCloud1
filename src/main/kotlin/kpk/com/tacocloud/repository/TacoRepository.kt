package kpk.com.tacocloud.repository

import kpk.com.tacocloud.model.Taco

interface TacoRepository {
    fun save(taco: Taco): Taco?
}