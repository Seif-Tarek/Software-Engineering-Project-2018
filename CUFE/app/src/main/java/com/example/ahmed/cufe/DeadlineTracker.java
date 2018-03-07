package com.example.ahmed.cufe;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class DeadlineTracker extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deadline_tracker);

        FloatingActionButton Add_Deadline_Invoke = (FloatingActionButton) findViewById(R.id.NewDeadline_floatingActionButton);
        Add_Deadline_Invoke.setOnClickListener(
                new FloatingActionButton.OnClickListener(){
                    public void onClick(View v){
                        Intent To_NewDeadlines = new Intent("com.example.ahmed.cufe.NewDeadline");
                        startActivity(To_NewDeadlines);
                    }
                }

        );
    }
}
