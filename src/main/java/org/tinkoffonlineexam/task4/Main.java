package org.tinkoffonlineexam.task4;

import java.util.HashMap;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int result = 0;
        Object[] incomingParameters = getAndCheckIncomingParameters();
        Integer[] integers = (Integer[]) incomingParameters[1];
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < integers.length; i++) {
            if (hashMap.containsKey(integers[i])) {
                hashMap.put(integers[i], hashMap.get(integers[i]) + 1);
            } else {
                hashMap.put(integers[i], 1);
            }
            long tmpCount = hashMap.values().stream().filter(o -> o == 1).count();
            if (tmpCount == hashMap.size()) {
                result = i + 1;
                continue;
            }
            int minValue = hashMap.values().stream().min((o1, o2) -> o1 - o2).get();
            if (minValue == 1) {
                Optional<Integer> any = hashMap.values().stream().filter(o -> o != 1).findAny();
                if (any.isPresent()) {
                    tmpCount = hashMap.values().stream().filter(o -> o != 1 && o == any.get()).count();
                    if (tmpCount == hashMap.size() - 1) {
                        result = i + 1;
                        continue;
                    }
                }
            }
            int maxValue = hashMap.values().stream().max((o1, o2) -> o1 - o2).get();
            if (maxValue != 1) {
                tmpCount = hashMap.values().stream().filter(o -> o == maxValue - 1).count();
                if (tmpCount == hashMap.size() - 1) {
                    result = i + 1;
                }
            }
        }
        System.out.println(result);
    }

    private static Object[] getAndCheckIncomingParameters() {
        Object[] result = new Object[2];
        Scanner scanner = new Scanner(System.in);
        result[0] = getAndCheckAreaLength(scanner);
        result[1] = getAndCheckNumbers(scanner, (Integer) result[0]);
        return result;
    }

    private static Integer[] getAndCheckNumbers(final Scanner scanner, final int areaLength) {
        Integer[] integers = new Integer[areaLength];
        String line = scanner.nextLine();
        String[] strings = line.split(" ");
        int counter = 0;
        for (int i = 0; i < strings.length; i++) {
            if (!"".equals(strings[i])) {
                integers[counter] = Integer.parseInt(strings[i]);
                counter++;
            }
            if (counter == areaLength) {
                break;
            }
        }
        for (Integer integer : integers) {
            if (integer > 20000 || integer < 1) {
                throw new NumberFormatException();
            }
        }
        return integers;
    }

    private static int getAndCheckAreaLength(final Scanner scanner) {
        String s = scanner.nextLine();
        int areaLength = Integer.parseInt(s);
        if (areaLength > 200000 || areaLength < 2) {
            throw new NumberFormatException();
        }
        return areaLength;
    }
}