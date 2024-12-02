package stage1.graduation.util;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Console {
    private static final Scanner scanner = new Scanner(System.in);

    public static void println(String message) {
        System.out.println(message);
    }

    public static void print(String message) {
        System.out.print(message);
    }

    public static void printf(String format, Object... args) {
        System.out.printf(format, args);
    }

    public static void printObjects(Object... objects) {
        for (Object o : objects) {
            System.out.println(o);
        }
        System.out.println();
    }

    public static String readString() {
        return scanner.nextLine();
    }

    public static int readInt() {
        int intNumber;
        do {
            try {
                intNumber = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("Ошибка! Введите корректное целое число: ");
                continue;
            } finally {
                scanner.nextLine();
            }
            break;
        } while (true);
        return intNumber;
    }

    public static float readFloat() {
        float floatNumber;
        do {
            try {
                floatNumber = scanner.nextFloat();
            } catch (InputMismatchException e) {
                System.out.print("Ошибка! Введите корректное вещественное число: ");
                continue;
            } finally {
                scanner.nextLine();
            }
            break;
        } while (true);
        return floatNumber;
    }

    public static void close() {
        scanner.close();
    }
}