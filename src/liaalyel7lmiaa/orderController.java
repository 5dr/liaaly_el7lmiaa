/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liaalyel7lmiaa;

import Service.DB;
import com.jfoenix.controls.JFXButton;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
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
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import static javafx.scene.paint.Color.rgb;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import model.menu;
import model.table;

/**
 * FXML Controller class
 *
 * @author asd
 */
public class orderController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Pane pane;
    @FXML
    private TextField addition;
    @FXML
    private TextField name;

    @FXML
    private TextField text_drink;

    @FXML
    private TextField text_price, client, table_name;
    @FXML
    private JFXButton delet;

    public void addclint(ActionEvent e) throws IOException, SQLException {
        allDb.DB_connection();
        allDb.setclient(client.getText(), new tableController().getTable_id());
        allDb.DB_close();

    }

    public void loggout(ActionEvent e) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("table.fxml"));
        Scene scene = new Scene(root);
        Stage s = (Stage) ((Node) e.getSource()).getScene().getWindow();
        //Stage s=new Stage();
        s.setScene(scene);
        s.show();

    }

    public void addNew(ActionEvent e) throws IOException {

        try {
            allDb.DB_connection();
            new tableController().setDate(allDb.getlastTime(new tableController().getTable_id()));
            allDb.DB_close();
            Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
            Scene scene = new Scene(root);
            Stage s2 = (Stage) ((Node) e.getSource()).getScene().getWindow();
            //Stage s2 = new Stage();
            s2.setScene(scene);
            s2.show();
        } catch (SQLException ex) {
        }

    }
    int x = 0;

    public void withoutftora(ActionEvent e) throws IOException {

        try {
            if (addition.getText().equals("")) {
                x = 0;
            } else {
                x = Integer.parseInt(addition.getText());
            }

            allDb.DB_connection();
            for (int i = 0; i < currentorder.size(); i++) {
                System.out.println(currentorder.size());
                    if(allDb.del(allDb.getlastidWithTable(new tableController().getTable_id())-i)){
                    allDb.Deletorder(allDb.getlastidWithTable(new tableController().getTable_id())-i);
                        System.out.println(allDb.getlastidWithTable(new tableController().getTable_id())-i);
                    }
                }
            allDb.setclient("no name", new tableController().getTable_id());
            allDb.apdateTable(new tableController().getTable_id(), "available");
            allDb.updateaddtion(x, allDb.getlastTime(new tableController().getTable_id()));
            allDb.DB_close();

            JOptionPane.showMessageDialog(null, "السعر الكلى :" + (totalPrice + x) + " جنيه");

        } catch (SQLException ex) {
        }

        Parent root = FXMLLoader.load(getClass().getResource("table.fxml"));
        Scene scene = new Scene(root);
        Stage s = (Stage) ((Node) e.getSource()).getScene().getWindow();
        //Stage s=new Stage();
        s.setScene(scene);
        s.show();

    }

    public void ftora(ActionEvent e) throws IOException {

        if (name.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "ادخل اسم الكاشير من فضلك");
        } else {
            try {
                if (addition.getText().equals("")) {
                    x = 0;
                } else {
                    x = Integer.parseInt(addition.getText());
                }

                allDb.DB_connection();
                System.out.println(currentorder.size());
                for (int i = 0; i < currentorder.size(); i++) {
                    if(allDb.del(allDb.getlastidWithTable(new tableController().getTable_id())-i)){
                    allDb.Deletorder(allDb.getlastidWithTable(new tableController().getTable_id())-i);
                    System.out.println(currentorder.size());
                    
                    }
                }
                
                afterCurrentorder = allDb.getCurrentOrder(new tableController().getTable_id());
                PrinterJob pj = PrinterJob.getPrinterJob();
                pj.setPrintable(new BillPrintable(afterCurrentorder, (totalPrice + x), x, name.getText(), new tableController().getTable_id()), getPageFormat(pj));
                pj.print();
                pj.cancel();
               
                allDb.setclient("no name", new tableController().getTable_id());
                allDb.apdateTable(new tableController().getTable_id(), "available");
                allDb.updateaddtion(x, allDb.getlastTime(new tableController().getTable_id()));
                allDb.DB_close();

                JOptionPane.showMessageDialog(null, "السعر الكلى :" + (totalPrice + x) + " جنيه");

            } catch (SQLException ex) {
            } catch (PrinterException ex) {
            }

            Parent root = FXMLLoader.load(getClass().getResource("table.fxml"));
            Scene scene = new Scene(root);
            Stage s = (Stage) ((Node) e.getSource()).getScene().getWindow();
            //Stage s=new Stage();
            s.setScene(scene);
            s.show();
        }
    }

    DB allDb;
    List<menu> currentorder;
    List<menu> afterCurrentorder;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afterCurrentorder = new ArrayList<menu>();
        currentorder = new ArrayList<menu>();
        table_name.setText(new tableController().getTable_id() + "");
        allDb = new DB();
        try {
            allDb.DB_connection();
            client.setText(allDb.getclient(new tableController().getTable_id()));
            currentorder = allDb.getCurrentOrder(new tableController().getTable_id());
            allDb.DB_close();
            showAllorder(pane, currentorder);
        } catch (SQLException ex) {
        }

    }
    int totalPrice = 0;

    private void showAllorder(Pane p, List<menu> m) throws SQLException {
        allDb.DB_connection();
        System.out.println(allDb.getlastidWithTable(new tableController().getTable_id()));
        allDb.DB_close();
        System.out.println("tata");
        text_drink.setText(m.get(0).getName());
        allDb.DB_connection();
      if(!allDb.del(allDb.getlastidWithTable(new tableController().getTable_id())))
        totalPrice += m.get(0).getPrice();
        allDb.DB_close();
      text_price.setText(m.get(0).getPrice() + "");
        delet.setOnAction((event) -> {
            try {
                allDb.DB_connection();
                // allDb.Deletorder(allDb.getlastidWithTable(new tableController().getTable_id()));
                System.out.println(allDb.getlastidWithTable(new tableController().getTable_id()));
                System.out.println(allDb.getlastTimeWithid(allDb.getlastidWithTable(new tableController().getTable_id())));
                allDb.insertdel(allDb.getlastidWithTable(new tableController().getTable_id()),
                         allDb.getlastTimeWithid(allDb.getlastidWithTable(new tableController().getTable_id())));
                allDb.DB_close();

                text_drink.setStyle(delet.getStyle());
                text_drink.setText("اتمسح(" + text_drink.getText() + ")");
                text_price.setStyle(delet.getStyle());
                totalPrice -= m.get(0).getPrice();
//                Parent root = FXMLLoader.load(getClass().getResource("order.fxml"));
//                Scene scene = new Scene(root);
//                Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
//                //Stage s=new Stage();
//                s.setScene(scene);
//                s.show();
            } catch (Exception ex) {
                Logger.getLogger(orderController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        try {
            allDb.DB_connection();
            System.out.println(allDb.getlastidWithTable(new tableController().getTable_id()));
            if (allDb.del(allDb.getlastidWithTable(new tableController().getTable_id()))) {
                text_drink.setStyle(delet.getStyle());
                text_drink.setText("اتمسح(" + text_drink.getText() + ")");
                text_price.setStyle(delet.getStyle());
            }
            allDb.DB_close();
        } catch (Exception ex) {
            Logger.getLogger(orderController.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (int i = 1; i < m.size(); i++) {
            TextField drink = new TextField(m.get(i).getName());
            drink.setPrefHeight(text_drink.getPrefHeight());
            drink.setPrefWidth(text_drink.getPrefWidth());
            drink.setLayoutX(text_drink.getLayoutX());
            drink.setLayoutY(text_drink.getLayoutY() + (55 * (i)));
            drink.setFont(text_drink.getFont());
            drink.setAlignment(Pos.CENTER);
            drink.setDisable(true);
            pane.getChildren().add(drink);
            allDb.DB_connection();
      if(!allDb.del(allDb.getlastidWithTable(new tableController().getTable_id())-i))
            totalPrice += m.get(i).getPrice();
      allDb.DB_close();
            TextField price = new TextField(m.get(i).getPrice() + "");
            price.setPrefHeight(text_price.getPrefHeight());
            price.setPrefWidth(text_price.getPrefWidth());
            price.setLayoutX(text_price.getLayoutX());
            price.setLayoutY(text_price.getLayoutY() + (55 * (i)));
            price.setFont(text_price.getFont());
            price.setAlignment(Pos.CENTER);
            price.setDisable(true);
            pane.getChildren().add(price);

            JFXButton d = new JFXButton(delet.getText());
            d.setPrefHeight(delet.getPrefHeight());
            d.setPrefWidth(delet.getPrefWidth());
            d.setLayoutX(delet.getLayoutX());
            d.setLayoutY(delet.getLayoutY() + (55 * (i)));
            d.setFont(delet.getFont());
            d.setStyle(delet.getStyle());
            d.setTextFill(delet.getTextFill());
            d.setId(i + "");
            try {
                allDb.DB_connection();
                System.out.println(allDb.getlastidWithTable(new tableController().getTable_id()) - Integer.parseInt(d.getId()));
                if (allDb.del(allDb.getlastidWithTable(new tableController().getTable_id()) - Integer.parseInt(d.getId()))) {
                    drink.setStyle(delet.getStyle());
                    drink.setText("اتمسح(" + drink.getText() + ")");
                    price.setStyle(delet.getStyle());
                }
                allDb.DB_close();
            } catch (Exception ex) {
                Logger.getLogger(orderController.class.getName()).log(Level.SEVERE, null, ex);
            }

            d.setOnAction((event) -> {

                try {
                    allDb.DB_connection();
                    System.out.println(allDb.getlastidWithTable(new tableController().getTable_id()) - Integer.parseInt(d.getId()));
                    System.out.println(allDb.getlastTimeWithid(allDb.getlastidWithTable(new tableController().getTable_id()) - Integer.parseInt(d.getId())));
                    allDb.insertdel(allDb.getlastidWithTable(new tableController().getTable_id()) - Integer.parseInt(d.getId()),
                             allDb.getlastTimeWithid(allDb.getlastidWithTable(new tableController().getTable_id()) - Integer.parseInt(d.getId())));
                    allDb.DB_close();
                    drink.setStyle(delet.getStyle());
                    drink.setText("اتمسح(" + drink.getText() + ")");
                    price.setStyle(delet.getStyle());
                    totalPrice -= m.get(Integer.parseInt(d.getId())).getPrice();
//                allDb.DB_connection();
//                System.out.println(allDb.getlastidWithTable(new tableController().getTable_id())- Integer.parseInt(d.getId()));
//                
//                allDb.Deletorder(allDb.getlastidWithTable(new tableController().getTable_id())- Integer.parseInt(d.getId()));
//                allDb.DB_close();
//                Parent root = FXMLLoader.load(getClass().getResource("order.fxml"));
//                Scene scene = new Scene(root);
//                Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
//                //Stage s=new Stage();
//                s.setScene(scene);
//                s.show();
                } catch (Exception ex) {
                    Logger.getLogger(orderController.class.getName()).log(Level.SEVERE, null, ex);
                }

            });
            pane.getChildren().add(d);

            if (i > 3) {
                p.setPrefHeight(p.getPrefHeight() + 90);
            }

        }
        System.out.println("end");
    }
    static double bHeight = 0.0;

    public PageFormat getPageFormat(PrinterJob pj) {

        PageFormat pf = pj.defaultPage();
        Paper paper = pf.getPaper();
        double bodyHeight;
        if (currentorder.size() <= 3) {
            bodyHeight = 0.0;
        } else {
            bodyHeight = (currentorder.size() - 3) * 5.0;
        }

        double headerHeight = 10.0;
        double footerHeight = 5.0;
        double width = cm_to_pp(8);
        double height = cm_to_pp(headerHeight + bodyHeight + footerHeight);
        paper.setSize(width, height);
        paper.setImageableArea(0, 10, width, height - cm_to_pp(1));

        pf.setOrientation(PageFormat.PORTRAIT);
        pf.setPaper(paper);

        return pf;
    }

    protected static double cm_to_pp(double cm) {
        return toPPI(cm * 0.393600787);
    }

    protected static double toPPI(double inch) {
        return inch * 72d;
    }

}
