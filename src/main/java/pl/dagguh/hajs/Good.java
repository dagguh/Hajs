package pl.dagguh.hajs;

import java.math.BigDecimal;

/**
 * User: Dagguh
 * Date: 22.05.13
 * Time: 00:42
 */
public class Good {
    private BigDecimal price;

    public Good(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

}
