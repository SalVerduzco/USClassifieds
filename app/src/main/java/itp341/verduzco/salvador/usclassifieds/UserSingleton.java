package itp341.verduzco.salvador.usclassifieds;

import android.content.Context;

public class UserSingleton {
    private Context context;
    private static UserSingleton singleton;
    private String userID;

    private UserSingleton(Context context){
        this.context = context;
    }

    public static UserSingleton getInstance(Context context){
        if (singleton == null){
            singleton = new UserSingleton(context);
        }
        return singleton;
    }

    public void setID(String userID){
        this.userID = userID;
    }

    public String getID(){
        return this.userID;
    }
}
