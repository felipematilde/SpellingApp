package com.felipematilde.spellingapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Vocabulary {
    private List<Word> list;

    Vocabulary(){
        list = new ArrayList<>();
    }

    public void addWord(String correctSpelling){
        Word word = new Word(correctSpelling.toLowerCase());
        this.list.add(word);
    }

    public Word getWord(String correctSpelling){
        correctSpelling = correctSpelling.toLowerCase();
        for(Word word:this.list){
            if(word.getCorrectSpelling().equals((correctSpelling))){
                return word;
            }
        }
        return null;
    }

    public Word getWord(){
        int i = (int) Math.round(Math.random()*(this.list.size()-1));
        if(this.list.size() == 0){
            return null;
        }
        return this.list.get(i);
    }

    public boolean contains(String spelling){
        spelling = spelling.toLowerCase();
        for(Word word:this.list){
            if(word.getCorrectSpelling().equals(spelling)){
                return true;
            }
        }
        return false;
    }

    public List<Word> getList(){
        return list;
    }

    public void sortListByError(){
        Collections.sort(this.list);
    }
}
