package itp341.verduzco.salvador.usclassifieds;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestUser {
    @Test
    public void testgetName() {
        User testuser = new User("Tony", "Tony@gmail.com", "Los Angeles", "123");
        assertEquals("Tony",testuser.getName());
    }

    @Test
    public void testgetEmail() {
        User testuser = new User("Tony", "Tony@gmail.com", "Los Angeles", "123");
        assertEquals("Tony@gmail.com", testuser.getEmail());
    }

    @Test
    public void testgetLocation() {
        User testuser = new User("Tony", "Tony@gmail.com", "Los Angeles", "123");
        assertEquals("Los Angeles", testuser.getLocation());
    }

    @Test
    public void testgetPhone() {
        User testuser = new User("Tony", "Tony@gmail.com", "Los Angeles", "123");
        assertEquals("123", testuser.getPhone());
    }

    @Test
    public void testsetName(){
        User testuser = new User("Tony", "Tony@gmail.com", "Los Angeles", "123");
        testuser.setName("Tony Stark");
        assertEquals("Tony Stark", testuser.getName());
    }

    @Test
    public void testsetEmail(){
        User testuser = new User("Tony", "Tony@gmail.com", "Los Angeles", "123");
        testuser.setEmail("TonyStark@gmail.com");
        assertEquals("TonyStark@gmail.com", testuser.getEmail());
    }

    @Test
    public void testsetLocation(){
        User testuser = new User("Tony", "Tony@gmail.com", "Los Angeles", "123");
        testuser.setLocation("New York");
        assertEquals("New York", testuser.getLocation());
    }

    @Test
    public void testsetPhone(){
        User testuser = new User("Tony", "Tony@gmail.com", "Los Angeles", "123");
        testuser.setPhone("456");
        assertEquals("456", testuser.getPhone());
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


}
