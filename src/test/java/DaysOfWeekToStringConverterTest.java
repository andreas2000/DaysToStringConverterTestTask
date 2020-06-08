import org.junit.Test;

import java.time.DateTimeException;

import static org.junit.Assert.assertEquals;

public class DaysOfWeekToStringConverterTest {

    @Test(expected = DateTimeException.class)
    public void convert_dayNotInWeekRange_throwDateTimeException () {
        DaysOfWeekToStringConverter converter = new DaysOfWeekToStringConverter();
        converter.convert(0);
    }

    @Test
    public void convert_oneDayOfWeek_returnCorrectResult () {
        DaysOfWeekToStringConverter converter = new DaysOfWeekToStringConverter();
        assertEquals("2", converter.convert(2));
    }

    @Test
    public void convert_daysOfWeekUnsorted_returnSorted  () {
        DaysOfWeekToStringConverter converter = new DaysOfWeekToStringConverter();
        assertEquals("1-2", converter.convert(21));
    }

    @Test
    public void convert_daysOfWeekWithDuplicates_removeDuplicates () {
        DaysOfWeekToStringConverter converter = new DaysOfWeekToStringConverter();
        assertEquals("1", converter.convert(11));
    }

    @Test
    public void convert_consecutiveSequence_returnSeparatedWithDash () {
        DaysOfWeekToStringConverter converter = new DaysOfWeekToStringConverter();
        assertEquals("5-6", converter.convert(56));
    }

    @Test
    public void convert_nonConsecutiveSequence_returnSeparatedWithComma () {
        DaysOfWeekToStringConverter converter = new DaysOfWeekToStringConverter();
        assertEquals("2,4", converter.convert(24));
    }

    @Test
    public void convert_consecutiveSequenceHasMoreThenTwoEntries_returnInsideDayReplacedWithDash () {
        DaysOfWeekToStringConverter converter = new DaysOfWeekToStringConverter();
        assertEquals("1-3", converter.convert(123));
    }

    @Test
    public void convert_threeNonConsecutiveSequence_returnSeparatedWithComma () {
        DaysOfWeekToStringConverter converter = new DaysOfWeekToStringConverter();
        assertEquals("1,3,5", converter.convert(135));
    }

    @Test
    public void convert_twoConsecutiveSequenceAndOneNonConsecutiveSequence_returnSeparatedWithDashAndComma () {
        DaysOfWeekToStringConverter converter = new DaysOfWeekToStringConverter();
        assertEquals("1-2,5", converter.convert(125));
    }

    @Test
    public void convert_twoConsecutiveSequenceAndTwoNonConsecutiveSequence_returnSeparatedWithDashAndComma () {
        DaysOfWeekToStringConverter converter = new DaysOfWeekToStringConverter();
        assertEquals("1-3,6-7", converter.convert(12367));
    }

    @Test
    public void convert_oneConsecutiveSequenceAndTwoNonConsecutiveSequence_returnSeparatedWithDashAndComma () {
        DaysOfWeekToStringConverter converter = new DaysOfWeekToStringConverter();
        assertEquals("1,3-7", converter.convert(134567));
    }

}
