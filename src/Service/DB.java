/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import com.mysql.jdbc.Connection;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.sql.Timestamp;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;
import model.elGard;
import model.menu;
import model.order;
import model.table;

/**
 *
 * @author asd
 */
public class DB {

    private static Connection connection;
    private static Statement statement;

    /////////////////Connection///////////////////////
    public void DB_connection() throws SQLException {

        try {
            connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/laialy_el7lmya", "root", "");

            System.out.println("connected");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void DB_close() throws SQLException {

        try {
            connection.close();
            System.out.println("closed");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public boolean isServerUp(int port) {
        boolean isUp = false;
        try {
            Socket socket = new Socket("127.0.0.1", port);
//            Socket socket = new Socket("localhost", port); <- also this
            // Server is up
            isUp = true;
            socket.close();
        } catch (IOException e) {
            // Server is down
        }
        return isUp;
    }
    /////////////////Connection///////////////////////

    ///////////////////////table/////////////////////////////
    public List<table> SelctAllTable() throws SQLException {
        List<table> t = new ArrayList<table>();

        try {
            statement = connection.createStatement();
            ResultSet r = statement.executeQuery("SELECT * FROM `_table`");

            while (r.next()) {
                // new table(r.getInt("table_id"));
                System.out.println(r.getInt("table_id"));
                t.add(new table(r.getInt("table_id"), r.getString("status")));

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return t;
    }

    public List<table> SelctAllOpenTable() throws SQLException {
        List<table> t = new ArrayList<table>();

        try {
            statement = connection.createStatement();
            ResultSet r = statement.executeQuery(""
                    + "SELECT * FROM `_table` WHERE `status`"
                    + " LIKE 'open' ORDER BY `table_id` ASC");

            while (r.next()) {
                // new table(r.getInt("table_id"));
                System.out.println(r.getInt("table_id"));
                t.add(new table(r.getInt("table_id"), r.getString("status")));

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return t;
    }

    public List<table> SelctAllAvailableTable() throws SQLException {
        List<table> t = new ArrayList<table>();

        try {
            statement = connection.createStatement();
            ResultSet r = statement.executeQuery(""
                    + "SELECT * FROM `_table` WHERE `status`"
                    + " LIKE 'available' ORDER BY `table_id` ASC");

            while (r.next()) {
                // new table(r.getInt("table_id"));
                System.out.println(r.getInt("table_id"));
                t.add(new table(r.getInt("table_id"), r.getString("status")));

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return t;
    }

    public boolean AddTable() throws SQLException {
        boolean bool = false;
        int max = 0;
        try {
              
        
            Statement statement1 = connection.createStatement();
            ResultSet i = statement1.executeQuery("SELECT MAX(table_id) FROM _table");
            while (i.next()) {
                max = i.getInt("MAX(table_id)");
            }
            
            statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO `_table` (`table_id`, `status`) VALUES ("+(max+1)+", 'available');");
            bool = true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return bool;
    }

    public boolean deletTable() throws SQLException {

        boolean bool = false;
        int max = 0;
        try {
            Statement statement1 = connection.createStatement();
            ResultSet i = statement1.executeQuery("SELECT MAX(table_id) FROM _table");
            while (i.next()) {
                max = i.getInt("MAX(table_id)");
            }
            statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM `_table` WHERE `_table`.`table_id` =" + max);
            bool = true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return bool;
    }

    public boolean apdateTable(int i, String status) throws SQLException {

        boolean bool = false;
        try {

            statement = connection.createStatement();
            statement.executeUpdate("UPDATE `_table` SET `status` = '" + status + "' WHERE `_table`.`table_id` = " + i + ";");
            bool = true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return bool;
    }

    public boolean apdateTableLastOrderId(int i, int id) throws SQLException {

        boolean bool = false;
        try {

            statement = connection.createStatement();
            statement.executeUpdate("UPDATE `_table` SET `last_id` = '" + id + "' WHERE `_table`.`table_id` = " + i + ";");
            bool = true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return bool;
    }

    public int Selctlastidorder(int id) throws SQLException {
        int i = 0;
        try {
            statement = connection.createStatement();
            ResultSet r = statement.executeQuery(""
                    + "SELECT last_id FROM `_table` WHERE `table_id` = " + id + "");

            while (r.next()) {
                // new table(r.getInt("table_id"));
                System.out.println(r.getInt("last_id"));
                i = r.getInt("last_id");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"هنا"+ ex);
        }
        return i;
    }
 
    
    public String getclient(int id) throws SQLException{
    
         String i = "";
        try {
            statement = connection.createStatement();
            ResultSet r = statement.executeQuery(""
                    + "SELECT name FROM `_table` WHERE `table_id` = " + id + "");

            while (r.next()) {
                // new table(r.getInt("table_id"));
                System.out.println(r.getString("name"));
                i = r.getString("name");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "هنا"+ex);
        }
        return i;
        
    }
    
     public boolean setclient(String name ,int id) throws SQLException{
    
         boolean i = false;
        try {
            statement = connection.createStatement();
           statement.executeUpdate( "UPDATE `_table` SET `name` = '"+name+"' WHERE `_table`.`table_id` = "+id+";");

           
      i=true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return i;
        
    }
    
  // SELECT * FROM `_table` ORDER BY `table_id` ASC 
///////////////////////table/////////////////////////////

    ////////////////////menu////////////////////////
     
    public List<menu> SelctMenu(String type) throws SQLException {
        List<menu> t = new ArrayList<menu>();
        System.out.println(type);

        try {
            statement = connection.createStatement();
            ResultSet r = statement.executeQuery("SELECT * FROM `_menu` WHERE `type` LIKE '" + type + "'");

            while (r.next()) {
                System.out.println(r.getInt("menu_id") + "   " + r.getString("name"));
                t.add(new menu(r.getInt("menu_id"), r.getString("name"), r.getInt("price"), r.getString("type")));

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " ins" + ex);
        }
        return t;
    }

    ////////////////////menu////////////////////////
    //////////////////stored/////////////////////
    public boolean AddStored(String name, int amount) throws SQLException {
        boolean bool = false;
        try {
            statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO `_stored` (`id`, `name`, `amount`) VALUES (null, '" + name + "', '" + amount + "');");
            bool = true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return bool;
    }

    public boolean DeletStored(String name) throws SQLException {
        boolean bool = false;
        try {
            statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM `_stored` WHERE `_stored`.`name` = '" + name + "'");
            bool = true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return bool;
    }

    public boolean updateStored(int amount, int id) throws SQLException {
        boolean bool = false;
        try {
            statement = connection.createStatement();
            statement.executeUpdate("UPDATE `_stored` SET `amount` = '" + amount + "' WHERE `_stored`.`id` = " + id + "");
            bool = true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return bool;
    }
    ///////////////////////////////store////////////////

    /////////////////////////order//////////////////////
    public boolean Addorder(List<order> all) throws SQLException {
        boolean bool = false;
        int max = 0;
        int count = 0;

        try {
            Statement statement1 = connection.createStatement();
            ResultSet i = statement1.executeQuery("SELECT MAX(order_id) FROM _order");
            while (i.next()) {
                max = i.getInt("MAX(order_id)");
                System.out.println(max);
                System.out.println("tata");
            }
            System.out.println(max);
            String time = null;
            ResultSet i1 = statement1.executeQuery("SELECT count,time FROM _order where order_id=" + max + "");
            while (i1.next()) {
                count = i1.getInt("count");
                time = i1.getString("time");
                System.out.println(count);
                System.out.println(time);
                System.out.println("tata");
            }
            String y = time.substring(0, 4);
            String m = time.substring(5, 7);
            String d = time.substring(8, 10);
            System.out.println(y);
            System.out.println(m);
            System.out.println(d);
             SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println(formatter.format(all.get(0).getTime()));
            String s = formatter.format(all.get(0).getTime());
            System.out.println(Integer.parseInt(s.substring(0, 4)));
            if ((Integer.parseInt(y) == Integer.parseInt(s.substring(0, 4)))
                    || (Integer.parseInt(y) == Integer.parseInt(s.substring(5, 7)))
                    || (Integer.parseInt(y) == Integer.parseInt(s.substring(8, 10)))) {
                count++;
                System.out.println(count);
            } else {
                count = 1;
            }
         for(int z=0;z<all.size();z++){
      
            statement = connection.createStatement();
            System.out.println(all.get(z).getTable_id() + "     " + all.get(z).getMenu_id());
            statement.executeUpdate("INSERT INTO `_order` (`order_id`, `count`, `table_id`, `menu_id`, `time`) VALUES (null, '" + count + "', '" + all.get(z).getTable_id() + "', '" + all.get(z).getMenu_id() + "', '" + formatter.format(all.get(z).getTime()) + "');");
         }bool = true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        
        return bool;
    }
    public List<menu> getCurrentOrder(int table_id) throws SQLException {
        Date max = null;
        List<menu> t = new ArrayList<menu>();
        statement = connection.createStatement();

        try {

            ResultSet r = statement.executeQuery("SELECT _order.menu_id,_menu.name,_menu.price,_menu.type"
                    + " FROM _order INNER JOIN _menu ON _order.menu_id=_menu.menu_id "
                    + "WHERE _order.time=( SELECT MAX(time)FROM _order WHERE table_id=" + table_id + ") ORDER BY `_order`.`order_id` DESC");
            while (r.next()) {
                System.out.println(r.getInt("menu_id") + "   " + r.getString("name"));
                t.add(new menu(r.getInt("menu_id"), r.getString("name"), r.getInt("price"), r.getString("type")));

            }
//            System.out.println(max);
//            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            Date date = new Date(System.currentTimeMillis());
            // System.out.println(formatter.format(date));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return t;
    }
    public Timestamp getlastTime(int table_id) {
        Timestamp d = null;

        try {
            statement = connection.createStatement();
            ResultSet r = statement.executeQuery("SELECT MAX(time) FROM _order WHERE table_id='" + table_id + "'");

            while (r.next()) {
                d = r.getTimestamp("MAX(time)");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " ins" + ex);
        }
        return d;
    }
    public Timestamp getlastTimeWithid(int id) {
        Timestamp d = null;

        try {
            statement = connection.createStatement();
            ResultSet r = statement.executeQuery("SELECT MAX(time) FROM _order WHERE order_id='" + id + "'");

            while (r.next()) {
                d = r.getTimestamp("MAX(time)");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " ins" + ex);
        }
        return d;
    }
    public int getlastid() {
        int d = 0;

        try {
            statement = connection.createStatement();
            ResultSet r = statement.executeQuery("SELECT MAX(order_id) FROM _order");

            while (r.next()) {
                d = r.getInt("MAX(order_id)");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " ins" + ex);
        }
        return d;
    }
    public int getlastidWithTable(int id) {
        int d = 0;

        try {
            statement = connection.createStatement();
            ResultSet r = statement.executeQuery("SELECT MAX(order_id) FROM _order WHERE table_id = "+id+"");

            while (r.next()) {
                d = r.getInt("MAX(order_id)");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " ins" + ex);
        }
        return d;
    }
    public boolean Deletorder(int id) throws SQLException {
        boolean bool = false;
        try {
            statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM `_order` WHERE `_order`.`order_id` = '" + id + "'");
            bool = true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return bool;
    }
    public boolean updateaddtion(int i,Timestamp t) throws SQLException {
        boolean bool = false;
        try {
            statement = connection.createStatement();
            statement.executeUpdate("UPDATE `_order` SET `additional` = '"+i+"' WHERE `_order`.`time` = '"+t+"'");
            bool = true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return bool;
    }
    public boolean del(int table){
        int d = 0;
        boolean b = false;

        try {
            statement = connection.createStatement();
            ResultSet r = statement.executeQuery("SELECT del FROM `_order` WHERE `order_id` = "+table+"");

            while (r.next()) {
                d = r.getInt("del");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " ins" + ex);
        }
        if(d==0)b=false;
        else b=true;
       
        return b;
     
     } 
    public void insertdel(int table,Timestamp t){
        int d = 0;
        boolean b = false;

        try {
           statement = connection.createStatement();
            statement.executeUpdate("UPDATE `_order` SET `time` = '"+t+"',`del` = '1' WHERE `_order`.`order_id` = "+table+"");


        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " ins" + ex);
        }
        if(d==0)b=false;
        else b=true;
     
     } 

/////////////////////////order//////////////////////

    
    
    
///////////////////////////////الجرد//////////////////
    public ObservableList<elGard> gard(Date from, Date to) {
        ObservableList<elGard> d = FXCollections.observableArrayList();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:MM");
        System.out.println(formatter.format(from));
        System.out.println(formatter.format(to));

        try {
            statement = connection.createStatement();
            ResultSet r = statement.executeQuery("SELECT _menu.name,_menu.price,_menu.type,_table.table_id,_order.time "
                    + "FROM _order INNER JOIN _menu ON _order.menu_id=_menu.menu_id "
                    + "INNER JOIN _table ON _order.table_id=_table.table_id "
                    + "WHERE `time` BETWEEN '" + formatter.format(from) + "' AND '" + formatter.format(to) + "'" + " "
                            + "ORDER BY `order_id` DESC");

            while (r.next()) {

                System.out.println(r.getInt("price") + "   " + r.getString("name"));
                d.add(new elGard(r.getString("name"), r.getInt("price"),
                        r.getString("type"), r.getInt("table_id"), r.getTimestamp("time")));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " ins" + ex);
        }
        return d;
    }
    public int gardsumprice(Date from, Date to) {
        int d = 0;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:MM");
        System.out.println(formatter.format(from));
        System.out.println(formatter.format(to));

        try {
            statement = connection.createStatement();
            ResultSet r = statement.executeQuery("SELECT SUM(_menu.price)FROM _order INNER JOIN _menu ON _order.menu_id=_menu.menu_id "
                    + "WHERE `time` BETWEEN '" + formatter.format(from) + "' AND '" + formatter.format(to) + "'");

            while (r.next()) {
                d = r.getInt("SUM(_menu.price)");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " ins" + ex);
        }
        return d;
    }
    public int gardcount(Date from, Date to) {
        int d = 0;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:MM");
        System.out.println(formatter.format(from));
        System.out.println(formatter.format(to));

        try {
            statement = connection.createStatement();
            ResultSet r = statement.executeQuery("SELECT COUNT(order_id)FROM _order "
                    + "WHERE `time` BETWEEN '" + formatter.format(from) + "' AND '" + formatter.format(to) + "'");

            while (r.next()) {
                d = r.getInt("COUNT(order_id)");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " ins" + ex);
        }
        return d;
    }
    public ObservableList<elGard> gardToday(Date today) {
        ObservableList<elGard> d = FXCollections.observableArrayList();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(formatter.format(today));

        try {
            statement = connection.createStatement();
            ResultSet r = statement.executeQuery("SELECT _menu.name,_menu.price,_menu.type,_table.table_id,_order.time "
                    + "FROM _order INNER JOIN _menu ON _order.menu_id=_menu.menu_id "
                    + "INNER JOIN _table ON _order.table_id=_table.table_id "
                    + "WHERE `time` BETWEEN '" + formatter.format(today) + " 00:00:00.000000' AND '" + formatter.format(today) + " 23:59:59.000000' "
                            + "ORDER BY `order_id` DESC");

            while (r.next()) {

                System.out.println(r.getInt("price") + "   " + r.getString("name"));
                d.add(new elGard(r.getString("name"), r.getInt("price"),
                        r.getString("type"), r.getInt("table_id"), r.getTimestamp("time")));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " ins" + ex);
        }
        return d;
    }
    public int gardcount(Date today) {
        int d = 0;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(formatter.format(today));

        try {
            statement = connection.createStatement();
            ResultSet r = statement.executeQuery("SELECT COUNT(order_id)FROM _order "
                    + "WHERE `time` BETWEEN '" + formatter.format(today) + " 00:00:00.000000' AND '" + formatter.format(today) + " 23:59:59.000000'");

            while (r.next()) {
                d = r.getInt("COUNT(order_id)");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " ins" + ex);
        }
        return d;
    }
    public int gardsumprice(Date today) {
        int d = 0;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(formatter.format(today));

        try {
            statement = connection.createStatement();
            ResultSet r = statement.executeQuery("SELECT SUM(_menu.price)FROM _order INNER JOIN _menu ON _order.menu_id=_menu.menu_id  "
                    + "WHERE `time` BETWEEN '" + formatter.format(today) + " 00:00:00.000000' AND '" + formatter.format(today) + " 23:59:59.000000'");

            while (r.next()) {
                d = r.getInt("SUM(_menu.price)");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " ins" + ex);
        }
        return d;
    }

}
//UPDATE `_order` SET `additional` = '5' WHERE `_order`.`order_id` = 58;
//SELECT COUNT(order_id)FROM _order WHERE `time` BETWEEN '2020-07-02 00:00:00.000000' AND '2020-07-31 00:00:00.000000'
//SELECT SUM(_menu.price)FROM _order INNER JOIN _menu ON _order.menu_id=_menu.menu_id WHERE `time` BETWEEN '2020-07-02 00:00:00.000000' AND '2020-07-31 00:00:00.000000'
//SELECT _menu.name,_menu.price,_menu.type,_table.table_id,_order.time FROM _order INNER JOIN _menu ON _order.menu_id=_menu.menu_id INNER JOIN _table ON _order.table_id=_table.table_id WHERE _order.time=( SELECT MAX(time)FROM _order WHERE table_id=2)
//SELECT * FROM `_order` WHERE `time` BETWEEN '2020-07-01' AND '2020-07-31' ORDER BY `order_id` ASC
//SELECT _menu.name,_menu.price,_menu.type,_table.table_id,_order.time FROM _order INNER JOIN _menu ON _order.menu_id=_menu.menu_id INNER JOIN _table ON _order.table_id=_table.table_id WHERE `time` BETWEEN '2020-07-01' AND '2020-07-31' ORDER BY `order_id` ASC
