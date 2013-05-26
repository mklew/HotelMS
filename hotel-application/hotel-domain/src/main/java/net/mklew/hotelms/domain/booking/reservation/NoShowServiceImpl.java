package net.mklew.hotelms.domain.booking.reservation;

import net.mklew.hotelms.domain.booking.reservation.NoShowService;
import net.mklew.hotelms.domain.booking.reservation.Reservation;

/**
 *
 * Place for logic related to marking reservation as no show. Current logic just changes status to no show.
 * Real implementation could trigger events to send emails. Charge reservation owner for no show and perform
 * other actions.
 *
 * @author Marek Lewandowski
 * @since 5/26/13
 */
public class NoShowServiceImpl  implements NoShowService
{
    @Override
    public void markAsNoShow(Reservation reservation)
    {
        reservation.noShow();
    }
}
