import org.junit.Test;

import java.time.DateTimeException;

import static org.junit.Assert.assertEquals;

public class IntegersToStringServiceTest {


    //todo check test method names
    @Test(expected = DateTimeException.class)
    public void daysOfWeekToString_dayOfWeekNotInWeekRange_throwDateTimeException () {
        DaysOfWeekToStringConverter daysOfWeekToStringConverter = new DaysOfWeekToStringConverter();
        daysOfWeekToStringConverter.convert(0);
    }

    @Test
    public void daysOfWeekToString_oneDayOfWeek_returnCorrectResult () {
        DaysOfWeekToStringConverter daysOfWeekToStringConverter = new DaysOfWeekToStringConverter();
        String dayToString = daysOfWeekToStringConverter.convert(2);
        assertEquals("2", dayToString);
    }

    @Test
    public void daysOfWeekToString_daysOfWeekUnsorted_returnSorted  () {
        DaysOfWeekToStringConverter daysOfWeekToStringConverter = new DaysOfWeekToStringConverter();
        String dayToString = daysOfWeekToStringConverter.convert(21);
        assertEquals("1-2", dayToString);
    }

    @Test
    public void daysOfWeekToString_daysOfWeekWithDuplicates_removeDuplicates  () {
        DaysOfWeekToStringConverter daysOfWeekToStringConverter = new DaysOfWeekToStringConverter();
        String dayToString = daysOfWeekToStringConverter.convert(11);
        assertEquals("1", dayToString);
    }

    @Test
    public void daysOfWeekToString_consecutiveSequencedDays_returnSeparatedWithDash () {
        DaysOfWeekToStringConverter daysOfWeekToStringConverter = new DaysOfWeekToStringConverter();
        String dayToString = daysOfWeekToStringConverter.convert(56);
        assertEquals("5-6", dayToString);
    }

    @Test
    public void daysOfWeekToString_nonConsecutiveSequencedDays_returnSeparatedWithComma () {
        DaysOfWeekToStringConverter daysOfWeekToStringConverter = new DaysOfWeekToStringConverter();
        String dayToString = daysOfWeekToStringConverter.convert(24);
        assertEquals("2,4", dayToString);
    }

    @Test
    public void daysOfWeekToString_consecutiveSequencedDaysWithDayInside_returnInsideDayReplacedWithDash () {
        DaysOfWeekToStringConverter daysOfWeekToStringConverter = new DaysOfWeekToStringConverter();
        String dayToString = daysOfWeekToStringConverter.convert(123);
        assertEquals("1-3", dayToString);
    }

    @Test
    public void daysOfWeekToString_3nonConsecutiveSequencedDays_returnSeparatedWithComma () {
        DaysOfWeekToStringConverter daysOfWeekToStringConverter = new DaysOfWeekToStringConverter();
        String dayToString = daysOfWeekToStringConverter.convert(135);
        assertEquals("1,3,5", dayToString);
    }

    @Test
    public void daysOfWeekToString_2consecutiveSequence_1nonConsecutiveSequencedDay_returnSeparatedWithDashAndComma () {
        DaysOfWeekToStringConverter daysOfWeekToStringConverter = new DaysOfWeekToStringConverter();
        String dayToString = daysOfWeekToStringConverter.convert(125);
        assertEquals("1-2,5", dayToString);
    }

    @Test
    public void daysOfWeekToString_2consecutiveSequence_2nonConsecutiveSequencedDay_returnSeparatedWithDashAndComma () {
        DaysOfWeekToStringConverter daysOfWeekToStringConverter = new DaysOfWeekToStringConverter();
        String dayToString = daysOfWeekToStringConverter.convert(12367);
        assertEquals("1-3,6-7", dayToString);
    }

    @Test
    public void daysOfWeekToString_1consecutiveSequence_2nonConsecutiveSequencedDay_returnSeparatedWithDashAndComma () {
        DaysOfWeekToStringConverter daysOfWeekToStringConverter = new DaysOfWeekToStringConverter();
        String dayToString = daysOfWeekToStringConverter.convert(134567);
        assertEquals("1,3-7", dayToString);
    }



}
