package net.mklew.hotelms.domain.booking.reservation;

import net.mklew.hotelms.domain.booking.Id;
import static org.fest.assertions.Assertions.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.fest.assertions.Fail.*;

/**
 * @author: Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since: 9/20/12
 * time 10:28 PM
 */
public class SingleReservationIdTest
{

    private static final Long id = 12345678l;
    private ReservationId objectUnderTest;

    @BeforeMethod
    public void before()
    {
        objectUnderTest = new SingleReservationId(id);
    }

    @Test
    public void single_reservation_should_have_simple_id() throws Exception
    {
        //given
        final Id expected = new Id(id.toString());
        // when
        Id result = objectUnderTest.getId();

        // then
        assertThat(result.getId()).isEqualTo(expected.getId());
    }

    @Test
    public void single_reservation_should_not_have_group() throws Exception
    {
        // given

        // when
        Group group = objectUnderTest.getGroup();

        // then
        assertThat(group).isEqualTo(Group.NO_GROUP);
    }
}
