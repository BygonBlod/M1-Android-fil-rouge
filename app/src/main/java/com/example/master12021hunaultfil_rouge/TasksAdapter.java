package com.example.master12021hunaultfil_rouge;

import android.content.Context;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.master12021hunaultfil_rouge.BD.TaskContract;

import java.util.ArrayList;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.Tasks_ViewHolder> {
    private Context m_context;
    private Cursor curseur;
    //private ArrayList<TAskPrioriteItem> m_data;


    /*public void ajoute(String priorite, String task) {
        TAskPrioriteItem item=new TAskPrioriteItem(priorite,task);
        m_data.add(item);
        this.notifyItemInserted(m_data.size()-1);
    }
    public void ajouteAll(ArrayList<TAskPrioriteItem> tasks){
        for( TAskPrioriteItem i:tasks){
            ajoute(i.priorite,i.task);
        }
    }
    public void supprimer(int position){
        m_data.remove(position);
        this.notifyItemRemoved(position);
        this.notifyItemRangeChanged(position,getItemCount()-position);
    }*/

    public void moveCursorInsert(Cursor c){
        curseur=c;
        this.notifyItemInserted(getItemCount());
    }

    public void moveCursorDelete(Cursor c,int position){
        curseur=c;
        this.notifyItemRemoved(position);
        this.notifyItemRangeChanged(position,getItemCount()-position);
    }

    public class Tasks_ViewHolder extends RecyclerView.ViewHolder{
        private TextView priorite;
        private TextView task;
        public Tasks_ViewHolder(@NonNull View itemView) {
            super(itemView);
            priorite=(TextView)itemView.findViewById(R.id.imagePriorite);
            task=(TextView) itemView.findViewById(R.id.task);
        }
    }

    public TasksAdapter (Context context,Cursor c){
        m_context=context;
        curseur=c;
    }
    @NonNull
    @Override
    public Tasks_ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(m_context).inflate(R.layout.line,viewGroup,false);
        return new Tasks_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Tasks_ViewHolder tasks_viewHolder, int i) {
        curseur.moveToPosition(i);
        //TAskPrioriteItem item=m_data.get(i);
        tasks_viewHolder.task.setText(curseur.getString(curseur.getColumnIndexOrThrow(TaskContract.TaskEntry.COLUMN_TASK)));
        int prioriteInt=Integer.parseInt(curseur.getString(curseur.getColumnIndexOrThrow(TaskContract.TaskEntry.COLUMN_PRIORITE)));
        if(prioriteInt==1) {
            //System.out.println("rouge");
            tasks_viewHolder.priorite.setBackgroundResource(R.color.rouge);
        }
        if(prioriteInt==2) {
            //System.out.println("orange");
            tasks_viewHolder.priorite.setBackgroundResource(R.color.orange);
        }
        if(prioriteInt==3){
            //System.out.println("jaune");
            tasks_viewHolder.priorite.setBackgroundResource(R.color.jaune);
        }
        tasks_viewHolder.itemView.setTag(i);

    }

    @Override
    public int getItemCount() {
        return curseur.getCount();
    }
    public TAskPrioriteItem getItem(int position){
        curseur.moveToPosition(position);
        TAskPrioriteItem res=new TAskPrioriteItem(curseur.getString(curseur.getColumnIndexOrThrow(TaskContract.TaskEntry.COLUMN_PRIORITE)),
                curseur.getString(curseur.getColumnIndexOrThrow(TaskContract.TaskEntry.COLUMN_TASK)));
        return res;
    }
    public Cursor getCurseur(){
        return curseur;
    }
   /* public ArrayList<TAskPrioriteItem> getM_data() {
        return m_data;
    }

    public void setM_data(ArrayList<TAskPrioriteItem> m_data) {
        this.m_data = m_data;
    }*/

}
