package com.example.fxfxfxf.cmdDictionary;

public class DictionaryCommandline {
    public static void showAllWords(Dictionary dictionary) {
        System.out.println("No\t|English\t\t|Vietnamese");
        int no = 1;
        for(Word w : dictionary.getWords()){
            System.out.println(no + "\t|" + w.getWord_target() + "\t\t\t|" + w.getWord_explain());
            no++;
        }
    }

    public static void dictionaryBasic(Dictionary dictionary){
        DictionaryManagement.insertFromCommandLine(dictionary);
        showAllWords(dictionary);
    }
    public static void dictionaryAdvanced(Dictionary dictionary) {
        DictionaryManagement.insertFromFile(dictionary);
        showAllWords(dictionary);
        DictionaryManagement.dictionaryLookup(dictionary);
    }
}
