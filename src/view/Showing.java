package view;


import controller.GameController;
import model.Chessboard;
import model.ChessboardPoint;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;


public class Showing extends ChessPieceComponent{
    public BufferedImage image;
    public Chessboard model;
    public GameController gameController;
    public void showController(GameController gameController) {
        this.gameController = gameController;
        this.model=gameController.getModel();
    }

    public GameController getGameController() {
        return gameController;
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public Showing(ChessboardPoint point, int size) {

        setSize(size/2, size/2);
        setLocation(0,0);
        loadImage();
        setVisible(true);
        setName("showing");

    }
    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);

    }
    private void loadImage() {
        try {
            {
                image = ImageIO.read(new File("C:\\Users\\16422\\Desktop\\Jungle\\heiheihei\\Jungle\\background\\show.png")); // Replace "path/to/red_dog.jpg" with the actual path to your red dog image file.
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
