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
import java.util.Random;

public class panelGame3 extends JPanel implements MouseListener, ActionListener {

    panelGameMap p07PanelGameMap;
    objPlayer player;
    Timer game3Timer, game3TimerCountDown,game3OverTimer;
    JProgressBar pbVertical;
    int game3TimerDelay,game3TimeRemaining,gameMode,delayModifier,game3OverDelay,
            game3OverTicks,gameStarted,gameEnded,genNum,tokenNum,
            numOfEnemy1Clicked,numOfEnemy2Clicked,numOfBonusClicked;
    JButton enemy1, enemy2, enemy3, bonus, itemType, startGame3;
    boolean enemy1Clicked,enemy2Clicked,enemy3Clicked,bonusClicked;
    JLabel intro3OverlayBg,intro3OverlayTxt1,intro3OverlayTxt2,
            gameOverBg,gameOverTxt1,gameOverTxt2;
    
    ImageIcon gameBackgroundPSU = new ImageIcon("images/game3BackgroundPSU.jpg"),
              gameBackgroundWU = new ImageIcon("images/game3BackgroundWU.jpg"),
              gameBackground8BIT = new ImageIcon("images/game3Background8Bit.jpg"),
              gameBackgroundDiaper = new ImageIcon("images/game3BackgroundDiaper.jpg"),
              charMapPortraitDaddy = new ImageIcon("images/iconDaddy.png"),
              charMapPortraitMommy = new ImageIcon("images/iconMommy.png"),
              charMapPortraitBaby = new ImageIcon("images/iconBaby.png");
    
    Image gameMapPsuBg = gameBackgroundPSU.getImage();
    Image gameMapWuBg = gameBackgroundWU.getImage();
    Image gameMap8BitBg = gameBackground8BIT.getImage();
    Image gameMapDiaperBg = gameBackgroundDiaper.getImage();
    Image gameMapCharDaddy = charMapPortraitDaddy.getImage();
    Image gameMapCharMommy = charMapPortraitMommy.getImage();
    Image gameMapCharBaby = charMapPortraitBaby.getImage();
    	
    Icon enemy1Spawn,enemy2Spawn,enemy3Spawn,bonusSpawn,
         enemy1PSUtheme = new ImageIcon("images/game3PsuEnemy1.png"),
	 enemy2PSUtheme = new ImageIcon("images/game3PsuEnemy2.png"),
	 bonusPSUtheme = new ImageIcon("images/game3PsuBonus.png"),
	 enemy1WUtheme = new ImageIcon("images/game3WuEnemy1.png"),
	 enemy2WUtheme = new ImageIcon("images/game3WuEnemy2.png"),
	 bonusWUtheme = new ImageIcon("images/game3WuBonus.png"),
	 enemy18BITtheme = new ImageIcon("images/game38BitEnemy1.png"),
	 enemy28BITtheme = new ImageIcon("images/game38BitEnemy2.png"),
	 bonus8BITtheme = new ImageIcon("images/game38BitBonus.png"),
	 enemy1Diapertheme = new ImageIcon("images/game3DiaperEnemy1.png"),
	 enemy2Diapertheme = new ImageIcon("images/game3DiaperEnemy2.png"),
	 bonusDiapertheme = new ImageIcon("images/game3DiaperBonus.png"),
         intro3OverlayBgImg = new ImageIcon("images/gameOverlay1.png"),
         gameOverBgImg = new ImageIcon("images/gameOverlay3.png");
	
    public panelGame3(objPlayer player){
        super();
        this.player = player;
        setLayout(null);

	buildGameInterface();   //BUILD INTERFACE ELEMENTS OF GAME
        setupInitialGameMechanics(); //BUILD TIMERS AND INITIAL VARIABLES
        setupIntro3Overlay();    //BUILD INTRO SPLASH SCREEN TO GAME
    }

    //==========================================================================
    // DRAW GAME BACKGROUND AND TOP OVERLAY	
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        //DRAW GAME BACKGROUND IMAGE
        if(player.gameTheme == 1){g.drawImage(gameMapPsuBg, 0, 0, this);}
        else if(player.gameTheme == 2){g.drawImage(gameMapWuBg, 0, 0, this);}
        else if(player.gameTheme == 3){g.drawImage(gameMap8BitBg, 0, 0, this);}
        else if(player.gameTheme == 4){g.drawImage(gameMapDiaperBg, 0, 0, this);}
       
        //DRAW TOP OVERLAY BACKGROUND
        g.setColor(new Color(0, 0, 0, 95)); //SET COLOR TO BLACK W/95% OPACITY
        g.fillRect(0, 0, 1024, 70); //LEFT, TOP, WIDTH, HEIGHT 

        //DRAW PLAYER CHARACTER ICON IN UPPER-LEFT CORNER OF OVERLAY
        if(player.character == 1){g.drawImage(gameMapCharDaddy,10,3, this);}
        else if(player.character == 2){g.drawImage(gameMapCharMommy,10,3, this);}
        else if(player.character == 3){g.drawImage(gameMapCharBaby,10,3, this);}        

        //DRAW TEXT
        Font f1 = new Font("Gothic", Font.BOLD, 20);
        g.setFont(f1);
        g.setColor(Color.WHITE);
        g.drawString("Penn State Behrend Campus",90,44);
        g.drawString("Timer: ", 500, 44);
        g.drawString("Score: ", 730, 44);
        
        //DRAW GAME TIME AND SCORE
        g.setColor(Color.YELLOW);
        g.drawString(String.format( "%-10d", player.gameTime + player.game3Time ), 575, 44); //ALIGN LEFT
        g.drawString(String.format( "%010d", player.gameScore + player.game3Score ), 805, 44); //PADDING 10 DIGITS W/ZEROS
    }
		
    //==========================================================================
    // BUILD GAME INTERFACE BASED ON PLAYER CHARACTER AND THEME CHOSEN
    public void buildGameInterface(){
        //SET PLAYER CHARACTER TIMER ADVANTAGE
	if(player.character == 1){game3TimeRemaining = 60;}
        else if (player.character == 2){game3TimeRemaining = 75;}
        else if (player.character == 3){game3TimeRemaining = 90;}
        
        //SET PLAYER CHARACTER TIMER DELAY MODIFIER
	if(player.character == 1){delayModifier = 10;}
        else if (player.character == 2){delayModifier = 8;}
        else if (player.character == 3){delayModifier = 5;}
		
	//SET GAME GRAPHICS BASED ON THEME
	if(player.gameTheme == 1)
	{
	enemy1 = new JButton(enemy1PSUtheme);
        enemy1.setBounds(0,0,151,105);
	enemy2 = new JButton(enemy2PSUtheme);
        enemy2.setBounds(0,0,97,106);
        enemy3 = new JButton(enemy1PSUtheme);
        enemy3.setBounds(0,0,151,105);
	bonus = new JButton(bonusPSUtheme);
        bonus.setBounds(0,0,135,91);
	}
	
	else if(player.gameTheme == 2)
	{
	enemy1 = new JButton(enemy1WUtheme);
        enemy1.setBounds(0,0,113,102);
	enemy2 = new JButton(enemy2WUtheme);
        enemy2.setBounds(0,0,149,112);
        enemy3 = new JButton(enemy1WUtheme);
        enemy3.setBounds(0,0,113,102);
	bonus = new JButton(bonusWUtheme);
        bonus.setBounds(0,0,129,118);
	}
	
	else if(player.gameTheme == 3)
	{
	enemy1 = new JButton(enemy18BITtheme);
        enemy1.setBounds(0,0,103,102);
	enemy2 = new JButton(enemy28BITtheme);
        enemy2.setBounds(0,0,84,125);
        enemy3 = new JButton(enemy18BITtheme);
        enemy3.setBounds(0,0,103,102);
	bonus = new JButton(bonus8BITtheme);
        bonus.setBounds(0,0,77,77);
	}
	
	else if(player.gameTheme == 4)
	{
	enemy1 = new JButton(enemy1Diapertheme);
        enemy1.setBounds(0,0,120,94);
	enemy2 = new JButton(enemy2Diapertheme);
        enemy2.setBounds(0,0,102,111);
        enemy3 = new JButton(enemy1Diapertheme);
        enemy3.setBounds(0,0,120,94);
	bonus = new JButton(bonusDiapertheme);
        bonus.setBounds(0,0,117,120);
	}	
	
        //SET GLOBAL GAME-BASED GRAPHICS SETTINGS
        enemy1.setVisible(false);
        enemy1.setBorder(BorderFactory.createEmptyBorder());
        enemy1.setContentAreaFilled(false);
        enemy2.setVisible(false);
        enemy2.setBorder(BorderFactory.createEmptyBorder());
        enemy2.setContentAreaFilled(false);
        enemy3.setVisible(false);
        enemy3.setBorder(BorderFactory.createEmptyBorder());
        enemy3.setContentAreaFilled(false);
        bonus.setVisible(false);
        bonus.setBorder(BorderFactory.createEmptyBorder());
        bonus.setContentAreaFilled(false);
        
        add(enemy1);
        add(enemy2);
        add(enemy3);
        add(bonus);
        
        enemy1.addMouseListener(this);
        enemy2.addMouseListener(this);
        enemy3.addMouseListener(this);
        bonus.addMouseListener(this);
  
	//SET GAME MODE BASED ON PLAYER CHARACTER
	if(player.character == 1){gameMode = 1;}
        else if (player.character == 2){gameMode = 2;}
        else if (player.character == 3){gameMode = 3;}	
    
        //BUILD VERTICAL JPROGRESS BAR FOR COUNTDOWN TIMER
        pbVertical = new JProgressBar(JProgressBar.VERTICAL, 0, game3TimeRemaining);
        pbVertical.setStringPainted(true);
        pbVertical.setString("");
        add(pbVertical);
        pbVertical.setBounds(964,70,60,698);
    }

    //==========================================================================
    // SETUP INITIAL GAME MECHANICS SUCH AS TIMERS AND VARIABLES    
    public void setupInitialGameMechanics(){
        //SETUP INITIAL GAME VARIABLES
        gameStarted = 0;
        gameEnded = 0;
        tokenNum = 0;
        enemy1Clicked = true;
        enemy2Clicked = true;
        enemy3Clicked = true;
        bonusClicked = true;
        numOfEnemy1Clicked = 0;
        numOfEnemy2Clicked = 0;
        numOfBonusClicked = 0;
   
        //START GAME #3 TIMER (TO CONTINUE GLOBAL GAME TIMER)
        game3TimerDelay = 1000;
        game3Timer = new Timer(game3TimerDelay, this);
        game3Timer.start();
        
        //SETUP GAME #3 COUNTDOWN TIMER
        game3TimerDelay = 1000;
        game3TimerCountDown = new Timer(game3TimerDelay, this);
        
        //SETUP GAME OVER OVERLAY TIMER
        game3OverDelay = 1000;
        game3OverTicks = 5;
        game3OverTimer = new Timer(game3OverDelay, this);
    }
    
    //==========================================================================
    // SETUP INTRODUCTION OVERLAY AND GAME START BUTTON  
    public void setupIntro3Overlay(){
        intro3OverlayBg = new JLabel(intro3OverlayBgImg);
        intro3OverlayBg.setBounds(135,225,684,236);
        intro3OverlayBg.setBorder(BorderFactory.createEmptyBorder());
        intro3OverlayBg.setVisible(true);
        
        intro3OverlayTxt1 = new JLabel("<html><center><font color=#FFFF00 size=5>"
                + "Welcome to the Button Masher Game at the Penn State Behrend Campus!");
        Font font = intro3OverlayTxt1.getFont().deriveFont(Font.BOLD);
        intro3OverlayTxt1.setFont(font);
        intro3OverlayTxt1.setBounds(155,210,650,100); //LEFT,TOP,WIDTH,HEIGHT
        intro3OverlayTxt1.setBorder(BorderFactory.createEmptyBorder());
        intro3OverlayTxt1.setVisible(true);
        
        intro3OverlayTxt2 = new JLabel("<html><center><font color=#FFFFFF size=5>"
                + "This game is a major clickfest so get your fingers ready!  Click all the enemies before the game timer runs out!  The timer is still ticking as you read this so hurry up and get the game started!");
        intro3OverlayTxt2.setBounds(150,225,650,200); //LEFT,TOP,WIDTH,HEIGHT
        intro3OverlayTxt2.setBorder(BorderFactory.createEmptyBorder());
        intro3OverlayTxt2.setVisible(true);
   
        startGame3 = new JButton("Click Here to Start the Game!");
        startGame3.setBounds(340,385,250,40); //LEFT,TOP,WIDTH,HEIGHT
        startGame3.setBorder(BorderFactory.createEmptyBorder());
        startGame3.setVisible(true);
        
        add(startGame3);
        add(intro3OverlayTxt1);
        add(intro3OverlayTxt2);
        add(intro3OverlayBg);
        
        startGame3.addMouseListener(this);
    }
 
    //==========================================================================
    // SETUP GAME OVER OVERLAY      
    public void gameOverOverlay(){
        enemy1.setVisible(false);
        enemy2.setVisible(false);
        enemy3.setVisible(false);
        bonus.setVisible(false);

        gameOverBg = new JLabel(gameOverBgImg);
        gameOverBg.setBounds(255,325,380,135); //LEFT,TOP,WIDTH,HEIGHT
        gameOverBg.setBorder(BorderFactory.createEmptyBorder());
        gameOverBg.setVisible(true);
        
        gameOverTxt1 = new JLabel("<html><center><font color=#FFFFFF size=7>"
                + "Game Over!");
        Font font2 = gameOverTxt1.getFont().deriveFont(Font.BOLD);
        gameOverTxt1.setFont(font2);
        gameOverTxt1.setBounds(340,345,360,60); //LEFT,TOP,WIDTH,HEIGHT
        gameOverTxt1.setBorder(BorderFactory.createEmptyBorder());
        gameOverTxt1.setVisible(true);
        
        gameOverTxt2 = new JLabel("<html><center><font color=#FFFFFF size=5>"
                + "You have scored " + player.game3Score + " points!");
        gameOverTxt2.setFont(font2);
        gameOverTxt2.setBounds(325,375,360,60); //LEFT,TOP,WIDTH,HEIGHT
        gameOverTxt2.setBorder(BorderFactory.createEmptyBorder());
        gameOverTxt2.setVisible(true);
        
        add(gameOverTxt1);
        add(gameOverTxt2);
        add(gameOverBg);
    }

    //==========================================================================
    // SETUP TOKEN RING GAME MECHANIC
    public void token(){
        tokenNum = tokenNum + 1;
        if(tokenNum==1){
            if(!enemy1Clicked){enemy1.setVisible(false);enemy1Clicked=true;}
            if(!enemy2Clicked){enemy2.setVisible(false);enemy2Clicked=true;}
            numRandomizer();
        }
        
        if(tokenNum==2){
            if(!bonusClicked){bonus.setVisible(false);bonusClicked=true;}
            numRandomizer();
        }
        
        if(tokenNum==3){
            numRandomizer();
        }
        
        if(tokenNum==4){
            if(!enemy2Clicked){enemy2.setVisible(false);enemy2Clicked=true;}
            if(!enemy3Clicked){enemy3.setVisible(false);enemy3Clicked=true;}
            if(!bonusClicked){bonus.setVisible(false);bonusClicked=true;}
            numRandomizer();
        }
        
        if(tokenNum==5){
            numRandomizer();
        }
        
        if(tokenNum==6){
            tokenNum = 0;
            if(!bonusClicked){bonus.setVisible(false);bonusClicked=true;}
            numRandomizer();
        }
    }

    //==========================================================================
    // SETUP ENEMY/ITEM RANDOMIZER
    public void numRandomizer(){
        genNum = new Random().nextInt(99);
        
        if(genNum < 70){
            if(enemy1Clicked){locationRandomizer(enemy1);}
            if(enemy3Clicked){locationRandomizer(enemy3);}
        }
        
        if(genNum > 56){
            if(enemy2Clicked){locationRandomizer(enemy2);}
        }
        
        if(genNum > 76 && genNum < 83){
            if(bonusClicked){locationRandomizer(bonus);}
        }
        
    }
    
    //==========================================================================
    // LOCATION RANDOMIZER
    public void locationRandomizer(JButton a){
        itemType = a;
        int randomX = new Random().nextInt(820); //ORIG 960
        int randomY = new Random().nextInt(588); //ORIG 698
        randomY = randomY + 70;
        
        if(itemType == enemy1){
            enemy1.setLocation(randomX,randomY);
            enemy1.setVisible(true);
        }
        else if(itemType == enemy2){
            enemy2.setLocation(randomX,randomY);
            enemy2.setVisible(true);
        }
        else if(itemType == enemy3){
            enemy3.setLocation(randomX,randomY);
            enemy3.setVisible(true);
        }
        else if(itemType == bonus){
            bonus.setLocation(randomX,randomY);
            bonus.setVisible(true);
        }
    }    
    
    //==========================================================================
    // SET ACTION PERFORMED LISTENERS (GAME TIMERS AND BUTTONS)
    public void actionPerformed(ActionEvent event){
        Object obj = event.getSource();
	
        //DO ON EACH GAME TIMER TICK
        if (obj == game3Timer){
            player.game3Time = player.game3Time + 1; //UPDATE GAME TIMER
            repaint();}
        
        //DO ON EACH COUNTDOWN TIMER TICK
        if (obj == game3TimerCountDown){
            token(); //ENABLE NEXT TOKEN
            
            game3TimeRemaining = game3TimeRemaining - 1; //UPDATE COUNTDOWN TIMER VALUE
            pbVertical.setValue(game3TimeRemaining); //UPDATE VERTICAL PROGRESS BAR
            game3TimerDelay = game3TimerDelay - delayModifier; //INCREASE DELAY BY delayModifier
            game3TimerCountDown.setDelay(game3TimerDelay); //SET NEW DELAY
            
            if(game3TimeRemaining==0){
                game3TimerCountDown.stop(); //STOP COUNTDOWN TIMER
                gameEnded=1; //SET MINI-GAME FLAG THAT GAME HAS ENDED
                gameOverOverlay(); //DISPLAY GAME OVER OVERLAY SCREEN
                game3OverTimer.start();
            }
        }
        if (obj == game3OverTimer){
                game3OverTicks = game3OverTicks - 1;
                if(game3OverTicks==0){
                    game3OverTimer.stop();
                    game3Timer.stop(); //STOP THIS MINI-GAME TIMER
                    
                    player.gameCampusThreePlayed = true; //SET FLAG THAT THIS GAME HAS BEEN PLAYED
                    player.currentMapTile = "B01"; //SET NEW POSITION FOR PLAYER ON MAIN MAP
                    player.gameScore = player.gameScore + player.game3Score; //UPDATE SCORE TOTAL
                    player.gameTime = player.gameTime + player.game3Time; //UPDATE GAME TIME TOTAL
                    removeAll(); //REMOVE ALL ELEMENTS FROM SCREEN
                    p07PanelGameMap = new panelGameMap(player); //LOAD GAME MAP PANEL
                    p07PanelGameMap.setBounds(0,0,1024,768);
                    add(p07PanelGameMap);
                    validate();
                    repaint(); 
                }
        }
    }
    
    //==========================================================================
    // MOUSE LISTENER
    public void mouseClicked(MouseEvent e){
        if (e.getComponent()==startGame3){           
            intro3OverlayBg.setVisible(false);
            intro3OverlayTxt1.setVisible(false);
            intro3OverlayTxt2.setVisible(false);
            startGame3.setVisible(false);
           
            game3TimerCountDown.start();
            pbVertical.setValue(game3TimeRemaining); //UPDATE VERTICAL PROGRESS BAR
            gameStarted = 1;
        }
    }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void mousePressed(MouseEvent e) { 
        if(gameStarted==1 && gameEnded==0){
            if (e.getComponent()==enemy1){
                player.game3Score = player.game3Score + 125;
                numOfEnemy1Clicked = numOfEnemy1Clicked + 1;
                enemy1.setVisible(false);
                enemy1Clicked = true;
                repaint();
            }
            if (e.getComponent()==enemy2){
                player.game3Score = player.game3Score + 375;
                numOfEnemy2Clicked = numOfEnemy2Clicked + 1;
                enemy2.setVisible(false);
                enemy2Clicked = true;
                repaint();
            }
            if (e.getComponent()==enemy3){
                player.game3Score = player.game3Score + 125;
                numOfEnemy1Clicked = numOfEnemy1Clicked + 1;
                enemy3.setVisible(false);
                enemy3Clicked = true;
                repaint();
            }
            if (e.getComponent()==bonus){
                player.game3Score = player.game3Score + 2500;
                numOfBonusClicked = numOfBonusClicked + 1;
                bonus.setVisible(false);
                bonusClicked = true;
                repaint();
            }
        }
    }
    public void mouseReleased(MouseEvent e) { }
    //==========================================================================
 
}        	