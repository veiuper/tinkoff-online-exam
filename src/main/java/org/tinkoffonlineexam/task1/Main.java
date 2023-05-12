package org.tinkoffonlineexam.task1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Object[] incomingParameters = getAndCheckIncomingParameters();
        List<Integer> integers = (List<Integer>) incomingParameters[0];
        boolean
                increasingSequence = true;
        boolean decreasingSequence = true;
        for (int i = 1; i < integers.size(); i++) {
            if (integers.get(i - 1) - integers.get(i) >= 0) {
                decreasingSequence = false;
            } else {
                increasingSequence = false;
            }
            if (!decreasingSequence && !increasingSequence) {
                break;
            }
        }
        System.out.println((increasingSequence || decreasingSequence) ? "YES" : "NO");
    }
    private static Object[] getAndCheckIncomingParameters() {
        Object[] result = new Object[1];
        Scanner scanner = new Scanner(System.in);
        result[0] = getAndCheckGrowth(scanner);
        return result;
    }

    private static List<Integer> getAndCheckGrowth(final Scanner scanner) {
        List<Integer> integers = new ArrayList<>();
        String s = scanner.nextLine();
        String[] strings = s.split(" ");
        int curInt;
        for (String string : strings) {
            if (!"".equals(string)) {
                curInt = Integer.parseInt(string);
                if (curInt < 0 || curInt > 300) {
                    throw new NumberFormatException();
                }
                integers.add(curInt);
            }
        }
        return integers;
    }
}
