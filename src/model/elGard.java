/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Timestamp;    
import java.util.Date;

/**
 *
 * @author asd
 */
public class elGard {
    
    private String name;
    private int price;
    private String type;
    private int table_id;
    private Timestamp time;

    public elGard(String name, int price, String type, int table_id, Timestamp time) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.table_id = table_id;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTable_id() {
        return table_id;
    }

    public void setTable_id(int table_id) {
        this.table_id = table_id;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    
    
    
}
