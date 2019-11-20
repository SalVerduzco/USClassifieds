package itp341.verduzco.salvador.usclassifieds;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;

public class UserDetailPage extends AppCompatActivity {
    private static String TAG="UserDetailPage";
    private String userId;

    private TextView email;
    private TextView name;
    private TextView phone;
    private TextView location;
    private TextView status;
    private Button addFriendButton;
    private Button removeFriendButton;
    private FirestoreHelper firestoreHelper;

    private User currentUser;
    private User profileUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        firestoreHelper = new FirestoreHelper();

        Intent intent = getIntent();
        if (intent != null){
            userId = intent.getStringExtra("userId");
        }

        email = findViewById(R.id.email);
        phone = findViewById(R.id.number);
        location = findViewById(R.id.location);
        name = findViewById(R.id.name);
        status = (TextView) findViewById(R.id.textView_status);

        addFriendButton = (Button) findViewById(R.id.button_add_friend);
        removeFriendButton = (Button) findViewById(R.id.button_remove_friend);

        firestoreHelper
                .getUserByUserIdRef(userId)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        profileUser = documentSnapshot.toObject(User.class);
                        updateDetails();
                    }
                });

        firestoreHelper
                .getUserByUserIdRef(UserSingleton.getInstance(getApplicationContext()).getID())
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        currentUser = documentSnapshot.toObject(User.class);
                        updateDetails();
                    }
                });
    }

    public void updateDetails() {
        if (profileUser!= null && currentUser!=null) {
            email.setText(profileUser.getEmail());
            location.setText(profileUser.getLocation());
            phone.setText(profileUser.getPhone());
            name.setText(profileUser.getName());

            // check friendship status
            if (currentUser.getFriends().contains(userId)) {
                status.setText("Friends");
                addFriendButton.setVisibility(View.INVISIBLE);
                removeFriendButton.setVisibility(View.VISIBLE);
                removeFriendButton.setText("Remove friend");
            } else if (profileUser.getRequested().contains(UserSingleton.getInstance(getApplicationContext()).getID())) {
                status.setText("Request recieved");
                addFriendButton.setVisibility(View.VISIBLE);
                removeFriendButton.setVisibility(View.VISIBLE);
                addFriendButton.setText("Accept request");
                removeFriendButton.setText("Decline request");

                addFriendButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        firestoreHelper.acceptFriendRequest(
                                currentUser,
                                UserSingleton.getInstance(getApplicationContext()).getID(),
                                profileUser,
                                userId,
                                new FirestoreHelper.FirestoreHelperListener() {
                                    @Override
                                    public void success() {
                                        finish();
                                    }

                                    @Override
                                    public void failure() {

                                    }
                                }
                        );
                    }
                });
            } else if (currentUser.getRequested().contains(userId)) {
                status.setText("Request sent");
                addFriendButton.setVisibility(View.INVISIBLE);
                removeFriendButton.setVisibility(View.VISIBLE);
                removeFriendButton.setText("Cancel request");
            } else {
                status.setText("Not friends");
                addFriendButton.setVisibility(View.VISIBLE);
                removeFriendButton.setVisibility(View.INVISIBLE);
                addFriendButton.setText("Send request");

                addFriendButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        firestoreHelper.sendFriendRequest(
                                currentUser,
                                UserSingleton.getInstance(getApplicationContext()).getID(),
                                userId,
                                new FirestoreHelper.FirestoreHelperListener() {
                                    @Override
                                    public void success() {
                                        finish();
                                    }

                                    @Override
                                    public void failure() {

                                    }
                                }
                        );
                    }
                });
            }

            removeFriendButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    firestoreHelper.removeFriend(
                            currentUser,
                            UserSingleton.getInstance(getApplicationContext()).getID(),
                            profileUser,
                            userId,
                            new FirestoreHelper.FirestoreHelperListener() {
                                @Override
                                public void success() {
                                    finish();
                                }

                                @Override
                                public void failure() {

                                }
                            }
                    );
                }
            });
        }
    }
}
