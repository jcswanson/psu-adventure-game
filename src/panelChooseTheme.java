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

public class panelChooseTheme extends JPanel implements MouseListener, ActionListener {

    JButton b1,b2,
            PSUThemeButton,
            WuThemeButton,
            EBitThemeButton,
            DiaperThemeButton;
    
    JLabel PSUThemeArrowHighlight,
           PSUThemeArrowSelected,
           PSUThemeDescription,
           WuThemeArrowHighlight,
           WuThemeArrowSelected,
           WuThemeDescription,
           EBitThemeArrowHighlight,
           EBitThemeArrowSelected,
           EBitThemeDescription,
           DiaperThemeArrowHighlight,
           DiaperThemeArrowSelected,
           DiaperThemeDescription;
    
    objPlayer player;    
    
    ImageIcon paneloptions1 = new ImageIcon("images/background2.jpg");
    ImageIcon paneloptions2 = new ImageIcon("images/overlay1.png");
    ImageIcon paneloptions3 = new ImageIcon("images/headerChooseTheme.png");
    Image optionsBackground1 = paneloptions1.getImage();
    Image optionsOverlay1 = paneloptions2.getImage();
    Image optionsTitle1 = paneloptions3.getImage();

    public panelChooseTheme(objPlayer player)
    {
        super();
        setLayout(null);
        
        this.player = player;
        
        b1 = new JButton("Back to Choose Character");
        b1.setBounds(775,225,200,100);
        b2 = new JButton("Done Setting Options");
        b2.setBounds(775,375,200,100);
        add(b1);
        add(b2);
        
        buildThemeButtons();
        themeSelected();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(optionsBackground1, 0, 0, this);  //IMG, LEFT, TOP
        g.drawImage(optionsOverlay1, 0, 240, this);  //IMG, LEFT, TOP
        g.drawImage(optionsTitle1, 60, 25, this);  //IMG, LEFT, TOP     
        
        g.setColor(new Color(0, 0, 0, 90));
        g.fillRect(800, 0, 220, 768); //LEFT, TOP, WIDTH, HEIGHT          
    }

    
    public void actionPerformed(ActionEvent event){
        Object obj = event.getSource();
        if (obj == PSUThemeButton){
            player.gameTheme = 1;
            themeSelected();
        }
        if (obj == WuThemeButton){
            player.gameTheme = 2;
            themeSelected();
        }
        if (obj == EBitThemeButton){
            player.gameTheme = 3;
            themeSelected();
        }
        if (obj == DiaperThemeButton){
            player.gameTheme = 4;
            themeSelected();
        }
    }
    
    
    //==========================================================================
    // MOUSE LISTENER
    public void mouseClicked(MouseEvent e){ }
    
    public void mouseEntered(MouseEvent e) {
        if (e.getComponent()==PSUThemeButton){
            PSUThemeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            if(player.gameTheme == 1){PSUThemeArrowHighlight.setVisible(false);}
            else{PSUThemeArrowHighlight.setVisible(true);}   
            }
        if (e.getComponent()==WuThemeButton){
            WuThemeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            if(player.gameTheme == 2){WuThemeArrowHighlight.setVisible(false);}
            else{WuThemeArrowHighlight.setVisible(true);}
            }
        if (e.getComponent()==EBitThemeButton){
            EBitThemeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            if(player.gameTheme == 3){EBitThemeArrowHighlight.setVisible(false);}
            else{EBitThemeArrowHighlight.setVisible(true);} 
            }
        if (e.getComponent()==DiaperThemeButton){
            DiaperThemeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            if(player.gameTheme == 4){DiaperThemeArrowHighlight.setVisible(false);}
            else{DiaperThemeArrowHighlight.setVisible(true);} 
            }
    }
    public void mouseExited(MouseEvent e) { 
        if (e.getComponent()==PSUThemeButton){
            PSUThemeArrowHighlight.setVisible(false);}
        if (e.getComponent()==WuThemeButton){
            WuThemeArrowHighlight.setVisible(false);}
        if (e.getComponent()==EBitThemeButton){
            EBitThemeArrowHighlight.setVisible(false);}
        if (e.getComponent()==DiaperThemeButton){
            DiaperThemeArrowHighlight.setVisible(false);}
    }
    
    public void mousePressed(MouseEvent e) { }
    public void mouseReleased(MouseEvent e) { }
    //==========================================================================
    
    
    
    public void buildThemeButtons(){
        PSUThemeButton = new JButton(new ImageIcon("images/themeSelectPSU.png"));
        PSUThemeButton.setBounds(260,200,475,96);
        PSUThemeButton.setBorder(BorderFactory.createEmptyBorder());
        PSUThemeButton.setContentAreaFilled(false);
        PSUThemeButton.addActionListener(this);
        PSUThemeButton.addMouseListener(this);
        PSUThemeArrowHighlight = new JLabel(new ImageIcon("images/themeArrowHighlight.gif"));
        PSUThemeArrowHighlight.setBounds(2,200,250,101);
        PSUThemeArrowHighlight.setBorder(BorderFactory.createEmptyBorder());
        PSUThemeArrowHighlight.setVisible(false);
        PSUThemeArrowSelected = new JLabel(new ImageIcon("images/themeArrowSelect.png"));
        PSUThemeArrowSelected.setBounds(2,200,250,101);
        PSUThemeArrowSelected.setBorder(BorderFactory.createEmptyBorder());
        PSUThemeArrowSelected.setVisible(false);
        PSUThemeDescription = new JLabel("<html><center><font color=#000000 size=5>"
                + "The default game theme.");
        Font font = PSUThemeDescription.getFont().deriveFont(Font.BOLD);
        PSUThemeDescription.setFont(font);
        PSUThemeDescription.setBounds(365,172,500,175);
        PSUThemeDescription.setBorder(BorderFactory.createEmptyBorder());
        PSUThemeDescription.setVisible(false);
        
        WuThemeButton = new JButton(new ImageIcon("images/themeSelectWutang.png"));
        WuThemeButton.setBounds(240,330,495,97);
        WuThemeButton.setBorder(BorderFactory.createEmptyBorder());
        WuThemeButton.setContentAreaFilled(false);
        WuThemeButton.addActionListener(this);
        WuThemeButton.addMouseListener(this);
        WuThemeArrowHighlight = new JLabel(new ImageIcon("images/themeArrowHighlight.gif"));
        WuThemeArrowHighlight.setBounds(2,330,250,101);
        WuThemeArrowHighlight.setBorder(BorderFactory.createEmptyBorder());
        WuThemeArrowHighlight.setVisible(false);
        WuThemeArrowSelected = new JLabel(new ImageIcon("images/themeArrowSelect.png"));
        WuThemeArrowSelected.setBounds(2,330,250,101);
        WuThemeArrowSelected.setBorder(BorderFactory.createEmptyBorder());
        WuThemeArrowSelected.setVisible(false);
        WuThemeDescription = new JLabel("<html><center><font color=#000000 size=5>"
                + "Enter the 36 Chambers...");
        WuThemeDescription.setFont(font);
        WuThemeDescription.setBounds(375,300,500,175);
        WuThemeDescription.setBorder(BorderFactory.createEmptyBorder());
        WuThemeDescription.setVisible(false);
        
        EBitThemeButton = new JButton(new ImageIcon("images/themeSelect8bit.png"));
        EBitThemeButton.setBounds(240,460,495,126);
        EBitThemeButton.setBorder(BorderFactory.createEmptyBorder());
        EBitThemeButton.setContentAreaFilled(false);
        EBitThemeButton.addActionListener(this);
        EBitThemeButton.addMouseListener(this);
        EBitThemeArrowHighlight = new JLabel(new ImageIcon("images/themeArrowHighlight.gif"));
        EBitThemeArrowHighlight.setBounds(2,475,250,101);
        EBitThemeArrowHighlight.setBorder(BorderFactory.createEmptyBorder());
        EBitThemeArrowHighlight.setVisible(false);
        EBitThemeArrowSelected = new JLabel(new ImageIcon("images/themeArrowSelect.png"));
        EBitThemeArrowSelected.setBounds(2,475,250,101);
        EBitThemeArrowSelected.setBorder(BorderFactory.createEmptyBorder());
        EBitThemeArrowSelected.setVisible(false);
        EBitThemeDescription = new JLabel("<html><center><font color=#ffffff size=5>"
                + "Battle Bowser and Gannon's Minions!");
        EBitThemeDescription.setFont(font);
        EBitThemeDescription.setBounds(385,436,500,175);
        EBitThemeDescription.setBorder(BorderFactory.createEmptyBorder());
        EBitThemeDescription.setVisible(false);
        
        DiaperThemeButton = new JButton(new ImageIcon("images/themeSelectDiaper.png"));
        DiaperThemeButton.setBounds(240,600,482,97);
        DiaperThemeButton.setBorder(BorderFactory.createEmptyBorder());
        DiaperThemeButton.setContentAreaFilled(false);
        DiaperThemeButton.addActionListener(this);
        DiaperThemeButton.addMouseListener(this);
        DiaperThemeArrowHighlight = new JLabel(new ImageIcon("images/themeArrowHighlight.gif"));
        DiaperThemeArrowHighlight.setBounds(2,605,250,101);
        DiaperThemeArrowHighlight.setBorder(BorderFactory.createEmptyBorder());
        DiaperThemeArrowHighlight.setVisible(false);
        DiaperThemeArrowSelected = new JLabel(new ImageIcon("images/themeArrowSelect.png"));
        DiaperThemeArrowSelected.setBounds(2,605,250,101);
        DiaperThemeArrowSelected.setBorder(BorderFactory.createEmptyBorder());
        DiaperThemeArrowSelected.setVisible(false);
        DiaperThemeDescription = new JLabel("<html><center><font color=#ffffff size=5>"
                + "Can you survive the dirty diapers???");
        DiaperThemeDescription.setFont(font);
        DiaperThemeDescription.setBounds(370,570,500,175);
        DiaperThemeDescription.setBorder(BorderFactory.createEmptyBorder());
        DiaperThemeDescription.setVisible(false);
        
        add(PSUThemeButton);
        add(PSUThemeArrowHighlight);
        add(PSUThemeArrowSelected);
        add(PSUThemeDescription);
        add(WuThemeButton);
        add(WuThemeArrowHighlight);
        add(WuThemeArrowSelected);
        add(WuThemeDescription);
        add(EBitThemeButton);
        add(EBitThemeArrowHighlight);
        add(EBitThemeArrowSelected);
        add(EBitThemeDescription);
        add(DiaperThemeButton);
        add(DiaperThemeArrowHighlight);
        add(DiaperThemeArrowSelected);
        add(DiaperThemeDescription);
    }
    
    public void themeSelected(){
        player.gameThemeSelected = true;
        PSUThemeDescription.setVisible(true);
        WuThemeDescription.setVisible(true);
        EBitThemeDescription.setVisible(true);
        DiaperThemeDescription.setVisible(true);
        
        if(player.gameTheme == 1){
            PSUThemeArrowSelected.setVisible(true);
            WuThemeArrowSelected.setVisible(false);
            EBitThemeArrowSelected.setVisible(false);
            DiaperThemeArrowSelected.setVisible(false);
            PSUThemeArrowHighlight.setVisible(false);
            WuThemeArrowHighlight.setVisible(false);
            EBitThemeArrowHighlight.setVisible(false);
            DiaperThemeArrowHighlight.setVisible(false);
        }
        if(player.gameTheme == 2){
            PSUThemeArrowSelected.setVisible(false);
            WuThemeArrowSelected.setVisible(true);
            EBitThemeArrowSelected.setVisible(false);
            DiaperThemeArrowSelected.setVisible(false);
            PSUThemeArrowHighlight.setVisible(false);
            WuThemeArrowHighlight.setVisible(false);
            EBitThemeArrowHighlight.setVisible(false);
            DiaperThemeArrowHighlight.setVisible(false);
        }
        if(player.gameTheme == 3){
            PSUThemeArrowSelected.setVisible(false);
            WuThemeArrowSelected.setVisible(false);
            EBitThemeArrowSelected.setVisible(true);
            DiaperThemeArrowSelected.setVisible(false);
            PSUThemeArrowHighlight.setVisible(false);
            WuThemeArrowHighlight.setVisible(false);
            EBitThemeArrowHighlight.setVisible(false);
            DiaperThemeArrowHighlight.setVisible(false);
        }
        if(player.gameTheme == 4){
            PSUThemeArrowSelected.setVisible(false);
            WuThemeArrowSelected.setVisible(false);
            EBitThemeArrowSelected.setVisible(false);
            DiaperThemeArrowSelected.setVisible(true);
            PSUThemeArrowHighlight.setVisible(false);
            WuThemeArrowHighlight.setVisible(false);
            EBitThemeArrowHighlight.setVisible(false);
            DiaperThemeArrowHighlight.setVisible(false);
        }
    }
}