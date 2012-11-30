package net.mklew.hotelms.inhouse.web.dto;

import net.mklew.hotelms.domain.booking.reservation.rates.Rate;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 11/24/12
 * time 10:13 PM
 */
@XmlRootElement(name = "ratedto")
public class RateDto
{
    public String name;
    public MoneyDto standardRate;
    public MoneyDto upchargeExtraPerson;
    public MoneyDto upchargeExtraBed;

    public RateDto()
    {

    }

    public static RateDto createFromRate(Rate rate)
    {
        RateDto rateDto = new RateDto();
        rateDto.name = rate.getRateName();
        rateDto.standardRate = MoneyDto.fromMoney(rate.standardPrice());
        rateDto.upchargeExtraPerson = MoneyDto.fromMoney(rate.upchargeExtraPerson());
        rateDto.upchargeExtraBed = MoneyDto.fromMoney(rate.upchargeExtraBed());
        return rateDto;
    }
}
