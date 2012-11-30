package net.mklew.hotelms.inhouse.web.dto;

import org.joda.money.Money;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 11/30/12
 *        time 2:43 PM
 */
@XmlRootElement (name = "moneydto")
public class MoneyDto
{
    public String amount;
    public String currency;

    public MoneyDto()
    {

    }

    public MoneyDto(String amount, String currency)
    {
        this.amount = amount;
        this.currency = currency;
    }

    public static MoneyDto fromMoney(Money money)
    {
        return new MoneyDto(money.getAmount().toPlainString(), money.getCurrencyUnit().getCurrencyCode());
    }
}
