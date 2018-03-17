package com.example.minal.studentapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;

public class HomescreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);

        CardView Maps = findViewById(R.id.CampusmapCardId);
        Maps.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                OpenMaps();
            }
        });
    }

    public void OpenMaps()
    {
        Intent IntentMapsActivity = new Intent(this,MapsActivity.class);
        startActivity(IntentMapsActivity);
    }

}