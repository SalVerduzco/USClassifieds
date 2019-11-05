package itp341.verduzco.salvador.usclassifieds;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class DemoListActivity extends AppCompatActivity {
    private static String TAG="DemoListActivity";
    private RecyclerView mList;
    private FirebaseFirestore mFirestore;
    private List<Item> mItems;
    private ItemListAdapter mItemListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_list_activity);

        mItems = new ArrayList<>();
        mItemListAdapter = new ItemListAdapter(mItems);

        mList = (RecyclerView) findViewById(R.id.recycler_demo);
        mList.setHasFixedSize(true);
        mList.setLayoutManager(new LinearLayoutManager(this));
        mList.setAdapter(mItemListAdapter);

        mFirestore = FirebaseFirestore.getInstance();

        mFirestore.collection("Items").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if(e != null) {
                    Log.d(TAG, e.getMessage());
                }

                mItems.clear();
                for (DocumentSnapshot snapshot: queryDocumentSnapshots) {
                    Log.d(TAG, "VAL--" + snapshot.get("title").toString());
                    mItems.add(snapshot.toObject(Item.class));
                }
                mItemListAdapter.notifyDataSetChanged();
            }
        });
    }
}
