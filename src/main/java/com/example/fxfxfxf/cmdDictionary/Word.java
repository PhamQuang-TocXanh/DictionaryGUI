package com.example.fxfxfxf.cmdDictionary;

public class Word implements Comparable<Word> {
    String word_target;
    String word_explain;

    public Word() {
        word_explain = "";
        word_target = "";
    }

    public Word(String word_target, String word_explain) {
        this.word_target = word_target;
        this.word_explain = word_explain;
    }

    public Word(Word word) {
        word_target = word.word_target;
        word_explain = word.word_explain;
    }

    public String getWord_explain() {
        return word_explain;
    }

    public void setWord_explain(String word_explain) {
        this.word_explain = word_explain;
    }

    public String getWord_target() {
        return word_target;
    }

    public void setWord_target(String word_target) {
        this.word_target = word_target;
    }

    @Override
    public int compareTo(Word o) {
        return word_target.compareTo(o.getWord_target());
    }

}