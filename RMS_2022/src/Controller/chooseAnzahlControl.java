package Controller;

import Model.PassData;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class chooseAnzahlControl {

    @FXML
    private Button b1;

    @FXML
    private Button logButton;

    @FXML
    private TextField textfield;


    @FXML
    void Delete(ActionEvent event) {

    }

    @FXML
    void Get_Numbers(ActionEvent event) {
        String s = ((Button) event.getSource()).getText();
        textfield.setText(textfield.getText() + s);

    }

    @FXML
    void LogAnzahl(ActionEvent event) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter("RMS_2022/src/texttemp/anzahlchoose.txt", "UTF-8");
        writer.print(textfield.getText());
        writer.close();
        PassData.setAnzhel(Integer.parseInt(textfield.getText()));


    }

}
