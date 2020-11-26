/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liaalyel7lmiaa;

import Service.DB;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author asd
 */
public class LiaalyEl7lmiaa extends Application {

    static int run = 0;

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.getIcons().add(new Image(LiaalyEl7lmiaa.class.getResourceAsStream("logo.jpg")));
        stage.setTitle("LiaalyEl7lmiaa"); 

        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws PrinterException {
        
        
        
        launch(args);

//               ProcessBuilder P1 = new ProcessBuilder("C:\\xampp\\mysql\\bin\\mysqld.exe");
//        try {
//////
//            P1.start();
// } catch (IOException ex) {
////            System.out.println("process");
//        }
//            launch(args);
//        try {
////
//             Runtime.getRuntime().exec("taskkill /IM mysqld.exe /F");
//        } catch (IOException ex) {
//        }
        }
    }


