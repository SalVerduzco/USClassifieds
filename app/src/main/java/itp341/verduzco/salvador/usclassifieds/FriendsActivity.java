package itp341.verduzco.salvador.usclassifieds;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FriendsActivity extends AppCompatActivity {
    private static String TAG="FriendsActivity";
    private FirestoreHelper firestoreHelper;

    private ListView friendListView;
    private List<String> friends;
    private UserListAdapter friendsListAdapter;


    private ListView requestListView;
    private List<String> requests;
    private UserListAdapter requestsListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friends_list);
        firestoreHelper = new FirestoreHelper();

        friends = new ArrayList<>();
        requests = new ArrayList<>();

        friendsListAdapter = new UserListAdapter(this, friends);
        requestsListAdapter = new UserListAdapter(this, requests);

        requestListView = (ListView) findViewById(R.id.listView_requests);
        friendListView = (ListView) findViewById(R.id.listView_friends);

        requestListView.setAdapter(requestsListAdapter);
        friendListView.setAdapter(friendsListAdapter);

        firestoreHelper
                .getUserByUserIdRef(UserSingleton.getInstance(getApplicationContext()).getID())
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                User user = documentSnapshot.toObject(User.class);
                Log.e("HERE", user.getName());
                Log.e("HERE", Arrays.toString(user.getRequests().toArray()));

                friends.clear();
                friends.addAll(user.getFriends());

                requests.clear();
                requests.addAll(user.getRequests());

                friendsListAdapter.notifyDataSetChanged();
                requestsListAdapter.notifyDataSetChanged();
            }
        });
    }

}
