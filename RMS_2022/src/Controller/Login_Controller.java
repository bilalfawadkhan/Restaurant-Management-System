package Controller;
import rms.Main;
import Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class
Login_Controller {
    Stage Login = new Stage();
    @FXML
    private TextField textfield;
    String ID = "";
    @FXML
    private Button login_button;
    @FXML
    private javafx.scene.control.Button Delete;
    @FXML
    private Label messageID;


    @FXML
    void Login_Button(ActionEvent event) throws Exception {
        System.out.println(this.textfield.getText());
        Database_Controller connection = new Database_Controller();
        Connection connectDB = Database_Controller.getConnection();
        String verifyLogin = "SELECT * FROM bwlana3jrca5fzranlwf.Mitarbeiter WHERE Bedienernummer = '" + this.textfield.getText() + "' ";


        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);
            System.out.println("Test1");

            while(queryResult.next()) {
                User currentUser = new User(queryResult.getInt("id"), queryResult.getString("Benutzername"), queryResult.getInt("bedienernummer"));
                System.out.println("Test2");
                this.logIn(currentUser);
               // this.messageID.setText("user login");
            }

            //this.messageID.setText("Invalid Login, Please try again ");
        } catch (Exception var8) {
            var8.printStackTrace();
            var8.getCause();
        }

    }

    public void logIn(User currentUser) throws IOException {

        if (currentUser.getId() == 1) {
            Stage stage = (Stage) login_button.getScene().getWindow();
            stage.close();

            Stage adminStage = new Stage();
            User.currentUser = currentUser;

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/View/AdminPanel.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            adminStage.setTitle("Hello!");
            adminStage.setScene(scene);
            adminStage.show();
        }
        else

        try {
            Stage stage = (Stage) login_button.getScene().getWindow();
            stage.close();

            Stage Tstage = new Stage();
            User.currentUser = currentUser;

            System.out.println(currentUser.getBenutzername());
            System.out.println("Test3");
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/View/Tische.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            Tstage.setTitle("Hello!");
            Tstage.setScene(scene);
            Tstage.show();

        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }
    @FXML
    void Get_Numbers(ActionEvent numbers) throws Exception{

        String ID = ((Button)numbers.getSource()).getText();
        textfield.setText(textfield.getText() + ID);

    }

    @FXML
    void Delete(ActionEvent event) throws Exception {
        textfield.setText("");
    }
    // TODO Scene wieder schließen mit Button
    @FXML
    void onCookButton(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/View/cookmenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 500);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();


    }

}
