/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liaalyel7lmiaa;

import com.mysql.jdbc.Buffer;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.io.IOException;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import model.f;
import model.menu;

/**
 *
 * @author asd
 */
public class BillPrintable implements Printable {

    private List<menu> m = new ArrayList<menu>();
    private List<String> strings = new ArrayList<String>();
    private List<f> fat=new ArrayList<f>();
    private int tot;
    private int add;
    private String name;
    private int table_id;

    public BillPrintable(List<menu> m, int tot, int add, String name, int table_id) {
        this.m = m;
        this.tot = tot;
        this.add = add;
        this.name = name;
        this.table_id = table_id;
        strings.add("ايس كوفى");
    }

    private BufferedImage i;

    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
            throws PrinterException {

        int r = 5;
        ImageIcon icon = new ImageIcon("logo.jpg");
        try {
            i = ImageIO.read(getClass().getResourceAsStream("3.jpeg"));
        } catch (IOException ex) {
            Logger.getLogger(BillPrintable.class.getName()).log(Level.SEVERE, null, ex);
        }
        int result = NO_SUCH_PAGE;
        if (pageIndex == 0) {

            Graphics2D g2d = (Graphics2D) graphics;
            double width = pageFormat.getImageableWidth();
            g2d.translate((int) pageFormat.getImageableX(), (int) pageFormat.getImageableY());

            //  FontMetrics metrics=g2d.getFontMetrics(new Font("Arial",Font.BOLD,7));
            try {
                int y = 20;
                int yShift = 10;
                int headerRectHeight = 15;
                // int headerRectHeighta=40;
                for (int i = 0; i < m.size(); i++) {
                    if (exist(m.get(i).getName())) {
                        System.out.println("count        :   " + count(m, m.get(i).getName()));
                    } else {
                        System.out.println("count  else      :   " + count(m, m.get(i).getName()));
                       fat.add(new f(m.get(i).getName(),count(m, m.get(i).getName()),m.get(i).getPrice()));
                       
                    }
                }
                g2d.setFont(new Font("Monospaced", Font.PLAIN, 9));
                //  g2d.drawImage(icon.getImage(), 50, 20, 90, 30,null);y+=yShift+30;
                g2d.drawImage(i, 10, 61, 50, 50, null);
                y += yShift + 30;
                g2d.drawString("-------------------------------------", 12, y);
                y += yShift;
                g2d.drawString(" ليالى الحلمية               ", 12, y);
                y += yShift;
                g2d.drawString("   الباجور المنوفيه طريق مصر         ", 12, y);
                y += yShift;
                g2d.drawString(" امام مدرسة التجارة             ", 12, y);
                y += yShift;
                g2d.drawString("                01093696146      ", 12, y);
                y += yShift;
                g2d.drawString("               اسم الكاشير " + name + "              ", 12, y);
                y += yShift;
                g2d.drawString("-------------------------------------", 12, y);
                y += headerRectHeight;

                g2d.drawString(" Table Num                " + table_id + "   ", 10, y);
                y += yShift;
                g2d.drawString("-------------------------------------", 10, y);
                y += headerRectHeight;
                g2d.drawString(" Item Name         count   Price   ", 10, y);
                y += yShift;
                g2d.drawString("-------------------------------------", 10, y);
                y += headerRectHeight;
                for (int s = 0; s < fat.size(); s++) {
                    

                    g2d.drawString("      " + fat.get(s).getName() + "  ", 10, y);
                    g2d.drawString("      " + fat.get(s).getCount()+ "  ", 90, y);
                    g2d.drawString(" " + fat.get(s).getPrice()+ "", 160, y);
                    y += yShift;
                    }
                y += yShift;
                g2d.drawString("  القيمة المضافة ", 10, y);
                g2d.drawString(" " + add + "", 160, y);
                y += yShift;

                g2d.drawString("-------------------------------------", 10, y);
                y += yShift;
                g2d.drawString(" Total amount:               " + tot + "   ", 10, y);
                y += yShift;
                g2d.drawString("*************************************", 10, y);
                y += yShift;
                g2d.drawString("       THANK YOU COME AGAIN            ", 10, y);
                y += yShift;
                g2d.drawString("*************************************", 10, y);
                y += yShift;
                g2d.drawString("    SOFTWARE BY:Eng MOSTAFA SAMY          ", 10, y);
                y += yShift;
                g2d.drawString("         CONTACT: 01010824193       ", 10, y);
                y += yShift;

            } catch (Exception e) {
                e.printStackTrace();
            }

            result = PAGE_EXISTS;
        }
        return result;
    }

    private boolean exist(String s) {
        for (int i = 0; i < strings.size(); i++) {
            if (s.equals(strings.get(i))) {
                System.out.println("exist" + strings.get(i));
                System.out.println(s);
                return true;
            }
        }
        return false;

    }

    private int count(List<menu> m, String s) {
        int c = 0;
        for (int i = 0; i < m.size(); i++) {
            if (m.get(i).getName().equals(s)) {
                c++;
            }

        }
        strings.add(s);
        System.out.println(strings);
        return c;
    }

}
