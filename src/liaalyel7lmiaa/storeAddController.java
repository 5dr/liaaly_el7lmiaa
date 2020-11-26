/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liaalyel7lmiaa;

import Service.DB;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.SQLException;
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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author asd
 */
public class storeAddController implements Initializable {

    @FXML
    private TextField name, amount;
    DB alldb = new DB();
public void loggout(ActionEvent e) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("store.fxml"));
        Scene scene = new Scene(root);
        Stage s = (Stage) ((Node) e.getSource()).getScene().getWindow();
        //Stage s=new Stage();
        s.setScene(scene);
        s.show();

    }
    
    public void AddStore(ActionEvent e) throws IOException {
        try {
            if(name.getText().equals("")&&amount.getText().equals("")){
          JOptionPane.showMessageDialog(null, "لم يتم ادخال الاسم او الكمية");

            }else{
            alldb.DB_connection();
            alldb.AddStored((name.getText()), Integer.parseInt(amount.getText()));
            alldb.DB_close();
            Parent root = FXMLLoader.load(getClass().getResource("store.fxml"));
            Scene scene = new Scene(root);
            Stage s = (Stage) ((Node) e.getSource()).getScene().getWindow();
            //Stage s = new Stage();
            s.setScene(scene);
            s.show();
            }} catch (Exception ex) {
        }

    }
      

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
