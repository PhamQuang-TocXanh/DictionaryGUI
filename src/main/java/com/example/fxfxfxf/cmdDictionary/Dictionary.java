package com.example.fxfxfxf.cmdDictionary;

import java.util.ArrayList;
import java.util.List;

public class Dictionary {
    List<Word> words;

    public Dictionary() {
        words = new ArrayList<Word>();
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }

    public List<Word> getWords() {
        return words;
    }

}
