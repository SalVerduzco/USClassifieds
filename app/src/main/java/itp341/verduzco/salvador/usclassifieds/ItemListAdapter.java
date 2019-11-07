package itp341.verduzco.salvador.usclassifieds;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;

import java.util.List;


public class ItemListAdapter extends ArrayAdapter<Item> {
    private List<Item> mItems;

    public ItemListAdapter(@NonNull Context context, List<Item> mItems) {
        super(context, R.layout.list_item_shop3, mItems);
        this.mItems = mItems;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_shop3, parent, false);
        }

        TextView textTitle = convertView.findViewById(R.id.text_title);
        TextView textSubtitle = convertView.findViewById(R.id.text_subtitle);

        Item item = this.mItems.get(position);

        textTitle.setText(item.getUserId());
        textSubtitle.setText(item.getDescription());

        return convertView;
    }
}