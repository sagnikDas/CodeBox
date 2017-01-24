package com.example.android.codebox;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by sagnik on 30/12/16.
 */

public class ListDataAdapter extends ArrayAdapter {



    List<Post> list = new ArrayList<>();
    public ListDataAdapter(Context context, int resource) {
        super(context, resource);

    }

    static class LayoutHandler{

        TextView WEB, USER, PSWD;
    }

    @Override
    public void add(Object object){
        super.add(object);
        list.add((Post) object);
    }

    @Override
    public int getCount(){
        return list.size();
    }


    @Override
    public Object getItem(int position){
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        View row = convertView;
        LayoutHandler layoutHandler;
        if(row == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout, parent, false);
            layoutHandler = new LayoutHandler();
            layoutHandler.WEB = (TextView) row.findViewById(R.id.title);
            layoutHandler.USER = (TextView) row.findViewById(R.id.subtitle);
            layoutHandler.PSWD = (TextView) row.findViewById(R.id.subtitle2);

            row.setTag(layoutHandler);
        }
        else
        {

            layoutHandler = (LayoutHandler) row.getTag();


        }
        Post post = (Post) this.getItem(position);
        layoutHandler.WEB.setText(post.getPostTitle());
        layoutHandler.USER.setText(post.getPostSubTitle());
        layoutHandler.PSWD.setText(post.getPostSubTitle2());

        return row;
    }


    public void filter(String charText) {

        charText = charText.toLowerCase(Locale.getDefault());

        list.clear();// slearing any pre-existing list

        if (charText.length() == 0) {
        //    list.addAll(list);

        } else {
            for (Post postDetail : list) {

                if (charText.length() != 0 && postDetail.getPostTitle().toLowerCase(Locale.getDefault()).contains(charText)) {
                    list.add(postDetail);
                } else if (charText.length() != 0 && postDetail.getPostSubTitle().toLowerCase(Locale.getDefault()).contains(charText)) {
                    list.add(postDetail);
                }
            }
        }
        notifyDataSetChanged();
    }

}
