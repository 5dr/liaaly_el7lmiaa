/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liaalyel7lmiaa;

import Service.DB;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.table;

/**
 *
 * @author asd
 */
public class HomeController implements Initializable {
    
    
    @FXML
    private JFXButton order_button, stored_button, inventory_button;
    
    
    public void order(ActionEvent e) throws IOException {
        
        
       Parent root = FXMLLoader.load(getClass().getResource("table.fxml"));
       Scene scene = new Scene(root);
       Stage s = (Stage) ((Node) e.getSource()).getScene().getWindow();
       //Stage s=new Stage();
       s.setScene(scene);
       s.show();
        
    }
    public void gard(ActionEvent e) throws IOException {
        
        
       Parent root = FXMLLoader.load(getClass().getResource("gard.fxml"));
       Scene scene = new Scene(root);
       Stage s = (Stage) ((Node) e.getSource()).getScene().getWindow();
       //Stage s=new Stage();
       s.setScene(scene);
       s.show();
        
    }
     public void admin(ActionEvent e) throws IOException {
        
        
       Parent root = FXMLLoader.load(getClass().getResource("admin.fxml"));
       Scene scene = new Scene(root);
       Stage s = (Stage) ((Node) e.getSource()).getScene().getWindow();
       //Stage s=new Stage();
       s.setScene(scene);
       s.show();
        
    }
     public void stored(ActionEvent e) throws IOException {
        
        
       Parent root = FXMLLoader.load(getClass().getResource("store.fxml"));
       Scene scene = new Scene(root);
       Stage s = (Stage) ((Node) e.getSource()).getScene().getWindow();
       //Stage s=new Stage();
       s.setScene(scene);
       s.show();
        
    }
    
    
   //static List<table> t=new ArrayList<table>();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
    
}
}
