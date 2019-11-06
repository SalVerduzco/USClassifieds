package itp341.verduzco.salvador.usclassifieds;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.List;

public class MainListActivity extends AppCompatActivity {

    public static final String TAG = MainListActivity.class.getSimpleName();
    private FirestoreHelper firestoreHelper;

    private List<Item> mItems;
    private ItemListAdapter mItemListAdapter;

    private Button buttonAdd;
    private ListView itemList;
    public String category = "all";
    public Context CURRENT_CONTEXT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        CURRENT_CONTEXT = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_list_activity);
        Log.d(TAG, "onCreate");

        // Find views
        buttonAdd = (Button) findViewById(R.id.button_add);
        itemList = (ListView) findViewById(R.id.listView);

        firestoreHelper = new FirestoreHelper();

        mItems = new ArrayList<>();
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
                for (DocumentSnapshot snapshot: queryDocumentSnapshots) {
                    mItems.add(snapshot.toObject(Item.class));
                }

                ItemSingleton.get(CURRENT_CONTEXT).SetList(mItems);
                mItemListAdapter.notifyDataSetChanged();
            }
        });

        //TODO What happens when user clicks add?
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "mButtonadd: onClick ");
                Intent intent = new Intent(MainListActivity.this, DetailActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        //TODO create listview item click listener
        itemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Log.d(TAG, "listView: onListItemClick");
                Intent intent = new Intent(MainListActivity.this, DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_POSITION, position);
                startActivityForResult(intent, 0);
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
                    category = "books";
                }
                    break;
            case R.id.radio_electronics:
                if (checked){
                    category = "electronics";
                }
                    break;
            case R.id.radio_furniture:
                if (checked){
                    category = "furniture";
                }
                    break;
            case R.id.radio_clothing:
                if (checked){
                    category = "clothing";
                }
                    break;
        }

        if(!old_category.equalsIgnoreCase(category)){
            ItemSingleton.get(this).parse(category);
            mItemListAdapter.notifyDataSetChanged();
        }
    }


}


