package itp341.verduzco.salvador.usclassifieds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AddItem extends AppCompatActivity {
    private FirebaseDatabase database = FirebaseDatabase.getInstance();

    ArrayList<String> listItems = new ArrayList<String>();

    //DEFINING A STRING ADAPTER WHICH WILL HANDLE THE DATA OF THE LISTVIEW
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
    }

    public void onClickSendInfo(View view) {
        DatabaseReference myRef = database.getReference();

        EditText description = (EditText) findViewById(R.id.description);
        EditText location = (EditText) findViewById(R.id.location);
        EditText title = (EditText) findViewById(R.id.title);
        EditText price = (EditText) findViewById(R.id.price);
        TextView seller = (TextView) findViewById(R.id.seller);

        Item newItem = new Item(description, location, title, price, seller);

        Map<String, Object> mymap = new HashMap<>();

        mymap.put(title.getText().toString(), newItem);
        myRef.child(title.getText().toString()).setValue(mymap);
        System.out.println("We did it bois");

        // go back to the main activity
        Intent k = new Intent(this, MainActivity.class);
        k.putExtra("description", newItem.getDescription());
        k.putExtra("location", newItem.getLocation());
        k.putExtra("title", newItem.getTitle());
        k.putExtra("price", newItem.getPrice());
        k.putExtra("seller", newItem.getSeller());
        startActivity(k);
    }
}
