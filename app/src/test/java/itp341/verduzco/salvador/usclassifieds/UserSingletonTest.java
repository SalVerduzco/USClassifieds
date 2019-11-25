package itp341.verduzco.salvador.usclassifieds;

import org.junit.Test;

import static org.junit.Assert.*;

import android.content.Context;

public class TestUserSingleton {

    @Test
    public void testgetID() {
        Context testcontext = null;
        UserSingleton.getInstance(testcontext).setID("11");
        assertEquals("11",UserSingleton.getInstance(testcontext).getID());
    }
    @Test
    public void testgetNullID()
    {
        Context testcontext = null;
        UserSingleton.getInstance(testcontext).setID(null);
        assertEquals(null,UserSingleton.getInstance(testcontext).getID());
    }
}