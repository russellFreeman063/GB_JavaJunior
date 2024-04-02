package ru.geekbrains.junior.homework1.task1;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14);

        double average = numbers.stream()
                .filter(number -> number % 2 == 0)
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0);

        System.out.println("Среднее значение всех четных чисел: " + average);

    }
}
