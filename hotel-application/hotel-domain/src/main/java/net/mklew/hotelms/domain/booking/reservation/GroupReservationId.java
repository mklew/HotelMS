package net.mklew.hotelms.domain.booking.reservation;

import java.io.Serializable;

public class GroupReservationId implements Serializable
{
    private Group group;
    private Long sequence;

    public GroupReservationId(Group group, Long sequence)
    {
        this.group = group;
        this.sequence = sequence;
    }

    GroupReservationId()
    {
    }

    public Group getGroup()
    {
        return group;
    }

    public void setGroup(Group group)
    {
        this.group = group;
    }

    public Long getSequence()
    {
        return sequence;
    }

    public void setSequence(Long sequence)
    {
        this.sequence = sequence;
    }
}