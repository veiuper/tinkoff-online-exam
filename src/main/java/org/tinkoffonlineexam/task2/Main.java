package org.tinkoffonlineexam.task2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        List<Integer> incomingParameters = getAndCheckIncomingParameters();
        int n = incomingParameters.get(0);
        int m = incomingParameters.get(1);
        int k = incomingParameters.get(2);
        System.out.println(n / m * k + n % m);
    }
    private static List<Integer> getAndCheckIncomingParameters() {
        List<Integer> integers = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        String[] strings = s.split(" ");
        int curInt;
        for (String string : strings) {
            if (!"".equals(string)) {
                curInt = Integer.parseInt(string);
                integers.add(curInt);
            }
        }
        for (Integer integer : integers) {
            if (10000 < integer || integer < 1) {
                throw new NumberFormatException();
            }
        }
        if (integers.get(2) > integers.get(0) || integers.get(2) > integers.get(1)) {
            throw new NumberFormatException();
        }
        if (integers.get(1) > integers.get(0)) {
            throw new NumberFormatException();
        }
        return integers;
    }
}