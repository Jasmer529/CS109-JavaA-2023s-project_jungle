package view;

import controller.GameController;
import model.Chessboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainFrame extends JFrame implements MouseListener,ActionListener {
    JFrame frame=new JFrame("Jungle");
    final int WIDTH=1100;
    final int HEIGHT=500;
    JButton Button1=new JButton("Play with AI");
    JButton Button2=new JButton("Play with yourself");
    JLabel xLabel =new JLabel("Jungle");

    public MainFrame()  {
        initFrame();
        this.frame.setLocationRelativeTo(null); // Center the window.
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        this.frame.setLayout(null);
        this.frame.setAlwaysOnTop(true);
        this.frame.setVisible(true);
    }

    public void initFrame() {
        this.frame.setBounds((ScreenUtils.getScreenWidth()-WIDTH)/2,(ScreenUtils.getScreenHeight()-HEIGHT)/2,WIDTH,HEIGHT);
        this.frame.setResizable(false);

        xLabel.setFont(new Font("Rockwell", Font.BOLD, 56));
        xLabel.setBounds((ScreenUtils.getScreenWidth()-WIDTH)/2+200,0,400,100);
        this.frame.getContentPane().add(xLabel);

        Button1.setBounds(100,150,150,50);
        Button1.addMouseListener(this);
        this.frame.getContentPane().add(Button1);
        Button2.setBounds(100,250,150,50);
        Button2.addMouseListener(this);
        this.frame.getContentPane().add(Button2);

        JLabel bg = new JLabel(new ImageIcon("C:\\Users\\16422\\Desktop\\Jungle\\heiheihei\\Jungle\\background\\main.jpg"));
        bg.setBounds((ScreenUtils.getScreenWidth()-WIDTH)/2, (ScreenUtils.getScreenHeight()-HEIGHT)/2, 1100, 500);
        this.frame.getContentPane().add(bg);

        this.frame.setVisible(true);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == Button1) {
            System.out.println("click Button1");
            frame.setVisible(false);
            new LoginJFrame();

        } else if (e.getSource() == Button2) {
            System.out.println("click Button2");
            frame.setVisible(false);
            ChessGameFrame mainFrame = new ChessGameFrame(1100, 810);
            GameController gameController = new GameController(mainFrame.getChessboardComponent(), new Chessboard());
            mainFrame.setGamecontroller(gameController);
            gameController.setStatusLabel(mainFrame.getStatusLabel());
            gameController.setTurnLabel(mainFrame.getTurnLabel());
            gameController.setTimeLabel(mainFrame.getTimeLabel());
            mainFrame.setVisible(true);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
