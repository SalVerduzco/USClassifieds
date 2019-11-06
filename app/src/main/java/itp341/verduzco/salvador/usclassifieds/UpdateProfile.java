package itp341.verduzco.salvador.usclassifieds;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import java.util.HashMap;
import java.util.Map;
import java.util.*;
import android.os.Bundle;

public class UpdateProfile extends AppCompatActivity {
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    Button updateButton;
    Button mainPageButton;
    EditText nameEdit;
    EditText emailEdit;
    EditText phoneEdit;
    //this store all the users
    ArrayList<User> allUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        Button button = findViewById(R.id.button_MainPage);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                finish();
            }
        });
    }
    public void onClickAddUser(View view) {
        DatabaseReference myRef = database.getReference();
        updateButton = (Button)findViewById(R.id.button_Update);
        nameEdit   = (EditText)findViewById(R.id.editProfileName);
        emailEdit   = (EditText)findViewById(R.id.editProfileEmail);
        phoneEdit   = (EditText)findViewById(R.id.editProfilePhone);

        User newUser = new User(nameEdit.getText().toString(),emailEdit.getText().toString(), "",phoneEdit.getText().toString());
        allUsers.add(newUser);
        Map<String, Object> mymap = new HashMap<>();

        mymap.put(nameEdit.getText().toString(), newUser);
        myRef.child(nameEdit.getText().toString()).setValue(mymap);
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
}
