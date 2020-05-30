package com.felipematilde.spellingapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.felipematilde.spellingapp.KeyboardHelper;
import com.felipematilde.spellingapp.R;

public class MainActivity extends JsonHandler {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recoverFromJson();
        addSpellingToList();
        goToPractice();
        goToAnalysis();
    }

    public void addSpellingToList(){
        final TextView correctSpellingTxt = findViewById(R.id.correctSpellingTxt);
        final TextView wrongSpellingTxt = findViewById(R.id.wrongSpellingTxt);
        final Button addSpellingButton = findViewById(R.id.addSpellingButton);
        final TextView addSpellingAlert = findViewById(R.id.addSpellingAlert);

        correctSpellingTxt.setText("");
        wrongSpellingTxt.setText("");
        addSpellingAlert.setText("");

        addSpellingButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String correcSpelling = correctSpellingTxt.getText().toString();
                String wrongSpelling = wrongSpellingTxt.getText().toString();
                if(correcSpelling.equals(wrongSpelling)){
                    addSpellingAlert.setText("Both spellings are the same");
                }else{
                    if(vocabulary.contains(correcSpelling)){
                        vocabulary.getWord(correcSpelling).addSpelling(wrongSpelling);
                    }else{
                        vocabulary.addWord(correcSpelling);
                        vocabulary.getWord(correcSpelling).addSpelling(wrongSpelling);
                    }
                    saveOnJson();
                    correctSpellingTxt.setText("");
                    wrongSpellingTxt.setText("");
                    addSpellingAlert.setText("Spelling added to your list");
                }
                KeyboardHelper.HideKeyboard(MainActivity.this,v);
            }
        });
    }

    public void goToPractice(){
        Button practiceButton = (Button) findViewById(R.id.practiceButton);
        final TextView practiceAlert = (TextView) findViewById(R.id.practiceAlert);
        practiceButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                practiceAlert.setText("");
                if(vocabulary.getList().size()==0){
                    practiceAlert.setText("Empty list");
                }else{
                    startActivity(new Intent(MainActivity.this,PracticeActivity.class));
                }
            }
        });

    }

    public void goToAnalysis(){
        Button analysisButton = (Button) findViewById(R.id.analysisButton);
        analysisButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AnalysisActivity.class));
            }
        });
    }
}
