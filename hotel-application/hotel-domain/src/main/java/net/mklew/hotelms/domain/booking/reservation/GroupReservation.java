package net.mklew.hotelms.domain.booking.reservation;


/**
 * @author: Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since: 9/16/12
 * time 5:11 PM
 */
class GroupReservation
{
    private Group group;
    private long sequence;
    private Reservation reservation;

    GroupReservation()
    {
    }

    public GroupReservation(Group group, long sequence, Reservation reservation)
    {
        this.group = group;
        this.sequence = sequence;
        this.reservation = reservation;
    }

    public Group getGroup()
    {
        return group;
    }

    void setGroup(Group group)
    {
        this.group = group;
    }

    public long getSequence()
    {
        return sequence;
    }

    void setSequence(long sequence)
    {
        this.sequence = sequence;
    }

    public Reservation getReservation()
    {
        return reservation;
    }

    void setReservation(Reservation reservation)
    {
        this.reservation = reservation;
    }
}
