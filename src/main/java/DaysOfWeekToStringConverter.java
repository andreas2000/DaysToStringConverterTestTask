import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;

public class DaysOfWeekToStringConverter {
    /**
     * Convert number(Integer) where each digit is in range 1..7 according to the day of week
     * (1 - Monday, 2 - Tuesday...7 - Sunday)
     * to the string, separating each digit with ‘,’ for non consecutive sequence days,
     * or ‘-’ for consecutive sequence days.
     * @param daysOfWeek - days of week present as integer
     * @return - converted days of week to string
     */
    public String convert(int daysOfWeek) {
        List<Integer> intDays = getDigits(daysOfWeek)
                .stream()
                .peek(this::checkDayInWeekRange)
                .sorted()
                .distinct()
                .collect(toList());

        return splitToConsecutiveSequences(intDays)
                .stream()
                .map(list -> list.size() > 1
                        ? list.get(0).toString() + "-" + list.get(list.size() - 1).toString()
                        : list.get(0).toString())
                .collect(Collectors.joining(","));
    }

    /**
     * Check whether input parameter in week range (1 - 7).
     * If given day not in week range method throws DateTimeException.
     * @param day - single day present as integer
     */
    private void checkDayInWeekRange (int day) {
        DayOfWeek.of(day);
    }

    /**
     * Convert number to digits list.
     * @param number - days of week present as integer
     * @return - list of digits.
     */
    private List<Integer> getDigits(int number) {
        List<Integer> digits = new ArrayList<>();
        collectDigits(number, digits);
        return digits;
    }

    private void collectDigits(int num, List<Integer> digits) {
        if(num / 10 > 0) {
            collectDigits(num / 10, digits);
        }
        digits.add(num % 10);
    }

    /**
     * Split list of integers to consecutive sequences
     * Examples : 123 -> 123; 1245 -> 12, 45; 1567 -> 1, 567
     * @param list - list of integers
     * @return - consecutive sequences
     */
    private List<List<Integer>> splitToConsecutiveSequences(List<Integer> list) {
        List<List<Integer>> sequences = new ArrayList<>();

        if (list.size() <= 1) {
            sequences.add(singletonList(list.get(0)));
            return sequences;
        }

        for (int i = 0; i < list.size(); i++) {
            List<Integer> sequence = new ArrayList<>();
            sequences.add(sequence);
            sequence.add(list.get(i));

            while (i + 1 < list.size() && list.get(i) + 1 == list.get(i + 1)) {
                sequence.add(list.get(i + 1));
                i = ++i;
            }
        }
        return sequences;
    }
}
