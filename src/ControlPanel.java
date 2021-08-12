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

public class ControlPanel extends JPanel implements ActionListener {
    
    panelAbout p01PanelAbout;
    panelCredits p02PanelCredits;
    panelHighScores p03PanelHighScores;
    panelInstructions p04PanelInstructions;
    panelChooseCharacter p05PanelChooseCharacter;
    panelChooseTheme p06PanelChooseTheme;
    panelGameMap p07PanelGameMap;
    
    objPlayer player;
    
    ImageIcon controlpanel1 = new ImageIcon("images/background1.jpg");
    ImageIcon controlpanel2 = new ImageIcon("images/logoBottom.png");
    ImageIcon controlpanel3 = new ImageIcon("images/logoTop.png");
    Image controlPanelBG1 = controlpanel1.getImage();
    Image controlPanelBG2 = controlpanel2.getImage();
    Image controlPanelBG3 = controlpanel3.getImage();
    
    JButton launchPanelAbout,launchPanelCredits,launchPanelHighScores,
            launchPanelInstructions,
            launchPanelChooseCharacter,launchPanelChooseTheme,
            launchPanelGameMap;
    
    JLabel  charDaddyIcon,charMommyIcon,charBabyIcon,
            charDaddyText,charMommyText,charBabyText,
            themePSUIcon,themeWuIcon,theme8BitIcon,themeDiaperIcon,
            themePSUText,themeWuText,theme8BitText,themeDiaperText;
            
    public ControlPanel()
    {
        super();
        setLayout(null);
        InitialGameSetUp();
        InitialSetUpForControlPanel();
        CreateComponentsThatWillBeSwapped();
    }
    public void InitialGameSetUp(){
        //BUILD PLAYER OBJECT
        this.player = new objPlayer(
                "Team 6",   //Set Default Player Name
                1,          //Set Default Character
                1,          //Set Default Game Theme
                false,      //Did Player Choose Character?
                false,      //Did Player Choose Game Theme?
                "U13",      //Set Default Starting Tile
                0,          //Initialize Game Time
		0,          //Initialize Game 1 Time
		0,          //Initialize Game 2 Time
		0,          //Initialize Game 3 Time
		0,          //Initialize Game 4 Time
                0,          //Initialize Game Score
		0,          //Initialize Game 1 Score
		0,          //Initialize Game 2 Score
		0,          //Initialize Game 3 Score
		0,          //Initialize Game 4 Score				
                false,      //Has Game Started?
                false,      //Is The Game Timer Paused?
                false,      //Has Campus One Been Entered?
                false,      //Has Campus Two Been Entered?
                false,      //Has Campus Three Been Entered?
                false,      //Has World Campus Been Entered?
                false       //Is Game Over?
        ); 
    }
    
    public void InitialSetUpForControlPanel(){
                
        launchPanelAbout = new JButton("About Game");
        launchPanelInstructions = new JButton("Instructions");
        launchPanelChooseCharacter = new JButton("Game Options");
        launchPanelGameMap = new JButton("Start Game");

        launchPanelAbout.setBounds(775,75,200,100);
        launchPanelInstructions.setBounds(775,225,200,100);
        launchPanelChooseCharacter.setBounds(775,375,200,100);
        launchPanelGameMap.setBounds(775,525,200,100);       

        launchPanelAbout.addActionListener(this);
        launchPanelInstructions.addActionListener(this);
        launchPanelChooseCharacter.addActionListener(this);
        launchPanelGameMap.addActionListener(this);
        
        add(launchPanelAbout);
        add(launchPanelInstructions);
        add(launchPanelChooseCharacter);
        add(launchPanelGameMap);

        if(player.character == 1){
            charDaddyIcon = new JLabel(new ImageIcon("images/iconDaddy.png"));
            charDaddyIcon.setBounds(140,335,63,62);
            charDaddyIcon.setBorder(BorderFactory.createEmptyBorder());
            charDaddyText = new JLabel("<html><center><font color=#ffffff size=3>"
                + "Daddy Nittany");
            Font font = charDaddyText.getFont().deriveFont(Font.BOLD);
            charDaddyText.setFont(font);
            charDaddyText.setBounds(216,350,200,30);
            charDaddyText.setBorder(BorderFactory.createEmptyBorder());
            charDaddyIcon.setVisible(true); 
            charDaddyText.setVisible(true); 
            add(charDaddyIcon);
            add(charDaddyText);
        }
        if(player.character == 2){
            charMommyIcon = new JLabel(new ImageIcon("images/iconMommy.png"));
            charMommyIcon.setBounds(140,335,63,62);
            charMommyIcon.setBorder(BorderFactory.createEmptyBorder());
            charMommyText = new JLabel("<html><center><font color=#ffffff size=3>"
                + "Mommy Nittany");
            Font font = charMommyText.getFont().deriveFont(Font.BOLD);
            charMommyText.setFont(font);
            charMommyText.setBounds(216,350,200,30);
            charMommyText.setBorder(BorderFactory.createEmptyBorder());
            charMommyIcon.setVisible(true); 
            charMommyText.setVisible(true); 
            add(charMommyIcon);
            add(charMommyText);
        }
        if(player.character == 3){
            charBabyIcon = new JLabel(new ImageIcon("images/iconBaby.png"));
            charBabyIcon.setBounds(140,335,63,62);
            charBabyIcon.setBorder(BorderFactory.createEmptyBorder());
            charBabyText = new JLabel("<html><center><font color=#ffffff size=3>"
                + "Baby Nittany");
            Font font = charBabyText.getFont().deriveFont(Font.BOLD);
            charBabyText.setFont(font);
            charBabyText.setBounds(216,350,200,30);
            charBabyText.setBorder(BorderFactory.createEmptyBorder());
            charBabyIcon.setVisible(true); 
            charBabyText.setVisible(true); 
            add(charBabyIcon);
            add(charBabyText);
        }
        if(player.gameTheme == 1){
            themePSUIcon = new JLabel(new ImageIcon("images/iconPSU.png"));
            themePSUIcon.setBounds(410,335,63,62);
            themePSUIcon.setBorder(BorderFactory.createEmptyBorder());
            themePSUText = new JLabel("<html><center><font color=#ffffff size=3>"
                + "PennState Game Theme Active");
            Font font = themePSUText.getFont().deriveFont(Font.BOLD);
            themePSUText.setFont(font);
            themePSUText.setBounds(478,350,200,30);
            themePSUText.setBorder(BorderFactory.createEmptyBorder());
            themePSUIcon.setVisible(true); 
            themePSUText.setVisible(true); 
            add(themePSUIcon);
            add(themePSUText);
        }
        if(player.gameTheme == 2){
            themeWuIcon = new JLabel(new ImageIcon("images/iconWu.png"));
            themeWuIcon.setBounds(410,335,75,56);
            themeWuIcon.setBorder(BorderFactory.createEmptyBorder());
            themeWuText = new JLabel("<html><center><font color=#ffffff size=3>"
                + "Wu-Tang Game Theme Active");
            Font font = themeWuText.getFont().deriveFont(Font.BOLD);
            themeWuText.setFont(font);
            themeWuText.setBounds(492,350,200,30);
            themeWuText.setBorder(BorderFactory.createEmptyBorder());
            themeWuIcon.setVisible(true); 
            themeWuText.setVisible(true); 
            add(themeWuIcon);
            add(themeWuText);
        }
        if(player.gameTheme == 3){
            theme8BitIcon = new JLabel(new ImageIcon("images/icon8Bit.png"));
            theme8BitIcon.setBounds(390,335,79,67);
            theme8BitIcon.setBorder(BorderFactory.createEmptyBorder());
            theme8BitText = new JLabel("<html><center><font color=#ffffff size=3>"
                + "8-Bit Retro Game Theme Active");
            Font font = theme8BitText.getFont().deriveFont(Font.BOLD);
            theme8BitText.setFont(font);
            theme8BitText.setBounds(475,350,200,30);
            theme8BitText.setBorder(BorderFactory.createEmptyBorder());
            theme8BitIcon.setVisible(true); 
            theme8BitText.setVisible(true); 
            add(theme8BitIcon);
            add(theme8BitText);
        }
        if(player.gameTheme == 4){
            themeDiaperIcon = new JLabel(new ImageIcon("images/iconDiaper.png"));
            themeDiaperIcon.setBounds(414,335,79,58);
            themeDiaperIcon.setBorder(BorderFactory.createEmptyBorder());
            themeDiaperText = new JLabel("<html><center><font color=#ffffff size=3>"
                + "Diaper Game Theme Active");
            Font font = themeDiaperText.getFont().deriveFont(Font.BOLD);
            themeDiaperText.setFont(font);
            themeDiaperText.setBounds(502,350,200,30);
            themeDiaperText.setBorder(BorderFactory.createEmptyBorder());
            themeDiaperIcon.setVisible(true); 
            themeDiaperText.setVisible(true); 
            add(themeDiaperIcon);
            add(themeDiaperText);
            
        }

    }

    //==========================================================================
    // DEFINE PANELS AND ADD LISTENERS TO JBUTTONS 
    public void CreateComponentsThatWillBeSwapped(){
        p01PanelAbout = new panelAbout();
        p02PanelCredits = new panelCredits();
        p03PanelHighScores = new panelHighScores();
        p04PanelInstructions = new panelInstructions();
        p05PanelChooseCharacter = new panelChooseCharacter(player);
        p06PanelChooseTheme = new panelChooseTheme(player); 
        p01PanelAbout.setBounds(0,0,1024,768);
        p02PanelCredits.setBounds(0,0,1024,768);
        p03PanelHighScores.setBounds(0,0,1024,768);
        p04PanelInstructions.setBounds(0,0,1024,768);
        p05PanelChooseCharacter.setBounds(0,0,1024,768);
        p06PanelChooseTheme.setBounds(0,0,1024,768);
        p01PanelAbout.b1.addActionListener(this);
        p01PanelAbout.b2.addActionListener(this);
        p01PanelAbout.b3.addActionListener(this);
        p02PanelCredits.b1.addActionListener(this);
        p03PanelHighScores.b1.addActionListener(this);
        p04PanelInstructions.b1.addActionListener(this);
        p05PanelChooseCharacter.b1.addActionListener(this);
        p06PanelChooseTheme.b1.addActionListener(this);
        p06PanelChooseTheme.b2.addActionListener(this);
    }

    public void actionPerformed(ActionEvent event){
        Object obj = event.getSource();
        //======================================================================
        // MAIN SCREEN BUTTON LISTENERS
        if (obj == launchPanelAbout){
            removeAll();
            add(p01PanelAbout);
            validate();
            repaint();
        }
        if (obj == launchPanelInstructions){
            removeAll();
            add(p04PanelInstructions);
            validate();
            repaint();
        }
        if (obj == launchPanelChooseCharacter){
            removeAll();
            add(p05PanelChooseCharacter);
            validate();
            repaint();
        }
        if (obj == launchPanelGameMap){
            removeAll();
            p07PanelGameMap = new panelGameMap(player); 
            p07PanelGameMap.setBounds(0,0,1024,768);
            add(p07PanelGameMap);
            validate();
            repaint();
        }
        
        //======================================================================
        // ABOUT SCREEN BUTTON LISTENERS        
        if (obj == p01PanelAbout.b1){ //GO TO CREDITS FROM ABOUT
            removeAll();
            add(p02PanelCredits);
            validate();
            repaint();
        }          
        if (obj == p02PanelCredits.b1) //RETURN TO ABOUT FROM CREDITS
        {
            removeAll();
            add(p01PanelAbout); //return to About page from Credits
            validate();
            repaint();
        }
        if (obj == p01PanelAbout.b2) //GO TO HIGH SCORES FROM ABOUT
        {
            removeAll();
            add(p03PanelHighScores); //return to About page from Credits
            validate();
            repaint();
        }
        if (obj == p03PanelHighScores.b1) //RETURN TO ABOUT FROM HIGH SCORES
        {
            removeAll();
            add(p01PanelAbout); //return to About page from Credits
            validate();
            repaint();
        }
        if (obj == p01PanelAbout.b3)
        {
            removeAll();
            InitialSetUpForControlPanel(); //rebuild the original ControlPanel again
            validate();
            repaint();
        }
        //======================================================================
        // INSTRUCTION SCREEN LISTENERS     
        if (obj == p04PanelInstructions.b1)
        {
            removeAll();
            InitialSetUpForControlPanel(); //rebuild the original ControlPanel again
            validate();
            repaint();
        }
        //======================================================================
        // OPTIONS / CHAR AND THEME SELECT SCREEN LISTENERS   
        if (obj == p05PanelChooseCharacter.b1) //CHOOSE THEME FROM CHAR SELECT
        {
            removeAll();
            add(p06PanelChooseTheme);
            validate();
            repaint();
        }
        if (obj == p06PanelChooseTheme.b1) //BACK TO CHOOSE CHAR
        {
            removeAll();
            add(p05PanelChooseCharacter);
            validate();
            repaint();
        }
        if (obj == p06PanelChooseTheme.b2) //DONE SELECTING THEME
        {
            removeAll();
            InitialSetUpForControlPanel(); //rebuild the original ControlPanel again
            validate();
            repaint();
        }
    }
    
    //=====================================

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
            g.drawImage(controlPanelBG1, 0, 0, this);  //IMG, LEFT, TOP
            g.drawImage(controlPanelBG2, 95, 90, this);  //IMG, LEFT, TOP
            g.drawImage(controlPanelBG3, 35, 100, this);  //IMG, LEFT, TOP
            g.setColor(new Color(0, 0, 0, 90));
            g.fillRect(800, 0, 220, 768); //LEFT, TOP, WIDTH, HEIGHT  
            g.fillRoundRect(160, 350, 150, 32, 16, 16);
            g.fillRoundRect(420, 350, 250, 32, 16, 16);
    }
}