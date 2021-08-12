/**
 * @author Hussein Ghaleb
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.*;
import javax.swing.Timer;

public class panelGameOverProc extends JPanel implements ActionListener {

    XML_240 x2Read,x2Write;
    objPlayer player;
    
    panelGameOver p13PanelGameOver;
    //panelGameOverScores p14PanelGameOverScores;
    
    ImageIcon   gameOverProcBGImg = new ImageIcon("images/background4.jpg"),
                gameOverProcAniImg = new ImageIcon("images/gameOverProcAni.gif");
    
    Image   gameOverProcBG = gameOverProcBGImg.getImage(),
            gameOverProcAni = gameOverProcAniImg.getImage();

    Timer processGameOverScreen;
    
    boolean newRecordSet,newHighScoreSet;
    
    String GameOutputXMLData;
    
    //IMPORTED RAW SCORE STRINGS
    String  rawScore1,rawScore2,rawScore3,rawScore4,rawScore5,rawScore6,
            rawScore7,rawScore8,rawScore9,rawScore10,scoreToParse,lastScore;
    
    //RAW SCORE STRINGS SPLIT INTO ARRAYS
    String[]    parsedScore,parsedScore1,parsedScore2,parsedScore3,parsedScore4,
                parsedScore5,parsedScore6,parsedScore7,parsedScore8,parsedScore9,
                parsedScore10,scoreToConvert,newScoreDataRaw;

    //RAW SCORE STRING ARRAYS CONVERTED TO INTEGER ARRAYS
    Integer[]   convertedScore,convertedScore1,convertedScore2,convertedScore3,
                convertedScore4,convertedScore5,convertedScore6,convertedScore7,
                convertedScore8,convertedScore9,convertedScore10;
    
    //OUTPUT STRINGS TO EXPORT XML
    String  OutputXMLScore1,OutputXMLScore2,OutputXMLScore3,OutputXMLScore4,
            OutputXMLScore5,OutputXMLScore6,OutputXMLScore7,OutputXMLScore8,
            OutputXMLScore9,OutputXMLScore10;
    
    int[][] highScoreArray,sortedArray,updatedHighScoreArray;
    
    int     newScore,newScoreRank,newScoreRankFound,scoreRanking,
            processGameOverScreenDelay,processGameOverScreenCountdown;
    
    int[] newScoreData;
    
    public panelGameOverProc(objPlayer player)
    {
        super();
        setLayout(null);
        
        this.player = player;
        
        x2Read = new XML_240();
        x2Write = new XML_240();
        
        //PREPARE GAME STATS TO BE WRITTEN TO XML_LASTGAMESCORE.XML
        GameOutputXMLData = "SCORESTART-"+player.gameScore+"-"
         +player.gameTime+"-"+player.character+"-"
          +player.gameTheme+"-"+player.game1Score+"-"
           +player.game1Time+"-"+player.game2Score+"-"
            +player.game2Time+"-"+player.game3Score+"-"
             +player.game3Time+"-"+player.game4Score+"-"
              +player.game4Time+"-END";
        
        //WRITE DATA TO XML_LASTGAMESCORE.XML
        x2Write.openWriterXML("XML_LastGameScore.xml");
        x2Write.writeObject(GameOutputXMLData);
        x2Write.closeWriterXML();
        
        importRawScores();  //IMPORT RAW SCORE DATA FROM XML FILE
        parseAllRawScores();  //PARSE ALL IMPORTED SCORE DATA INTO INDIVIDUAL ARRAYS
        highScoreArray = buildScoreArray(); //BUILD MULTI-DIMENTIONAL INTEGER ARRAY OF PARSED SCORE DATA
        sortScoreArrayByScore(highScoreArray);  //SORT ARRAY ITEMS BY SCORE (LOW TO HIGH)
        
        x2Read.openReaderXML("XML_LastGameScore.xml");  //PULL SCORE DATA OF LAST GAME COMPLETED VIA XML
        lastScore = (String) x2Read.ReadObject();   //PULL SCORE DATA OF LAST GAME COMPLETED VIA XML
        x2Read.closeReaderXML();
        
        newScoreDataRaw = parseRawScore(lastScore);  //IMPORT LAST GAME SCORE DATA FROM SEPERATE XML HERE
        newScoreData = buildNewScoreArray(newScoreDataRaw);  //PARSE LAST GAME DATA INTO ARRAY
        newScore = newScoreData[0]; //SET VARIABLE FOR NEW SCORE
        
        //---------------------------------------------------------------
        //CHECK IF THE NEW SCORE IS HIGHER THAN THE LOWEST SCORE RECORDED
        if(newScore > highScoreArray[0][0]){
            //ACHIEVED NEW HIGH SCORE!!!
            newHighScoreSet = true;
            
            //A NEW HIGH RECORD SET!!!
            if(newScore > highScoreArray[9][0]){
                newRecordSet=true;}
            
            newScoreRank = findRankingOfNewScore(newScore); //WHAT RANK IS THE SCORE, 0 IS LOWEST AND 9 IS TOP SCORE
            updatedHighScoreArray = buildUpdatedScoreArray(newScoreData); //UPDATE SCORE LIST BY ADDING NEW SCORE AND REMOVING LOWEST SCORE
            sortScoreArrayByScore(updatedHighScoreArray); //RESORT THE SCORE ARRAY FROM LOW SCORE TO HIGH SCORE
            outputUpdatedScoreArrayToXML(updatedHighScoreArray); //OUTPUT THE UPDATED SCORE LIST TO XML
            
            if(newScoreRank==9){scoreRanking = 1;}
            else if(newScoreRank==8){scoreRanking = 2;}
            else if(newScoreRank==7){scoreRanking = 3;}
            else if(newScoreRank==6){scoreRanking = 4;}
            else if(newScoreRank==5){scoreRanking = 5;}
            else if(newScoreRank==4){scoreRanking = 6;}
            else if(newScoreRank==3){scoreRanking = 7;}
            else if(newScoreRank==2){scoreRanking = 8;}
            else if(newScoreRank==1){scoreRanking = 9;}
            else if(newScoreRank==0){scoreRanking = 10;}
            else {scoreRanking = 0;}
        }
        else { newHighScoreSet = false; //IF HIGH SCORE NOT ACHEIVED
               scoreRanking = 0; 
               updatedHighScoreArray = highScoreArray;
        } 
        
        //testAllOutputsGameOverProc();  //FOR TESTING ONLY - TEST ALL PROCESSED VARIABLES
        
        processGameOverScreenDelay = 1000;
        processGameOverScreenCountdown = 4;
        processGameOverScreen = new javax.swing.Timer(processGameOverScreenDelay, this);
        processGameOverScreen.start(); //START COUNTDOWN TIMER
    }
    
    //IMPORT RAW SCORE DATA FROM EXTERNAL HIGH SCORE XML FILE
    public void importRawScores(){
        x2Read.openReaderXML("XML_Highscores.xml");
        rawScore1 = (String) x2Read.ReadObject();
        rawScore2 = (String) x2Read.ReadObject();
        rawScore3 = (String) x2Read.ReadObject();
        rawScore4 = (String) x2Read.ReadObject();
        rawScore5 = (String) x2Read.ReadObject();
        rawScore6 = (String) x2Read.ReadObject();
        rawScore7 = (String) x2Read.ReadObject();
        rawScore8 = (String) x2Read.ReadObject();
        rawScore9 = (String) x2Read.ReadObject();
        rawScore10 = (String) x2Read.ReadObject();
        x2Read.closeReaderXML();
    }
    public String[] parseRawScore(String a){
        scoreToParse = a;
        String[] parsedScore = scoreToParse.split("-", 25); 
        return parsedScore;
    }
    public void parseAllRawScores(){
        parsedScore1 = parseRawScore(rawScore1);
        parsedScore2 = parseRawScore(rawScore2);
        parsedScore3 = parseRawScore(rawScore3);
        parsedScore4 = parseRawScore(rawScore4);
        parsedScore5 = parseRawScore(rawScore5);
        parsedScore6 = parseRawScore(rawScore6);
        parsedScore7 = parseRawScore(rawScore7);
        parsedScore8 = parseRawScore(rawScore8);
        parsedScore9 = parseRawScore(rawScore9);
        parsedScore10 = parseRawScore(rawScore10);
    }
    
    //BUILDING MULTI-DIMENSIONAL INTEGER ARRAY OF ALL HIGH SCORE DATA
    public int[][] buildScoreArray(){
        int[][] highScoreArray = new int[10][12];
        highScoreArray[0][0] = Integer.parseInt(parsedScore1[1]);
        highScoreArray[0][1] = Integer.parseInt(parsedScore1[2]);
        highScoreArray[0][2] = Integer.parseInt(parsedScore1[3]);
        highScoreArray[0][3] = Integer.parseInt(parsedScore1[4]);
        highScoreArray[0][4] = Integer.parseInt(parsedScore1[5]);
        highScoreArray[0][5] = Integer.parseInt(parsedScore1[6]);
        highScoreArray[0][6] = Integer.parseInt(parsedScore1[7]);
        highScoreArray[0][7] = Integer.parseInt(parsedScore1[8]);
        highScoreArray[0][8] = Integer.parseInt(parsedScore1[9]);
        highScoreArray[0][9] = Integer.parseInt(parsedScore1[10]);
        highScoreArray[0][10] = Integer.parseInt(parsedScore1[11]);
        highScoreArray[0][11] = Integer.parseInt(parsedScore1[12]);
        highScoreArray[1][0] = Integer.parseInt(parsedScore2[1]);
        highScoreArray[1][1] = Integer.parseInt(parsedScore2[2]);
        highScoreArray[1][2] = Integer.parseInt(parsedScore2[3]);
        highScoreArray[1][3] = Integer.parseInt(parsedScore2[4]);
        highScoreArray[1][4] = Integer.parseInt(parsedScore2[5]);
        highScoreArray[1][5] = Integer.parseInt(parsedScore2[6]);
        highScoreArray[1][6] = Integer.parseInt(parsedScore2[7]);
        highScoreArray[1][7] = Integer.parseInt(parsedScore2[8]);
        highScoreArray[1][8] = Integer.parseInt(parsedScore2[9]);
        highScoreArray[1][9] = Integer.parseInt(parsedScore2[10]);
        highScoreArray[1][10] = Integer.parseInt(parsedScore2[11]);
        highScoreArray[1][11] = Integer.parseInt(parsedScore2[12]);
        highScoreArray[2][0] = Integer.parseInt(parsedScore3[1]);
        highScoreArray[2][1] = Integer.parseInt(parsedScore3[2]);
        highScoreArray[2][2] = Integer.parseInt(parsedScore3[3]);
        highScoreArray[2][3] = Integer.parseInt(parsedScore3[4]);
        highScoreArray[2][4] = Integer.parseInt(parsedScore3[5]);
        highScoreArray[2][5] = Integer.parseInt(parsedScore3[6]);
        highScoreArray[2][6] = Integer.parseInt(parsedScore3[7]);
        highScoreArray[2][7] = Integer.parseInt(parsedScore3[8]);
        highScoreArray[2][8] = Integer.parseInt(parsedScore3[9]);
        highScoreArray[2][9] = Integer.parseInt(parsedScore3[10]);
        highScoreArray[2][10] = Integer.parseInt(parsedScore3[11]);
        highScoreArray[2][11] = Integer.parseInt(parsedScore3[12]);
        highScoreArray[3][0] = Integer.parseInt(parsedScore4[1]);
        highScoreArray[3][1] = Integer.parseInt(parsedScore4[2]);
        highScoreArray[3][2] = Integer.parseInt(parsedScore4[3]);
        highScoreArray[3][3] = Integer.parseInt(parsedScore4[4]);
        highScoreArray[3][4] = Integer.parseInt(parsedScore4[5]);
        highScoreArray[3][5] = Integer.parseInt(parsedScore4[6]);
        highScoreArray[3][6] = Integer.parseInt(parsedScore4[7]);
        highScoreArray[3][7] = Integer.parseInt(parsedScore4[8]);
        highScoreArray[3][8] = Integer.parseInt(parsedScore4[9]);
        highScoreArray[3][9] = Integer.parseInt(parsedScore4[10]);
        highScoreArray[3][10] = Integer.parseInt(parsedScore4[11]);
        highScoreArray[3][11] = Integer.parseInt(parsedScore4[12]);
        highScoreArray[4][0] = Integer.parseInt(parsedScore5[1]);
        highScoreArray[4][1] = Integer.parseInt(parsedScore5[2]);
        highScoreArray[4][2] = Integer.parseInt(parsedScore5[3]);
        highScoreArray[4][3] = Integer.parseInt(parsedScore5[4]);
        highScoreArray[4][4] = Integer.parseInt(parsedScore5[5]);
        highScoreArray[4][5] = Integer.parseInt(parsedScore5[6]);
        highScoreArray[4][6] = Integer.parseInt(parsedScore5[7]);
        highScoreArray[4][7] = Integer.parseInt(parsedScore5[8]);
        highScoreArray[4][8] = Integer.parseInt(parsedScore5[9]);
        highScoreArray[4][9] = Integer.parseInt(parsedScore5[10]);
        highScoreArray[4][10] = Integer.parseInt(parsedScore5[11]);
        highScoreArray[4][11] = Integer.parseInt(parsedScore5[12]);
        highScoreArray[5][0] = Integer.parseInt(parsedScore6[1]);
        highScoreArray[5][1] = Integer.parseInt(parsedScore6[2]);
        highScoreArray[5][2] = Integer.parseInt(parsedScore6[3]);
        highScoreArray[5][3] = Integer.parseInt(parsedScore6[4]);
        highScoreArray[5][4] = Integer.parseInt(parsedScore6[5]);
        highScoreArray[5][5] = Integer.parseInt(parsedScore6[6]);
        highScoreArray[5][6] = Integer.parseInt(parsedScore6[7]);
        highScoreArray[5][7] = Integer.parseInt(parsedScore6[8]);
        highScoreArray[5][8] = Integer.parseInt(parsedScore6[9]);
        highScoreArray[5][9] = Integer.parseInt(parsedScore6[10]);
        highScoreArray[5][10] = Integer.parseInt(parsedScore6[11]);
        highScoreArray[5][11] = Integer.parseInt(parsedScore6[12]);
        highScoreArray[6][0] = Integer.parseInt(parsedScore7[1]);
        highScoreArray[6][1] = Integer.parseInt(parsedScore7[2]);
        highScoreArray[6][2] = Integer.parseInt(parsedScore7[3]);
        highScoreArray[6][3] = Integer.parseInt(parsedScore7[4]);
        highScoreArray[6][4] = Integer.parseInt(parsedScore7[5]);
        highScoreArray[6][5] = Integer.parseInt(parsedScore7[6]);
        highScoreArray[6][6] = Integer.parseInt(parsedScore7[7]);
        highScoreArray[6][7] = Integer.parseInt(parsedScore7[8]);
        highScoreArray[6][8] = Integer.parseInt(parsedScore7[9]);
        highScoreArray[6][9] = Integer.parseInt(parsedScore7[10]);
        highScoreArray[6][10] = Integer.parseInt(parsedScore7[11]);
        highScoreArray[6][11] = Integer.parseInt(parsedScore7[12]);
        highScoreArray[7][0] = Integer.parseInt(parsedScore8[1]);
        highScoreArray[7][1] = Integer.parseInt(parsedScore8[2]);
        highScoreArray[7][2] = Integer.parseInt(parsedScore8[3]);
        highScoreArray[7][3] = Integer.parseInt(parsedScore8[4]);
        highScoreArray[7][4] = Integer.parseInt(parsedScore8[5]);
        highScoreArray[7][5] = Integer.parseInt(parsedScore8[6]);
        highScoreArray[7][6] = Integer.parseInt(parsedScore8[7]);
        highScoreArray[7][7] = Integer.parseInt(parsedScore8[8]);
        highScoreArray[7][8] = Integer.parseInt(parsedScore8[9]);
        highScoreArray[7][9] = Integer.parseInt(parsedScore8[10]);
        highScoreArray[7][10] = Integer.parseInt(parsedScore8[11]);
        highScoreArray[7][11] = Integer.parseInt(parsedScore8[12]);
        highScoreArray[8][0] = Integer.parseInt(parsedScore9[1]);
        highScoreArray[8][1] = Integer.parseInt(parsedScore9[2]);
        highScoreArray[8][2] = Integer.parseInt(parsedScore9[3]);
        highScoreArray[8][3] = Integer.parseInt(parsedScore9[4]);
        highScoreArray[8][4] = Integer.parseInt(parsedScore9[5]);
        highScoreArray[8][5] = Integer.parseInt(parsedScore9[6]);
        highScoreArray[8][6] = Integer.parseInt(parsedScore9[7]);
        highScoreArray[8][7] = Integer.parseInt(parsedScore9[8]);
        highScoreArray[8][8] = Integer.parseInt(parsedScore9[9]);
        highScoreArray[8][9] = Integer.parseInt(parsedScore9[10]);
        highScoreArray[8][10] = Integer.parseInt(parsedScore9[11]);
        highScoreArray[8][11] = Integer.parseInt(parsedScore9[12]);
        highScoreArray[9][0] = Integer.parseInt(parsedScore10[1]);
        highScoreArray[9][1] = Integer.parseInt(parsedScore10[2]);
        highScoreArray[9][2] = Integer.parseInt(parsedScore10[3]);
        highScoreArray[9][3] = Integer.parseInt(parsedScore10[4]);
        highScoreArray[9][4] = Integer.parseInt(parsedScore10[5]);
        highScoreArray[9][5] = Integer.parseInt(parsedScore10[6]);
        highScoreArray[9][6] = Integer.parseInt(parsedScore10[7]);
        highScoreArray[9][7] = Integer.parseInt(parsedScore10[8]);
        highScoreArray[9][8] = Integer.parseInt(parsedScore10[9]);
        highScoreArray[9][9] = Integer.parseInt(parsedScore10[10]);
        highScoreArray[9][10] = Integer.parseInt(parsedScore10[11]);
        highScoreArray[9][11] = Integer.parseInt(parsedScore10[12]);
        return highScoreArray;
    }
    public int[] buildNewScoreArray(String newScoreData[]){
        int[] newScoreIntData = new int[12];
        newScoreIntData[0] = Integer.parseInt(newScoreData[1]);
        newScoreIntData[1] = Integer.parseInt(newScoreData[2]);
        newScoreIntData[2] = Integer.parseInt(newScoreData[3]);
        newScoreIntData[3] = Integer.parseInt(newScoreData[4]);
        newScoreIntData[4] = Integer.parseInt(newScoreData[5]);
        newScoreIntData[5] = Integer.parseInt(newScoreData[6]);
        newScoreIntData[6] = Integer.parseInt(newScoreData[7]);
        newScoreIntData[7] = Integer.parseInt(newScoreData[8]);
        newScoreIntData[8] = Integer.parseInt(newScoreData[9]);
        newScoreIntData[9] = Integer.parseInt(newScoreData[10]);
        newScoreIntData[10] = Integer.parseInt(newScoreData[11]);
        newScoreIntData[11] = Integer.parseInt(newScoreData[12]);
        return newScoreIntData;
    }
    
    //SORT INDEX OF MULTI-DIMENSIONAL ARRAY BY SCORE (LOWEST TO HIGHEST)
    public void sortScoreArrayByScore(int arr[][]){
        Arrays.sort(arr, new Comparator<int[]>(){
            public int compare(int[] entry1, int[] entry2) {
                if (entry1[0] > entry2[0]) return 1;
                else return -1; 
            }
        });
    }
    public void printSortedArray(int arrToSort[][]){
        for (int i = 0; i < arrToSort.length; i++) { 
            for (int j = 0; j < arrToSort[i].length; j++) 
                System.out.print(arrToSort[i][j] + " "); 
            System.out.println(); 
        }
    }
    
    //RETURN ARRAY POSITION OF NEW HIGH SCORE
    public int findRankingOfNewScore(int newScore){
        for (int i = 0; i < 10; i++){
            if(newScore > highScoreArray[0][0] && newScore < highScoreArray[1][0]){newScoreRankFound = 0;}
            else if(newScore > highScoreArray[1][0] && newScore < highScoreArray[2][0]){newScoreRankFound = 1;}
            else if(newScore > highScoreArray[2][0] && newScore < highScoreArray[3][0]){newScoreRankFound = 2;}
            else if(newScore > highScoreArray[3][0] && newScore < highScoreArray[4][0]){newScoreRankFound = 3;}
            else if(newScore > highScoreArray[4][0] && newScore < highScoreArray[5][0]){newScoreRankFound = 4;}
            else if(newScore > highScoreArray[5][0] && newScore < highScoreArray[6][0]){newScoreRankFound = 5;}
            else if(newScore > highScoreArray[6][0] && newScore < highScoreArray[7][0]){newScoreRankFound = 6;}
            else if(newScore > highScoreArray[7][0] && newScore < highScoreArray[8][0]){newScoreRankFound = 7;}
            else if(newScore > highScoreArray[8][0] && newScore < highScoreArray[9][0]){newScoreRankFound = 8;}
            else if(newScore > highScoreArray[9][0]){newScoreRankFound = 9;}
        }
        return newScoreRankFound;
    }
    
    //UPDATE THE MULTI-DIMENSIONAL ARRAY REPLACING THE LOWEST SCORE W/NEW HIGH SCORE DATA
    public int[][] buildUpdatedScoreArray(int newScoreData[]){
        int[][] updatedHighScoreArray = new int[10][12];
        updatedHighScoreArray = highScoreArray;
        updatedHighScoreArray[0][0] = newScoreData[0];
        updatedHighScoreArray[0][1] = newScoreData[1];
        updatedHighScoreArray[0][2] = newScoreData[2];
        updatedHighScoreArray[0][3] = newScoreData[3];
        updatedHighScoreArray[0][4] = newScoreData[4];
        updatedHighScoreArray[0][5] = newScoreData[5];
        updatedHighScoreArray[0][6] = newScoreData[6];
        updatedHighScoreArray[0][7] = newScoreData[7];
        updatedHighScoreArray[0][8] = newScoreData[8];
        updatedHighScoreArray[0][9] = newScoreData[9];
        updatedHighScoreArray[0][10] = newScoreData[10];
        updatedHighScoreArray[0][11] = newScoreData[11];
        return updatedHighScoreArray;
    }
    
    public void outputUpdatedScoreArrayToXML(int[][] updatedHighScoreArray){
        OutputXMLScore1 = "SCORESTART-"+updatedHighScoreArray[0][0]+"-"
         +updatedHighScoreArray[0][1]+"-"+updatedHighScoreArray[0][2]+"-"
          +updatedHighScoreArray[0][3]+"-"+updatedHighScoreArray[0][4]+"-"
           +updatedHighScoreArray[0][5]+"-"+updatedHighScoreArray[0][6]+"-"
            +updatedHighScoreArray[0][7]+"-"+updatedHighScoreArray[0][8]+"-"
             +updatedHighScoreArray[0][9]+"-"+updatedHighScoreArray[0][10]+"-"
              +updatedHighScoreArray[0][11]+"-END";
        
        OutputXMLScore2 = "SCORESTART-"+updatedHighScoreArray[1][0]+"-"
         +updatedHighScoreArray[1][1]+"-"+updatedHighScoreArray[1][2]+"-"
          +updatedHighScoreArray[1][3]+"-"+updatedHighScoreArray[1][4]+"-"
           +updatedHighScoreArray[1][5]+"-"+updatedHighScoreArray[1][6]+"-"
            +updatedHighScoreArray[1][7]+"-"+updatedHighScoreArray[1][8]+"-"
             +updatedHighScoreArray[1][9]+"-"+updatedHighScoreArray[1][10]+"-"
              +updatedHighScoreArray[1][11]+"-END";
        
        OutputXMLScore3 = "SCORESTART-"+updatedHighScoreArray[2][0]+"-"
         +updatedHighScoreArray[2][1]+"-"+updatedHighScoreArray[2][2]+"-"
          +updatedHighScoreArray[2][3]+"-"+updatedHighScoreArray[2][4]+"-"
           +updatedHighScoreArray[2][5]+"-"+updatedHighScoreArray[2][6]+"-"
            +updatedHighScoreArray[2][7]+"-"+updatedHighScoreArray[2][8]+"-"
             +updatedHighScoreArray[2][9]+"-"+updatedHighScoreArray[2][10]+"-"
              +updatedHighScoreArray[2][11]+"-END";
        
        OutputXMLScore4 = "SCORESTART-"+updatedHighScoreArray[3][0]+"-"
         +updatedHighScoreArray[3][1]+"-"+updatedHighScoreArray[3][2]+"-"
          +updatedHighScoreArray[3][3]+"-"+updatedHighScoreArray[3][4]+"-"
           +updatedHighScoreArray[3][5]+"-"+updatedHighScoreArray[3][6]+"-"
            +updatedHighScoreArray[3][7]+"-"+updatedHighScoreArray[3][8]+"-"
             +updatedHighScoreArray[3][9]+"-"+updatedHighScoreArray[3][10]+"-"
              +updatedHighScoreArray[3][11]+"-END";
        
        OutputXMLScore5 = "SCORESTART-"+updatedHighScoreArray[4][0]+"-"
         +updatedHighScoreArray[4][1]+"-"+updatedHighScoreArray[4][2]+"-"
          +updatedHighScoreArray[4][3]+"-"+updatedHighScoreArray[4][4]+"-"
           +updatedHighScoreArray[4][5]+"-"+updatedHighScoreArray[4][6]+"-"
            +updatedHighScoreArray[4][7]+"-"+updatedHighScoreArray[4][8]+"-"
             +updatedHighScoreArray[4][9]+"-"+updatedHighScoreArray[4][10]+"-"
              +updatedHighScoreArray[4][11]+"-END";
        
        OutputXMLScore6 = "SCORESTART-"+updatedHighScoreArray[5][0]+"-"
         +updatedHighScoreArray[5][1]+"-"+updatedHighScoreArray[5][2]+"-"
          +updatedHighScoreArray[5][3]+"-"+updatedHighScoreArray[5][4]+"-"
           +updatedHighScoreArray[5][5]+"-"+updatedHighScoreArray[5][6]+"-"
            +updatedHighScoreArray[5][7]+"-"+updatedHighScoreArray[5][8]+"-"
             +updatedHighScoreArray[5][9]+"-"+updatedHighScoreArray[5][10]+"-"
              +updatedHighScoreArray[5][11]+"-END";
        
        OutputXMLScore7 = "SCORESTART-"+updatedHighScoreArray[6][0]+"-"
         +updatedHighScoreArray[6][1]+"-"+updatedHighScoreArray[6][2]+"-"
          +updatedHighScoreArray[6][3]+"-"+updatedHighScoreArray[6][4]+"-"
           +updatedHighScoreArray[6][5]+"-"+updatedHighScoreArray[6][6]+"-"
            +updatedHighScoreArray[6][7]+"-"+updatedHighScoreArray[6][8]+"-"
             +updatedHighScoreArray[6][9]+"-"+updatedHighScoreArray[6][10]+"-"
              +updatedHighScoreArray[6][11]+"-END";
        
        OutputXMLScore8 = "SCORESTART-"+updatedHighScoreArray[7][0]+"-"
         +updatedHighScoreArray[7][1]+"-"+updatedHighScoreArray[7][2]+"-"
          +updatedHighScoreArray[7][3]+"-"+updatedHighScoreArray[7][4]+"-"
           +updatedHighScoreArray[7][5]+"-"+updatedHighScoreArray[7][6]+"-"
            +updatedHighScoreArray[7][7]+"-"+updatedHighScoreArray[7][8]+"-"
             +updatedHighScoreArray[7][9]+"-"+updatedHighScoreArray[7][10]+"-"
              +updatedHighScoreArray[7][11]+"-END";
        
        OutputXMLScore9 = "SCORESTART-"+updatedHighScoreArray[8][0]+"-"
         +updatedHighScoreArray[8][1]+"-"+updatedHighScoreArray[8][2]+"-"
          +updatedHighScoreArray[8][3]+"-"+updatedHighScoreArray[8][4]+"-"
           +updatedHighScoreArray[8][5]+"-"+updatedHighScoreArray[8][6]+"-"
            +updatedHighScoreArray[8][7]+"-"+updatedHighScoreArray[8][8]+"-"
             +updatedHighScoreArray[8][9]+"-"+updatedHighScoreArray[8][10]+"-"
              +updatedHighScoreArray[8][11]+"-END";
        
        OutputXMLScore10 = "SCORESTART-"+updatedHighScoreArray[9][0]+"-"
         +updatedHighScoreArray[9][1]+"-"+updatedHighScoreArray[9][2]+"-"
          +updatedHighScoreArray[9][3]+"-"+updatedHighScoreArray[9][4]+"-"
           +updatedHighScoreArray[9][5]+"-"+updatedHighScoreArray[9][6]+"-"
            +updatedHighScoreArray[9][7]+"-"+updatedHighScoreArray[9][8]+"-"
             +updatedHighScoreArray[9][9]+"-"+updatedHighScoreArray[9][10]+"-"
              +updatedHighScoreArray[9][11]+"-END";
        
        x2Write.openWriterXML("XML_Highscores.xml");
        x2Write.writeObject(OutputXMLScore1);
        x2Write.writeObject(OutputXMLScore2);
        x2Write.writeObject(OutputXMLScore3);
        x2Write.writeObject(OutputXMLScore4);
        x2Write.writeObject(OutputXMLScore5);
        x2Write.writeObject(OutputXMLScore6);
        x2Write.writeObject(OutputXMLScore7);
        x2Write.writeObject(OutputXMLScore8);
        x2Write.writeObject(OutputXMLScore9);
        x2Write.writeObject(OutputXMLScore10);
        x2Write.closeWriterXML();
    }
    
    public void testAllOutputsGameOverProc(){
        System.out.println(player.gameScore + "(Total Score)");
        System.out.println(player.gameTime + "(Total Time)");
        System.out.println(player.character + "(Player Character)");
        System.out.println(player.gameTheme + "(Game Theme)");
        System.out.println(player.game1Score + "(Game1 Score)");
        System.out.println(player.game1Time + "(Game1 Time)");
        System.out.println(player.game2Score + "(Game2 Score)");
        System.out.println(player.game2Time + "(Game2 Time)");
        System.out.println(player.game3Score + "(Game3 Score)");
        System.out.println(player.game3Time + "(Game3 Time)");
        System.out.println(player.game4Score + "(Game4 Score)");
        System.out.println(player.game4Time + "(Game4 Time)");
        System.out.println("");
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println(scoreRanking + "(Score Ranking)");
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println("");
        System.out.println("-=-=-=-= 10TH PLACE -=-=-=-=-");
        System.out.println(updatedHighScoreArray[0][0]);
        System.out.println(updatedHighScoreArray[0][1]);
        System.out.println(updatedHighScoreArray[0][2]);
        System.out.println(updatedHighScoreArray[0][3]);
        System.out.println(updatedHighScoreArray[0][4]);
        System.out.println(updatedHighScoreArray[0][5]);
        System.out.println(updatedHighScoreArray[0][6]);
        System.out.println(updatedHighScoreArray[0][7]);
        System.out.println(updatedHighScoreArray[0][8]);
        System.out.println(updatedHighScoreArray[0][9]);
        System.out.println(updatedHighScoreArray[0][10]);
        System.out.println(updatedHighScoreArray[0][11]);
        
        System.out.println("-=-=-=-= 9TH PLACE -=-=-=-=-");
        System.out.println(updatedHighScoreArray[1][0]);
        System.out.println(updatedHighScoreArray[1][1]);
        System.out.println(updatedHighScoreArray[1][2]);
        System.out.println(updatedHighScoreArray[1][3]);
        System.out.println(updatedHighScoreArray[1][4]);
        System.out.println(updatedHighScoreArray[1][5]);
        System.out.println(updatedHighScoreArray[1][6]);
        System.out.println(updatedHighScoreArray[1][7]);
        System.out.println(updatedHighScoreArray[1][8]);
        System.out.println(updatedHighScoreArray[1][9]);
        System.out.println(updatedHighScoreArray[1][10]);
        System.out.println(updatedHighScoreArray[1][11]);
        
        System.out.println("-=-=-=-= 8TH PLACE -=-=-=-=-");
        System.out.println(updatedHighScoreArray[2][0]);
        System.out.println(updatedHighScoreArray[2][1]);
        System.out.println(updatedHighScoreArray[2][2]);
        System.out.println(updatedHighScoreArray[2][3]);
        System.out.println(updatedHighScoreArray[2][4]);
        System.out.println(updatedHighScoreArray[2][5]);
        System.out.println(updatedHighScoreArray[2][6]);
        System.out.println(updatedHighScoreArray[2][7]);
        System.out.println(updatedHighScoreArray[2][8]);
        System.out.println(updatedHighScoreArray[2][9]);
        System.out.println(updatedHighScoreArray[2][10]);
        System.out.println(updatedHighScoreArray[2][11]);
        
        System.out.println("-=-=-=-= 7TH PLACE -=-=-=-=-");
        System.out.println(updatedHighScoreArray[3][0]);
        System.out.println(updatedHighScoreArray[3][1]);
        System.out.println(updatedHighScoreArray[3][2]);
        System.out.println(updatedHighScoreArray[3][3]);
        System.out.println(updatedHighScoreArray[3][4]);
        System.out.println(updatedHighScoreArray[3][5]);
        System.out.println(updatedHighScoreArray[3][6]);
        System.out.println(updatedHighScoreArray[3][7]);
        System.out.println(updatedHighScoreArray[3][8]);
        System.out.println(updatedHighScoreArray[3][9]);
        System.out.println(updatedHighScoreArray[3][10]);
        System.out.println(updatedHighScoreArray[3][11]);
        
        System.out.println("-=-=-=-= 6TH PLACE -=-=-=-=-");
        System.out.println(updatedHighScoreArray[4][0]);
        System.out.println(updatedHighScoreArray[4][1]);
        System.out.println(updatedHighScoreArray[4][2]);
        System.out.println(updatedHighScoreArray[4][3]);
        System.out.println(updatedHighScoreArray[4][4]);
        System.out.println(updatedHighScoreArray[4][5]);
        System.out.println(updatedHighScoreArray[4][6]);
        System.out.println(updatedHighScoreArray[4][7]);
        System.out.println(updatedHighScoreArray[4][8]);
        System.out.println(updatedHighScoreArray[4][9]);
        System.out.println(updatedHighScoreArray[4][10]);
        System.out.println(updatedHighScoreArray[4][11]);
        
        System.out.println("-=-=-=-= 5TH PLACE -=-=-=-=-");
        System.out.println(updatedHighScoreArray[5][0]);
        System.out.println(updatedHighScoreArray[5][1]);
        System.out.println(updatedHighScoreArray[5][2]);
        System.out.println(updatedHighScoreArray[5][3]);
        System.out.println(updatedHighScoreArray[5][4]);
        System.out.println(updatedHighScoreArray[5][5]);
        System.out.println(updatedHighScoreArray[5][6]);
        System.out.println(updatedHighScoreArray[5][7]);
        System.out.println(updatedHighScoreArray[5][8]);
        System.out.println(updatedHighScoreArray[5][9]);
        System.out.println(updatedHighScoreArray[5][10]);
        System.out.println(updatedHighScoreArray[5][11]);
        
        System.out.println("-=-=-=-= 4TH PLACE -=-=-=-=-");
        System.out.println(updatedHighScoreArray[6][0]);
        System.out.println(updatedHighScoreArray[6][1]);
        System.out.println(updatedHighScoreArray[6][2]);
        System.out.println(updatedHighScoreArray[6][3]);
        System.out.println(updatedHighScoreArray[6][4]);
        System.out.println(updatedHighScoreArray[6][5]);
        System.out.println(updatedHighScoreArray[6][6]);
        System.out.println(updatedHighScoreArray[6][7]);
        System.out.println(updatedHighScoreArray[6][8]);
        System.out.println(updatedHighScoreArray[6][9]);
        System.out.println(updatedHighScoreArray[6][10]);
        System.out.println(updatedHighScoreArray[6][11]);
        
        System.out.println("-=-=-=-= 3RD PLACE -=-=-=-=-");
        System.out.println(updatedHighScoreArray[7][0]);
        System.out.println(updatedHighScoreArray[7][1]);
        System.out.println(updatedHighScoreArray[7][2]);
        System.out.println(updatedHighScoreArray[7][3]);
        System.out.println(updatedHighScoreArray[7][4]);
        System.out.println(updatedHighScoreArray[7][5]);
        System.out.println(updatedHighScoreArray[7][6]);
        System.out.println(updatedHighScoreArray[7][7]);
        System.out.println(updatedHighScoreArray[7][8]);
        System.out.println(updatedHighScoreArray[7][9]);
        System.out.println(updatedHighScoreArray[7][10]);
        System.out.println(updatedHighScoreArray[7][11]);
        
        System.out.println("-=-=-=-= 2ND PLACE -=-=-=-=-");
        System.out.println(updatedHighScoreArray[8][0]);
        System.out.println(updatedHighScoreArray[8][1]);
        System.out.println(updatedHighScoreArray[8][2]);
        System.out.println(updatedHighScoreArray[8][3]);
        System.out.println(updatedHighScoreArray[8][4]);
        System.out.println(updatedHighScoreArray[8][5]);
        System.out.println(updatedHighScoreArray[8][6]);
        System.out.println(updatedHighScoreArray[8][7]);
        System.out.println(updatedHighScoreArray[8][8]);
        System.out.println(updatedHighScoreArray[8][9]);
        System.out.println(updatedHighScoreArray[8][10]);
        System.out.println(updatedHighScoreArray[8][11]);
        
        System.out.println("-=-=-=-= 1ST PLACE -=-=-=-=-");
        System.out.println(updatedHighScoreArray[9][0]);
        System.out.println(updatedHighScoreArray[9][1]);
        System.out.println(updatedHighScoreArray[9][2]);
        System.out.println(updatedHighScoreArray[9][3]);
        System.out.println(updatedHighScoreArray[9][4]);
        System.out.println(updatedHighScoreArray[9][5]);
        System.out.println(updatedHighScoreArray[9][6]);
        System.out.println(updatedHighScoreArray[9][7]);
        System.out.println(updatedHighScoreArray[9][8]);
        System.out.println(updatedHighScoreArray[9][9]);
        System.out.println(updatedHighScoreArray[9][10]);
        System.out.println(updatedHighScoreArray[9][11]);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(gameOverProcBG, 0, 0, this);    //DRAW BACKGROUND IMAGE
        g.drawImage(gameOverProcAni, 237, 288, this);     //DRAW GAME OVER ANI GIF
    }
    
    public void actionPerformed(ActionEvent event){
        Object obj = event.getSource();
        String choice = event.getActionCommand();
        
        if (obj == processGameOverScreen){
            processGameOverScreenCountdown = processGameOverScreenCountdown - 1; //UPDATE COUNTDOWN TIMER
            if(processGameOverScreenCountdown==0){
                removeAll();
                p13PanelGameOver = new panelGameOver(player,scoreRanking,updatedHighScoreArray);
                p13PanelGameOver.setBounds(0,0,1024,768);
                add(p13PanelGameOver);
                validate();
                repaint();
            }
        }
    }
}