package ru.nb.bdsrv.auth.data.repo

import org.springframework.data.jpa.repository.JpaRepository
import ru.nb.bdsrv.auth.data.model.RefreshTokenEntity

interface RefreshTokenRepository : JpaRepository<RefreshTokenEntity, Long> {
    fun findByUserIdAndHashedToken(userId: Long, hashedToken: String): RefreshTokenEntity?
    fun deleteByUserIdAndHashedToken(userId: Long, hashedToken: String)
}