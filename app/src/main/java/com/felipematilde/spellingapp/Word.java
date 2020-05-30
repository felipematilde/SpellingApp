package com.felipematilde.spellingapp;

import java.util.ArrayList;
import java.util.List;

public class Word implements Comparable<Word> {
    private String correctSpelling;
    private List<String> wrongSpellingList = new ArrayList<>();
    private int nErrors, nTrys;

    Word(String correctSpelling){
        this.correctSpelling = correctSpelling.toLowerCase();
        this.nErrors = 0;
        this.nTrys = 0;
    }

    public int compareTo(Word word){
        return word.nErrors-this.nErrors;
    }

    public void addSpelling(String spelling){
        spelling = spelling.toLowerCase();
        this.nTrys++;
        if(!this.correctSpelling.equals(spelling)){
            if (!this.wrongSpellingList.contains(spelling)) {
                this.wrongSpellingList.add(spelling);
            }
            this.nErrors++;
        }
    }

    public int getErrors(){
        return this.nErrors;
    }

    public int getTrys(){
        return this.nTrys;
    }

    public String getCorrectSpelling(){
        return this.correctSpelling;
    }

    public List<String> getWrongSpellingList(){
        return this.wrongSpellingList;
    }

    public String getRandomSpelling(){
        if(2*Math.random()-1>0){
            return this.correctSpelling;
        }else{
            int i = (int) Math.round(Math.random()*(this.getWrongSpellingList().size()-1));
            return this.getWrongSpellingList().get(i);
        }
    }
}
