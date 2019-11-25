package itp341.verduzco.salvador.usclassifieds;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainListActivity extends AppCompatActivity {
    public static final String TAG = MainListActivity.class.getSimpleName();
    private FirestoreHelper firestoreHelper;

    private List<Item> mItems;
    private List<String> mItemKeys;

    private ItemListAdapter mItemListAdapter;
    public String category = "all";
    private ArrayAdapter<CharSequence> spinnerAdapter;

    private Button buttonAdd;
    private ListView itemList;
    private Spinner priceSpinner;
    private Button searchButton;

    private Button sendToFriend;
    private EditText editString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.e("create","create");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_list_activity);
        Log.d(TAG, "onCreate");

        // Find views
        buttonAdd = (Button) findViewById(R.id.button_add);
        itemList = (ListView) findViewById(R.id.listView);
        sendToFriend = findViewById(R.id.sendToFriendsButton);
        priceSpinner = findViewById(R.id.price_spinner);
        searchButton = findViewById(R.id.searchButton);
        editString = findViewById(R.id.editSearch);



        spinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.sortByArray,
                android.R.layout.simple_spinner_item);

        priceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id){
                Log.e(TAG, priceSpinner.getSelectedItem().toString());
                if(!priceSpinner.getSelectedItem().toString().equalsIgnoreCase("Low to High")){
                    Log.e(TAG, "if statem.");
                    Collections.sort(mItems, new Comparator<Item>(){
                        public int compare(Item obj1, Item obj2) {
                            return Integer.valueOf(obj2.getPrice()).compareTo(Integer.valueOf(obj1.getPrice()));
                        }
                    });

                } else {

                    Collections.sort(mItems, new Comparator<Item>(){
                        public int compare(Item obj1, Item obj2) {
                            return Integer.valueOf(obj1.getPrice()).compareTo(Integer.valueOf(obj2.getPrice()));
                        }
                    });

                }

                /* Now fix keys */
                mItemKeys.clear();
                for(Item i : mItems){
                    mItemKeys.add(i.id);
                }
                mItemListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView){

            }
        });

        priceSpinner.setAdapter(spinnerAdapter);

        firestoreHelper = new FirestoreHelper();

        mItems = new ArrayList<>();
        mItemKeys = new ArrayList<>();

        mItemListAdapter = new ItemListAdapter(this, mItems);
        itemList.setAdapter(mItemListAdapter);

        firestoreHelper.getItemsRef().addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                // error occured.. log it
                if(e != null) {
                    Log.d(TAG, e.getMessage());
                }

                mItems.clear();
                mItemKeys.clear();
                for (DocumentSnapshot snapshot: queryDocumentSnapshots) {
                    Item newItem = snapshot.toObject(Item.class);
                    newItem.id = snapshot.getId();
                    mItems.add(newItem);
                    mItemKeys.add(snapshot.getId());
                }

                mItemListAdapter.notifyDataSetChanged();
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "mButtonadd: onClick ");
                Intent intent = new Intent(MainListActivity.this, DetailActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        Button button = findViewById(R.id.button_profile);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(MainListActivity.this, UpdateProfile.class);
                startActivityForResult(intent, 0);
            }
        });

        Button history = findViewById(R.id.button_history);
        history.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(MainListActivity.this, ItemListActivity.class);
                intent.putExtra("option", "sold");
                startActivityForResult(intent, 0);
            }
        });

        Button button_selling = findViewById(R.id.MySellingButton);
        button_selling.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(MainListActivity.this, ItemListActivity.class);
                intent.putExtra("option", "selling");
                startActivity(intent);
            }
        });

        sendToFriend.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(MainListActivity.this, FriendsActivity.class);
                startActivity(intent);
            }
        });

        itemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Log.d(TAG, "listView: onListItemClick");
                Intent intent = new Intent(MainListActivity.this, ViewItem.class);
                intent.putExtra("itemId", mItemKeys.get(position));
                startActivity(intent);
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                String searchString = editString.getText().toString();
                Log.e(TAG, searchString);
                if(searchString.length()==0){
                    Log.e(TAG, "empty search");
                    return;
                }

                /* Update List by the query */
                Query query = firestoreHelper.getItemsRef();
                query = firestoreHelper.getItemsBySearchRef(searchString);
                query.addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        // error occured.. log it
                        if(e != null) {
                            Log.e(TAG, e.getMessage());
                        }
                        mItemKeys.clear();
                        mItems.clear();
                        for (DocumentSnapshot snapshot: queryDocumentSnapshots) {
                            Item newItem = snapshot.toObject(Item.class);
                            newItem.id = snapshot.getId();
                            mItems.add(newItem);
                            mItemKeys.add(snapshot.getId());
                        }
                        mItemListAdapter.notifyDataSetChanged();
                    }
                });

            }
        });

    }

    //TODO finish onActivityResult
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: requestCode: " + requestCode);
        mItemListAdapter.notifyDataSetChanged();
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        String old_category = category;

        switch(view.getId()) {
            case R.id.radio_books:
                if (checked){
                    category = "Books";
                }
                    break;
            case R.id.radio_electronics:
                if (checked){
                    category = "Electronics";
                }
                    break;
            case R.id.radio_furniture:
                if (checked){
                    category = "Furniture";
                }
                    break;
            case R.id.radio_clothing:
                if (checked){
                    category = "Clothing";
                }
                    break;
        }

        /* Update our list by the category */
        Query query = firestoreHelper.getItemsRef();
        query = firestoreHelper.getItemByCategoryRef(category);
        query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                // error occured.. log it
                Log.e(TAG, "Test");
                if(e != null) {
                    Log.e(TAG, e.getMessage());
                }
                mItemKeys.clear();
                mItems.clear();
                for (DocumentSnapshot snapshot: queryDocumentSnapshots) {
                    Item newItem = snapshot.toObject(Item.class);
                    newItem.id = snapshot.getId();
                    mItems.add(newItem);
                    mItemKeys.add(snapshot.getId());
                }

                if(!priceSpinner.getSelectedItem().toString().equalsIgnoreCase("Low to High")){
                    Log.e(TAG, "if statem.");
                    Collections.sort(mItems, new Comparator<Item>(){
                        public int compare(Item obj1, Item obj2) {
                            return Integer.valueOf(obj2.getPrice()).compareTo(Integer.valueOf(obj1.getPrice()));
                        }
                    });

                } else {

                    Collections.sort(mItems, new Comparator<Item>(){
                        public int compare(Item obj1, Item obj2) {
                            return Integer.valueOf(obj1.getPrice()).compareTo(Integer.valueOf(obj2.getPrice()));
                        }
                    });

                }

                /* Now fix keys */
                mItemKeys.clear();
                for(Item i : mItems){
                    mItemKeys.add(i.id);
                }
                mItemListAdapter.notifyDataSetChanged();

                mItemListAdapter.notifyDataSetChanged();
            }
        });
    }


}


