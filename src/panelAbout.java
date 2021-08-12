/**
 * Group Project Final Deliverable
 * 12/8/2019
 * 
 * IST-240 Team 6
 * 
 * Jay Carnaghi
 * Hussein Ghaleb
 * David Logan
 * John Christian Swanson
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class panelAbout extends JPanel {

    JButton b1,b2,b3;

    ImageIcon panelabout1 = new ImageIcon("images/background2.jpg");
    ImageIcon panelabout2 = new ImageIcon("images/headerAbout.png");
    ImageIcon panelabout3 = new ImageIcon("images/photoJay.png");
    ImageIcon panelabout4 = new ImageIcon("images/photoHussein.png");
    ImageIcon panelabout5 = new ImageIcon("images/photoDave.png");
    ImageIcon panelabout6 = new ImageIcon("images/photoJohn.png");
    Image aboutBackground1 = panelabout1.getImage();
    Image aboutTitle1 = panelabout2.getImage();
    Image aboutBioPic1 = panelabout3.getImage();
    Image aboutBioPic2 = panelabout4.getImage();
    Image aboutBioPic3 = panelabout5.getImage();
    Image aboutBioPic4 = panelabout6.getImage();
    
    public panelAbout()
    {
        super();
        setLayout(null);      
        
        b1 = new JButton("Credits / Sources");
        b2 = new JButton("High Scores");
        b3 = new JButton("Back to Main Menu");
        b1.setBounds(775,275,200,100);
        b2.setBounds(775,125,200,100);
        b3.setBounds(775,425,200,100);
        add(b1);
        add(b2);
        add(b3);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(aboutBackground1, 0, 0, this);  //IMG, LEFT, TOP
        g.drawImage(aboutTitle1, 76, 20, this);  //IMG, LEFT, TOP
        g.drawImage(aboutBioPic1, 20, 420, this);  //IMG, LEFT, TOP
        g.drawImage(aboutBioPic2, 205, 470, this);  //IMG, LEFT, TOP
        g.drawImage(aboutBioPic3, 390, 420, this);  //IMG, LEFT, TOP
        g.drawImage(aboutBioPic4, 575, 470, this);  //IMG, LEFT, TOP
        
        g.setColor(new Color(0, 0, 0, 90));
        g.fillRect(800, 0, 220, 768); //LEFT, TOP, WIDTH, HEIGHT  
        
        g.setColor(new Color(255, 255, 255, 90));
        g.fillRect(50, 180, 680, 220); //LEFT, TOP, WIDTH, HEIGHT       
        
        Font f1 = new Font("Gothic", Font.BOLD, 24);
        Font f2 = new Font("Gothic", Font.BOLD, 18);
        Font f3 = new Font("Gothic", Font.BOLD, 14);
        g.setColor(Color.WHITE);
        
        g.setFont(f1);
        g.drawString("Jay Carnaghi", 34, 625);
        g.drawString("Hussein Ghaleb", 205, 675);  //TEXT, LEFT, TOP
        g.drawString("David Logan", 411, 625);
        g.drawString("John Swanson", 586, 675);
        g.setFont(f2);
        g.drawString("jac6200@psu.edu", 34, 644);
        g.drawString("hmg7@psu.edu", 228, 694);  //TEXT, LEFT, TOP
        g.drawString("dkl5103@psu.edu", 406, 644);
        g.drawString("jcs6270@psu.edu", 595, 694);
        
        g.setFont(f3);
        g.setColor(Color.BLACK);
        g.drawString("This application was made by IST 240 Team #6 consisting of Jay Carnaghi, Hussein Ghaleb,", 70, 205);  //TEXT, LEFT, TOP
        g.drawString("David Logan, and John Christian Swanson.  This application will serve to meet the final", 70, 225);  //TEXT, LEFT, TOP
        g.drawString("project requirements of our IST 240 course at The Pennsylvania State University.  This", 70, 245);  //TEXT, LEFT, TOP
        g.drawString("application represents an entire semester worth of our learning.", 70, 265);  //TEXT, LEFT, TOP
        
        g.drawString("We hope you enjoy playing our game as much as we had fun making it!!!", 70, 305);  //TEXT, LEFT, TOP
    }
}