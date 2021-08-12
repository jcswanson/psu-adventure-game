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

public class panelGameMap extends JPanel implements KeyListener, ActionListener {

    JButton gamemapLandBoundry,
            gamemapLand,
            gamemapPlayer;
    
    JLabel  gamemapCharacter,
            gamemapTheme,
            gamemap1Scranton,gamemap1ScrantonFlag,
            gamemap2UniversityPark,gamemap2UniversityParkFlag,
            gamemap3Behrend,gamemap3BehrendFlag,
            gamemap4WorldCampus,
            gamemapShipWreck;
    
    objPlayer player;
    objMapTile[] mapTilesArray;
    
    Timer gameTimer;
    
    panelGame1 p08PanelGame1;
    panelGame2 p09PanelGame2;
    panelGame3 p10PanelGame3;
    panelGame4 p11PanelGame4;
    panelGameOverProc p12PanelGameOverProc;
    
    Icon charMapLeft,charMapRight,charMapUp,charMapDown,charMapPortrait,charMapTheme,
         charMapPortraitDaddy = new ImageIcon("images/iconDaddy.png"),
         charMapDaddyLeft = new ImageIcon("images/charMapDaddyLeft.gif"),
         charMapDaddyRight = new ImageIcon("images/charMapDaddyRight.gif"),
         charMapDaddyUp = new ImageIcon("images/charMapDaddyUp.gif"),
         charMapDaddyDown = new ImageIcon("images/charMapDaddyDown.gif"),
         charMapPortraitMommy = new ImageIcon("images/iconMommy.png"),
         charMapMommyLeft = new ImageIcon("images/charMapMommyLeft.gif"),
         charMapMommyRight = new ImageIcon("images/charMapMommyRight.gif"),
         charMapMommyUp = new ImageIcon("images/charMapMommyUp.gif"),
         charMapMommyDown = new ImageIcon("images/charMapMommyDown.gif"),
         charMapPortraitBaby = new ImageIcon("images/iconBaby.png"),
         charMapBabyLeft = new ImageIcon("images/charMapBabyLeft.gif"),
         charMapBabyRight = new ImageIcon("images/charMapBabyRight.gif"),
         charMapBabyUp = new ImageIcon("images/charMapBabyUp.gif"),
         charMapBabyDown = new ImageIcon("images/charMapBabyDown.gif"),
         themeMapPSU = new ImageIcon("images/iconPSUMini.png"),
         themeMapWU = new ImageIcon("images/iconWUMini.png"),
         themeMap8BIT = new ImageIcon("images/icon8BitMini.png"),
         themeMapDiaper = new ImageIcon("images/iconDiaperMini.png"),
         gamemapCampusFlag = new ImageIcon("images/gamemapFlagDone.png"),
         gamemapCampusPlayed = new ImageIcon("images/gamemapCampusPlayed.png"),
         gamemapCampusNotPlayed = new ImageIcon("images/gamemapCampusNotplayed.gif"),
         gamemapWorldCampus = new ImageIcon("images/gamemapWorldCampus.png"),
         gamemapWorldCampusPlayed = new ImageIcon("images/gamemapWorldCampusPlayed.png"),
         gamemapShipWreckImg = new ImageIcon("images/gamemapShipWreck.png"),
         gamemapShipReadyImg = new ImageIcon("images/gamemapShipReady.gif");

    ImageIcon gamemapWater = new ImageIcon("images/gamemapWater.jpg");
    Image gamemapBG = gamemapWater.getImage();
    
    String a,arrayPositionToFind,nextID;
    
    int tempPosition,arrayPosition,currentPosition,nextPosition,nextX,nextY,currentTileType,
        gameTimerDelay;
    
    boolean overlayEnabled = false;
    
    public panelGameMap(objPlayer player)
    {
        super();
        setLayout(null);
        this.player = player;
        this.mapTilesArray = buildMapTiles();
        buildPlayerCharAndGameTheme();
        
        if(!player.gameStarted){buildInitialGameMap();}
        else {rebuildGameMap();}
    }

    //==========================================================================
    //PAINT GAME MAP AND TOP OVERLAY USING paintComponent
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(gamemapBG, 0, 0, this);  //DRAW GAME MAP
        
        g.setColor(new Color(0, 0, 0, 95)); //SET COLOR TO BLACK W/95% OPACITY
        g.fillRect(0, 0, 1024, 70); //LEFT, TOP, WIDTH, HEIGHT 
        
        Font f1 = new Font("Gothic", Font.BOLD, 20);
        g.setFont(f1);
        g.setColor(Color.WHITE);
        g.drawString("Timer: ", 500, 44);
        g.drawString("Score: ", 730, 44);
        
        g.setColor(Color.YELLOW);
        g.drawString(String.format( "%-10d", player.gameTime ), 575, 44); //ALIGN LEFT
        g.drawString(String.format( "%010d", player.gameScore ), 805, 44); //PADDING 10 DIGITS W/ZEROS
        
        setCharFocus();  //SET FOCUS ON PLAYER'S CHARACTER
    }
    
    //==========================================================================
    //BUILD GAME MAP FOR FIRST TIME
    public void buildInitialGameMap(){
        //SET GAME STARTED TO TRUE
        player.gameStarted = true;
        
        //START GAME TIMER
        gameTimerDelay = 1000;
        gameTimer = new Timer(gameTimerDelay, this);
        gameTimer.start();

        //BUILD THE PLAYER'S CHARACTER ON GAME MAP
        buildGameMapPlayer("Left",player.currentMapTile); //Place Player on Tile U13 Facing Left
        
        //BUILD THE GAME CAMPUSES ON GAME MAP
        buildGameMapCampus1(player.gameCampusOnePlayed);
        buildGameMapCampus2(player.gameCampusTwoPlayed);
        buildGameMapCampus3(player.gameCampusThreePlayed);
        buildGameMapCampus4(player.gameCampusOnePlayed,player.gameCampusTwoPlayed,player.gameCampusThreePlayed);
        
        buildGameMapArea();
    }
    
    //==========================================================================
    //REBUILD GAME MAP
    public void rebuildGameMap(){
        //UNPAUSE MAIN MAP GAME TIMER
        player.gameTimerPaused = false;
        
        //BUILD THE PLAYER'S CHARACTER ON GAME MAP
        buildGameMapPlayer("Down",player.currentMapTile);
        
        //BUILD THE GAME CAMPUSES ON GAME MAP
        buildGameMapCampus1(player.gameCampusOnePlayed);
        buildGameMapCampus2(player.gameCampusTwoPlayed);
        buildGameMapCampus3(player.gameCampusThreePlayed);
        buildGameMapCampus4(player.gameCampusOnePlayed,player.gameCampusTwoPlayed,player.gameCampusThreePlayed);
        
        buildGameMapArea();
    }
    
    //MAKE SURE FOCUS IS SET ON PLAYER'S CHARACTER ON GAME MAP
    public void setCharFocus(){
        gamemapPlayer.requestFocusInWindow();
        gamemapPlayer.requestFocus();
        gamemapPlayer.setVisible(true);
    }
   
    //MOVE GAME MAP CHARACTER UP ONE TILE
    public void moveCharUp(){
        int currentPosition = arrayPosition(player.currentMapTile);
        if(mapTilesArray[currentPosition].canMoveUp){
            nextPosition = arrayPosition(mapTilesArray[currentPosition].tileAbove);
            nextX = mapTilesArray[nextPosition].xPos;
            nextY = mapTilesArray[nextPosition].yPos;
            nextID = mapTilesArray[nextPosition].id;
            gamemapPlayer.setBounds(nextX,nextY,40,40);
            player.currentMapTile = nextID;
            checkTileType(nextPosition);
        }
    }
    
    //MOVE GAME MAP CHARACTER DOWN ONE TILE
    public void moveCharDown(){
        int currentPosition = arrayPosition(player.currentMapTile);
        if(mapTilesArray[currentPosition].canMoveDown){
            nextPosition = arrayPosition(mapTilesArray[currentPosition].tileBelow);
            nextX = mapTilesArray[nextPosition].xPos;
            nextY = mapTilesArray[nextPosition].yPos;
            nextID = mapTilesArray[nextPosition].id;
            gamemapPlayer.setBounds(nextX,nextY,40,40);
            player.currentMapTile = nextID;
            checkTileType(nextPosition);
        }
    }
    
    //MOVE GAME MAP CHARACTER LEFT ONE TILE
    public void moveCharLeft(){
        int currentPosition = arrayPosition(player.currentMapTile);
        if(mapTilesArray[currentPosition].canMoveLeft){
            nextPosition = arrayPosition(mapTilesArray[currentPosition].tileLeft);
            nextX = mapTilesArray[nextPosition].xPos;
            nextY = mapTilesArray[nextPosition].yPos;
            nextID = mapTilesArray[nextPosition].id;
            gamemapPlayer.setBounds(nextX,nextY,40,40);
            player.currentMapTile = nextID;
            checkTileType(nextPosition);
        }
    }
    
    //MOVE GAME MAP CHARACTER RIGHT ONE TILE
    public void moveCharRight(){
        int currentPosition = arrayPosition(player.currentMapTile);
        if(mapTilesArray[currentPosition].canMoveRight){
            nextPosition = arrayPosition(mapTilesArray[currentPosition].tileRight);
            nextX = mapTilesArray[nextPosition].xPos;
            nextY = mapTilesArray[nextPosition].yPos;
            nextID = mapTilesArray[nextPosition].id;
            gamemapPlayer.setBounds(nextX,nextY,40,40);
            player.currentMapTile = nextID;
            checkTileType(nextPosition);
        }
    }
    
    //DETERMINE ARRAY POSITION OF MAP TILE BY MAP TILE ID
    public int arrayPosition(String a){
        arrayPositionToFind = a;
        for (int i=0; i< mapTilesArray.length; i++){
            if(mapTilesArray[i].id == arrayPositionToFind){
               arrayPosition = i;
            }
        }
        return arrayPosition;
    }
    
    //CHECK MAP TILE ONCE PLAYER'S CHARACTER MOVES INTO IT && LOAD MINI GAMES
    public void checkTileType(int a){
        //DO NOTHING IF TILE TYPE EQUALS 1
        if (mapTilesArray[a].type == 1){ return; }
        
        //LOAD GAME1 IF TILE TYPE EQUALS 2
        else if (mapTilesArray[a].type == 2) 
            {
            player.gameTimerPaused = true;
            removeAll();
            p08PanelGame1 = new panelGame1(player);
            p08PanelGame1.setBounds(0,0,1024,768);
            add(p08PanelGame1);
            validate();
            repaint();
            }
        else if (mapTilesArray[a].type == 3) 
            {
            player.gameTimerPaused = true;
            removeAll();
            p09PanelGame2 = new panelGame2(player);
            p09PanelGame2.setBounds(0,0,1024,768);
            add(p09PanelGame2);
            validate();
            repaint();
            }
        else if (mapTilesArray[a].type == 4) 
            {
            player.gameTimerPaused = true;
            removeAll();
            p10PanelGame3 = new panelGame3(player);
            p10PanelGame3.setBounds(0,0,1024,768);
            add(p10PanelGame3);
            validate();
            repaint();
            }
        else if (mapTilesArray[a].type == 5) 
            {
            player.gameTimerPaused = true;
            removeAll();
            p11PanelGame4 = new panelGame4(player);
            p11PanelGame4.setBounds(0,0,1024,768);
            add(p11PanelGame4);
            validate();
            repaint();
            }
        else if (mapTilesArray[a].type == 6) 
            {
            player.gameTimerPaused = true;
            removeAll();
            p12PanelGameOverProc = new panelGameOverProc(player);
            p12PanelGameOverProc.setBounds(0,0,1024,768);
            add(p12PanelGameOverProc);
            validate();
            repaint();
            }
        else { return; }
    }

    //SET STATIC MAP PLAYER GRAPHICS AND OVERLAY GRAPHICS
    public void buildPlayerCharAndGameTheme(){
    //SET GAME MAP CHARACTER TO MATCH PLAYER'S CHARACTER
        if(player.character == 1){
        charMapLeft = charMapDaddyLeft;
        charMapRight = charMapDaddyRight;
        charMapUp = charMapDaddyUp;
        charMapDown = charMapDaddyDown;
        charMapPortrait = charMapPortraitDaddy;
        }
        else if (player.character == 2){
        charMapLeft = charMapMommyLeft;
        charMapRight = charMapMommyRight;
        charMapUp = charMapMommyUp;
        charMapDown = charMapMommyDown;
        charMapPortrait = charMapPortraitMommy;
        }
        else {
        charMapLeft = charMapBabyLeft;
        charMapRight = charMapBabyRight;
        charMapUp = charMapBabyUp;
        charMapDown = charMapBabyDown;
        charMapPortrait = charMapPortraitBaby;
        }
        
        //SET THEME ICON FOR PLAYER'S CHARACTER
        if(player.gameTheme == 1){charMapTheme = themeMapPSU;}
        else if(player.gameTheme == 2){charMapTheme = themeMapWU;}
        else if(player.gameTheme == 3){charMapTheme = themeMap8BIT;}
        else if(player.gameTheme == 4){charMapTheme = themeMapDiaper;}
        
        //BUILD GAME SETTINGS ICONS IN OVERLAY
        gamemapTheme = new JLabel(charMapTheme);
        gamemapTheme.setBounds(45,42,30,25);
        add(gamemapTheme);
        gamemapTheme.setBorder(BorderFactory.createEmptyBorder());
        gamemapTheme.setFocusable(false);
        gamemapTheme.setVisible(true);
        gamemapCharacter = new JLabel(charMapPortrait);
        gamemapCharacter.setBounds(10,3,64,64);
        add(gamemapCharacter);
        gamemapCharacter.setBorder(BorderFactory.createEmptyBorder());
        gamemapCharacter.setFocusable(false);
        gamemapCharacter.setVisible(true);
    }
    
    //BUILD PLAYER CHARACTER ON GAME MAP
    public void buildGameMapPlayer(String a, String b){
        if (a == "Left"){gamemapPlayer = new JButton(charMapLeft);}
        else if (a == "Right"){gamemapPlayer = new JButton(charMapRight);}
        else if (a == "Up"){gamemapPlayer = new JButton(charMapUp);}
        else {gamemapPlayer = new JButton(charMapDown);}
        
        nextPosition = arrayPosition(b);
        nextX = mapTilesArray[nextPosition].xPos;
        nextY = mapTilesArray[nextPosition].yPos;
        gamemapPlayer.setBounds(nextX,nextY,40,40);   
        add(gamemapPlayer);
        
        gamemapPlayer.setBorder(BorderFactory.createEmptyBorder());
        gamemapPlayer.setContentAreaFilled(false);
        gamemapPlayer.setFocusable(true);
        gamemapPlayer.setVisible(false);
        gamemapPlayer.addKeyListener(this);
        gamemapPlayer.requestFocusInWindow();
        gamemapPlayer.requestFocus();
    }
    
    //BUILD GAME 1 SCRANTON CAMPUS ON GAME MAP
    public void buildGameMapCampus1(boolean a){
        if(!a){     //IF GAME 1 HAS NOT BEEN PLAYED YET
            gamemap1Scranton = new JLabel(gamemapCampusNotPlayed);
            gamemap1Scranton.setBounds(815,250,88,68);
            add(gamemap1Scranton);
            gamemap1Scranton.setBorder(BorderFactory.createEmptyBorder());
            gamemap1Scranton.setFocusable(false);
            gamemap1Scranton.setVisible(true);
        }
        else if(a){
            gamemap1Scranton = new JLabel(gamemapCampusPlayed);
            gamemap1ScrantonFlag = new JLabel(gamemapCampusFlag);
            gamemap1Scranton.setBounds(815,250,88,68);
            gamemap1ScrantonFlag.setBounds(825,230,45,57);
            add(gamemap1ScrantonFlag);
            add(gamemap1Scranton);
            gamemap1Scranton.setBorder(BorderFactory.createEmptyBorder());
            gamemap1ScrantonFlag.setBorder(BorderFactory.createEmptyBorder());
            gamemap1Scranton.setFocusable(false);
            gamemap1ScrantonFlag.setFocusable(false);
            gamemap1Scranton.setVisible(true);
            gamemap1ScrantonFlag.setVisible(true);
            
            mapTilesArray[275].type=1; mapTilesArray[276].type=1;
            mapTilesArray[287].type=1; mapTilesArray[288].type=1;            
            mapTilesArray[276].canMoveUp=false;
            mapTilesArray[276].canMoveRight=false;
            mapTilesArray[262].canMoveRight=false;
            mapTilesArray[263].canMoveRight=false;
            mapTilesArray[274].canMoveDown=false;
            mapTilesArray[286].canMoveDown=false;
            mapTilesArray[298].canMoveLeft=false;
            mapTilesArray[299].canMoveLeft=false;
            mapTilesArray[277].canMoveUp=false;
            mapTilesArray[289].canMoveUp=false;
        }
    }
    
    //BUILD GAME 2 UNIVERSITY PARK CAMPUS ON GAME MAP
    public void buildGameMapCampus2(boolean a){
        if(!a){     //IF GAME 2 HAS NOT BEEN PLAYED YET
            gamemap2UniversityPark = new JLabel(gamemapCampusNotPlayed);
            gamemap2UniversityPark.setBounds(385,370,88,68);
            add(gamemap2UniversityPark);
            gamemap2UniversityPark.setBorder(BorderFactory.createEmptyBorder());
            gamemap2UniversityPark.setFocusable(false);
            gamemap2UniversityPark.setVisible(true);
        }
        else if(a){
            gamemap2UniversityPark = new JLabel(gamemapCampusPlayed);
            gamemap2UniversityParkFlag = new JLabel(gamemapCampusFlag);
            gamemap2UniversityPark.setBounds(385,370,88,68);
            gamemap2UniversityParkFlag.setBounds(372,345,88,68);
            add(gamemap2UniversityParkFlag);
            add(gamemap2UniversityPark);
            gamemap2UniversityPark.setBorder(BorderFactory.createEmptyBorder());
            gamemap2UniversityParkFlag.setBorder(BorderFactory.createEmptyBorder());
            gamemap2UniversityPark.setFocusable(false);
            gamemap2UniversityParkFlag.setFocusable(false);
            gamemap2UniversityPark.setVisible(true);
            gamemap2UniversityParkFlag.setVisible(true);
            
            mapTilesArray[135].type=1; mapTilesArray[136].type=1;
            mapTilesArray[148].type=1; mapTilesArray[149].type=1;
            mapTilesArray[136].canMoveUp=false;
            mapTilesArray[136].canMoveRight=false;
            mapTilesArray[122].canMoveRight=false;
            mapTilesArray[123].canMoveRight=false;
            mapTilesArray[134].canMoveDown=false;
            mapTilesArray[147].canMoveDown=false;
            mapTilesArray[137].canMoveUp=false;
            mapTilesArray[150].canMoveUp=false;
            mapTilesArray[161].canMoveLeft=false;
            mapTilesArray[162].canMoveLeft=false;
        }
    }
    
    //BUILD GAME 3 BEHREND CAMPUS ON GAME MAP
    public void buildGameMapCampus3(boolean a){
        if(!a){     //IF GAME 3 HAS NOT BEEN PLAYED YET
            gamemap3Behrend = new JLabel(gamemapCampusNotPlayed);
            gamemap3Behrend.setBounds(36,144,88,68);
            add(gamemap3Behrend);
            gamemap3Behrend.setBorder(BorderFactory.createEmptyBorder());
            gamemap3Behrend.setFocusable(false);
            gamemap3Behrend.setVisible(true);
        }
        else if(a){
            gamemap3Behrend = new JLabel(gamemapCampusPlayed);
            gamemap3BehrendFlag = new JLabel(gamemapCampusFlag);
            gamemap3Behrend.setBounds(36,144,88,68);
            gamemap3BehrendFlag.setBounds(22,119,88,68);
            add(gamemap3BehrendFlag);
            add(gamemap3Behrend);
            gamemap3Behrend.setBorder(BorderFactory.createEmptyBorder());
            gamemap3BehrendFlag.setBorder(BorderFactory.createEmptyBorder());
            gamemap3Behrend.setFocusable(false);
            gamemap3BehrendFlag.setFocusable(false);
            gamemap3Behrend.setVisible(true);
            gamemap3BehrendFlag.setVisible(true);
            
            mapTilesArray[13].type=1; mapTilesArray[13].canMoveRight=false;
            mapTilesArray[26].type=1; mapTilesArray[26].canMoveLeft=false;
            mapTilesArray[0].canMoveRight=false;
            mapTilesArray[14].canMoveUp=false;
            mapTilesArray[27].canMoveUp=false;
            mapTilesArray[39].canMoveLeft=false;
        }
    }
    
    //BUILD GAME 4 WORLD CAMPUS ON GAME MAP
    public void buildGameMapCampus4(boolean a, boolean b, boolean c){
        if(!player.gameCampusWCPlayed){
          if(!a || !b || !c){  //IF GAME 1,2, OR 3 HAVE NOT BEEN PLAYED 
                gamemap4WorldCampus = new JLabel(gamemapWorldCampus);
                gamemap4WorldCampus.setBounds(0,0,0,0);
                add(gamemap4WorldCampus);
                gamemap4WorldCampus.setVisible(false);
            }
        
          else if(a && b && c){ //IF GAMES 1,2, AND 3 HAVE BEEN PLAYED
                gamemap4WorldCampus = new JLabel(gamemapWorldCampus);
                gamemap4WorldCampus.setBounds(110,523,177,76);
                add(gamemap4WorldCampus);
                gamemap4WorldCampus.setBorder(BorderFactory.createEmptyBorder());
                gamemap4WorldCampus.setFocusable(false);
                gamemap4WorldCampus.setVisible(true);
                mapTilesArray[48].type=5; mapTilesArray[49].type=5;
                mapTilesArray[61].type=5; mapTilesArray[62].type=5;
                mapTilesArray[74].type=5; mapTilesArray[75].type=5;
                mapTilesArray[87].type=5; mapTilesArray[88].type=5;
            }
            //ADD SMALL SHIPWRECK THAT CANT BE ENTERED NEAR START POSITION
            gamemapShipWreck = new JLabel(gamemapShipWreckImg);
            gamemapShipWreck.setBounds(830,655,24,21);
            add(gamemapShipWreck);
            gamemapShipWreck.setBorder(BorderFactory.createEmptyBorder());
            gamemapShipWreck.setFocusable(false);
        }
        else if(player.gameCampusWCPlayed){
            gamemap4WorldCampus = new JLabel(gamemapWorldCampusPlayed);
            gamemap4WorldCampus.setBounds(110,489,171,110);
            add(gamemap4WorldCampus);
            gamemap4WorldCampus.setBorder(BorderFactory.createEmptyBorder());
            gamemap4WorldCampus.setFocusable(false);
            gamemap4WorldCampus.setVisible(true);
            mapTilesArray[48].type=1; mapTilesArray[49].type=1;
            mapTilesArray[61].type=1; mapTilesArray[62].type=1;
            mapTilesArray[74].type=1; mapTilesArray[75].type=1;
            mapTilesArray[87].type=1; mapTilesArray[88].type=1;
            mapTilesArray[47].canMoveDown=false;
            mapTilesArray[60].canMoveDown=false;
            mapTilesArray[73].canMoveDown=false;
            mapTilesArray[86].canMoveDown=false;
            mapTilesArray[50].canMoveUp=false;
            mapTilesArray[63].canMoveUp=false;
            mapTilesArray[76].canMoveUp=false;
            mapTilesArray[89].canMoveUp=false;
            mapTilesArray[35].canMoveRight=false;
            mapTilesArray[36].canMoveRight=false;
            mapTilesArray[100].canMoveLeft=false;
            mapTilesArray[101].canMoveLeft=false;
            mapTilesArray[75].canMoveUp=false;
            mapTilesArray[75].canMoveRight=false;
            mapTilesArray[75].canMoveLeft=false;
            
            //ENABLE RETURNING TO SHIP ON TILE V13 TO END THE GAME
            mapTilesArray[272].canMoveRight=true;
            mapTilesArray[272].tileRight="V13";
            mapTilesArray[284].canMoveDown=true;
            mapTilesArray[284].tileBelow="V13";
            
            //ADD ANIMATED SHIPWRECK THAT CAN BE ENTERED
            gamemapShipWreck = new JLabel(gamemapShipReadyImg);
            gamemapShipWreck.setBounds(820,643,45,45);
            add(gamemapShipWreck);
            gamemapShipWreck.setBorder(BorderFactory.createEmptyBorder());
            gamemapShipWreck.setFocusable(false);
        }
    }
    
    //BUILD ACTUAL GAME MAP LAND
    public void buildGameMapArea(){
        //BUILD AN INVISIBLE RECTANGULAR LAND BOUNDRY ON GAME MAP FOR DEBUGGING
        gamemapLandBoundry = new JButton(new ImageIcon("images/gamemapLandBoundryGridLabeled.png"));
        gamemapLandBoundry.setBounds(0,168,976,508);
        gamemapLandBoundry.setBorder(BorderFactory.createEmptyBorder());
        gamemapLandBoundry.setContentAreaFilled(false);
        gamemapLandBoundry.setVisible(false); //TO HIDE OVERLAY SET TO FALSE
        add(gamemapLandBoundry);
        
        //BUILD GAME MAP LAND
        gamemapLand = new JButton(new ImageIcon("images/gamemapLand.png"));
        gamemapLand.setBounds(0,100,1000,588);
        gamemapLand.setBorder(BorderFactory.createEmptyBorder());
        gamemapLand.setContentAreaFilled(false);
        gamemapLand.setVisible(true);
        add(gamemapLand);
    }
    
    //==========================================================================
    // SET KEYBOARD LISTENERS
    public void keyPressed(KeyEvent evt){
       int kk = evt.getKeyCode();
       
       //F1 KEY WILL RETURN PLAYER SPECIFIC OBJECT STATUS
       if(kk ==  evt.VK_F1){
           System.out.println("-=-=-=-=-=-=-=-=-=-=-");
           System.out.println("PLAYER STATUS REPORT (F1 KEY)");
           System.out.println("-=-=-=-=-=-=-=-=-=-=-");
           System.out.println("Player Character = " + player.character);
           System.out.println("Game Theme = " + player.gameTheme);
           System.out.println("Current Map Tile Id = " + player.currentMapTile);
           System.out.println("-=-=-=-=-=-=-=-=-=-=-");
           System.out.println("Has Game Started? = " + player.gameStarted);
           System.out.println("Has Game Ended? = " + player.gameOver);
           System.out.println("-=-=-=-=-=-=-=-=-=-=-");
           System.out.println("Current Game Time = " + player.gameTime);
           System.out.println("Current Score = " + player.gameScore);
           System.out.println("-=-=-=-=-=-=-=-=-=-=-");
           System.out.println("G1 Played=" + player.gameCampusOnePlayed + " | G2 Played=" + player.gameCampusTwoPlayed);
           System.out.println("G3 Played=" + player.gameCampusThreePlayed + " | G4 Played=" + player.gameCampusWCPlayed);
        }
       
       //F2 KEY WILL RETURN MINI-GAME SPECIFIC OBJECT STATUS
       if(kk ==  evt.VK_F2){
           System.out.println("-=-=-=-=-=-=-=-=-=-=-");
           System.out.println("MINI-GAME STATUS REPORT (F2 KEY)");
           System.out.println("-=-=-=-=-=-=-=-=-=-=-");
           System.out.println("Game 1 Played = " + player.gameCampusOnePlayed);
           System.out.println("Game 1 Score = " + player.game1Score);
           System.out.println("Game 1 Time = " + player.game1Time);
           System.out.println("Game 2 Played = " + player.gameCampusTwoPlayed);
           System.out.println("Game 2 Score = " + player.game2Score);
           System.out.println("Game 2 Time = " + player.game2Time);
           System.out.println("Game 3 Played = " + player.gameCampusThreePlayed);
           System.out.println("Game 3 Score = " + player.game3Score);
           System.out.println("Game 3 Time = " + player.game3Time);
           System.out.println("Game 4 Played = " + player.gameCampusWCPlayed);
           System.out.println("Game 4 Score = " + player.game4Score);
           System.out.println("Game 4 Time = " + player.game4Time);
           System.out.println("-=-=-=-=-=-=-=-=-=-=-");
        }
       
       //F3 KEY WILL TOGGLE GAMEMAP DEBUG OVERLAY
       if(kk ==  evt.VK_F3){
           if(!overlayEnabled){
               gamemapLandBoundry.setVisible(true);
               gamemapLandBoundry.repaint();
               overlayEnabled=true;
               System.out.println("Toggling Game Map Overlay");
           }
           else {
               gamemapLandBoundry.setVisible(false);
               gamemapLandBoundry.repaint();
               overlayEnabled=false;
               System.out.println("Toggling Game Map Overlay");
           }
       }
              
       //LEFT ARROW KEY
       if(kk == evt.VK_LEFT){
           gamemapPlayer.setIcon(charMapLeft);
           gamemapPlayer.repaint();
           moveCharLeft();
        }
       
       //RIGHT ARROW KEY
       if(kk == evt.VK_RIGHT){
           gamemapPlayer.setIcon(charMapRight);
           gamemapPlayer.repaint();
           moveCharRight();
        }
       
       //UP ARROW KEY
       if(kk == evt.VK_UP){
           gamemapPlayer.setIcon(charMapUp);
           gamemapPlayer.repaint();
           moveCharUp();
        }
       
       //DOWN ARROW KEY
       if(kk == evt.VK_DOWN){
           gamemapPlayer.setIcon(charMapDown);
           gamemapPlayer.repaint();
           moveCharDown();
        }
    }
    public void keyReleased(KeyEvent evt) { }
    public void keyTyped(KeyEvent evt) { }
    
    //==========================================================================
    // SET ACTION PERFORMED LISTENERS (GAME TIMERS)
    public void actionPerformed(ActionEvent event){
        Object obj = event.getSource();
        String choice = event.getActionCommand();
	
        //DO ON EACH TIMER TICK
        if (obj == gameTimer){
            if (!player.gameTimerPaused) //IF gameTimerPaused IS FALSE
                {player.gameTime = player.gameTime + 1; //UPDATE GAME TIMER
                repaint();}
        }	
    }
    
    //==========================================================================
    //BUILD ARRAY OF MAP TILE OBJECTS USING objMapTile.java DEFINED CLASS
    public objMapTile[] buildMapTiles(){
        objMapTile[] mapTileArray = new objMapTile[311]; //311 TILES TOTAL IN GAME
        mapTileArray[0] = new objMapTile("A01",0,167,1,"NONE","A02","NONE","B01",false,true,false,true);
        mapTileArray[1] = new objMapTile("A02",0,206,1,"A01","A03","NONE","B02",true,true,false,true);
        mapTileArray[2] = new objMapTile("A03",0,245,1,"A02","A04","NONE","B03",true,true,false,true);
        mapTileArray[3] = new objMapTile("A04",0,284,1,"A03","A05","NONE","B04",true,true,false,true);
        mapTileArray[4] = new objMapTile("A05",0,323,1,"A04","A06","NONE","B05",true,true,false,true);
        mapTileArray[5] = new objMapTile("A06",0,362,1,"A05","A07","NONE","B06",true,true,false,true);
        mapTileArray[6] = new objMapTile("A07",0,401,1,"A06","A08","NONE","B07",true,true,false,true);
        mapTileArray[7] = new objMapTile("A08",0,440,1,"A07","A09","NONE","B08",true,true,false,true);
        mapTileArray[8] = new objMapTile("A09",0,479,1,"A08","A10","NONE","B09",true,true,false,true);
        mapTileArray[9] = new objMapTile("A10",0,518,1,"A09","A11","NONE","B10",true,true,false,true);
        mapTileArray[10] = new objMapTile("A11",0,557,1,"A10","A12","NONE","B11",true,true,false,true);
        mapTileArray[11] = new objMapTile("A12",0,596,1,"A11","A13","NONE","B12",true,true,false,true);
        mapTileArray[12] = new objMapTile("A13",0,635,1,"A12","NONE","NONE","B13",true,false,false,true);
        mapTileArray[13] = new objMapTile("B01",39,167,4,"NONE","B02","A01","C01",false,true,true,true);
        mapTileArray[14] = new objMapTile("B02",39,206,1,"B01","B03","A02","C02",true,true,true,true);
        mapTileArray[15] = new objMapTile("B03",39,245,1,"B02","B04","A03","C03",true,true,true,true);
        mapTileArray[16] = new objMapTile("B04",39,284,1,"B03","B05","A04","C04",true,true,true,true);
        mapTileArray[17] = new objMapTile("B05",39,323,1,"B04","B06","A05","C05",true,true,true,true);
        mapTileArray[18] = new objMapTile("B06",39,362,1,"B05","B07","A06","C06",true,true,true,true);
        mapTileArray[19] = new objMapTile("B07",39,401,1,"B06","B08","A07","C07",true,true,true,true);
        mapTileArray[20] = new objMapTile("B08",39,440,1,"B07","B09","A08","C08",true,true,true,true);
        mapTileArray[21] = new objMapTile("B09",39,479,1,"B08","B10","A09","C09",true,true,true,true);
        mapTileArray[22] = new objMapTile("B10",39,518,1,"B09","B11","A10","C10",true,true,true,true);
        mapTileArray[23] = new objMapTile("B11",39,557,1,"B10","B12","A11","C11",true,true,true,true);
        mapTileArray[24] = new objMapTile("B12",39,596,1,"B11","B13","A12","C12",true,true,true,true);
        mapTileArray[25] = new objMapTile("B13",39,635,1,"B12","NONE","A13","C13",true,false,true,true);
        mapTileArray[26] = new objMapTile("C01",78,167,4,"NONE","C02","B01","D01",false,true,true,true);
        mapTileArray[27] = new objMapTile("C02",78,206,1,"C01","C03","B02","D02",true,true,true,true);
        mapTileArray[28] = new objMapTile("C03",78,245,1,"C02","C04","B03","D03",true,true,true,true);
        mapTileArray[29] = new objMapTile("C04",78,284,1,"C03","C05","B04","D04",true,true,true,true);
        mapTileArray[30] = new objMapTile("C05",78,323,1,"C04","C06","B05","D05",true,true,true,true);
        mapTileArray[31] = new objMapTile("C06",78,362,1,"C05","C07","B06","D06",true,true,true,true);
        mapTileArray[32] = new objMapTile("C07",78,401,1,"C06","C08","B07","D07",true,true,true,true);
        mapTileArray[33] = new objMapTile("C08",78,440,1,"C07","C09","B08","D08",true,true,true,true);
        mapTileArray[34] = new objMapTile("C09",78,479,1,"C08","C10","B09","D09",true,true,true,true);
        mapTileArray[35] = new objMapTile("C10",78,518,1,"C09","C11","B10","D10",true,true,true,true);
        mapTileArray[36] = new objMapTile("C11",78,557,1,"C10","C12","B11","D11",true,true,true,true);
        mapTileArray[37] = new objMapTile("C12",78,596,1,"C11","C13","B12","D12",true,true,true,true);
        mapTileArray[38] = new objMapTile("C13",78,635,1,"C12","NONE","B13","D13",true,false,true,true);
        mapTileArray[39] = new objMapTile("D01",117,167,1,"NONE","D02","C01","E01",false,true,true,true);
        mapTileArray[40] = new objMapTile("D02",117,206,1,"D01","D03","C02","E02",true,true,true,true);
        mapTileArray[41] = new objMapTile("D03",117,245,1,"D02","D04","C03","E03",true,true,true,true);
        mapTileArray[42] = new objMapTile("D04",117,284,1,"D03","D05","C04","E04",true,true,true,true);
        mapTileArray[43] = new objMapTile("D05",117,323,1,"D04","D06","C05","E05",true,true,true,true);
        mapTileArray[44] = new objMapTile("D06",117,362,1,"D05","D07","C06","E06",true,true,true,true);
        mapTileArray[45] = new objMapTile("D07",117,401,1,"D06","D08","C07","E07",true,true,true,true);
        mapTileArray[46] = new objMapTile("D08",117,440,1,"D07","D09","C08","E08",true,true,true,true);
        mapTileArray[47] = new objMapTile("D09",117,479,1,"D08","D10","C09","E09",true,true,true,true);
        mapTileArray[48] = new objMapTile("D10",117,518,1,"D09","D11","C10","E10",true,true,true,true);
        mapTileArray[49] = new objMapTile("D11",117,557,1,"D10","D12","C11","E11",true,true,true,true);
        mapTileArray[50] = new objMapTile("D12",117,596,1,"D11","D13","C12","E12",true,true,true,true);
        mapTileArray[51] = new objMapTile("D13",117,635,1,"D12","NONE","C13","E13",true,false,true,true);
        mapTileArray[52] = new objMapTile("E01",155,167,1,"NONE","E02","D01","F01",false,true,true,true);
        mapTileArray[53] = new objMapTile("E02",155,206,1,"E01","E03","D02","F02",true,true,true,true);
        mapTileArray[54] = new objMapTile("E03",155,245,1,"E02","E04","D03","F03",true,true,true,true);
        mapTileArray[55] = new objMapTile("E04",155,284,1,"E03","E05","D04","F04",true,true,true,true);
        mapTileArray[56] = new objMapTile("E05",155,323,1,"E04","E06","D05","F05",true,true,true,true);
        mapTileArray[57] = new objMapTile("E06",155,362,1,"E05","E07","D06","F06",true,true,true,true);
        mapTileArray[58] = new objMapTile("E07",155,401,1,"E06","E08","D07","F07",true,true,true,true);
        mapTileArray[59] = new objMapTile("E08",155,440,1,"E07","E09","D08","F08",true,true,true,true);
        mapTileArray[60] = new objMapTile("E09",155,479,1,"E08","E10","D09","F09",true,true,true,true);
        mapTileArray[61] = new objMapTile("E10",155,518,1,"E09","E11","D10","F10",true,true,true,true);
        mapTileArray[62] = new objMapTile("E11",155,557,1,"E10","E12","D11","F11",true,true,true,true);
        mapTileArray[63] = new objMapTile("E12",155,596,1,"E11","E13","D12","F12",true,true,true,true);
        mapTileArray[64] = new objMapTile("E13",155,635,1,"E12","NONE","D13","F13",true,false,true,true);
        mapTileArray[65] = new objMapTile("F01",195,167,1,"NONE","F02","E01","G01",false,true,true,true);
        mapTileArray[66] = new objMapTile("F02",195,206,1,"F01","F03","E02","G02",true,true,true,true);
        mapTileArray[67] = new objMapTile("F03",195,245,1,"F02","F04","E03","G03",true,true,true,true);
        mapTileArray[68] = new objMapTile("F04",195,284,1,"F03","F05","E04","G04",true,true,true,true);
        mapTileArray[69] = new objMapTile("F05",195,323,1,"F04","F06","E05","G05",true,true,true,true);
        mapTileArray[70] = new objMapTile("F06",195,362,1,"F05","F07","E06","G06",true,true,true,true);
        mapTileArray[71] = new objMapTile("F07",195,401,1,"F06","F08","E07","G07",true,true,true,true);
        mapTileArray[72] = new objMapTile("F08",195,440,1,"F07","F09","E08","G08",true,true,true,true);
        mapTileArray[73] = new objMapTile("F09",195,479,1,"F08","F10","E09","G09",true,true,true,true);
        mapTileArray[74] = new objMapTile("F10",195,518,1,"F09","F11","E10","G10",true,true,true,true);
        mapTileArray[75] = new objMapTile("F11",195,557,1,"F10","F12","E11","G11",true,true,true,true);
        mapTileArray[76] = new objMapTile("F12",195,596,1,"F11","F13","E12","G12",true,true,true,true);
        mapTileArray[77] = new objMapTile("F13",195,635,1,"F12","NONE","E13","G13",true,false,true,true);
        mapTileArray[78] = new objMapTile("G01",234,167,1,"NONE","G02","F01","H01",false,true,true,true);
        mapTileArray[79] = new objMapTile("G02",234,206,1,"G01","G03","F02","H02",true,true,true,true);
        mapTileArray[80] = new objMapTile("G03",234,245,1,"G02","G04","F03","H03",true,true,true,true);
        mapTileArray[81] = new objMapTile("G04",234,284,1,"G03","G05","F04","H04",true,true,true,true);
        mapTileArray[82] = new objMapTile("G05",234,323,1,"G04","G06","F05","H05",true,true,true,true);
        mapTileArray[83] = new objMapTile("G06",234,362,1,"G05","G07","F06","H06",true,true,true,true);
        mapTileArray[84] = new objMapTile("G07",234,401,1,"G06","G08","F07","H07",true,true,true,true);
        mapTileArray[85] = new objMapTile("G08",234,440,1,"G07","G09","F08","H08",true,true,true,true);
        mapTileArray[86] = new objMapTile("G09",234,479,1,"G08","G10","F09","H09",true,true,true,true);
        mapTileArray[87] = new objMapTile("G10",234,518,1,"G09","G11","F10","H10",true,true,true,true);
        mapTileArray[88] = new objMapTile("G11",234,557,1,"G10","G12","F11","H11",true,true,true,true);
        mapTileArray[89] = new objMapTile("G12",234,596,1,"G11","G13","F12","H12",true,true,true,true);
        mapTileArray[90] = new objMapTile("G13",234,635,1,"G12","NONE","F13","H13",true,false,true,true);
        mapTileArray[91] = new objMapTile("H01",273,167,1,"NONE","H02","G01","I01",false,true,true,true);
        mapTileArray[92] = new objMapTile("H02",273,206,1,"H01","H03","G02","I02",true,true,true,true);
        mapTileArray[93] = new objMapTile("H03",273,245,1,"H02","H04","G03","I03",true,true,true,true);
        mapTileArray[94] = new objMapTile("H04",273,284,1,"H03","H05","G04","I04",true,true,true,true);
        mapTileArray[95] = new objMapTile("H05",273,323,1,"H04","H06","G05","I05",true,true,true,true);
        mapTileArray[96] = new objMapTile("H06",273,362,1,"H05","H07","G06","I06",true,true,true,true);
        mapTileArray[97] = new objMapTile("H07",273,401,1,"H06","H08","G07","I07",true,true,true,true);
        mapTileArray[98] = new objMapTile("H08",273,440,1,"H07","H09","G08","I08",true,true,true,true);
        mapTileArray[99] = new objMapTile("H09",273,479,1,"H08","H10","G09","I09",true,true,true,true);
        mapTileArray[100] = new objMapTile("H10",273,518,1,"H09","H11","G10","I10",true,true,true,true);
        mapTileArray[101] = new objMapTile("H11",273,557,1,"H10","H12","G11","I11",true,true,true,true);
        mapTileArray[102] = new objMapTile("H12",273,596,1,"H11","H13","G12","I12",true,true,true,true);
        mapTileArray[103] = new objMapTile("H13",273,635,1,"H12","NONE","G13","I13",true,false,true,true);
        mapTileArray[104] = new objMapTile("I01",312,167,1,"NONE","I02","H01","J01",false,true,true,true);
        mapTileArray[105] = new objMapTile("I02",312,206,1,"I01","I03","H02","J02",true,true,true,true);
        mapTileArray[106] = new objMapTile("I03",312,245,1,"I02","I04","H03","J03",true,true,true,true);
        mapTileArray[107] = new objMapTile("I04",312,284,1,"I03","I05","H04","J04",true,true,true,true);
        mapTileArray[108] = new objMapTile("I05",312,323,1,"I04","I06","H05","J05",true,true,true,true);
        mapTileArray[109] = new objMapTile("I06",312,362,1,"I05","I07","H06","J06",true,true,true,true);
        mapTileArray[110] = new objMapTile("I07",312,401,1,"I06","I08","H07","J07",true,true,true,true);
        mapTileArray[111] = new objMapTile("I08",312,440,1,"I07","I09","H08","J08",true,true,true,true);
        mapTileArray[112] = new objMapTile("I09",312,479,1,"I08","I10","H09","J09",true,true,true,true);
        mapTileArray[113] = new objMapTile("I10",312,518,1,"I09","I11","H10","J10",true,true,true,true);
        mapTileArray[114] = new objMapTile("I11",312,557,1,"I10","I12","H11","J11",true,true,true,true);
        mapTileArray[115] = new objMapTile("I12",312,596,1,"I11","I13","H12","J12",true,true,true,true);
        mapTileArray[116] = new objMapTile("I13",312,635,1,"I12","NONE","H13","J13",true,false,true,true);
        mapTileArray[117] = new objMapTile("J01",351,167,1,"NONE","J02","I01","K01",false,true,true,true);
        mapTileArray[118] = new objMapTile("J02",351,206,1,"J01","J03","I02","K02",true,true,true,true);
        mapTileArray[119] = new objMapTile("J03",351,245,1,"J02","J04","I03","K03",true,true,true,true);
        mapTileArray[120] = new objMapTile("J04",351,284,1,"J03","J05","I04","K04",true,true,true,true);
        mapTileArray[121] = new objMapTile("J05",351,323,1,"J04","J06","I05","K05",true,true,true,true);
        mapTileArray[122] = new objMapTile("J06",351,362,1,"J05","J07","I06","K06",true,true,true,true);
        mapTileArray[123] = new objMapTile("J07",351,401,1,"J06","J08","I07","K07",true,true,true,true);
        mapTileArray[124] = new objMapTile("J08",351,440,1,"J07","J09","I08","K08",true,true,true,true);
        mapTileArray[125] = new objMapTile("J09",351,479,1,"J08","J10","I09","K09",true,true,true,true);
        mapTileArray[126] = new objMapTile("J10",351,518,1,"J09","J11","I10","K10",true,true,true,true);
        mapTileArray[127] = new objMapTile("J11",351,557,1,"J10","J12","I11","K11",true,true,true,true);
        mapTileArray[128] = new objMapTile("J12",351,596,1,"J11","J13","I12","K12",true,true,true,true);
        mapTileArray[129] = new objMapTile("J13",351,635,1,"J12","NONE","I13","K13",true,false,true,true);
        mapTileArray[130] = new objMapTile("K01",390,167,1,"NONE","K02","J01","L01",false,true,true,true);
        mapTileArray[131] = new objMapTile("K02",390,206,1,"K01","K03","J02","L02",true,true,true,true);
        mapTileArray[132] = new objMapTile("K03",390,245,1,"K02","K04","J03","L03",true,true,true,true);
        mapTileArray[133] = new objMapTile("K04",390,284,1,"K03","K05","J04","L04",true,true,true,true);
        mapTileArray[134] = new objMapTile("K05",390,323,1,"K04","K06","J05","L05",true,true,true,true);
        mapTileArray[135] = new objMapTile("K06",390,362,3,"K05","K07","J06","L06",true,true,true,true);
        mapTileArray[136] = new objMapTile("K07",390,401,3,"K06","K08","J07","L07",true,true,true,true);
        mapTileArray[137] = new objMapTile("K08",390,440,1,"K07","K09","J08","L08",true,true,true,true);
        mapTileArray[138] = new objMapTile("K09",390,479,1,"K08","K10","J09","L09",true,true,true,true);
        mapTileArray[139] = new objMapTile("K10",390,518,1,"K09","K11","J10","L10",true,true,true,true);
        mapTileArray[140] = new objMapTile("K11",390,557,1,"K10","K12","J11","L11",true,true,true,true);
        mapTileArray[141] = new objMapTile("K12",390,596,1,"K11","K13","J12","L12",true,true,true,true);
        mapTileArray[142] = new objMapTile("K13",390,635,1,"K12","NONE","J13","L13",true,false,true,true);
        mapTileArray[143] = new objMapTile("L01",429,167,1,"NONE","L02","K01","M01",false,true,true,true);
        mapTileArray[144] = new objMapTile("L02",429,206,1,"L01","L03","K02","M02",true,true,true,true);
        mapTileArray[145] = new objMapTile("L03",429,245,1,"L02","L04","K03","M03",true,true,true,true);
        mapTileArray[146] = new objMapTile("L04",429,284,1,"L03","L05","K04","M04",true,true,true,true);
        mapTileArray[147] = new objMapTile("L05",429,323,1,"L04","L06","K05","M05",true,true,true,true);
        mapTileArray[148] = new objMapTile("L06",429,362,3,"L05","L07","K06","M06",true,true,true,true);
        mapTileArray[149] = new objMapTile("L07",429,401,3,"L06","L08","K07","M07",true,true,true,true);
        mapTileArray[150] = new objMapTile("L08",429,440,1,"L07","L09","K08","M08",true,true,true,true);
        mapTileArray[151] = new objMapTile("L09",429,479,1,"L08","L10","K09","M09",true,true,true,true);
        mapTileArray[152] = new objMapTile("L10",429,518,1,"L09","L11","K10","M10",true,true,true,true);
        mapTileArray[153] = new objMapTile("L11",429,557,1,"L10","L12","K11","M11",true,true,true,true);
        mapTileArray[154] = new objMapTile("L12",429,596,1,"L11","L13","K12","M12",true,true,true,true);
        mapTileArray[155] = new objMapTile("L13",429,635,1,"L12","NONE","K13","M13",true,false,true,true);        
        mapTileArray[156] = new objMapTile("M01",467,167,1,"NONE","M02","L01","N01",false,true,true,true);
        mapTileArray[157] = new objMapTile("M02",467,206,1,"M01","M03","L02","N02",true,true,true,true);
        mapTileArray[158] = new objMapTile("M03",467,245,1,"M02","M04","L03","N03",true,true,true,true);
        mapTileArray[159] = new objMapTile("M04",467,284,1,"M03","M05","L04","N04",true,true,true,true);
        mapTileArray[160] = new objMapTile("M05",467,323,1,"M04","M06","L05","N05",true,true,true,true);
        mapTileArray[161] = new objMapTile("M06",467,362,1,"M05","M07","L06","N06",true,true,true,true);
        mapTileArray[162] = new objMapTile("M07",467,401,1,"M06","M08","L07","N07",true,true,true,true);
        mapTileArray[163] = new objMapTile("M08",467,440,1,"M07","M09","L08","N08",true,true,true,true);
        mapTileArray[164] = new objMapTile("M09",467,479,1,"M08","M10","L09","N09",true,true,true,true);
        mapTileArray[165] = new objMapTile("M10",467,518,1,"M09","M11","L10","N10",true,true,true,true);
        mapTileArray[166] = new objMapTile("M11",467,557,1,"M10","M12","L11","N11",true,true,true,true);
        mapTileArray[167] = new objMapTile("M12",467,596,1,"M11","M13","L12","N12",true,true,true,true);
        mapTileArray[168] = new objMapTile("M13",467,635,1,"M12","NONE","L13","N13",true,false,true,true);        
        mapTileArray[169] = new objMapTile("N01",507,167,1,"NONE","N02","M01","O01",false,true,true,true);
        mapTileArray[170] = new objMapTile("N02",507,206,1,"N01","N03","M02","O02",true,true,true,true);
        mapTileArray[171] = new objMapTile("N03",507,245,1,"N02","N04","M03","O03",true,true,true,true);
        mapTileArray[172] = new objMapTile("N04",507,284,1,"N03","N05","M04","O04",true,true,true,true);
        mapTileArray[173] = new objMapTile("N05",507,323,1,"N04","N06","M05","O05",true,true,true,true);
        mapTileArray[174] = new objMapTile("N06",507,362,1,"N05","N07","M06","O06",true,true,true,true);
        mapTileArray[175] = new objMapTile("N07",507,401,1,"N06","N08","M07","O07",true,true,true,true);
        mapTileArray[176] = new objMapTile("N08",507,440,1,"N07","N09","M08","O08",true,true,true,true);
        mapTileArray[177] = new objMapTile("N09",507,479,1,"N08","N10","M09","O09",true,true,true,true);
        mapTileArray[178] = new objMapTile("N10",507,518,1,"N09","N11","M10","O10",true,true,true,true);
        mapTileArray[179] = new objMapTile("N11",507,557,1,"N10","N12","M11","O11",true,true,true,true);
        mapTileArray[180] = new objMapTile("N12",507,596,1,"N11","N13","M12","O12",true,true,true,true);
        mapTileArray[181] = new objMapTile("N13",507,635,1,"N12","NONE","M13","O13",true,false,true,true);        
        mapTileArray[182] = new objMapTile("O01",546,167,1,"NONE","O02","N01","P01",false,true,true,true);
        mapTileArray[183] = new objMapTile("O02",546,206,1,"O01","O03","N02","P02",true,true,true,true);
        mapTileArray[184] = new objMapTile("O03",546,245,1,"O02","O04","N03","P03",true,true,true,true);
        mapTileArray[185] = new objMapTile("O04",546,284,1,"O03","O05","N04","P04",true,true,true,true);
        mapTileArray[186] = new objMapTile("O05",546,323,1,"O04","O06","N05","P05",true,true,true,true);
        mapTileArray[187] = new objMapTile("O06",546,362,1,"O05","O07","N06","P06",true,true,true,true);
        mapTileArray[188] = new objMapTile("O07",546,401,1,"O06","O08","N07","P07",true,true,true,true);
        mapTileArray[189] = new objMapTile("O08",546,440,1,"O07","O09","N08","P08",true,true,true,true);
        mapTileArray[190] = new objMapTile("O09",546,479,1,"O08","O10","N09","P09",true,true,true,true);
        mapTileArray[191] = new objMapTile("O10",546,518,1,"O09","O11","N10","P10",true,true,true,true);
        mapTileArray[192] = new objMapTile("O11",546,557,1,"O10","O12","N11","P11",true,true,true,true);
        mapTileArray[193] = new objMapTile("O12",546,596,1,"O11","O13","N12","P12",true,true,true,true);
        mapTileArray[194] = new objMapTile("O13",546,635,1,"O12","NONE","N13","P13",true,false,true,true);         
        mapTileArray[195] = new objMapTile("P01",585,167,1,"NONE","P02","O01","Q01",false,true,true,true);
        mapTileArray[196] = new objMapTile("P02",585,206,1,"P01","P03","O02","Q02",true,true,true,true);
        mapTileArray[197] = new objMapTile("P03",585,245,1,"P02","P04","O03","Q03",true,true,true,true);
        mapTileArray[198] = new objMapTile("P04",585,284,1,"P03","P05","O04","Q04",true,true,true,true);
        mapTileArray[199] = new objMapTile("P05",585,323,1,"P04","P06","O05","Q05",true,true,true,true);
        mapTileArray[200] = new objMapTile("P06",585,362,1,"P05","P07","O06","Q06",true,true,true,true);
        mapTileArray[201] = new objMapTile("P07",585,401,1,"P06","P08","O07","Q07",true,true,true,true);
        mapTileArray[202] = new objMapTile("P08",585,440,1,"P07","P09","O08","Q08",true,true,true,true);
        mapTileArray[203] = new objMapTile("P09",585,479,1,"P08","P10","O09","Q09",true,true,true,true);
        mapTileArray[204] = new objMapTile("P10",585,518,1,"P09","P11","O10","Q10",true,true,true,true);
        mapTileArray[205] = new objMapTile("P11",585,557,1,"P10","P12","O11","Q11",true,true,true,true);
        mapTileArray[206] = new objMapTile("P12",585,596,1,"P11","P13","O12","Q12",true,true,true,true);
        mapTileArray[207] = new objMapTile("P13",585,635,1,"P12","NONE","O13","Q13",true,false,true,true);        
        mapTileArray[208] = new objMapTile("Q01",624,167,1,"NONE","Q02","P01","R01",false,true,true,true);
        mapTileArray[209] = new objMapTile("Q02",624,206,1,"Q01","Q03","P02","R02",true,true,true,true);
        mapTileArray[210] = new objMapTile("Q03",624,245,1,"Q02","Q04","P03","R03",true,true,true,true);
        mapTileArray[211] = new objMapTile("Q04",624,284,1,"Q03","Q05","P04","R04",true,true,true,true);
        mapTileArray[212] = new objMapTile("Q05",624,323,1,"Q04","Q06","P05","R05",true,true,true,true);
        mapTileArray[213] = new objMapTile("Q06",624,362,1,"Q05","Q07","P06","R06",true,true,true,true);
        mapTileArray[214] = new objMapTile("Q07",624,401,1,"Q06","Q08","P07","R07",true,true,true,true);
        mapTileArray[215] = new objMapTile("Q08",624,440,1,"Q07","Q09","P08","R08",true,true,true,true);
        mapTileArray[216] = new objMapTile("Q09",624,479,1,"Q08","Q10","P09","R09",true,true,true,true);
        mapTileArray[217] = new objMapTile("Q10",624,518,1,"Q09","Q11","P10","R10",true,true,true,true);
        mapTileArray[218] = new objMapTile("Q11",624,557,1,"Q10","Q12","P11","R11",true,true,true,true);
        mapTileArray[219] = new objMapTile("Q12",624,596,1,"Q11","Q13","P12","R12",true,true,true,true);
        mapTileArray[220] = new objMapTile("Q13",624,635,1,"Q12","NONE","P13","R13",true,false,true,true);
        mapTileArray[221] = new objMapTile("R01",663,167,1,"NONE","R02","Q01","S01",false,true,true,true);
        mapTileArray[222] = new objMapTile("R02",663,206,1,"R01","R03","Q02","S02",true,true,true,true);
        mapTileArray[223] = new objMapTile("R03",663,245,1,"R02","R04","Q03","S03",true,true,true,true);
        mapTileArray[224] = new objMapTile("R04",663,284,1,"R03","R05","Q04","S04",true,true,true,true);
        mapTileArray[225] = new objMapTile("R05",663,323,1,"R04","R06","Q05","S05",true,true,true,true);
        mapTileArray[226] = new objMapTile("R06",663,362,1,"R05","R07","Q06","S06",true,true,true,true);
        mapTileArray[227] = new objMapTile("R07",663,401,1,"R06","R08","Q07","S07",true,true,true,true);
        mapTileArray[228] = new objMapTile("R08",663,440,1,"R07","R09","Q08","S08",true,true,true,true);
        mapTileArray[229] = new objMapTile("R09",663,479,1,"R08","R10","Q09","S09",true,true,true,true);
        mapTileArray[230] = new objMapTile("R10",663,518,1,"R09","R11","Q10","S10",true,true,true,true);
        mapTileArray[231] = new objMapTile("R11",663,557,1,"R10","R12","Q11","S11",true,true,true,true);
        mapTileArray[232] = new objMapTile("R12",663,596,1,"R11","R13","Q12","S12",true,true,true,true);
        mapTileArray[233] = new objMapTile("R13",663,635,1,"R12","NONE","Q13","S13",true,false,true,true);
        mapTileArray[234] = new objMapTile("S01",702,167,1,"NONE","S02","R01","T01",false,true,true,true);
        mapTileArray[235] = new objMapTile("S02",702,206,1,"S01","S03","R02","T02",true,true,true,true);
        mapTileArray[236] = new objMapTile("S03",702,245,1,"S02","S04","R03","T03",true,true,true,true);
        mapTileArray[237] = new objMapTile("S04",702,284,1,"S03","S05","R04","T04",true,true,true,true);
        mapTileArray[238] = new objMapTile("S05",702,323,1,"S04","S06","R05","T05",true,true,true,true);
        mapTileArray[239] = new objMapTile("S06",702,362,1,"S05","S07","R06","T06",true,true,true,true);
        mapTileArray[240] = new objMapTile("S07",702,401,1,"S06","S08","R07","T07",true,true,true,true);
        mapTileArray[241] = new objMapTile("S08",702,440,1,"S07","S09","R08","T08",true,true,true,true);
        mapTileArray[242] = new objMapTile("S09",702,479,1,"S08","S10","R09","T09",true,true,true,true);
        mapTileArray[243] = new objMapTile("S10",702,518,1,"S09","S11","R10","T10",true,true,true,true);
        mapTileArray[244] = new objMapTile("S11",702,557,1,"S10","S12","R11","T11",true,true,true,true);
        mapTileArray[245] = new objMapTile("S12",702,596,1,"S11","S13","R12","T12",true,true,true,true);
        mapTileArray[246] = new objMapTile("S13",702,635,1,"S12","NONE","R13","T13",true,false,true,true);       
        mapTileArray[247] = new objMapTile("T01",740,167,1,"NONE","T02","S01","U01",false,true,true,true);
        mapTileArray[248] = new objMapTile("T02",740,206,1,"T01","T03","S02","U02",true,true,true,true);
        mapTileArray[249] = new objMapTile("T03",740,245,1,"T02","T04","S03","U03",true,true,true,true);
        mapTileArray[250] = new objMapTile("T04",740,284,1,"T03","T05","S04","U04",true,true,true,true);
        mapTileArray[251] = new objMapTile("T05",740,323,1,"T04","T06","S05","U05",true,true,true,true);
        mapTileArray[252] = new objMapTile("T06",740,362,1,"T05","T07","S06","U06",true,true,true,true);
        mapTileArray[253] = new objMapTile("T07",740,401,1,"T06","T08","S07","U07",true,true,true,true);
        mapTileArray[254] = new objMapTile("T08",740,440,1,"T07","T09","S08","U08",true,true,true,true);
        mapTileArray[255] = new objMapTile("T09",740,479,1,"T08","T10","S09","U09",true,true,true,true);
        mapTileArray[256] = new objMapTile("T10",740,518,1,"T09","T11","S10","U10",true,true,true,true);
        mapTileArray[257] = new objMapTile("T11",740,557,1,"T10","T12","S11","U11",true,true,true,true);
        mapTileArray[258] = new objMapTile("T12",740,596,1,"T11","T13","S12","U12",true,true,true,true);
        mapTileArray[259] = new objMapTile("T13",740,635,1,"T12","NONE","S13","U13",true,false,true,true);
        mapTileArray[260] = new objMapTile("U01",780,167,1,"NONE","U02","T01","V01",false,true,true,true);
        mapTileArray[261] = new objMapTile("U02",780,206,1,"U01","U03","T02","V02",true,true,true,true);
        mapTileArray[262] = new objMapTile("U03",780,245,1,"U02","U04","T03","V03",true,true,true,true);
        mapTileArray[263] = new objMapTile("U04",780,284,1,"U03","U05","T04","V04",true,true,true,true);
        mapTileArray[264] = new objMapTile("U05",780,323,1,"U04","U06","T05","V05",true,true,true,true);
        mapTileArray[265] = new objMapTile("U06",780,362,1,"U05","U07","T06","V06",true,true,true,true);
        mapTileArray[266] = new objMapTile("U07",780,401,1,"U06","U08","T07","V07",true,true,true,true);
        mapTileArray[267] = new objMapTile("U08",780,440,1,"U07","U09","T08","V08",true,true,true,true);
        mapTileArray[268] = new objMapTile("U09",780,479,1,"U08","U10","T09","V09",true,true,true,true);
        mapTileArray[269] = new objMapTile("U10",780,518,1,"U09","U11","T10","V10",true,true,true,true);
        mapTileArray[270] = new objMapTile("U11",780,557,1,"U10","U12","T11","V11",true,true,true,true);
        mapTileArray[271] = new objMapTile("U12",780,596,1,"U11","U13","T12","V12",true,true,true,true);
        mapTileArray[272] = new objMapTile("U13",780,635,1,"U12","NONE","T13","NONE",true,false,true,false);        
        mapTileArray[273] = new objMapTile("V01",819,167,1,"NONE","V02","U01","W01",false,true,true,true);
        mapTileArray[274] = new objMapTile("V02",819,206,1,"V01","V03","U02","W02",true,true,true,true);
        mapTileArray[275] = new objMapTile("V03",819,245,2,"V02","V04","U03","W03",true,true,true,true);
        mapTileArray[276] = new objMapTile("V04",819,284,2,"V03","V05","U04","W04",true,true,true,true);
        mapTileArray[277] = new objMapTile("V05",819,323,1,"V04","V06","U05","W05",true,true,true,true);
        mapTileArray[278] = new objMapTile("V06",819,362,1,"V05","V07","U06","W06",true,true,true,true);
        mapTileArray[279] = new objMapTile("V07",819,401,1,"V06","V08","U07","W07",true,true,true,true);
        mapTileArray[280] = new objMapTile("V08",819,440,1,"V07","V09","U08","W08",true,true,true,true);
        mapTileArray[281] = new objMapTile("V09",819,479,1,"V08","V10","U09","W09",true,true,true,true);
        mapTileArray[282] = new objMapTile("V10",819,518,1,"V09","V11","U10","W10",true,true,true,true);
        mapTileArray[283] = new objMapTile("V11",819,557,1,"V10","V12","U11","W11",true,true,true,true);
        mapTileArray[284] = new objMapTile("V12",819,596,1,"V11","NONE","U12","W12",true,false,true,true);        
        mapTileArray[285] = new objMapTile("W01",858,167,1,"NONE","W02","V01","NONE",false,true,true,false);
        mapTileArray[286] = new objMapTile("W02",858,206,1,"W01","W03","V02","X02",true,true,true,true);
        mapTileArray[287] = new objMapTile("W03",858,245,2,"W02","W04","V03","X03",true,true,true,true);
        mapTileArray[288] = new objMapTile("W04",858,284,2,"W03","W05","V04","X04",true,true,true,true);
        mapTileArray[289] = new objMapTile("W05",858,323,1,"W04","W06","V05","X05",true,true,true,true);
        mapTileArray[290] = new objMapTile("W06",858,362,1,"W05","W07","V06","X06",true,true,true,true);
        mapTileArray[291] = new objMapTile("W07",858,401,1,"W06","W08","V07","X07",true,true,true,true);
        mapTileArray[292] = new objMapTile("W08",858,440,1,"W07","W09","V08","NONE",true,true,true,false);
        mapTileArray[293] = new objMapTile("W09",858,479,1,"W08","W10","V09","X09",true,true,true,true);
        mapTileArray[294] = new objMapTile("W10",858,518,1,"W09","W11","V10","X10",true,true,true,true);
        mapTileArray[295] = new objMapTile("W11",858,557,1,"W10","W12","V11","X11",true,true,true,true);
        mapTileArray[296] = new objMapTile("W12",858,596,1,"W11","NONE","V12","X12",true,false,true,true);
        mapTileArray[297] = new objMapTile("X02",896,206,1,"NONE","X03","W02","NONE",false,true,true,false);
        mapTileArray[298] = new objMapTile("X03",896,245,1,"X02","X04","W03","NONE",true,true,true,false);
        mapTileArray[299] = new objMapTile("X04",896,284,1,"X03","X05","W04","Y04",true,true,true,true);
        mapTileArray[300] = new objMapTile("X05",896,323,1,"X04","X06","W05","Y05",true,true,true,true);
        mapTileArray[301] = new objMapTile("X06",896,362,1,"X05","X07","W06","NONE",true,true,true,false);
        mapTileArray[302] = new objMapTile("X07",896,401,1,"X06","NONE","W07","NONE",true,false,true,false);
        mapTileArray[303] = new objMapTile("X09",896,479,1,"NONE","X10","W09","NONE",false,true,true,false);
        mapTileArray[304] = new objMapTile("X10",896,518,1,"X09","X11","W10","NONE",true,true,true,false);
        mapTileArray[305] = new objMapTile("X11",896,557,1,"X10","X12","W11","Y11",true,true,true,true);
        mapTileArray[306] = new objMapTile("X12",896,596,1,"X11","NONE","W12","NONE",true,false,true,false);
        mapTileArray[307] = new objMapTile("Y04",934,284,1,"NONE","Y05","X04","NONE",false,true,true,false);
        mapTileArray[308] = new objMapTile("Y05",934,323,1,"Y04","NONE","X05","NONE",true,false,true,false);
        mapTileArray[309] = new objMapTile("Y11",934,557,1,"NONE","NONE","X11","NONE",false,false,true,false);
        mapTileArray[310] = new objMapTile("V13",819,635,6,"V12","NONE","U13","NONE",true,false,true,false);
        return mapTileArray;
    }
    
} //END OF panelGameMap