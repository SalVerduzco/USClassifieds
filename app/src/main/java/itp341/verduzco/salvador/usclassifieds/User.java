package itp341.verduzco.salvador.usclassifieds;

import com.google.firebase.firestore.IgnoreExtraProperties;

import java.util.ArrayList;
import java.util.List;

@IgnoreExtraProperties
public class User {
    private String name;
    private String location;
    private String phone;
    private String email;
    private String description;
    private String picture_url;
    private List<String> requested;
    private List<String> friends;

    public User() {
        this.requested = new ArrayList<>();
        this.friends = new ArrayList<>();
    }

    public User(String name, String email, String location, String phone) {
        this.name = name;
        this.location = location;
        this.email = email;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPictureUrl() {
        return picture_url;
    }

    public void setPictureUrl(String picture_url) {
        this.picture_url = picture_url;
    }

    public String getPicture_url() {
        return picture_url;
    }

    public void setPicture_url(String picture_url) {
        this.picture_url = picture_url;
    }

    public List<String> getRequested() {
        return this.requested;
    }

    public List<String> getFriends() {
        return this.friends;
    }

    public void setRequested(List<String> requested) {
        this.requested = requested;
    }

    public void addRequested(String id) {
        this.requested.add(id);
    }

    public void removeRequested(String id) {
        this.requested.remove(id);
    }

    public void addFriend(String id) {
        this.friends.add(id);
    }

    public void removeFriend(String id) {
        this.friends.remove(id);
    }

}
