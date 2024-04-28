package view.Animal;

import model.PlayerColor;
import view.ChessPieceComponent;

import javax.swing.*;
import java.awt.*;

public class LionChessComponent extends ChessPieceComponent {
    private PlayerColor owner;

    private boolean selected;
    private ImageIcon lion;

    public LionChessComponent(PlayerColor owner, int size) {
        this.owner = owner;
        this.selected = false;
        if (owner.equals(PlayerColor.BLUE)){
            lion=new ImageIcon("C:\\Users\\16422\\Desktop\\Jungle\\heiheihei\\Jungle\\animalsPicture\\Lion.png");
        } else if (owner.equals(PlayerColor.RED)) {
            lion=new ImageIcon("C:\\Users\\16422\\Desktop\\Jungle\\heiheihei\\Jungle\\animalsPicture\\Lion2.png");
        }
        setSize(size/2, size/2);
        setLocation(0,0);
        setVisible(true);
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }


@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (owner.equals(PlayerColor.BLUE)){
            g.drawImage(lion.getImage(),0,0,getWidth(),getHeight(),null);
        } else if (owner.equals(PlayerColor.RED)) {
            g.drawImage(lion.getImage(), 0, 0, getWidth(), getHeight(), null);
        }
        if (isSelected()) { // Highlights the model if selected.
            g.setColor(Color.GREEN);
            g.drawOval(0, 0, getWidth() , getHeight());
        }
    }
}
