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
import java.awt.Image;

public class panelGame2 extends JPanel implements MouseListener, ActionListener {

    panelGameMap p07PanelGameMap;
    objPlayer player;
    String dialogToBuild,playerChoice,enemyChoice,matchOutcome,gameWinner;
    Timer game2Timer,game2EnemyChoosing,game2EnemyCalling,game2MatchOutcome,
          game2Reset,game2DialogReset,game2OverTimer;
    boolean firstDialogMessage;
    
    int game2Started,game2Ended,game2TimerDelay,game2Mode,game2GenNum,
        game2Delay1,game2Delay2,game2Delay3,game2Delay4,game2Delay5,game2Delay6,
        gamesWon,gamesLost,gamesTied,gamesWonInARow;
    
    JButton startGame2,
            buttonRock,buttonPaper,buttonScissors,
            buttonRockH,buttonRockS,buttonRockI,
            buttonPaperH,buttonPaperS,buttonPaperI,
            buttonScissorsH,buttonScissorsS,buttonScissorsI;

    JLabel intro2OverlayBg,intro2OverlayTxt1,intro2OverlayTxt2,
           gameOverBg,gameOverTxt1,gameOverTxt2,
           enemyPlay,enemyWin,enemyLoss,speechBubble,
           plus500points,plus2500points,bonus2500points,bonus5000points,
           bonus10000points,bonus25000points,enemyThinkingMove,
           enemyThinkingDone,gameDialogMsg,game2OverTxt1,game2OverTxt2;
    
    ImageIcon gameBackgroundPSU = new ImageIcon("images/game2BackgroundPsu.jpg"),
              gameBackgroundWU = new ImageIcon("images/game2BackgroundWu.jpg"),
              gameBackground8BIT = new ImageIcon("images/game2Background8Bit.jpg"),
              gameBackgroundDiaper = new ImageIcon("images/game2BackgroundDiaper.jpg"),
              charMapPortraitDaddy = new ImageIcon("images/iconDaddy.png"),
              charMapPortraitMommy = new ImageIcon("images/iconMommy.png"),
              charMapPortraitBaby = new ImageIcon("images/iconBaby.png");
    
    Image   gameMapPsuBg = gameBackgroundPSU.getImage(),
            gameMapWuBg = gameBackgroundWU.getImage(),
            gameMap8BitBg = gameBackground8BIT.getImage(),
            gameMapDiaperBg = gameBackgroundDiaper.getImage(),
            gameMapCharDaddy = charMapPortraitDaddy.getImage(),
            gameMapCharMommy = charMapPortraitMommy.getImage(),
            gameMapCharBaby = charMapPortraitBaby.getImage();
		
    Icon 
        enemyPsuPlay = new ImageIcon("images/game2PsuFacePlay.png"),
        enemyPsuWin = new ImageIcon("images/game2PsuFaceWin.png"),
        enemyPsuLoss = new ImageIcon("images/game2PsuFaceLoss.png"),
        enemyWuPlay = new ImageIcon("images/game2WuFacePlay.png"),
        enemyWuWin = new ImageIcon("images/game2WuFaceWin.png"),
        enemyWuLoss = new ImageIcon("images/game2WuFaceLoss.png"),
        enemy8BitPlay = new ImageIcon("images/game28BitFacePlay.png"),
        enemy8BitWin = new ImageIcon("images/game28BitFaceWin.png"),
        enemy8BitLoss = new ImageIcon("images/game28BitFaceLoss.png"),
        enemyDiaperPlay = new ImageIcon("images/game2DiaperFacePlay.png"),
        enemyDiaperWin = new ImageIcon("images/game2DiaperFaceWin.png"),
        enemyDiaperLoss = new ImageIcon("images/game2DiaperFaceLoss.png"),
        enemyThinking = new ImageIcon("images/game2EnemyThinking.gif"),
        enemyThinkingOver = new ImageIcon("images/game2EnemyThinkingDone.png"),    
        speechBubblePsu = new ImageIcon("images/game2PsuChatBubble.png"),
        speechBubbleWu = new ImageIcon("images/game2WuChatBubble.png"),
        speechBubble8Bit = new ImageIcon("images/game28BitChatBubble.png"),
        speechBubbleDiaper = new ImageIcon("images/game2DiaperChatBubble.png"),
        buttonRockItem = new ImageIcon("images/game2RockButton.png"),
        buttonRockHighlighted = new ImageIcon("images/game2RockButtonHighlighted.png"),
        buttonRockSelected = new ImageIcon("images/game2RockButtonSelected.png"),
        buttonRockInactive = new ImageIcon("images/game2RockButtonInactive.png"),
        buttonPaperItem = new ImageIcon("images/game2PaperButton.png"),
        buttonPaperHighlighted = new ImageIcon("images/game2PaperButtonHighlighted.png"),
        buttonPaperSelected = new ImageIcon("images/game2PaperButtonSelected.png"),
        buttonPaperInactive = new ImageIcon("images/game2PaperButtonInactive.png"),
        buttonScissorsItem = new ImageIcon("images/game2ScissorsButton.png"),
        buttonScissorsHighlighted = new ImageIcon("images/game2ScissorsButtonHighlighted.png"),
        buttonScissorsSelected = new ImageIcon("images/game2ScissorsButtonSelected.png"),
        buttonScissorsInactive = new ImageIcon("images/game2ScissorsButtonInactive.png"),
        intro2OverlayBgImg = new ImageIcon("images/gameOverlay1.png"),
        gameOverBgImg = new ImageIcon("images/gameOverlay3.png");

    //MAIN GAME METHOD
    public panelGame2(objPlayer player)
    {
        super();
        this.player = player;
        setLayout(null);
        
	buildGameInterface();   //BUILD INTERFACE ELEMENTS OF GAME
        setupInitialGameMechanics(); //BUILD TIMERS AND INITIAL VARIABLES
        disableGameButtons();
        setupIntro2Overlay();
    }

    public void paintComponent(Graphics g)
    {
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
        Font f2 = new Font("Gothic", Font.BOLD, 11);
        Font f3 = new Font("Gothic", Font.BOLD, 14);
        g.setFont(f1);
        g.setColor(Color.WHITE);
        g.drawString("Penn State University Park Campus",90,44);
        g.drawString("Timer: ", 580, 44);
        g.drawString("Score: ", 740, 44);
        g.setFont(f2);
        g.drawString("W  |  L", 490, 36);
        g.drawString("|", 506, 48);
        
        //DRAW GAME TIME AND SCORE
        g.setFont(f1);
        g.setColor(Color.YELLOW);
        g.drawString(String.format( "%-10d", player.gameTime + player.game2Time ), 655, 44); //ALIGN LEFT
        g.drawString(String.format( "%010d", player.gameScore + player.game2Score ), 815, 44); //PADDING 10 DIGITS W/ZEROS
        g.setFont(f3);
        g.drawString(String.format( "%-10d", gamesWon ), 491, 48);
        g.drawString(String.format( "%-10d", gamesLost ), 515, 48);
        
        //g.drawString("W  |  L", 490, 48);
    }

    public void buildGameInterface(){
        //SET GAME MODE BASED ON PLAYER CHARACTER
	if(player.character == 1){game2Mode = 1;}
        else if (player.character == 2){game2Mode = 2;}
        else if (player.character == 3){game2Mode = 3;}        
        
        plus500points = new JLabel("<html><center><font color=#FFFF00 size=5>" + "+500" );
        plus500points.setBounds(884,36,100,40);
        plus500points.setVisible(true);
        plus500points.setBorder(BorderFactory.createEmptyBorder());
        plus2500points = new JLabel("<html><center><font color=#FFFF00 size=5>" + "+2500" );
        plus2500points.setBounds(874,36,100,40);
        plus2500points.setVisible(true);
        plus2500points.setBorder(BorderFactory.createEmptyBorder());
        
        bonus2500points = new JLabel("<html><center><font color=#FFFF00 size=5>" + "2 In a Row Bonus! +2500" );
        bonus2500points.setBounds(713,57,300,40);
        bonus2500points.setVisible(true);
        bonus2500points.setBorder(BorderFactory.createEmptyBorder());
        bonus5000points = new JLabel("<html><center><font color=#FFFF00 size=5>" + "3 In a Row Bonus! +5000" );
        bonus5000points.setBounds(713,57,300,40);
        bonus5000points.setVisible(true);
        bonus5000points.setBorder(BorderFactory.createEmptyBorder());
        bonus10000points = new JLabel("<html><center><font color=#FFFF00 size=5>" + "4 In a Row Bonus! +10000" );
        bonus10000points.setBounds(703,57,300,40);
        bonus10000points.setVisible(true);
        bonus10000points.setBorder(BorderFactory.createEmptyBorder());
        bonus25000points = new JLabel("<html><center><font color=#FFFF00 size=5>" + "FLAWLESS VICTORY! +25000" );
        bonus25000points.setBounds(668,57,300,40);
        bonus25000points.setVisible(true);
        bonus25000points.setBorder(BorderFactory.createEmptyBorder());

        //SET ENEMY GRAPHICS BASED ON THEME
	if(player.gameTheme == 1)
        {
	enemyPlay = new JLabel(enemyPsuPlay);
        enemyPlay.setBounds(630,288,205,154);
	enemyWin = new JLabel(enemyPsuWin);
        enemyWin.setBounds(630,288,206,165);
        enemyLoss = new JLabel(enemyPsuLoss);
        enemyLoss.setBounds(630,288,209,167);
        speechBubble = new JLabel(speechBubblePsu);
        speechBubble.setBounds(205,110,534,233);
        enemyThinkingMove = new JLabel(enemyThinking);
        enemyThinkingMove.setBounds(465,100,260,260);
	}
	
	else if(player.gameTheme == 2)
	{
	enemyPlay = new JLabel(enemyWuPlay);
        enemyPlay.setBounds(750,180,156,104);
	enemyWin = new JLabel(enemyWuWin);
        enemyWin.setBounds(755,182,144,118);
        enemyLoss = new JLabel(enemyWuLoss);
        enemyLoss.setBounds(768,180,122,157);
        speechBubble = new JLabel(speechBubbleWu);
        speechBubble.setBounds(105,110,632,227);
        enemyThinkingMove = new JLabel(enemyThinking);
        enemyThinkingMove.setBounds(465,45,260,260);
	}
	
	else if(player.gameTheme == 3)
	{
	enemyPlay = new JLabel(enemy8BitPlay);
        enemyPlay.setBounds(620,250,237,225);
	enemyWin = new JLabel(enemy8BitWin);
        enemyWin.setBounds(620,250,236,225);
        enemyLoss = new JLabel(enemy8BitLoss);
        enemyLoss.setBounds(620,250,227,225);
        speechBubble = new JLabel(speechBubble8Bit);
        speechBubble.setBounds(105,110,543,270);
        enemyThinkingMove = new JLabel(enemyThinking);
        enemyThinkingMove.setBounds(425,45,260,260);
	}
	
	else if(player.gameTheme == 4)
	{
	enemyPlay = new JLabel(enemyDiaperPlay);
        enemyPlay.setBounds(694,238,196,169);
	enemyWin = new JLabel(enemyDiaperWin);
        enemyWin.setBounds(694,250,196,163);
        enemyLoss = new JLabel(enemyDiaperLoss);
        enemyLoss.setBounds(698,252,218,160);
        speechBubble = new JLabel(speechBubbleDiaper);
        speechBubble.setBounds(125,110,489,251);
        enemyThinkingMove = new JLabel(enemyThinking);
        enemyThinkingMove.setBounds(385,45,260,260);
	}

        //SET GLOBAL GAME-BASED GRAPHICS SETTINGS
        enemyPlay.setVisible(true);
        enemyPlay.setBorder(BorderFactory.createEmptyBorder());
        enemyWin.setVisible(false);
        enemyWin.setBorder(BorderFactory.createEmptyBorder());
        enemyLoss.setVisible(false);
        enemyLoss.setBorder(BorderFactory.createEmptyBorder());
        speechBubble.setVisible(false);
        speechBubble.setBorder(BorderFactory.createEmptyBorder());
        enemyThinkingMove.setVisible(false);
        enemyThinkingMove.setBorder(BorderFactory.createEmptyBorder());
        add(enemyWin);
        add(enemyLoss);
        
        //BUILD GLOBAL GAME BUTTONS
        buttonRock = new JButton(buttonRockItem);
        buttonRock.setBounds(135,550,180,180);
        buttonRockH = new JButton(buttonRockHighlighted);
        buttonRockH.setBounds(80,495,289,271);
        buttonRockS = new JButton(buttonRockSelected);
        buttonRockS.setBounds(80,495,289,271);
        buttonRockI = new JButton(buttonRockInactive);
        buttonRockI.setBounds(135,550,180,180);

	buttonPaper = new JButton(buttonPaperItem);
        buttonPaper.setBounds(415,550,180,180);
        buttonPaperH = new JButton(buttonPaperHighlighted);
        buttonPaperH.setBounds(360,495,289,271);
        buttonPaperS = new JButton(buttonPaperSelected);
        buttonPaperS.setBounds(360,495,289,271);
        buttonPaperI = new JButton(buttonPaperInactive);
        buttonPaperI.setBounds(415,550,180,180);

        buttonScissors = new JButton(buttonScissorsItem);
        buttonScissors.setBounds(695,550,179,180);
        buttonScissorsH = new JButton(buttonScissorsHighlighted);
        buttonScissorsH.setBounds(640,495,289,271);
        buttonScissorsS = new JButton(buttonScissorsSelected);
        buttonScissorsS.setBounds(640,495,289,271);
        buttonScissorsI = new JButton(buttonScissorsInactive);
        buttonScissorsI.setBounds(695,550,179,180);

        buttonRock.setVisible(true);
        buttonRock.setBorder(BorderFactory.createEmptyBorder());
        buttonRock.setContentAreaFilled(false);
        buttonRockH.setVisible(false);
        buttonRockH.setBorder(BorderFactory.createEmptyBorder());
        buttonRockH.setContentAreaFilled(false);
        buttonRockS.setVisible(false);
        buttonRockS.setBorder(BorderFactory.createEmptyBorder());
        buttonRockS.setContentAreaFilled(false);
        buttonRockI.setVisible(false);
        buttonRockI.setBorder(BorderFactory.createEmptyBorder());
        buttonRockI.setContentAreaFilled(false);       

        buttonPaper.setVisible(true);
        buttonPaper.setBorder(BorderFactory.createEmptyBorder());
        buttonPaper.setContentAreaFilled(false);
        buttonPaperH.setVisible(false);
        buttonPaperH.setBorder(BorderFactory.createEmptyBorder());
        buttonPaperH.setContentAreaFilled(false);
        buttonPaperS.setVisible(false);
        buttonPaperS.setBorder(BorderFactory.createEmptyBorder());
        buttonPaperS.setContentAreaFilled(false);
        buttonPaperI.setVisible(false);
        buttonPaperI.setBorder(BorderFactory.createEmptyBorder());
        buttonPaperI.setContentAreaFilled(false);      

        buttonScissors.setVisible(true);
        buttonScissors.setBorder(BorderFactory.createEmptyBorder());
        buttonScissors.setContentAreaFilled(false);
        buttonScissorsH.setVisible(false);
        buttonScissorsH.setBorder(BorderFactory.createEmptyBorder());
        buttonScissorsH.setContentAreaFilled(false);
        buttonScissorsS.setVisible(false);
        buttonScissorsS.setBorder(BorderFactory.createEmptyBorder());
        buttonScissorsS.setContentAreaFilled(false);
        buttonScissorsI.setVisible(false);
        buttonScissorsI.setBorder(BorderFactory.createEmptyBorder());
        buttonScissorsI.setContentAreaFilled(false);      

        add(buttonRock);
        add(buttonRockH);
        add(buttonRockS);
        add(buttonRockI);
        add(buttonPaper);
        add(buttonPaperH);
        add(buttonPaperS);
        add(buttonPaperI);
        add(buttonScissors);
        add(buttonScissorsH);
        add(buttonScissorsS);
        add(buttonScissorsI);

        buttonRock.addMouseListener(this);
        buttonRockH.addMouseListener(this);
        buttonRockS.addMouseListener(this);
        buttonRockI.addMouseListener(this);
        buttonPaper.addMouseListener(this);
        buttonPaperH.addMouseListener(this);
        buttonPaperS.addMouseListener(this);
        buttonPaperI.addMouseListener(this);
        buttonScissors.addMouseListener(this);
        buttonScissorsH.addMouseListener(this);
        buttonScissorsS.addMouseListener(this);
        buttonScissorsI.addMouseListener(this);
    }

    //==========================================================================
    // BUILD ENEMY DIALOG
    public void buildDialog(String a){
        dialogToBuild = a;
        
        //==========================================
	//DIALOG FOR GAME STARTING = dialogGameStart
        if(dialogToBuild == "dialogGameStart"){
            if(player.gameTheme == 1){
                speechBubble.setVisible(true);
                gameDialogMsg = new JLabel("<html><center><font color=#FFFFFF size=6>"
                + "You Think You Can Beat Brutus" + "<br />"
                + "the Buckeye?!? I'll CRUSH Any" + "<br />"
                + "NITTANY!  Lets Get Started!!!" + "<br />"
                + "                          "  );
                Font font = gameDialogMsg.getFont().deriveFont(Font.BOLD);
                gameDialogMsg.setFont(font);
                gameDialogMsg.setBounds(295,110,500,175);
                gameDialogMsg.setBorder(BorderFactory.createEmptyBorder());
                gameDialogMsg.setVisible(true);
                add(gameDialogMsg);
                add(speechBubble);
            }
            if(player.gameTheme == 2){
                speechBubble.setVisible(true);
                gameDialogMsg = new JLabel("<html><center><font color=#FFFFFF size=6>"
                + "Yo Fool Step Up To The Wu And" + "<br />"
                + "Get Wrecked Son!  " + "<br />"
                + "Throw Down Punk!!!" + "<br />"
                + "                          "  );
                Font font = gameDialogMsg.getFont().deriveFont(Font.BOLD);
                gameDialogMsg.setFont(font);
                gameDialogMsg.setBounds(175,140,500,175);
                gameDialogMsg.setBorder(BorderFactory.createEmptyBorder());
                gameDialogMsg.setVisible(true);
                add(gameDialogMsg);
                add(speechBubble);
            }
            if(player.gameTheme == 3){
                speechBubble.setVisible(true);
                gameDialogMsg = new JLabel("<html><center><font color=#FFFFFF size=6>"
                + "..." + "<br />"
                + "Who dares challenges King Boo?" + "<br />"
                + "..." + "<br />"
                + "                          "  );
                Font font = gameDialogMsg.getFont().deriveFont(Font.BOLD);
                gameDialogMsg.setFont(font);
                gameDialogMsg.setBounds(175,140,500,175);
                gameDialogMsg.setBorder(BorderFactory.createEmptyBorder());
                gameDialogMsg.setVisible(true);
                add(gameDialogMsg);
                add(speechBubble);
            }
            if(player.gameTheme == 4){
                speechBubble.setVisible(true);
                gameDialogMsg = new JLabel("<html><center><font color=#FFFFFF size=6>"
                + "This is one diaper you cant" + "<br />"
                + "handle!  I sneak up on you at" + "<br />"
                + "4AM, and will leak!" + "<br />"
                + "Try me!!!"  );
                Font font = gameDialogMsg.getFont().deriveFont(Font.BOLD);
                gameDialogMsg.setFont(font);
                gameDialogMsg.setBounds(175,155,500,175);
                gameDialogMsg.setBorder(BorderFactory.createEmptyBorder());
                gameDialogMsg.setVisible(true);
                add(gameDialogMsg);
                add(speechBubble);
            }
        }
	//=====================================
        //DIALOG FOR MATCH WON = dialogMatchWon		
        else if(dialogToBuild == "dialogMatchWon"){
            if(player.gameTheme == 1){
                speechBubble.setVisible(true);
                gameDialogMsg = new JLabel("<html><center><font color=#FFFFFF size=6>"
                + "This isnt possible!" + "<br />"
                + "You got lucky Nittany!" + "<br />"
                + "You wont guess my next play!" + "<br />"
                + "                          "  );
                Font font = gameDialogMsg.getFont().deriveFont(Font.BOLD);
                gameDialogMsg.setFont(font);
                gameDialogMsg.setBounds(305,110,500,175);
                gameDialogMsg.setBorder(BorderFactory.createEmptyBorder());
                gameDialogMsg.setVisible(true);
                add(gameDialogMsg);
                add(speechBubble);
            }
            if(player.gameTheme == 2){
                speechBubble.setVisible(true);
                gameDialogMsg = new JLabel("<html><center><font color=#FFFFFF size=6>"
                + "Whaaaaaaaaaaaack!" + "<br />"
                + "Try see if you can repeat" + "<br />"
                + "that again fool!!!" + "<br />"
                + "                          "  );
                Font font = gameDialogMsg.getFont().deriveFont(Font.BOLD);
                gameDialogMsg.setFont(font);
                gameDialogMsg.setBounds(230,130,500,175);
                gameDialogMsg.setBorder(BorderFactory.createEmptyBorder());
                gameDialogMsg.setVisible(true);
                add(gameDialogMsg);
                add(speechBubble);
            }
            if(player.gameTheme == 3){
                speechBubble.setVisible(true);
                gameDialogMsg = new JLabel("<html><center><font color=#FFFFFF size=6>"
                + "..." + "<br />"
                + "Nooooooooo..." + "<br />"
                + "..." + "<br />"
                + "                          "  );
                Font font = gameDialogMsg.getFont().deriveFont(Font.BOLD);
                gameDialogMsg.setFont(font);
                gameDialogMsg.setBounds(305,130,500,175);
                gameDialogMsg.setBorder(BorderFactory.createEmptyBorder());
                gameDialogMsg.setVisible(true);
                add(gameDialogMsg);
                add(speechBubble);
            }
            if(player.gameTheme == 4){
                speechBubble.setVisible(true);
                gameDialogMsg = new JLabel("<html><center><font color=#FFFFFF size=6>"
                + "Luck! You cant keep me clean!" + "<br />"
                + "Lets go another round and see" + "<br />"
                + "if you can handle what comes" + "<br />"
                + "next!!!"  );
                Font font = gameDialogMsg.getFont().deriveFont(Font.BOLD);
                gameDialogMsg.setFont(font);
                gameDialogMsg.setBounds(160,155,500,175);
                gameDialogMsg.setBorder(BorderFactory.createEmptyBorder());
                gameDialogMsg.setVisible(true);
                add(gameDialogMsg);
                add(speechBubble);
            }
        }		
	//=======================================	
	//DIALOG FOR MATCH LOST = dialogMatchLost		
        else if(dialogToBuild == "dialogMatchLost"){
            if(player.gameTheme == 1){
                speechBubble.setVisible(true);
                gameDialogMsg = new JLabel("<html><center><font color=#FFFFFF size=6>"
                + "Buckeyes!! Buckeyes!!" + "<br />"
                + "Weak Tiger Cant Beat a Tree!" + "<br />"
                + "Buckeyes!! Buckeyes!!" + "<br />"
                + "                          "  );
                Font font = gameDialogMsg.getFont().deriveFont(Font.BOLD);
                gameDialogMsg.setFont(font);
                gameDialogMsg.setBounds(305,110,500,175);
                gameDialogMsg.setBorder(BorderFactory.createEmptyBorder());
                gameDialogMsg.setVisible(true);
                add(gameDialogMsg);
                add(speechBubble);
            }
            if(player.gameTheme == 2){
                speechBubble.setVisible(true);
                gameDialogMsg = new JLabel("<html><center><font color=#FFFFFF size=6>"
                + "Thats Right Son!!!" + "<br />"
                + "Wu Tang Clan Aint Nothin" + "<br />"
                + "To Freak With!" + "<br />"
                + "                          "  );
                Font font = gameDialogMsg.getFont().deriveFont(Font.BOLD);
                gameDialogMsg.setFont(font);
                gameDialogMsg.setBounds(210,130,500,175);
                gameDialogMsg.setBorder(BorderFactory.createEmptyBorder());
                gameDialogMsg.setVisible(true);
                add(gameDialogMsg);
                add(speechBubble);
            }
            if(player.gameTheme == 3){
                speechBubble.setVisible(true);
                gameDialogMsg = new JLabel("<html><center><font color=#FFFFFF size=6>"
                + "..." + "<br />"
                + "Expectations Met..." + "<br />"
                + "..." + "<br />"
                + "                          "  );
                Font font = gameDialogMsg.getFont().deriveFont(Font.BOLD);
                gameDialogMsg.setFont(font);
                gameDialogMsg.setBounds(280,130,500,175);
                gameDialogMsg.setBorder(BorderFactory.createEmptyBorder());
                gameDialogMsg.setVisible(true);
                add(gameDialogMsg);
                add(speechBubble);
            }
            if(player.gameTheme == 4){
                speechBubble.setVisible(true);
                gameDialogMsg = new JLabel("<html><center><font color=#FFFFFF size=6>"
                + "Thats right!!!  Clean me" + "<br />"
                + "and I come back for more!!!" + "<br />"
                + "Setting my next alarm" + "<br />"
                + "for 4AM MUHAHAHA!"  );
                Font font = gameDialogMsg.getFont().deriveFont(Font.BOLD);
                gameDialogMsg.setFont(font);
                gameDialogMsg.setBounds(180,140,500,175);
                gameDialogMsg.setBorder(BorderFactory.createEmptyBorder());
                gameDialogMsg.setVisible(true);
                add(gameDialogMsg);
                add(speechBubble);
            }
        }
        //==================================================================
        //DIALOG FOR TIED MATCH = dialogMatchTied		
        else if(dialogToBuild == "dialogMatchTied"){
            if(player.gameTheme == 1){
                speechBubble.setVisible(true);
                gameDialogMsg = new JLabel("<html><center><font color=#FFFFFF size=6>"
				+ "Hmmmm a tie!"  );
                Font font = gameDialogMsg.getFont().deriveFont(Font.BOLD);
                gameDialogMsg.setFont(font);
                gameDialogMsg.setBounds(385,108,500,175);
                gameDialogMsg.setBorder(BorderFactory.createEmptyBorder());
                gameDialogMsg.setVisible(true);
                add(gameDialogMsg);
                add(speechBubble);
            }
            if(player.gameTheme == 2){
                speechBubble.setVisible(true);
                gameDialogMsg = new JLabel("<html><center><font color=#FFFFFF size=6>"
				+ "Damn Son You Tryin To Be Like Me!"  );
                Font font = gameDialogMsg.getFont().deriveFont(Font.BOLD);
                gameDialogMsg.setFont(font);
                gameDialogMsg.setBounds(150,125,500,175);
                gameDialogMsg.setBorder(BorderFactory.createEmptyBorder());
                gameDialogMsg.setVisible(true);
                add(gameDialogMsg);
                add(speechBubble);
            }
            if(player.gameTheme == 3){
                speechBubble.setVisible(true);
                gameDialogMsg = new JLabel("<html><center><font color=#FFFFFF size=6>"
				+ "...A Tie..."  );
                Font font = gameDialogMsg.getFont().deriveFont(Font.BOLD);
                gameDialogMsg.setFont(font);
                gameDialogMsg.setBounds(330,140,500,175);
                gameDialogMsg.setBorder(BorderFactory.createEmptyBorder());
                gameDialogMsg.setVisible(true);
                add(gameDialogMsg);
                add(speechBubble);
            }
            if(player.gameTheme == 4){
                speechBubble.setVisible(true);
                gameDialogMsg = new JLabel("<html><center><font color=#FFFFFF size=6>"
				+ "You Choose Like A Diaper!"  );
                Font font = gameDialogMsg.getFont().deriveFont(Font.BOLD);
                gameDialogMsg.setFont(font);
                gameDialogMsg.setBounds(182,140,500,175);
                gameDialogMsg.setBorder(BorderFactory.createEmptyBorder());
                gameDialogMsg.setVisible(true);
                add(gameDialogMsg);
                add(speechBubble);
            }
        }
	//====================================================	
	//DIALOG FOR REMATCH AFTER WIN = dialogRematchAfterWin		
        else if(dialogToBuild == "dialogRematchAfterWin"){
            if(player.gameTheme == 1){
                speechBubble.setVisible(true);
                gameDialogMsg = new JLabel("<html><center><font color=#FFFFFF size=6>"
                + "Alright Nittany!" + "<br />"
                + "Im going to get you this" + "<br />"
                + "time you blue tiger!!!" + "<br />"
                + "                          "  );
                Font font = gameDialogMsg.getFont().deriveFont(Font.BOLD);
                gameDialogMsg.setFont(font);
                gameDialogMsg.setBounds(335,110,500,175);
                gameDialogMsg.setBorder(BorderFactory.createEmptyBorder());
                gameDialogMsg.setVisible(true);
                add(gameDialogMsg);
                add(speechBubble);
            }
            if(player.gameTheme == 2){
                speechBubble.setVisible(true);
                gameDialogMsg = new JLabel("<html><center><font color=#FFFFFF size=6>"
                + "Alright im set." + "<br />"
                + "I just needed to regather" + "<br />"
                + "myself.  Prepare for a" + "<br />"
                + "whooping kid!!!"  );
                Font font = gameDialogMsg.getFont().deriveFont(Font.BOLD);
                gameDialogMsg.setFont(font);
                gameDialogMsg.setBounds(220,130,500,175);
                gameDialogMsg.setBorder(BorderFactory.createEmptyBorder());
                gameDialogMsg.setVisible(true);
                add(gameDialogMsg);
                add(speechBubble);
            }
            if(player.gameTheme == 3){
                speechBubble.setVisible(true);
                gameDialogMsg = new JLabel("<html><center><font color=#FFFFFF size=6>"
                + "..." + "<br />"
                + "Ill get you this time..." + "<br />"
                + "..." + "<br />"
                + "                          "  );
                Font font = gameDialogMsg.getFont().deriveFont(Font.BOLD);
                gameDialogMsg.setFont(font);
                gameDialogMsg.setBounds(265,130,500,175);
                gameDialogMsg.setBorder(BorderFactory.createEmptyBorder());
                gameDialogMsg.setVisible(true);
                add(gameDialogMsg);
                add(speechBubble);
            }
            if(player.gameTheme == 4){
                speechBubble.setVisible(true);
                gameDialogMsg = new JLabel("<html><center><font color=#FFFFFF size=6>"
                + "Im ready for you." + "<br />"
                + "This time I wont be so" + "<br />"
                + "easy!  Diaper Power!!!" + "<br />"
                + ""  );
                Font font = gameDialogMsg.getFont().deriveFont(Font.BOLD);
                gameDialogMsg.setFont(font);
                gameDialogMsg.setBounds(220,140,500,175);
                gameDialogMsg.setBorder(BorderFactory.createEmptyBorder());
                gameDialogMsg.setVisible(true);
                add(gameDialogMsg);
                add(speechBubble);
            }
        }		
	//======================================================	
	//DIALOG FOR REMATCH AFTER LOSS = dialogRematchAfterLoss		
        else if(dialogToBuild == "dialogRematchAfterLoss"){
            if(player.gameTheme == 1){
                speechBubble.setVisible(true);
                gameDialogMsg = new JLabel("<html><center><font color=#FFFFFF size=6>"
                + "Dont want to give up yet?" + "<br />"
                + "Thats fine, just remember" + "<br />"
                + "OHIO RULES LOL!!!" + "<br />"
                + "                          "  );
                Font font = gameDialogMsg.getFont().deriveFont(Font.BOLD);
                gameDialogMsg.setFont(font);
                gameDialogMsg.setBounds(325,115,500,175);
                gameDialogMsg.setBorder(BorderFactory.createEmptyBorder());
                gameDialogMsg.setVisible(true);
                add(gameDialogMsg);
                add(speechBubble);
            }
            if(player.gameTheme == 2){
                speechBubble.setVisible(true);
                gameDialogMsg = new JLabel("<html><center><font color=#FFFFFF size=6>"
                + "Try again kid." + "<br />"
                + "Aint nothing wrong with that." + "<br />"
                + "Just dont think you can" + "<br />"
                + "beat this Shaolin style!!!"  );
                Font font = gameDialogMsg.getFont().deriveFont(Font.BOLD);
                gameDialogMsg.setFont(font);
                gameDialogMsg.setBounds(190,130,500,175);
                gameDialogMsg.setBorder(BorderFactory.createEmptyBorder());
                gameDialogMsg.setVisible(true);
                add(gameDialogMsg);
                add(speechBubble);
            }
            if(player.gameTheme == 3){
                speechBubble.setVisible(true);
                gameDialogMsg = new JLabel("<html><center><font color=#FFFFFF size=6>"
                + "..." + "<br />"
                + "Rinse and repeat..." + "<br />"
                + "..." + "<br />"
                + "                          "  );
                Font font = gameDialogMsg.getFont().deriveFont(Font.BOLD);
                gameDialogMsg.setFont(font);
                gameDialogMsg.setBounds(290,130,500,175);
                gameDialogMsg.setBorder(BorderFactory.createEmptyBorder());
                gameDialogMsg.setVisible(true);
                add(gameDialogMsg);
                add(speechBubble);
            }
            if(player.gameTheme == 4){
                speechBubble.setVisible(true);
                gameDialogMsg = new JLabel("<html><center><font color=#FFFFFF size=6>"
                + "Its ok.  I see many" + "<br />"
                + "dirtier diapers in your" + "<br />"
                + "future!!! Its endless!" + "<br />"
                + "MUHAHAHAAHAHAHA!!!"  );
                Font font = gameDialogMsg.getFont().deriveFont(Font.BOLD);
                gameDialogMsg.setFont(font);
                gameDialogMsg.setBounds(200,140,500,175);
                gameDialogMsg.setBorder(BorderFactory.createEmptyBorder());
                gameDialogMsg.setVisible(true);
                add(gameDialogMsg);
                add(speechBubble);
            }
        }
        //==================================================================
        //DIALOG FOR ENEMY CHOOSING ROCK = dialogEnemyChoosesRock		
        else if(dialogToBuild == "dialogEnemyChoosesRock"){
            if(player.gameTheme == 1){
                speechBubble.setVisible(true);
                gameDialogMsg = new JLabel("<html><center><font color=#FFFFFF size=6>"
                + "Ohio Chooses ROCK!"  );
                Font font = gameDialogMsg.getFont().deriveFont(Font.BOLD);
                gameDialogMsg.setFont(font);
                gameDialogMsg.setBounds(345,108,500,175);
                gameDialogMsg.setBorder(BorderFactory.createEmptyBorder());
                gameDialogMsg.setVisible(true);
                add(gameDialogMsg);
                add(speechBubble);
            }
            if(player.gameTheme == 2){
                speechBubble.setVisible(true);
                gameDialogMsg = new JLabel("<html><center><font color=#FFFFFF size=6>"
                + "Goin ROCK Son!"  );
                Font font = gameDialogMsg.getFont().deriveFont(Font.BOLD);
                gameDialogMsg.setFont(font);
                gameDialogMsg.setBounds(255,125,500,175);
                gameDialogMsg.setBorder(BorderFactory.createEmptyBorder());
                gameDialogMsg.setVisible(true);
                add(gameDialogMsg);
                add(speechBubble);
            }
            if(player.gameTheme == 3){
                speechBubble.setVisible(true);
                gameDialogMsg = new JLabel("<html><center><font color=#FFFFFF size=6>"
                + "...ROCK!..."  );
                Font font = gameDialogMsg.getFont().deriveFont(Font.BOLD);
                gameDialogMsg.setFont(font);
                gameDialogMsg.setBounds(330,140,500,175);
                gameDialogMsg.setBorder(BorderFactory.createEmptyBorder());
                gameDialogMsg.setVisible(true);
                add(gameDialogMsg);
                add(speechBubble);
            }
            if(player.gameTheme == 4){
                speechBubble.setVisible(true);
                gameDialogMsg = new JLabel("<html><center><font color=#FFFFFF size=6>"
                + "Roooock!!!"  );
                Font font = gameDialogMsg.getFont().deriveFont(Font.BOLD);
                gameDialogMsg.setFont(font);
                gameDialogMsg.setBounds(278,140,500,175);
                gameDialogMsg.setBorder(BorderFactory.createEmptyBorder());
                gameDialogMsg.setVisible(true);
                add(gameDialogMsg);
                add(speechBubble);
            }
        }
        //==================================================================
        //DIALOG FOR ENEMY CHOOSING PAPER = dialogEnemyChoosesPaper		
        else if(dialogToBuild == "dialogEnemyChoosesPaper"){
            if(player.gameTheme == 1){
                speechBubble.setVisible(true);
                gameDialogMsg = new JLabel("<html><center><font color=#FFFFFF size=6>"
				+ "Buckeyes Go PAPER!"  );
                Font font = gameDialogMsg.getFont().deriveFont(Font.BOLD);
                gameDialogMsg.setFont(font);
                gameDialogMsg.setBounds(345,108,500,175);
                gameDialogMsg.setBorder(BorderFactory.createEmptyBorder());
                gameDialogMsg.setVisible(true);
                add(gameDialogMsg);
                add(speechBubble);
            }
            if(player.gameTheme == 2){
                speechBubble.setVisible(true);
                gameDialogMsg = new JLabel("<html><center><font color=#FFFFFF size=6>"
				+ "PAPER Time Kid!"  );
                Font font = gameDialogMsg.getFont().deriveFont(Font.BOLD);
                gameDialogMsg.setFont(font);
                gameDialogMsg.setBounds(250,125,500,175);
                gameDialogMsg.setBorder(BorderFactory.createEmptyBorder());
                gameDialogMsg.setVisible(true);
                add(gameDialogMsg);
                add(speechBubble);
            }
            if(player.gameTheme == 3){
                speechBubble.setVisible(true);
                gameDialogMsg = new JLabel("<html><center><font color=#FFFFFF size=6>"
				+ "...PAPER!..."  );
                Font font = gameDialogMsg.getFont().deriveFont(Font.BOLD);
                gameDialogMsg.setFont(font);
                gameDialogMsg.setBounds(325,140,500,175);
                gameDialogMsg.setBorder(BorderFactory.createEmptyBorder());
                gameDialogMsg.setVisible(true);
                add(gameDialogMsg);
                add(speechBubble);
            }
            if(player.gameTheme == 4){
                speechBubble.setVisible(true);
                gameDialogMsg = new JLabel("<html><center><font color=#FFFFFF size=6>"
				+ "Paper!!!!!"  );
                Font font = gameDialogMsg.getFont().deriveFont(Font.BOLD);
                gameDialogMsg.setFont(font);
                gameDialogMsg.setBounds(278,140,500,175);
                gameDialogMsg.setBorder(BorderFactory.createEmptyBorder());
                gameDialogMsg.setVisible(true);
                add(gameDialogMsg);
                add(speechBubble);
            }
        }
        //==================================================================
        //DIALOG FOR ENEMY CHOOSING SCISSORS = dialogEnemyChoosesScissors		
        else if(dialogToBuild == "dialogEnemyChoosesScissors"){
            if(player.gameTheme == 1){
                speechBubble.setVisible(true);
                gameDialogMsg = new JLabel("<html><center><font color=#FFFFFF size=6>"
				+ "We Choose SCISSORS!"  );
                Font font = gameDialogMsg.getFont().deriveFont(Font.BOLD);
                gameDialogMsg.setFont(font);
                gameDialogMsg.setBounds(342,108,500,175);
                gameDialogMsg.setBorder(BorderFactory.createEmptyBorder());
                gameDialogMsg.setVisible(true);
                add(gameDialogMsg);
                add(speechBubble);
            }
            if(player.gameTheme == 2){
                speechBubble.setVisible(true);
                gameDialogMsg = new JLabel("<html><center><font color=#FFFFFF size=6>"
				+ "Cut You With SCISSORS!"  );
                Font font = gameDialogMsg.getFont().deriveFont(Font.BOLD);
                gameDialogMsg.setFont(font);
                gameDialogMsg.setBounds(220,125,500,175);
                gameDialogMsg.setBorder(BorderFactory.createEmptyBorder());
                gameDialogMsg.setVisible(true);
                add(gameDialogMsg);
                add(speechBubble);
            }
            if(player.gameTheme == 3){
                speechBubble.setVisible(true);
                gameDialogMsg = new JLabel("<html><center><font color=#FFFFFF size=6>"
				+ "...SCISSORS!..."  );
                Font font = gameDialogMsg.getFont().deriveFont(Font.BOLD);
                gameDialogMsg.setFont(font);
                gameDialogMsg.setBounds(310,140,500,175);
                gameDialogMsg.setBorder(BorderFactory.createEmptyBorder());
                gameDialogMsg.setVisible(true);
                add(gameDialogMsg);
                add(speechBubble);
            }
            if(player.gameTheme == 4){
                speechBubble.setVisible(true);
                gameDialogMsg = new JLabel("<html><center><font color=#FFFFFF size=6>"
				+ "Scissors!!"  );
                Font font = gameDialogMsg.getFont().deriveFont(Font.BOLD);
                gameDialogMsg.setFont(font);
                gameDialogMsg.setBounds(278,140,500,175);
                gameDialogMsg.setBorder(BorderFactory.createEmptyBorder());
                gameDialogMsg.setVisible(true);
                add(gameDialogMsg);
                add(speechBubble);
            }
        }
	//==============================================================
	//DIALOG FOR GAMEOVER AFTER WINNING MOST = dialogGameOverWonMost		
        else if(dialogToBuild == "dialogGameOverWonMost"){
            if(player.gameTheme == 1){
                speechBubble.setVisible(true);
                gameDialogMsg = new JLabel("<html><center><font color=#FFFFFF size=6>"
                + "I cant believe thats it!" + "<br />"
                + "This isnt over!" + "<br />"
                + "Ohio cant lose to Penn State!" + "<br />"
                + ":("  );
                Font font = gameDialogMsg.getFont().deriveFont(Font.BOLD);
                gameDialogMsg.setFont(font);
                gameDialogMsg.setBounds(310,120,500,175);
                gameDialogMsg.setBorder(BorderFactory.createEmptyBorder());
                gameDialogMsg.setVisible(true);
                add(gameDialogMsg);
                add(speechBubble);
            }
            if(player.gameTheme == 2){
                speechBubble.setVisible(true);
                gameDialogMsg = new JLabel("<html><center><font color=#FFFFFF size=6>"
                + "You got me son." + "<br />"
                + "You got that sick nasty style." + "<br />"
                + "Peace God!" + "<br />"
                + ""  );
                Font font = gameDialogMsg.getFont().deriveFont(Font.BOLD);
                gameDialogMsg.setFont(font);
                gameDialogMsg.setBounds(190,130,500,175);
                gameDialogMsg.setBorder(BorderFactory.createEmptyBorder());
                gameDialogMsg.setVisible(true);
                add(gameDialogMsg);
                add(speechBubble);
            }
            if(player.gameTheme == 3){
                speechBubble.setVisible(true);
                gameDialogMsg = new JLabel("<html><center><font color=#FFFFFF size=6>"
                + "Noooooo..." + "<br />"
                + "King Boo Cant Lose..." + "<br />"
                + "Noooooo..." + "<br />"
                + "                          "  );
                Font font = gameDialogMsg.getFont().deriveFont(Font.BOLD);
                gameDialogMsg.setFont(font);
                gameDialogMsg.setBounds(260,135,500,175);
                gameDialogMsg.setBorder(BorderFactory.createEmptyBorder());
                gameDialogMsg.setVisible(true);
                add(gameDialogMsg);
                add(speechBubble);
            }
            if(player.gameTheme == 4){
                speechBubble.setVisible(true);
                gameDialogMsg = new JLabel("<html><center><font color=#FFFFFF size=6>"
                + "You may have cleaned" + "<br />"
                + "the last diaper for today," + "<br />"
                + "but I promise Ill be back" + "<br />"
                + "several times tomorrow!!!"  );
                Font font = gameDialogMsg.getFont().deriveFont(Font.BOLD);
                gameDialogMsg.setFont(font);
                gameDialogMsg.setBounds(192,140,500,175);
                gameDialogMsg.setBorder(BorderFactory.createEmptyBorder());
                gameDialogMsg.setVisible(true);
                add(gameDialogMsg);
                add(speechBubble);
            }
        }    
	//==================================================================
	//DIALOG FOR GAMEOVER AFTER LOSING THE MOST = dialogGameOverLostMost		
        else if(dialogToBuild == "dialogGameOverLostMost"){
            if(player.gameTheme == 1){
                speechBubble.setVisible(true);
                gameDialogMsg = new JLabel("<html><center><font color=#FFFFFF size=6>"
                + "Hahaha!  Its Over" + "<br />"
                + "and the Buckeyes Crush" + "<br />"
                + "Penn State Again!  Im Going" + "<br />"
                + "To Dance All Over Campus!!!"  );
                Font font = gameDialogMsg.getFont().deriveFont(Font.BOLD);
                gameDialogMsg.setFont(font);
                gameDialogMsg.setBounds(310,108,500,175);
                gameDialogMsg.setBorder(BorderFactory.createEmptyBorder());
                gameDialogMsg.setVisible(true);
                add(gameDialogMsg);
                add(speechBubble);
            }
            if(player.gameTheme == 2){
                speechBubble.setVisible(true);
                gameDialogMsg = new JLabel("<html><center><font color=#FFFFFF size=6>"
                + "What did you expect" + "<br />"
                + "Stepping up to the Wu?." + "<br />"
                + "Analog Expectations Kid!!!" + "<br />"
                + ""  );
                Font font = gameDialogMsg.getFont().deriveFont(Font.BOLD);
                gameDialogMsg.setFont(font);
                gameDialogMsg.setBounds(210,125,500,175);
                gameDialogMsg.setBorder(BorderFactory.createEmptyBorder());
                gameDialogMsg.setVisible(true);
                add(gameDialogMsg);
                add(speechBubble);
            }
            if(player.gameTheme == 3){
                speechBubble.setVisible(true);
                gameDialogMsg = new JLabel("<html><center><font color=#FFFFFF size=6>"
                + "Now let me go..." + "<br />"
                + "And cheese some players..." + "<br />"
                + "In Mario Kart..." + "<br />"
                + "Muahahahaahaha..."  );
                Font font = gameDialogMsg.getFont().deriveFont(Font.BOLD);
                gameDialogMsg.setFont(font);
                gameDialogMsg.setBounds(230,140,500,175);
                gameDialogMsg.setBorder(BorderFactory.createEmptyBorder());
                gameDialogMsg.setVisible(true);
                add(gameDialogMsg);
                add(speechBubble);
            }
            if(player.gameTheme == 4){
                speechBubble.setVisible(true);
                gameDialogMsg = new JLabel("<html><center><font color=#FFFFFF size=6>"
                + "Your all out of wipes" + "<br />"
                + "and the juicy one just arrived!" + "<br />"
                + "You can try again another day" + "<br />"
                + "but the diaper always wins!!!"  );
                Font font = gameDialogMsg.getFont().deriveFont(Font.BOLD);
                gameDialogMsg.setFont(font);
                gameDialogMsg.setBounds(165,140,500,175);
                gameDialogMsg.setBorder(BorderFactory.createEmptyBorder());
                gameDialogMsg.setVisible(true);
                add(gameDialogMsg);
                add(speechBubble);
            }
        }
    }

    //==========================================================================
    // SETUP THE INITIAL MECHANICS OF THE GAME
    public void setupInitialGameMechanics(){
        //SETUP INITIAL GAME VARIABLES
        game2Started = 0;
        game2Ended = 0;
        game2TimerDelay = 1000;
        gamesWon = 0;
        gamesLost = 0;
        gamesTied = 0;
        gamesWonInARow = 0;
        firstDialogMessage = true;
        
        //START GAME #2 TIMER (TO CONTINUE GLOBAL GAME TIMER)
        game2Timer = new Timer(game2TimerDelay, this);
        game2Timer.start();
        
        //BUILD TIMERS USED ELSEWHERE IN GAME
        game2EnemyChoosing = new Timer(game2TimerDelay, this);
        game2EnemyCalling = new Timer(game2TimerDelay, this);
        game2MatchOutcome = new Timer(game2TimerDelay + 200, this);
        game2Reset = new Timer(game2TimerDelay + 800, this);
        game2DialogReset = new Timer(game2TimerDelay + 500, this);
        game2OverTimer = new Timer(game2TimerDelay, this);
    }

    //==========================================================================
    // SET ACTION PERFORMED LISTENERS (GAME TIMERS AND BUTTONS)
    public void actionPerformed(ActionEvent event){
        Object obj = event.getSource();
	
        //DO ON EACH GAME TIMER TICK
        if (obj == game2Timer){
            player.game2Time = player.game2Time + 1; //UPDATE GAME TIMER
            repaint();}
        
        //TIMER #1 - ENEMY CHOOSING CHOICE
        if (obj == game2EnemyChoosing){
            game2Delay1 = game2Delay1 - 1;
            enemyThinkingMove.setVisible(true);
            add(enemyThinkingMove);
            
            if (game2Delay1 == 0){
               
                enemyThinkingMove.setVisible(false);
                remove(enemyThinkingMove);
                
                
                game2EnemyChoosing.stop();
                game2Delay1 = 6;
                
                if(enemyChoice == "rock"){buildDialog("dialogEnemyChoosesRock");}
                if(enemyChoice == "paper"){buildDialog("dialogEnemyChoosesPaper");}
                if(enemyChoice == "scissors"){buildDialog("dialogEnemyChoosesScissors");}

                game2EnemyCalling.start();
            }
        }

        //TIMER #2 - ENEMY STATING CHOICE MADE
        if (obj == game2EnemyCalling){
             game2Delay2 = game2Delay2 - 1;
             
            if (game2Delay2 == 0){
            remove(gameDialogMsg);
            remove(speechBubble);   
            game2EnemyCalling.stop();
            game2Delay2 = 3;
            
            game2MatchOutcome.start();
            }  
        }

        //TIMER #3 - DISPLAY MATCH OUTCOME & APPLY SCORE CHANGES
        if (obj == game2MatchOutcome){
             game2Delay3 = game2Delay3 - 1;
             
            if (game2Delay3 == 0){
                game2MatchOutcome.stop();
                game2Delay3 = 1;
                
                //IF PLAYER WON
                if(matchOutcome == "playerWon"){ 
                    enemyLoss.setVisible(true);     //CHANGE ENEMY FACE
                    enemyPlay.setVisible(false);    //CHANGE ENEMY FACE
                    buildDialog("dialogMatchWon");  //SET ENEMY LOST DIALOG
	
                    gamesWon = gamesWon + 1;    //UPDATE MATCH WIN COUNT
                    player.game2Score = player.game2Score + 2500; //APPLY POINTS
                    add(plus2500points);    //DISPLAY POINTS ADDED
                    gamesWonInARow = gamesWonInARow + 1; //ADD TO CONSECUTIVE WIN COUNTER
                
                    //IF CONSECUTIVE WIN COUNTER IS ABOVE 2, APPLY PROPER SCORE BONUS AND DISPLAY BONUS ADDED
                    if(gamesWonInARow == 2){player.game2Score = player.game2Score + 2500; add(bonus2500points);}
                    if(gamesWonInARow == 3){player.game2Score = player.game2Score + 5000; add(bonus5000points);}
                    if(gamesWonInARow == 4){player.game2Score = player.game2Score + 10000; add(bonus10000points);}
                    if(gamesWonInARow == 5){player.game2Score = player.game2Score + 25000; add(bonus25000points); }
                
                    game2Reset.start();     //START TIMER #4
                }
                
                //IF PLAYER LOST
                if(matchOutcome == "playerLost"){
                    enemyWin.setVisible(true);  //CHANGE ENEMY FACE
                    enemyPlay.setVisible(false);    //CHANGE ENEMY FACE
                    buildDialog("dialogMatchLost"); //SET ENEMY WON DIALOG
	
                    gamesLost = gamesLost + 1;  //UPDATE MATCH LOSS COUNT
                    gamesWonInARow = 0;     //RESET CONSECUTIVE WIN COUNTER TO ZERO
                    game2Reset.start();     //START TIMER #4
                }
                
                //IF PLAYER TIES
                if(matchOutcome == "playerTied"){
                    enemyWin.setVisible(true);  //CHANGE ENEMY FACE
                    enemyPlay.setVisible(false);    //CHANGE ENEMY FACE
                    buildDialog("dialogMatchTied"); //SET ENEMY TIED DIALOG
	
                    gamesTied = gamesTied + 1;  //UPDATE MATCH TIED COUNT
                    player.game2Score = player.game2Score + 500;    //APPLY POINTS
                    add(plus500points); //DISPLAY POINTS ADDED
                    //gamesWonInARow = 0; //DISABLE TIE PENALTY
                    game2Reset.start();  //START TIMER #4
                }
            }
        }
        
        //TIMER #4 - DETERMINE REMATCH OR GAME OVER AND PREPARE FOR EITHER
        if (obj == game2Reset){
            game2Delay4 = game2Delay4 - 1;
            
            if(game2Delay4 == 0){
                remove(gameDialogMsg);remove(speechBubble);
                remove(plus500points);remove(plus2500points);
                remove(bonus2500points);remove(bonus5000points);
                remove(bonus10000points);remove(bonus25000points);
                game2Reset.stop();
                game2Delay4 = 2;
                
                if(matchOutcome == "playerWon"){
                    if(gamesWon == 4){gameIsOver();}    //CHECK IF GAME IS OVER
                    else {
                        enemyLoss.setVisible(false);
                        enemyPlay.setVisible(true);
                        buildDialog("dialogRematchAfterWin");
                        game2DialogReset.start();
                    }
                }
                
                if(matchOutcome == "playerLost"){
                    if(gamesLost == 4){gameIsOver();}   //CHECK IF GAME IS OVER
                    else {
                        enemyWin.setVisible(false);
                        enemyPlay.setVisible(true);
                        buildDialog("dialogRematchAfterLoss");
                        game2DialogReset.start();
                    }
                }

                if(matchOutcome == "playerTied"){
                    enemyWin.setVisible(false);
                    enemyPlay.setVisible(true);
                    buildDialog("dialogRematchAfterWin");
                    game2DialogReset.start();
                }
            }
        }

        //TIMER #5 - RESET MATCH
        if (obj == game2DialogReset){
            game2Delay5 = game2Delay5 - 1;
            
            if(game2Delay5 == 0){
                remove(gameDialogMsg);
                remove(speechBubble);
                game2DialogReset.stop();
                game2Delay5 = 2;
                resetGameButtons();
            }
        }
        
        //TIMER #6 - GAME IS OVER TIMER
        if (obj == game2OverTimer){
            game2Delay6 = game2Delay6 - 1;
            
            if(game2Delay6 == 5){
                remove(gameDialogMsg);
                remove(speechBubble);
                gameOverOverlay();
            }           
            if(game2Delay6 == 0){
                game2OverTimer.stop();
                game2Timer.stop(); //STOP THIS MINI-GAME TIMER
                
                player.gameCampusTwoPlayed = true; //SET FLAG THAT THIS GAME HAS BEEN PLAYED
                player.currentMapTile = "K07"; //SET NEW POSITION FOR PLAYER ON MAIN MAP
                player.gameScore = player.gameScore + player.game2Score; //UPDATE SCORE TOTAL
                player.gameTime = player.gameTime + player.game2Time; //UPDATE GAME TIME TOTAL
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
        if (e.getComponent()==buttonRock){
            buttonRockS.setVisible(true);
            buttonRockH.setVisible(false);
            buttonRock.setVisible(false);
            buttonPaper.setVisible(false);
            buttonScissors.setVisible(false);
            buttonPaperI.setVisible(true);
            buttonScissorsI.setVisible(true);
            playerChooses("rock");
            }
        if (e.getComponent()==buttonPaper){
            buttonPaperS.setVisible(true);
            buttonPaperH.setVisible(false);
            buttonRock.setVisible(false);
            buttonPaper.setVisible(false);
            buttonScissors.setVisible(false);
            buttonScissorsI.setVisible(true);
            buttonRockI.setVisible(true);
            playerChooses("paper");
            }
        if (e.getComponent()==buttonScissors){
            buttonScissorsS.setVisible(true);
            buttonScissorsH.setVisible(false);
            buttonRock.setVisible(false);
            buttonPaper.setVisible(false);
            buttonScissors.setVisible(false);
            buttonRockI.setVisible(true);
            buttonPaperI.setVisible(true);
            playerChooses("scissors");
            }
        if (e.getComponent()==startGame2){
            intro2OverlayBg.setVisible(false);
            intro2OverlayTxt1.setVisible(false);
            intro2OverlayTxt2.setVisible(false);
            startGame2.setVisible(false);
            resetGameButtons();
            buildDialog("dialogGameStart");  //SETUP INITIAL DIALOG
        }
    }
    public void mouseEntered(MouseEvent e) {
        if (e.getComponent()==buttonRock){
            buttonRockH.setVisible(true);
            buttonRock.setCursor(new Cursor(Cursor.HAND_CURSOR));}
        if (e.getComponent()==buttonPaper){
            buttonPaperH.setVisible(true);
            buttonPaper.setCursor(new Cursor(Cursor.HAND_CURSOR));}
        if (e.getComponent()==buttonScissors){
            buttonScissorsH.setVisible(true);
            buttonScissors.setCursor(new Cursor(Cursor.HAND_CURSOR));}
    }
    public void mouseExited(MouseEvent e) { 
        if (e.getComponent()==buttonRock){
            buttonRockH.setVisible(false);}
        if (e.getComponent()==buttonPaper){
            buttonPaperH.setVisible(false);}
        if (e.getComponent()==buttonScissors){
            buttonScissorsH.setVisible(false);}
    }
    public void mousePressed(MouseEvent e) { }
    public void mouseReleased(MouseEvent e) { }
    

    
    //PLAYER CHOOSES ROCK, PAPER, OR SCISSORS BUTTON
    public void playerChooses(String a){
        playerChoice = a;
        
        //SETUP GAME TIMER COUNTDOWN VALUES
        game2Delay1 = 6;
        game2Delay2 = 3;
        game2Delay3 = 1;
        game2Delay4 = 2;
        game2Delay5 = 2;
        game2Delay6 = 10;
        
        if(firstDialogMessage){remove(gameDialogMsg);remove(speechBubble);}
        enemyChoice = getEnemyChoice();
        matchOutcome = getMatchOutcome(playerChoice,enemyChoice);
        
        game2EnemyChoosing.start(); //START THE FIRST MATCH TIMER
    }
    
    //GET WHAT ENEMY CHOOSES AND RETURN AS STRING VARIABLE
    public String getEnemyChoice(){
        game2GenNum = new Random().nextInt(8);
        if(game2GenNum<3 && player.character==1){enemyChoice = "rock";}
        if(game2GenNum<3 && player.character==2){enemyChoice = "paper";}
        if(game2GenNum<3 && player.character==3){enemyChoice = "rock";}
        if(game2GenNum>2 && game2GenNum<6){enemyChoice = "paper";}
        if(game2GenNum>5){enemyChoice = "scissors";}

        return enemyChoice;
    }
    
    //GET OUTCOME OF MATCH AND RETURN AS STRING VARIABLE
    public String getMatchOutcome(String a,String b){
        a = playerChoice;
        b = enemyChoice;
        
        if(playerChoice=="rock"){
            if(enemyChoice=="rock"){matchOutcome = "playerTied";}
            if(enemyChoice=="paper"){matchOutcome = "playerLost";}
            if(enemyChoice=="scissors"){matchOutcome = "playerWon";}
        }
        else if(playerChoice=="paper"){
            if(enemyChoice=="rock"){matchOutcome = "playerWon";}
            if(enemyChoice=="paper"){matchOutcome = "playerTied";}
            if(enemyChoice=="scissors"){matchOutcome = "playerLost";}
        }
        else if(playerChoice=="scissors"){
            if(enemyChoice=="rock"){matchOutcome = "playerLost";}
            if(enemyChoice=="paper"){matchOutcome = "playerWon";}
            if(enemyChoice=="scissors"){matchOutcome = "playerTied";}
        }
        return matchOutcome;
    }
    
    //RESET ROCK,PAPER,SCISSORS BUTTONS AT BEGINNING OF REMATCH
    public void resetGameButtons(){
        buttonRock.setVisible(true);
        buttonRockH.setVisible(false);
        buttonRockS.setVisible(false);
        buttonRockI.setVisible(false);    
        buttonPaper.setVisible(true);
        buttonPaperH.setVisible(false);
        buttonPaperS.setVisible(false);
        buttonPaperI.setVisible(false);   
        buttonScissors.setVisible(true);
        buttonScissorsH.setVisible(false);
        buttonScissorsS.setVisible(false);
        buttonScissorsI.setVisible(false);
    }
    
    //DISABLE ROCK,PAPER,SCISSORS BUTTONS
    public void disableGameButtons(){
        buttonRock.setVisible(false);
        buttonRockH.setVisible(false);
        buttonRockS.setVisible(false);
        buttonRockI.setVisible(true);    
        buttonPaper.setVisible(false);
        buttonPaperH.setVisible(false);
        buttonPaperS.setVisible(false);
        buttonPaperI.setVisible(true);   
        buttonScissors.setVisible(false);
        buttonScissorsH.setVisible(false);
        buttonScissorsS.setVisible(false);
        buttonScissorsI.setVisible(true);
    }
    
    //SET THE GAME AS OVER
    public void gameIsOver(){
        disableGameButtons();
        game2Ended=1; //SET MINI-GAME FLAG THAT GAME HAS ENDED
        if(gamesWon == 5) {gameWinner = "player";} else {gameWinner = "enemy";}
        remove(gameDialogMsg); remove(speechBubble);
        if(gameWinner == "player"){ buildDialog("dialogGameOverWonMost"); }
        if(gameWinner == "enemy"){ buildDialog("dialogGameOverLostMost"); }
	game2OverTimer.start();
}
    
    //BUILD THE GAME OVER SUMMARY OVERLAY
    public void gameOverOverlay(){
        gameOverBg = new JLabel(gameOverBgImg);
        gameOverBg.setBounds(255,275,380,135); //LEFT,TOP,WIDTH,HEIGHT
        gameOverBg.setBorder(BorderFactory.createEmptyBorder());
        gameOverBg.setVisible(true);
        
        game2OverTxt1 = new JLabel("<html><center><font color=#FFFFFF size=7>"
                + "Game Over!");
        Font font2 = game2OverTxt1.getFont().deriveFont(Font.BOLD);
        game2OverTxt1.setFont(font2);
        game2OverTxt1.setBounds(340,295,360,60); //LEFT,TOP,WIDTH,HEIGHT
        game2OverTxt1.setBorder(BorderFactory.createEmptyBorder());
        game2OverTxt1.setVisible(true);
        
        game2OverTxt2 = new JLabel("<html><center><font color=#FFFFFF size=5>"
                + "You have scored " + player.game2Score + " points!");
        game2OverTxt2.setFont(font2);
        game2OverTxt2.setBounds(325,325,360,60); //LEFT,TOP,WIDTH,HEIGHT
        game2OverTxt2.setBorder(BorderFactory.createEmptyBorder());
        game2OverTxt2.setVisible(true);
        
        add(game2OverTxt1);
        add(game2OverTxt2);
        add(gameOverBg);
    }

    // SETUP INTRODUCTION OVERLAY AND GAME START BUTTON  
    public void setupIntro2Overlay(){
        intro2OverlayBg = new JLabel(intro2OverlayBgImg);
        intro2OverlayBg.setBounds(135,225,700,236);
        intro2OverlayBg.setBorder(BorderFactory.createEmptyBorder());
        intro2OverlayBg.setVisible(true);
        
        intro2OverlayTxt1 = new JLabel("<html><center><font color=#FFFF00 size=5>"
                + "Welcome to the Penn State University Park Campus!");
        Font font = intro2OverlayTxt1.getFont().deriveFont(Font.BOLD);
        intro2OverlayTxt1.setFont(font);
        intro2OverlayTxt1.setBounds(245,210,680,100); //LEFT,TOP,WIDTH,HEIGHT
        intro2OverlayTxt1.setBorder(BorderFactory.createEmptyBorder());
        intro2OverlayTxt1.setVisible(true);
        
        intro2OverlayTxt2 = new JLabel("<html><center><font color=#FFFFFF size=5>"
                + "Get ready for the most EPIC game of Rock, Paper, Scissors!  The first player to win 4 matches wins the game!  Earn extra points for consecutive wins!");
        intro2OverlayTxt2.setBounds(146,225,680,200); //LEFT,TOP,WIDTH,HEIGHT
        intro2OverlayTxt2.setBorder(BorderFactory.createEmptyBorder());
        intro2OverlayTxt2.setVisible(true);
   
        startGame2 = new JButton("Click Here to Start the Game!");
        startGame2.setBounds(340,385,250,40); //LEFT,TOP,WIDTH,HEIGHT
        startGame2.setBorder(BorderFactory.createEmptyBorder());
        startGame2.setVisible(true);
        
        add(startGame2);
        add(intro2OverlayTxt1);
        add(intro2OverlayTxt2);
        add(intro2OverlayBg);
        add(enemyPlay);
        startGame2.addMouseListener(this);
    }
    
}