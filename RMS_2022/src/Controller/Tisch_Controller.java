package Controller;

import Model.*;
import javafx.scene.text.Text;
import rms.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static Model.User.currentUser;


public class Tisch_Controller implements Initializable {
    String anzahlcount = "0";
    PassData pd = new PassData();
    TableData td = new TableData();
    @FXML
    private Button editButton;
    @FXML
    private TableView<Drinks> TabelDate;
    @FXML
    private TableView<Speisen> DishTable;

    @FXML
    private TableView<Order> allOrdersTableList;


    @FXML
    private TextField anzahl;
    @FXML
    private Button goBackButton;


    @FXML
    private TableColumn<Drinks, String> nameTableCol;
    @FXML
    private TableColumn<Drinks, String> preisTableCol;

    @FXML
    private TableColumn<Speisen, String> nameDishTableCol;
    @FXML
    private TableColumn<Speisen, String> preisDishTableCol;
    @FXML
    private TableColumn<Order, String> PriesOrderCol;
    @FXML
    private TableColumn<Order, String> anzahlOrderCol;
    @FXML
    private TableColumn<Order, String> nameOrderCol;


    @FXML
    private TableColumn<Order, String> custOrderCol;
    @FXML
    private TextField mergeT;
    @FXML
    private TextField splitT;
    @FXML
    private Text totalBillText;

    @FXML
    private TextField changeTableT;
    @FXML
    private TextField custNoTextF;


    @FXML
    private Button logButton;
    @FXML
    private TextField textfield;
    ObservableList<Drinks> drinkList = FXCollections.observableArrayList();
    ObservableList<Speisen> speisenList = FXCollections.observableArrayList();
    ObservableList<Order> orderList = FXCollections.observableArrayList();
    @FXML
    void Delete(ActionEvent event) {

    }

    @FXML
    void goBack(ActionEvent event) {
        Stage stage = (Stage) goBackButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void edit(ActionEvent event)throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/View/Status.fxml"));
        Stage servus = new Stage();
        Scene hallo = new Scene(fxmlLoader.load(), 550, 500);
        servus.setTitle("Bestellungen");
        servus.setScene(hallo);
        servus.initModality(Modality.WINDOW_MODAL);
        servus.initOwner(editButton.getScene().getWindow());
        servus.show();



    }
    @FXML
    void addItemsToTable(ActionEvent event) {
        Speisen sp = DishTable.getSelectionModel().getSelectedItem();
        Drinks d = TabelDate.getSelectionModel().getSelectedItem();

       if(!(sp == null)) {
           System.out.println(sp.getName() + sp.getPreis());
           orderList.add(new Order(sp.getName(),Double.parseDouble(sp.getPreis()),Integer.parseInt(anzahl.getText()),custNoTextF.getText()));
           String foodS = sp.getName() + "-" + String.format("%.1f",Double.parseDouble(sp.getPreis())) + "-" + anzahl.getText()+ "-" + custNoTextF.getText();
            TableData.addTableFood(TableData.getSelectedTable(),foodS);
       }
       if(!(d == null)) {
           System.out.println(d.getName() + d.getPreis());
           orderList.add(new Order(d.getName(),Double.parseDouble(d.getPreis()),Integer.parseInt(anzahl.getText()),custNoTextF.getText()));
           String drinkS = d.getName() + "-" + String.format("%.1f",Double.parseDouble(d.getPreis())) + "-" + anzahl.getText() + "-" +custNoTextF.getText();
           TableData.addTableFood(TableData.getSelectedTable(),drinkS);
       }
       allOrdersTableList.setItems(orderList);
       allOrdersTableList.refresh();

        DishTable.getSelectionModel().clearSelection();
        TabelDate.getSelectionModel().clearSelection();

    }
    @FXML
    void chooseAnzahl(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/View/ChooseAnzahl.fxml"));
        Stage Anzahl = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 250, 500);
        Anzahl.setTitle("Anzahl");
        Anzahl.setScene(scene);
        Anzahl.initModality(Modality.WINDOW_MODAL);
        Anzahl.initOwner(anzahl.getScene().getWindow());
        Anzahl.showAndWait();
        anzahl.setText(String.valueOf(PassData.getAnzhel()));
    }
    @FXML
    void deleteOrder(ActionEvent event) {
        Order order = allOrdersTableList.getSelectionModel().getSelectedItem();
        String foodS = order.getName() + "-" + order.getPries() + "-" + order.getAnzahl() + "-" + order.getCustomer();
        System.out.println(foodS);
        TableData.deleteTableFood(TableData.getSelectedTable(), foodS);
        allOrdersTableList.getItems().clear();
        allOrdersTableList.refresh();
        // Rrefershing the view by getting tablefood and populating the tablev
        ArrayList<String> s = TableData.getTableFood(TableData.getSelectedTable());
        System.out.println(orderList.size());

        if(s.size() != 0) {
            for (int i = 0; i < s.size(); i++) {
                String foodName = s.get(i).split("-")[0];
                Double Pries = Double.parseDouble(s.get(i).split("-")[1]);
                int anzahl = Integer.parseInt(s.get(i).split("-")[2]);
                String cno= s.get(i).split("-")[3];
                orderList.add(new Order(foodName, Pries, anzahl,cno));
            }
        }
        allOrdersTableList.setItems(orderList);
        allOrdersTableList.refresh();



    }


    @FXML
    void LogAnzahl(ActionEvent event) {
        Stage stage = (Stage) logButton.getScene().getWindow();
        stage.close();
    }
    public void getDishInt(){
        int selectedDishindex = DishTable.getSelectionModel().getSelectedIndex();
        System.out.println(selectedDishindex);
    }

    //TODO überarbeiten return wert ist vlt falsch
   public int getInt() {
        int selectedIndex = TabelDate.getSelectionModel().getSelectedIndex();
        return selectedIndex;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        custNoTextF.setText("1");
        Database_Controller connection = new Database_Controller();
        Connection connectDB = connection.getConnection();


        String sql = "SELECT * FROM Drinks";
        try {
            Statement statement = connectDB.createStatement();
            ResultSet QueryOutput = statement.executeQuery(sql);
            while (QueryOutput.next()) {

                int drinkID = QueryOutput.getInt("id");
                String drinkName = QueryOutput.getString("name");
                String drinkPreis = QueryOutput.getString("preis");
                drinkList.add(new Drinks(drinkID, drinkName, drinkPreis));


                nameTableCol.setCellValueFactory(new PropertyValueFactory<>("name"));
                preisTableCol.setCellValueFactory(new PropertyValueFactory<>("preis"));
                TabelDate.setItems(drinkList);

            }

        } catch (SQLException e) {

            Logger.getLogger(Tisch_Controller.class.getName()).log(Level.SEVERE, null, e);
        }
        String sql_2 = "SELECT * FROM Speisen";
        try {
            Statement statement = connectDB.createStatement();
            ResultSet QueryOutput = statement.executeQuery(sql_2);
            while (QueryOutput.next()) {

                int speisenID = QueryOutput.getInt("id");
                String speisenName = QueryOutput.getString("Name");
                String speisenPreis = QueryOutput.getString("Preis");
                speisenList.add(new Speisen(speisenID,speisenName, speisenPreis));

                nameDishTableCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
                preisDishTableCol.setCellValueFactory(new PropertyValueFactory<>("Preis"));
                DishTable.setItems(speisenList);
            }
            anzahl.setText(String.valueOf(PassData.getAnzhel()));
            anzahlOrderCol.setCellValueFactory(new PropertyValueFactory<>("Anzahl"));
            nameOrderCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
            PriesOrderCol.setCellValueFactory(new PropertyValueFactory<>("Pries"));
            custOrderCol.setCellValueFactory(new PropertyValueFactory<>("customer"));

//            System.out.println(TableData.getSelectedTable());
            ArrayList<String> s = TableData.getTableFood(TableData.getSelectedTable());
            System.out.println(orderList.size());

            if(s.size() != 0){
                for (int i = 0 ; i < s.size() ; i++){
                    String foodName = s.get(i).split("-")[0];
                    Double Pries = Double.parseDouble(s.get(i).split("-")[1]);
                    int anzahl = Integer.parseInt(s.get(i).split("-")[2]);
                    String cno= s.get(i).split("-")[3];
                    orderList.add(new Order(foodName,Pries,anzahl,cno));
                }


                allOrdersTableList.setItems(orderList);
                allOrdersTableList.refresh();

            }





        } catch (SQLException e) {

            Logger.getLogger(Tisch_Controller.class.getName()).log(Level.SEVERE, null, e);

        }

    }

    @FXML
    void mergeButton(ActionEvent event) {
        TableData.mergeTable(TableData.getSelectedTable(),Integer.parseInt(mergeT.getText()));
        allOrdersTableList.getItems().clear();

    }

    @FXML
    void splitButton(ActionEvent event) {
        ArrayList<String> s = TableData.getTableFood(TableData.getSelectedTable());
        ArrayList<String> customerCount = new ArrayList<>(); // Keep track of Customer from the order of one tabel
        for (int i = 0 ; i < s.size() ; i ++){// getting number of Customers
            boolean ifNotExist = true;
            if(customerCount.size() == 0){
                customerCount.add(s.get(i).split("-")[3]);// getting customer no by splitting from String
                continue;
            }
            else{
                for(int j = 0 ; j < customerCount.size() ; j++){
                    if(customerCount.get(j).equals(s.get(i).split("-")[3])){ // checking if the customerexit in customer count or not
                        ifNotExist = false;
                        continue;
                    }
                }
            }
            if(ifNotExist){
                customerCount.add(s.get(i).split("-")[3]);
            }
        }

        // Now Splitting the bill
        Double[] sum = new Double[customerCount.size()];
        String splitBill = "";
        Arrays.fill(sum,0.0);
        if(s.size() != 0){
            for(int k = 0 ; k <customerCount.size(); k++){
                for (int i = 0 ; i < s.size() ; i++){
                    if(customerCount.get(k).equals(s.get(i).split("-")[3])){
                        String foodName = s.get(i).split("-")[0];
                        Double Pries = Double.parseDouble(s.get(i).split("-")[1]);
                        int anzahl = Integer.parseInt(s.   get(i).split("-")[2]);
                       System.out.println("Food " + foodName + " Total " + (Pries * anzahl));
                       sum[k] += (Pries * anzahl);
                   }
                }
                splitBill += String.format("C%s: %.1f",customerCount.get(k),sum[k]);
            }

            System.out.println("Total of each food is : " + splitBill);
            totalBillText.setText(splitBill);
        }
        else
            System.out.println("No Order");

    }

    @FXML
    void totalButton(ActionEvent event) throws SQLException {

        ArrayList<String> s = TableData.getTableFood(TableData.getSelectedTable());
        int sum = 0;
        if(s.size() != 0){
            for (int i = 0 ; i < s.size() ; i++){
                String foodName = s.get(i).split("-")[0];
                Double Pries = Double.parseDouble(s.get(i).split("-")[1]);
                int anzahl = Integer.parseInt(s.get(i).split("-")[2]);
                System.out.println("Food " + foodName + " Total " + (Pries * anzahl));
                sum += (Pries * anzahl);
            }
            System.out.println("Total of all food is : " + sum);
            totalBillText.setText(String.valueOf(sum));
//            Database_Controller connection = new Database_Controller();
//            Connection connectDB = connection.getConnection();
//            PreparedStatement ps = connectDB.prepareStatement("INSERT INTO bmiervx5yaquiotrakw1.orderhistory (waiterName, orderPrice) VALUES (?, ?)");
//            ps.setString(1,currentUser.getBenutzername());
//            ps.setString(2,String.valueOf(sum));
//            ps.execute();
        }
        else
            System.out.println("No Order");

    }
    @FXML
    void onGenerateInvoice(ActionEvent event) throws FileNotFoundException, UnsupportedEncodingException {
        ArrayList<String> s = TableData.getTableFood(TableData.getSelectedTable());
        ArrayList<String> customerCount = new ArrayList<>(); // Keep track of Customer from the order of one tabel
        for (int i = 0 ; i < s.size() ; i ++){// getting number of Customers
            boolean ifNotExist = true;
            if(customerCount.size() == 0){
                customerCount.add(s.get(i).split("-")[3]);// getting customer no by splitting from String
                continue;
            }
            else{
                for(int j = 0 ; j < customerCount.size() ; j++){
                    if(customerCount.get(j).equals(s.get(i).split("-")[3])){ // checking if the customerexit in customer count or not
                        ifNotExist = false;
                        continue;
                    }
                }
            }
            if(ifNotExist){
                customerCount.add(s.get(i).split("-")[3]);
            }
        }

        PrintWriter writer = new PrintWriter("Receipt.txt", "UTF-8");
        // Now Splitting the bill
        Double[] sum = new Double[customerCount.size()];
        String splitBill = "";
        Arrays.fill(sum,0.0);
        if(s.size() != 0){
            for(int k = 0 ; k <customerCount.size(); k++){
                writer.printf("%15s","My Restaurant");
                writer.println("-----------------------------------");
                writer.println("--------Customer#"+ (k + 1)+ "-----------");
                writer.printf("%10s%10s%10s%n","Food Name","Preis","Anzahl");
                for (int i = 0 ; i < s.size() ; i++){
                    if(customerCount.get(k).equals(s.get(i).split("-")[3])){
                        String foodName = s.get(i).split("-")[0];
                        Double Pries = Double.parseDouble(s.get(i).split("-")[1]);
                        int anzahl = Integer.parseInt(s.   get(i).split("-")[2]);
//                        System.out.println("Food " + foodName + " Total " + (Pries * anzahl));
                        writer.printf("%10s%10s%10s%n",foodName,Pries,anzahl);
                        sum[k] += (Pries * anzahl);
                    }
                }
//                splitBill += String.format("C%s: %.1f",customerCount.get(k),sum[k]);
                writer.println("-----------------------------------");
                writer.println("Your total bill amount is $" + sum[k]);
//                writer.println("Your total bill amount is $" + totalBillText.getText() + ".");

                writer.println(">----------------------------------->");
            }

            System.out.println("Total of each food is : " + splitBill);
            totalBillText.setText(splitBill);
        }
        writer.close();
    }

    @FXML
    void onchangeTable(ActionEvent event) {
        boolean flag  = TableData.transferTable(TableData.getSelectedTable(),Integer.parseInt(changeTableT.getText()));
        if(flag){
            allOrdersTableList.getItems().clear();
        }
        else {
            System.out.println("Table Occupied");
        }


    }

    public String  converttoDouble(String  val ){
       String temp =  String.format("%.1f",Double.parseDouble(val));
       temp = temp.replace(',','.');

        return temp;
    }

    public void printInvoice(){

    }

}