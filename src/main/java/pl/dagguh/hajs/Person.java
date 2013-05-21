package pl.dagguh.hajs;

import java.math.BigDecimal;

/**
 * User: Dagguh
 * Date: 21.05.13
 * Time: 23:59
 */
public class Person {

    private Good boughtGood;

    public BigDecimal getDebt(Person bob) {
        return bob.getBoughtPrice();
    }

    public void buy(Good good) {
        boughtGood = good;
    }

    public BigDecimal getBoughtPrice() {
        if (boughtGood == null) {
            return BigDecimal.ZERO;
        } else {
            return boughtGood.getPrice();
        }
    }
}
