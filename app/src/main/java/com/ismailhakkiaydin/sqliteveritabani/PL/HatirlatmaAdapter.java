package com.ismailhakkiaydin.sqliteveritabani.PL;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ismailhakkiaydin.sqliteveritabani.DAL.Entities.EntityBase;
import com.ismailhakkiaydin.sqliteveritabani.DAL.Entities.HatirlatmaEntity;

import java.util.Vector;

public class HatirlatmaAdapter extends BaseAdapter {

    private Context context;
    private Vector<EntityBase> model;

    public HatirlatmaAdapter(Context context, Vector<EntityBase> model){
        this.context = context;
        this.model = model;
    }

    @Override
    public int getCount(){
        return model.size();
    }

    @Override
    public Object getItem(int position){
        return model.get(position);
    }

    @Override
    public long getItemId(int position){
        return ((HatirlatmaEntity)getItem(position)).getID();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        HatirlatmaEntity ke = (HatirlatmaEntity) getItem(position);
        TextView tID = new TextView(context);
        tID.setHeight(90);
        tID.setText(String.valueOf(ke.getID()));

        return tID;
    }

}
