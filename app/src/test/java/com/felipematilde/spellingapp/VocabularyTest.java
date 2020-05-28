package com.felipematilde.spellingapp;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class VocabularyTest {
    Vocabulary vocabulary;

    @Before
    public void setUp() throws Exception {
        vocabulary = new Vocabulary();
    }

    @Test
    public void addTestContains(){
        this.vocabulary.addWord("Banana");
        assertTrue(this.vocabulary.contains("banana"));
    }

    @Test
    public void getWordStringTest(){
        this.vocabulary.addWord("Mango");
        assertTrue(this.vocabulary.getWord("mango").getCorrectSpelling().equals("mango"));
    }

    @Test
    public void getWordTest(){
        this.vocabulary.addWord("Chocolate");
        assertTrue(this.vocabulary.getWord().getCorrectSpelling().equals("chocolate"));
    }

    @Test
    public void containsTest(){
        this.vocabulary.addWord("Strawberry");
        assertTrue(this.vocabulary.contains("strawberry"));
    }

    @Test
    public void containsTestNot(){
        this.vocabulary.addWord("Republic");
        assertTrue(!this.vocabulary.contains("Country"));
    }
}