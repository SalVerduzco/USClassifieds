package itp341.verduzco.salvador.usclassifieds;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    public static final String TAG = DetailActivity.class.getSimpleName();
    private FirestoreHelper firestoreHelper;

    //TODO how will we pass data from MainList?
    public static final String EXTRA_POSITION = DetailActivity.class.getPackage().getName() + "extra_position";

    private EditText editSeller;
    private EditText editLocation;
    private EditText editDescription;
    private Spinner spinnerState;
    private EditText editTitle;
    private EditText editPrice;
    //private EditText editWebsite;
    private Button buttonSaveListing;
    private Button buttonDeleteListing;

    private int position;

    public static String[] states; // read from arrays.xml for US states
    ArrayAdapter<CharSequence> spinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
        firestoreHelper = new FirestoreHelper();

        //TODO get intent data
        Intent intent = getIntent();
        if (intent != null){
            position = intent.getIntExtra(EXTRA_POSITION, -1);
        }

        //findViews
        //editSeller = (EditText) findViewById(R.id.edit_seller); //name
        editLocation = (EditText) findViewById(R.id.edit_location); //address
        editDescription = (EditText) findViewById(R.id.edit_description); //city
        spinnerState = (Spinner) findViewById(R.id.spinner_state); // update
        editTitle = (EditText) findViewById(R.id.edit_title); //zip
        editPrice = (EditText) findViewById(R.id.edit_price);

        //editWebsite = (EditText) findViewById(R.id.edit_website);
        buttonSaveListing = (Button) findViewById(R.id.button_save_listing);
        buttonDeleteListing = (Button) findViewById(R.id.button_delete_listing);

        spinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.states,
                android.R.layout.simple_spinner_item);

        spinnerState.setAdapter(spinnerAdapter);



        //button listeners
        buttonDeleteListing.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                deleteAndClose();

            }
        });
        buttonSaveListing.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                saveAndClose();

            }
        });

        //TODO check if this is an existing or new listing
        if (position != -1){
            Item item = ItemSingleton.get(getApplicationContext()).getItem(position);
            loadData(item);
        }
    }


    //TODO load data from existing item object
    private void loadData(Item item) {
        editLocation.setText(item.getLocation());
        editPrice.setText(item.getPrice());
        editDescription.setText(item.getDescription());
        editTitle.setText(item.getTitle());
        //int pos = spinnerAdapter.getPosition(item.getCategory());
        //spinnerState.setSelection(pos);

    }

    //TODO modify to use arguments
    //TODO Listing should be saved (updated if existing, or added if new)
    private void saveAndClose() {
        Log.d(TAG, "saveAndClose");

        Item item = new Item(
                editDescription.getText().toString(),
                editLocation.getText().toString(),
                editTitle.getText().toString(),
                editPrice.getText().toString(),
                spinnerState.getSelectedItem().toString(),
                UserSingleton.getInstance(getApplicationContext()).getID()
        );

        firestoreHelper.addItem(item, new FirestoreHelper.FirestoreHelperListener() {
            @Override
            public void success() {
                Log.e(TAG, "YAY");
                finish();
            }
            @Override
            public void failure() {
                Log.e(TAG, "Noo");

            }
        });

        if (position == -1){
            ItemSingleton.get(this).addItem(item);
        } else {
            ItemSingleton.get(this).updateItem(position, item);
        }

    }

    //TODO Listing should be deleted (only it was an existing entry, not if it was new))
    private void deleteAndClose() {
        Log.d(TAG, "deleteAndClose");

        if (position != -1){
            ItemSingleton.get(this).removeItem(position);
        }

        finish();
    }




}


