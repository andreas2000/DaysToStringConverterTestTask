import org.junit.Test;

import java.time.DateTimeException;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class IntegersToStringServiceTest {


    //todo check test method names
    @Test(expected = DateTimeException.class)
    public void daysOfWeekToString_dayOfWeekNotInWeekRange_throwDateTimeException () {
        IntegersToStringService integersToStringService = new IntegersToStringService();
        integersToStringService.daysOfWeekToString(0);
    }

    @Test
    public void daysOfWeekToString_oneDayOfWeek_returnCorrectResult () {
        IntegersToStringService integersToStringService = new IntegersToStringService();
        String dayToString = integersToStringService.daysOfWeekToString(2);
        assertEquals("2", dayToString);
    }

    @Test
    public void daysOfWeekToString_daysOfWeekUnsorted_returnSorted  () {
        IntegersToStringService integersToStringService = new IntegersToStringService();
        String dayToString = integersToStringService.daysOfWeekToString(21);
        assertEquals("1-2", dayToString);
    }

    @Test
    public void daysOfWeekToString_daysOfWeekWithDuplicates_removeDuplicates  () {
        IntegersToStringService integersToStringService = new IntegersToStringService();
        String dayToString = integersToStringService.daysOfWeekToString(11);
        assertEquals("1", dayToString);
    }

    @Test
    public void daysOfWeekToString_consecutiveSequencedDays_returnSeparatedWithDash () {
        IntegersToStringService integersToStringService = new IntegersToStringService();
        String dayToString = integersToStringService.daysOfWeekToString(56);
        assertEquals("5-6", dayToString);
    }

    @Test
    public void daysOfWeekToString_nonConsecutiveSequencedDays_returnSeparatedWithComma () {
        IntegersToStringService integersToStringService = new IntegersToStringService();
        String dayToString = integersToStringService.daysOfWeekToString(24);
        assertEquals("2,4", dayToString);
    }

    @Test
    public void daysOfWeekToString_consecutiveSequencedDaysWithDayInside_returnInsideDayReplacedWithDash () {
        IntegersToStringService integersToStringService = new IntegersToStringService();
        String dayToString = integersToStringService.daysOfWeekToString(123);
        assertEquals("1-3", dayToString);
    }

    @Test
    public void daysOfWeekToString_3nonConsecutiveSequencedDays_returnSeparatedWithComma () {
        IntegersToStringService integersToStringService = new IntegersToStringService();
        String dayToString = integersToStringService.daysOfWeekToString(135);
        assertEquals("1,3,5", dayToString);
    }

    @Test
    public void daysOfWeekToString_2consecutiveSequence_1nonConsecutiveSequencedDay_returnSeparatedWithDashAndComma () {
        IntegersToStringService integersToStringService = new IntegersToStringService();
        String dayToString = integersToStringService.daysOfWeekToString(125);
        assertEquals("1-2,5", dayToString);
    }

    @Test
    public void daysOfWeekToString_2consecutiveSequence_2nonConsecutiveSequencedDay_returnSeparatedWithDashAndComma () {
        IntegersToStringService integersToStringService = new IntegersToStringService();
        String dayToString = integersToStringService.daysOfWeekToString(12367);
        assertEquals("1-3,6-7", dayToString);
    }

    @Test
    public void daysOfWeekToString_1consecutiveSequence_2nonConsecutiveSequencedDay_returnSeparatedWithDashAndComma () {
        IntegersToStringService integersToStringService = new IntegersToStringService();
        String dayToString = integersToStringService.daysOfWeekToString(134567);
        assertEquals("1,3-7", dayToString);
    }



}
