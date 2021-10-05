package com.example.fxfxfxf.cmdDictionary;

import com.example.fxfxfxf.Main;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class DictionaryManagement {
    public static Scanner sc;

    static {
        sc = new Scanner(System.in);
    }

    public static int BinarySearch(List<Word> words, int left, int right, String engWord) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (words.get(mid).getWord_target().compareTo(engWord) == 0) return mid;
            else if(words.get(mid).getWord_target().compareTo(engWord) < 0) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }

    public static void insertFromCommandLine(Dictionary dictionary) {
        System.out.print("Nhập số lượng từ: ");
        int n = Integer.parseInt(sc.nextLine());
        List<Word> words = dictionary.getWords();
        for(int i = 0; i < n; i++) {
            System.out.print("Nhập từ tiếng Anh: ");
            String eng = sc.nextLine();
            int j = 1;
            while (j < words.size() && words.get(j).getWord_target().compareTo(eng) < 0) {
                j *= 2;
            }
            int index = BinarySearch(words, j/2, Math.min(j, words.size()-1), eng);
            if (index != -1) {
                System.out.println("Từ " + eng + " đã có trong từ điển!");
                i--;
                continue;
            }
            System.out.print("Nhập nghĩa của từ: ");
            String viet = sc.nextLine();
            Word word = new Word(eng, viet);
            words.add(word);
        }
        words.sort(new Comparator<Word>() {
            @Override
            public int compare(Word o1, Word o2) {
                return o1.getWord_target().compareTo(o2.getWord_target());
            }
        });
    }

    public static void dictionaryExportToFile(Dictionary dictionary) {
        dictionary.getWords().sort(Comparator.comparing(Word::getWord_target));

        try {
            File dictionaryFile = new File(Main.class.getResource("dictionaries.txt").getPath());
            FileWriter fileWriter = new FileWriter(dictionaryFile, false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Word word : dictionary.getWords()) {
                bufferedWriter.write(word.getWord_target() + "\t" + word.getWord_explain());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            fileWriter.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insertFromFile(Dictionary dictionary) {
        try {
            File dictionaryFile = new File(Main.class.getResource("dictionaries.txt").getPath());
            FileReader fileReader = new FileReader(dictionaryFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while (true) {
                line = bufferedReader.readLine();
                if(line == null) break;
                String []eng_viet = line.split("\t");
                Word word = new Word(eng_viet[0], eng_viet[1]);
                dictionary.getWords().add(word);
            }
            bufferedReader.close();
            fileReader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        dictionary.getWords().sort(Comparator.comparing(Word::getWord_target));
    }

    public static void dictionaryLookup(Dictionary dictionary) {
        System.out.print("Nhập từ cần tìm kiếm: ");
        String engWord = sc.nextLine();
        int i = 1;
        List<Word> words = dictionary.getWords();
        while (i < words.size() && words.get(i).getWord_target().compareTo(engWord) < 0) {
            i *= 2;
        }
        int index = BinarySearch(words, i/2, Math.min(i, words.size()-1), engWord);
        if (index != -1) {
            System.out.println("Nghĩa của " + engWord + ": " + words.get(index).getWord_explain());
        } else {
            System.out.println("Không tìm thấy từ " + engWord);
        }
    }

    public static void deleteWord(Dictionary dictionary) {
        do {
            System.out.print("Nhập từ cần xóa: ");
            String word_target = sc.nextLine();
            int i = 1;
            List<Word> words = dictionary.getWords();
            while (i < words.size() && words.get(i).getWord_target().compareTo(word_target) < 0) {
                i *= 2;
            }
            int index = BinarySearch(words, i/2, Math.min(i, words.size()-1), word_target);
            if (index != -1) {
                words.remove(index);
                break;
            } else {
                System.out.println("Không tìm thấy từ " + word_target);
            }
        } while (true);
    }

    public static void editWord(Dictionary dictionary) {
        do {
            System.out.print("Nhập từ cần sửa: ");
            String word_target = sc.nextLine();
            int i = 1;
            List<Word> words = dictionary.getWords();
            while (i < words.size() && words.get(i).getWord_target().compareTo(word_target) < 0) {
                i *= 2;
            }
            int index = BinarySearch(words, i/2, Math.min(i, words.size()-1), word_target);
            if (index != -1) {
                System.out.print("Nhập từ cần thay thế: ");
                words.get(index).setWord_target(sc.nextLine());
                System.out.print("Nhập nghĩa của từ: ");
                words.get(index).setWord_explain(sc.nextLine());
                break;
            } else {
                System.out.println("Không tìm thấy từ " + word_target);
            }
        } while (true);
    }

    public static void dictionarySearcher(Dictionary dictionary) {
        System.out.print("Nhập kí tự: ");
        String s = sc.nextLine();
        List<Word> words = dictionary.getWords();
        List<Word> searcher = new ArrayList<>();

        for(int i = 0; i < words.size(); i++){
            if (words.get(i).getWord_target().startsWith(s)) {
                while (i < words.size() && words.get(i).getWord_target().startsWith(s)) {
                    searcher.add(words.get(i));
                    i++;
                }
                break;
            }
        }

        for (Word word : searcher) {
            System.out.println(word.getWord_target());
        }
    }

    //GUI
    public static int dictionaryLookup(Dictionary dictionary, String word_target) {
        if (word_target.equals("")) return -1;
        int i = 1;
        List<Word> words = dictionary.getWords();
        while (i < words.size() && words.get(i).getWord_target().compareTo(word_target) < 0) {
            i *= 2;
        }
        int index = BinarySearch(words, i/2, Math.min(i, words.size()-1), word_target);
        return index;
    }

}
