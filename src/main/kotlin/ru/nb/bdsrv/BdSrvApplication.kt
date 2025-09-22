package ru.nb.bdsrv

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
class BdSrvApplication

fun main(args: Array<String>) {
    runApplication<BdSrvApplication>(*args)
}
