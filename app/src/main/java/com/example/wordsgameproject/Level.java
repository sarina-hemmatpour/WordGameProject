package com.example.wordsgameproject;

import java.util.ArrayList;

public class Level {
    private int Id;
    private ArrayList<String> words;

    public Level() {
        words=new ArrayList<>();
    }

    public ArrayList<String> getWords() {
        return words;
    }

    public void setWords(ArrayList<String> words) {
        this.words = words;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}
