package org.tinkoffonlineexam.task5;

import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Object[] incomingParameters = getAndCheckIncomingParameters();
        Integer[] balances = (Integer[]) incomingParameters[1];
        Set<Segment> segments = new HashSet<>();
        for (int i = 0; i < balances.length; i++) {
            for (int j = i + 1; j < balances.length; j++) {
                if (sum(balances, i, j) == 0) {
                    segments.add(new Segment(i, j));
                    for (int k = 0; k != i && k < j; k++) {
                        segments.add(new Segment(k, j));
                    }
                    for (int k = j + 1; k != i && k < balances.length; k++) {
                        segments.add(new Segment(i, k));
                    }
                }
            }
        }
        System.out.println(segments.size());
    }

    private static long sum(Integer[] integers, int leftIndex, int rightIndex) {
        long result = 0;
        for (int i = leftIndex; i <= rightIndex; i++) {
            result += integers[i];
        }
        return result;
    }

    private static Object[] getAndCheckIncomingParameters() {
        Object[] result = new Object[2];
        Scanner scanner = new Scanner(System.in);
        result[0] = getAndCheckNumberOfDays(scanner);
        result[1] = getAndCheckBalances(scanner, (Integer) result[0]);
        return result;
    }

    private static Integer[] getAndCheckBalances(final Scanner scanner, final int numberOfDays) {
        Integer[] balances = new Integer[numberOfDays];
        String line = scanner.nextLine();
        String[] strings = line.split(" ");
        int counter = 0;
        for (int i = 0; i < strings.length; i++) {
            if (!"".equals(strings[i])) {
                balances[counter] = Integer.parseInt(strings[i]);
                counter++;
            }
            if (counter == numberOfDays) {
                break;
            }
        }
        for (Integer balance : balances) {
            if (1_000_000_000 < balance || balance < -1_000_000_000) {
                throw new NumberFormatException();
            }
        }
        return balances;
    }

    private static int getAndCheckNumberOfDays(final Scanner scanner) {
        String s = scanner.nextLine();
        int numberOfDays = Integer.parseInt(s);
        if (numberOfDays > 200000 || numberOfDays < 1) {
            throw new NumberFormatException();
        }
        return numberOfDays;
    }

    private static class Segment {
        Integer left;
        Integer right;

        public Segment(Integer left, Integer right) {
            this.left = left;
            this.right = right;
        }

        public Integer getLeft() {
            return left;
        }

        public Integer getRight() {
            return right;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Segment segment = (Segment) o;
            if (!Objects.equals(left, segment.left)) return false;
            return Objects.equals(right, segment.right);
        }

        @Override
        public int hashCode() {
            int result = left != null ? left.hashCode() : 0;
            result = 31 * result + (right != null ? right.hashCode() : 0);
            return result;
        }
    }
}
