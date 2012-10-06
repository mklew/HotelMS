package net.mklew.hotelms.domain.booking;

import static org.fest.assertions.Assertions.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 9/20/12
 * time 11:04 PM
 */
public class IdTest
{
    private final static String SAMPLE_ID = "12345678";
    private final static int SEQUENCE = 4;
    private final static String DELIMETER = ".";

    private Id objectUnderTest;

    @BeforeMethod
    private void before() throws Exception
    {
        objectUnderTest = new Id(SAMPLE_ID);
    }

    @Test
    public void should_return_string_id() throws Exception
    {
        // given

        // when
        String result = objectUnderTest.getId();
        // then
        assertThat(result).isEqualTo(SAMPLE_ID);
    }

    @Test
    public void should_be_able_to_concat_id_with_seq() throws Exception
    {
        // given
        Id seqId = new Id(new Integer(SEQUENCE).toString());
        // when
        String concatedId = objectUnderTest.concat(seqId).getId();
        // then
        assertThat(concatedId).isEqualTo(SAMPLE_ID + DELIMETER + SEQUENCE);
    }
}
