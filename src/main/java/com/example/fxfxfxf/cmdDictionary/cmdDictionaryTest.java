package com.example.fxfxfxf.cmdDictionary;

import java.util.Scanner;

public class cmdDictionaryTest {

    public static void main(String[] args) {
        // write your code here
        Dictionary dictionary = new Dictionary();
        DictionaryManagement.insertFromFile(dictionary);
//        DictionaryCommandline.dictionaryBasic(dictionary);
//        DictionaryCommandline.dictionaryAdvanced(dictionary);
//        DictionaryManagement.dictionarySearcher(dictionary);

        Scanner sc = new Scanner(System.in);
        int check;
        do {
            System.out.println("*------------Từ điển Anh Việt-----------*");
            System.out.println("1. Tra từ tương đối");
            System.out.println("2. Tra từ tuyệt đối");
            System.out.println("3. Thêm từ");
            System.out.println("4. Sửa từ");
            System.out.println("5. Xóa từ");
            System.out.println("6. In tất cả các từ trong từ điển");
            System.out.println("7. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            check = sc.nextInt();
            switch (check) {
                case 1 -> DictionaryManagement.dictionarySearcher(dictionary); // tim tu tuong doi
                case 2 -> DictionaryManagement.dictionaryLookup(dictionary); // tra tu tuyet doi
                case 3 -> DictionaryManagement.insertFromCommandLine(dictionary);
                case 4 -> DictionaryManagement.editWord(dictionary);
                case 5 -> DictionaryManagement.deleteWord(dictionary);
                case 6 -> DictionaryCommandline.showAllWords(dictionary);
                case 7 -> System.out.println("Bai Bai");
                default -> System.out.println("Nhập sai!!!");
            }
        }
        while(check != 7);
        DictionaryManagement.dictionaryExportToFile(dictionary);
        System.gc();
    }
}