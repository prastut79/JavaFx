package password_manager.database;

import password_manager.model.Password;
import password_manager.model.User;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseAccess {

    public static boolean validateCredentials(String username, String pasword) {
        int count = 0;
        try {
            String query = "SELECT Count(*) FROM User WHERE id=1 AND username=? AND password=?";
            PreparedStatement preparedStatement = DatabaseConnection.getDbInstance().getConnection().prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, pasword);
            ResultSet set = preparedStatement.executeQuery();
            while (set.next()) {
                count = set.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count == 1;
    }

    public static User getUser() {
        User user = new User();
        try {
            String query = "SELECT * FROM User WHERE id=1";
            PreparedStatement preparedStatement = DatabaseConnection.getDbInstance().getConnection().prepareStatement(query);
            ResultSet set = preparedStatement.executeQuery();
            while (set.next()) {
                user.setId(set.getInt("id"));
                user.setFname(set.getString("fname"));
                user.setMname(set.getString("mname"));
                user.setLname(set.getString("lname"));
                user.setUsername(set.getString("username"));
                user.setPassword(set.getString("password"));
                user.setProfileImage((set.getString("profileImage")));
                user.setJoinedDate(set.getString("joinedDate"));
                user.setLastLogin(set.getString("lastLogin"));
                user.setInfo(set.getString("info"));
                break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static boolean insertPassword(Password password) {
        String query = "INSERT INTO Passwords (title, username, password, website, info, addedDate, modifiedDate, addedBy) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = DatabaseConnection.getDbInstance().getConnection().prepareStatement(query);
            preparedStatement.setString(1, password.getTitle());
            preparedStatement.setString(2, password.getUsername());
            preparedStatement.setString(3, password.getPassword());
            preparedStatement.setString(4, password.getWebsite());
            preparedStatement.setString(5, password.getInfo());
            preparedStatement.setString(6, password.getAddedDate());
            preparedStatement.setString(7, password.getModifiedDate());
            preparedStatement.setString(8, password.getAddedBy());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean updatePassword(Password password){
        try {
            String query= "UPDATE passwords SET title=?, username=?, password=?, website=?, info=?, modifiedDate=? WHERE id=?";
            PreparedStatement preparedStatement = DatabaseConnection.getDbInstance().getConnection().prepareStatement(query);
            preparedStatement.setString(1, password.getTitle());
            preparedStatement.setString(2, password.getUsername());
            preparedStatement.setString(3, password.getPassword());
            preparedStatement.setString(4, password.getWebsite());
            preparedStatement.setString(5, password.getInfo());
            preparedStatement.setString(6, password.getModifiedDate());
            preparedStatement.setInt(7, password.getId());
            preparedStatement.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public static boolean deletePassword(int id){
        try {
            String query= "DELETE FROM passwords WHERE id=?";
            PreparedStatement preparedStatement = DatabaseConnection.getDbInstance().getConnection().prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public static ArrayList<Password> getAllPassword() {
        ArrayList<Password> passwords = new ArrayList<>();
        try {
            String query = "SELECT * FROM Passwords";
            PreparedStatement preparedStatement = DatabaseConnection.getDbInstance().getConnection().prepareStatement(query);
            ResultSet set = preparedStatement.executeQuery();

            while (set.next()) {
                int id = set.getInt("id");
                String title = set.getString("title");
                String username = set.getString("username");
                String password = set.getString("password");
                String info = set.getString("info");
                String website = set.getString("website");
                String addedDate = set.getString("addedDate");
                String modifiedDate = set.getString("modifiedDate");
                String addedBy = set.getString("addedBy");
                Password psw = new Password(id, title, username, password, website, info, addedDate, modifiedDate, addedBy);
                passwords.add(psw);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return passwords;
    }

    public static boolean updateLastLogin(String time) {
        String query = "UPDATE User SET lastLogin=? WHERE id=1";
        try {
            PreparedStatement preparedStatement = DatabaseConnection.getDbInstance().getConnection().prepareStatement(query);
            preparedStatement.setString(1, time);
            preparedStatement.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}

