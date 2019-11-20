package itp341.verduzco.salvador.usclassifieds;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;


public class UpdateProfile extends AppCompatActivity {
    Button updateButton;
    Button mainPageButton;
    TextView name;
    TextView email;
    TextView location;
    TextView number;
    EditText locationEdit;
    EditText numberEdit;
    private FirestoreHelper firestoreHelper;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        firestoreHelper = new FirestoreHelper();

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        location = findViewById(R.id.location);
        number = findViewById(R.id.number);
        locationEdit = findViewById(R.id.edit_location);
        numberEdit = findViewById(R.id.edit_number);

        firestoreHelper
                .getUserByUserIdRef(UserSingleton.getInstance(getApplicationContext()).getID())
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        user = documentSnapshot.toObject(User.class);
                        name.setText(user.getName());
                        email.setText("Email: " + user.getEmail());
                        location.setText("Location: " + user.getLocation());
                        number.setText("Phone: " + user.getPhone());
                    }
                });

    }
    public void onClickReturntoMain(View view) {
        mainPageButton = (Button)findViewById(R.id.button_MainPage);
        mainPageButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(UpdateProfile.this, MainListActivity.class);
                startActivity(intent);
            }
        });
    }

    public void onClickAddUser(View view) {
        updateButton = (Button)findViewById(R.id.button_Update);
        updateButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                String newLocation = locationEdit.getText().toString();
                String newPhone = numberEdit.getText().toString();
                user.setLocation(newLocation);
                user.setPhone(newPhone);

                Intent intent = new Intent(UpdateProfile.this, MainListActivity.class);
                startActivity(intent);
            }
        });
    }
}
