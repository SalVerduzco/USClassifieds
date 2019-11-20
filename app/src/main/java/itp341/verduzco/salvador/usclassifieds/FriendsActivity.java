package itp341.verduzco.salvador.usclassifieds;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class FriendsActivity extends AppCompatActivity {
    private static String TAG="FriendsActivity";
    private FirestoreHelper firestoreHelper;

    private ListView friendListView;
    private List<User> friends;
    private List<String> friendUserIds;
    private UserListAdapter friendsListAdapter;


    private ListView requestListView;
    private List<User> requests;
    private List<String> requestUserIds;
    private UserListAdapter requestsListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friends_list);
        firestoreHelper = new FirestoreHelper();

        friends = new ArrayList<>();
        friendUserIds = new ArrayList<>();
        requests = new ArrayList<>();
        requestUserIds = new ArrayList<>();

        friendsListAdapter = new UserListAdapter(this, friends);
        requestsListAdapter = new UserListAdapter(this, requests);

        requestListView = (ListView) findViewById(R.id.listView_requests);
        friendListView = (ListView) findViewById(R.id.listView_friends);

        requestListView.setAdapter(requestsListAdapter);
        friendListView.setAdapter(friendsListAdapter);

        firestoreHelper
                .getRequestsByUserIdRef(UserSingleton.getInstance(getApplicationContext()).getID())
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        // error occured.. log it
                        if(e != null) {
                            Log.d(TAG, e.getMessage());
                        }

                        requests.clear();
                        requestUserIds.clear();
                        for (DocumentSnapshot snapshot: queryDocumentSnapshots) {
                            requests.add(snapshot.toObject(User.class));
                            requestUserIds.add(snapshot.getId());
                        }
                        requestsListAdapter.notifyDataSetChanged();
                    }
                });

        firestoreHelper
                .getFriendsByUserIdRef(UserSingleton.getInstance(getApplicationContext()).getID())
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        // error occured.. log it
                        if(e != null) {
                            Log.d(TAG, e.getMessage());
                        }

                        friends.clear();
                        friendUserIds.clear();
                        for (DocumentSnapshot snapshot: queryDocumentSnapshots) {
                            friends.add(snapshot.toObject(User.class));
                            friendUserIds.add(snapshot.getId());
                        }
                        friendsListAdapter.notifyDataSetChanged();
                    }
                });

        requestListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Log.d(TAG, "requestListView: onListItemClick");
                Intent intent = new Intent(FriendsActivity.this, UserDetailPage.class);
                intent.putExtra("userId", requestUserIds.get(position));
                startActivityForResult(intent, 0);
            }
        });

        friendListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Log.d(TAG, "friendListView: onListItemClick");
                Intent intent = new Intent(FriendsActivity.this, UserDetailPage.class);
                intent.putExtra("userId", friendUserIds.get(position));
                startActivityForResult(intent, 0);
            }
        });
    }

}
