package pl.dagguh.hajs;

import org.fest.assertions.BigDecimalAssert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * User: Dagguh
 * Date: 21.05.13
 * Time: 23:16
 */
public class HajsTest {

    private Person alice;
    private Person bob;

    @Before
    public void setUp() {
        alice = new Person();
        bob = new Person();
    }

    private Good mockGood(BigDecimal price) {
        Good good = mock(Good.class);
        when(good.getPrice()).thenReturn(price);
        return good;
    }

    @Test
    public void shouldOweNothing() {
        BigDecimal aliceDebt = getAliceDebt();

        assertAliceDebt(BigDecimal.ZERO, aliceDebt);
    }

    private BigDecimal getAliceDebt() {
        return alice.getDebt(bob);
    }

    private BigDecimalAssert assertAliceDebt(BigDecimal pizzaPrice, BigDecimal aliceDebt) {
        return assertThat(aliceDebt).isEqualTo(pizzaPrice);
    }

    @Test
    public void shouldOweForPizza() {
        BigDecimal price = BigDecimal.valueOf(17);
        Good pizza = mockGood(price);
        bob.buy(pizza);

        BigDecimal aliceDebt = getAliceDebt();

        assertAliceDebt(price, aliceDebt);
    }

    @Test
    public void shouldOweForPepsi() {
        BigDecimal price = BigDecimal.valueOf(6);
        Good pepsi = mockGood(price);
        bob.buy(pepsi);

        BigDecimal aliceDebt = getAliceDebt();

        assertAliceDebt(price, aliceDebt);
    }


}
