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

public class panelInstructionTab4 extends JPanel
{

    ImageIcon panelinstructions1 = new ImageIcon("images/background3.png");
    ImageIcon panelinstructions2 = new ImageIcon("images/instructionsPanel4.png");
    Image panelBackground = panelinstructions1.getImage();
    Image panelForeground = panelinstructions2.getImage();

    public panelInstructionTab4()
    {
        setBackground(null);
    }
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(panelBackground, 0, 0, this);  //IMG, LEFT, TOP
        g.drawImage(panelForeground, 15, 92, this);  //IMG, LEFT, TOP
        
        Font f1 = new Font("Gothic", Font.BOLD, 20);
        g.setFont(f1);
        g.setColor(Color.WHITE);
        g.drawString("Game 2 @ The University Park Campus", 30, 50);
        Font f2 = new Font("Gothic", Font.BOLD, 16);
        g.setFont(f2);
        g.drawString("Rock-Paper-Scissors", 31, 67);
    }

}