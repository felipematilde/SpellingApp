package com.felipematilde.spellingapp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WordTest {
    Word word;

    @Before
    public void setUp() throws Exception {
        word = new Word("banana");
    }

    @Test
    public void constructorTest(){
        assertTrue(word.getCorrectSpelling().equals("banana"));
    }

    @Test
    public void addSpellingTest_wrongSpelling(){
        int nErrors = word.getErrors();
        int nTrys = word.getTrys();
        word.addSpelling("Bananna");
        assertTrue(word.getWrongSpellingList().contains("bananna"));
        assertEquals(word.getErrors(),nErrors+1);
        assertEquals(word.getTrys(),nTrys+1);
    }

    @Test
    public void checkSpellingTest_correctSpelling(){
        int nErrors = word.getErrors();
        int nTrys = word.getTrys();
        word.addSpelling("Banana");
        assertEquals(word.getErrors(),nErrors);
        assertEquals(word.getTrys(),nTrys+1);
    }

    @Test
    public void checkSpellingTest_repeatedWrongSpelling(){
        int nErrors = word.getErrors();
        int nTrys = word.getTrys();
        word.addSpelling("Bananna");
        word.addSpelling("Bananna");
        assertEquals(word.getErrors(),nErrors+2);
        assertEquals(word.getTrys(),nTrys+2);
        assertTrue(word.getWrongSpellingList().size()==1);
    }
}