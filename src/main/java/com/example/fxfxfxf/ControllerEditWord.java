package com.example.fxfxfxf;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class ControllerEditWord extends ControllerFindWord implements Initializable {
    @FXML
    private TextField newPronunciation;
    @FXML
    private TextArea newMeaning;
    @FXML
    private Label word;
    @FXML
    private Label status;
    @FXML
    private Button saveButton;
    @FXML
    private Button backButton;

    private DictDatabase dictDB = Main.dictDB;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
     //   System.out.println(wordNow);
        word.setText(wordNow);
        saveButton.setOnAction(actionEvent -> {
            String pronunciation_input = newPronunciation.getText().toLowerCase(Locale.ROOT).trim().replaceAll("\\s+", " ");
            String meaning_input = newMeaning.getText().toLowerCase(Locale.ROOT).trim().replaceAll("\\s+", " ");

            if (pronunciation_input == "") {
                status.setTextFill(Color.RED);
                status.setText("Invalid pronunciation");
            } else if (meaning_input == "") {
                status.setTextFill(Color.RED);
                status.setText("Invalid meaning");
            }
            else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Editing word");
                alert.setHeaderText("Are you sure to save this word?");

                if (alert.showAndWait().get() == ButtonType.OK) {
                    dictDB.editWord(word.getText(), pronunciation_input, meaning_input);
                    status.setTextFill(Color.web("00FF80")); // green
                    status.setText("Edit succesfully!!!");
                }
            }
            status.setWrapText(true);
            status.setTextAlignment(TextAlignment.CENTER);
            status.setVisible(true);
        });
    }

    public void clearStatus() {
        status.setVisible(false);
    }

    public void backToFindWord() {// close window
        ((Stage) backButton.getScene().getWindow()).close();
    }
}
