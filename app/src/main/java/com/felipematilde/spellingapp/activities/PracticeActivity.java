package com.felipematilde.spellingapp.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.felipematilde.spellingapp.KeyboardHelper;
import com.felipematilde.spellingapp.R;
import com.felipematilde.spellingapp.Vocabulary;
import com.felipematilde.spellingapp.Word;

import org.w3c.dom.Text;

public class PracticeActivity extends JsonHandler {
    Word word;

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
                    evaluateAnswer();
                    saveOnJson();
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

    public void evaluateAnswer(){
        TextView sampleTextView = (TextView) findViewById(R.id.sampleTextView);
        Button correctButton = (Button) findViewById(R.id.correctButton);
        TextView spellingTextView = (TextView) findViewById(R.id.spellingTextView);
        TextView spellingAlert = (TextView) findViewById(R.id.spellingAlert);
        String sample = sampleTextView.getText().toString();
        sample = sample.toLowerCase();
        String userSpelling = spellingTextView.getText().toString();
        userSpelling = userSpelling.toLowerCase();
        if((word.getCorrectSpelling().equals(sample) && correctButton.isSelected()) ||
                (!word.getCorrectSpelling().equals(sample) && word.getCorrectSpelling().equals(userSpelling))) {
            word.addSpelling(word.getCorrectSpelling());
            spellingAlert.setText("Good job!");
        }else{
            word.addSpelling(userSpelling);
            spellingAlert.setText("Sorry, the correct spelling is " + word.getCorrectSpelling());
        }
    }

    public void setRandomWord(){
        TextView sampleTextView = (TextView) findViewById(R.id.sampleTextView);
        this.word = vocabulary.getWord();
        String spelling = this.word.getRandomSpelling();
        spelling = spelling.substring(0,1).toUpperCase()+spelling.substring(1).toLowerCase();
        sampleTextView.setText(spelling);
    }
}
