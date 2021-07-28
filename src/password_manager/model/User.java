package password_manager.model;

import java.sql.Date;

public class User {
    private int id;
    private String fname;
    private String mname;
    private String lname;
    private String username;
    private String password;
    private String profileImage;
    private String joinedDate;
    private String lastLogin;
    private String info;


    public User(){
    }

    public User(int id, String fname, String mname, String lname, String username, String password, String profileImage, String joinedDate, String lastLogin, String info) {
        this.id = id;
        this.fname = fname;
        this.mname = mname;
        this.lname = lname;
        this.username = username;
        this.password = password;
        this.profileImage = profileImage;
        this.joinedDate = joinedDate;
        this.lastLogin = lastLogin;
        this.info = info;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(String joinedDate) {
        this.joinedDate = joinedDate;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
