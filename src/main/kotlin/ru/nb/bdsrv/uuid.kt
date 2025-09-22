package ru.nb.bdsrv

import com.fasterxml.jackson.annotation.ObjectIdGenerators
import org.hibernate.id.UUIDGenerator
import java.util.*

fun genFnv1aHash(input: String?): ULong {
//        val offsetBasis = 0xcbf29ce484222325L
    val offsetBasis: ULong = 2166136261U // Начальное значение (для 64 бит)
    val prime = 16777619u // Простое число-множитель

    if (input.isNullOrEmpty()) return 0U

    var hash = offsetBasis
    for (c in input) {
        hash = hash xor c.code.toULong()
        hash *= prime
    }
    return hash
}



fun main() {
    val id = genFnv1aHash("123")
//    val id = "123"
    println(id)

    val inputStr = "I am not a standard UUID representation."

    val uuid = UUID.nameUUIDFromBytes(inputStr.toByteArray())
    val uuid2 = UUID.nameUUIDFromBytes(inputStr.toByteArray())
    println(uuid)
    println(uuid2)
}