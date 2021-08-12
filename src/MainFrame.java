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

public class MainFrame extends JFrame
{

    ControlPanel mjp;

    public MainFrame()
    {
        super("Group Project Deliverable 2 (Team 6)");
        mjp = new ControlPanel();
        add(mjp);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1024, 768);
        setVisible(true);
        setResizable(false); //DO NOT ALLOW WINDOW TO BE RESIZED
    }

}
