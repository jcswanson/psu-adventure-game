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

public class panelChooseCharacter extends JPanel implements MouseListener, ActionListener {

    JButton b1, 
            daddyNittanyButton, 
            mommyNittanyButton, 
            babyNittanyButton;
    
    JLabel daddyNittanyButtonHighlight, 
           mommyNittanyButtonHighlight,
           babyNittanyButtonHighlight,
           daddyNittanyButtonSelected,
           mommyNittanyButtonSelected,
           babyNittanyButtonSelected,
           daddyNittanyButtonTitle, 
           mommyNittanyButtonTitle,
           babyNittanyButtonTitle,
           daddyNittanyCharText, 
           mommyNittanyCharText,
           babyNittanyCharText;
    
    objPlayer player;
    
    ImageIcon paneloptions1 = new ImageIcon("images/background2.jpg");
    ImageIcon paneloptions2 = new ImageIcon("images/overlay1.png");
    ImageIcon paneloptions3 = new ImageIcon("images/headerChooseChar.png");
    Image optionsBackground1 = paneloptions1.getImage();
    Image optionsOverlay1 = paneloptions2.getImage();
    Image optionsTitle1 = paneloptions3.getImage();

    public panelChooseCharacter(objPlayer player)
    {
        super();
        setLayout(null);
        
        this.player = player;
        
        b1 = new JButton("Next...");
        b1.setBounds(775,300,200,100);
        add(b1);
        
        buildCharButtons();
        charSelected();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(optionsBackground1, 0, 0, this);  //IMG, LEFT, TOP
        g.drawImage(optionsOverlay1, 0, 240, this);  //IMG, LEFT, TOP
        g.drawImage(optionsTitle1, 60, 25, this);  //IMG, LEFT, TOP     
        
        g.setColor(new Color(0, 0, 0, 90));
        g.fillRect(800, 0, 220, 768); //LEFT, TOP, WIDTH, HEIGHT          
    }

    public void actionPerformed(ActionEvent event){
        Object obj = event.getSource();
        if (obj == daddyNittanyButton){
            this.player.character = 1;
            charSelected();
        }
        if (obj == mommyNittanyButton){
            this.player.character = 2;
            charSelected();
        }
        if (obj == babyNittanyButton){
            this.player.character = 3;
            charSelected();
        }
    }
    
    
    //==========================================================================
    // MOUSE LISTENER
    public void mouseClicked(MouseEvent e){ }
    public void mouseEntered(MouseEvent e) {
        if (e.getComponent()==daddyNittanyButton){
            daddyNittanyButtonHighlight.setVisible(true);
            daddyNittanyButton.setCursor(new Cursor(Cursor.HAND_CURSOR));}
        if (e.getComponent()==mommyNittanyButton){
            mommyNittanyButtonHighlight.setVisible(true);
            mommyNittanyButton.setCursor(new Cursor(Cursor.HAND_CURSOR));}
        if (e.getComponent()==babyNittanyButton){
            babyNittanyButtonHighlight.setVisible(true);
            babyNittanyButton.setCursor(new Cursor(Cursor.HAND_CURSOR));}
    }
    public void mouseExited(MouseEvent e) { 
        if (e.getComponent()==daddyNittanyButton){
            daddyNittanyButtonHighlight.setVisible(false);}
        if (e.getComponent()==mommyNittanyButton){
            mommyNittanyButtonHighlight.setVisible(false);}
        if (e.getComponent()==babyNittanyButton){
            babyNittanyButtonHighlight.setVisible(false);}
    }
    public void mousePressed(MouseEvent e) { }
    public void mouseReleased(MouseEvent e) { }
    //==========================================================================

    public void buildCharButtons(){
        daddyNittanyButton = new JButton(new ImageIcon("images/daddynittany1.png"));
        daddyNittanyButton.setBounds(55,490,226,174);
        daddyNittanyButton.setBorder(BorderFactory.createEmptyBorder());
        daddyNittanyButton.setContentAreaFilled(false);
        daddyNittanyButton.addActionListener(this);
        daddyNittanyButton.addMouseListener(this);
        daddyNittanyButtonHighlight = new JLabel(new ImageIcon("images/charHighlight.gif"));
        daddyNittanyButtonHighlight.setBounds(40,450,300,300);
        daddyNittanyButtonHighlight.setBorder(BorderFactory.createEmptyBorder());
        daddyNittanyButtonHighlight.setVisible(false);
        daddyNittanyButtonSelected = new JLabel(new ImageIcon("images/charSelect.png"));
        daddyNittanyButtonSelected.setBounds(40,450,300,300);
        daddyNittanyButtonSelected.setBorder(BorderFactory.createEmptyBorder());
        daddyNittanyButtonSelected.setVisible(false);
        daddyNittanyButtonTitle = new JLabel(new ImageIcon("images/charTitleDaddy.png"));
        daddyNittanyButtonTitle.setBounds(295,165,273,72);
        daddyNittanyButtonTitle.setBorder(BorderFactory.createEmptyBorder());
        daddyNittanyButtonTitle.setVisible(false);   
        daddyNittanyCharText = new JLabel("<html><center><font color=#ffffff size=5>"
                + "The Daddy Nittany is capable of performing strongly in terms of strength and can out perform the Mommy and Baby.  "
                + "However, Daddy is not as smart as the Mommy and not as lucky as the Baby!");
        Font font = daddyNittanyCharText.getFont().deriveFont(Font.BOLD);
        daddyNittanyCharText.setFont(font);
        daddyNittanyCharText.setBounds(170,200,500,175);
        daddyNittanyCharText.setBorder(BorderFactory.createEmptyBorder());
        daddyNittanyCharText.setVisible(false); 
        
        mommyNittanyButton = new JButton(new ImageIcon("images/mommynittany1.png"));
        mommyNittanyButton.setBounds(310,420,247,125);
        mommyNittanyButton.setBorder(BorderFactory.createEmptyBorder());
        mommyNittanyButton.setContentAreaFilled(false);
        mommyNittanyButton.addActionListener(this);
        mommyNittanyButton.addMouseListener(this);   
        mommyNittanyButtonHighlight = new JLabel(new ImageIcon("images/charHighlight.gif"));
        mommyNittanyButtonHighlight.setBounds(295,340,300,300);
        mommyNittanyButtonHighlight.setBorder(BorderFactory.createEmptyBorder());
        mommyNittanyButtonHighlight.setVisible(false);
        mommyNittanyButtonSelected = new JLabel(new ImageIcon("images/charSelect.png"));
        mommyNittanyButtonSelected.setBounds(295,340,300,300);
        mommyNittanyButtonSelected.setBorder(BorderFactory.createEmptyBorder());
        mommyNittanyButtonSelected.setVisible(false);      
        mommyNittanyButtonTitle = new JLabel(new ImageIcon("images/charTitleMommy.png"));
        mommyNittanyButtonTitle.setBounds(295,165,289,72);
        mommyNittanyButtonTitle.setBorder(BorderFactory.createEmptyBorder());
        mommyNittanyButtonTitle.setVisible(false);      
        mommyNittanyCharText = new JLabel("<html><center><font color=#ffffff size=5>"
                + "The Mommy Nittany is much smarter than Daddy and Baby.  She also has a "
                + "magical ability to 'Talk Trash'.  She is not as strong as Daddy and"
                + "not as lucky as the Baby!");
        mommyNittanyCharText.setFont(font);
        mommyNittanyCharText.setBounds(170,200,500,175);
        mommyNittanyCharText.setBorder(BorderFactory.createEmptyBorder());
        mommyNittanyCharText.setVisible(false); 
        
        babyNittanyButton = new JButton(new ImageIcon("images/babynittany1.png"));
        babyNittanyButton.setBounds(555,555,142,100);
        babyNittanyButton.setBorder(BorderFactory.createEmptyBorder());
        babyNittanyButton.setContentAreaFilled(false);
        babyNittanyButton.addActionListener(this);
        babyNittanyButton.addMouseListener(this);          
        babyNittanyButtonHighlight = new JLabel(new ImageIcon("images/charHighlight.gif"));
        babyNittanyButtonHighlight.setBounds(485,450,300,300);
        babyNittanyButtonHighlight.setBorder(BorderFactory.createEmptyBorder());
        babyNittanyButtonHighlight.setVisible(false);
        babyNittanyButtonSelected = new JLabel(new ImageIcon("images/charSelect.png"));
        babyNittanyButtonSelected.setBounds(485,450,300,300);
        babyNittanyButtonSelected.setBorder(BorderFactory.createEmptyBorder());
        babyNittanyButtonSelected.setVisible(false);
        babyNittanyButtonTitle = new JLabel(new ImageIcon("images/charTitleBaby.png"));
        babyNittanyButtonTitle.setBounds(295,165,251,72);
        babyNittanyButtonTitle.setBorder(BorderFactory.createEmptyBorder());
        babyNittanyButtonTitle.setVisible(false);   
        babyNittanyCharText = new JLabel("<html><center><font color=#ffffff size=5>"
                + "The Baby is not very strong or very smart but she is very very lucky!  "
                + "Thats right, the baby is a she Nittany and loves Daddy more than Mommy, "
                + "hence the additional amount of luck!");
        babyNittanyCharText.setFont(font);
        babyNittanyCharText.setBounds(170,200,500,175);
        babyNittanyCharText.setBorder(BorderFactory.createEmptyBorder());
        babyNittanyCharText.setVisible(false); 
        
        add(daddyNittanyButton);
        add(mommyNittanyButton);
        add(babyNittanyButton);
        add(daddyNittanyButtonHighlight);        
        add(mommyNittanyButtonHighlight); 
        add(babyNittanyButtonHighlight); 
        add(daddyNittanyButtonSelected);        
        add(mommyNittanyButtonSelected); 
        add(babyNittanyButtonSelected);    
        add(daddyNittanyButtonTitle); 
        add(mommyNittanyButtonTitle);
        add(babyNittanyButtonTitle);
        add(daddyNittanyCharText);
        add(mommyNittanyCharText);
        add(babyNittanyCharText);     
    }
    
    public void charSelected(){
        player.playerCharacterSelected = true;
        if(player.character == 1){
            daddyNittanyButtonSelected.setVisible(true);
            daddyNittanyButtonTitle.setVisible(true);
            daddyNittanyCharText.setVisible(true);
            mommyNittanyButtonSelected.setVisible(false);
            mommyNittanyButtonTitle.setVisible(false);
            mommyNittanyCharText.setVisible(false); 
            babyNittanyButtonSelected.setVisible(false);
            babyNittanyButtonTitle.setVisible(false);
            babyNittanyCharText.setVisible(false); 
        }
        if(player.character == 2){
            mommyNittanyButtonSelected.setVisible(true);
            mommyNittanyButtonTitle.setVisible(true);
            mommyNittanyCharText.setVisible(true); 
            daddyNittanyButtonSelected.setVisible(false);
            daddyNittanyButtonTitle.setVisible(false);
            daddyNittanyCharText.setVisible(false); 
            babyNittanyButtonSelected.setVisible(false);
            babyNittanyButtonTitle.setVisible(false);
            babyNittanyCharText.setVisible(false); 
        }
        if(player.character == 3){
            babyNittanyButtonSelected.setVisible(true);
            babyNittanyButtonTitle.setVisible(true);
            babyNittanyCharText.setVisible(true); 
            daddyNittanyButtonSelected.setVisible(false);
            daddyNittanyButtonTitle.setVisible(false);
            daddyNittanyCharText.setVisible(false);
            mommyNittanyButtonSelected.setVisible(false);
            mommyNittanyButtonTitle.setVisible(false);
            mommyNittanyCharText.setVisible(false); 
        }
    }
    

}