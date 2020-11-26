/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liaalyel7lmiaa;

import Service.DB;
import com.mysql.jdbc.Connection;
import java.io.IOException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import model.store;

/**
 * FXML Controller class
 *
 * @author asd
 */
public class storeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<store> table;

    @FXML
    private TableColumn<store, String>  name;

    @FXML
    private TableColumn<store, Integer> amount;
    
    ObservableList<store> ob = FXCollections.observableArrayList();

    @FXML
    private Pane store_pane;

    public void loggout(ActionEvent e) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Scene scene = new Scene(root);
        Stage s = (Stage) ((Node) e.getSource()).getScene().getWindow();
        //Stage s=new Stage();
        s.setScene(scene);
        s.show();

    }

    public void OpenAdd(ActionEvent e) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("storeAdd.fxml"));
        Scene scene = new Scene(root);
        Stage s = (Stage) ((Node) e.getSource()).getScene().getWindow();
        //Stage s=new Stage();
        s.setScene(scene);
        s.show();
        store_pane.setDisable(true);

    }

    private static int id;
    private static int Amount;

    public int getId() {
        return id;
    }

    public int getAmount() {
        return Amount;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/laialy_el7lmya", "root", "");

            Statement statement = connection.createStatement();

            ResultSet r = statement.executeQuery("SELECT id,name,amount FROM _stored");

            while (r.next()) {
                System.out.println(r.getInt("id"));
                System.out.println(r.getString("name"));
                System.out.println(r.getString("amount"));

                ob.add(new store(r.getInt("id"),
                        r.getString("name"), r.getInt("amount")));
                System.out.println(ob);
            }

            connection.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        table.setItems(ob);
        table.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (table.getSelectionModel().getSelectedItem() == null) {
                } else {
                    id = table.getSelectionModel().getSelectedItem().getId();
                    Amount = table.getSelectionModel().getSelectedItem().getAmount();
                    System.out.println(table.getSelectionModel().getSelectedItem().getName());
                    Parent root;
                    try {
                        root = FXMLLoader.load(getClass().getResource("increaseAndDecrease.fxml"));
                    Scene scene = new Scene(root);
                    Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    //Stage s=new Stage();
                    s.setScene(scene);
                    s.show();
                    } catch (IOException ex) {
                        Logger.getLogger(storeController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
            }
        });
    }

}
