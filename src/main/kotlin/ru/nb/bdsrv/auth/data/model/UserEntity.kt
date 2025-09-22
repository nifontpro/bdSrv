package ru.nb.bdsrv.auth.data.model

import jakarta.persistence.*
import org.hibernate.annotations.Cache
import org.hibernate.annotations.CacheConcurrencyStrategy
import ru.nb.bdsrv.auth.domain.model.User

@Entity
@Table(name = "usr", schema = "auth")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "user")
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    var email: String = "",
    var hashedPassword: String = "",
)

fun User.toUserEntity() = UserEntity(
//    id = this.id.takeIf { it != 0L },
    id = this.id,
    email = this.email,
    hashedPassword = this.hashedPassword
)

fun UserEntity.toUser() = User(
//    id = this.id ?: 0,
    id = this.id,
    email = this.email,
    hashedPassword = this.hashedPassword
)
