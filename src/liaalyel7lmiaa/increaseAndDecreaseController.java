/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liaalyel7lmiaa;

import Service.DB;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asd
 */
public class increaseAndDecreaseController implements Initializable {

    /**
     * Initializes the controller class.
     */
    DB alldb = new DB();
    @FXML
    private TextField amount;
    private int id;
    private int Amount;
    
      public void loggout(ActionEvent e) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Scene scene = new Scene(root);
        Stage s = (Stage) ((Node) e.getSource()).getScene().getWindow();
        //Stage s=new Stage();
        s.setScene(scene);
        s.show();

    }
    
    
    public void increase(ActionEvent e) throws IOException {
        try {
           Amount+=Integer.parseInt(amount.getText());
            alldb.DB_connection();
            alldb.updateStored(Amount, id);
            alldb.DB_close();
            Parent root = FXMLLoader.load(getClass().getResource("store.fxml"));
            Scene scene = new Scene(root);
            Stage s = (Stage) ((Node) e.getSource()).getScene().getWindow();
            //Stage s = new Stage();
            s.setScene(scene);
            s.show();
        } catch (Exception ex) {
        }

    }    
     public void decrese(ActionEvent e) throws IOException {
        try {
           Amount-=Integer.parseInt(amount.getText());
            alldb.DB_connection();
            alldb.updateStored(Amount, id);
            alldb.DB_close();
            Parent root = FXMLLoader.load(getClass().getResource("store.fxml"));
            Scene scene = new Scene(root);
            Stage s = (Stage) ((Node) e.getSource()).getScene().getWindow();
            //Stage s = new Stage();
            s.setScene(scene);
            s.show();
        } catch (Exception ex) {
        }

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id=new storeController().getId();
        Amount=new storeController().getAmount();
        System.out.println(id+"  "+Amount);
        
    }    
    
}
