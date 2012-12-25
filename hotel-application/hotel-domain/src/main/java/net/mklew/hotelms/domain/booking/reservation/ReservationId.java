package net.mklew.hotelms.domain.booking.reservation;


import net.mklew.hotelms.domain.booking.Id;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Identifies reservation
 *
 * @author: Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since: 9/9/12
 * time 7:49 PM
 */
public abstract class ReservationId
{
    protected final Group group;

    public ReservationId(Group group)
    {
        this.group = group;
    }

    /**
     * Returns Id of reservation. This is either Id of single reservation or Id of group reservation.
     *
     * @return Id of reservation
     */
    public abstract Id getId();

    /**
     * Returns group
     *
     * @return Group if reservation belongs to group. Special case NoGroup otherwise
     */
    public Group getGroup()
    {
        return group;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (obj == this)
        {
            return true;
        }
        if (obj.getClass() != getClass())
        {
            return false;
        }
        ReservationId rhs = (ReservationId) obj;
        return new EqualsBuilder()
                .append(group, rhs.group)
                .append(getId(), rhs.getId())
                .isEquals();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder(13, 47)
                .append(group)
                .append(getId())
                .toHashCode();
    }

}
