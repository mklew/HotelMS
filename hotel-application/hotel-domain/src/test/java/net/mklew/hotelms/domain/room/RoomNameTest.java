package net.mklew.hotelms.domain.room;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.fest.assertions.Assertions.assertThat;


/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 12/25/12
 *        time 7:43 PM
 */
public class RoomNameTest
{
    @Test
    public void prefix_should_be_hash()
    {
        assertThat(RoomName.DELIMETER).isEqualTo("#");
    }

    @Test(dataProvider = "getNames")
    public void should_remove_prefix_from_room_name(String displayedName, RoomName expectedRoomName)
    {
        // given

        // when
        final RoomName name = RoomName.getNameWithoutPrefix(displayedName);

        // then
        assertThat(name).isEqualTo(expectedRoomName);
    }

    @DataProvider
    private Object[][] getNames()
    {
        return new Object[][]{
                {"L#101", new RoomName("101")},
                {"ABC#1005", new RoomName("1005")},
                {"#101", new RoomName("101")},
                {"102", new RoomName("102")}
        };
    }
}
