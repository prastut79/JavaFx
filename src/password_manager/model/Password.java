package password_manager.model;

public class Password {
    int id;
    String title;
    String username;
    String password;
    String website;
    String info;
    String addedDate;
    String modifiedDate;
    String addedBy;

    public String getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(String addedDate) {
        this.addedDate = addedDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }


    public  Password(){
    }

    public Password(int id, String title, String username, String password, String website, String info, String addedDate, String modifiedDate, String addedBy) {
        this.id = id;
        this.title = title;
        this.username = username;
        this.password = password;
        this.website = website;
        this.info = info;
        this.addedDate = addedDate;
        this.modifiedDate = modifiedDate;
        this.addedBy = addedBy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }
}
