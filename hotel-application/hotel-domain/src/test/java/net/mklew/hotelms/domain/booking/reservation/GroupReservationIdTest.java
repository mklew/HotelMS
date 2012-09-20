package net.mklew.hotelms.domain.booking.reservation;

import net.mklew.hotelms.domain.booking.Id;
import static org.fest.assertions.Assertions.*;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.sound.midi.Sequence;

import static org.fest.assertions.Fail.*;
/**
 * @author: Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since: 9/20/12
 * time 10:29 PM
 */
public class GroupReservationIdTest
{
    @Mock
    private Group group;

    private Id id;

    private final static String idStr = "12345678";

    private final static int SEQUENCE = 1;

    private GroupReservationId objectUnderTest;

    @BeforeMethod
    public void before()
    {
        MockitoAnnotations.initMocks(this);
        id = new Id(idStr);
    }

    @Test
    public void group_reservation_should_have_complex_id() throws Exception
    {
        // given
        String expectedId = idStr + '.' + SEQUENCE;

        when(group.getId()).thenReturn(id);

        // when

        objectUnderTest = new GroupReservationId(group, SEQUENCE);

        Id result = objectUnderTest.getId();

        // then
        assertThat(result.getId()).isEqualTo(expectedId);
    }

    @Test
    public void group_reservation_should_belong_to_group() throws Exception
    {
        // given

        // when
        objectUnderTest = new GroupReservationId(group, SEQUENCE);
        final Group group = objectUnderTest.getGroup();

        // then

        assertThat(group).isNotEqualTo(Group.NO_GROUP);

    }

}
