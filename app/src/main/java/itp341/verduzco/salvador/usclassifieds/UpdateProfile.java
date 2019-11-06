package itp341.verduzco.salvador.usclassifieds;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;


import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import java.util.HashMap;
import java.util.Map;
import java.util.*;
import android.os.Bundle;
import android.widget.TextView;

public class UpdateProfile extends AppCompatActivity {
    Button updateButton;
    Button mainPageButton;
    TextView name;
    TextView email;
    EditText locationEdit;
    EditText numberEdit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        locationEdit = findViewById(R.id.edit_location);
        numberEdit = findViewById(R.id.edit_number);

        Button button = findViewById(R.id.button_MainPage);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                finish();
            }
        });
    }
    public void onClickAddUser(View view) {


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
