package com.example.master12021hunaultfil_rouge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

public class AddTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_task_activity);
    }
    public void on_click(View view){
        if(view.getId()==R.id.boutonAjouter){
            EditText rentrer=(EditText)findViewById(R.id.text);
            RadioButton r1=(RadioButton)findViewById(R.id.boutonBasse);
            RadioButton r2=(RadioButton)findViewById(R.id.boutonMoyen);
            RadioButton r3=(RadioButton)findViewById(R.id.boutonHaute);
            int priorite=0;
            if(r3.isChecked())priorite=1;
            if(r2.isChecked())priorite=2;
            if(r1.isChecked())priorite=3;
            String tache=rentrer.getText().toString();
            if(priorite!=0 && !tache.equals("")) {

                Intent intent=new Intent();
                intent.putExtra("TACHE",tache);
                intent.putExtra("PRIORITE",priorite);
                setResult(RESULT_OK,intent);
                finish();
            }
        }
    }
    public void launch_preferences(View view){
        Intent start=new Intent (this,SettingsActivity.class);
        startActivity(start);
    }


}