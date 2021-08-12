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

public class panelGame4 extends JPanel implements MouseListener, ActionListener {

    panelGameMap p07PanelGameMap;
    objPlayer player;
    Timer game4Timer,game4CorrectTimer,game4OverTimer;
    int game4TimerDelay,game4CorrectTimerDelay,game4OverTimerDelay;
    
    //************************************************************************
    //START OF GAME CODE
    XML_240 x2;
    int questionNumber,timesQ1Answered,timesQ2Answered,timesQ3Answered,
        timesQ4Answered,consecutiveAnswersCorrect;
    Random random = new Random();
    
    JButton startGame4,endGame4,jbQuestion1,jbQuestion2,jbQuestion3,jbQuestion4;
    Boolean wasQ1Asked,wasQ2Asked,wasQ3Asked,wasQ4Asked;
    JTextArea questionBox;
    
    String  question1, question2, question3, question4,
            question1answer1, question1answer2, question1answer3,
            question2answer1, question2answer2, question2answer3, 
            question3answer1, question3answer2, question3answer3,
            question4answer1, question4answer2, question4answer3,
            question1correctAnswer, question2correctAnswer, question3correctAnswer, 
            question4correctAnswer;
    //END OF GAME CODE
    //************************************************************************
    
    ImageIcon game1BackgroundPSU = new ImageIcon("images/game4BackgroundPsu.jpg"),
        game1BackgroundWU = new ImageIcon("images/game4BackgroundWu.jpg"),
        game1Background8BIT = new ImageIcon("images/game4Background8bit.jpg"),
        game1BackgroundDiaper = new ImageIcon("images/game4BackgroundDiaper.jpg"),
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
    
    Icon    game4OverBgImg = new ImageIcon("images/gameOverlay3.png"),
            intro4OverlayBgImg = new ImageIcon("images/gameOverlay1.png");
    
    JLabel  intro4OverlayBg,intro4OverlayTxt1,intro4OverlayTxt2,
            game4OverTxt1,game4OverTxt2,game4OverTxt3;

    public panelGame4(objPlayer player)
    {
        super();
        this.player = player;
        setLayout(null);
        startGame4Timer(); //STARTS THE GAME 4 TIMER
        x2 = new XML_240(); //BUILD XML READER
        setupIntro4Overlay(); //BUILD GAME INTRO OVERLAY WITH START GAME BUTTON
        initializeGame4Questions(); //BUILD EMPTY QUESTIONS TO BE POPULATED VIA XML
        determineQuestionFile(); //SELECT XML DOCUMENT BASED UPON GAME THEME
        buildGame4Questions(); //POPULATE QUESTION VARIABLES USING XML DOC
        buildGame4BoxesAndButtons(); //BUILDS THE JBUTTON ANSWERS AND TEXTBOX
    }

    public void startGame4Timer(){
        //START GAME TIMER
        game4TimerDelay = 1000;
        game4Timer = new Timer(game4TimerDelay, this);
        game4Timer.start();
        
        //SET TIMER FOR WHEN QUESTIONS ARE ANSWERED CORRECTLY
        game4CorrectTimer = new Timer(game4TimerDelay, this);
        game4CorrectTimerDelay = 3; //SECOND DELAY WHEN QUESTIONS ANSWERED CORRECTLY
        
        //SET TIMER FOR GAMEOVER OVERLAY
        game4OverTimer = new Timer(game4TimerDelay, this);
        game4OverTimerDelay = 6; //SECOND DELAY BEFORE GAME RETURNS TO GAME MAP
    }
    
    //SETUP INTRODUCTION OVERLAY AND GAME START BUTTON  
    public void setupIntro4Overlay(){
        intro4OverlayBg = new JLabel(intro4OverlayBgImg);
        intro4OverlayBg.setBounds(190,225,700,236);
        intro4OverlayBg.setBorder(BorderFactory.createEmptyBorder());
        intro4OverlayBg.setVisible(true);
        
        intro4OverlayTxt1 = new JLabel("<html><center><font color=#FFFF00 size=5>"
            + "You Have Made It To The World Campus!");
        Font font = intro4OverlayTxt1.getFont().deriveFont(Font.BOLD);
        intro4OverlayTxt1.setFont(font);
        intro4OverlayTxt1.setBounds(365,210,680,100); //LEFT,TOP,WIDTH,HEIGHT
        intro4OverlayTxt1.setBorder(BorderFactory.createEmptyBorder());
        intro4OverlayTxt1.setVisible(true);
        
        intro4OverlayTxt2 = new JLabel("<html><center><font color=#FFFFFF size=5>"
            + "Four questions remain, if you answer them correctly, then the curse of Pennsylvania will be lifted!  Good luck Nittany!!!");
        intro4OverlayTxt2.setBounds(201,225,680,200); //LEFT,TOP,WIDTH,HEIGHT
        intro4OverlayTxt2.setBorder(BorderFactory.createEmptyBorder());
        intro4OverlayTxt2.setVisible(true);
   
        startGame4 = new JButton("Click Here to Start the Game!");
        startGame4.setBounds(395,385,250,40); //LEFT,TOP,WIDTH,HEIGHT
        startGame4.setBorder(BorderFactory.createEmptyBorder());
        startGame4.setVisible(true);
        
        add(startGame4);
        add(intro4OverlayTxt1);
        add(intro4OverlayTxt2);
        add(intro4OverlayBg);
        startGame4.addMouseListener(this);
    }

    // SETUP GAME OVER OVERLAY      
    public void game4OverOverlay(){
        game4OverTxt1 = new JLabel("<html><center><font color=#ffd300 size=7>"
                + "The Final Game Is Over!");
        Font font2 = game4OverTxt1.getFont().deriveFont(Font.BOLD);
        game4OverTxt1.setFont(font2);
        game4OverTxt1.setBounds(340,150,560,60); //LEFT,TOP,WIDTH,HEIGHT
        game4OverTxt1.setBorder(BorderFactory.createEmptyBorder());
        game4OverTxt1.setVisible(true);
        
        game4OverTxt2 = new JLabel("<html><center><font color=#FFFFFF size=5>"
                + "You have scored " + player.game4Score + " points!");
        game4OverTxt2.setFont(font2);
        game4OverTxt2.setBounds(420,190,560,60); //LEFT,TOP,WIDTH,HEIGHT
        game4OverTxt2.setBorder(BorderFactory.createEmptyBorder());
        game4OverTxt2.setVisible(true);
        
        game4OverTxt3 = new JLabel("<html><center><font color=#FFFFFF size=4>"
                + "The Curse of Pennsylvania Island has been lifted!  You have triumphed amongst all challenges and restored the Pennsylvanians confidence in the mighty Nittany.  Go forth from whence you came and hope to have achieved a high score and be placed in the history books of humanity!");
        game4OverTxt3.setFont(font2);
        game4OverTxt3.setBounds(250,160,580,250); //LEFT,TOP,WIDTH,HEIGHT
        game4OverTxt3.setBorder(BorderFactory.createEmptyBorder());
        game4OverTxt3.setVisible(true);
        
        add(game4OverTxt1);
        add(game4OverTxt2);
        add(game4OverTxt3);
    }
    
    public void initializeGame4Questions(){
        question1 = "";
        question1correctAnswer = "";
        question1answer1 = "";
        question1answer2 = "";
        question1answer3 = "";
        question2 = "";
        question2answer1 = "";
        question2correctAnswer = "";
        question2answer2 = "";
        question2answer3 = "";
        question3 = "";
        question3answer1 = "";
        question3answer2 = "";
        question3correctAnswer = "";
        question3answer3 = "";
        question4 = "";
        question4answer1 = "";
        question4answer2 = "";
        question4answer3 = "";
        question4correctAnswer = "";
        
        wasQ1Asked = false; //HAS QUESTION1 BEEN ASKED?
        wasQ2Asked = false; //HAS QUESTION2 BEEN ASKED?
        wasQ3Asked = false; //HAS QUESTION3 BEEN ASKED?
        wasQ4Asked = false; //HAS QUESTION4 BEEN ASKED?
        
        timesQ1Answered = 0; //HOW MANY TIMES HAS Q1 BEEN ANSWERED CORRECTLY?
        timesQ2Answered = 0; //HOW MANY TIMES HAS Q2 BEEN ANSWERED CORRECTLY?
        timesQ3Answered = 0; //HOW MANY TIMES HAS Q3 BEEN ANSWERED CORRECTLY?
        timesQ4Answered = 0; //HOW MANY TIMES HAS Q4 BEEN ANSWERED CORRECTLY?
        
        consecutiveAnswersCorrect = 0;
    }
    
    public void determineQuestionFile(){
        //DRAW GAME BACKGROUND IMAGE
        if(player.gameTheme == 1){x2.openReaderXML("XML_sports.xml");}
        else if(player.gameTheme == 2){x2.openReaderXML("XML_movies.xml");}
        else if(player.gameTheme == 3){x2.openReaderXML("XML_history.xml");}
        else if(player.gameTheme == 4){x2.openReaderXML("XML_science.xml");}
    }
    
    public void buildGame4Questions(){
        question1 = (String) x2.ReadObject();
        question1correctAnswer = (String) x2.ReadObject();
        question1answer1 = (String) x2.ReadObject();
        question1answer2 = (String) x2.ReadObject();
        question1answer3 = (String) x2.ReadObject();
        question2 = (String) x2.ReadObject();
        question2answer1 = (String) x2.ReadObject();
        question2correctAnswer = (String) x2.ReadObject();
        question2answer2 = (String) x2.ReadObject();
        question2answer3 = (String) x2.ReadObject();
        question3 = (String) x2.ReadObject();
        question3answer1 = (String) x2.ReadObject();
        question3answer2 = (String) x2.ReadObject();
        question3correctAnswer = (String) x2.ReadObject();
        question3answer3 = (String) x2.ReadObject();
        question4 = (String) x2.ReadObject();
        question4answer1 = (String) x2.ReadObject();
        question4answer2 = (String) x2.ReadObject();
        question4answer3 = (String) x2.ReadObject();
        question4correctAnswer = (String) x2.ReadObject();
        x2.closeReaderXML();
    }
    
    public void buildGame4BoxesAndButtons(){
        questionBox = new JTextArea();
        questionBox.setBounds(250,170,575,175);
        questionBox.setForeground(Color.white);
        questionBox.setBackground(Color.black);
        Font f1 = new Font("Gothic", Font.BOLD, 20);
        questionBox.setFont(f1);
        questionBox.setOpaque(true);
        questionBox.setLineWrap(true);
        questionBox.setWrapStyleWord(true);
        questionBox.setVisible(true);
        add(questionBox);
        jbQuestion1 = new JButton();
        jbQuestion1.setBounds(325,400,420,55);
        jbQuestion1.addActionListener(this);
        jbQuestion1.addMouseListener(this);
        jbQuestion2 = new JButton();
        jbQuestion2.setBounds(325,475,420,55);
        jbQuestion2.addActionListener(this);
        jbQuestion2.addMouseListener(this);
        jbQuestion3 = new JButton();
        jbQuestion3.setBounds(325,550,420,55);
        jbQuestion3.addActionListener(this);
        jbQuestion3.addMouseListener(this);
        jbQuestion4 = new JButton();
        jbQuestion4.setBounds(325,625,420,55);
        jbQuestion4.addActionListener(this);
        jbQuestion4.addMouseListener(this);
        endGame4 = new JButton("Click Here to Return to Game Map!");
        endGame4.setBounds(330,425,420,155);
        endGame4.addActionListener(this);
        endGame4.addMouseListener(this);
        endGame4.setVisible(true);
    }
    
    public void setQuestionAndAnswer(){
        questionNumber = random.nextInt(4); //CREATE RANDOM NUM BETWEEN 0 AND 3

        //IF RANDOM NUMBER EQUALS ZERO && Q1 HASNT BEEN ASKED
        if (questionNumber == 0 && wasQ1Asked == false)
        {
            questionBox.setText(question1);
            jbQuestion1.setText(question1correctAnswer);
            jbQuestion2.setText(question1answer1);
            jbQuestion3.setText(question1answer2);
            jbQuestion4.setText(question1answer3);
            wasQ1Asked = true;
        }
        
        //ELSE IF RANDOM NUMBER EQUALS ONE && Q2 HASNT BEEN ASKED
        else if (questionNumber == 1 && wasQ2Asked == false)
        {
            questionBox.setText(question2);
            jbQuestion1.setText(question2answer1);
            jbQuestion2.setText(question2correctAnswer);
            jbQuestion3.setText(question2answer2);
            jbQuestion4.setText(question2answer3);
            wasQ2Asked = true;
        }
        
        //ELSE IF RANDOM NUMBER EQUALS TWO && Q3 HASNT BEEN ASKED
        else if (questionNumber == 2 && wasQ3Asked != true)
        {
            questionBox.setText(question3);
            jbQuestion1.setText(question3answer1);
            jbQuestion2.setText(question3answer2);
            jbQuestion3.setText(question3correctAnswer);
            jbQuestion4.setText(question3answer3);
            wasQ3Asked = true;
        }
        
        //ELSE IF RANDOM NUMBER EQUALS THREE && Q4 HASNT BEEN ASKED
        else if (questionNumber == 3 && wasQ4Asked == false)
        {
            questionBox.setText(question4);
            jbQuestion1.setText(question4answer1);
            jbQuestion2.setText(question4answer2);
            jbQuestion3.setText(question4answer3);
            jbQuestion4.setText(question4correctAnswer);
            wasQ4Asked = true;
        }
        
        //ELSE IF ALL QUESTIONS HAVE BEEN ASKED, BREAK LOOP AND SET GAME OVER OVERLAY AND TIMER
        else if (wasQ1Asked && wasQ2Asked && wasQ3Asked && wasQ4Asked){
            game4OverTimer.start();
            questionNumber=999;
            remove(questionBox);
            remove(jbQuestion1);
            remove(jbQuestion2);
            remove(jbQuestion3);
            remove(jbQuestion4);
            game4OverOverlay(); //ADD GAME OVER OVERLAY
        }
        
        //ELSE RESTART THE LOOP UNTIL ALL QUESTIONS GET ASKED
        else {setQuestionAndAnswer();}
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        //DRAW GAME BACKGROUND IMAGE
        if(player.gameTheme == 1){g.drawImage(game1PsuBg, 0, 0, this);}
        else if(player.gameTheme == 2){g.drawImage(game1WuBg, 0, 0, this);}
        else if(player.gameTheme == 3){g.drawImage(game18BitBg, 0, 0, this);}
        else if(player.gameTheme == 4){g.drawImage(game1DiaperBg, 0, 0, this);}
        
        //DRAW TOP OVERLAY BACKGROUND
        g.setColor(new Color(0, 0, 0, 96)); //SET COLOR TO BLACK W/96% OPACITY
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
        g.drawString("Penn State University World Campus",90,44);
        g.drawString("Timer: ", 580, 44);
        g.drawString("Score: ", 740, 44);
        g.setFont(f2);
        
        //DRAW GAME TIME AND SCORE
        g.setFont(f1);
        g.setColor(Color.YELLOW);
        g.drawString(String.format( "%-10d", player.gameTime + player.game4Time ), 655, 44); //ALIGN LEFT
        g.drawString(String.format( "%010d", player.gameScore + player.game4Score ), 815, 44); //PADDING 10 DIGITS W/ZEROS
    }
    
    //==========================================================================
    // MOUSE LISTENER
    public void mouseClicked(MouseEvent e){ 
        if (e.getComponent()==startGame4){
            remove(startGame4);
            remove(intro4OverlayTxt1);
            remove(intro4OverlayTxt2);
            remove(intro4OverlayBg);
            add(jbQuestion1);
            add(jbQuestion2);
            add(jbQuestion3);
            add(jbQuestion4);
            setQuestionAndAnswer(); //STARTS THE GAME
        }
        if (e.getComponent()==endGame4){
            game4Timer.stop(); //STOP THIS MINI-GAME TIMER
            player.gameCampusWCPlayed = true; //SET FLAG THAT THIS GAME HAS BEEN PLAYED
            player.currentMapTile = "F11"; //SET NEW POSITION FOR PLAYER ON MAIN MAP
            player.gameScore = player.gameScore + player.game4Score; //UPDATE SCORE TOTAL
            player.gameTime = player.gameTime + player.game4Time; //UPDATE GAME TIME TOTAL
            removeAll();
            p07PanelGameMap = new panelGameMap(player);
            p07PanelGameMap.setBounds(0,0,1024,768);
            add(p07PanelGameMap);
            validate();
            repaint();
        }
    }
    public void mouseEntered(MouseEvent e) {
        if (e.getComponent()==startGame4){
            startGame4.setCursor(new Cursor(Cursor.HAND_CURSOR));}
        if (e.getComponent()==endGame4){
            endGame4.setCursor(new Cursor(Cursor.HAND_CURSOR));}
        if (e.getComponent()==jbQuestion1){
            jbQuestion1.setCursor(new Cursor(Cursor.HAND_CURSOR));}
        if (e.getComponent()==jbQuestion2){
            jbQuestion2.setCursor(new Cursor(Cursor.HAND_CURSOR));}
        if (e.getComponent()==jbQuestion3){
            jbQuestion3.setCursor(new Cursor(Cursor.HAND_CURSOR));}
        if (e.getComponent()==jbQuestion4){
            jbQuestion4.setCursor(new Cursor(Cursor.HAND_CURSOR));}
    }
    public void mouseExited(MouseEvent e) { }
    public void mousePressed(MouseEvent e) { }
    public void mouseReleased(MouseEvent e) { }
    
    //==========================================================================
    // SET ACTION PERFORMED LISTENERS (GAME TIMERS AND BUTTONS)
    public void actionPerformed(ActionEvent event){
        Object obj = event.getSource();
        String choice = event.getActionCommand();
	
        //DO ON EACH TIMER TICK
        if (obj == game4Timer){
            player.game4Time = player.game4Time + 1; //UPDATE GAME TIMER
            repaint();}
        
        //CORRECT ANSWER SELECTED TIMER
        if (obj == game4CorrectTimer){
            game4CorrectTimerDelay = game4CorrectTimerDelay - 1; //UPDATE GAME TIMER
            if(game4CorrectTimerDelay == 0){
                game4CorrectTimer.stop();
                game4CorrectTimerDelay = 3;
                jbQuestion1.setBackground(new JButton().getBackground()); //RESET BUTTON BGCOLOR
                jbQuestion2.setBackground(new JButton().getBackground()); //RESET BUTTON BGCOLOR
                jbQuestion3.setBackground(new JButton().getBackground()); //RESET BUTTON BGCOLOR
                jbQuestion4.setBackground(new JButton().getBackground()); //RESET BUTTON BGCOLOR
                setQuestionAndAnswer();
            }
        }
        
        //CORRECT ANSWER FOR QUESTION #1 TRIGGERED
        if (obj == jbQuestion1 && questionNumber == 0){
            jbQuestion1.setText("Correct!");
            jbQuestion1.setBackground(Color.green);
            timesQ1Answered = timesQ1Answered + 1;
            if(timesQ1Answered == 1){ //DO ONLY IF THIS HAS BEEN ANSWERED ONE TIME
                player.game4Score = player.game4Score +2500;
                if(consecutiveAnswersCorrect==1){
                    if(player.character==1){player.game4Score = player.game4Score +2500;}
                    else if(player.character==2){player.game4Score = player.game4Score +2000;}
                    else if(player.character==3){player.game4Score = player.game4Score +1500;}
                }
                else if(consecutiveAnswersCorrect==2){
                    if(player.character==1){player.game4Score = player.game4Score +7500;}
                    else if(player.character==2){player.game4Score = player.game4Score +7000;}
                    else if(player.character==3){player.game4Score = player.game4Score +5000;}
                }
                else if(consecutiveAnswersCorrect==3){
                    if(player.character==1){player.game4Score = player.game4Score +20000;}
                    else if(player.character==2){player.game4Score = player.game4Score +19000;}
                    else if(player.character==3){player.game4Score = player.game4Score +18000;}
                }
                consecutiveAnswersCorrect = consecutiveAnswersCorrect + 1;
            }
            game4CorrectTimer.start(); //START CORRECT ANSWER TIMER
        }
        
        //CORRECT ANSWER FOR QUESTION #2 TRIGGERED
        if (obj == jbQuestion2 && questionNumber == 1){
            jbQuestion2.setText("Correct!");
            jbQuestion2.setBackground(Color.green);
            timesQ2Answered = timesQ2Answered + 1;
            if(timesQ2Answered == 1){
                player.game4Score = player.game4Score +2500;
                if(consecutiveAnswersCorrect==1){
                    if(player.character==1){player.game4Score = player.game4Score +2500;}
                    else if(player.character==2){player.game4Score = player.game4Score +2000;}
                    else if(player.character==3){player.game4Score = player.game4Score +1500;}
                }
                else if(consecutiveAnswersCorrect==2){
                    if(player.character==1){player.game4Score = player.game4Score +7500;}
                    else if(player.character==2){player.game4Score = player.game4Score +7000;}
                    else if(player.character==3){player.game4Score = player.game4Score +5000;}
                }
                else if(consecutiveAnswersCorrect==3){
                    if(player.character==1){player.game4Score = player.game4Score +20000;}
                    else if(player.character==2){player.game4Score = player.game4Score +19000;}
                    else if(player.character==3){player.game4Score = player.game4Score +18000;}
                }
                consecutiveAnswersCorrect = consecutiveAnswersCorrect + 1;
            }
            game4CorrectTimer.start(); //START CORRECT ANSWER TIMER
        }
        
        //CORRECT ANSWER FOR QUESTION #3 TRIGGERED
        if (obj == jbQuestion3 && questionNumber == 2){
            jbQuestion3.setText("Correct!");
            jbQuestion3.setBackground(Color.green);
            timesQ3Answered = timesQ3Answered + 1;
            if(timesQ3Answered == 1){
                player.game4Score = player.game4Score +2500;
                if(consecutiveAnswersCorrect==1){
                    if(player.character==1){player.game4Score = player.game4Score +2500;}
                    else if(player.character==2){player.game4Score = player.game4Score +2000;}
                    else if(player.character==3){player.game4Score = player.game4Score +1500;}
                }
                else if(consecutiveAnswersCorrect==2){
                    if(player.character==1){player.game4Score = player.game4Score +7500;}
                    else if(player.character==2){player.game4Score = player.game4Score +7000;}
                    else if(player.character==3){player.game4Score = player.game4Score +5000;}
                }
                else if(consecutiveAnswersCorrect==3){
                    if(player.character==1){player.game4Score = player.game4Score +20000;}
                    else if(player.character==2){player.game4Score = player.game4Score +19000;}
                    else if(player.character==3){player.game4Score = player.game4Score +18000;}
                }
                consecutiveAnswersCorrect = consecutiveAnswersCorrect + 1;
            }
            game4CorrectTimer.start(); //START CORRECT ANSWER TIMER
        }
        
        //CORRECT ANSWER FOR QUESTION #4 TRIGGERED
        if (obj == jbQuestion4 && questionNumber == 3){
            jbQuestion4.setText("Correct!");
            jbQuestion4.setBackground(Color.green);
            timesQ4Answered = timesQ4Answered + 1;
            if(timesQ4Answered == 1){
                player.game4Score = player.game4Score +2500;
                if(consecutiveAnswersCorrect==1){
                    if(player.character==1){player.game4Score = player.game4Score +2500;}
                    else if(player.character==2){player.game4Score = player.game4Score +2000;}
                    else if(player.character==3){player.game4Score = player.game4Score +1500;}
                }
                else if(consecutiveAnswersCorrect==2){
                    if(player.character==1){player.game4Score = player.game4Score +7500;}
                    else if(player.character==2){player.game4Score = player.game4Score +7000;}
                    else if(player.character==3){player.game4Score = player.game4Score +5000;}
                }
                else if(consecutiveAnswersCorrect==3){
                    if(player.character==1){player.game4Score = player.game4Score +20000;}
                    else if(player.character==000000..............choice.2){player.game4Score = player.game4Score +19000;}
                    else if(player.character==3){player.game4Score = player.game4Score +18000;}
                }
                consecutiveAnswersCorrect = consecutiveAnswersCorrect + 1;
            }
            game4CorrectTimer.start(); //START CORRECT ANSWER TIMER
        }     
        
        //INCORRECT ANSWER FOR QUESTION #1 TRIGGERED
        if (obj == jbQuestion1 && questionNumber != 0){
            jbQuestion1.setBackground(Color.red);
            consecutiveAnswersCorrect = 0;
        }
        
        //INCORRECT ANSWER FOR QUESTION #2 TRIGGERED
        if (obj == jbQuestion2 && questionNumber != 1){
            jbQuestion2.setBackground(Color.red);
            consecutiveAnswersCorrect = 0;
        }
    
        //INCORRECT ANSWER FOR QUESTION #3 TRIGGERED
        if (obj == jbQuestion3 && questionNumber != 2){
            jbQuestion3.setBackground(Color.red);
            consecutiveAnswersCorrect = 0;
        }
        
        //INCORRECT ANSWER FOR QUESTION #4 TRIGGERED
        if (obj == jbQuestion4 && questionNumber != 3){
            jbQuestion4.setBackground(Color.red);
            consecutiveAnswersCorrect = 0;
        }

        //WHEN GAME OVER COUNTDOWN TIMER IS ACTIVATED
        if (obj == game4OverTimer){
            game4OverTimerDelay = game4OverTimerDelay - 1;
            if(game4OverTimerDelay == 0){
                game4OverTimer.stop();
                add(endGame4);
            }
        } 
    }
}