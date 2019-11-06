package itp341.verduzco.salvador.usclassifieds;

import com.google.firebase.firestore.IgnoreExtraProperties;

import java.util.Arrays;
import java.util.List;

@IgnoreExtraProperties
public class User {
    public String name;
    public String location;
    public String phone;
    public String email;
    public String description;
    public String picture_url;

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


    /*public List<String> previouslySold;
    public List<String> currentlySelling;
    public List<String> friends;
    public List<String> pendingFriends;*/

    /*
    public void addToPreviouslySold(String id) {
        this.previouslySold.add(id);
    }
    public void addToCurrentlySelling(String id) {
        this.currentlySelling.add(id);
    }
    public void removeFromCurrentlySelling(String id) {
        this.currentlySelling.remove(this.currentlySelling.indexOf(id));
    }
    public void editPhone(String newNum) {
        this.phone = newNum;
    }
    public void addFriend(String friend) {
        this.friends.add(friend);
    }
    public void removeFriend(String friend) {
        this.friends.remove(this.friends.indexOf(friend));
    }
    public void sendFriendRequest(String potentialFriend) {
        this.pendingFriends.add(potentialFriend);
    }
    public void removeFriendRequest(String user) {
        this.pendingFriends.remove(this.pendingFriends.indexOf(user));
    }*/

}
