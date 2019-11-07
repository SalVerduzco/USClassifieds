package itp341.verduzco.salvador.usclassifieds;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
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
    private List<String> friends;
    private UserListAdapter friendsListAdapter;


    private ListView requestListView;
    private List<String> requests;
    private UserListAdapter requestsListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_list_activity);
        firestoreHelper = new FirestoreHelper();

        friends = new ArrayList<>();
        requests = new ArrayList<>();

        friendsListAdapter = new UserListAdapter(this, friends);
        requestsListAdapter = new UserListAdapter(this, requests);

        requestListView = (ListView) findViewById(R.id.listview_requests);
        friendListView = (ListView) findViewById(R.id.listview_friends);

        requestListView.setAdapter(requestsListAdapter);
        friendListView.setAdapter(friendsListAdapter);

        firestoreHelper.getUserByUserIdRef("103151951588532023496").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                User user = documentSnapshot.toObject(User.class);
                friends = user.getFriends();
                requests = user.getRequests();
                friendsListAdapter.notifyDataSetChanged();
                requestsListAdapter.notifyDataSetChanged();
            }
        });
    }

}
