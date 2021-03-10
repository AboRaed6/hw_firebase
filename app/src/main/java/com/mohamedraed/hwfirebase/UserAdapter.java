package com.mohamedraed.hwfirebase;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class UserAdapter extends ArrayAdapter<User> {
    public UserAdapter(Context context, List<User> object){
        super(context,0, object);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView =  ((Activity)getContext()).getLayoutInflater().inflate(R.layout.item,parent,false);
        }

        TextView nameTextView = (TextView) convertView.findViewById(R.id.txt_name);
        TextView phoneTextView = (TextView) convertView.findViewById(R.id.txt_phone);
        TextView addressTextView = (TextView) convertView.findViewById(R.id.txt_address);

        User user = getItem(position);

        nameTextView.setText(user.getName());
        phoneTextView.setText(user.getPhone());
        addressTextView.setText(user.getAddress());


        return convertView;
    }

}