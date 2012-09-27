package net.mklew.hotelms.domain.booking.reservation.rates.inclusion;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 9/27/12
 *        Time: 12:40 PM
 */
enum ServedUpon
{
    EVERYDAY,
    EVERYDAY_BUT_CHECK_IN,
    EVERYDAY_BUT_CHECK_OUT,
    CHECK_IN_AND_CHECK_OUT,
    ONLY_ON_CHECK_IN,
    ONLY_ON_CHECK_OUT,
    ONCE,
    EVERYDAY_BUT_CHECK_IN_AND_CHECK_OUT,
    QUANTITY_BASED
}
