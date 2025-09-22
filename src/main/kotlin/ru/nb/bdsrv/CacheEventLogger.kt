package ru.nb.bdsrv

import org.ehcache.event.CacheEvent
import org.ehcache.event.CacheEventListener
import ru.nb.bdsrv.auth.domain.service.Log

class CacheEventLogger : CacheEventListener<Any, Any> {

    override fun onEvent(cacheEvent: CacheEvent<out Any, out Any>) {
        log.info("key: {} old: {} new: {}", cacheEvent.getKey(), cacheEvent.getOldValue(), cacheEvent.getNewValue())
    }

    companion object : Log()
}