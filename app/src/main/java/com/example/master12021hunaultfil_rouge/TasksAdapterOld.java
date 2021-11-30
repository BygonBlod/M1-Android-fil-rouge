package com.example.master12021hunaultfil_rouge;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class TasksAdapterOld extends ArrayAdapter {
    private Context m_context;

    @NonNull
    @Override
    public android.view.View getView(int position, @Nullable android.view.View convertView, @NonNull ViewGroup parent) {
        TAskPrioriteItem Item= (TAskPrioriteItem) getItem(position);
        View racine =convertView;
        if(racine==null){
            LayoutInflater layout_inflater=LayoutInflater.from(m_context);
            racine=layout_inflater.inflate(R.layout.line,parent,false);
        }

        TextView task=(TextView) racine.findViewById(R.id.task);
        TextView priorite=(TextView)racine.findViewById(R.id.imagePriorite);

        task.setText(Item.task);
        System.out.println(Item.priorite);
        int prioriteInt=Integer.parseInt(Item.priorite);
        if(prioriteInt==1) {
            System.out.println("rouge");
            priorite.setBackgroundResource(R.color.rouge);
        }
        if(prioriteInt==2) {
            System.out.println("orange");
            priorite.setBackgroundResource(R.color.orange);
        }
        if(prioriteInt==3){
                System.out.println("jaune");
                priorite.setBackgroundResource(R.color.jaune);
        }

        return racine;
    }

    public void ajoute(String priorite,String task){
        TAskPrioriteItem item=new TAskPrioriteItem(priorite,task);
        add(item);
    }

    public TasksAdapterOld(@NonNull Context context) {
        super(context, R.layout.line);
        m_context=context;
    }






}
