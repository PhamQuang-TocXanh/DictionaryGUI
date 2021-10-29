package com.example.fxfxfxf;

import com.example.fxfxfxf.cmdDictionary.Dictionary;
import com.example.fxfxfxf.cmdDictionary.DictionaryManagement;
import com.example.fxfxfxf.cmdDictionary.Word;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

import java.net.URL;
import java.util.Comparator;
import java.util.Locale;
import java.util.ResourceBundle;

public class ControllerAddWord implements Initializable {
    @FXML
    private TextField newWord;
    @FXML
    private TextField pronunciation;
    @FXML
    private TextArea meaning;
    @FXML
    private Button add;
    @FXML
    private Label status;
    private final Dictionary dictionary = Main.dictionary;
    private DictDatabase dictDB = Main.dictDB;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        add.setOnAction(actionEvent -> {
            String newWord_input = newWord.getText().toLowerCase(Locale.ROOT).trim().replaceAll("\\s+", " ");
            String pronunciation_input = pronunciation.getText().toLowerCase(Locale.ROOT).trim().replaceAll("\\s+", " ");
            String meaning_input = meaning.getText().toLowerCase(Locale.ROOT).trim().replaceAll("\\s+", " ");
            /*
            int index = DictionaryManagement.dictionaryLookup(dictionary, newWord_input);
            if (index != -1) {
                status.setTextFill(Color.RED);
                status.setText(newWord_input + " is already in the dictionary!");
            } else {
                Word word = new Word(newWord_input, meaning_input);
                dictionary.getWords().add(word);
                dictionary.getWords().sort(Comparator.comparing(Word::getWord_target));
                status.setTextFill(Color.web("00FF80")); // green
                status.setText("Add succesfully!!!");
            }
            */
            if (dictDB.isInDictionary(newWord_input)) {
                status.setTextFill(Color.RED);
                status.setText(newWord_input + " is already in the dictionary!");
            } else if (pronunciation_input == "") {
                status.setTextFill(Color.RED);
                status.setText("Invalid pronunciation");
            } else if (meaning_input == "") {
                status.setTextFill(Color.RED);
                status.setText("Invalid meaning");
            }
            else {
                dictDB.addWord(newWord_input, pronunciation_input, meaning_input);
                status.setTextFill(Color.web("00FF80")); // green
                status.setText("Add succesfully!!!");
            }
            status.setWrapText(true);
            status.setTextAlignment(TextAlignment.CENTER);
            status.setVisible(true);
        });
    }

    public void reset() {
        status.setVisible(false);
    }

}
