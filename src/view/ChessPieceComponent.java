package view;

import model.PlayerColor;

import javax.swing.*;
import java.awt.*;

public class ChessPieceComponent extends JComponent {

    private PlayerColor owner;
    public String name;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    private boolean selected;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

}
