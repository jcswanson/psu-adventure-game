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

public class panelInstructionTab1 extends JPanel
{

    ImageIcon panelinstructions1 = new ImageIcon("images/background3.png");
    ImageIcon panelinstructions2 = new ImageIcon("images/instructionsPanel1.png");
    Image panelBackground = panelinstructions1.getImage();
    Image panelForeground = panelinstructions2.getImage();

    public panelInstructionTab1()
    {
        setBackground(null);
    }
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(panelBackground, 0, 0, this);  //IMG, LEFT, TOP
        g.drawImage(panelForeground, 15, 25, this);  //IMG, LEFT, TOP
    }

}