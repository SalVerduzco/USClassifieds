package itp341.verduzco.salvador.usclassifieds;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            Bundle extras = getIntent().getExtras();
            String description = extras.getString("description");
            String location = extras.getString("location");
            String title = extras.getString("title");
            // int price = Integer.parseInt(extras.getString("price"));
            String seller = extras.getString("seller");

            ListView item_list = (ListView) findViewById(R.id.item_list);
            ArrayList<String> listItems = new ArrayList<>();
            listItems.add(description);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.item_view, R.id.textView, listItems);
            item_list.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        } catch(Exception e) {
            e.printStackTrace();
        }

        Button button = findViewById(R.id.send_buy);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, MainListActivity.class);
                startActivity(intent);
            }
        });

        Button loginbutton= findViewById(R.id.login);
        loginbutton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, LoginPage.class);
                startActivity(intent);
            }
        });
    }

    public void onClickAddItem(View view) {
        Intent k = new Intent(this, AddItem.class);
        startActivity(k);
    }
}
