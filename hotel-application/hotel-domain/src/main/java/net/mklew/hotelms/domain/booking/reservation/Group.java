package net.mklew.hotelms.domain.booking.reservation;

import net.mklew.hotelms.domain.booking.Id;

import java.util.Collections;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 9/9/12
 *        time 8:27 PM
 */
public class Group
{
    private Id groupId;
    private Set<GroupReservation> reservationsInGroup;

    Group()
    {
    }

    public synchronized void addReservationToGroup(Reservation reservationToAdd)
    {
        long nextSequence = getNextSequenceNumber();
        GroupReservation groupReservation = new GroupReservation(this, nextSequence, reservationToAdd);
        reservationsInGroup.add(groupReservation);
    }

    private long getNextSequenceNumber()
    {
        if (reservationsInGroup.size() == 0)
        {
            return 0;
        }
        SortedSet<Long> sequences = new TreeSet<>();
        for (GroupReservation groupReservation : reservationsInGroup)
        {
            sequences.add(groupReservation.getSequence());
        }
        return sequences.last() + 1;
    }

    public Group(Id groupId)
    {
        this.groupId = groupId;
        reservationsInGroup = new TreeSet<>();
    }

    public Id getGroupId()
    {
        return groupId;
    }

    public Set<GroupReservation> getReservationsInGroup()
    {
        return Collections.unmodifiableSet(reservationsInGroup);
    }
}
