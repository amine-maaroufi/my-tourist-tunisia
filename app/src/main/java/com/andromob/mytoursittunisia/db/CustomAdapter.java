package com.andromob.mytoursittunisia.db;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.andromob.mytoursittunisia.R;
import com.andromob.mytoursittunisia.pojo.Contact;

import java.util.ArrayList;

public class CustomAdapter  extends BaseAdapter{

    private Context context;
    private ArrayList<Contact> contactArrayList;

    public CustomAdapter(Context context, ArrayList<Contact> contactArrayList) {

        this.context = context;
        this.contactArrayList = contactArrayList;
    }


    @Override
    public int getCount() {
        return contactArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return contactArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.lv_item, null, true);

            holder.tvname = (TextView) convertView.findViewById(R.id.name);
            holder.tvtel = (TextView) convertView.findViewById(R.id.tel);

            convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)convertView.getTag();
        }

        holder.tvname.setText("Name: "+contactArrayList.get(position).getName());
        holder.tvtel.setText("Tel: "+contactArrayList.get(position).getTel());

        return convertView;


    }

    private class ViewHolder{

         TextView tvname, tvtel;
         Button btndelete;

    }
}
