package view;

import javax.swing.*;
import java.awt.*;

public class BackgroundJPane extends JPanel {
    private Image backIcon;
    public BackgroundJPane(Image backIcon) {
        this.backIcon = backIcon;
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(backIcon,0,0,this.getWidth(),this.getHeight(),null);
    }
}
