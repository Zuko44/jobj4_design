package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public class PrintUsage {
    public static void main(String[] args) {
        File file = new File("data/print.txt");
        try (PrintStream stream = new PrintStream(file)) {
            /**try (PrintStream stream = new PrintStream(new FileOutputStream("data/print.txt"))) {
             stream.println("Из PrintStream в FileOutputStream");
             stream.write("Новая строка".getBytes());
             */
            stream.println("Из PrintStream в FileOutputStream");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * public static void main(String[] args) {
     *         try (PrintStream stream = new PrintStream(new FileOutputStream("data/print.txt"));
     *              PrintWriter writer = new PrintWriter("data/write.txt")) {
     *             stream.println("Из PrintStream в FileOutputStream");
     *             stream.write("Новая строка".getBytes());
     *             writer.println("Новое сообщение");
     *         } catch (IOException e) {
     *             e.printStackTrace();
     *         }
     *     }
     */
}
