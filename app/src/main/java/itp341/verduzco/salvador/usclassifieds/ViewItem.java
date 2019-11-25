package itp341.verduzco.salvador.usclassifieds;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;

public class ViewItem extends AppCompatActivity {

    public static final String TAG = ViewItem.class.getSimpleName();
    private FirestoreHelper firestoreHelper;
    private String itemId;
    private Item item;

    private TextView editLocation;
    private TextView editDescription;
    private TextView editTitle;
    private TextView editPrice;
    private TextView spinnerState;
    private TextView availability;
    private Button markSoldButton;
    private Button showSellerButton;
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

        Log.e(TAG, itemId);

        // Find Views
        editLocation = findViewById(R.id.edit_location);
        editDescription = findViewById(R.id.edit_description);
        editTitle = findViewById(R.id.edit_title);
        editPrice = findViewById(R.id.edit_price);
        spinnerState = findViewById(R.id.category_text);
        availability = (TextView) findViewById(R.id.availability_text);
        markSoldButton = (Button) findViewById(R.id.button_mark_sold);
        showSellerButton = (Button) findViewById(R.id.button_show_seller);

        spinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.states,
                android.R.layout.simple_spinner_item);

        //spinnerState.setAdapter(spinnerAdapter);

        firestoreHelper
                .getItemByItemIdRef(itemId)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        item = documentSnapshot.toObject(Item.class);
                        editLocation.setText(item.getLocation());
                        editPrice.setText(item.getPrice());
                        editDescription.setText(item.getDescription());
                        editTitle.setText(item.getTitle());
                        spinnerState.setText(item.getCategory());
                        if (item.isIs_available()) {
                            availability.setText("Yes");
                        } else {
                            availability.setText("No");
                        }

                        Log.e(TAG, item.getCategory());
                        //Log.e(TAG, "" + spinnerAdapter.getPosition(item.getCategory()));
                        // if item is not available
                        // or the seller is not the current user
                        // hide the sell button
                        if (!item.isIs_available() || !item.getUserId().equals(UserSingleton.getInstance(getApplicationContext()).getID())) {
                            markSoldButton.setVisibility(View.INVISIBLE);
                        }

                        if (item.getUserId().equals(UserSingleton.getInstance(getApplicationContext()).getID())) {
                            showSellerButton.setVisibility(View.INVISIBLE);
                        }

                    }
                });

        markSoldButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firestoreHelper.markItemAsSold(itemId, new FirestoreHelper.FirestoreHelperListener() {
                    @Override
                    public void success() {
                        finish();
                    }

                    @Override
                    public void failure() {

                    }
                });
            }
        });

        showSellerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewItem.this, UserDetailPage.class);
                intent.putExtra("userId", item.getUserId());
                startActivityForResult(intent, 0);
            }
        });
    }


}


