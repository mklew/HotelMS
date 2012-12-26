package net.mklew.hotelms.domain.booking.reservation;


/**
 * @author: Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since: 9/16/12
 * time 5:11 PM
 */
class GroupReservation
{
    private Reservation reservation;
    private GroupReservationId groupReservationId;

    GroupReservation()
    {
    }

    public GroupReservation(Group group, long sequence, Reservation reservation)
    {
        groupReservationId = new GroupReservationId(group, sequence);
        this.reservation = reservation;
    }

    public Group getGroup()
    {
        return groupReservationId.getGroup();
    }

    void setGroup(Group group)
    {
        this.groupReservationId.setGroup(group);
    }

    public long getSequence()
    {
        return groupReservationId.getSequence();
    }

    void setSequence(long sequence)
    {
        this.groupReservationId.setSequence(sequence);
    }

    public Reservation getReservation()
    {
        return reservation;
    }

    void setReservation(Reservation reservation)
    {
        this.reservation = reservation;
    }

    // hibernate

    private GroupReservationId getGroupReservationId()
    {
        return groupReservationId;
    }

    private void setGroupReservationId(GroupReservationId groupReservationId)
    {
        this.groupReservationId = groupReservationId;
    }
}
