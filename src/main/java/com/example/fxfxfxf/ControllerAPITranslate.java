package com.example.fxfxfxf;

import com.example.fxfxfxf.module.APITranslate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerAPITranslate {
    @FXML
    private Button backBtn;
    @FXML
    private Label header;
    @FXML
    private TextArea srcText;
    @FXML
    private TextArea dstText;
    @FXML
    private Button enToVi;
    @FXML
    private Button viToEn;

    private Stage stage;

    public void switchToFindWord(ActionEvent e) {// close window
        stage = (Stage) backBtn.getScene().getWindow();
        stage.close();
    }

    public void translateEnToVi(ActionEvent e) throws IOException {
        String text = srcText.getText();
        String translatedText = APITranslate.translate("en", "vi", text);
        dstText.setText(translatedText);
    }

    public void translateViToEn(ActionEvent e) throws IOException {
        String text = srcText.getText();
        String translatedText = APITranslate.translate("vi", "en", text);
        dstText.setText(translatedText);
    }
}
