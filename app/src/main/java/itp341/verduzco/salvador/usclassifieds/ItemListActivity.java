package itp341.verduzco.salvador.usclassifieds;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ItemListActivity extends AppCompatActivity {
    private static String TAG=ItemListActivity.class.getSimpleName();
    private FirestoreHelper firestoreHelper;

    private List<Item> mItems;
    private List<String> mItemKeys;
    private ItemListAdapter mItemListAdapter;

    private TextView textViewTitle;
    private ListView mList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.items_list);
        firestoreHelper = new FirestoreHelper();

        // Find Views
        textViewTitle = (TextView) findViewById(R.id.textView_user_items_title);
        mList = (ListView) findViewById(R.id.listView_user_items);

        Query query = firestoreHelper.getItemsRef();
        Intent intent = getIntent();
        if (intent != null) {
            String option = intent.getStringExtra("option");
            // Selling items, default to sold
            if (option != null && option.equals("selling")) {
                textViewTitle.setText("Selling Items");
                query = firestoreHelper.getSellingItemsByUserIdRef(
                        UserSingleton.getInstance(getApplicationContext()).getID()
                );
            } else if (option != null && option.equals("friend")) {
                String friendUserId = intent.getStringExtra("friend_user_id");
                textViewTitle.setText("Friends' Selling Items");
                query = firestoreHelper.getSellingItemsByUserIdRef(
                        friendUserId
                );
            } else {
                textViewTitle.setText("Sold Items");
                query = firestoreHelper.getSoldItemsByUserIdRef(
                        UserSingleton.getInstance(getApplicationContext()).getID()
                );
            }
        }

        mItems = new ArrayList<>();
        mItemKeys = new ArrayList<>();
        mItemListAdapter = new ItemListAdapter(this, mItems);

        mList.setAdapter(mItemListAdapter);

        query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                // error occured.. log it
                if(e != null) {
                    Log.d(TAG, e.getMessage());
                }
                mItemKeys.clear();
                mItems.clear();
                for (DocumentSnapshot snapshot: queryDocumentSnapshots) {
                    mItemKeys.add(snapshot.getId());
                    mItems.add(snapshot.toObject(Item.class));
                }
                mItemListAdapter.notifyDataSetChanged();
            }
        });

        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Log.d(TAG, "listView: onListItemClick");
                Intent intent = new Intent(ItemListActivity.this, ViewItem.class);
                intent.putExtra("itemId", mItemKeys.get(position));
                startActivityForResult(intent, 0);
            }
        });
    }
}

