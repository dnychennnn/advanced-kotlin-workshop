package operators

import java.math.BigDecimal

enum class Currency { EUR, PLN, GBP }

data class Money(val amount: BigDecimal, val currency: Currency) {
}

operator fun Money.plus(other: Money): Money {
    require(this.currency==other.currency) { "Cannot add ${this.currency}"}
    return Money(this.amount+other.amount, currency)
}

fun main() {
    val money1 = Money(10.toBigDecimal(), Currency.EUR)
    val money2 = Money(20.toBigDecimal(), Currency.EUR)

    println(money1 + money2)
}
