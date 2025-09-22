package ru.nb.bdsrv.auth.data.model

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.Instant

@Entity
@Table(name = "refresh_token", schema = "auth")

data class RefreshTokenEntity(
    @Id
    var userId: Long,
    var expiresAt: Instant,
    var hashedToken: String,
    var createdAt: Instant = Instant.now()
)
