package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText nameEditetext, emailEdittext;
    private Button save, show;
    private TextView showName, showEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEditetext = findViewById(R.id.name);
        emailEdittext = findViewById(R.id.email_address);
        save = findViewById(R.id.save);
        show = findViewById(R.id.show);
        showName = findViewById(R.id.show_name);
        showEmail = findViewById(R.id.show_email_address);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storeDataToSharedPreference();
            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDataFromSharedPreference();
            }
        });
    }

    private void storeDataToSharedPreference() {

        String name = nameEditetext.getText().toString();
        String email = emailEdittext.getText().toString();

        if (name.equals("") && email.equals("")){
            Toast.makeText(this, "please enter some data", Toast.LENGTH_SHORT).show();
        } else {
            SharedPreferences sharedPreferences = getSharedPreferences("DatabaseName", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("nameKey", name);
            editor.putString("emailKey", email);
            editor.commit();
            Toast.makeText(this, "data is stored successfully", Toast.LENGTH_SHORT).show();
        }
    }

    private void showDataFromSharedPreference() {

        SharedPreferences sharedPreferences = getSharedPreferences("DatabaseName", Context.MODE_PRIVATE);
        if (sharedPreferences.contains("nameKey") && sharedPreferences.contains("emailKey")){
            String name = sharedPreferences.getString("nameKey", "Data Not Found");
            String email = sharedPreferences.getString("emailKey", "Data Not Found");

            showName.setText(name);
            showEmail.setText(email);
        }
    }
}
