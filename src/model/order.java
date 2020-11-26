/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Timestamp;
import java.sql.Time;

/**
 *
 * @author asd
 */
public class order {
    
    private int order_id;
    private int count;
    private int menu_id;
    private int table_id;
    private Timestamp time;

    public order(int order_id, int count, int menu_id, int table_id, Timestamp time) {
        this.order_id = order_id;
        this.count = count;
        this.menu_id = menu_id;
        this.table_id = table_id;
        this.time = time;
    }

    public int getOrder_id() {
        return order_id;
    }

    public int getCount() {
        return count;
    }

    public int getMenu_id() {
        return menu_id;
    }

    public int getTable_id() {
        return table_id;
    }

    public Timestamp getTime() {
        return time;
    }
    
 
    
    
}
