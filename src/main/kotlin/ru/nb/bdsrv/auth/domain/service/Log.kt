package ru.nb.bdsrv.auth.domain.service

// import com.sun.org.slf4j.internal.Logger
// import com.sun.org.slf4j.internal.LoggerFactory

import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Suppress("unused")
abstract class Log {
    protected val log: Logger = LoggerFactory.getLogger(javaClass.enclosingClass)
}
