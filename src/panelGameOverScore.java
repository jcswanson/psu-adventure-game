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

public class panelGameOverScore extends JPanel implements ActionListener {

    panelGameOver p13PanelGameOver;
    objPlayer player;
    int ranking;
    int[][] highScores;
    
    JButton launchGameOverScore,launchExitGame;

    ImageIcon panelGameOver1 = new ImageIcon("images/background1.jpg");
    ImageIcon panelGameOver2 = new ImageIcon("images/gameOverBackground1.png");
    ImageIcon panelGameOverHighScores3 = new ImageIcon("images/headerHighScores.png");
    ImageIcon panelGameOverHighScores4 = new ImageIcon("images/gameOverTopTenScores.png");
    ImageIcon panelGameOverHighScores5 = new ImageIcon("images/gameOverScoreHeader.png");
    ImageIcon panelGameOverHighScores6 = new ImageIcon("images/gameOverScoreRankings.png");
    ImageIcon panelGameOverHighScoresHighlightLarge = new ImageIcon("images/gameOverScoreHighlightLarge.png");
    ImageIcon panelGameOverHighScoresHighlightSmall = new ImageIcon("images/gameOverScoreHighlightSmall.png");
    
    Image gameOverBackground1 = panelGameOver1.getImage();
    Image gameOverBackground2 = panelGameOver2.getImage();
    Image gameOverHighScoresTitle1 = panelGameOverHighScores3.getImage();
    Image gameOverHighScoresTitle2 = panelGameOverHighScores4.getImage();
    Image gameOverHighScoresHeader1 = panelGameOverHighScores5.getImage();
    Image gameOverHighScoresHeader2 = panelGameOverHighScores6.getImage();
    Image gameOverHighlightL = panelGameOverHighScoresHighlightLarge.getImage();
    Image gameOverHighlightS = panelGameOverHighScoresHighlightSmall.getImage();
    
    public panelGameOverScore(objPlayer player, int ranking, int[][] highScores)
    {
        super();
        setLayout(null);
        
        this.player = player;
        this.ranking = ranking;
        this.highScores = highScores;
        
        launchGameOverScore = new JButton("Return to Game Stats");
        launchExitGame = new JButton("Exit Game");
        launchGameOverScore.setBounds(775,225,200,100);
        launchGameOverScore.addActionListener(this);
        launchExitGame.setBounds(775,375,200,100);
        launchExitGame.addActionListener(this);
        add(launchGameOverScore);
        add(launchExitGame);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(gameOverBackground1, 0, 0, this);  //IMG, LEFT, TOP
        g.drawImage(gameOverBackground2, 90, 155, this);  //IMG, LEFT, TOP
        
        //HIGHLIGHT HIGH SCORE AREA IF HIGH SCORE IS ACHEIVED
        if(ranking==1){g.drawImage(gameOverHighlightL, 111, 235, this);}  //IMG, LEFT, TOP}
        if(ranking==2){g.drawImage(gameOverHighlightL, 111, 304, this);}  //IMG, LEFT, TOP}
        if(ranking==3){g.drawImage(gameOverHighlightL, 111, 371, this);}  //IMG, LEFT, TOP}
        if(ranking==4){g.drawImage(gameOverHighlightS, 111, 447, this);}  //IMG, LEFT, TOP}
        if(ranking==5){g.drawImage(gameOverHighlightS, 111, 481, this);}  //IMG, LEFT, TOP}
        if(ranking==6){g.drawImage(gameOverHighlightS, 111, 515, this);}  //IMG, LEFT, TOP}
        if(ranking==7){g.drawImage(gameOverHighlightS, 111, 549, this);}  //IMG, LEFT, TOP}
        if(ranking==8){g.drawImage(gameOverHighlightS, 111, 583, this);}  //IMG, LEFT, TOP}
        if(ranking==9){g.drawImage(gameOverHighlightS, 111, 617, this);}  //IMG, LEFT, TOP}
        if(ranking==10){g.drawImage(gameOverHighlightS, 111, 652, this);}  //IMG, LEFT, TOP}
        
        g.drawImage(gameOverHighScoresTitle1, 76, 20, this);  //IMG, LEFT, TOP
        g.drawImage(gameOverHighScoresTitle2, 250, 130, this);  //IMG, LEFT, TOP
        g.drawImage(gameOverHighScoresHeader1, 210, 210, this);  //IMG, LEFT, TOP
        g.drawImage(gameOverHighScoresHeader2, 128, 245, this);  //IMG, LEFT, TOP
        g.drawImage(gameOverHighScoresHeader2, 128, 245, this);  //IMG, LEFT, TOP

        Font f1 = new Font("Gothic", Font.BOLD, 24);
        Font f2 = new Font("Gothic", Font.BOLD, 18);
        Font f3 = new Font("Gothic", Font.BOLD, 14);
        Font f4 = new Font("Gothic", Font.BOLD, 12);
        g.setColor(Color.YELLOW);
        
        //DRAW FIRST PLACE DATA
        g.setFont(f1);
        g.drawString(String.format( "%-10d", highScores[9][0] ), 208, 267);
        g.drawString(String.format( "%-10d", highScores[9][1] ), 358, 267);
        g.setFont(f3);
        if(highScores[9][2]==1){g.drawString("Daddy Nittany", 455, 263);}
            else if(highScores[9][2]==2){g.drawString("Mommy Nittany", 455, 263);}
            else{g.drawString("Baby Nittany", 456, 263);}
        if(highScores[9][3]==1){g.drawString("Penn State", 580, 263);}
            else if(highScores[9][3]==2){g.drawString("Wu-Tang", 580, 263);}
            else if(highScores[9][3]==3){g.drawString("8-Bit", 580, 263);}
            else{g.drawString("Diaper", 580, 263);}

        //DRAW SECOND PLACE DATA
        g.setFont(f1);
        g.drawString(String.format( "%-10d", highScores[8][0] ), 208, 338);
        g.drawString(String.format( "%-10d", highScores[8][1] ), 358, 338);
        g.setFont(f3);
        if(highScores[8][2]==1){g.drawString("Daddy Nittany", 455, 333);}
            else if(highScores[8][2]==2){g.drawString("Mommy Nittany", 455, 333);}
            else{g.drawString("Baby Nittany", 456, 333);}
        if(highScores[8][3]==1){g.drawString("Penn State", 580, 333);}
            else if(highScores[8][3]==2){g.drawString("Wu-Tang", 580, 333);}
            else if(highScores[8][3]==3){g.drawString("8-Bit", 580, 333);}
            else{g.drawString("Diaper", 580, 333);}
        
        //DRAW THIRD PLACE DATA
        g.setFont(f1);
        g.drawString(String.format( "%-10d", highScores[7][0] ), 208, 405);
        g.drawString(String.format( "%-10d", highScores[7][1] ), 358, 405);
        g.setFont(f3);
        if(highScores[7][2]==1){g.drawString("Daddy Nittany", 455, 401);}
            else if(highScores[7][2]==2){g.drawString("Mommy Nittany", 455, 401);}
            else{g.drawString("Baby Nittany", 456, 401);}
        if(highScores[7][3]==1){g.drawString("Penn State", 580, 401);}
            else if(highScores[7][3]==2){g.drawString("Wu-Tang", 580, 401);}
            else if(highScores[7][3]==3){g.drawString("8-Bit", 580, 401);}
            else{g.drawString("Diaper", 580, 401);}
        
        //DRAW FOURTH PLACE DATA
        g.setFont(f2);
        g.drawString(String.format( "%-10d", highScores[6][0] ), 217, 475);
        g.drawString(String.format( "%-10d", highScores[6][1] ), 363, 475);
        g.setFont(f4);
        if(highScores[6][2]==1){g.drawString("Daddy Nittany", 460, 473);}
            else if(highScores[6][2]==2){g.drawString("Mommy Nittany", 460, 473);}
            else{g.drawString("Baby Nittany", 461, 473);}
        if(highScores[6][3]==1){g.drawString("Penn State", 582, 473);}
            else if(highScores[6][3]==2){g.drawString("Wu-Tang", 582, 473);}
            else if(highScores[6][3]==3){g.drawString("8-Bit", 582, 473);}
            else{g.drawString("Diaper", 582, 473);}
        
        //DRAW FIFTH PLACE DATA
        g.setFont(f2);
        g.drawString(String.format( "%-10d", highScores[5][0] ), 217, 509);
        g.drawString(String.format( "%-10d", highScores[5][1] ), 363, 509);
        g.setFont(f4);
        if(highScores[5][2]==1){g.drawString("Daddy Nittany", 460, 507);}
            else if(highScores[5][2]==2){g.drawString("Mommy Nittany", 460, 507);}
            else{g.drawString("Baby Nittany", 461, 507);}
        if(highScores[5][3]==1){g.drawString("Penn State", 582, 507);}
            else if(highScores[5][3]==2){g.drawString("Wu-Tang", 582, 507);}
            else if(highScores[5][3]==3){g.drawString("8-Bit", 582, 507);}
            else{g.drawString("Diaper", 582, 507);}
        
        //DRAW SIXTH PLACE DATA
        g.setFont(f2);
        g.drawString(String.format( "%-10d", highScores[4][0] ), 217, 543);
        g.drawString(String.format( "%-10d", highScores[4][1] ), 363, 543);
        g.setFont(f4);
        if(highScores[4][2]==1){g.drawString("Daddy Nittany", 460, 541);}
            else if(highScores[4][2]==2){g.drawString("Mommy Nittany", 460, 541);}
            else{g.drawString("Baby Nittany", 461, 541);}
        if(highScores[4][3]==1){g.drawString("Penn State", 582, 541);}
            else if(highScores[4][3]==2){g.drawString("Wu-Tang", 582, 541);}
            else if(highScores[4][3]==3){g.drawString("8-Bit", 582, 541);}
            else{g.drawString("Diaper", 582, 541);}
        
        //DRAW SEVENTH PLACE DATA
        g.setFont(f2);
        g.drawString(String.format( "%-10d", highScores[3][0] ), 217, 577);
        g.drawString(String.format( "%-10d", highScores[3][1] ), 363, 577);
        g.setFont(f4);
        if(highScores[3][2]==1){g.drawString("Daddy Nittany", 460, 575);}
            else if(highScores[3][2]==2){g.drawString("Mommy Nittany", 460, 575);}
            else{g.drawString("Baby Nittany", 461, 575);}
        if(highScores[3][3]==1){g.drawString("Penn State", 582, 575);}
            else if(highScores[3][3]==2){g.drawString("Wu-Tang", 582, 575);}
            else if(highScores[3][3]==3){g.drawString("8-Bit", 582, 575);}
            else{g.drawString("Diaper", 582, 575);}
        
        //DRAW EIGTH PLACE DATA
        g.setFont(f2);
        g.drawString(String.format( "%-10d", highScores[2][0] ), 217, 611);
        g.drawString(String.format( "%-10d", highScores[2][1] ), 363, 611);
        g.setFont(f4);
        if(highScores[2][2]==1){g.drawString("Daddy Nittany", 460, 609);}
            else if(highScores[2][2]==2){g.drawString("Mommy Nittany", 460, 609);}
            else{g.drawString("Baby Nittany", 461, 609);}
        if(highScores[2][3]==1){g.drawString("Penn State", 582, 609);}
            else if(highScores[2][3]==2){g.drawString("Wu-Tang", 582, 609);}
            else if(highScores[2][3]==3){g.drawString("8-Bit", 582, 609);}
            else{g.drawString("Diaper", 582, 609);}
        
        //DRAW NINTH PLACE DATA
        g.setFont(f2);
        g.drawString(String.format( "%-10d", highScores[1][0] ), 217, 645);
        g.drawString(String.format( "%-10d", highScores[1][1] ), 363, 645);
        g.setFont(f4);
        if(highScores[1][2]==1){g.drawString("Daddy Nittany", 460, 643);}
            else if(highScores[1][2]==2){g.drawString("Mommy Nittany", 460, 643);}
        else{g.drawString("Baby Nittany", 461, 643);}
            if(highScores[1][3]==1){g.drawString("Penn State", 582, 643);}
            else if(highScores[1][3]==2){g.drawString("Wu-Tang", 582, 643);}
            else if(highScores[1][3]==3){g.drawString("8-Bit", 582, 643);}
            else{g.drawString("Diaper", 582, 643);}
        
        //DRAW TENTH PLACE DATA
        g.setFont(f2);
        g.drawString(String.format( "%-10d", highScores[0][0] ), 217, 679);
        g.drawString(String.format( "%-10d", highScores[0][1] ), 363, 679);
        g.setFont(f4);
        if(highScores[0][2]==1){g.drawString("Daddy Nittany", 460, 677);}
            else if(highScores[0][2]==2){g.drawString("Mommy Nittany", 460, 677);}
            else{g.drawString("Baby Nittany", 461, 677);}
        if(highScores[0][3]==1){g.drawString("Penn State", 582, 677);}
            else if(highScores[0][3]==2){g.drawString("Wu-Tang", 582, 677);}
            else if(highScores[0][3]==3){g.drawString("8-Bit", 582, 677);}
            else{g.drawString("Diaper", 582, 677);}

        //DRAW BACKGROUND FOR BUTTONS ON RIGHT-SIDE
        g.setColor(new Color(0, 0, 0, 90));
        g.fillRect(800, 0, 220, 768); //LEFT, TOP, WIDTH, HEIGHT          
    }
    
    public void actionPerformed(ActionEvent event){
        Object obj = event.getSource();
        if (obj == launchGameOverScore){
            p13PanelGameOver = new panelGameOver(player,ranking,highScores);
            p13PanelGameOver.setBounds(0,0,1024,768);
            removeAll();
            add(p13PanelGameOver);
            validate();
            repaint();
        }
        if (obj == launchExitGame){
            System.exit(0);
        }
    }
}