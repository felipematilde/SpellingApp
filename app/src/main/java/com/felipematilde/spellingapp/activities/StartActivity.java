package com.felipematilde.spellingapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.felipematilde.spellingapp.R;

public class StartActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        setTextContent();
        goToManagementActivity();
    }

    public void goToManagementActivity(){
        Button startButton = (Button) findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartActivity.this,MainActivity.class));
            }
        });
    }

    public void setTextContent(){
        TextView titleTextView = (TextView) findViewById(R.id.titleTextView);
        TextView subtitleTextView = (TextView) findViewById(R.id.subtitleTextView);
        TextView contentTextView = (TextView) findViewById(R.id.contentTextView);

        titleTextView.setText("Welcome to SpellingApp!");
        subtitleTextView.setText("How does this app work?");
        contentTextView.setText("You can add a mispelled word and its correct spelling so you can keep control of your common mistakes. By inserting the words you can practice spelling them over and over! The app also provides an analysis of how much erros and hits you have done. Try it!");
    }
}
