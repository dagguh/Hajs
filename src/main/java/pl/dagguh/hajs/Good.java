package pl.dagguh.hajs;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * User: Dagguh
 * Date: 22.05.13
 * Time: 00:42
 */
@EqualsAndHashCode
@ToString
public class Good {
    private BigDecimal price;
    private Collection<Person> buyers;
    private Collection<Person> consumers;

    public Good(BigDecimal price, Collection<Person> buyers, Collection<Person> consumers) {
        this.price = price;
        this.buyers = buyers;
        this.consumers = consumers;
    }

    public BigDecimal getDebt(Person debtor, Person creditor) {
        if (!consumers.contains(debtor)) {
            return BigDecimal.ZERO;
        }
        if (!buyers.contains(creditor)) {
            return BigDecimal.ZERO;
        }
        int buyerCount = buyers.size();
        int consumerCount = consumers.size();
        BigDecimal pricePerBuyer = divide(price, buyerCount);
        return divide(pricePerBuyer, consumerCount);
    }

    private BigDecimal divide(BigDecimal numerator, int divisor) {
        BigDecimal decimalDivisor = BigDecimal.valueOf(divisor);
        return numerator.divide(decimalDivisor);
    }

}
