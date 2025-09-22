package ru.nb.bdsrv.auth.data.repo

import jakarta.persistence.EntityManager
import org.springframework.stereotype.Service
import ru.nb.bdsrv.auth.data.model.UserEntity
import ru.nb.bdsrv.auth.data.model.toUser
import ru.nb.bdsrv.auth.data.model.toUserEntity
import ru.nb.bdsrv.auth.domain.model.User
import ru.nb.bdsrv.auth.domain.service.IUserService

@Service
class UserService(
    private val userRepository: UserRepository,
//    private val entityManager: EntityManager
) : IUserService {

    override fun getUsers(): List<User> {
//        val query = entityManager.createQuery("from " + UserEntity::class)
//        query.setHint("org.hibernate.cacheable", true)
        return userRepository.selectAll().map { it.toUser() }
    }

    override fun getById(id: Long) = userRepository.selectById(id)?.toUser()

    override fun update(user: User) {
        userRepository.saveAndFlush(user.toUserEntity())
    }

}