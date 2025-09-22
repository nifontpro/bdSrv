package ru.nb.bdsrv

import org.springframework.boot.fromApplication
import org.springframework.boot.with


fun main(args: Array<String>) {
    fromApplication<BdSrvApplication>().with(TestcontainersConfiguration::class).run(*args)
}
