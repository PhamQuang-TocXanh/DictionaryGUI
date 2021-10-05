package com.example.fxfxfxf;

import com.example.fxfxfxf.cmdDictionary.Word;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerListWord implements Initializable {
    @FXML
    private Button backBtn;
    @FXML
    private Label header;
    @FXML
    private ListView<String> listView;
    @FXML
    private Label wordMeaning;

    private Stage stage;

    private final List<Word> listWord = Main.dictionary.getWords();

    public void switchToFindWord(ActionEvent e) {// close word list window
        stage = (Stage) backBtn.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> listEngWord = new ArrayList<>(); //list of English word to display in listView
        for (Word word : listWord) {
            listEngWord.add(word.getWord_target());
        }

        String[] arr = listEngWord.toArray(new String[0]);
        listView.getItems().addAll(arr);
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                int index = listView.getSelectionModel().getSelectedIndex();
                wordMeaning.setText(listWord.get(index).getWord_explain());
            }
        });
    }
}
