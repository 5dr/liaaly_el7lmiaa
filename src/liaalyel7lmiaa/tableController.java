/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liaalyel7lmiaa;

import Service.DB;
import com.jfoenix.controls.JFXButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import static javafx.scene.paint.Color.color;
import static javax.swing.text.StyleConstants.Bold;
import java.lang.System;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import static javafx.scene.paint.Color.rgb;
import javafx.stage.Stage;
import model.table;

/**
 *
 * @author asd
 */
public class tableController implements Initializable {

    @FXML
    private Pane order_pane, available_pane, open_pane;
    @FXML
    private ScrollPane scroll_open, scroll_ava;

    public void loggout(ActionEvent e) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Scene scene = new Scene(root);
        Stage s = (Stage) ((Node) e.getSource()).getScene().getWindow();
        //Stage s=new Stage();
        s.setScene(scene);
        s.show();

    }

    public void open(ActionEvent e) {
        scroll_ava.setVisible(false);
        scroll_open.setVisible(true);

    }

    public void available(ActionEvent e) {

        scroll_ava.setVisible(true);
        scroll_open.setVisible(false);

    }
    private static int table_id;
    private static int order_id;
    private static Timestamp date;

    public int getTable_id() {
        return table_id;
    }

    public int getorder_id() {
        return order_id;
    }

    public Timestamp getDate() {
        return date;
    }

    public static void setDate(Timestamp date) {
        tableController.date = date;
    }

//    public void changeStatusTable(ActionEvent e) {
//        int x = e.getSource().toString().length();
//        char s = e.getSource().toString().charAt(x - 6);
////        System.out.println(s);
////        System.out.println(e.getSource().toString());
//        if (s == 'م') {
//            System.out.println("ava");
////            int x1 = e.getSource().toString().length();
////            String s1;
////            if(x1==57){
////         s1 = e.getSource().toString().substring(x - 8, x-7);
////}else{              s1 = e.getSource().toString().substring(x - 9, x-7);
////}
//            //  System.out.println("lllllllllllllllllllllllll : "+s1);
//            // table_id=Integer.parseInt(s1);
//            //  System.out.println(s1);
//            //    System.out.println(e.getSource().toString());
//            //  System.out.println("xxxxxxxxxxxxx : "+x1);
//
//            try {
//                allDb.DB_connection();
//                //    allDb.apdateTable(Character.getNumericValue(s1), "open");
//                // table_id = Integer.parseInt(s1);
//                allDb.apdateTableLastOrderId(table_id, allDb.getlastid() + 1);
//                allDb.DB_close();
//                date = new Timestamp(System.currentTimeMillis());
//                Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
//                Scene scene = new Scene(root);
//                Stage s2 = (Stage) ((Node) e.getSource()).getScene().getWindow();
//                //Stage s2 = new Stage();
//                s2.setScene(scene);
//                s2.show();
//            } catch (Exception ex) {
//                System.out.println(ex);
//            }
//
//        } else {
//            System.out.println("open");
//            int x1 = e.getSource().toString().length();
//            String s1;
//            if (x1 == 58) {
//                s1 = e.getSource().toString().substring(x - 9, x - 8);
//            } else {
//                s1 = e.getSource().toString().substring(x - 10, x - 8);
//            }
//            System.out.println(s1);
//            System.out.println("xxxxxxxxxxxxx : " + x1);
//            System.out.println(e.getSource().toString());
//            try {
//                table_id = Integer.parseInt(s1);
//                allDb.DB_connection();
//                order_id = allDb.Selctlastidorder(table_id);
//                allDb.DB_close();
//                System.out.println("tttttttttttttttttttttt : " + order_id);
//                System.out.println("tttttttttttttttttttttt : " + table_id);
//                System.out.println(getDate());
//                Parent root = FXMLLoader.load(getClass().getResource("order.fxml"));
//                Scene scene = new Scene(root);
//                Stage s2 = (Stage) ((Node) e.getSource()).getScene().getWindow();
//                //Stage s2 = new Stage();
//                s2.setScene(scene);
//                s2.show();
//
//            } catch (Exception ex) {
//                System.out.println(ex);
//            }
//        }
//
//    }

    DB allDb;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<table> tableAvailable;
        List<table> tableOpen;

        tableAvailable = new ArrayList<table>();
        tableOpen = new ArrayList<table>();

        allDb = new DB();

        try {
            allDb.DB_connection();
            tableAvailable = allDb.SelctAllAvailableTable();
            tableOpen = allDb.SelctAllOpenTable();
            makeTable(available_pane, tableAvailable);
            makeTable(open_pane, tableOpen);
            allDb.DB_close();
        } catch (SQLException ex) {
        }
    }

    private void makeTable(Pane p, List<table> table) throws SQLException {

        double lengh = table.size();
        int count = 0;
        int h = (int) Math.ceil(lengh / 5);
        int def = h - 3;

        if (def > 0) {
            p.setPrefHeight(p.getPrefHeight() + def * 226);
        }

        int colmn = (int) ((lengh < 6) ? lengh : 5);
        int row = (int) (Math.ceil(lengh / 5));
        int mod = (int) (lengh - ((int) Math.floor(lengh / 5) * 5));
        System.out.println(colmn + "  " + row + "   " + mod);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < colmn; j++) {
                if (i == row - 1 && j > mod - 1 && mod != 0) {
                } else {
                    Image image = new Image(getClass().getResourceAsStream("2.png"));
                    ImageView Iview = new ImageView(image);
                    Iview.setFitHeight(150.0);
                    Iview.setFitWidth(217);
                    Iview.setPickOnBounds(true);
                    Iview.setPreserveRatio(true);

                    JFXButton l = new JFXButton();
                    l.setButtonType(JFXButton.ButtonType.RAISED);
                    l.setContentDisplay(ContentDisplay.TOP);
                    l.setLayoutX(j * 200 + 35);
                    l.setLayoutY(i * 226 + 10);
                    l.setPrefHeight(202.0);
                    l.setPrefHeight(165.0);
                    l.setStyle("-fx-background-color: #e0d8c3;");
                    System.out.println(table.get(count).getStatus());
                    String s = table.get(count).getStatus();
                    String status = (table.get(count).getStatus().equals("open")) ? "مفتوحة" : "متاحة";
                    l.setId(table.get(count).getTable_id() + "");
                    l.setText(table.get(count++).getTable_id() + " " + status);
                    l.setTextFill(rgb(54, 27, 20));
                    l.setGraphic(Iview);
                    l.setFont(javafx.scene.text.Font.font("System Bold", 22));

                    l.setOnAction(e -> {
                        if (s.equals("available")) {
                            System.out.println("iiiiiiiiiiiidddddddddd : " + l.getId());

                            try {
                                allDb.DB_connection();
                               // allDb.apdateTable(Integer.parseInt(l.getId()), "open");
                                table_id = Integer.parseInt(l.getId());
                                allDb.apdateTableLastOrderId(table_id, allDb.getlastid() + 1);
                                allDb.DB_close();
                                date = new Timestamp(System.currentTimeMillis());
                                Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
                                Scene scene = new Scene(root);
                                Stage s2 = (Stage) ((Node) e.getSource()).getScene().getWindow();
                                //Stage s2 = new Stage();
                                s2.setScene(scene);
                                s2.show();
                            } catch (Exception ex) {
                                System.out.println(ex);
                            }

                        } else {
                            System.out.println("iiiiiiiiiiiidddddddddd : " + l.getId());

            try {
                table_id = Integer.parseInt(l.getId());
                allDb.DB_connection();
                order_id = allDb.Selctlastidorder(table_id);
                allDb.DB_close();
                System.out.println("tttttttttttttttttttttt : " + order_id);
                System.out.println("tttttttttttttttttttttt : " + table_id);
                System.out.println(getDate());
                Parent root = FXMLLoader.load(getClass().getResource("order.fxml"));
                Scene scene = new Scene(root);
                Stage s2 = (Stage) ((Node) e.getSource()).getScene().getWindow();
                //Stage s2 = new Stage();
                s2.setScene(scene);
                s2.show();

            } catch (Exception ex) {
                System.out.println(ex);
            
                        
                        }
                        }
//                        System.out.println("iiiiiiiiiiiidddddddddd : "+l.getId());
//                        changeStatusTable(e);

                    });
                    p.getChildren().add(l);
                }
            }
        }

    }

}
