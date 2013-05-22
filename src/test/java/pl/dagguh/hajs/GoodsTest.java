package pl.dagguh.hajs;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;
import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * User: Dagguh
 * Date: 22.05.13
 * Time: 23:29
 */
@RunWith(MockitoJUnitRunner.class)
public class GoodsTest {

    private static final BigDecimal KEBAB_PRICE = BigDecimal.valueOf(16);
    private static final BigDecimal WATER_PRICE = BigDecimal.valueOf(3);
    private static final BigDecimal PEPSI_PRICE = BigDecimal.valueOf(5);
    @Mock
    private Person alice;
    @Mock
    private Person bob;
    @Mock
    private Person charlie;
    @Mock
    private Good kebab;
    @Mock
    private Good pepsi;
    @Mock
    private Good water;
    private Goods goods;

    @Before
    public void setUp() {
        stubDefaultDebt(kebab);
        stubDefaultDebt(water);
        stubDefaultDebt(pepsi);
        goods = new Goods();
        goods.add(kebab);
        goods.add(water);
        goods.add(pepsi);
    }

    private void stubDefaultDebt(Good good) {
        when(good.getDebt(anyPerson(), anyPerson())).thenReturn(ZERO);
    }

    private Person anyPerson() {
        return any(Person.class);
    }

    @Test
    public void onlyAliceShouldOweBob() {
        when(kebab.getDebt(alice, bob)).thenReturn(KEBAB_PRICE);
        when(water.getDebt(alice, charlie)).thenReturn(WATER_PRICE);
        when(pepsi.getDebt(alice, bob)).thenReturn(PEPSI_PRICE);

        BigDecimal aliceDebtToBob = goods.getDebt(alice, bob);
        BigDecimal charlieDebtToBob = goods.getDebt(charlie, bob);

        assertThat(aliceDebtToBob).isEqualTo(KEBAB_PRICE.add(PEPSI_PRICE));
        assertThat(charlieDebtToBob).isEqualTo(ZERO);
    }

}
