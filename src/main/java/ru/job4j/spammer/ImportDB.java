package ru.job4j.spammer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImportDB {
    private Properties cfg;
    private String dump;

    public ImportDB(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
    }

    public static void main(String[] args) throws Exception {
        Properties cfg = new Properties();
        /**try (InputStream in = ImportDB.class.getClassLoader().getResourceAsStream("data/app.properties")) {
         cfg.load(in);
         }*/
        try (FileReader fileReader = new FileReader("data/app.properties")) {
            cfg.load(fileReader);
        }
        ImportDB db = new ImportDB(cfg, "./dump.txt");
        db.save(db.load());
    }

    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            for (String line = rd.readLine(); line != null; line = rd.readLine()) {
                String[] lines = line.split(";");
                if (lines.length < 2 || lines[0].length() < 1 || lines[1].length() < 1) {
                    throw new IllegalArgumentException("parameters should be 2");
                }
                users.add(new User(lines[0], lines[1]));
            }
        }
        /**for (User us : users) {
         System.out.println(us.getName());
         System.out.println(us.getEmail());
         }*/
        return users;
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(cfg.getProperty("hibernate.connection.driver_class"));
        try (Connection cnt = DriverManager.getConnection(
                cfg.getProperty("hibernate.connection.url"),
                cfg.getProperty("hibernate.connection.username"),
                cfg.getProperty("hibernate.connection.password")
        )) {
            for (User user : users) {
                try (PreparedStatement ps = cnt.prepareStatement("INSERT INTO spam(name, email) VALUES (?, ?)")) {
                    ps.setString(1, user.name);
                    ps.setString(2, user.email);
                    ps.execute();
                }
            }
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
        /**
         public String getName() {
         return name;
         }

         public String getEmail() {
         return email;
         }
         */
    }
}
