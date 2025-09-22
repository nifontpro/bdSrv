package ru.nb.bdsrv.auth.domain.model

data class User(
    val id: Long = 0,
    val email: String,
    val hashedPassword: String,
)