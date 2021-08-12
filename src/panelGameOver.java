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

public class panelGameOver extends JPanel implements ActionListener {

    panelGameMap p07PanelGameMap;
    objPlayer player;
    int ranking;
    int[][] highScores;
    
    JButton launchGameOverScore,launchExitGame;
    
    panelGameOverScore p14PanelGameOverHighScore;
    
    ImageIcon panelGameOver1 = new ImageIcon("images/background1.jpg");
    ImageIcon panelGameOver2 = new ImageIcon("images/gameOverBackground2.png");
    ImageIcon panelGameOver3 = new ImageIcon("images/gameOverBackground3.png");
    ImageIcon panelGameOverHeader1 = new ImageIcon("images/gameOverHeader1.png");
    ImageIcon panelGameOverHeader2 = new ImageIcon("images/gameOverHeader2.png");
    ImageIcon panelGameOverHeader3 = new ImageIcon("images/gameOverHeader3.png");
    ImageIcon panelGameOverHeader4 = new ImageIcon("images/gameOverHeader4.png");
    ImageIcon panelGameOverText1 = new ImageIcon("images/gameOverTextFirstPlace.png");
    ImageIcon panelGameOverText2 = new ImageIcon("images/gameOverTextHighScore.png");
    ImageIcon panelGameOverText3 = new ImageIcon("images/gameOverTextNoHighScore.png");
    ImageIcon panelGameOverGameStatText = new ImageIcon("images/gameOverTextGameStats.png");
    
    Image gameOverBackground1 = panelGameOver1.getImage();
    Image gameOverBackground2 = panelGameOver2.getImage();
    Image gameOverBackground3 = panelGameOver3.getImage();
    Image gameOverHeader1 = panelGameOverHeader1.getImage();
    Image gameOverHeader2 = panelGameOverHeader2.getImage();
    Image gameOverHeader3 = panelGameOverHeader3.getImage();
    Image gameOverHeader4 = panelGameOverHeader4.getImage();
    Image gameOverText1 = panelGameOverText1.getImage();
    Image gameOverText2 = panelGameOverText2.getImage();
    Image gameOverText3 = panelGameOverText3.getImage();
    Image gameOverGameStatText = panelGameOverGameStatText.getImage();
    
    boolean newRecordSet,newHighScoreSet;
    
    public panelGameOver(objPlayer player, int ranking, int[][] highScores)
    {
        super();
        setLayout(null);
        
        this.player = player;
        this.ranking = ranking;
        this.highScores = highScores;
        
        launchGameOverScore = new JButton("Top 10 High Scores");
        launchExitGame = new JButton("Exit Game");
        launchGameOverScore.setBounds(775,225,200,100);
        launchExitGame.setBounds(775,375,200,100);
        launchGameOverScore.addActionListener(this);
        launchExitGame.addActionListener(this);
        add(launchGameOverScore);
        add(launchExitGame);

        //PAGE BUILD STUFF
        if(ranking==1){newRecordSet = true;}
            else {newRecordSet = false;}
        if(ranking>0){newHighScoreSet = true;}
            else {newHighScoreSet = false;}
        
        //testAllOutputsGameOver();   //FOR TESTING
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(gameOverBackground1, 0, 0, this);  //IMG, LEFT, TOP
        g.drawImage(gameOverBackground2, 75, 170, this);  //IMG, LEFT, TOP
        g.drawImage(gameOverBackground3, 75, 450, this);  //IMG, LEFT, TOP
        
        //DETERMINE HEADER OF GAME STATS PANEL BASED ON SCORE RANKING
        if (newHighScoreSet && newRecordSet){g.drawImage(gameOverHeader3, 25, 45, this);}
        else if(newHighScoreSet && !newRecordSet){g.drawImage(gameOverHeader2, 70, 45, this);}
        else {g.drawImage(gameOverHeader1, 100, 45, this);}
        
        //DETERMINE TEXT OF GAME OVER PANEL BASED ON SCORE RANKING
        if (newHighScoreSet && newRecordSet){g.drawImage(gameOverText1, 135, 210, this);}
        else if(newHighScoreSet && !newRecordSet){g.drawImage(gameOverText2, 135, 210, this);}
        else {g.drawImage(gameOverText3, 135, 210, this);}
        
        g.drawImage(gameOverHeader4, 255, 410, this);  //IMG, LEFT, TOP
        g.drawImage(gameOverGameStatText, 105, 480, this);  //IMG, LEFT, TOP
        
        Font f1 = new Font("Gothic", Font.BOLD, 20);
        Font f2 = new Font("Gothic", Font.BOLD, 16);
        Font f3 = new Font("Gothic", Font.BOLD, 14);
        Font f4 = new Font("Gothic", Font.BOLD, 12);
        g.setColor(Color.YELLOW);
        
        //g.drawString("Hussein Ghaleb", 205, 675);  //TEXT, LEFT, TOP
        g.setFont(f3);
        if(player.character==1){g.drawString("Daddy Nittany", 240, 539);}
        else if(player.character==2){g.drawString("Mommy Nittany", 240, 539);}
        else{g.drawString("Baby Nittany", 240, 539);}
        
        if(player.gameTheme==1){g.drawString("Penn State", 240, 563);}
        else if(player.gameTheme==2){g.drawString("Wu-Tang", 240, 563);}
        else if(player.gameTheme==3){g.drawString("8-Bit", 240, 563);}
        else{g.drawString("Diaper", 240, 563);}
        
        g.setFont(f1);
        g.drawString(String.format( "%-10d", player.gameTime ), 240, 589);
        g.drawString(String.format( "%-10d", player.gameScore ), 240, 613);
        
        g.setFont(f4);
        g.drawString(String.format( "%-10d", player.game1Score ), 566, 494);
        g.drawString(String.format( "%-10d", player.game1Time ), 566, 510);
        g.drawString(String.format( "%-10d", player.game2Score ), 566, 542);
        g.drawString(String.format( "%-10d", player.game2Time ), 566, 558);
        g.drawString(String.format( "%-10d", player.game3Score ), 566, 590);
        g.drawString(String.format( "%-10d", player.game3Time ), 566, 606);
        g.drawString(String.format( "%-10d", player.game4Score ), 566, 638);
        g.drawString(String.format( "%-10d", player.game4Time ), 566, 654);
        
        //WRITE RANKING IF HIGH SCORE (BUT NOT TOP SCORE) ACHEIVED
        if(newHighScoreSet && !newRecordSet){
            g.setFont(f2);
            g.setColor(Color.YELLOW);
            g.drawString(String.format( "%-10d", ranking ), 520, 221);
        }
        
        g.setColor(new Color(0, 0, 0, 90));
        g.fillRect(800, 0, 220, 768); //LEFT, TOP, WIDTH, HEIGHT          
    }
    
    public void actionPerformed(ActionEvent event){
        Object obj = event.getSource();
        if (obj == launchGameOverScore){
            p14PanelGameOverHighScore = new panelGameOverScore(player,ranking,highScores);
            p14PanelGameOverHighScore.setBounds(0,0,1024,768);
            removeAll();
            add(p14PanelGameOverHighScore);
            validate();
            repaint();
        }
        if (obj == launchExitGame){
            System.exit(0);
        }
    }
    
    public void testAllOutputsGameOver(){
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
        System.out.println(ranking + "(Score Ranking)");
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println("");
        System.out.println("-=-=-=-= 10TH PLACE -=-=-=-=-");
        System.out.println(highScores[0][0]);
        System.out.println(highScores[0][1]);
        System.out.println(highScores[0][2]);
        System.out.println(highScores[0][3]);
        System.out.println(highScores[0][4]);
        System.out.println(highScores[0][5]);
        System.out.println(highScores[0][6]);
        System.out.println(highScores[0][7]);
        System.out.println(highScores[0][8]);
        System.out.println(highScores[0][9]);
        System.out.println(highScores[0][10]);
        System.out.println(highScores[0][11]);
        
        System.out.println("-=-=-=-= 9TH PLACE -=-=-=-=-");
        System.out.println(highScores[1][0]);
        System.out.println(highScores[1][1]);
        System.out.println(highScores[1][2]);
        System.out.println(highScores[1][3]);
        System.out.println(highScores[1][4]);
        System.out.println(highScores[1][5]);
        System.out.println(highScores[1][6]);
        System.out.println(highScores[1][7]);
        System.out.println(highScores[1][8]);
        System.out.println(highScores[1][9]);
        System.out.println(highScores[1][10]);
        System.out.println(highScores[1][11]);
        
        System.out.println("-=-=-=-= 8TH PLACE -=-=-=-=-");
        System.out.println(highScores[2][0]);
        System.out.println(highScores[2][1]);
        System.out.println(highScores[2][2]);
        System.out.println(highScores[2][3]);
        System.out.println(highScores[2][4]);
        System.out.println(highScores[2][5]);
        System.out.println(highScores[2][6]);
        System.out.println(highScores[2][7]);
        System.out.println(highScores[2][8]);
        System.out.println(highScores[2][9]);
        System.out.println(highScores[2][10]);
        System.out.println(highScores[2][11]);
        
        System.out.println("-=-=-=-= 7TH PLACE -=-=-=-=-");
        System.out.println(highScores[3][0]);
        System.out.println(highScores[3][1]);
        System.out.println(highScores[3][2]);
        System.out.println(highScores[3][3]);
        System.out.println(highScores[3][4]);
        System.out.println(highScores[3][5]);
        System.out.println(highScores[3][6]);
        System.out.println(highScores[3][7]);
        System.out.println(highScores[3][8]);
        System.out.println(highScores[3][9]);
        System.out.println(highScores[3][10]);
        System.out.println(highScores[3][11]);
        
        System.out.println("-=-=-=-= 6TH PLACE -=-=-=-=-");
        System.out.println(highScores[4][0]);
        System.out.println(highScores[4][1]);
        System.out.println(highScores[4][2]);
        System.out.println(highScores[4][3]);
        System.out.println(highScores[4][4]);
        System.out.println(highScores[4][5]);
        System.out.println(highScores[4][6]);
        System.out.println(highScores[4][7]);
        System.out.println(highScores[4][8]);
        System.out.println(highScores[4][9]);
        System.out.println(highScores[4][10]);
        System.out.println(highScores[4][11]);
        
        System.out.println("-=-=-=-= 5TH PLACE -=-=-=-=-");
        System.out.println(highScores[5][0]);
        System.out.println(highScores[5][1]);
        System.out.println(highScores[5][2]);
        System.out.println(highScores[5][3]);
        System.out.println(highScores[5][4]);
        System.out.println(highScores[5][5]);
        System.out.println(highScores[5][6]);
        System.out.println(highScores[5][7]);
        System.out.println(highScores[5][8]);
        System.out.println(highScores[5][9]);
        System.out.println(highScores[5][10]);
        System.out.println(highScores[5][11]);
        
        System.out.println("-=-=-=-= 4TH PLACE -=-=-=-=-");
        System.out.println(highScores[6][0]);
        System.out.println(highScores[6][1]);
        System.out.println(highScores[6][2]);
        System.out.println(highScores[6][3]);
        System.out.println(highScores[6][4]);
        System.out.println(highScores[6][5]);
        System.out.println(highScores[6][6]);
        System.out.println(highScores[6][7]);
        System.out.println(highScores[6][8]);
        System.out.println(highScores[6][9]);
        System.out.println(highScores[6][10]);
        System.out.println(highScores[6][11]);
        
        System.out.println("-=-=-=-= 3RD PLACE -=-=-=-=-");
        System.out.println(highScores[7][0]);
        System.out.println(highScores[7][1]);
        System.out.println(highScores[7][2]);
        System.out.println(highScores[7][3]);
        System.out.println(highScores[7][4]);
        System.out.println(highScores[7][5]);
        System.out.println(highScores[7][6]);
        System.out.println(highScores[7][7]);
        System.out.println(highScores[7][8]);
        System.out.println(highScores[7][9]);
        System.out.println(highScores[7][10]);
        System.out.println(highScores[7][11]);
        
        System.out.println("-=-=-=-= 2ND PLACE -=-=-=-=-");
        System.out.println(highScores[8][0]);
        System.out.println(highScores[8][1]);
        System.out.println(highScores[8][2]);
        System.out.println(highScores[8][3]);
        System.out.println(highScores[8][4]);
        System.out.println(highScores[8][5]);
        System.out.println(highScores[8][6]);
        System.out.println(highScores[8][7]);
        System.out.println(highScores[8][8]);
        System.out.println(highScores[8][9]);
        System.out.println(highScores[8][10]);
        System.out.println(highScores[8][11]);
        
        System.out.println("-=-=-=-= 1ST PLACE -=-=-=-=-");
        System.out.println(highScores[9][0]);
        System.out.println(highScores[9][1]);
        System.out.println(highScores[9][2]);
        System.out.println(highScores[9][3]);
        System.out.println(highScores[9][4]);
        System.out.println(highScores[9][5]);
        System.out.println(highScores[9][6]);
        System.out.println(highScores[9][7]);
        System.out.println(highScores[9][8]);
        System.out.println(highScores[9][9]);
        System.out.println(highScores[9][10]);
        System.out.println(highScores[9][11]);
    }
}