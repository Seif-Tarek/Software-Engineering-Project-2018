package com.example.ahmed.cufe;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by ahmed on 3/6/2018.
 */

public class Deadline {

    private String Label, Description, Type, DueDate, DaysBefor, HoursBefore;

    Deadline(String FileName,Context cntx)
    {

        FileInputStream ReadFile = null;
        try {
            ReadFile = cntx.openFileInput(FileName);
            InputStreamReader Reader = new InputStreamReader(ReadFile);
            BufferedReader Readings_Buffer = new BufferedReader(Reader);

            this.setLabel(Readings_Buffer.readLine().toString());
            this.setDescription(Readings_Buffer.readLine().toString());
            this.setType(Readings_Buffer.readLine().toString());
            this.setDueDate(Readings_Buffer.readLine().toString());
            this.setDaysBefor(Readings_Buffer.readLine().toString());
            this.setHoursBefore(Readings_Buffer.readLine().toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();

            this.Invoke_Toast("Could not Create Deadline! File Not Found",cntx);
        } catch (IOException e) {
            e.printStackTrace();
            this.Invoke_Toast("Could not create Reminder! File Could not be read",cntx);
        }


    }

    private void Invoke_Toast(String ToastMessage,Context ctx){

        Toast Error_CreatingDeadline = Toast.makeText(ctx,ToastMessage,Toast.LENGTH_LONG);
        Error_CreatingDeadline.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        Error_CreatingDeadline.show();
    }

    public void setLabel(String LabelInput)
    {
        this.Label = LabelInput;
    }
    public String getLabel()
    {
        return this.Label;
    }

    public void setDescription(String DescriptionInput)
    {
        this.Description = DescriptionInput;
    }
    public String getDescription()
    {
        return this.Description;
    }

    public void setType(String TypeInput)
    {
        this.Type = TypeInput;
    }
    public String getType()
    {
        if( this.Type == "1") return "Quiz";
        else if(this.Type == "2") return "Project";
        else if(this.Type == "3") return "Assignment";
        else  return "Other..";

    }

    public void setDueDate(String DueDateInput)
    {
        this.DueDate = DueDateInput;
    }
    public String getDueDate()
    {
        return this.DueDate;
}

    public void setDaysBefor(String DaysBeforInput)
    {
        this.DaysBefor = DaysBeforInput;
    }
    public String getDaysBefor()
    {
        return this.DaysBefor;
    }

    public void setHoursBefore(String HoursBeforeInput)
    {
        this.HoursBefore = HoursBeforeInput;
    }
    public String getHoursBefore()
    {
        return this.HoursBefore;
    }


    public String GetBasicInfo()
    {
        String Label_Basic = this.Label;
        if( this.Label.length() > 20)
        {
            Label_Basic = this.Label.substring(0,19);
        }

        Label_Basic += "\n";
        Label_Basic += ("Due on "+this.DueDate);

        return Label_Basic;
    }

    public String getThumbnail()
    {
        if(this.Type == "1") return "@drawable/quiz";
        else if(this.Type == "2") return "@drawable/assignment";
        else if(this.Type == "3") return "@drawable/projectmanagemant";
        else return "@drawable/other";
    }

    public boolean beginNotifying(){return true;}
}
