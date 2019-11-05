package itp341.verduzco.salvador.usclassifieds;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class LoginDetails {

    private static LoginDetails singleton;
    private Context context;
    private String email;

    //TODO private singleton constructor
    private LoginDetails(Context context){
        this.context = context;
    }

    //TODO Singleton get method
    public static LoginDetails get(Context context){
        if (singleton == null){
            singleton = new LoginDetails(context);
        }
        return singleton;
    }

    public void SetEmail(String email){
        this.email = email;
    }

    public String GetEmail(String email){
        return this.email;
    }

}
