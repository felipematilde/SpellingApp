package com.felipematilde.spellingapp.activities;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.felipematilde.spellingapp.R;

import java.util.Collections;

public class AnalysisActivity extends JsonHandler {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);

        LinearLayout linearLayout = findViewById(R.id.linearLayoutAnalysis);
        recoverFromJson();
        Collections.sort(vocabulary.getList());

        for(int i =0;i<vocabulary.getList().size();i++){
            LinearLayout ll = new LinearLayout(this);
            ll.setOrientation(LinearLayout.VERTICAL);
            TextView textView = new TextView(this);
            textView.setText(i+1 + ") " + adjustUpperCase(vocabulary.getList().get(i).getCorrectSpelling()) +
                    " | Errors: " + vocabulary.getList().get(i).getErrors() +
                    " | Trys: " + vocabulary.getList().get(i).getTrys());
            ll.addView(textView);
            linearLayout.addView(ll);
        }
    }

    private String adjustUpperCase(String str){
        return str.substring(0,1).toUpperCase()+str.substring(1).toLowerCase();
    }
}
