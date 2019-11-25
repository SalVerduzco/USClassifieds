package itp341.verduzco.salvador.usclassifieds;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestUser {
    @Test
    public void testGetName() {
        User testuser = new User("Tony", "Tony@gmail.com", "Los Angeles", "123");
        assertEquals("Tony",testuser.getName());
    }

    @Test
    public void testGetEmail() {
        User testuser = new User("Tony", "Tony@gmail.com", "Los Angeles", "123");
        assertEquals("Tony@gmail.com", testuser.getEmail());
    }

    @Test
    public void testGetLocation() {
        User testuser = new User("Tony", "Tony@gmail.com", "Los Angeles", "123");
        assertEquals("Los Angeles", testuser.getLocation());
    }

    @Test
    public void testGetPhone() {
        User testuser = new User("Tony", "Tony@gmail.com", "Los Angeles", "123");
        assertEquals("123", testuser.getPhone());
    }

    @Test
    public void testSetName(){
        User testuser = new User("Tony", "Tony@gmail.com", "Los Angeles", "123");
        testuser.setName("Tony Stark");
        assertEquals("Tony Stark", testuser.getName());
    }

    @Test
    public void testSetEmail(){
        User testuser = new User("Tony", "Tony@gmail.com", "Los Angeles", "123");
        testuser.setEmail("TonyStark@gmail.com");
        assertEquals("TonyStark@gmail.com", testuser.getEmail());
    }

    @Test
    public void testSetLocation(){
        User testuser = new User("Tony", "Tony@gmail.com", "Los Angeles", "123");
        testuser.setLocation("New York");
        assertEquals("New York", testuser.getLocation());
    }

    @Test
    public void testSetPhone(){
        User testuser = new User("Tony", "Tony@gmail.com", "Los Angeles", "123");
        testuser.setPhone("456");
        assertEquals("456", testuser.getPhone());
    }

    @Test
    public void testSetDescription(){
        User testuser = new User("Tony", "Tony@gmail.com", "Los Angeles", "123");
        testuser.setDescription("Hi this is Tony");
        assertEquals("Hi this is Tony", testuser.getDescription());
    }

    @Test
    public void testSetpictureUrl(){
        User testuser = new User("Tony", "Tony@gmail.com", "Los Angeles", "123");
        testuser.setPictureUrl("tonyprofile.png");
        assertEquals("tonyprofile.png", testuser.getPictureUrl());
    }

    @Test
    public void testRequested(){
        User testuser = new User();
        testuser.addRequested("123456");
        assertEquals("123456",testuser.getRequested().get(0));
    }

    @Test
    public void testRequestedMultiple(){
        User testuser = new User();
        for(int i = 0; i < 20; i++){
            testuser.addRequested(Integer.toString(i));
        }
        for(int i= 0; i < 20; i++){
            assertEquals(Integer.toString(i), testuser.getRequested().get(i));
        }
    }

    @Test
    public void testSetRequested(){
        User testuser = new User();
        List<String> requestedTest = new ArrayList<>();
        for(int i = 0; i < 15; i++){
            requestedTest.add(Integer.toString(i));
        }
        testuser.setRequested(requestedTest);
        assertEquals(requestedTest, testuser.getRequested());
    }

    @Test
    public void testRemoveSingleRequested(){
        User testuser = new User();
        testuser.addRequested("123456");
        testuser.removeRequested("123456");
        assertTrue(testuser.getFriends().isEmpty());
    }

    @Test
    public void testRemoveRequestedMultiple(){
        User testuser = new User();
        for(int i = 0; i < 20; i++){
            testuser.addRequested(Integer.toString(i));
        }
        for(int i=20; i >= 0 ; i--){
            testuser.removeRequested(Integer.toString(i));
        }
        assertTrue(testuser.getRequested().isEmpty());
    }


    @Test
    public void testRemoveParticularRequested(){
        User testuser = new User();
        List<String> requestedTest = new ArrayList<>();
        for(int i = 0; i < 15; i++){
            requestedTest.add(Integer.toString(i));
        }
        testuser.setRequested(requestedTest);
        requestedTest.remove(12);
        testuser.removeRequested("12");
        assertEquals(requestedTest, testuser.getRequested());
    }

    @Test
    public void removeRequestedEmpty(){
        User testuser = new User();
        testuser.removeRequested("123");
        assertTrue(testuser.getRequested().isEmpty());
    }

    @Test
    public void testAddFriend(){
        User testuser = new User();
        testuser.addFriend("123456");
        assertEquals("123456",testuser.getFriends().get(0));
    }

    @Test
    public void testFriendMultiple(){
        User testuser = new User();
        for(int i = 0; i < 20; i++){
            testuser.addFriend(Integer.toString(i));
        }
        for(int i= 0; i < 20; i++){
            assertEquals(Integer.toString(i), testuser.getFriends().get(i));
        }
    }

    @Test
    public void testRemoveSingleFriend(){
        User testuser = new User();
        testuser.addFriend("123456");
        testuser.removeFriend("123456");
        assertTrue(testuser.getFriends().isEmpty());
    }

    @Test
    public void testRemoveFriendMultiple(){
        User testuser = new User();
        for(int i = 0; i < 20; i++){
            testuser.addFriend(Integer.toString(i));
        }
        for(int i=20; i >= 0 ; i--){
            testuser.removeFriend(Integer.toString(i));
        }
        assertTrue(testuser.getFriends().isEmpty());
    }

    @Test
    public void testRemoveParticularFriend(){
        User testuser = new User();
        List<String> friendTest = new ArrayList<>();
        for(int i = 0; i < 15; i++){
            friendTest.add(Integer.toString(i));
            testuser.addFriend(Integer.toString(i));
        }
        friendTest.remove(12);
        testuser.removeFriend("12");
        assertEquals(friendTest, testuser.getFriends());
    }

    @Test
    public void removeFriendEmpty(){
        User testuser = new User();
        testuser.removeFriend("123");
        assertTrue(testuser.getFriends().isEmpty());
    }

}
