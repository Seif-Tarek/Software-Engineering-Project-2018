package com.example.minal.studentapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;

import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import android.content.Intent;
import android.view.View;

import com.example.minal.studentapp.R;


public class LoginActivity extends AppCompatActivity {

    /*private EditText ID_text = findViewById(R.id.ID_Text);
    private EditText Pass_text = findViewById(R.id.Password_Text);
    private CardView Login = findViewById(R.id.Login_Card);*/

    private String username,password;
    private CardView ok;
    private EditText editTextID,editTextPassword;
    private CheckBox saveLoginCheckBox;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private Boolean saveLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_login);

        CardView Login = findViewById(R.id.Login_Card);
        Login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                login();
            }
        });

        editTextID = findViewById(R.id.ID_Text);
        editTextPassword = findViewById(R.id.Password_Text);
        saveLoginCheckBox = findViewById(R.id.RememberMecheckBox);
        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();

        saveLogin = loginPreferences.getBoolean("saveLogin", false);
        if (saveLogin == true) {
            editTextID.setText(loginPreferences.getString("username", ""));
            editTextPassword.setText(loginPreferences.getString("password", ""));
            saveLoginCheckBox.setChecked(true);
        }


    }


    public void login() {

        if (!validate()) {
            onLoginFailed();
            return;
        }

        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editTextID.getWindowToken(), 0);

        username = editTextID.getText().toString();
        password = editTextPassword.getText().toString();

        if (saveLoginCheckBox.isChecked()) {
            loginPrefsEditor.putBoolean("saveLogin", true);
            loginPrefsEditor.putString("username", username);
            loginPrefsEditor.putString("password", password);
            loginPrefsEditor.commit();
        } else {
            loginPrefsEditor.clear();
            loginPrefsEditor.commit();
        }

        CardView Login = findViewById(R.id.Login_Card);
        EditText ID_text = findViewById(R.id.ID_Text);
        EditText Pass_text = findViewById(R.id.Password_Text);
        Login.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        int ID = Integer.parseInt(ID_text.getText().toString());
        String password = Pass_text.getText().toString();

        // TODO: Implement your own authentication logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 500);
    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        CardView Login = findViewById(R.id.Login_Card);
        Login.setEnabled(true);
        Intent IntentActivity2 = new Intent(this,NavDrawerActivity.class);
        startActivity(IntentActivity2);
        finish();
    }

    public void onLoginFailed() {
        CardView Login = findViewById(R.id.Login_Card);
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
        Login.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        EditText ID_text = findViewById(R.id.ID_Text);
        EditText Pass_text = findViewById(R.id.Password_Text);


        String ID = (ID_text.getText().toString());
        String password = Pass_text.getText().toString();

        if (ID.isEmpty()) {
            ID_text.setError("Enter a valid ID");
            valid = false;
        } else {
            ID_text.setError(null);
        }

        if (password.isEmpty()) {
            Pass_text.setError("Enter a valid password");
            valid = false;
        } else {
            Pass_text.setError(null);
        }

        return valid;
    }

}


