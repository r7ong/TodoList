package com.rtong.listit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by rtong on 10/2/15.
 */
public class ItemAdapter extends ArrayAdapter<Item> {
    ItemAdapter(Context context, ArrayList<Item> todoItems) {
        super(context, R.layout.item, todoItems);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.item, parent, false);
        String content = getItem(position).getContent();
        TextView tvContent = (TextView) view.findViewById(R.id.tvContent);
        TextView tvPriority = (TextView) view.findViewById(R.id.tvPriority);
        tvContent.setText(content);
        tvPriority.setText(content);
        return view;
    }
}
