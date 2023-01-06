package org.example.Database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.LibraryContextPackage.*;

import java.io.*;
import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;

public class LibraryDatabase {
    private static final Logger logger = LogManager.getLogger(org.example.Database.LibraryDatabase.class);

    private static final String URL = "jdbc:oracle:thin:@//ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl";
    private static String LOGIN;
    private static String HASHEDPASSWORD;
    private static Connection CONNECTION;

    public static void initLoginInfo() throws IOException {
        try {
            FileInputStream fileStream = new FileInputStream("src/main/resources/info.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fileStream));
            LOGIN = br.readLine();
            HASHEDPASSWORD = br.readLine();
            fileStream.close();
        }
        catch (IOException e){
            logger.error("Could not get login to database info in initLoginInfo method " + e.getMessage());
            throw e;
        }
    }

    private static ArrayList<UserInDB> searchUsers(final String query_str) throws SQLException {
        ArrayList<UserInDB> users = new ArrayList<>();
        try {
            CONNECTION = DriverManager.getConnection(URL, LOGIN, getPassword());
            logger.info("Connected to database.");
            Statement query = CONNECTION.createStatement();
            ResultSet result = query.executeQuery(query_str);
            while (result.next()) {
                int id = result.getInt(DatabaseConstants.UserConstants.USER_ID);
                String name = result.getString(DatabaseConstants.UserConstants.USER_NAME);
                String surname = result.getString(DatabaseConstants.UserConstants.USER_SURNAME);
                String mail = result.getString(DatabaseConstants.UserConstants.USER_MAIL);
                int booksNumber = result.getInt(DatabaseConstants.UserConstants.USER_BOOKS_NR);
                users.add(new UserInDB(id, name, surname, mail, booksNumber));
            }
            logger.info("Executed searchUsers method.");
        } catch (java.sql.SQLException e) {
            logger.warn("Could not execute query in searchUsers method. " + e.getMessage());
            throw e;
        }
        return users;
    }

    public static ArrayList<String> getUsers(final String query_str) {
        ArrayList<String> result = new ArrayList<>();
        ArrayList<UserInDB> users;
        try {
            users = searchUsers(query_str);
            for(var user : users) {
                String respresentation = user.getId() + " " + user.getName() + " " + user.getSurname() + " " + user.getMail() + " " + user.getBooksNumber();
                result.add(respresentation);
            }
        } catch (SQLException e) {
            result.add("Exception occurred in database.");
            logger.warn("Could not get users in getUsers. " + e.getMessage());
        }
        return result;
    }

    public static ArrayList<Admin> getAdmins() throws SQLException, NullOrEmptyStringException, InvalidIdException {
        ArrayList<Admin> admins = new ArrayList<>();
        try {
            CONNECTION = DriverManager.getConnection(URL, LOGIN, getPassword());
            logger.info("Connected to database.");
            Statement query = CONNECTION.createStatement();
            ResultSet result = query.executeQuery(DatabaseConstants.InquiriesConstants.SELECT_ADMINS);
            while (result.next()) {
                int id = result.getInt(DatabaseConstants.AdminConstants.ADMIN_ID);
                String name = result.getString(DatabaseConstants.AdminConstants.ADMIN_NAME);
                String surname = result.getString(DatabaseConstants.AdminConstants.ADMIN_SURNAME);
                String mail = result.getString(DatabaseConstants.AdminConstants.ADMIN_MAIL);
                String login = result.getString(DatabaseConstants.AdminConstants.ADMIN_LOGIN);
                String password = result.getString(DatabaseConstants.AdminConstants.ADMIN_PASSWORD);
                admins.add(new Admin(login, password, name, surname, mail, id));
            }
            logger.info("Executed getAdmins method.");
        } catch (java.sql.SQLException | NullOrEmptyStringException | InvalidIdException e) {
            logger.warn("Could not execute query in getAdmins method " + e.getMessage());
            throw e;
        }
        return admins;
    }

    public static ArrayList<CommonUser> getCommonUsers() throws SQLException, InvalidBookNumberException, NullOrEmptyStringException, InvalidIdException {
        ArrayList<CommonUser> users = new ArrayList<>();
        try {
            CONNECTION = DriverManager.getConnection(URL, LOGIN, getPassword());
            logger.info("Connected to database.");
            Statement query = CONNECTION.createStatement();
            ResultSet result = query.executeQuery(DatabaseConstants.InquiriesConstants.SELECT_COMMON_USERS);
            while (result.next()) {
                int id = result.getInt(DatabaseConstants.UserConstants.USER_ID);
                String name = result.getString(DatabaseConstants.UserConstants.USER_NAME);
                String surname = result.getString(DatabaseConstants.UserConstants.USER_SURNAME);
                String mail = result.getString(DatabaseConstants.UserConstants.USER_MAIL);
                String login = result.getString(DatabaseConstants.UserConstants.USER_LOGIN);
                String password = result.getString(DatabaseConstants.UserConstants.USER_PASSWORD);
                int booksNumber = result.getInt(DatabaseConstants.UserConstants.USER_BOOKS_NR);
                users.add(new CommonUser(login, password, name, surname, id, mail, booksNumber));
            }
            logger.info("Executed getUsers method.");
        } catch (java.sql.SQLException | NullOrEmptyStringException | InvalidIdException | InvalidBookNumberException e) {
            logger.warn("Could not execute query in getAdmins method " + e.getMessage());
            throw e;
        }
        return users;
    }

    public static void addUser(String id, final String name, final String surname, final String mail) {
        String query_str = "insert into nkulakow.pap_users values (" + id + ",'" + name + "','" + surname + "','" + mail+ "','" + 0  + "')";

        try {
            CONNECTION = DriverManager.getConnection(URL, LOGIN, getPassword());
            logger.info("Connected to database.");
            Statement query = CONNECTION.createStatement();
            query.executeUpdate(query_str);
            logger.info("Executed addUser method.");
        } catch (java.sql.SQLException e) {
            logger.warn("Could not execute query in addUser method " + e.getMessage());
        }
    }

    private static String getPassword()
    {
        byte[] bytePasswd = Base64.getDecoder().decode(HASHEDPASSWORD);
        return new String(bytePasswd);
    }
}

class DatabaseConstants {
    public static final class Tables {
        public static final int TABLE_USERS = 0;
        public static final int TABLE_BOOKS = 1;
        public static final int TABLE_LOANS = 2;
    }
    public static final class UserConstants {
        public static final String USER_ID = "user_id";
        public static final String USER_NAME = "first_name";
        public static final String USER_SURNAME = "last_name";
        public static final String USER_MAIL = "mail";
        public static final String USER_BOOKS_NR = "books_nr";
        public static final String USER_LOGIN = "login";
        public static final String USER_PASSWORD = "password";
    }

    public static final class AdminConstants {
        public static final String ADMIN_ID = "admin_id";
        public static final String ADMIN_NAME = "name";
        public static final String ADMIN_SURNAME = "surname";
        public static final String ADMIN_LOGIN = "login";
        public static final String ADMIN_PASSWORD = "password";
        public static final String ADMIN_MAIL = "mail";
    }
    public static final class InquiriesConstants {
        public static final String SELECT_COMMON_USERS = "SELECT u.*, p.login, p.password from nkulakow.PAP_USERS u join nkulakow.PAP_USERS_PASSWD p on(u.user_id=p.user_id)";
        public static final String SELECT_ADMINS = "SELECT a.*, p.login, p.password from nkulakow.PAP_ADMINS a join nkulakow.PAP_ADMINS_PASSWD p on(a.admin_id=p.admin_id)";
    }
}

