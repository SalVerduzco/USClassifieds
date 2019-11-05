package itp341.verduzco.salvador.usclassifieds;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainListActivity extends AppCompatActivity {

    public static final String TAG = MainListActivity.class.getSimpleName();

    private Button buttonAdd;
    private ListView listView;
    public String category = "all";

    //TODO create array and adapter

    //    private ArrayAdapter<Item> arrayAdapter;
    private ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_list_activity);
        Log.d(TAG, "onCreate");

        //find views
        buttonAdd = (Button) findViewById(R.id.button_add);
        listView = (ListView) findViewById(R.id.listView);

        List<Item> items = ItemSingleton.get(this).getItems();

        itemAdapter = new ItemAdapter(this, items);
        listView.setAdapter(itemAdapter);

        //TODO What happens when user clicks add?
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "mButtonadd: onClick ");
                Intent intent = new Intent(MainListActivity.this, DetailActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        //TODO create listview item click listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Log.d(TAG, "listView: onListItemClick");
                Intent intent = new Intent(MainListActivity.this, DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_POSITION, position);
                startActivityForResult(intent, 0);
            }
        });


    }

    //TODO finish onActivityResult
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d(TAG, "onActivityResult: requestCode: " + requestCode);

        itemAdapter.notifyDataSetChanged();

    }


    private class ItemAdapter extends ArrayAdapter<Item>{
        private List<Item> items;

        public ItemAdapter(Context context, List<Item> items){
            super(context, R.layout.list_item_shop3, items);
            this.items = items;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_shop3, parent, false);
            }

            TextView textTitle = convertView.findViewById(R.id.text_title);
            TextView textSubtitle = convertView.findViewById(R.id.text_subtitle);

            Item item = items.get(position);

            textTitle.setText(item.getSeller());
            textSubtitle.setText(item.getDescription());

            return convertView;
        }
    }

    public void LoadData(){

        /* BASIL -> QUERY FOR ALL ITEMS */
        //ItemSingleton set list to query

    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        String old_category = category;

        switch(view.getId()) {
            case R.id.radio_books:
                if (checked){
                    category = "books";
                }
                    break;
            case R.id.radio_electronics:
                if (checked){
                    category = "electronics";
                }
                    break;
            case R.id.radio_furniture:
                if (checked){
                    category = "furniture";
                }
                    break;
            case R.id.radio_clothing:
                if (checked){
                    category = "clothing";
                }
                    break;
        }

        if(!old_category.equalsIgnoreCase(category)){

            /* BASIL -> QUERY ITEMS BY CATEGORY */
            ItemSingleton.get(this).parse(category);
            itemAdapter.notifyDataSetChanged();
        }
    }


}


