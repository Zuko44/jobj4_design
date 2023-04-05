package ru.job4j.io;

import java.io.*;

public class Analysis {
    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }

    public void unavailable(String source, String target) {
        try (BufferedReader read = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(
                     new BufferedOutputStream(
                             new FileOutputStream(target)
                     ))) {
            String line;
            boolean flag = true;
            String total = "";
            while ((line = read.readLine()) != null) {
                String[] data = line.split(" ");
                /**if (flag && Integer.parseInt(data[0]) >= 400) {
                 out.print(data[1] + ";");
                 flag = false;
                 } else if (!flag && Integer.parseInt(data[0]) < 400) {
                 flag = true;
                 out.println(data[1] + ";");
                 }*/
                total = total.equals("") && Integer.parseInt(data[0]) >= 400 ? data[1] + ";" : total;
                if (Integer.parseInt(data[0]) < 400 && !total.equals("")) {
                    out.println(total + (data[1] + ";"));
                    total = "";
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
