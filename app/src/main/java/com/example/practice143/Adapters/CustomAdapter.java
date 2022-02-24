package com.example.practice143.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.practice143.Models.Person;
import com.example.practice143.R;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    Context context;
    ArrayList<Person> data = new ArrayList<>();
    LayoutInflater inflater = null;
    Person tempValues = null;

    public CustomAdapter(Context context, ArrayList<Person> data) {
        this.context = context;
        this.data = data;

        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        if (data.size() <= 0)
            return 1;
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder {
        public TextView txtName;
        public TextView txtEmail;
        public TextView txtMobile;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View view = convertView;
        ViewHolder holder;

        if (convertView == null) {
            view = inflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder();

            holder.txtName = view.findViewById(R.id.txtName);
            holder.txtEmail = view.findViewById(R.id.txtEmail);
            holder.txtMobile = view.findViewById(R.id.txtMobile);

            view.setTag(holder);
        } else
            holder = (ViewHolder) view.getTag();




        if (data.size() == 0){
            holder.txtName.setText("No Data");
        } else {
            tempValues = data.get(position);
            holder.txtName.setText(tempValues.getName());
            holder.txtEmail.setText(tempValues.getEmail());
            holder.txtMobile.setText(tempValues.getPhone().getMobile());
        }

        return view;
    }
}
