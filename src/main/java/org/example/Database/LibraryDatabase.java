package org.example.Database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;

public class LibraryDatabase {
    private static final Logger logger = LogManager.getLogger(org.example.Database.LibraryDatabase.class);

    private static final String URL = "jdbc:oracle:thin:@//ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl";
    private static final String LOGIN = "nkulakow";
    private static final String HASHEDPASSWORD = "bmt1bGFrb3c=";
    private static Connection CONNECTION;

    private static ArrayList<UserInDB> searchUsers(final String query_str) {
        ArrayList<UserInDB> users = new ArrayList<>();
        try {
            CONNECTION = DriverManager.getConnection(URL, LOGIN, getPassword());
            logger.info("Connected to database.");
            Statement query = CONNECTION.createStatement();
            ResultSet result = query.executeQuery(query_str);
            while (result.next()) {
                int id = result.getInt(DatabaseConstants.USER_ID);
                String name = result.getString(DatabaseConstants.USER_NAME);
                String surname = result.getString(DatabaseConstants.USER_SURNAME);
                String mail = result.getString(DatabaseConstants.USER_MAIL);
                int booksNumber = result.getInt(DatabaseConstants.USER_BOOKS_NR);
                users.add(new UserInDB(id, name, surname, mail, booksNumber));
            }
            logger.info("Executed query in searchUsers method.");
        } catch (java.sql.SQLException e) {
            logger.error("Could not execute query in searchUsers method.");
        }
        return users;
    }

    public static ArrayList<String> getUsers(final String query_str) {
        ArrayList<String> result = new ArrayList<>();
        var users = searchUsers(query_str);
        for(var user : users) {
            String respresentation = user.getId() + " " + user.getName() + " " + user.getSurname()+" "+user.getMail()+" "+user.getBooksNumber();
            result.add(respresentation);
        }
        return result;
    }

    public static void addUser(String id, final String name, final String surname, final String mail) {
        String query_str = "insert into pap_users values (" + id + ",'" + name + "','" + surname + "','" + mail+ "','" + 0  + "')";

        try {
            CONNECTION = DriverManager.getConnection(URL, LOGIN, getPassword());
            logger.info("Connected to database.");
            Statement query = CONNECTION.createStatement();
            query.executeUpdate(query_str);
            logger.info("Executed update in addUser method.");
        } catch (java.sql.SQLException e) {
            logger.error("Could not execute query in addUser method.");
        }
    }

    private static String getPassword()
    {
        byte[] bytePasswd = Base64.getDecoder().decode(HASHEDPASSWORD);
        return new String(bytePasswd);
    }
}

class DatabaseConstants {
    public static final int TABLE_USERS = 0;
    public static final int TABLE_BOOKS = 1;
    public static final int TABLE_LOANS = 2;
    public static final String USER_ID = "user_id";
    public static final String USER_NAME = "first_name";
    public static final String USER_SURNAME = "last_name";
    public static final String USER_MAIL = "mail";
    public static final String USER_BOOKS_NR = "books_nr";
}
