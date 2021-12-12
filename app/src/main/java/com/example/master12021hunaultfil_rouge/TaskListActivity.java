package com.example.master12021hunaultfil_rouge;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.master12021hunaultfil_rouge.BD.TaskSQLHelper;

import java.util.ArrayList;

public class TaskListActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {
    private RecyclerView taskList;
    private TasksAdapter adapter;
    private TaskSQLHelper bd;
    private TextView textPrio;
    static final int ACTIVITE_TACHE_RETOUR=1;
    private SharedPreferences app_prefs;
    private int textSize;
    private boolean cacher;

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.task_list_activity);
        textPrio=(TextView)findViewById(R.id.text_priorité);
        app_prefs = PreferenceManager.getDefaultSharedPreferences(this);
        bd=new TaskSQLHelper(getApplicationContext());
        taskList = (RecyclerView) findViewById(R.id.tasks_list2);
        textSize=Integer.parseInt(app_prefs.getString(getString(R.string.pref_setting_2_key),"20"));
        cacher=app_prefs.getBoolean(getString(R.string.pref_setting_1_key),false);
        if(cacher==true)textPrio.setText("");
        else textPrio.setText("Priorité");
        adapter = new TasksAdapter(this,bd.getCursor(),textSize,cacher);
        taskList.setAdapter(adapter);
        /*peut être utile si on sauvegarde d'autres données autre que dans la base
        if(state!=null){

        }
        else {

        }*/

        ItemTouchHelper.SimpleCallback touch_helper = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position=(int)viewHolder.itemView.getTag();
                TAskPrioriteItem itemSup=adapter.getItem(position);
                bd.DeleteTask(itemSup.priorite,itemSup.task);
                adapter.moveCursorDelete(bd.getCursor(),position);

            }
        };
        new ItemTouchHelper(touch_helper).attachToRecyclerView(taskList);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId=item.getItemId();
        switch(itemId){
            case R.id.menu_action_1:
                Intent intent=new Intent(TaskListActivity.this, AddTaskActivity.class);
                startActivityForResult(intent,ACTIVITE_TACHE_RETOUR);
                return true;
            case R.id.menu_action_2:
                Intent start=new Intent (this,SettingsActivity.class);
                startActivity(start);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==ACTIVITE_TACHE_RETOUR){
            if(resultCode==RESULT_OK){
                String tacheretour=data.getStringExtra("TACHE");
                int prioriteretour=data.getIntExtra("PRIORITE",0);
                bd.InsertTask(Integer.toString(prioriteretour),tacheretour);
                adapter.moveCursorInsert(bd.getCursor());
            }
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        textSize=Integer.parseInt(app_prefs.getString(getString(R.string.pref_setting_2_key),"20"));
        cacher=app_prefs.getBoolean(getString(R.string.pref_setting_1_key),false);
        adapter=new TasksAdapter(this,bd.getCursor(),textSize,cacher);
        taskList.setAdapter(adapter);
        if(cacher==true)textPrio.setText("");
        else textPrio.setText("Priorité");
    }

    @Override
    protected void onResume() {
        super.onResume();
        app_prefs.registerOnSharedPreferenceChangeListener(this);

    }
}