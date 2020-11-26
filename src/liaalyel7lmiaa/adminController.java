/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liaalyel7lmiaa;

import Service.DB;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author asd
 */
public class adminController implements Initializable{

     public void loggout(ActionEvent e) throws IOException {
        
        
       Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
       Scene scene = new Scene(root);
       Stage s = (Stage) ((Node) e.getSource()).getScene().getWindow();
       //Stage s=new Stage();
       s.setScene(scene);
       s.show();
        
    }
     DB allDb;
      public void addTable(ActionEvent e) throws IOException {
         allDb=new DB();
          try {
             allDb.DB_connection();
             if(allDb.AddTable()){JOptionPane.showMessageDialog(null, ""
                     +"تم اضافة الطرابيزة");}
             allDb.DB_close();
         } catch (SQLException ex) {
             try {
                 allDb.DB_close();
             } catch (SQLException ex1) {
             }

         }    
    }
      public void deletTable(ActionEvent e) throws IOException {
         allDb=new DB();
          try {
             allDb.DB_connection();
             if(allDb.deletTable()){JOptionPane.showMessageDialog(null, ""
                     +"تم حذف الطرابيزة");}
             allDb.DB_close();
         } catch (SQLException ex) {
             try {
                 allDb.DB_close();
             } catch (SQLException ex1) {
             }

         }    
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
    
}
