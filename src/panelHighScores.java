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
import java.util.*;

public class panelHighScores extends JPanel {

    XML_240 xHighScoresRead;
    JButton b1;
    
    panelGameMap p07PanelGameMap;
    objPlayer player;
    
    ImageIcon panelHighScores1 = new ImageIcon("images/background1.jpg");
    ImageIcon panelHighScores2 = new ImageIcon("images/gameOverBackground1.png");
    ImageIcon panelHighScores3 = new ImageIcon("images/headerHighScores.png");
    ImageIcon panelHighScores4 = new ImageIcon("images/gameOverTopTenScores.png");
    ImageIcon panelHighScores5 = new ImageIcon("images/gameOverScoreHeader.png");
    ImageIcon panelHighScores6 = new ImageIcon("images/gameOverScoreRankings.png");
    
    Image highScoresBackground1 = panelHighScores1.getImage();
    Image highScoresBackground2 = panelHighScores2.getImage();
    Image highScoresTitle1 = panelHighScores3.getImage();
    Image highScoresTitle2 = panelHighScores4.getImage();
    Image highScoresHeader1 = panelHighScores5.getImage();
    Image highScoresHeader2 = panelHighScores6.getImage();
    
    //IMPORTED RAW SCORE STRINGS
    String  rawHighScores1,rawHighScores2,rawHighScores3,rawHighScores4,rawHighScores5,rawHighScores6,
            rawHighScores7,rawHighScores8,rawHighScores9,rawHighScores10,scoreToParse,lastScore;
    
    //RAW SCORE STRINGS SPLIT INTO ARRAYS
    String[]    parsedHighScores,parsedHighScores1,parsedHighScores2,parsedHighScores3,parsedHighScores4,
                parsedHighScores5,parsedHighScores6,parsedHighScores7,parsedHighScores8,parsedHighScores9,
                parsedHighScores10,scoreToConvert,newScoreDataRaw;
 
    String  highScoreCharacterNameToConvert,highScoreCharacterRank1,highScoreCharacterRank2,
            highScoreCharacterRank3,highScoreCharacterRank4,highScoreCharacterRank5,
            highScoreCharacterRank6,highScoreCharacterRank7,highScoreCharacterRank8,
            highScoreCharacterRank9,highScoreCharacterRank10,highScoreGameTheme1,
            highScoreGameTheme2,highScoreGameTheme3,highScoreGameTheme4,highScoreGameTheme5,
            highScoreGameTheme6,highScoreGameTheme7,highScoreGameTheme8,highScoreGameTheme9,
            highScoreGameTheme10; 
    
    public panelHighScores()
    {
        super();
        xHighScoresRead = new XML_240();
        setLayout(null);
        
        importRawHighScores();  //IMPORT RAW SCORE DATA FROM XML FILE
        parseAllRawHighScores();  //PARSE ALL IMPORTED SCORE DATA INTO INDIVIDUAL ARRAYS
        setHighScoreCharacterNames(); //SET CHARACTER NAMES FOR ALL RANKED SCORES
        setHighScoreGameThemes(); //SET GAME THEME NAMES FOR ALL RANKED SCORES
        
        b1 = new JButton("Back to About Game");
        b1.setBounds(775,300,200,100);
        add(b1);
    }

    //IMPORT RAW SCORE DATA FROM EXTERNAL HIGH SCORE XML FILE
    public void importRawHighScores(){
        xHighScoresRead.openReaderXML("XML_Highscores.xml");
        rawHighScores1 = (String) xHighScoresRead.ReadObject();
        rawHighScores2 = (String) xHighScoresRead.ReadObject();
        rawHighScores3 = (String) xHighScoresRead.ReadObject();
        rawHighScores4 = (String) xHighScoresRead.ReadObject();
        rawHighScores5 = (String) xHighScoresRead.ReadObject();
        rawHighScores6 = (String) xHighScoresRead.ReadObject();
        rawHighScores7 = (String) xHighScoresRead.ReadObject();
        rawHighScores8 = (String) xHighScoresRead.ReadObject();
        rawHighScores9 = (String) xHighScoresRead.ReadObject();
        rawHighScores10 = (String) xHighScoresRead.ReadObject();
        xHighScoresRead.closeReaderXML();
    }
    public String[] parseRawScore(String a){
        scoreToParse = a;
        String[] parsedHighScores = scoreToParse.split("-", 25); 
        return parsedHighScores;
    }
    public void parseAllRawHighScores(){
        parsedHighScores1 = parseRawScore(rawHighScores1);
        parsedHighScores2 = parseRawScore(rawHighScores2);
        parsedHighScores3 = parseRawScore(rawHighScores3);
        parsedHighScores4 = parseRawScore(rawHighScores4);
        parsedHighScores5 = parseRawScore(rawHighScores5);
        parsedHighScores6 = parseRawScore(rawHighScores6);
        parsedHighScores7 = parseRawScore(rawHighScores7);
        parsedHighScores8 = parseRawScore(rawHighScores8);
        parsedHighScores9 = parseRawScore(rawHighScores9);
        parsedHighScores10 = parseRawScore(rawHighScores10);
    }
    
    //SET ALL HIGH SCORE CHARACTER CHOSEN VARIABLES
    public void setHighScoreCharacterNames(){
        if (Integer.parseInt(parsedHighScores10[3])==1){highScoreCharacterRank1="Daddy Nittany";}
        else if (Integer.parseInt(parsedHighScores10[3])==2){highScoreCharacterRank1="Mommy Nittany";}
        else {highScoreCharacterRank1="Baby Nittany";}
        
        if (Integer.parseInt(parsedHighScores9[3])==1){highScoreCharacterRank2="Daddy Nittany";}
        else if (Integer.parseInt(parsedHighScores9[3])==2){highScoreCharacterRank2="Mommy Nittany";}
        else {highScoreCharacterRank2="Baby Nittany";}
        
        if (Integer.parseInt(parsedHighScores8[3])==1){highScoreCharacterRank3="Daddy Nittany";}
        else if (Integer.parseInt(parsedHighScores8[3])==2){highScoreCharacterRank3="Mommy Nittany";}
        else {highScoreCharacterRank3="Baby Nittany";}
        
        if (Integer.parseInt(parsedHighScores7[3])==1){highScoreCharacterRank4="Daddy Nittany";}
        else if (Integer.parseInt(parsedHighScores7[3])==2){highScoreCharacterRank4="Mommy Nittany";}
        else {highScoreCharacterRank4="Baby Nittany";}
        
        if (Integer.parseInt(parsedHighScores6[3])==1){highScoreCharacterRank5="Daddy Nittany";}
        else if (Integer.parseInt(parsedHighScores6[3])==2){highScoreCharacterRank5="Mommy Nittany";}
        else {highScoreCharacterRank5="Baby Nittany";}
        
        if (Integer.parseInt(parsedHighScores5[3])==1){highScoreCharacterRank6="Daddy Nittany";}
        else if (Integer.parseInt(parsedHighScores5[3])==2){highScoreCharacterRank6="Mommy Nittany";}
        else {highScoreCharacterRank6="Baby Nittany";}
        
        if (Integer.parseInt(parsedHighScores4[3])==1){highScoreCharacterRank7="Daddy Nittany";}
        else if (Integer.parseInt(parsedHighScores4[3])==2){highScoreCharacterRank7="Mommy Nittany";}
        else {highScoreCharacterRank7="Baby Nittany";}
        
        if (Integer.parseInt(parsedHighScores3[3])==1){highScoreCharacterRank8="Daddy Nittany";}
        else if (Integer.parseInt(parsedHighScores3[3])==2){highScoreCharacterRank8="Mommy Nittany";}
        else {highScoreCharacterRank8="Baby Nittany";}
        
        if (Integer.parseInt(parsedHighScores2[3])==1){highScoreCharacterRank9="Daddy Nittany";}
        else if (Integer.parseInt(parsedHighScores2[3])==2){highScoreCharacterRank9="Mommy Nittany";}
        else {highScoreCharacterRank9="Baby Nittany";}
        
        if (Integer.parseInt(parsedHighScores1[3])==1){highScoreCharacterRank10="Daddy Nittany";}
        else if (Integer.parseInt(parsedHighScores1[3])==2){highScoreCharacterRank10="Mommy Nittany";}
        else {highScoreCharacterRank10="Baby Nittany";}
    }

    //SET ALL HIGH SCORE GAME THEME VARIABLES
    public void setHighScoreGameThemes(){
        if (Integer.parseInt(parsedHighScores10[4])==1){highScoreGameTheme1="PennState";}
        else if (Integer.parseInt(parsedHighScores10[4])==2){highScoreGameTheme1="Wu-Tang";}
        else if (Integer.parseInt(parsedHighScores10[4])==3){highScoreGameTheme1="8-Bit";}
        else {highScoreGameTheme1="Diaper";}
        
        if (Integer.parseInt(parsedHighScores9[4])==1){highScoreGameTheme2="PennState";}
        else if (Integer.parseInt(parsedHighScores9[4])==2){highScoreGameTheme2="Wu-Tang";}
        else if (Integer.parseInt(parsedHighScores9[4])==3){highScoreGameTheme2="8-Bit";}
        else {highScoreGameTheme2="Diaper";}
        
        if (Integer.parseInt(parsedHighScores8[4])==1){highScoreGameTheme3="PennState";}
        else if (Integer.parseInt(parsedHighScores8[4])==2){highScoreGameTheme3="Wu-Tang";}
        else if (Integer.parseInt(parsedHighScores8[4])==3){highScoreGameTheme3="8-Bit";}
        else {highScoreGameTheme3="Diaper";}
        
        if (Integer.parseInt(parsedHighScores7[4])==1){highScoreGameTheme4="PennState";}
        else if (Integer.parseInt(parsedHighScores7[4])==2){highScoreGameTheme4="Wu-Tang";}
        else if (Integer.parseInt(parsedHighScores7[4])==3){highScoreGameTheme4="8-Bit";}
        else {highScoreGameTheme4="Diaper";}
        
        if (Integer.parseInt(parsedHighScores6[4])==1){highScoreGameTheme5="PennState";}
        else if (Integer.parseInt(parsedHighScores6[4])==2){highScoreGameTheme5="Wu-Tang";}
        else if (Integer.parseInt(parsedHighScores6[4])==3){highScoreGameTheme5="8-Bit";}
        else {highScoreGameTheme5="Diaper";}
        
        if (Integer.parseInt(parsedHighScores5[4])==1){highScoreGameTheme6="PennState";}
        else if (Integer.parseInt(parsedHighScores5[4])==2){highScoreGameTheme6="Wu-Tang";}
        else if (Integer.parseInt(parsedHighScores5[4])==3){highScoreGameTheme6="8-Bit";}
        else {highScoreGameTheme6="Diaper";}
        
        if (Integer.parseInt(parsedHighScores4[4])==1){highScoreGameTheme7="PennState";}
        else if (Integer.parseInt(parsedHighScores4[4])==2){highScoreGameTheme7="Wu-Tang";}
        else if (Integer.parseInt(parsedHighScores4[4])==3){highScoreGameTheme7="8-Bit";}
        else {highScoreGameTheme7="Diaper";}
        
        if (Integer.parseInt(parsedHighScores3[4])==1){highScoreGameTheme8="PennState";}
        else if (Integer.parseInt(parsedHighScores3[4])==2){highScoreGameTheme8="Wu-Tang";}
        else if (Integer.parseInt(parsedHighScores3[4])==3){highScoreGameTheme8="8-Bit";}
        else {highScoreGameTheme8="Diaper";}
        
        if (Integer.parseInt(parsedHighScores2[4])==1){highScoreGameTheme9="PennState";}
        else if (Integer.parseInt(parsedHighScores2[4])==2){highScoreGameTheme9="Wu-Tang";}
        else if (Integer.parseInt(parsedHighScores2[4])==3){highScoreGameTheme9="8-Bit";}
        else {highScoreGameTheme9="Diaper";}
        
        if (Integer.parseInt(parsedHighScores1[4])==1){highScoreGameTheme10="PennState";}
        else if (Integer.parseInt(parsedHighScores1[4])==2){highScoreGameTheme10="Wu-Tang";}
        else if (Integer.parseInt(parsedHighScores1[4])==3){highScoreGameTheme10="8-Bit";}
        else {highScoreGameTheme10="Diaper";}
    }
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(highScoresBackground1, 0, 0, this);  //IMG, LEFT, TOP
        g.drawImage(highScoresBackground2, 90, 150, this);  //IMG, LEFT, TOP
        g.drawImage(highScoresTitle1, 76, 20, this);  //IMG, LEFT, TOP
        g.drawImage(highScoresTitle2, 250, 130, this);  //IMG, LEFT, TOP
        g.drawImage(highScoresHeader1, 210, 210, this);  //IMG, LEFT, TOP
        g.drawImage(highScoresHeader2, 128, 245, this);  //IMG, LEFT, TOP
        
        Font f1 = new Font("Gothic", Font.BOLD, 24);
        Font f2 = new Font("Gothic", Font.BOLD, 18);
        Font f3 = new Font("Gothic", Font.BOLD, 12);
        
        g.setColor(Color.YELLOW);
        
        //1ST PLACE
        g.setFont(f1);
        g.drawString(parsedHighScores10[1], 208, 268);  //TEXT, LEFT, TOP
        g.drawString(parsedHighScores10[2], 355, 268);  //TEXT, LEFT, TOP
        g.setFont(f3);
        g.drawString(highScoreCharacterRank1, 460, 262);  //TEXT, LEFT, TOP
        g.drawString(highScoreGameTheme1, 585, 262);  //TEXT, LEFT, TOP
        
        //2ND PLACE
        g.setFont(f1);
        g.drawString(parsedHighScores9[1], 208, 336);  //TEXT, LEFT, TOP
        g.drawString(parsedHighScores9[2], 355, 336);  //TEXT, LEFT, TOP
        g.setFont(f3);
        g.drawString(highScoreCharacterRank2, 460, 330);  //TEXT, LEFT, TOP
        g.drawString(highScoreGameTheme2, 585, 330);  //TEXT, LEFT, TOP
        
        //3RD PLACE
        g.setFont(f1);
        g.drawString(parsedHighScores8[1], 208, 404);  //TEXT, LEFT, TOP
        g.drawString(parsedHighScores8[2], 355, 404);  //TEXT, LEFT, TOP
        g.setFont(f3);
        g.drawString(highScoreCharacterRank3, 460, 398);  //TEXT, LEFT, TOP
        g.drawString(highScoreGameTheme3, 585, 398);  //TEXT, LEFT, TOP
        
        g.setColor(Color.WHITE);
        
        //4TH PLACE
        g.setFont(f2);
        g.drawString(parsedHighScores7[1], 220, 475);  //TEXT, LEFT, TOP
        g.drawString(parsedHighScores7[2], 358, 475);  //TEXT, LEFT, TOP
        g.setFont(f3);
        g.drawString(highScoreCharacterRank4, 460, 472);  //TEXT, LEFT, TOP
        g.drawString(highScoreGameTheme4, 585, 472);  //TEXT, LEFT, TOP
        
        //5TH PLACE
        g.setFont(f2);
        g.drawString(parsedHighScores6[1], 220, 510);  //TEXT, LEFT, TOP
        g.drawString(parsedHighScores6[2], 358, 510);  //TEXT, LEFT, TOP
        g.setFont(f3);
        g.drawString(highScoreCharacterRank5, 460, 507);  //TEXT, LEFT, TOP
        g.drawString(highScoreGameTheme5, 585, 507);  //TEXT, LEFT, TOP
        
        //6TH PLACE
        g.setFont(f2);
        g.drawString(parsedHighScores5[1], 220, 544);  //TEXT, LEFT, TOP
        g.drawString(parsedHighScores5[2], 358, 544);  //TEXT, LEFT, TOP
        g.setFont(f3);
        g.drawString(highScoreCharacterRank6, 460, 541);  //TEXT, LEFT, TOP
        g.drawString(highScoreGameTheme6, 585, 541);  //TEXT, LEFT, TOP
        
        //7TH PLACE
        g.setFont(f2);
        g.drawString(parsedHighScores4[1], 220, 578);  //TEXT, LEFT, TOP
        g.drawString(parsedHighScores4[2], 358, 578);  //TEXT, LEFT, TOP
        g.setFont(f3);
        g.drawString(highScoreCharacterRank7, 460, 575);  //TEXT, LEFT, TOP
        g.drawString(highScoreGameTheme7, 585, 575);  //TEXT, LEFT, TOP
        
        //8TH PLACE
        g.setFont(f2);
        g.drawString(parsedHighScores3[1], 220, 612);  //TEXT, LEFT, TOP
        g.drawString(parsedHighScores3[2], 358, 612);  //TEXT, LEFT, TOP
        g.setFont(f3);
        g.drawString(highScoreCharacterRank8, 460, 609);  //TEXT, LEFT, TOP
        g.drawString(highScoreGameTheme8, 585, 609);  //TEXT, LEFT, TOP
        
        //9TH PLACE
        g.setFont(f2);
        g.drawString(parsedHighScores2[1], 220, 646);  //TEXT, LEFT, TOP
        g.drawString(parsedHighScores2[2], 358, 646);  //TEXT, LEFT, TOP
        g.setFont(f3);
        g.drawString(highScoreCharacterRank9, 460, 643);  //TEXT, LEFT, TOP
        g.drawString(highScoreGameTheme9, 585, 643);  //TEXT, LEFT, TOP
        
        //10TH PLACE
        g.setFont(f2);
        g.drawString(parsedHighScores1[1], 220, 680);  //TEXT, LEFT, TOP
        g.drawString(parsedHighScores1[2], 358, 680);  //TEXT, LEFT, TOP
        g.setFont(f3);
        g.drawString(highScoreCharacterRank10, 460, 677);  //TEXT, LEFT, TOP
        g.drawString(highScoreGameTheme10, 585, 677);  //TEXT, LEFT, TOP
        
        g.setColor(new Color(0, 0, 0, 90));
        g.fillRect(800, 0, 220, 768); //LEFT, TOP, WIDTH, HEIGHT          
    }
}