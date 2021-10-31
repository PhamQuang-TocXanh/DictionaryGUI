package com.example.fxfxfxf;

import com.example.fxfxfxf.cmdDictionary.*;

import com.example.fxfxfxf.cmdDictionary.Dictionary;
import com.example.fxfxfxf.module.TextToSpeech;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import static com.example.fxfxfxf.cmdDictionary.DictionaryManagement.BinarySearch;

public class ControllerFindWord implements Initializable {
    @FXML
    TextField wordSearch;
    @FXML
    private ListView<String> listView;
    @FXML
    TextArea meaning;
    @FXML
    Button editButton;
    @FXML
    Button deleteButton;
    @FXML
    private Button apiTranslate;
    @FXML
    private Button addNewWord;
    @FXML
    private Button aboutUs;

    public static String wordNow;


    private Stage stage;
    private Scene view_scene;
    private Parent root;

    private final Dictionary dictionary = Main.dictionary;
    private List<Word> listWord = dictionary.getWords();
    private List<String> wordInlistView;
    private DictDatabase dictDB = Main.dictDB;

    public void switchToAPITranslate(ActionEvent e) throws IOException {
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("APITranslate-view.fxml")));
            view_scene = new Scene(root);
            stage = new Stage();
            stage.setTitle("Google API Translate");
            stage.setScene(view_scene);
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void displayWordExplain() {
        if (wordSearch.getText() == null) return;
        //String word_target = wordSearch.getText().trim().toLowerCase(Locale.ROOT).replaceAll("\\s+"," ");
        wordNow = wordSearch.getText().trim().toLowerCase(Locale.ROOT).replaceAll("\\s+"," ");
        String word_explain = dictDB.findWord(wordNow);
        meaning.setText((word_explain.equals("") ? "" : word_explain));
        meaning.setWrapText(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        wordSearch.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode()== KeyCode.ENTER) {
                    displayWordExplain();
                }
            }
        });
        wordInlistView = new ArrayList<>();
        wordNow = "";
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        listView.getItems().addAll(dictDB.dictionarySearcher(""));
        listView.setPrefHeight(listView.getItems().size() * listView.getFixedCellSize());
        buttonHoverEffect(apiTranslate, 102, 202, 255, 102, 163, 255);
        buttonHoverEffect(addNewWord, 102, 202, 255, 102, 163, 255);
        buttonHoverEffect(aboutUs, 102, 202, 255, 102, 163, 255);
        buttonHoverEffect(editButton, 102, 202, 255, 102, 163, 255);
        buttonHoverEffect(deleteButton, 255, 63, 63, 255, 127, 127);
        //selectWordInList();
    }

    public void buttonHoverEffect(Button button, int r1, int g1, int b1, int r2, int g2, int b2) {
        String backgroundColor1 = "-fx-background-color: rgb(" + Integer.toString(r1) +", " + Integer.toString(g1) + ", " + Integer.toString(b1) + ");";
        String backgroundColor2 = "-fx-background-color: rgb(" + Integer.toString(r2) +", " + Integer.toString(g2) + ", " + Integer.toString(b2) + ");";
        System.out.println(backgroundColor1);
        button.hoverProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if(button.isHover()) button.setStyle(backgroundColor1);
                else button.setStyle(backgroundColor2);
            }
        });
    }

    public void updateListView() {
        try {

            if (wordSearch.getText() == null) {
                wordNow = "";
            } else {
                wordNow = wordSearch.getText().trim().toLowerCase(Locale.ROOT).replaceAll("\\s+"," ");
            }
            listView.getItems().clear();
            listView.getItems().addAll(dictDB.dictionarySearcher(wordSearch.getText().trim().toLowerCase(Locale.ROOT).replaceAll("\\s+", " ")));
            listView.setPrefHeight(listView.getItems().size() * listView.getFixedCellSize());

            /*
            if (listView.getItems().size() > 0) {
                listView.getItems().subList(0, listView.getItems().size()).clear();
            }
            if(wordSearch.getText() == null) {
                listView.getItems().addAll(dictDB.dictionarySearcher(""));
                listView.setPrefHeight(listView.getItems().size() * listView.getFixedCellSize());
                return;
            }
            wordInlistView.clear();
            int n = listWord.size();

            if (!wordOnTyping.equals("")) {
                for (int i = 0; i < n; i++) {
                    if(listWord.get(i).getWord_target().startsWith(wordOnTyping)) {
                        while (i < n && listWord.get(i).getWord_target().startsWith(wordOnTyping)) {
                            wordInlistView.add(listWord.get(i).getWord_target());
                            i++;
                        }
                        break;
                    }
                }

                listView.getItems().addAll(wordInlistView);

                if (wordSearch.getText().equals("")) {
                    listView.setPrefHeight(0);
                } else {
                    listView.setPrefHeight(listView.getItems().size() * listView.getFixedCellSize());
                }
            } else {
                listView.setPrefHeight(0);
            }
            */
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void selectWordInList() {
        wordSearch.setText(listView.getSelectionModel().getSelectedItems().get(0));
        displayWordExplain();
        /*
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                wordSearch.setText(listView.getSelectionModel().getSelectedItem());
                System.out.println("selectWordInList");
                displayWordExplain();
            }
        });
        */
    }

    /**
     * Add Word Button.
     */
    public void addWord() {
//        try {
//            stage = new Stage();
//            stage.setTitle("Add word to your dictionary");
//
//            AnchorPane root = new AnchorPane();
//            view_scene = new Scene(root,300,250, Color.WHITESMOKE);
//
//            Label header = new Label("Add word");
//            header.setLayoutX(100);
//            header.setLayoutY(8);
//            header.setTextAlignment(TextAlignment.CENTER);
//            header.setFont(new Font("Roboto", 25));
//            root.getChildren().add(header);
//
//            TextField newWord = new TextField();
//            newWord.setPromptText("new word");
//            newWord.setLayoutX(80);
//            newWord.setLayoutY(60);
//            root.getChildren().add(newWord);
//
//            TextField newWordMeaning = new TextField();
//            newWordMeaning.setPromptText("meaning");
//            newWordMeaning.setLayoutX(80);
//            newWordMeaning.setLayoutY(100);
//            root.getChildren().add(newWordMeaning);
//
//            Button button = new Button("Add");
//            button.setLayoutX(130);
//            button.setLayoutY(140);
//            button.setStyle("-fx-background-color: #4da4f8; ");
//            button.setTextFill(Color.WHITESMOKE);
//            root.getChildren().add(button);
//
//            Label check = new Label("");//chua chỉnh vị trí
//            check.setPrefHeight(25); check.setMaxHeight(50);
//            check.setLayoutX(0);
//            check.setLayoutY(200);
//            check.setFont(new Font("Roboto", 20));
//            check.setTextFill(Color.web("#f2f2f2"));
//            check.setStyle("-fx-background-color: #4da4fa; ");
//            root.getChildren().add(check);
//
//            button.setOnAction(new EventHandler<ActionEvent>() {
//                @Override
//                public void handle(ActionEvent actionEvent) {
//                    if (newWord.getText() == null) return;
//                    String eng = newWord.getText().trim().toLowerCase(Locale.ROOT).replaceAll("\\s+"," ");
//                    if (eng.equals("")) {
//                        return;
//                    }
//
//                    int index = DictionaryManagement.dictionaryLookup(dictionary, eng);
//                    if (index != -1) {
//                        check.setText(eng + " already in the dictionary!");
//                    } else {
//                        if (newWordMeaning.getText() == null) return;
//                        String viet = newWordMeaning.getText().trim().toLowerCase(Locale.ROOT).replaceAll("\\s+", " ");
//                        if (viet.equals("")) {
//                            check.setText("Meaning required!");
//                            return;
//                        }
//                        Word word = new Word(eng, viet);
//                        dictionary.getWords().add(word);
//                        dictionary.getWords().sort(new Comparator<Word>() {
//                            @Override
//                            public int compare(Word o1, Word o2) {
//                                return o1.getWord_target().compareTo(o2.getWord_target());
//                            }
//                        });
//                        check.setText("Add succesfully!!!");
//                    }
//                }
//            });
//
//            stage.initModality(Modality.APPLICATION_MODAL);
//            stage.setScene(view_scene);
//            stage.setResizable(false);
//            stage.showAndWait();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("addWord.fxml")));
            view_scene = new Scene(root);
            stage = new Stage();
            stage.setTitle("Add word");
            stage.setScene(view_scene);
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
    /*
    public void deleteWord() {
        try {
            stage = new Stage();
            stage.setTitle("Delete word from your dictionary");

            AnchorPane root = new AnchorPane();
            view_scene = new Scene(root,300,200, Color.WHITESMOKE);

            Label header = new Label("Delete word");
            header.setLayoutX(88);
            header.setLayoutY(8);
            header.setTextAlignment(TextAlignment.CENTER);
            header.setFont(new Font("Roboto", 25));
            root.getChildren().add(header);

            TextField deleteWord = new TextField();
            deleteWord.setPromptText("word");
            deleteWord.setLayoutX(80);
            deleteWord.setLayoutY(60);
            root.getChildren().add(deleteWord);

            Button button = new Button("Delete");
            button.setLayoutX(130);
            button.setLayoutY(100);
            button.setStyle("-fx-background-color: #4da4f8; ");
            button.setTextFill(Color.WHITESMOKE);
            root.getChildren().add(button);

            Label check = new Label("");
            check.setPrefHeight(25); check.setMaxHeight(50);
            check.setLayoutX(77);
            check.setLayoutY(150);
            check.setFont(new Font("Roboto", 20));
            check.setTextFill(Color.web("#f2f2f2"));
            check.setStyle("-fx-background-color: #4da4fa; ");
            root.getChildren().add(check);

            deleteWord.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent keyEvent) {
                    if (keyEvent.getCode()==KeyCode.ENTER) {

                            if (deleteWord.getText() == null) return;
                            String eng = deleteWord.getText().trim().toLowerCase(Locale.ROOT).replaceAll("\\s+"," ");
                            if (eng.equals("")) {
                                return;
                            }

                            int index = DictionaryManagement.dictionaryLookup(dictionary, eng);
                            if (index != -1) {
                                listWord.remove(index);
                                check.setText("Delete succesfully!");
                            } else {
                                check.setText("Can not find word!");
                            }


                    }
                }
            });

            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if (deleteWord.getText() == null) return;
                    String eng = deleteWord.getText().trim().toLowerCase(Locale.ROOT).replaceAll("\\s+"," ");
                    if (eng.equals("")) {
                        return;
                    }

                    int index = DictionaryManagement.dictionaryLookup(dictionary, eng);
                    if (index != -1) {
                        listWord.remove(index);
                        check.setText("Delete succesfully!");
                    } else {
                        check.setText("Can not find word!");
                    }
                }
            });

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(view_scene);
            stage.setResizable(false);
            stage.showAndWait();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    */

    /**
     * Delete Button.
     */
    public void deleteWord() {
        if (!dictDB.isInDictionary(wordNow)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Word is not exist!");
            alert.setTitle("Error");
            alert.showAndWait();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Deleting word");
        alert.setHeaderText("Are you sure want to delete this word?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            dictDB.deleteWord(wordNow);
            wordSearch.setText("");
            meaning.setText("");
        }
    }

    /**
     * Edit Button
     * @param e
     */
    public void editWord(ActionEvent e) {
        if (!dictDB.isInDictionary(wordNow)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Word is not exist!");
            alert.setTitle("Error");
            alert.showAndWait();
            return;
        }
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("editWord.fxml")));
            view_scene = new Scene(root);
            stage = new Stage();
            stage.setTitle("Edit word");
            stage.setScene(view_scene);
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            meaning.setText(dictDB.findWord(wordNow));
            meaning.setWrapText(true);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    /**
     * Text to speech.
     */
    public void textToSpeech() {
        TextToSpeech.textToSpeech(wordNow);
    }

    /**
     * About Us Button.
     */
    public void showAboutUs() {
        wordSearch.setText("");
        updateListView();
        try {
            File dictionaryFile = new File(Main.class.getResource("aboutUs.txt").getPath());
            FileReader fileReader = new FileReader(dictionaryFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuilder info = new StringBuilder();
            String line;
            while (true) {
                line = bufferedReader.readLine();
                if(line == null) break;
                info.append(line + "\n");
            }
            bufferedReader.close();
            fileReader.close();
            meaning.setText(info.toString());
            meaning.setWrapText(true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}