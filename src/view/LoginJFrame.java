package view;


import controller.AIcontroller;
import controller.GameController;
import model.Chessboard;
import model.PlayerColor;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class LoginJFrame implements MouseListener, ActionListener {
        JFrame loginFrame=new JFrame("Login");
        final int WIDTH=500;
        final int HEIGHT=350;
    JLabel xLabel =new JLabel("name:");
    JLabel yLabel =new JLabel("password:");
    JTextField xTextField=new JTextField();
    JPasswordField yTextField=new JPasswordField();
    JButton loginButton=new JButton("login");
    JButton registerButton=new JButton("register");

        public LoginJFrame(){
            init();
            this.loginFrame.setLocationRelativeTo(null); // Center the window.
            this.loginFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
            this.loginFrame.setLayout(null);
            this.loginFrame.setAlwaysOnTop(true);
            this.loginFrame.setVisible(true);
        }
        public void init(){
            this.loginFrame.setBounds((ScreenUtils.getScreenWidth()-WIDTH)/2,(ScreenUtils.getScreenHeight()-HEIGHT)/2,WIDTH,HEIGHT);
            this.loginFrame.setResizable(false);

            xLabel.setBounds(80,100,100,20);
            xLabel.setFont(new Font("Rockwell", Font.BOLD, 16));
            this.loginFrame.getContentPane().add(xLabel);
            xTextField.setBounds(180,100,200,20);
            this.loginFrame.getContentPane().add(xTextField);

            yLabel.setBounds(80,150,100,20);
            yLabel.setFont(new Font("Rockwell", Font.BOLD, 16));
            this.loginFrame.getContentPane().add(yLabel);
            yTextField.setBounds(180,150,200,20);
            this.loginFrame.getContentPane().add(yTextField);


            loginButton.setBounds(80,210,90,20);
            loginButton.addMouseListener(this);
            this.loginFrame.getContentPane().add(loginButton);
            registerButton.setBounds(320,210,90,20);
            registerButton.addMouseListener(this);
            this.loginFrame.getContentPane().add(registerButton);

            JLabel bg = new JLabel(new ImageIcon("D:\\12\\程设\\project\\Jungle\\Jungle\\background\\login.jpg"));
            bg.setBounds((ScreenUtils.getScreenWidth()-WIDTH)/2, (ScreenUtils.getScreenHeight()-HEIGHT)/2, 470, 390);
            this.loginFrame.getContentPane().add(bg);

            this.loginFrame.setVisible(true);
        }
        public void showJDialog(String content){
            JDialog jDialog=new JDialog();
            jDialog.setFont(new Font("Rockwell",Font.BOLD,44));
            jDialog.setSize(300,100);
            jDialog.add(new JLabel(content));
            jDialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
            jDialog.setAlwaysOnTop(true);
            jDialog.setLocationRelativeTo(null);
            jDialog.setModal(true);
            jDialog.setVisible(true);
        }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == loginButton) {
            System.out.println("click loginButton");
            //获取两个文本输入框中的内容
            String usernameInput =xTextField.getText();
            String passwordInput =yTextField.getText();

            //创建一个User对象
            User userInfo = new User(usernameInput, passwordInput);
            System.out.println("name:" + usernameInput);
            System.out.println("password:" + passwordInput);

            if (usernameInput.length() == 0 || passwordInput.length() == 0) {
                System.out.println("name or password is empty");
                showJDialog("name or password is empty");
            } else if  ((usernameInput.equals("Jasmer")&&passwordInput.equals("12345"))) {
                System.out.println("name and password is true");
                //关闭当前登录界面
                loginFrame.setVisible(false);
                //打开游戏的主界面
                //需要把当前登录的用户名传递给游戏界面
                AIChessGameFrame mainFrame = new AIChessGameFrame(1100, 810);
                AIcontroller gameController = new AIcontroller(mainFrame.getChessboardComponent(), new Chessboard());
                mainFrame.setAIcontroller(gameController);
                gameController.setStatusLabel(mainFrame.getStatusLabel());
                gameController.setTurnLabel(mainFrame.getTurnLabel());
                gameController.setTimeLabel(mainFrame.getTimeLabel());
                gameController.restartGame();
                mainFrame.setVisible(true);
            } else if  ((usernameInput.equals("Xybmy")&&passwordInput.equals("1234"))) {
                System.out.println("name and password is true");
                //关闭当前登录界面
                loginFrame.setVisible(false);
                //打开游戏的主界面
                //需要把当前登录的用户名传递给游戏界面
                AIChessGameFrame mainFrame = new AIChessGameFrame(1100, 810);
                AIcontroller gameController = new AIcontroller(mainFrame.getChessboardComponent(), new Chessboard());
                mainFrame.setAIcontroller(gameController);
                gameController.setStatusLabel(mainFrame.getStatusLabel());
                gameController.setTurnLabel(mainFrame.getTurnLabel());
                gameController.setTimeLabel(mainFrame.getTimeLabel());
                gameController.restartGame();
                mainFrame.setVisible(true);
            }else if  ((usernameInput.equals("ff")&&passwordInput.equals("123"))) {
                System.out.println("name and password is true");
                //关闭当前登录界面
                loginFrame.setVisible(false);
                //打开游戏的主界面
                //需要把当前登录的用户名传递给游戏界面
                AIChessGameFrame mainFrame = new AIChessGameFrame(1100, 810);
                AIcontroller gameController = new AIcontroller(mainFrame.getChessboardComponent(), new Chessboard());
                mainFrame.setAIcontroller(gameController);
                gameController.setStatusLabel(mainFrame.getStatusLabel());
                gameController.setTurnLabel(mainFrame.getTurnLabel());
                gameController.setTimeLabel(mainFrame.getTimeLabel());
                gameController.restartGame();
                mainFrame.setVisible(true);
            }
            else {
                System.out.println("name or password is false");
                showJDialog("name or password is false");
            }
        } else if (e.getSource() == registerButton) {
            System.out.println("click registerButton");
            new RegisterJFrame();
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
