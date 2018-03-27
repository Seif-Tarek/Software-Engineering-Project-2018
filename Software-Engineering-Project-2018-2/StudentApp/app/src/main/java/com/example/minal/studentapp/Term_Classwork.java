package com.example.minal.studentapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class Term_Classwork extends AppCompatActivity {

    String TAG = "Response";
    String ID = "";
    String Password = "";
    String Term_Classwork_invoke = ID+","+Password+",5";
    SoapPrimitive resultString;

    String data = "";
    String dataParsed_subject = "";
    String singleParsed_subject = "";
    String dataParsed_midterm = "";
    String singleParsed_midterm = "";
    String dataParsed_dailywork = "";
    String singleParsed_dailywork = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term__classwork);
        AsyncCallWS task = new AsyncCallWS();
        task.execute();
    }

    private class AsyncCallWS extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            Log.i(TAG, "onPreExecute");
        }

        @Override
        protected Void doInBackground(Void... params) {
            Log.i(TAG, "doInBackground");
            Get_Grades();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            Log.i(TAG, "onPostExecute");
            TextView tv1 = (TextView) findViewById(R.id.text1);
            tv1.setText(dataParsed_subject);
            TextView tv2 = (TextView) findViewById(R.id.text2);
            tv2.setText(dataParsed_midterm);
            TextView tv3 = (TextView) findViewById(R.id.text3);
            tv3.setText(dataParsed_dailywork);
        }


            /*
            URL url = new URL("https://api.myjson.com/bins/j5f6b");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while(line != null){
                line = bufferedReader.readLine();
                data = data + line;
            }
            */


    public void Get_Grades() {
        String SOAP_ACTION = "http://tempuri.org/GetData";
        String METHOD_NAME = "GetData";
        String NAMESPACE = "http://tempuri.org/";
        String URL = "http://chws.eng.cu.edu.eg/webservice1.asmx";


        try {
            SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);
            Request.addProperty("Params_CommaSeparated", Term_Classwork_invoke);

            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.dotNet = true;
            soapEnvelope.setOutputSoapObject(Request);

            HttpTransportSE transport = new HttpTransportSE(URL);

            transport.call(SOAP_ACTION, soapEnvelope);
            resultString = (SoapPrimitive) soapEnvelope.getResponse();

            Log.i(TAG, "Grades: " + resultString);
        } catch (Exception ex) {
            Log.e(TAG, "Error: " + ex.getMessage());
        }

        try{
            data = resultString.toString();
            JSONObject JBO = new JSONObject(data);
            JSONArray JA = (JSONArray) JBO.get("Term_Classwork");
            //JSONArray JA = new JSONArray(data);
            for (int i = 0; i < JA.length(); i++) {
                JSONObject JO = (JSONObject) JA.get(i);
                singleParsed_subject = JO.get("Subject_Name") + "";
                singleParsed_midterm = JO.get("Midterm") + "";
                singleParsed_dailywork = JO.get("DailyWork") + "";

                dataParsed_subject = dataParsed_subject + singleParsed_subject + "\n";
                dataParsed_midterm = dataParsed_midterm + singleParsed_midterm + "\n";
                dataParsed_dailywork = dataParsed_dailywork + singleParsed_dailywork + "\n";


            }

        } catch(
                JSONException e)

        {
            e.printStackTrace();
        }


    }

            }

}
