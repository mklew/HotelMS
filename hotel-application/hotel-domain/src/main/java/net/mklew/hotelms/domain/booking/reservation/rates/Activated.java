package net.mklew.hotelms.domain.booking.reservation.rates;

/**
 * Activated is interface which allows to ask object if it is active for example
 * Package might be defined but is not yet activated/published so cannot be purchased
 * or Season offer is defined but also not activated.
 *
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 11/4/12
 *        Time: 2:18 PM
 */
public interface Activated
{
    boolean isActive();
}
