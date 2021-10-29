package com.example.fxfxfxf;

import com.example.fxfxfxf.cmdDictionary.Dictionary;
import com.example.fxfxfxf.cmdDictionary.DictionaryManagement;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Main extends Application {
    public static Dictionary dictionary;
    public static DictDatabase dictDB;
    @Override
    public void start(Stage stage) throws IOException {
        dictionary = new Dictionary();
        dictDB = new DictDatabase();
        DictionaryManagement.insertFromFile(dictionary);

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("findWord-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        try {
//            File file = new File(Main.class.getResource("dictionaries.txt").getPath());
//            Scanner sc = new Scanner(file);
//            while (sc.hasNext()) {
//                System.out.println(sc.nextLine());
//            }

            File iconFile = new File(Main.class.getResource("image/dictionary_icon.png").getPath());
            FileInputStream fileInputStream = new FileInputStream(iconFile);
            Image icon = new Image(fileInputStream);
            stage.getIcons().add(icon);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        stage.setTitle("MY DICTIONARY!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        stage.setOnCloseRequest(e -> {
            e.consume();
            logout(stage);
        });
    }

    public void logout(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Closing!!!");
        alert.setHeaderText("Are you sure want to quit?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            stage.close();
        }
    }

    public static void main(String[] args) {
        launch();
        System.gc();
    }
}