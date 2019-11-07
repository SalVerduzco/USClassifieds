package itp341.verduzco.salvador.usclassifieds;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UserSingleton.getInstance(getApplicationContext()).setID("TESTUSER");

        Button buy_button = findViewById(R.id.send_buy);
        buy_button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, MainListActivity.class);
                startActivity(intent);
            }
        });

        Button profile_button = findViewById(R.id.send_profile);
        profile_button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, UpdateProfile.class);
                startActivity(intent);
            }
        });

        Button login_button = findViewById(R.id.login);
        login_button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, LoginPage.class);
                startActivity(intent);
            }
        });

        Button button_selling = findViewById(R.id.button_selling);
        button_selling.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, ItemListActivity.class);
                intent.putExtra("option", "selling");
                startActivity(intent);
            }
        });

        Button button_sold = findViewById(R.id.button_sold);
        button_sold.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, ItemListActivity.class);
                intent.putExtra("option", "sold");
                startActivity(intent);
            }
        });

        Button button_friends = findViewById(R.id.button_friends);
        button_friends.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, FriendsActivity.class);
                startActivity(intent);
            }
        });
    }
}
