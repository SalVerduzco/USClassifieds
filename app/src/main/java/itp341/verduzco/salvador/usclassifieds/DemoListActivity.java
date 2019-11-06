package itp341.verduzco.salvador.usclassifieds;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class DemoListActivity extends AppCompatActivity {
    private static String TAG="DemoListActivity";
    private FirestoreHelper firestoreHelper;

    private ListView mList;
    private List<Item> mItems;
    private ItemListAdapter mItemListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_list_activity);
        firestoreHelper = new FirestoreHelper();

        mItems = new ArrayList<>();
        mItemListAdapter = new ItemListAdapter(this, mItems);

        mList = (ListView) findViewById(R.id.listView);
        mList.setAdapter(mItemListAdapter);

        firestoreHelper.getSoldItemsByUserIdRef("103151951588532023496").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                // error occured.. log it
                if(e != null) {
                    Log.d(TAG, e.getMessage());
                }
                System.out.println("HJSDBJSBDJBSJBJDSB");
                mItems.clear();
                for (DocumentSnapshot snapshot: queryDocumentSnapshots) {
                    mItems.add(snapshot.toObject(Item.class));
                }
                mItemListAdapter.notifyDataSetChanged();
            }
        });
    }
}
