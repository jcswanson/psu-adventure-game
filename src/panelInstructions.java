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

public class panelInstructions extends JPanel {

    JButton b1;

    ImageIcon panelinstructions1 = new ImageIcon("images/background2.jpg");
    ImageIcon panelinstructions2 = new ImageIcon("images/headerInstructions.png");
    Image instructionsBackground1 = panelinstructions1.getImage();
    Image instructionsTitle1 = panelinstructions2.getImage();
    
    JTabbedPane tabsPanel;
    panelInstructionTab1 tab1;
    panelInstructionTab2 tab2;
    panelInstructionTab3 tab3;
    panelInstructionTab4 tab4;
    panelInstructionTab5 tab5;
    panelInstructionTab6 tab6;
    
    public panelInstructions()
    {
        super();
        setLayout(null);

        tabsPanel = new JTabbedPane();
        tabsPanel.setBackground(Color.gray);

        tab1 = new panelInstructionTab1();
        tab2 = new panelInstructionTab2();
        tab3 = new panelInstructionTab3();
        tab4 = new panelInstructionTab4();
        tab5 = new panelInstructionTab5();
        tab6 = new panelInstructionTab6();
        
        tabsPanel.addTab("Setting Up Game", tab1);
        tabsPanel.addTab("Traversing The Game Map", tab2);
        tabsPanel.addTab("Game 1", tab3);
        tabsPanel.addTab("Game 2", tab4);
        tabsPanel.addTab("Game 3", tab5);
        tabsPanel.addTab("Game 4", tab6);
        
        tabsPanel.setEnabledAt(0, true);
        tabsPanel.setEnabledAt(1, true);
        tabsPanel.setEnabledAt(2, true);
        tabsPanel.setEnabledAt(3, true);
        tabsPanel.setEnabledAt(4, true);
        tabsPanel.setEnabledAt(5, true);
        
        tabsPanel.setSelectedIndex(0);
        tabsPanel.setBounds(50,190,700,510);
        add(tabsPanel);
        
        b1 = new JButton("Back to Main Menu");
        b1.setBounds(775,300,200,100);
        add(b1);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(instructionsBackground1, 0, 0, this);  //IMG, LEFT, TOP
        g.drawImage(instructionsTitle1, 30, 20, this);  //IMG, LEFT, TOP
        
        g.setColor(new Color(0, 0, 0, 90));
        g.fillRect(800, 0, 220, 768); //LEFT, TOP, WIDTH, HEIGHT  
    }
}