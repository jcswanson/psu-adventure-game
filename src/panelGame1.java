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

public class panelGame1 extends JPanel implements MouseListener, ActionListener {

    panelGameMap p07PanelGameMap;
    objPlayer player;
    
    Timer   game1Timer,game1EnemyChoosing,game1NextMatch,game1DrawMatch,
            game1OverTimer,game1PointTimer;
    
    boolean game1IsEnemyMove;
    
    JButton startGame1;
    
    int     game1TimerDelay,game1EnemyChoosingDelay,game1NextMatchDelay,
            game1DrawMatchDelay,game1OverTimerDelay,game1PointTimerDelay,
            game1VictoryType,games1Won,games1Lost,game1SlotsFree,
            game1WonInARow;
    
    String  game1Slot1,game1Slot2,game1Slot3,game1Slot4,game1Slot5,game1Slot6,
            game1Slot7,game1Slot8,game1Slot9,game1MatchWinner,game1Winner;
    
    ImageIcon game1BackgroundPSU = new ImageIcon("images/game1BackgroundPsu.jpg"),
              game1BackgroundWU = new ImageIcon("images/game1BackgroundWu.jpg"),
              game1Background8BIT = new ImageIcon("images/game1Background8Bit.jpg"),
              game1BackgroundDiaper = new ImageIcon("images/game1BackgroundDiaper.jpg"),
              charMap1PortraitDaddy = new ImageIcon("images/iconDaddy.png"),
              charMap1PortraitMommy = new ImageIcon("images/iconMommy.png"),
              charMap1PortraitBaby = new ImageIcon("images/iconBaby.png");
    
    Image   game1PsuBg = game1BackgroundPSU.getImage(),
            game1WuBg = game1BackgroundWU.getImage(),
            game18BitBg = game1Background8BIT.getImage(),
            game1DiaperBg = game1BackgroundDiaper.getImage(),
            game1CharDaddy = charMap1PortraitDaddy.getImage(),
            game1CharMommy = charMap1PortraitMommy.getImage(),
            game1CharBaby = charMap1PortraitBaby.getImage();
    
    Icon    game1BoardX = new ImageIcon("images/game1X.png"),
            game1BoardO = new ImageIcon("images/game1O.png"),
            game1BoardE = new ImageIcon("images/game1Empty.gif"),
            game1BoardH = new ImageIcon("images/game1Highlight.gif"),
            game1MatchW = new ImageIcon("images/game1WonMatch.png"),
            game1MatchL = new ImageIcon("images/game1LostMatch.png"),
            game1MatchT = new ImageIcon("images/game1TiedMatch.png"),
            game1HorVic = new ImageIcon("images/game1HorizontalVictory.png"),
            game1VerVic = new ImageIcon("images/game1VerticalVictory.png"),
            game1Dia1Vic = new ImageIcon("images/game1Diag1Victory.png"),
            game1Dia2Vic = new ImageIcon("images/game1Diag2Victory.png"),
            game1OverBgImg = new ImageIcon("images/gameOverlay3.png"),
            intro1OverlayBgImg = new ImageIcon("images/gameOverlay1.png");

    JLabel  s1ot1empty,s1ot2empty,s1ot3empty,s1ot4empty,s1ot5empty,s1ot6empty,
            s1ot7empty,s1ot8empty,s1ot9empty,s1ot1X,s1ot2X,s1ot3X,s1ot4X,s1ot5X,
            s1ot6X,s1ot7X,s1ot8X,s1ot9X,s1ot1O,s1ot2O,s1ot3O,s1ot4O,s1ot5O,
            s1ot6O,s1ot7O,s1ot8O,s1ot9O,game1Slot1highlight,game1Slot2highlight,
            game1Slot3highlight,game1Slot4highlight,game1Slot5highlight,
            game1Slot6highlight,game1Slot7highlight,game1Slot8highlight,
            game1Slot9highlight,game1Ximg,game1Oimg,game1VictoryType1,game1VictoryType2,
            game1VictoryType3,game1VictoryType4,game1VictoryType5,game1VictoryType6,
            game1VictoryType7,game1VictoryType8,game1MatchWin,game1MatchLost,game1MatchTied,
            game1OverBg,game1OverTxt1,game1OverTxt2,plus200points,plus600points,
            plus3000points,bonus6000points,bonus30000points,intro1OverlayBg,
            intro1OverlayTxt1,intro1OverlayTxt2;
    
    public panelGame1(objPlayer player)
    {
        super();
        this.player = player;
        setLayout(null);

        game1InitializeMechanics();     //STARTS THE GAME UP
    }
    
    //BUILD INITIAL GAME MECHANICS
    public void game1InitializeMechanics(){
        //SETUP AND START GAME TIMER
        game1TimerDelay = 1000;
        game1Timer = new Timer(game1TimerDelay, this);
        game1Timer.start();
        
        //SETUP OTHER GAME TIMERS
        game1EnemyChoosing = new Timer(game1TimerDelay, this);
        game1NextMatch = new Timer(game1TimerDelay, this);
        game1DrawMatch = new Timer(game1TimerDelay, this);
        game1OverTimer = new Timer(game1TimerDelay, this);
        game1PointTimer = new Timer(game1TimerDelay, this);
        game1OverTimerDelay = 9;
        game1PointTimerDelay = 1;
        
        //ZERO GAMES HAVE BEEN WON IN A ROW
        game1WonInARow = 0;
        
        //SETUP ALL SLOTS ON GAME BOARD
        s1ot1empty = new JLabel(game1BoardE);
        s1ot2empty = new JLabel(game1BoardE);
        s1ot3empty = new JLabel(game1BoardE);
        s1ot4empty = new JLabel(game1BoardE);
        s1ot5empty = new JLabel(game1BoardE);
        s1ot6empty = new JLabel(game1BoardE);
        s1ot7empty = new JLabel(game1BoardE);
        s1ot8empty = new JLabel(game1BoardE);
        s1ot9empty = new JLabel(game1BoardE);
        game1Slot1highlight = new JLabel(game1BoardH);
        game1Slot2highlight = new JLabel(game1BoardH);
        game1Slot3highlight = new JLabel(game1BoardH);
        game1Slot4highlight = new JLabel(game1BoardH);
        game1Slot5highlight = new JLabel(game1BoardH);
        game1Slot6highlight = new JLabel(game1BoardH);
        game1Slot7highlight = new JLabel(game1BoardH);
        game1Slot8highlight = new JLabel(game1BoardH);
        game1Slot9highlight = new JLabel(game1BoardH);
        s1ot1X = new JLabel(game1BoardX);
        s1ot2X = new JLabel(game1BoardX);
        s1ot3X = new JLabel(game1BoardX);
        s1ot4X = new JLabel(game1BoardX);
        s1ot5X = new JLabel(game1BoardX);
        s1ot6X = new JLabel(game1BoardX);
        s1ot7X = new JLabel(game1BoardX);
        s1ot8X = new JLabel(game1BoardX);
        s1ot9X = new JLabel(game1BoardX);
        s1ot1O = new JLabel(game1BoardO);
        s1ot2O = new JLabel(game1BoardO);
        s1ot3O = new JLabel(game1BoardO);
        s1ot4O = new JLabel(game1BoardO);
        s1ot5O = new JLabel(game1BoardO);
        s1ot6O = new JLabel(game1BoardO);
        s1ot7O = new JLabel(game1BoardO);
        s1ot8O = new JLabel(game1BoardO);
        s1ot9O = new JLabel(game1BoardO);
        game1MatchWin = new JLabel(game1MatchW);
        game1MatchLost = new JLabel(game1MatchL);
        game1MatchTied = new JLabel(game1MatchT);
        game1VictoryType1 = new JLabel(game1HorVic);
        game1VictoryType2 = new JLabel(game1HorVic);
        game1VictoryType3 = new JLabel(game1HorVic);
        game1VictoryType4 = new JLabel(game1VerVic);
        game1VictoryType5 = new JLabel(game1VerVic);
        game1VictoryType6 = new JLabel(game1VerVic);
        game1VictoryType7 = new JLabel(game1Dia2Vic);
        game1VictoryType8 = new JLabel(game1Dia1Vic);
        game1OverBg = new JLabel(game1OverBgImg);
        game1OverTxt1 = new JLabel("<html><center><font color=#FFFFFF size=7>"
                + "Game Over!");
        game1OverTxt2 = new JLabel("<html><center><font color=#FFFFFF size=5>"
                + "You have scored " + player.game1Score + " points!");
        s1ot1empty.setBounds(315,212,80,80);
        s1ot2empty.setBounds(480,212,80,80);
        s1ot3empty.setBounds(650,212,80,80);
        s1ot4empty.setBounds(315,375,80,80);
        s1ot5empty.setBounds(480,375,80,80);
        s1ot6empty.setBounds(650,375,80,80);
        s1ot7empty.setBounds(315,540,80,80);
        s1ot8empty.setBounds(480,540,80,80);
        s1ot9empty.setBounds(650,540,80,80);
        game1Slot1highlight.setBounds(315,212,80,80);
        game1Slot2highlight.setBounds(480,212,80,80);
        game1Slot3highlight.setBounds(650,212,80,80);
        game1Slot4highlight.setBounds(315,375,80,80);
        game1Slot5highlight.setBounds(480,375,80,80);
        game1Slot6highlight.setBounds(650,375,80,80);
        game1Slot7highlight.setBounds(315,540,80,80);
        game1Slot8highlight.setBounds(480,540,80,80);
        game1Slot9highlight.setBounds(650,540,80,80);
        s1ot1X.setBounds(310,195,88,116);
        s1ot2X.setBounds(475,195,88,116);
        s1ot3X.setBounds(640,195,88,116);
        s1ot4X.setBounds(310,358,88,116);
        s1ot5X.setBounds(475,358,88,116);
        s1ot6X.setBounds(640,358,88,116);
        s1ot7X.setBounds(310,525,88,116);
        s1ot8X.setBounds(475,525,88,116);
        s1ot9X.setBounds(640,525,88,116);
        s1ot1O.setBounds(310,195,98,119);
        s1ot2O.setBounds(475,195,98,119);
        s1ot3O.setBounds(640,195,98,119);
        s1ot4O.setBounds(310,358,98,119);
        s1ot5O.setBounds(475,358,98,119);
        s1ot6O.setBounds(640,358,98,119);
        s1ot7O.setBounds(310,525,98,119);
        s1ot8O.setBounds(475,525,98,119);
        s1ot9O.setBounds(640,525,98,119);
        game1MatchWin.setBounds(16,90,979,183);
        game1MatchLost.setBounds(17,90,977,183);
        game1MatchTied.setBounds(222,90,592,183);
        game1VictoryType1.setBounds(274,168,491,160);
        game1VictoryType2.setBounds(274,334,491,160);
        game1VictoryType3.setBounds(274,500,491,160);
        game1VictoryType4.setBounds(275,168,159,491);
        game1VictoryType5.setBounds(440,168,159,491);
        game1VictoryType6.setBounds(606,168,159,491);
        game1VictoryType7.setBounds(274,168,491,491);
        game1VictoryType8.setBounds(275,168,491,491);
        game1OverBg.setBounds(325,275,380,135); //LEFT,TOP,WIDTH,HEIGHT
        game1OverTxt1.setBounds(410,295,360,60); //LEFT,TOP,WIDTH,HEIGHT
        game1OverTxt2.setBounds(395,325,360,60); //LEFT,TOP,WIDTH,HEIGHT
        game1OverBg.setBorder(BorderFactory.createEmptyBorder());
        game1OverTxt1.setBorder(BorderFactory.createEmptyBorder());
        game1OverTxt2.setBorder(BorderFactory.createEmptyBorder());
        s1ot1empty.setVisible(false);
        s1ot2empty.setVisible(false);
        s1ot3empty.setVisible(false);
        s1ot4empty.setVisible(false);
        s1ot5empty.setVisible(false);
        s1ot6empty.setVisible(false);
        s1ot7empty.setVisible(false);
        s1ot8empty.setVisible(false);
        s1ot9empty.setVisible(false);
        game1Slot1highlight.setVisible(false);
        game1Slot2highlight.setVisible(false);
        game1Slot3highlight.setVisible(false);
        game1Slot4highlight.setVisible(false);
        game1Slot5highlight.setVisible(false);
        game1Slot6highlight.setVisible(false);
        game1Slot7highlight.setVisible(false);
        game1Slot8highlight.setVisible(false);
        game1Slot9highlight.setVisible(false);
        s1ot1X.setVisible(false);
        s1ot2X.setVisible(false);
        s1ot3X.setVisible(false);
        s1ot4X.setVisible(false);
        s1ot5X.setVisible(false);
        s1ot6X.setVisible(false);
        s1ot7X.setVisible(false);
        s1ot8X.setVisible(false);
        s1ot9X.setVisible(false);
        s1ot1O.setVisible(false);
        s1ot2O.setVisible(false);
        s1ot3O.setVisible(false);
        s1ot4O.setVisible(false);
        s1ot5O.setVisible(false);
        s1ot6O.setVisible(false);
        s1ot7O.setVisible(false);
        s1ot8O.setVisible(false);
        s1ot9O.setVisible(false);
        game1MatchWin.setVisible(false);
        game1MatchLost.setVisible(false);
        game1MatchTied.setVisible(false);
        game1VictoryType1.setVisible(false);
        game1VictoryType2.setVisible(false);
        game1VictoryType3.setVisible(false);
        game1VictoryType4.setVisible(false);
        game1VictoryType5.setVisible(false);
        game1VictoryType6.setVisible(false);
        game1VictoryType7.setVisible(false);
        game1VictoryType8.setVisible(false);
        game1OverBg.setVisible(false);    
        game1OverTxt1.setVisible(false);
        game1OverTxt2.setVisible(false);
        Font font2 = game1OverTxt1.getFont().deriveFont(Font.BOLD);
        game1OverTxt1.setFont(font2);
        game1OverTxt2.setFont(font2);
        add(game1OverTxt1);
        add(game1OverTxt2);
        add(game1OverBg);
        add(game1MatchWin);
        add(game1MatchLost);
        add(game1MatchTied);
        add(s1ot1X);
        add(s1ot2X);
        add(s1ot3X);
        add(s1ot4X);
        add(s1ot5X);
        add(s1ot6X);
        add(s1ot7X);
        add(s1ot8X);
        add(s1ot9X);
        add(s1ot1O);
        add(s1ot2O);
        add(s1ot3O);
        add(s1ot4O);
        add(s1ot5O);
        add(s1ot6O);
        add(s1ot7O);
        add(s1ot8O);
        add(s1ot9O);
        add(game1VictoryType1);
        add(game1VictoryType2);
        add(game1VictoryType3);
        add(game1VictoryType4);
        add(game1VictoryType5);
        add(game1VictoryType6);
        add(game1VictoryType7);
        add(game1VictoryType8);
        s1ot1empty.addMouseListener(this);
        s1ot2empty.addMouseListener(this);
        s1ot3empty.addMouseListener(this);
        s1ot4empty.addMouseListener(this);
        s1ot5empty.addMouseListener(this);
        s1ot6empty.addMouseListener(this);
        s1ot7empty.addMouseListener(this);
        s1ot8empty.addMouseListener(this);
        s1ot9empty.addMouseListener(this);
        game1Slot1highlight.addMouseListener(this);
        game1Slot2highlight.addMouseListener(this);
        game1Slot3highlight.addMouseListener(this);
        game1Slot4highlight.addMouseListener(this);
        game1Slot5highlight.addMouseListener(this);
        game1Slot6highlight.addMouseListener(this);
        game1Slot7highlight.addMouseListener(this);
        game1Slot8highlight.addMouseListener(this);
        game1Slot9highlight.addMouseListener(this);
        
        //POINTS
        plus200points = new JLabel("<html><center><font color=#FFFF00 size=5>" + "+200" );
        plus200points.setBounds(884,36,100,40);
        plus200points.setVisible(true);
        plus200points.setBorder(BorderFactory.createEmptyBorder());
        plus600points = new JLabel("<html><center><font color=#FFFF00 size=5>" + "+600" );
        plus600points.setBounds(884,36,100,40);
        plus600points.setVisible(true);
        plus600points.setBorder(BorderFactory.createEmptyBorder());
        plus3000points = new JLabel("<html><center><font color=#FFFF00 size=5>" + "+3000" );
        plus3000points.setBounds(874,57,100,40);
        plus3000points.setVisible(true);
        plus3000points.setBorder(BorderFactory.createEmptyBorder());
           
        //BONUS POINTS
        bonus6000points = new JLabel("<html><center><font color=#FFFF00 size=5>" + "2 In a Row Bonus! +6000" );
        bonus6000points.setBounds(713,78,300,40);
        bonus6000points.setVisible(true);
        bonus6000points.setBorder(BorderFactory.createEmptyBorder());
        bonus30000points = new JLabel("<html><center><font color=#FFFF00 size=5>" + "FLAWLESS VICTORY! +30000" );
        bonus30000points.setBounds(668,78,300,40);
        bonus30000points.setVisible(true);
        bonus30000points.setBorder(BorderFactory.createEmptyBorder());
        
        game1IsEnemyMove = false; //SET GAME TO NOT BE ENEMY'S FIRST MOVE
        game1DisablePlayerChoosing(); //DISABLE CHOOSING SLOTS INITIALLY
        setupIntro1Overlay(); //LOAD GAME INTRO OVERLAY W/START BUTTON
    }
    
    //CREATE BLANK GAME BOARD
    public void game1NewGameBoard(){
	game1SlotsFree = 9;
        game1MatchWinner = "None";
        game1Slot1 = "N"; game1Slot2 = "N"; game1Slot3 = "N";
        game1Slot4 = "N"; game1Slot5 = "N"; game1Slot6 = "N";
        game1Slot7 = "N"; game1Slot8 = "N"; game1Slot9 = "N";
        s1ot1empty.setVisible(true);
        s1ot2empty.setVisible(true);
        s1ot3empty.setVisible(true);
        s1ot4empty.setVisible(true);
        s1ot5empty.setVisible(true);
        s1ot6empty.setVisible(true);
        s1ot7empty.setVisible(true);
        s1ot8empty.setVisible(true);
        s1ot9empty.setVisible(true);
        game1Slot1highlight.setVisible(false);
        game1Slot2highlight.setVisible(false);
        game1Slot3highlight.setVisible(false);
        game1Slot4highlight.setVisible(false);
        game1Slot5highlight.setVisible(false);
        game1Slot6highlight.setVisible(false);
        game1Slot7highlight.setVisible(false);
        game1Slot8highlight.setVisible(false);
        game1Slot9highlight.setVisible(false);
        s1ot1X.setVisible(false);
        s1ot2X.setVisible(false);
        s1ot3X.setVisible(false);
        s1ot4X.setVisible(false);
        s1ot5X.setVisible(false);
        s1ot6X.setVisible(false);
        s1ot7X.setVisible(false);
        s1ot8X.setVisible(false);
        s1ot9X.setVisible(false);
        s1ot1O.setVisible(false);
        s1ot2O.setVisible(false);
        s1ot3O.setVisible(false);
        s1ot4O.setVisible(false);
        s1ot5O.setVisible(false);
        s1ot6O.setVisible(false);
        s1ot7O.setVisible(false);
        s1ot8O.setVisible(false);
        s1ot9O.setVisible(false);
        game1VictoryType1.setVisible(false);
        game1VictoryType2.setVisible(false);
        game1VictoryType3.setVisible(false);
        game1VictoryType4.setVisible(false);
        game1VictoryType5.setVisible(false);
        game1VictoryType6.setVisible(false);
        game1VictoryType7.setVisible(false);
        game1VictoryType8.setVisible(false);
        add(game1Slot1highlight);
        add(game1Slot2highlight);
        add(game1Slot3highlight);
        add(game1Slot4highlight);
        add(game1Slot5highlight);
        add(game1Slot6highlight);
        add(game1Slot7highlight);
        add(game1Slot8highlight);
        add(game1Slot9highlight);
        add(s1ot1empty);
        add(s1ot2empty);
        add(s1ot3empty);
        add(s1ot4empty);
        add(s1ot5empty);
        add(s1ot6empty);
        add(s1ot7empty);
        add(s1ot8empty);
        add(s1ot9empty);
        
        //DETERMINE WHO GOES FIRST
        if (game1IsEnemyMove==true){game1EnemyMove();}
        else {game1EnablePlayerChoosing();}
    }
    
    //CHECK IF THERE IS A WINNER
    public void game1CheckIfWinner(){
        //HORIZONTAL VICTORY CONDITIONS
        if(game1Slot1 == "X" && game1Slot2 == "X" && game1Slot3 == "X"){game1Winner("player",1);}      //VICTORY TYPE 1
        else if(game1Slot4 == "X" && game1Slot5 == "X" && game1Slot6 == "X"){game1Winner("player",2);} //VICTORY TYPE 2
        else if(game1Slot7 == "X" && game1Slot8 == "X" && game1Slot9 == "X"){game1Winner("player",3);} //VICTORY TYPE 3
        else if(game1Slot1 == "O" && game1Slot2 == "O" && game1Slot3 == "O"){game1Winner("enemy",1);}  //VICTORY TYPE 1
        else if(game1Slot4 == "O" && game1Slot5 == "O" && game1Slot6 == "O"){game1Winner("enemy",2);}  //VICTORY TYPE 2
        else if(game1Slot7 == "O" && game1Slot8 == "O" && game1Slot9 == "O"){game1Winner("enemy",3);}  //VICTORY TYPE 3
        //VERTICAL VICTORY CONDITIONS
        else if(game1Slot1 == "X" && game1Slot4 == "X" && game1Slot7 == "X"){game1Winner("player",4);} //VICTORY TYPE 4
        else if(game1Slot2 == "X" && game1Slot5 == "X" && game1Slot8 == "X"){game1Winner("player",5);} //VICTORY TYPE 5
        else if(game1Slot3 == "X" && game1Slot6 == "X" && game1Slot9 == "X"){game1Winner("player",6);} //VICTORY TYPE 6
        else if(game1Slot1 == "O" && game1Slot4 == "O" && game1Slot7 == "O"){game1Winner("enemy",4);}  //VICTORY TYPE 4
        else if(game1Slot2 == "O" && game1Slot5 == "O" && game1Slot8 == "O"){game1Winner("enemy",5);}  //VICTORY TYPE 5
        else if(game1Slot3 == "O" && game1Slot6 == "O" && game1Slot9 == "O"){game1Winner("enemy",6);}  //VICTORY TYPE 6
        //DIAGONAL VICTORY CONDITIONS
        else if(game1Slot1 == "X" && game1Slot5 == "X" && game1Slot9 == "X"){game1Winner("player",7);} //VICTORY TYPE 7
        else if(game1Slot3 == "X" && game1Slot5 == "X" && game1Slot7 == "X"){game1Winner("player",8);} //VICTORY TYPE 8
        else if(game1Slot1 == "O" && game1Slot5 == "O" && game1Slot9 == "O"){game1Winner("enemy",7);}  //VICTORY TYPE 7
        else if(game1Slot3 == "O" && game1Slot5 == "O" && game1Slot7 == "O"){game1Winner("enemy",8);}  //VICTORY TYPE 8
        else {game1CheckIfTie();}
    }

    //CHECK IF THERE IS A TIE
    public void game1CheckIfTie(){
        if(game1SlotsFree == 0 && game1Winner != "player" && game1Winner != "enemy"){
            game1MatchTied.setVisible(true);
            player.game1Score = player.game1Score + 600;
            add(plus600points);
            game1PointTimer.start();
            game1DrawMatchTimer();
        }
        else if (game1IsEnemyMove==true){
            game1DisablePlayerChoosing();
            game1EnemyMoveTimer();
        }
        else {game1EnablePlayerChoosing();}
    }
    
    //CREATE SHORT DELAY AFTER MATCH RESULTS IN A DRAW
    public void game1DrawMatchTimer(){
        game1DrawMatchDelay = 5;
        game1DrawMatch.start();
    }
    
    //ACTIONS AFTER A MATCH IS WON
    public void game1Winner(String a, int b){
        game1MatchWinner = a;
        game1VictoryType = b;
        game1DisablePlayerChoosing();
        if(game1MatchWinner=="player"){
            game1MatchWin.setVisible(true);
            games1Won = games1Won + 1;
            player.game1Score = player.game1Score + 3000;
            add(plus3000points);
            if(game1WonInARow==1){
                player.game1Score = player.game1Score + 6000;
                add(bonus6000points);
            }
            if(game1WonInARow==2){
                player.game1Score = player.game1Score + 30000;
                add(bonus30000points);
            }
            game1PointTimer.start();
            game1WonInARow = game1WonInARow + 1;
        }
        else if(game1MatchWinner=="enemy"){
            game1MatchLost.setVisible(true);
            games1Lost = games1Lost + 1;
            game1WonInARow = 0;
        }
        if(game1VictoryType==1){
            game1VictoryType1.setVisible(true);
        }
        else if(game1VictoryType==2){
            game1VictoryType2.setVisible(true);
        }
        else if(game1VictoryType==3){
            game1VictoryType3.setVisible(true);
        }
        else if(game1VictoryType==4){
            game1VictoryType4.setVisible(true);
        }
        else if(game1VictoryType==5){
            game1VictoryType5.setVisible(true);
        }
        else if(game1VictoryType==6){
            game1VictoryType6.setVisible(true);
        }
        else if(game1VictoryType==7){
            game1VictoryType7.setVisible(true);
        }
        else if(game1VictoryType==8){
            game1VictoryType8.setVisible(true);
        }

        game1CheckIfGameOver();
    }

    //CREATE SHORT DELAY IN NEXT MATCH STARTING
    public void game1NextMatchTimer(){
        game1NextMatchDelay = 5;
        game1NextMatch.start();
    }
    
    //DISABLE PLAYER FROM CHOOSING
    public void game1DisablePlayerChoosing(){
        s1ot1empty.setVisible(false);
        s1ot2empty.setVisible(false);
        s1ot3empty.setVisible(false);
        s1ot4empty.setVisible(false);
        s1ot5empty.setVisible(false);
        s1ot6empty.setVisible(false);
        s1ot7empty.setVisible(false);
        s1ot8empty.setVisible(false);
        s1ot9empty.setVisible(false);
    }
    
    //RE-ENABLE CHOOSING FOR PLAYER
    public void game1EnablePlayerChoosing(){
        s1ot1empty.setVisible(true);
        s1ot2empty.setVisible(true);
        s1ot3empty.setVisible(true);
        s1ot4empty.setVisible(true);
        s1ot5empty.setVisible(true);
        s1ot6empty.setVisible(true);
        s1ot7empty.setVisible(true);
        s1ot8empty.setVisible(true);
        s1ot9empty.setVisible(true);
    }
    
    //CREATE SHORT DELAY IN ENEMY DECISION MAKING
    public void game1EnemyMoveTimer(){
        game1EnemyChoosingDelay = 2;
        game1EnemyChoosing.start();
    }
    
    //ENEMY MAKES A MOVE (ENEMY PLAYER AI)
    public void game1EnemyMove(){
        game1DisablePlayerChoosing();
        if (game1Slot5 == "N") {
            s1ot5O.setVisible(true);
            remove(game1Slot5highlight);
            remove(s1ot5empty);
            game1Slot5 = "O";
            game1SlotsFree = game1SlotsFree - 1;
            game1IsEnemyMove = false;
            game1CheckIfWinner();
        }
        else if (game1Slot1 == "N") {
            s1ot1O.setVisible(true);
            remove(game1Slot1highlight);
            remove(s1ot1empty);
            game1Slot1 = "O";
            game1SlotsFree = game1SlotsFree - 1;
            game1IsEnemyMove = false;
            game1CheckIfWinner();
        }
        else if (game1Slot9 == "N") {
            s1ot9O.setVisible(true);
            remove(game1Slot9highlight);
            remove(s1ot9empty);
            game1Slot9 = "O";
            game1SlotsFree = game1SlotsFree - 1;
            game1IsEnemyMove = false;
            game1CheckIfWinner();
        }
        else if (game1Slot3 == "N") {
            s1ot3O.setVisible(true);
            remove(game1Slot3highlight);
            remove(s1ot3empty);
            game1Slot3 = "O";
            game1SlotsFree = game1SlotsFree - 1;
            game1IsEnemyMove = false;
            game1CheckIfWinner();
        }
        else if (game1Slot7 == "N") {
            s1ot7O.setVisible(true);
            remove(game1Slot7highlight);
            remove(s1ot7empty);
            game1Slot7 = "O";
            game1SlotsFree = game1SlotsFree - 1;
            game1IsEnemyMove = false;
            game1CheckIfWinner();
        }
        else if (game1Slot2 == "N") {
            s1ot2O.setVisible(true);
            remove(game1Slot2highlight);
            remove(s1ot2empty);
            game1Slot2 = "O";
            game1SlotsFree = game1SlotsFree - 1;
            game1IsEnemyMove = false;
            game1CheckIfWinner();
        }
        else if (game1Slot4 == "N") {
            s1ot4O.setVisible(true);
            remove(game1Slot4highlight);
            remove(s1ot4empty);
            game1Slot4 = "O";
            game1SlotsFree = game1SlotsFree - 1;
            game1IsEnemyMove = false;
            game1CheckIfWinner();
        }
        else if (game1Slot6 == "N") {
            s1ot6O.setVisible(true);
            remove(game1Slot6highlight);
            remove(s1ot6empty);
            game1Slot6 = "O";
            game1SlotsFree = game1SlotsFree - 1;
            game1IsEnemyMove = false;
            game1CheckIfWinner();
        }
        else if (game1Slot8 == "N") {
            s1ot8O.setVisible(true);
            remove(game1Slot8highlight);
            remove(s1ot8empty);
            game1Slot8 = "O";
            game1SlotsFree = game1SlotsFree - 1;
            game1IsEnemyMove = false;
            game1CheckIfWinner();
        }
        else {game1CheckIfWinner();}
    }

    //CHECK IF GAME IS OVER
    public void game1CheckIfGameOver(){
        if (games1Won==5 || games1Lost == 5){
            game1OverTimer.start();
        }
        else {game1NextMatchTimer();}  //START 5 SECOND TIMER BEFORE NEW BOARD CREATED
    }
    
    //BUILD THE GAME OVER SUMMARY OVERLAY
    public void gameOverOverlay(){
        remove(game1OverTxt2);
        game1OverTxt2 = new JLabel("<html><center><font color=#FFFFFF size=5>"
                + "You have scored " + player.game1Score + " points!");
        add(game1OverTxt2);
        game1OverBg.setVisible(true);
        game1OverTxt1.setVisible(true);
        game1OverTxt2.setVisible(true);
    }

    //SETUP INTRODUCTION OVERLAY AND GAME START BUTTON  
    public void setupIntro1Overlay(){
        intro1OverlayBg = new JLabel(intro1OverlayBgImg);
        intro1OverlayBg.setBounds(135,225,700,236);
        intro1OverlayBg.setBorder(BorderFactory.createEmptyBorder());
        intro1OverlayBg.setVisible(true);
        
        intro1OverlayTxt1 = new JLabel("<html><center><font color=#FFFF00 size=5>"
            + "Welcome to the Penn State University Scranton Campus!");
        Font font = intro1OverlayTxt1.getFont().deriveFont(Font.BOLD);
        intro1OverlayTxt1.setFont(font);
        intro1OverlayTxt1.setBounds(240,210,680,100); //LEFT,TOP,WIDTH,HEIGHT
        intro1OverlayTxt1.setBorder(BorderFactory.createEmptyBorder());
        intro1OverlayTxt1.setVisible(true);
        
        intro1OverlayTxt2 = new JLabel("<html><center><font color=#FFFFFF size=5>"
            + "Are you ready for some Tic-Tac-Toe?  The first player to win 5 matches wins the game!  Earn extra points for consecutive wins!");
        intro1OverlayTxt2.setBounds(146,225,680,200); //LEFT,TOP,WIDTH,HEIGHT
        intro1OverlayTxt2.setBorder(BorderFactory.createEmptyBorder());
        intro1OverlayTxt2.setVisible(true);
   
        startGame1 = new JButton("Click Here to Start the Game!");
        startGame1.setBounds(340,385,250,40); //LEFT,TOP,WIDTH,HEIGHT
        startGame1.setBorder(BorderFactory.createEmptyBorder());
        startGame1.setVisible(true);
        
        add(startGame1);
        add(intro1OverlayTxt1);
        add(intro1OverlayTxt2);
        add(intro1OverlayBg);
        startGame1.addMouseListener(this);
    }

    //==========================================================================
    // MOUSE LISTENER
    public void mouseClicked(MouseEvent e){  
        if (e.getComponent()==game1Slot1highlight){
            s1ot1X.setVisible(true);
            remove(game1Slot1highlight);
            remove(s1ot1empty);
            if(player.character!=2){player.game1Score = player.game1Score + 200;}
            if(player.character!=2){add(plus200points);}
            game1PointTimer.start();
            game1Slot1 = "X";
            game1SlotsFree = game1SlotsFree - 1;
            game1IsEnemyMove = true;
            game1CheckIfWinner();
        }
        if (e.getComponent()==game1Slot2highlight){
            s1ot2X.setVisible(true);
            remove(game1Slot2highlight);
            remove(s1ot2empty);
            if(player.character!=2){player.game1Score = player.game1Score + 200;}
            if(player.character!=2){add(plus200points);}
            game1PointTimer.start();
            game1Slot2 = "X";
            game1SlotsFree = game1SlotsFree - 1;
            game1IsEnemyMove = true;
            game1CheckIfWinner();
        }
        if (e.getComponent()==game1Slot3highlight){
            s1ot3X.setVisible(true);
            remove(game1Slot3highlight);
            remove(s1ot3empty);
            if(player.character!=2){player.game1Score = player.game1Score + 200;}
            if(player.character!=2){add(plus200points);}
            game1PointTimer.start();
            game1Slot3 = "X";
            game1SlotsFree = game1SlotsFree - 1;
            game1IsEnemyMove = true;
            game1CheckIfWinner();
        }
        if (e.getComponent()==game1Slot4highlight){
            s1ot4X.setVisible(true);
            remove(game1Slot4highlight);
            remove(s1ot4empty);
            if(player.character!=2){player.game1Score = player.game1Score + 200;}
            if(player.character!=2){add(plus200points);}
            game1PointTimer.start();
            game1Slot4 = "X";
            game1SlotsFree = game1SlotsFree - 1;
            game1IsEnemyMove = true;
            game1CheckIfWinner();
        }
        if (e.getComponent()==game1Slot5highlight){
            s1ot5X.setVisible(true);
            remove(game1Slot5highlight);
            remove(s1ot5empty);
            if(player.character!=2){player.game1Score = player.game1Score + 200;}
            if(player.character!=2){add(plus200points);}
            game1PointTimer.start();
            game1Slot5 = "X";
            game1SlotsFree = game1SlotsFree - 1;
            game1IsEnemyMove = true;
            game1CheckIfWinner();
        }
        if (e.getComponent()==game1Slot6highlight){
            s1ot6X.setVisible(true);
            remove(game1Slot6highlight);
            remove(s1ot6empty);
            if(player.character!=2){player.game1Score = player.game1Score + 200;}
            if(player.character!=2){add(plus200points);}
            game1PointTimer.start();
            game1Slot6 = "X";
            game1SlotsFree = game1SlotsFree - 1;
            game1IsEnemyMove = true;
            game1CheckIfWinner();
        }
        if (e.getComponent()==game1Slot7highlight){
            s1ot7X.setVisible(true);
            remove(game1Slot7highlight);
            remove(s1ot7empty);
            if(player.character!=2){player.game1Score = player.game1Score + 200;}
            if(player.character!=2){add(plus200points);}
            game1PointTimer.start();
            game1Slot7 = "X";
            game1SlotsFree = game1SlotsFree - 1;
            game1IsEnemyMove = true;
            game1CheckIfWinner();
        }
        if (e.getComponent()==game1Slot8highlight){
            s1ot8X.setVisible(true);
            remove(game1Slot8highlight);
            remove(s1ot8empty);
            if(player.character!=2){player.game1Score = player.game1Score + 200;}
            if(player.character!=2){add(plus200points);}
            game1PointTimer.start();
            game1Slot8 = "X";
            game1SlotsFree = game1SlotsFree - 1;
            game1IsEnemyMove = true;
            game1CheckIfWinner();
        }
        if (e.getComponent()==game1Slot9highlight){
            s1ot9X.setVisible(true);
            remove(game1Slot9highlight);
            remove(s1ot9empty);
            if(player.character!=2){player.game1Score = player.game1Score + 200;}
            if(player.character!=2){add(plus200points);}
            game1PointTimer.start();
            game1Slot9 = "X";
            game1SlotsFree = game1SlotsFree - 1;
            game1IsEnemyMove = true;
            game1CheckIfWinner();
        }
        if (e.getComponent()==startGame1){
            remove(intro1OverlayBg);
            remove(intro1OverlayTxt1);
            remove(intro1OverlayTxt2);
            remove(startGame1);
            game1NewGameBoard(); //BEGIN FIRST GAME MATCH
        }
    }
    public void mouseEntered(MouseEvent e) {
        if (e.getComponent()==s1ot1empty){
            game1Slot1highlight.setVisible(true);
            game1Slot1highlight.setCursor(new Cursor(Cursor.HAND_CURSOR));
            s1ot1empty.setVisible(false);
            s1ot1empty.setCursor(new Cursor(Cursor.HAND_CURSOR));}
        if (e.getComponent()==s1ot2empty){
            game1Slot2highlight.setVisible(true);
            game1Slot2highlight.setCursor(new Cursor(Cursor.HAND_CURSOR));
            s1ot2empty.setVisible(false);
            s1ot2empty.setCursor(new Cursor(Cursor.HAND_CURSOR));}
        if (e.getComponent()==s1ot3empty){
            game1Slot3highlight.setVisible(true);
            game1Slot3highlight.setCursor(new Cursor(Cursor.HAND_CURSOR));
            s1ot3empty.setVisible(false);
            s1ot3empty.setCursor(new Cursor(Cursor.HAND_CURSOR));}
        if (e.getComponent()==s1ot4empty){
            game1Slot4highlight.setVisible(true);
            game1Slot4highlight.setCursor(new Cursor(Cursor.HAND_CURSOR));
            s1ot4empty.setVisible(false);
            s1ot4empty.setCursor(new Cursor(Cursor.HAND_CURSOR));}
        if (e.getComponent()==s1ot5empty){
            game1Slot5highlight.setVisible(true);
            game1Slot5highlight.setCursor(new Cursor(Cursor.HAND_CURSOR));
            s1ot5empty.setVisible(false);
            s1ot5empty.setCursor(new Cursor(Cursor.HAND_CURSOR));}
        if (e.getComponent()==s1ot6empty){
            game1Slot6highlight.setVisible(true);
            game1Slot6highlight.setCursor(new Cursor(Cursor.HAND_CURSOR));
            s1ot6empty.setVisible(false);
            s1ot6empty.setCursor(new Cursor(Cursor.HAND_CURSOR));}			
        if (e.getComponent()==s1ot7empty){
            game1Slot7highlight.setVisible(true);
            game1Slot7highlight.setCursor(new Cursor(Cursor.HAND_CURSOR));
            s1ot7empty.setVisible(false);
            s1ot7empty.setCursor(new Cursor(Cursor.HAND_CURSOR));}
        if (e.getComponent()==s1ot8empty){
            game1Slot8highlight.setVisible(true);
            game1Slot8highlight.setCursor(new Cursor(Cursor.HAND_CURSOR));
            s1ot8empty.setVisible(false);
            s1ot8empty.setCursor(new Cursor(Cursor.HAND_CURSOR));}
        if (e.getComponent()==s1ot9empty){
            game1Slot9highlight.setVisible(true);
            game1Slot9highlight.setCursor(new Cursor(Cursor.HAND_CURSOR));
            s1ot9empty.setVisible(false);
            s1ot9empty.setCursor(new Cursor(Cursor.HAND_CURSOR));}
    }
    public void mouseExited(MouseEvent e) { 
        if (e.getComponent()==game1Slot1highlight){
            s1ot1empty.setVisible(true);
            game1Slot1highlight.setVisible(false);}
        if (e.getComponent()==game1Slot2highlight){
            s1ot2empty.setVisible(true);
            game1Slot2highlight.setVisible(false);}
        if (e.getComponent()==game1Slot3highlight){
            s1ot3empty.setVisible(true);
            game1Slot3highlight.setVisible(false);}
		if (e.getComponent()==game1Slot4highlight){
            s1ot4empty.setVisible(true);
            game1Slot4highlight.setVisible(false);}
        if (e.getComponent()==game1Slot5highlight){
            s1ot5empty.setVisible(true);
            game1Slot5highlight.setVisible(false);}
        if (e.getComponent()==game1Slot6highlight){
            s1ot6empty.setVisible(true);
            game1Slot6highlight.setVisible(false);}
		if (e.getComponent()==game1Slot7highlight){
            s1ot7empty.setVisible(true);
            game1Slot7highlight.setVisible(false);}
        if (e.getComponent()==game1Slot8highlight){
            s1ot8empty.setVisible(true);
            game1Slot8highlight.setVisible(false);}
        if (e.getComponent()==game1Slot9highlight){
            s1ot9empty.setVisible(true);
            game1Slot9highlight.setVisible(false);}
    }
    public void mousePressed(MouseEvent e) { }
    public void mouseReleased(MouseEvent e) { } 
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        //DRAW GAME BACKGROUND IMAGE
        if(player.gameTheme == 1){g.drawImage(game1PsuBg, 0, 0, this);}
        else if(player.gameTheme == 2){g.drawImage(game1WuBg, 0, 0, this);}
        else if(player.gameTheme == 3){g.drawImage(game18BitBg, 0, 0, this);}
        else if(player.gameTheme == 4){g.drawImage(game1DiaperBg, 0, 0, this);}
        
        //DRAW TOP OVERLAY BACKGROUND
        g.setColor(new Color(0, 0, 0, 95)); //SET COLOR TO BLACK W/95% OPACITY
        g.fillRect(0, 0, 1024, 70); //LEFT, TOP, WIDTH, HEIGHT 

        //DRAW PLAYER CHARACTER ICON IN UPPER-LEFT CORNER OF OVERLAY
        if(player.character == 1){g.drawImage(game1CharDaddy,10,3, this);}
        else if(player.character == 2){g.drawImage(game1CharMommy,10,3, this);}
        else if(player.character == 3){g.drawImage(game1CharBaby,10,3, this);}        

        //DRAW TEXT
        Font f1 = new Font("Gothic", Font.BOLD, 20);
        Font f2 = new Font("Gothic", Font.BOLD, 11);
        Font f3 = new Font("Gothic", Font.BOLD, 14);
        g.setFont(f1);
        g.setColor(Color.WHITE);
        g.drawString("Penn State University Scranton Campus",90,44);
        g.drawString("Timer: ", 580, 44);
        g.drawString("Score: ", 740, 44);
        g.setFont(f2);
        g.drawString("W  |  L", 510, 36);
        g.drawString("|", 526, 48);
        
        //DRAW GAME TIME AND SCORE
        g.setFont(f1);
        g.setColor(Color.YELLOW);
        g.drawString(String.format( "%-10d", player.gameTime + player.game1Time ), 655, 44); //ALIGN LEFT
        g.drawString(String.format( "%010d", player.gameScore + player.game1Score ), 815, 44); //PADDING 10 DIGITS W/ZEROS
        g.setFont(f3);
        g.drawString(String.format( "%-10d", games1Won ), 511, 48);
        g.drawString(String.format( "%-10d", games1Lost ), 535, 48);
    }

    //==========================================================================
    // SET ACTION PERFORMED LISTENERS (GAME TIMERS AND BUTTONS)
    public void actionPerformed(ActionEvent event){
        Object obj = event.getSource();
        String choice = event.getActionCommand();
	
        //DO ON EACH GAME TIMER TICK
        if (obj == game1Timer){
            player.game1Time = player.game1Time + 1; //UPDATE GAME TIMER
            repaint();
        }
        
        //POINT REMOVER TIMER
        if (obj == game1PointTimer){
            game1PointTimerDelay = game1PointTimerDelay - 1; //UPDATE COUNTDOWN
            if (game1PointTimerDelay == 0){ 
                game1PointTimer.stop();
                game1PointTimerDelay = 1;
                remove(plus200points);
                remove(plus600points);
                remove(plus3000points);
                remove(bonus6000points);
                remove(bonus30000points);
            }
        }
   
        //ENEMY DECISION TIMER
        if (obj == game1EnemyChoosing){
            game1EnemyChoosingDelay = game1EnemyChoosingDelay - 1; //UPDATE COUNTDOWN
            if (game1EnemyChoosingDelay == 0){ 
                game1EnemyChoosing.stop();
                game1EnemyMove(); 
            }
        }
        
        //NEXT MATCH DELAY TIMER
        if (obj == game1NextMatch){
            game1NextMatchDelay = game1NextMatchDelay - 1; //UPDATE COUNTDOWN
            if (game1NextMatchDelay == 0){ 
                game1NextMatch.stop();
                game1MatchWin.setVisible(false);
                game1MatchLost.setVisible(false);
                game1NewGameBoard();
            }
        }
        
        //DRAW MATCH TIMER TIMER
        if (obj == game1DrawMatch){
            game1DrawMatchDelay = game1DrawMatchDelay - 1; //UPDATE COUNTDOWN
            if (game1DrawMatchDelay == 0){
                game1DrawMatch.stop();
                game1MatchTied.setVisible(false);
                game1NewGameBoard();
            }
        }
        
        //GAME OVER TIMER
        if (obj == game1OverTimer){
            game1OverTimerDelay = game1OverTimerDelay - 1; //UPDATE COUNTDOWN
            if (game1OverTimerDelay == 6){
                gameOverOverlay();
                remove(game1MatchWin);
                remove(game1MatchLost);
            }
            if (game1OverTimerDelay == 0){
                game1OverTimer.stop();
                game1Timer.stop(); //STOP THIS MINI-GAME TIMER
                remove(game1OverTxt1);
                remove(game1OverTxt2);
                remove(game1OverBg);
                player.gameCampusOnePlayed = true; //SET FLAG THAT THIS GAME HAS BEEN PLAYED
                player.currentMapTile = "V04"; //SET NEW POSITION FOR PLAYER ON MAIN MAP
                player.gameScore = player.gameScore + player.game1Score; //UPDATE SCORE TOTAL
                player.gameTime = player.gameTime + player.game1Time; //UPDATE GAME TIME TOTAL
                removeAll();
                p07PanelGameMap = new panelGameMap(player);
                p07PanelGameMap.setBounds(0,0,1024,768);
                add(p07PanelGameMap);
                validate();
                repaint();
            }
        }
    }
}