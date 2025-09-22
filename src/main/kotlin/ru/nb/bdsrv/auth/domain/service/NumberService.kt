package ru.nb.bdsrv.auth.domain.service

import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import java.math.BigDecimal


@Service
class NumberService {

//    @Cacheable(value = ["squareCache"], key = "#number", condition = "#number>10")
    fun square(number: Long): BigDecimal {
        val square = BigDecimal.valueOf(number)
            .multiply(BigDecimal.valueOf(number))
        log.info("square of {} is {}", number, square)
        return square
    }

    companion object : Log()
}