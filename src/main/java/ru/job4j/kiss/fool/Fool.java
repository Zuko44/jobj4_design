package ru.job4j.kiss.fool;

import java.util.Scanner;

public class Fool {
    public static void main(String[] args) {
        System.out.println("Игра FizzBuzz.");
        var startAt = 1;
        var io = new Scanner(System.in);
        while (startAt < 100) {
            System.out.println(check(startAt));
            startAt++;
            var answer = io.nextLine();
            if (!check(startAt).equals(answer)) {
                System.out.println("Ошибка. Начинай снова.");
                startAt = 0;
            }
            startAt++;
        }
    }

    public static String check(int num) {
        String out;
        if (num % 3 == 0 && num % 5 == 0) {
            out = "FizzBuzz";
        } else if (num % 3 == 0) {
            out = "Fizz";
        } else if (num % 5 == 0) {
            out = "Buzz";
        } else {
            out = Integer.toString(num);
        }
        return out;
    }
}

