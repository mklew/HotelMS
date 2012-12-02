package net.mklew.hotelms.domain.money;

import org.joda.money.Money;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Represents discount in percents.
 *
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 9/27/12
 *        Time: 12:37 PM
 */
public class Discount
{
    private BigDecimal discount; // kept as percent 25.1 => 25.1%
    private static final BigDecimal HUNDRED = new BigDecimal(100);

    Discount()
    {
    }

    Discount(BigDecimal discount)
    {
        this.discount = discount;
    }

    public static Discount of(String percent)
    {
        final String value = percent.replace('%', ' ').trim();
        return new Discount(new BigDecimal(value));
    }

    /**
     *
     * @param percent value like 25 means 25%, value like 0.5 means half percent.
     * @return discount
     */
    public static Discount of(BigDecimal percent)
    {
        return new Discount(percent);
    }

    public Money applyDiscount(Money money)
    {
        final BigDecimal multiplier = HUNDRED.add(discount.negate()).divide(HUNDRED);
        return money.multipliedBy(multiplier, RoundingMode.HALF_UP);
    }

    public BigDecimal getDiscount()
    {
        return discount;
    }

    private void setDiscount(BigDecimal discount)
    {
        this.discount = discount;
    }
}
