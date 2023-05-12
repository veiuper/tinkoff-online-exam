package org.tinkoffonlineexam.task3;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Object[] incomingParameters = getAndCheckIncomingParameters();
        int numberOfCharacters = (int) incomingParameters[0];
        String string = (String) incomingParameters[1];
        boolean symbolAFound = false;
        boolean symbolBFound = false;
        boolean symbolCFound = false;
        boolean symbolDFound = false;
        int minLength = Integer.MAX_VALUE;
        int lastIndexA = -1;
        int lastIndexB = -1;
        int lastIndexC = -1;
        int lastIndexD = -1;
        for (int i = 0; i < numberOfCharacters; i++) {
            if (string.charAt(i) == 'a') {
                symbolAFound = true;
                lastIndexA = i;
            } else if (string.charAt(i) == 'b') {
                symbolBFound = true;
                lastIndexB = i;
            } else if (string.charAt(i) == 'c') {
                symbolCFound = true;
                lastIndexC = i;
            } else if (string.charAt(i) == 'd') {
                symbolDFound = true;
                lastIndexD = i;
            }
            if (symbolAFound && symbolBFound && symbolCFound && symbolDFound) {
                int minIndex = Math.min(Math.min(Math.min(lastIndexA, lastIndexB), lastIndexC), lastIndexD);
                int maxIndex = Math.max(Math.max(Math.max(lastIndexA, lastIndexB), lastIndexC), lastIndexD);
                minLength = Math.min(minLength, maxIndex - minIndex + 1);
                if (minIndex == lastIndexA) {
                    symbolAFound = false;
                } else if (minIndex == lastIndexB) {
                    symbolBFound = false;
                } else if (minIndex == lastIndexC) {
                    symbolCFound = false;
                } else {
                    symbolDFound = false;
                }
            }
        }
        if (minLength == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(minLength);
        }
    }

    private static Object[] getAndCheckIncomingParameters() {
        Object[] result = new Object[2];
        Scanner scanner = new Scanner(System.in);
        result[0] = getAndCheckNumberOfCharacters(scanner);
        result[1] = getAndCheckString(scanner);
        return result;
    }

    private static int getAndCheckNumberOfCharacters(final Scanner scanner) {
        String nextLine = scanner.nextLine();
        int numberOfCharacters = Integer.parseInt(nextLine);
        if (numberOfCharacters > 200000 || numberOfCharacters < 1) {
            throw new NumberFormatException();
        }
        return numberOfCharacters;
    }

    private static String getAndCheckString(final Scanner scanner) {
        return scanner.nextLine();
    }
}