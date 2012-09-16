package net.mklew.hotelms.domain.booking;

/**
 * @author: Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since: 9/16/12
 * time 5:11 PM
 */
public class GroupReservationId extends ReservationId
{
    private final Integer sequence;
    private Id id;

    public GroupReservationId(Group group, Integer sequence)
    {
        super(group);
        this.sequence = sequence;
    }

    @Override
    public Id getId()
    {
        if ( id != null )
            return id;
        id = group.getId().concat(new Id(sequence.toString()));
        return id;
    }
}
