package net.mklew.hotelms.domain.booking;

/**
 * @author: Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since: 9/16/12
 * time 4:32 PM
 */
public class SingleReservationId extends ReservationId
{
    private final Long id;

    public SingleReservationId(Long id)
    {
        super(Group.NO_GROUP);
        this.id = id;
    }

    /**
     * @inheritDoc
     * @return Id of single reservation
     */
    @Override
    public Id getId()
    {
        return new Id(id.toString());
    }
}
