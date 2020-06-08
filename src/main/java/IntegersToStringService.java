import java.lang.reflect.Array;
import java.time.DayOfWeek;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;

//todo rename?
public class IntegersToStringService {

    public String daysOfWeekToString (int daysOfWeek) {

        Integer[] daysDigits = getDigits(daysOfWeek);
        List<Integer> intDays = Arrays.stream(daysDigits)
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

//
//        List<String> stringDays = Arrays.stream(daysDigits)
//                .peek(this::checkDayInWeekRange)
//                .sorted()
//                .distinct()
//                .map(Object::toString)
//                .collect(toList());
//
        return String.join(",", collect);

    }

    private void checkDayInWeekRange (int day) {
        DayOfWeek.of(day);
    }

    private Integer[] getDigits(int num) {
        List<Integer> digits = new ArrayList<>();
        collectDigits(num, digits);
        return digits.toArray(new Integer[]{});
    }

    private void collectDigits(int num, List<Integer> digits) {
        if(num / 10 > 0) {
            collectDigits(num / 10, digits);
        }
        digits.add(num % 10);
    }

    public static int consecutive(int[] array) {
        if (array.length <= 1) {
            return array.length;
        }
        int maxRun = 0;
        for (int i = 1; i < array.length; i++) {
            int thisRun = 1;
            while (i < array.length && array[i - 1] + 1 == array[i]) {
                thisRun++;
                i++;
            }
            if (maxRun < thisRun) { // checking geater occurance
                maxRun = thisRun;
            }
        }
        return maxRun;
    }


//    public List<List<Integer>> consecutive(List<Integer> list) {
//        List<List<Integer>> sequences = new ArrayList<>();
//
//        if (list.size() <= 1) {
//            sequences.add(singletonList(list.get(0)));
//            return sequences;
//        }
//
//        for (int i = 1; i < list.size(); i++) {
//            List<Integer> sequence = new ArrayList<>();
//            sequences.add(sequence);
//            sequence.add(list.get(i - 1));
//            int j = i;
//            while (j < list.size() && list.get(j - 1) + 1 == list.get(j)) {
//                sequence.add(list.get(j));
//                j = ++j;
//            }
//        }
//        return sequences;
//    }

    public List<List<Integer>> consecutive(List<Integer> list) {
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

//    public static int consecutive1(List<Integer> list) {
//        List<List<Integer>> sequences = new ArrayList<>();
//
//        int maxRun = 0;
//        for (int i = 1; i < list.size(); i++) {
//            List<Integer> sequence = new ArrayList<>();
//            sequences.add(sequence);
//            int thisRun = 1;
//            sequence.add(list.get(i - 1));
//            while (i < list.size() && list.get(i - 1) + 1 == list.get(i)) {
//                sequence.add(list.get(i));
//                thisRun++;
//                i++;
//            }
//            if (maxRun < thisRun) { // checking geater occurance
//                maxRun = thisRun;
//            }
//        }
//        return maxRun;
//    }
}
