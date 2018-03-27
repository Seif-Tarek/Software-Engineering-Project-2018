package com.example.ahmed.cufe;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.List;


public class Deadlines_Track extends AppCompatActivity {


   private ShimmerFrameLayout mShimmerViewContainer;

    private RecyclerView recyclerView;
    private List<Deadline> cartList;
    private deadlinelist_adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deadlines__track);

        mShimmerViewContainer = findViewById(R.id.shimmer_view_container);

        recyclerView = findViewById(R.id.recycler_view);
        cartList = new ArrayList<>();
        mAdapter = new deadlinelist_adapter(this, cartList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16));
        recyclerView.setAdapter(mAdapter);

        // making http call and fetching menu json
        fetchRecipes();

        //our reference Layout:
        //GridLayout ExistingLayout = (GridLayout) findViewById(R.id.gridLayout3) ;





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

    public void ReadAllDeadlines(ReadDeadlines Reader)
    {


        if(Reader.isEmpty() == false) {
           // TextView emptyDeadlines_Text = (TextView) findViewById(R.id.NoDeadlines_Txt);
            //emptyDeadlines_Text.setVisibility(View.GONE);
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        mShimmerViewContainer.startShimmerAnimation();
    }

    @Override
    public void onPause() {
        mShimmerViewContainer.stopShimmerAnimation();
        super.onPause();
    }

    private void fetchRecipes()
    {
        //load all:
        try{
            ReadDeadlines Reader = new ReadDeadlines(getApplicationContext(), this.cartList);
             //First is to read all Deadlines;

            //Create at least one text View:


            //TextView MainFunctionality_Test = (TextView) findViewById(R.id.textView3);
            //MainFunctionality_Test.setText(Reader.getNextLabel());

        }
        catch (Exception e)
        {
            e.printStackTrace();
            Toast couldnot = Toast.makeText(getApplicationContext(),"Could not read deadlines", Toast.LENGTH_LONG);
            couldnot.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            couldnot.show();
        }

        // refreshing recycler view
        mAdapter.notifyDataSetChanged();

        // stop animating Shimmer and hide the layout
        mShimmerViewContainer.stopShimmerAnimation();
        mShimmerViewContainer.setVisibility(View.GONE);
    }
}