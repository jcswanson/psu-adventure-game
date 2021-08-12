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

public class panelCredits extends JPanel {

    JButton b1;
    
    ImageIcon panelabout1 = new ImageIcon("images/background2.jpg");
    ImageIcon panelsources1 = new ImageIcon("images/headerSources.png");
    Image aboutBackground1 = panelabout1.getImage();
    Image aboutTitle1 = panelsources1.getImage();

    public panelCredits()
    {
        super();
        setLayout(null);
        
        b1 = new JButton("Back to About Game");
        b1.setBounds(775,300,200,100);
        add(b1);
        
        JTextArea displaySources = new JTextArea(32,158);
        displaySources.setBounds(775,300,200,100);
        
        displaySources.setEditable(false); // set textArea non-editable
        JScrollPane scroll = new JScrollPane(displaySources);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        add(displaySources);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(aboutBackground1, 0, 0, this);  //IMG, LEFT, TOP
        g.drawImage(aboutTitle1, 114, 20, this);  //IMG, LEFT, TOP        

        g.setColor(new Color(0, 0, 0, 90));
        g.fillRect(800, 0, 220, 768); //LEFT, TOP, WIDTH, HEIGHT  
        
        g.setColor(new Color(255, 255, 255, 90));
        g.fillRect(40, 180, 700, 560); //LEFT, TOP, WIDTH, HEIGHT
        
        Font f1 = new Font("Gothic", Font.BOLD, 20);
        Font f2 = new Font("Gothic", Font.BOLD, 12);
        g.setFont(f1);
        g.setColor(Color.BLACK);
        g.drawString("External Images Used:", 50, 206);
        g.setFont(f2);
        g.drawString("https://www.youvisit.com/tour/photos/psueriebehrend/128629?id=950366", 50, 221);
        g.drawString("https://en.wikipedia.org/wiki/Penn_State_Nittany_Lions", 50, 236);
        g.drawString("https://www.deviantart.com/lillgrafo/art/Wu-Tang-HD-wallpaper-213413915", 50, 251);
        g.drawString("https://www.pngfly.com/png-1xwv63/download.html", 50, 266);
        g.drawString("https://sneakersteal.com/2014/02/27/timberland-6-inch-premium-boot/", 50, 281);
        g.drawString("https://www.amazon.com/Nursery-Elephant-Cartoon-Emoji-Sticker/dp/B01MZCQ3O2", 50, 296);
        g.drawString("https://www.google.com/url?sa=i&source=images&cd=&ved=2ahUKEwieiYbW3czlAhUvn-AKHRmdBBkQjRx6BAgBEAQ&", 50, 311);
        g.drawString("url=https%3A%2F%2Fwww.vectorstock.com%2Froyalty-free-vector%2", 70, 326);
        g.drawString("Fcartoon-diaper-vector-5258019&psig=AOvVaw0oOUyMivAhmEtV5zO-mAsC&ust=1572825480865277", 70, 341);
        g.drawString("https://www.vectorstock.com/royalty-free-vector/devil-baby-diaper-character-cartoon-vector-17539967", 50, 356);
        g.drawString("https://www.spriters-resource.com/fullview/119176/", 50, 371);
        g.drawString("https://www.nicepng.com/ourpic/u2w7q8u2t4y3i1y3_squashed-goomba-super-mario-goomba-8-bit/", 50, 386);
        g.drawString("https://giphy.com/stickers/bandits-thinking-brain-think-1g1H4WB8iLHSprAuIN", 50, 401);
        g.drawString("https://www.amazon.com/Onipse-Dev-Rock-Paper-Scissors/dp/B07B3KJ966", 50, 416);
        g.drawString("https://www.spriters-resource.com/fullview/122958/", 50, 431);
        g.drawString("https://www.deviantart.com/kalapkaki/art/Wu-Tang-Panda-200000544", 50, 446);
        g.drawString("https://www.pngfans.com/middle-d1230feef46d022a-buckeye-logo-transparent.html", 50, 461);
        
        g.setFont(f1);
        g.drawString("XML_240 Java Class:", 50, 500);
        g.setFont(f2);
        g.drawString("Contents of the XML_240.java class file are a direct copy from the java class used in our course from our XML lesson.", 50, 515);
        g.drawString("The only modifiction made is how this code is referenced from other class files.", 50, 530);
        
        g.setFont(f1);
        g.drawString("Code Sourced from The Internet:", 50, 580);
        g.setFont(f2);
        g.drawString("Code for sorting and comparing Multidimensional arrays was obtained from:", 50, 595);
        g.drawString("https://stackoverflow.com/questions/15452429/java-arrays-sort-2d-array/", 70, 610);
    }
}