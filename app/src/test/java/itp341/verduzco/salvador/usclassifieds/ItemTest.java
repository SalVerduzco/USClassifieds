package itp341.verduzco.salvador.usclassifieds;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ItemTest {
    String description = "a brand new item still in the box";
    String location = "Los Angeles, CA 90007";
    String title = "iPhone 15";
    String price = "1100.00";
    String category = "tech";
    String userID = "aAIFBEasdfiayaisdg1235";

    Item item = new Item(description, location, title, price, category, userID);

    @Test
    public void getItemDescriptionTest() {
        assertEquals("a brand new item still in the box", item.getDescription());
    }

    @Test
    public void getItemLocationTest() {
        assertEquals("Los Angeles, CA 90007", item.getLocation());
    }

    @Test
    public void getItemTitleTest() {
        assertEquals("iPhone 15", item.getTitle());
    }

    @Test
    public void getItemPriceTest() {
        assertEquals("1100.00", item.getPrice());
    }

    @Test
    public void getItemCategoryTest() {
        assertEquals("tech", item.getCategory());
    }

    @Test
    public void getItemUserIDTest() {
        assertEquals("aAIFBEasdfiayaisdg1235", item.getUserId());
    }

    @Test
    public void isAvailableTest() {
        assertTrue(item.isIs_available());
    }

    @Test
    public void pictureURLTest() {
        assertEquals("default", item.getPicture_url());
    }

    @Test
    public void searchableKeywordsTest() {
        String myList[] = new String[] {"iphone", "15", "tech"};

        assertEquals(Arrays.asList(myList), item.getSearchable_keywords());
    }

    @Test
    public void setDescriptionTest() {
        item.setDescription("this is jenk");

        assertEquals("this is jenk", item.getDescription());
    }

    @Test
    public void setLocationTest() {
        item.setLocation("Palo Alto, CA");

        assertEquals("Palo Alto, CA", item.getLocation());
    }

    @Test
    public void setTitleTest() {
        item.setTitle("iPhone XXVII");

        assertEquals("iPhone XXVII", item.getTitle());
    }

    @Test
    public void setPriceTest() {
        item.setPrice("500.23");

        assertEquals("500.23", item.getPrice());
    }

    @Test
    public void setUserIDTest() {
        item.setUserId("gmiu4334yr849872r3irh43uyhr3i2guy3");

        assertEquals("gmiu4334yr849872r3irh43uyhr3i2guy3", item.getUserId());
    }

    @Test
    public void setPictureURLTest() {
        item.setPicture_url("www.google.com/images");

        assertEquals("www.google.com/images", item.getPicture_url());
    }

    @Test
    public void setIsIsAvailableTest() {
        item.setIs_available(false);

        assertFalse(item.isIs_available());
    }

    @Test
    public void setCategoryTest() {
        item.setCategory("furniture");

        assertEquals("furniture", item.getCategory());
    }
}
