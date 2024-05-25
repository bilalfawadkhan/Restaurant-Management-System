package Controller;

import Model.TableData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import rms.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CookMenuConroller implements Initializable {
    TableData td = new TableData();

    @FXML
    private TextArea cookOrderT;

    @FXML
    private Button goback;

    @FXML
    void onGoBack(ActionEvent event) throws IOException {
        Stage stage = (Stage) goback.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/View/Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 350, 500);
        stage.setTitle("Login");
        stage.setScene(scene);
    }

    @Override
    public void initialize (URL url, ResourceBundle resourceBundle) {
        cookOrderT.setText(String.format("%s%10s%10s\n","TableNo","Food Name","Anzahl"));
        for(int i = 1 ; i < 10 ; i++){
           ArrayList<String> s = TableData.getTableFood(i);
           if(s.size() != 0 ){
               for (int j = 0; j < s.size(); j++) {
                   String foodName = s.get(j).split("-")[0];
                   Double Pries = Double.parseDouble(s.get(j).split("-")[1]);
                   int anzahl = Integer.parseInt(s.get(j).split("-")[2]);
//                   String custNo = s.get(i).split("-")[3];
                   cookOrderT.appendText(String.format("%s%10s%10s\n",foodName,Pries,anzahl));


               }

           }
           else {
               cookOrderT.setText("No Food Ordered");
           }
        }
    }
}
