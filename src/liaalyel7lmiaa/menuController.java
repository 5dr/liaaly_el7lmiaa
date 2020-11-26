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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import model.menu;
import model.order;
import model.table;

/**
 * FXML Controller class
 *
 * @author asd
 */
public class menuController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label order, price,id;
    @FXML
    private MenuButton drinks, B_torky;

    @FXML
    private MenuButton B_ice_coffee;

    @FXML
    private MenuButton B_fresh_juice;

    @FXML
    private MenuButton B_hot_drink;
    @FXML
    private MenuButton B_soda;

    @FXML
    private MenuButton B_smoozy;

    @FXML
    private MenuButton B_milk_shake, B_cocktail, B_spacial, B_soft;
    @FXML
    private CheckMenuItem check;
    
        @FXML
    private Label amount;

    DB allDb;
    List<order>allOrder=new ArrayList<order>();
    public void loggout(ActionEvent e) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("table.fxml"));
        Scene scene = new Scene(root);
        Stage s = (Stage) ((Node) e.getSource()).getScene().getWindow();
        //Stage s=new Stage();
        s.setScene(scene);
        s.show();

    }
    int a=0;
    public void addamount(ActionEvent e) {
    Button s =(Button) e.getSource();
        System.out.println(s.getId());
    switch(s.getId()){
        case "one":amount.setText("1");a=1;break;
        case "two":amount.setText("2");a=2;break;
        case "three":amount.setText("3");a=3;break;
        case "four":amount.setText("4");a=4;break;
        case "five":amount.setText("5");a=5;break;
        case "six":amount.setText("6");a=6;break;
        case "siven":amount.setText("7");a=7;break;
        case "eight":amount.setText("8");a=8;break;
        case "nine":amount.setText("9");a=9;break;
        case "delet":amount.setText("0");a=0;break;
    }
    }
    public void addOrder(ActionEvent e) {
        System.out.println(a);
        System.out.println(id.getText());
        if(order.getText().equals("")){
        
          JOptionPane.showMessageDialog(null, "يجب اختيار مشروب اولا قبل عمل الاضافة");
        }
        else if(a==0){
            JOptionPane.showMessageDialog(null, "عدد المشروبات صفر ");
                }
        else{
            //allDb.DB_connection();
            
            for(int i=0;i<a;i++){
            allOrder.add(new order(0,0,Integer.parseInt(id.getText()),new tableController().getTable_id(),new tableController().getDate()));
                System.out.println(a);
            }
          // allDb.Addorder(new tableController().getTable_id(), Integer.parseInt(id.getText()),new tableController().getDate());
          //  allDb.DB_close();
        
        }
        
    }

    public void tmam(ActionEvent e) throws IOException, SQLException{
    
        if(order.getText().equals("")){
        
          JOptionPane.showMessageDialog(null, "يجب اختيار مشروب اولا قبل عمل الخروج");
        }else{
        
        allDb.DB_connection();
        allDb.apdateTable(new tableController().getTable_id(), "open");  
        allDb.Addorder(allOrder);
        allDb.DB_close();
        Parent root = FXMLLoader.load(getClass().getResource("table.fxml"));
        Scene scene = new Scene(root);
        Stage s = (Stage) ((Node) e.getSource()).getScene().getWindow();
        //Stage s=new Stage();
        s.setScene(scene);
        s.show();
    
    }}
    public void addmilk(ActionEvent e) {
        
        List<menu> drink = new ArrayList<menu>();
        try {
            allDb.DB_connection();
            drink = allDb.SelctMenu("hot drink");
            allDb.DB_close();

        } catch (SQLException ex) {
        }
        if (order.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "يجب اختيار من المشروبات الساخنه اولا");
            check.setSelected(false);
        } else if (check(order.getText(), drink)) {
            JOptionPane.showMessageDialog(null, "هذا ليس من المشروبات الساخنه يجب اختيار من المشروبات الساخنه فقط");
            check.setSelected(false);
        } else {
            if (check.isSelected()) {
                int inc = Integer.parseInt(price.getText()) + 4;
                switch(Integer.parseInt(id.getText())){
                    case 16:id.setText("87"); break;
                    case 17:id.setText("88"); break;
                    case 18:id.setText("89"); break;
                    case 19:id.setText("90"); break;
                    case 20:id.setText("91"); break;
                    case 21:id.setText("92"); break;
                    case 22:id.setText("93"); break;
                    case 29:id.setText("94"); break;
                    case 30:id.setText("95"); break;
                    case 31:id.setText("96"); break;
                    case 32:id.setText("97"); break;
                    case 33:id.setText("98"); break;                
                }
                price.setText(inc + "");
            } else {
                int inc = Integer.parseInt(price.getText()) - 4;
                price.setText(inc + "");
                switch(Integer.parseInt(id.getText())){
                    case 87:id.setText("16"); break;
                    case 88:id.setText("17"); break;
                    case 89:id.setText("18"); break;
                    case 90:id.setText("19"); break;
                    case 91:id.setText("20"); break;
                    case 92:id.setText("21"); break;
                    case 93:id.setText("22"); break;
                    case 94:id.setText("29"); break;
                    case 95:id.setText("30"); break;
                    case 96:id.setText("31"); break;
                    case 97:id.setText("32"); break;
                    case 98:id.setText("33"); break; 
            }}
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<menu> drink = new ArrayList<menu>();
        List<menu> torky = new ArrayList<menu>();
        List<menu> ice_coffee = new ArrayList<menu>();
        List<menu> fresh_juice = new ArrayList<menu>();
        List<menu> hot_drink = new ArrayList<menu>();
        List<menu> soda = new ArrayList<menu>();
        List<menu> smoozy = new ArrayList<menu>();
        List<menu> milk_shake = new ArrayList<menu>();
        List<menu> cocktail = new ArrayList<menu>();
        List<menu> special = new ArrayList<menu>();
        List<menu> soft_drink = new ArrayList<menu>();
        allDb = new DB();
        try {
            allDb.DB_connection();
            drink = allDb.SelctMenu("hot drink");
            torky = allDb.SelctMenu("torky");
            ice_coffee = allDb.SelctMenu("ice coffee");
            fresh_juice = allDb.SelctMenu("fresh juice");
            hot_drink = allDb.SelctMenu("hot drinks");
            soda = allDb.SelctMenu("soda");
            smoozy = allDb.SelctMenu("smoozy");
            milk_shake = allDb.SelctMenu("milk shake");
            cocktail = allDb.SelctMenu("fruit cocktail");
            special = allDb.SelctMenu("special");
            soft_drink = allDb.SelctMenu("soft drinks");
            allDb.DB_close();
            makeMenu(drinks, drink);
            makeMenu(B_cocktail, cocktail);
            makeMenu(B_fresh_juice, fresh_juice);
            makeMenu(B_hot_drink, hot_drink);
            makeMenu(B_ice_coffee, ice_coffee);
            makeMenu(B_milk_shake, milk_shake);
            makeMenu(B_soda, soda);
            makeMenu(B_smoozy, smoozy);
            makeMenu(B_soft, soft_drink);
            makeMenu(B_spacial, special);
            makeMenu(B_torky, torky);
            //drinks.setStyle("-fx-mark-color: red;");

        } catch (SQLException ex) {
        }
            
    }

    private void makeMenu(MenuButton mb, List<menu> m) {
        System.out.println("in");
        System.out.println(m.get(0).getName());
        for (int i = 0; i < m.size(); i++) {
            System.out.println(i);
            String s = m.get(i).getName();
            int p = m.get(i).getPrice();
            int _id = m.get(i).getMenu_id();
            MenuItem mi = new MenuItem();
            mi.setText(s);
            mi.setStyle("-fx-text-fill: black;-fx-font: 20px \"Arial\";");
            mi.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    order.setText(s);
                    price.setText(String.valueOf(p));
                    check.setSelected(false);
                    id.setText(String.valueOf(_id));
                    
                    
                }
            });
            mb.getItems().add(mi);
            System.out.println(i);
        }
    }

    private boolean check(String s, List<menu> m) {
        for (int i = 0; i < m.size(); i++) {
            if (m.get(i).getName().equals(s)) {
                return false;
            }
            System.out.println(m.get(i).getName());
            System.out.println(s);

        }
        return true;
    }
}
