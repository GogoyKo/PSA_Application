package com.example.kugangka.myapplication;


import android.content.Context;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class PopulationListAdapter extends BaseAdapter {

    private Context mContext;
    private List<Population_Gridview_Items> mPopulationGridviewItemsList;


    //Constructor

    public PopulationListAdapter(Context mContext, List<Population_Gridview_Items> mPopulationGridviewItemsList) {
        this.mContext = mContext;
        this.mPopulationGridviewItemsList = mPopulationGridviewItemsList;
    }

    @Override
    public int getCount() {
        return mPopulationGridviewItemsList.size();
    }

    @Override
    public Object getItem(int position) {
        return mPopulationGridviewItemsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        View v = View.inflate(mContext, R.layout.population_gridview_items, null);

       // Button edit_btn = (Button)v.findViewById(R.id.edit_btn);
       // Button update_btn = (Button)v.findViewById(R.id.update_btn);
       // final Button date_btn = (Button)v.findViewById(R.id.p4_date);


        final TextView lastname = (TextView)v.findViewById(R.id.lastname);
        final TextView firstname = (TextView)v.findViewById(R.id.firstname);
        final TextView p2 = (TextView)v.findViewById(R.id.p2);
         TextView p3 = (TextView)v.findViewById(R.id.p3);
        ImageView pop_color = v.findViewById(R.id.pop_color);

        //Set text for TextView

        lastname.setText(mPopulationGridviewItemsList.get(position).lastname_p1());
        firstname.setText(mPopulationGridviewItemsList.get(position).firstname_p1());
        p2.setText(mPopulationGridviewItemsList.get(position).p2());
        p3.setText(mPopulationGridviewItemsList.get(position).p3());

        String test = p3.getText().toString().trim();
        if(test.equals("Female")){
            pop_color.setImageResource(R.drawable.girl);
        }else{
            pop_color.setImageResource(R.drawable.boy);
        }
        //Save population_gridview_items id to tag
        v.setTag(mPopulationGridviewItemsList.get(position).getId());

        return v;
    }
}

