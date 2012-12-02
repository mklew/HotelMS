package net.mklew.hotelms.domain.money;


import static org.fest.assertions.Assertions.*;
import static net.mklew.hotelms.domain.booking.utils.ArrayUtils.*;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigDecimal;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 12/2/12
 *        time 12:37 PM
 */
public class DiscountTest
{
    @Test(dataProvider = "moneyAndDiscount")
    public void testApplyDiscount(Money money, Discount discount, Money expected) throws Exception
    {
        // given

        // when
        final Money discounted = discount.applyDiscount(money);

        // then
        assertThat(discounted).isEqualTo(expected);
    }

    @DataProvider
    private Object[][] moneyAndDiscount()
    {
        return $$(
                $(Money.of(CurrencyUnit.EUR, new BigDecimal(100)), Discount.of("25%"), Money.of(CurrencyUnit.EUR, new BigDecimal(75.00))),
                $(Money.of(CurrencyUnit.EUR, new BigDecimal(100)), Discount.of("25"), Money.of(CurrencyUnit.EUR, new BigDecimal(75.00))),
                $(Money.of(CurrencyUnit.EUR, new BigDecimal(150)), Discount.of("30"), Money.of(CurrencyUnit.EUR, new BigDecimal(105.00))),
                $(Money.of(CurrencyUnit.EUR, new BigDecimal(100)), Discount.of("0%"), Money.of(CurrencyUnit.EUR, new BigDecimal(100.00)))
        );
    }
}
