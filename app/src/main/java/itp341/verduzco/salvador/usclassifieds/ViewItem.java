package itp341.verduzco.salvador.usclassifieds;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;

public class ViewItem extends AppCompatActivity {

    public static final String TAG = ViewItem.class.getSimpleName();
    private FirestoreHelper firestoreHelper;
    private String itemId;

    private TextView editLocation;
    private TextView editDescription;
    private TextView editTitle;
    private TextView editPrice;
    private Spinner spinnerState;
    ArrayAdapter<CharSequence> spinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_item);
        firestoreHelper = new FirestoreHelper();

        Intent intent = getIntent();
        if (intent != null){
            itemId = intent.getStringExtra("itemId");
        }

        // Find Views
        editLocation = findViewById(R.id.edit_location);
        editDescription = findViewById(R.id.edit_description);
        editTitle = findViewById(R.id.edit_title);
        editPrice = findViewById(R.id.edit_price);
        spinnerState = (Spinner) findViewById(R.id.spinner_state);

        spinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.states,
                android.R.layout.simple_spinner_item);

        spinnerState.setAdapter(spinnerAdapter);

        firestoreHelper
                .getItemByItemIdRef(itemId)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        Item item = documentSnapshot.toObject(Item.class);
                        editLocation.setText(item.getLocation());
                        editPrice.setText(item.getPrice());
                        editDescription.setText(item.getDescription());
                        editTitle.setText(item.getTitle());
                        Log.e(TAG, item.getCategory());
                        Log.e(TAG, "" + spinnerAdapter.getPosition(item.getCategory()));
                        spinnerState.setSelection(spinnerAdapter.getPosition(item.getCategory()));
                    }
                });
    }

}


