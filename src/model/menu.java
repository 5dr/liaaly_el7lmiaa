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
public class menu {
    
   private int menu_id;
   private String name;
   private int price;
   private String type;

    public menu(int menu_id, String name, int price, String type) {
        this.menu_id = menu_id;
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public String getType() {
        return type;
    }

   

    public int getMenu_id() {
        return menu_id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    } 
}
