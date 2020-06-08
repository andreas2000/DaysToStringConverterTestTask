import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;

public class DaysOfWeekToStringConverter {

    public String convert(int daysOfWeek) {
        List<Integer> daysDigits = getDigits(daysOfWeek);
        List<Integer> intDays = daysDigits.stream()
                .peek(this::checkDayInWeekRange)
                .sorted()
                .distinct()
                .collect(toList());

        List<List<Integer>> consecutive = consecutive(intDays);
        List<String> collect = consecutive.stream()
                .map(list -> list.size() > 1
                        ? list.get(0).toString() + "-" + list.get(list.size() - 1).toString()
                        : list.get(0).toString())
                .collect(toList());

        return String.join(",", collect);

    }

    private void checkDayInWeekRange (int day) {
        DayOfWeek.of(day);
    }

    private List<Integer> getDigits(int num) {
        List<Integer> digits = new ArrayList<>();
        collectDigits(num, digits);
        return digits;
    }

    private void collectDigits(int num, List<Integer> digits) {
        if(num / 10 > 0) {
            collectDigits(num / 10, digits);
        }
        digits.add(num % 10);
    }

    private List<List<Integer>> consecutive(List<Integer> list) {
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
