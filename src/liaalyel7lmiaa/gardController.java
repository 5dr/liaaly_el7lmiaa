/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liaalyel7lmiaa;

import Service.DB;
import com.jfoenix.controls.JFXTimePicker;
import java.io.IOException;
import java.net.URL;
import java.security.Timestamp;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import model.elGard;
import model.store;

/**
 * FXML Controller class
 *
 * @author asd
 */
public class gardController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<elGard> table;

    @FXML
    private TableColumn<elGard, Timestamp> time;

    @FXML
    private TableColumn<elGard, Integer> tables;

    @FXML
    private TableColumn<elGard, String> type;

    @FXML
    private TableColumn<elGard, Integer> price;

    @FXML
    private TableColumn<elGard, Integer> name;
    ObservableList<elGard> ob = FXCollections.observableArrayList();
    
    @FXML
    private DatePicker date_from;

    @FXML
    private DatePicker date_to;

    @FXML
    private Label l_price;

    @FXML
    private Label l_total;
    
        @FXML
    private JFXTimePicker time_to;

    @FXML
    private JFXTimePicker time_from;

    public void loggout(ActionEvent e) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Scene scene = new Scene(root);
        Stage s = (Stage) ((Node) e.getSource()).getScene().getWindow();
        //Stage s=new Stage();
        s.setScene(scene);
        s.show();
    }
    public void elgard(ActionEvent e) throws IOException {
    
    if(date_from.getValue()==null&&date_to.getValue()==null){
        JOptionPane.showMessageDialog(null, "ادخل مدة بدايه و نهاية الجرداولا ");
}
    else if(date_from.getValue().compareTo(date_to.getValue())>0){
    JOptionPane.showMessageDialog(null, " بداية المدة اكبر من النهاية");
    }
    else
    {  System.out.println("ok");
        System.out.println(date_from.getValue());
        System.out.println(date_to.getValue());
        try {
            ZoneId defaultZoneId = ZoneId.systemDefault();
            allDb.DB_connection();
//           ob= allDb.gard(Date.from(date_from.getValue().atStartOfDay(defaultZoneId).toInstant())
//                    , Date.from(date_to.getValue().atStartOfDay(defaultZoneId).toInstant()));
           ob= allDb.gard(Date.from(date_from.getValue().atTime(time_from.getValue()).atZone(ZoneId.systemDefault())
            .toInstant()),Date.from(date_to.getValue().atTime(time_to.getValue()).atZone(ZoneId.systemDefault())
            .toInstant()));
           
           
           l_price.setText(allDb.gardsumprice(Date.from(date_from.getValue().atTime(time_from.getValue()).atZone(ZoneId.systemDefault())
            .toInstant()),Date.from(date_to.getValue().atTime(time_to.getValue()).atZone(ZoneId.systemDefault()).toInstant()) )+"");
           l_total.setText(allDb.gardcount(Date.from(date_from.getValue().atTime(time_from.getValue()).atZone(ZoneId.systemDefault())
            .toInstant()),Date.from(date_to.getValue().atTime(time_to.getValue()).atZone(ZoneId.systemDefault()).toInstant()) )+"");
           
           allDb.DB_close();
           price.setCellValueFactory(new PropertyValueFactory<>("price"));
          name.setCellValueFactory(new PropertyValueFactory<>("name"));
          type.setCellValueFactory(new PropertyValueFactory<>("type"));
          tables.setCellValueFactory(new PropertyValueFactory<>("table_id"));
          time.setCellValueFactory(new PropertyValueFactory<>("time"));
        table.setItems(ob);
        
            System.out.println(time_from.getValue().getClass());
            System.out.println(date_from.getValue().getClass());
            System.out.println(Date.from(date_from.getValue().atTime(time_from.getValue()).atZone(ZoneId.systemDefault())
            .toInstant()));
        
        } catch (SQLException ex) {
        }
        
    }
    }
    
    DB allDb;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Date date = new Date(System.currentTimeMillis());
            allDb=new DB();
            allDb.DB_connection();
            System.out.println(date);
            ob=allDb.gardToday(date);
            l_total.setText(allDb.gardcount(date)+"");
            l_price.setText(allDb.gardsumprice(date)+"");
            allDb.DB_close();
          price.setCellValueFactory(new PropertyValueFactory<>("price"));
          name.setCellValueFactory(new PropertyValueFactory<>("name"));
          type.setCellValueFactory(new PropertyValueFactory<>("type"));
          tables.setCellValueFactory(new PropertyValueFactory<>("table_id"));
          time.setCellValueFactory(new PropertyValueFactory<>("time"));
        table.setItems(ob);
                    
                    } catch (SQLException ex) {
        }
        
    }

}
