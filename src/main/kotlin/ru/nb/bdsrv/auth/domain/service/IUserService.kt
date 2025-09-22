package ru.nb.bdsrv.auth.domain.service

import ru.nb.bdsrv.auth.data.model.UserEntity
import ru.nb.bdsrv.auth.domain.model.User
import java.util.Optional

interface IUserService {

    fun getUsers(): List<User>

    fun getById(id: Long): User?
    fun update(user: User)
}