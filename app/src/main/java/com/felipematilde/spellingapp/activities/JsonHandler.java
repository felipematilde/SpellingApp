package com.felipematilde.spellingapp.activities;

import android.support.v7.app.AppCompatActivity;

import com.felipematilde.spellingapp.Vocabulary;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public abstract class JsonHandler extends AppCompatActivity {
    protected Vocabulary vocabulary;
    protected String jsonFileName;

    JsonHandler(){
        this.jsonFileName = "/vocabulary.json";
    }

    public void saveOnJson(){
        Gson gson = new Gson();
        String json = gson.toJson(vocabulary);

        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(getFilesDir()+this.jsonFileName,false);
            fileWriter.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fileWriter != null){
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void recoverFromJson(){
        Gson gson = new Gson();
        File file = new File(getFilesDir()+this.jsonFileName);
        if(!file.exists()){
            createJson();
        }
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
            vocabulary = gson.fromJson(scanner.nextLine(),Vocabulary.class);
        } catch (IOException e) {
            e.printStackTrace();
            vocabulary = null;
        } finally {
            if(scanner!=null){
                scanner.close();
            }
        }
    }

    public void createJson(){
        File file = new File(getFilesDir()+this.jsonFileName);
        try {
            FileWriter fw = new FileWriter(file);
            fw.write("{}");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
