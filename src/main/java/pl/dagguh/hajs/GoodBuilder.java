package pl.dagguh.hajs;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedList;

public class GoodBuilder {
    private BigDecimal price = BigDecimal.ZERO;
    private Collection<Person> buyers = new LinkedList<Person>();
    private Collection<Person> consumers = new LinkedList<Person>();

    public GoodBuilder withPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public GoodBuilder addBuyer(Person buyer) {
        buyers.add(buyer);
        return this;
    }

    public GoodBuilder addConsumer(Person consumer) {
        consumers.add(consumer);
        return this;
    }

    public Good build() {
        return new Good(price, buyers, consumers);
    }
}