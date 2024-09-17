package com.example.login.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.example.login.model.User;

import java.util.List;

public class UserAdapter extends ArrayAdapter<User> {
private Context context;
private List<User> users;

    public UserAdapter(@NonNull Context context, int resource, @NonNull List<User> objects) {
        super(context, resource, objects);
    this.context= context;
        this.users = objects;
    }
   /* public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        LayoutInflater layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView=layoutInflater.inflate(R.layout.content_main,parent, false);
        TextView txtIdUser= (TextView) rowView.findViewById(R.id.IDuser) ;
        TextView txtfirstName= (TextView) rowView.findViewById(R.id.firstName) ;
        TextView txtLastName= (TextView) rowView.findViewById(R.id.lastName) ;

        txtIdUser.setText(String.format("ID:%s",users.get(position).getId()));
        txtfirstName.setText(String.format("Nombre:%s",users.get(position).getFirstName()));
        txtLastName.setText(String.format("Apellido:%s",users.get(position).getLastName()));
      return rowView;
    }
*/
}
