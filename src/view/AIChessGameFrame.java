package view;

import controller.AIcontroller;
import model.Chessboard;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 这个类表示游戏过程中的整个游戏界面，是一切的载体
 */
public class AIChessGameFrame extends JFrame implements ActionListener{
    //    public final Dimension FRAME_SIZE ;
    private final int WIDTH;
    private final int HEIGTH;

    private final int ONE_CHESS_SIZE;
    public static int time=20;
    private ChessboardComponent chessboardComponent;
    private AIcontroller controller;
    JLabel statusLabel;
    JLabel turnLabel;
    JLabel timeLabel;
    JMenuItem ruleItem = new JMenuItem("rules");
    JMenu songJMenu = new JMenu("song");
    JMenuItem start=new JMenuItem("start");
    JMenuItem stop=new JMenuItem("stop");
    JMenuItem reLoginItem = new JMenuItem("reLogin");
    JMenuItem returnMainFrame = new JMenuItem("returnMainFrame");

    public AIChessGameFrame(int width, int height) {
        setTitle("Jungle"); //设置标题
        this.WIDTH = width;
        this.HEIGTH = height;
        this.ONE_CHESS_SIZE = (HEIGTH * 4 / 5) / 9;
        //this.model = controller.getModel();
        setSize(WIDTH, HEIGTH);
        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);
        initJMenuBar();
        addLabel();
        addLabel2();
        addLabel3();
        addLabel4();
        addLabel5();
        addUndoButton();
        addStartButton();
        addLoadButton();
        addSaveButton();
        addRankButton();
        addChessboard();

    }
    private void initJMenuBar() {
        JMenuBar jMenuBar = new JMenuBar();

        JMenu settingJMenu = new JMenu("setting");
        JMenu ruleJMenu = new JMenu("rule");

        songJMenu.add(start);
        songJMenu.add(stop);
        settingJMenu.add(songJMenu);
        settingJMenu.add(reLoginItem);
        settingJMenu.add(returnMainFrame);
        ruleJMenu.add(ruleItem);
        ruleItem.addActionListener(this);
        start.addActionListener(this);
        stop.addActionListener(this);
        reLoginItem.addActionListener(this);
        returnMainFrame.addActionListener(this);

        jMenuBar.add(settingJMenu);
        jMenuBar.add(ruleJMenu);
        this.setJMenuBar(jMenuBar);
    }


    public ChessboardComponent getChessboardComponent() {
        return chessboardComponent;
    }

    public void setChessboardComponent(ChessboardComponent chessboardComponent) {
        this.chessboardComponent = chessboardComponent;
    }

    /**
     * 在游戏面板中添加棋盘
     */
    private void addChessboard() {
        chessboardComponent = new ChessboardComponent(ONE_CHESS_SIZE);
        chessboardComponent.setLocation(HEIGTH / 5, (HEIGTH / 10)-50);
        add(chessboardComponent);
        try {
            BufferedImage background= ImageIO.read(new FileInputStream("D:\\bak\\谁是小卷怪\\CS109PJ\\background\\1.jpg"));
            BufferedImage newBackground=ImageUtils.resizeImage(background,1100,810);
            ImageIO.write(newBackground,"jpg",new File("D:\\bak\\谁是小卷怪\\CS109PJ\\background\\1.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JLabel bg = new JLabel(new ImageIcon("D:\\bak\\谁是小卷怪\\CS109PJ\\background\\1.jpg"));
        bg.setBounds(-200, 0, 1500, 810);
        add(bg);
    }

    /**
     * 在游戏面板中添加标签
     */
    private void addLabel() {
        statusLabel = new JLabel("Jungle");
        statusLabel.setLocation(HEIGTH+20, HEIGTH / 10);
        statusLabel.setSize(200, 60);
        statusLabel.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(statusLabel);
    }

    private void addLabel2() {
        statusLabel = new JLabel("BLUE START");
        statusLabel.setLocation(HEIGTH+20, (HEIGTH / 10)+40);
        statusLabel.setSize(200, 60);
        statusLabel.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(statusLabel);
    }
    private void addLabel3() {
        turnLabel = new JLabel("Turn 0");
        turnLabel.setLocation(HEIGTH+20, (HEIGTH / 10)+80);
        turnLabel.setSize(200, 60);
        turnLabel.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(turnLabel);
    }
    private void addLabel5() {
        timeLabel = new JLabel("T");
        timeLabel.setLocation(HEIGTH+120, (HEIGTH / 10)+120);
        timeLabel.setSize(200, 60);
        timeLabel.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(timeLabel);
    }
    private void addLabel4() {
        timeLabel = new JLabel("Timenow");
        timeLabel.setLocation(HEIGTH+20, (HEIGTH / 10)+120);
        timeLabel.setSize(200, 60);
        timeLabel.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(timeLabel);
    }
    public JLabel getTimeLabel() {
        return timeLabel;
    }

    public void setTimeLabel(JLabel timeLabel) {
        this.timeLabel = timeLabel;
    }

    public JLabel getStatusLabel() {
        return statusLabel;
    }

    public void setStatusLabel(JLabel statusLabel) {
        this.statusLabel = statusLabel;
    }

    public JLabel getTurnLabel() {
        return turnLabel;
    }

    public void setTurnLabel(JLabel turnLabel) {
        this.turnLabel = turnLabel;
    }

    /**
     * 在游戏面板中增加一个按钮，如果按下的话就会显示Hello, world!
     */

    private void addUndoButton() {
        Chessboard chessboard = new Chessboard();
        JButton button = new JButton("Undo");
        button.addActionListener((e) -> {
            JOptionPane.showMessageDialog(this, "提示：即将撤回刚才的操作！");
            controller.undo();

        });
        button.setLocation(HEIGTH, HEIGTH / 10 + 250);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
    }

    private void addStartButton() {
        JButton button = new JButton("Restart");
        button.addActionListener((e) -> {
            JOptionPane.showMessageDialog(this, "提示：棋盘即将重置！");
            controller.restartGame();
        });
        button.setLocation(HEIGTH, HEIGTH / 10 + 180);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
    }
    public void setAIcontroller(AIcontroller AIcontroller) {
        this.controller=AIcontroller;
    }

    private void addLoadButton() {
        JButton button = new JButton("Load");
        button.setLocation(HEIGTH, HEIGTH / 10 + 320);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
        button.addActionListener(e -> {
            System.out.println("Click load");
            String path = JOptionPane.showInputDialog(this,"Input Game Path here");
            controller.loadGameFromFile(path);
        });
    }
    private void addRankButton() {
        JButton button = new JButton("Rank");
        button.setLocation(HEIGTH, HEIGTH / 10 + 460);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
        button.addActionListener(e -> {
            System.out.println("Click load");
            controller.rank();
        });
    }
    private void addSaveButton() {
        JButton button = new JButton("Save");
        button.setLocation(HEIGTH, HEIGTH / 10 + 390);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
        button.addActionListener(e -> {
            System.out.println("Click save");
            // String path = JOptionPane.showInputDialog(this,"Save Path here");
            String[][] array = controller.HEArray();
            controller.saveFile(array);
            controller.saveTurn(controller.getTurn());
        });
    }
    static Clip clip;
    public static void playMusic(){
        try {
            File path=new File("D:\\12\\程设\\project\\Jungle\\Jungle\\Music\\Bgm.wav");
            AudioInputStream inputStream= AudioSystem.getAudioInputStream(path);
            clip=AudioSystem.getClip();
            clip.open(inputStream);
            FloatControl control=(FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            control.setValue(-10.0f);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void stopMusic(){
        clip.stop();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj==start) {
            System.out.println("song");
            playMusic();
        } else if (obj==stop) {
            System.out.println("song");
            stopMusic();
        }else if (obj==reLoginItem){
            this.setVisible(false);
            new LoginJFrame();
        }else if (obj==returnMainFrame){
            this.setVisible(false);
            new MainFrame();
        } else if (obj == ruleItem) {
            System.out.println("rules");
            JDialog rule=new JDialog();
            JLabel rules=new JLabel("<html><body>"+"1、Game Initialization: At the beginning, all 16 pieces are put into the chessboard as the initial state."
                    +"<br>"+"2、Game Progress: The player with blue moves first. Two players take the turn alternatively until the game is finished. When a player takes turn, he/she can select one of his pieces and do one of the following:"
                    +"<br>"+"1）Moving one square horizontally or vertically. For lion, tiger and rat, they also have special movements related to the river squares, which have been introduced previously."
                    +"<br>"+"2)Capturing an opposing piece. In all captures, the captured pieces is removed from the board and the square is occupied by the capturing piece. A piece can capture any enemy piece following the rules introduced in Pieces."
                    +"<br>"+"3、Game Termination: A player loses the game if one of the following happens:"
                    +"<br>"+"1)The den is entered by his/her opponents."
                    +"<br>"+"2)All of his/her pieces are captured and he/her is unable to do any movement."
                    +"<body></html>");
            rules.setForeground(Color.BLACK);
            rules.setBounds(50,100,250,150);
            rule.getContentPane().add(rules);
            rule.setSize(350,350);
            rule.setAlwaysOnTop(true);
            rule.setLocationRelativeTo(null);
            rule.setVisible(true);
        }
    }
}
