package itp341.verduzco.salvador.usclassifieds;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AddItem extends AppCompatActivity {
    private static String TAG = "AddItem";
    private FirebaseFirestore firebaseFirestore;

    ArrayList<String> listItems = new ArrayList<String>();

    //DEFINING A STRING ADAPTER WHICH WILL HANDLE THE DATA OF THE LISTVIEW
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    public void onClickSendInfo(View view) {

        EditText description = (EditText) findViewById(R.id.description);
        EditText location = (EditText) findViewById(R.id.location);
        EditText title = (EditText) findViewById(R.id.title);
        EditText price = (EditText) findViewById(R.id.price);
        TextView seller = (TextView) findViewById(R.id.seller);

        Item item = new Item(
                description.getText().toString(),
                location.getText().toString(),
                title.getText().toString(),
                price.getText().toString(),
                seller.getText().toString(),
                true
        );

        firebaseFirestore.collection("Items")
                .add(item)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "DocumentSnapshot failed");
                    }
                });

        // go back to the main activity
        Intent k = new Intent(this, MainActivity.class);
        k.putExtra("title", item.getTitle());
        startActivity(k);
    }
}
