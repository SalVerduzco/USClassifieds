package itp341.verduzco.salvador.usclassifieds;

import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.firestore.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Item {
    private String description;
    private String location;
    private String title;
    private String price;
    private String userId;
    private String category;
    private String picture_url;
    private boolean is_available;

//    public Item() {
//        super();
//    }
//
//    Item(EditText description, EditText location, EditText title, EditText price, TextView seller) {
//        this.description = description.getText().toString();
//        this.location = location.getText().toString();
//        this.title = title.getText().toString();
//        this.price = title.getText().toString();
//        this.userId = seller.getText().toString();
//    }

    public Item(String description, String location, String title, String price, String category, String userId) {
        this.description = description;
        this.location = location;
        this.title = title;
        this.price = price;
        this.userId = userId;
        this.category = category;
        this.picture_url = "default";
        this.is_available = true;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPicture_url() {
        return picture_url;
    }

    public void setPicture_url(String picture_url) {
        this.picture_url = picture_url;
    }

    public boolean isIs_available() {
        return is_available;
    }

    public void setIs_available(boolean is_available) {
        this.is_available = is_available;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}