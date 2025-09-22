package ru.nb.bdsrv.auth.rest

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import ru.nb.bdsrv.auth.domain.model.User
import ru.nb.bdsrv.auth.domain.service.IUserService
import ru.nb.bdsrv.auth.domain.service.NumberService
import java.math.BigDecimal
import kotlin.random.Random

@RestController
class UserController(
    private val userService: IUserService,
    private val numberService: NumberService
) {

    @GetMapping("/users")
    fun getUsers(): List<User> {
        return userService.getUsers()
    }

    @GetMapping("/user/{id}")
    fun getById(@PathVariable id: Long): User? {
        return userService.getById(id)
    }

    @PostMapping("/user")
    fun update(@RequestBody user: User) {
        userService.update(user)
    }

    @GetMapping("/sql/{number}")
    fun getSql(@PathVariable number: Long): BigDecimal {
        return numberService.square(number)
    }

}