package ru.nb.bdsrv.auth.data.repo

import jakarta.persistence.QueryHint
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.data.jpa.repository.QueryHints
import org.springframework.stereotype.Repository
import ru.nb.bdsrv.auth.data.model.UserEntity

@Repository
interface UserRepository : JpaRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {

    @QueryHints(QueryHint(name = "org.hibernate.cacheable", value = "true"))
    @Query("from UserEntity where true")
    fun selectAll(): List<UserEntity>

    fun findByEmail(email: String): UserEntity?

    @QueryHints(QueryHint(name = "org.hibernate.cacheable", value = "true"))
    @Query("from UserEntity where id=:id")
    fun selectById(id: Long): UserEntity?
}