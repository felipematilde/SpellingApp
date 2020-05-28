package com.felipematilde.spellingapp.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.felipematilde.spellingapp.KeyboardHelper;
import com.felipematilde.spellingapp.R;
import com.felipematilde.spellingapp.Vocabulary;

import org.w3c.dom.Text;

public class PracticeActivity extends JsonHandler {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);

        recoverFromJson();
        setRandomWord();
        setSubmissionAndNext();
    }

    public void setSubmissionAndNext(){
        final Button correctButton = (Button) findViewById(R.id.correctButton);
        final Button wrongButton = (Button) findViewById(R.id.wrongButton);
        final Button submitButton = (Button) findViewById(R.id.submitButton);
        final Button nextButton = (Button) findViewById(R.id.nextButton);
        final TextView spellingTextView = (TextView) findViewById(R.id.spellingTextView);
        final TextView spellingAlert = (TextView) findViewById(R.id.spellingAlert);

        correctButton.setEnabled(true);
        correctButton.setSelected(false);
        wrongButton.setEnabled(true);
        wrongButton.setSelected(false);
        spellingTextView.setText("");
        spellingTextView.setEnabled(false);
        submitButton.setEnabled(false);
        nextButton.setEnabled(false);

        correctButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                correctButton.setSelected(true);
                wrongButton.setSelected(false);
                spellingTextView.setText("");
                spellingTextView.setEnabled(false);
                submitButton.setEnabled(true);
            }
        });

        wrongButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                correctButton.setSelected(false);
                wrongButton.setSelected(true);
                spellingTextView.setText("");
                spellingTextView.setEnabled(true);
                submitButton.setEnabled(true);
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(spellingTextView.getText().toString().equals("") && wrongButton.isSelected()) {
                    spellingAlert.setText("Please, enter a spelling");
                }else{
                    correctButton.setEnabled(false);
                    wrongButton.setEnabled(false);
                    spellingTextView.setEnabled(false);
                    spellingAlert.setText("Submited!");
                    submitButton.setEnabled(false);
                    KeyboardHelper.HideKeyboard(PracticeActivity.this,v);
                    nextButton.setEnabled(true);
                }
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                setRandomWord();
                correctButton.setEnabled(true);
                correctButton.setSelected(false);
                wrongButton.setEnabled(true);
                wrongButton.setSelected(false);
                spellingTextView.setText("");
                spellingTextView.setEnabled(false);
                submitButton.setEnabled(false);
                spellingAlert.setText("");
                nextButton.setEnabled(false);
            }
        });
    }

    public void setRandomWord(){
        TextView sampleTextView = (TextView) findViewById(R.id.sampleTextView);
        sampleTextView.setText("Hello World");
        String str = vocabulary.getWord().getCorrectSpelling();
        str = str.substring(0,1).toUpperCase()+str.substring(1).toLowerCase();
        sampleTextView.setText(str);
    }
}
