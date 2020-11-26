/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author asd
 */
public class table {
    
   private int table_id;
   private String status;
   

    public table(int table_id, String status) {
        this.table_id = table_id;
        this.status = status;
    }

    public int getTable_id() {
        return table_id;
    }

    public String getStatus() {
        return status;
    }
   
   
}
