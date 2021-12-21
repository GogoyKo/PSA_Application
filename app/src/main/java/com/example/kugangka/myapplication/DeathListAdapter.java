package com.example.kugangka.myapplication;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class DeathListAdapter extends BaseAdapter {

    private Context mContext;
    private List<Death_Gridview_Items> mDeathGridviewItemsList;

    //Constructor

    public DeathListAdapter(Context mContext, List<Death_Gridview_Items> mDeathGridviewItemsList) {
        this.mContext = mContext;
        this.mDeathGridviewItemsList = mDeathGridviewItemsList;
    }

    @Override
    public int getCount() {
        return mDeathGridviewItemsList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDeathGridviewItemsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      View v = View.inflate(mContext, R.layout.item_product_list, null);
         TextView tvName = (TextView)v.findViewById(R.id.lastname);
        TextView tvPrice = (TextView)v.findViewById(R.id.firstname);
        TextView tvDescription = (TextView)v.findViewById(R.id.p2);
        ImageView pop_color = v.findViewById(R.id.pop_color);
        //Set text for TextView
        tvName.setText(mDeathGridviewItemsList.get(position).firstname());
        tvPrice.setText(mDeathGridviewItemsList.get(position).lastname());
        tvDescription.setText(mDeathGridviewItemsList.get(position).gender());



        String test = tvDescription.getText().toString().trim();
        if(test.equals("Female")){
            pop_color.setImageResource(R.drawable.girl);
        }else{
            pop_color.setImageResource(R.drawable.boy);
        }



        //Save product id to tag
        v.setTag(mDeathGridviewItemsList.get(position).getId());

        return v;

    }
}
