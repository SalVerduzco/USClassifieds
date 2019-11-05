package itp341.verduzco.salvador.usclassifieds;

import android.widget.EditText;
import android.widget.TextView;

public class Item { // serializable added later

    //Instance variables
    private String seller;
    private String location;
    private String description;
    private String category;
    private String title;
    private String price;
    //private double rating;
    // private String website; <-- not needed

    Item(EditText description, EditText location, EditText title, EditText price, TextView seller) {
        this.description = description.getText().toString();
        this.location = location.getText().toString();
        this.title = title.getText().toString();
        this.price = title.getText().toString();
        this.seller = seller.getText().toString();
    }


    //constructor used for testing
    public Item() {
        super();
    }

    public Item(String seller, String location, String description, String category,
                String title, String price, String website, double rating) {
        super();
        this.seller = seller;
        this.location = location;
        this.description = description;
        this.category = category;
        this.title = title;
        this.price = price;
    }



    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public String getCategory() {
        return category;
    }


    public void setCategory(String category) {
        this.category = category;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getSeller() {
        return seller;
    }


    public void setSeller(String seller) {
        this.seller = seller;
    }


    public String getLocation() {
        return location;
    }


    public void setLocation(String location) {
        this.location = location;
    }


    public String getPrice() {
        return price;
    }


    public void setPrice(String price) {
        this.price = price;
    }


//    public String getWebsite() {
//        return website;
//    }


//    public void setWebsite(String website) {
//        this.website = website;
//    }


//    public double getRating() {
//        return rating;
//    }
//    public void setRating(double rating) {
//        this.rating = rating;
//    }


    //TODO toString()
    public String toString() {
        return seller;
    }

}
