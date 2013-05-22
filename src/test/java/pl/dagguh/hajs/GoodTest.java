package pl.dagguh.hajs;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;
import static org.fest.assertions.Assertions.assertThat;

/**
 * User: Dagguh
 * Date: 21.05.13
 * Time: 23:16
 */
@RunWith(MockitoJUnitRunner.class)
public class GoodTest {

    public static final BigDecimal TWO = BigDecimal.valueOf(2);
    @Mock
    private Person alice;
    @Mock
    private Person bob;
    @Mock
    private Person charlie;
    private BigDecimal pizzaPrice = BigDecimal.valueOf(17);
    private BigDecimal beerPrice = BigDecimal.valueOf(6);

    @Before
    public void setUp() {
    }

    @Test
    public void shouldOweNothing() {
        Good pizza = new GoodBuilder().build();

        assertAliceDebtToBob(pizza, ZERO);
        assertAliceDebtToCharlie(pizza, ZERO);
    }

    private void assertAliceDebtToBob(Good good, BigDecimal expectedDebt) {
        assertAliceDebt(bob, good, expectedDebt);
    }

    private void assertAliceDebt(Person creditor, Good good, BigDecimal expectedDebt) {
        BigDecimal aliceDebt = good.getDebt(alice, creditor);
        assertThat(aliceDebt).isEqualTo(expectedDebt);
    }

    private void assertAliceDebtToCharlie(Good good, BigDecimal expectedDebt) {
        assertAliceDebt(charlie, good, expectedDebt);
    }

    @Test
    public void shouldOweBobForPizza() {
        Good pizza = new GoodBuilder()
                .withPrice(pizzaPrice)
                .addBuyer(bob)
                .addConsumer(alice)
                .build();

        assertAliceDebtToBob(pizza, pizzaPrice);
        assertAliceDebtToCharlie(pizza, ZERO);
    }

    @Test
    public void shouldOweCharlieForBeer() {
        Good beer = new GoodBuilder()
                .withPrice(beerPrice)
                .addBuyer(charlie)
                .addConsumer(alice)
                .build();

        assertAliceDebtToBob(beer, ZERO);
        assertAliceDebtToCharlie(beer, beerPrice);
    }

    @Test
    public void shouldOweForHalfWithTwoBuyers() {
        Good pizza = new GoodBuilder()
                .withPrice(pizzaPrice)
                .addConsumer(alice)
                .addBuyer(bob)
                .addBuyer(charlie)
                .build();
        BigDecimal halfAPizzaPrice = pizzaPrice.divide(TWO);

        assertAliceDebtToBob(pizza, halfAPizzaPrice);
        assertAliceDebtToCharlie(pizza, halfAPizzaPrice);
    }

    @Test
    public void shouldOweForHalfWithTwoConsumers() {
        Good pizza = new GoodBuilder()
                .withPrice(pizzaPrice)
                .addConsumer(alice)
                .addConsumer(charlie)
                .addBuyer(bob)
                .build();
        BigDecimal halfAPizzaPrice = pizzaPrice.divide(TWO);

        assertAliceDebtToBob(pizza, halfAPizzaPrice);
        assertAliceDebtToCharlie(pizza, ZERO);
    }


}
