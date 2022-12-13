package ru.job4j.jdbc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
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
    
    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            rd.lines().forEach(s -> {
                /*Alternative validation can be made via validate(s) (see below)*/
                String[] user = s.split(";", 2);
                if (user.length != 2 || user[0].isBlank() || user[1].isBlank()) {
                    throw new IllegalArgumentException();
                }
                users.add(new User(user[0], user[1]));
            /*Alternative solution with lambda:
              rd.lines()
                .validate(s)
                .map(s -> new User(s.split(";",2)[0], s.split(";",2)[1]))
                .forEach(users::add);*/
            });
        }
        return users;
    }
    
    /*Alternative solution to be used with strings:
    private static void validate(String arg) {
        if (!arg.contains(";") || arg.startsWith(";")
            || arg.endsWith(";") && arg.indexOf(";") == arg.length() - 1) {
            throw new IllegalArgumentException();
        }
    }*/
    
    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(cfg.getProperty("jdbc.driver"));
        try (Connection cnt = DriverManager.getConnection(
                cfg.getProperty("jdbc.url"),
                cfg.getProperty("jdbc.username"),
                cfg.getProperty("jdbc.password")
        )) {
            for (User user : users) {
                /*методы execute(), executeUpdate() и executeQuery() интерфейса PreparedStatement
                не принимают никаких аргументов, в отличие от одноименных методов Statement.
                Они выполняют указанный при создании объекта SQL-запрос с подставленными аргументами*/
                try (PreparedStatement ps = cnt.prepareStatement("INSERT INTO users(name, email) VALUES (?,?)")) {
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
    }
    
    public static void main(String[] args) throws Exception {
        Properties cfg = new Properties();
        try (InputStream in = ImportDB.class.getClassLoader().getResourceAsStream("app1.properties")) {
            cfg.load(in);
        }
        ImportDB db = new ImportDB(cfg, "./data/dump.txt");
        db.save(db.load());
    }
}
