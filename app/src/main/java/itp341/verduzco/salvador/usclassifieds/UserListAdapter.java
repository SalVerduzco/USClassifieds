package itp341.verduzco.salvador.usclassifieds;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class UserListAdapter extends ArrayAdapter<User> {
    private List<User> mUsers;

    public UserListAdapter(@NonNull Context context, List<User> users) {
        super(context, R.layout.user_list_item, users);
        this.mUsers = users;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.user_list_item, parent, false);
        }

        TextView textName = convertView.findViewById(R.id.user_list_name);

        String user = this.mUsers.get(position).getName();

        textName.setText(user);

        return convertView;
    }
}