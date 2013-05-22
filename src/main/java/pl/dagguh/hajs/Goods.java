package pl.dagguh.hajs;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedList;

/**
 * User: Dagguh
 * Date: 22.05.13
 * Time: 23:28
 */
public class Goods {

    private Collection<Good> goods = new LinkedList<Good>();

    public void add(Good good) {
        goods.add(good);
    }

    public BigDecimal getDebt(Person debtor, Person creditor) {
        BigDecimal debtSum = BigDecimal.ZERO;
        for (Good good : goods) {
            BigDecimal debt = good.getDebt(debtor, creditor);
            debtSum = debtSum.add(debt);
        }
        return debtSum;
    }
}
