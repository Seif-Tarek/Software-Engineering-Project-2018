package com.example.ahmed.cufe;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.support.v7.widget.CardView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import android.util.Log;
public class NewDeadline extends AppCompatActivity {

    private static final String TAG = "Deadline Tags";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_deadline);
        Log.i(TAG,"NewDeadline OnCreate is invoked");


        CardView NewDeadline_Invoke = findViewById(R.id.Add_Card);


        NewDeadline_Invoke.setOnClickListener(
                new CardView.OnClickListener(){
                    public void onClick(View v){
                        Log.i(TAG,"Listener is invoked");

                        CreateDeadline();
                        //Shift Back to Deadlines Viewer:
                        Intent To_Deadlines = new Intent("com.example.ahmed.cufe.Deadlines_Track");
                        startActivity(To_Deadlines);
                    }
                }

        );
    }

    public void CreateDeadline()
    {
        // Instantiating varables for all existing Data:
        EditText DeadlineLabel_Text = (EditText) findViewById(R.id.Label_Text);
        MultiAutoCompleteTextView DeadlineDesc_Text  = (MultiAutoCompleteTextView) findViewById(R.id.Desc_Txt);
        RadioButton Radiobutton_Quiz = (RadioButton) findViewById(R.id.Quiz_RadioButton);
        RadioButton Radiobutton_Project = (RadioButton) findViewById(R.id.ProjectPhase_RadioButton);
        RadioButton Radiobutton_Ass  = (RadioButton) findViewById(R.id.Assignment_RadioButton);
        DatePicker DeadlineDueDate_Text = (DatePicker) findViewById(R.id.datePicker);
        EditText DeadlineDaysToRemind_Text = (EditText) findViewById(R.id.ElapsedDays_Text);
        EditText DeadlineHoursToRemind_Text = (EditText) findViewById(R.id.ElapsedHours_Text);

        //Reading Values:
        Deadline_Write newDeadlineFile = new Deadline_Write(DeadlineLabel_Text.getText().toString(), getApplicationContext());

        newDeadlineFile.addDeadlineLabel(DeadlineLabel_Text.getText().toString());
        newDeadlineFile.addDeadlineDescription(DeadlineDesc_Text.getText().toString());

        if( Radiobutton_Quiz.isChecked()) newDeadlineFile.addDeadlineType("Quiz");
        else if( Radiobutton_Project.isChecked()) newDeadlineFile.addDeadlineType("Project");
        else if( Radiobutton_Ass.isChecked()) newDeadlineFile.addDeadlineType("Assignment");
        else newDeadlineFile.addDeadlineType("Other");

        newDeadlineFile.addDeadlineDueDate(checkDigit(DeadlineDueDate_Text.getDayOfMonth())+"/"+checkDigit(DeadlineDueDate_Text.getMonth()+1)+"/"+DeadlineDueDate_Text.getYear());
        newDeadlineFile.addDeadlineTimeElapsed(DeadlineDaysToRemind_Text.getText().toString(),DeadlineHoursToRemind_Text.getText().toString());
        newDeadlineFile.CloseFile();

    }
    public String checkDigit(int number)
    {
        return number<=9?"0"+number:String.valueOf(number);
    }
}




