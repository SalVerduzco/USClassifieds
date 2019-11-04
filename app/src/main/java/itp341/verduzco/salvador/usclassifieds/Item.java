package itp341.verduzco.salvador.usclassifieds;

import android.widget.EditText;
import android.widget.TextView;

public class Item {
    public String description;
    public String location;
    public String title;
    public String price;
    public String seller;

    Item(EditText description, EditText location, EditText title, EditText price, TextView seller) {
        this.description = description.getText().toString();
        this.location = location.getText().toString();
        this.title = title.getText().toString();
        this.price = title.getText().toString();
        this.seller = seller.getText().toString();
    }
}
