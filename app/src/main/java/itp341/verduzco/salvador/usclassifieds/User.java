package itp341.verduzco.salvador.usclassifieds;

import java.util.Arrays;
import java.util.List;

public class User {
    public String name;
    public String email;
    public String phone;
    public List<String> previouslySold;
    public List<String> currentlySelling;
    public List<String> friends;
    public List<String> pendingFriends;

    User(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;

        this.previouslySold = Arrays.asList();
        this.currentlySelling = Arrays.asList();
        this.friends = Arrays.asList();
        this.pendingFriends = Arrays.asList();
    }

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
    }
}
