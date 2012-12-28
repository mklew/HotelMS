package net.mklew.hotelms.domain.booking.reservation;

import net.mklew.hotelms.domain.room.RoomRepository;

/**
 * Implements booking business logic. What's below is very simple business logic with no overbooking taken into account
 *
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 12/25/12
 *        time 4:04 PM
 */
public class BookingService
{
    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;

    public BookingService(RoomRepository roomRepository, ReservationRepository reservationRepository)
    {
        this.roomRepository = roomRepository;
        this.reservationRepository = reservationRepository;
    }

    public boolean isRoomAvailable(Reservation reservation)
    {
        return roomRepository.isRoomAvailableBetweenDates(reservation.getRoom(), reservation.getCheckIn(),
                reservation.getCheckOut());
    }

    public void bookReservation(Reservation reservation) throws RoomIsUnavailableException
    {
        if (isRoomAvailable(reservation))
        {
            reservation.reserve();
            reservationRepository.bookNewReservation(reservation);
        }
        else
        {
            throw new RoomIsUnavailableException(reservation.getRoom().fullRoomName(), reservation.getCheckIn(),
                    reservation.getCheckOut());
        }
    }
}
