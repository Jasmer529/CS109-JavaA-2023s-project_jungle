package controller;

import model.*;
import view.CellComponent;
import view.ChessPieceComponent;
import view.ChessboardComponent;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class AIcontroller extends GameController{
    private Chessboard model;
    private Chessboard lastModel;
    private ChessboardComponent view;
    public static PlayerColor currentPlayer;

    // Record whether there is a selected piece before
    private ChessboardPoint selectedPoint;
    private PlayerColor winner;
    private JLabel statusLabel;
    private JLabel turnLabel;

    private JLabel timeLabel;
    ChessboardPoint lastPoint;
    ChessboardPoint nowPoint;

    public ArrayList<EveryStep> undoing = new ArrayList<>();
    private int record;
    private int count;
    private java.util.Timer timer;
    public JLabel getTimeLabel() {
        return timeLabel;
    }

    public void setTimeLabel(JLabel timeLabel) {
        this.timeLabel = timeLabel;
    }

    public void enterPage() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                count++;
                if(count>20){
                    swapColor();
                    count = 0;
                }
                //将count的值更新到Label中
                timeLabel.setText(String.valueOf(count));
            }
        }, 0, 1000); //每隔1秒钟更新一次
    }
    public void leavePage() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }
    public void setRecord(int record) {
        this.record = turn;
    }

    public int getRecord() {
        return record;
    }

    public String[][] HEArray() {
        return model.NowArray();
    }

    public ChessboardPoint getNowPoint() {
        return nowPoint;
    }

    public void setNowPoint(ChessboardPoint nowPoint) {
        this.nowPoint = nowPoint;
    }


    public ChessboardPoint getLastPoint() {
        return lastPoint;
    }

    public void setLastPoint(ChessboardPoint lastPoint) {
        this.lastPoint = lastPoint;
    }

    int turn = 0;

    public PlayerColor getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(PlayerColor currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Chessboard getModel() {
        return model;
    }

    public void setModel(Chessboard model) {
        this.model = model;
    }

    public Chessboard getLastModel() {
        return lastModel;
    }

    public void setLastModel(Chessboard lastModel) {
        this.lastModel = lastModel;
    }

    public JLabel getTurnLabel() {
        return turnLabel;
    }

    public void setTurnLabel(JLabel turnLabel) {
        this.turnLabel = turnLabel;
    }

    public JLabel getStatusLabel() {
        return statusLabel;
    }

    public void setStatusLabel(JLabel statusLabel) {
        this.statusLabel = statusLabel;
    }

    public AIcontroller(ChessboardComponent view, Chessboard model) {
        super(view,model);
        this.view = view;
        this.model = model;
        this.currentPlayer = PlayerColor.BLUE;
        view.registerController(this);
        initialize();
        view.initiateChessComponent(model);
        view.repaint();
    }

    private void initialize() {
        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {

            }
        }
    }

    // after a valid move swap the player

    private void swapColor() {
        currentPlayer = currentPlayer == PlayerColor.BLUE ? PlayerColor.RED : PlayerColor.BLUE;
        statusLabel.setText("Player: " + currentPlayer.name());
        turn++;
        turnLabel.setText("Turn " + turn);


        AIPlayer ai = new AIPlayer(model);
        EveryStep step = ai.generateMove(currentPlayer);


        model.moveChessPiece(step.getSrc(),step.getDest());
        undoing.add(step);
        view.setChessComponentAtGrid(step.getDest(), view.removeChessComponentAtGrid(step.getSrc()));
        selectedPoint = null;
        view.repaint();

        System.out.printf("%d %d %d %d\n",step.getSrc().getRow(),step.getSrc().getCol(),step.getDest().getRow(),step.getDest().getCol());


        currentPlayer = currentPlayer == PlayerColor.BLUE ? PlayerColor.RED : PlayerColor.BLUE;
        statusLabel.setText("Player: " + currentPlayer.name());
        turn++;
        turnLabel.setText("Turn " + turn);
    }

    public void initialColor() {
        currentPlayer = PlayerColor.BLUE;
    }

    // click an empty cell
    @Override
    public void onPlayerClickCell(ChessboardPoint point, CellComponent component) {
        setLastPoint(selectedPoint);
        setNowPoint(point);
        if (CheckWin()) {
            System.out.println("GAME OVER");
        }
        if (selectedPoint != null && model.isValidMove(selectedPoint, point)) {
            if (((point.getRow() == 0) || (point.getRow() == 8)) && (point.getCol() == 3)) {
                model.moveChessPiece(selectedPoint, point);
                EveryStep step = model.recordStep(selectedPoint, point, currentPlayer);
                undoing.add(step);
                view.setChessComponentAtGrid(point, view.removeChessComponentAtGrid(selectedPoint));
                selectedPoint = null;
                swapColor();
                view.repaint();
                WinDons();//到时候直接写一个游戏结束的页面，在Win01里，然后在这里执行后游戏结束
            }
            if (((point.getRow() == 0) || (point.getRow() == 8)) && ((point.getCol() == 2) || (point.getCol() == 4))) {
                model.moveChessPiece(selectedPoint, point);
                EveryStep step = model.recordStep(selectedPoint, point, currentPlayer);
                undoing.add(step);
                view.setChessComponentAtGrid(point, view.removeChessComponentAtGrid(selectedPoint));
                selectedPoint = null;
                swapColor();
                view.repaint();
                model.TrapPunish(point);
                System.out.println("rank is zero now!");
            }
            if (((point.getRow() == 1) || (point.getRow() == 7)) && ((point.getCol() == 3))) {
                model.moveChessPiece(selectedPoint, point);
                EveryStep step = model.recordStep(selectedPoint, point, currentPlayer);
                undoing.add(step);
                view.setChessComponentAtGrid(point, view.removeChessComponentAtGrid(selectedPoint));
                selectedPoint = null;
                swapColor();
                view.repaint();
                model.TrapPunish(point);
                System.out.println("rank is zero now!");
            } else {
                EveryStep step = model.recordStep(selectedPoint, point, currentPlayer);
                undoing.add(step);
                model.moveChessPiece(selectedPoint, point);
                view.setChessComponentAtGrid(point, view.removeChessComponentAtGrid(selectedPoint));
                selectedPoint = null;
                swapColor();
                leavePage();
                count = 0;
                enterPage();
                view.repaint();
            }
            // TODO: if the chess enter Dens or Traps and so on
        }
    }

    boolean temp = false;

    // click a cell with a chess
    @Override
    public void onPlayerClickChessPiece(ChessboardPoint point, ChessPieceComponent component) {
        setLastPoint(selectedPoint);
        setNowPoint(point);
        temp = false;
        if (selectedPoint == null) {
            if (model.getChessPieceOwner(point) != null) {
                if (model.getChessPieceOwner(point).equals(currentPlayer)) {
                    selectedPoint = point;
                    component.setSelected(true);
                    component.repaint();
                }
            }
        } else if (selectedPoint.equals(point)) {
            selectedPoint = null;
            component.setSelected(false);
            component.repaint();
        } else {
            if (CheckWin()) {
                alreadyWin(currentPlayer.name());
            }
            temp = model.isValidCapture(selectedPoint, point);
            if (!temp) {
                System.out.println("NOWAY");
            } else {
                temp = true;
                EveryStep step = model.recordStep(selectedPoint, point, currentPlayer);
                undoing.add(step);
                model.captureChessPiece(selectedPoint, point);
                view.removeChessComponentAtGrid(point);
                view.setChessComponentAtGrid(point, view.removeChessComponentAtGrid(selectedPoint));
                view.repaint();

                selectedPoint = null;
                swapColor();
                swapColor();
                leavePage();
                count = 0;
                enterPage();

                view.repaint();
                if (CheckWin()) {
                    String nana = null;
                    if (currentPlayer.name().equals(PlayerColor.RED.name())) {
                        nana = PlayerColor.BLUE.name();
                    }
                    if (currentPlayer.name().equals(PlayerColor.BLUE.name())) {
                        nana = PlayerColor.RED.name();
                    }
                    alreadyWin(nana);
                }
            }
        }
    }

    private void WinDons() {
        if(currentPlayer == PlayerColor.BLUE){
            winner = PlayerColor.RED;
        }
        if(currentPlayer == PlayerColor.RED){
            winner = PlayerColor.BLUE;
        }
        System.out.println("Winner is " + winner);
        alreadyWin(String.valueOf(winner));
    }

    public void alreadyWin(String winner) {
        int result = JOptionPane.showConfirmDialog(this, "The winner is " + winner + "! Restart game?", "Game Over", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            restartGame();
        }
    }

    private boolean CheckWin() {
        int countRed = 0;
        int countBlue = 0;
        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {
                if (model.getGrid()[i][j].getPiece() != null) {
                    if (model.getGrid()[i][j].getPiece().getOwner() == PlayerColor.BLUE) {
                        countBlue++;
                    }
                    if (model.getGrid()[i][j].getPiece().getOwner() == PlayerColor.RED) {
                        countRed++;
                    }
                }
            }
        }
        System.out.println("现在的蓝棋数量为" + countBlue);
        System.out.println("现在的红棋数量为" + countRed);
        if (countRed == 0) {
            System.out.println("Winner is BLUE");
            leavePage();
            count = 0;
            return true;
        }
        if (countBlue == 0) {
            System.out.println("Winner is RED");
            count = 0;
            leavePage();
            return true;
        } else {
            return false;
        }
    }
    public void undo() {
        if (undoing.size()==0) {
            return;
        }
        EveryStep step = undoing.remove(undoing.size() - 1);
        model.everyone(step);
        view.everyone(step);
        view.repaint();
        swapColor();
        turn=turn-2;
        turnLabel.setText("Turn "+turn);
    }


//        }
       /*
        if(temp){
            undoing.add(model.foot.get(model.foot.size() - 1));
            System.out.println(src.getRow());
            System.out.println(src.getCol());
            System.out.println(dest.getRow());
            System.out.println(dest.getCol());
            model.removeChessPiece(dest);
            model.setChessPiece(dest, model.removeChessPiece(src));
            view.removeChessComponentAtGrid(dest);
            view.setChessComponentAtGrid(dest, view.removeChessComponentAtGrid(src));
//            model.setChessPiece(dest, model.removeChessPiece(src));
//            model.moveChessPiece(dest, src);
//
//
//         //这还是有点问题，我酷似
//            view.removeChessComponentAtGrid(dest);
//            view.setChessComponentAtGrid(src, view.removeChessComponentAtGrid(dest));
            selectedPoint=null;
            view.repaint();
            swapColor();//swapColor多加了一次turn
            turn=turn-2;
            turnLabel.setText("Turn "+turn);
        }
        else {
            undoing.add(model.foot.get(model.foot.size() - 1));
            model.moveChessPiece(dest, src);
            view.setChessComponentAtGrid(src, view.removeChessComponentAtGrid(dest));
            //model.moveChessPiece(nowPoint, lastPoint);
           // view.setChessComponentAtGrid(lastPoint, view.removeChessComponentAtGrid(nowPoint));
            selectedPoint = null;
            view.repaint();
            swapColor();//swapColor多加了一次turn
            turn=turn-2;
            turnLabel.setText("Turn "+turn);
        }
        model.foot.remove(model.foot.size() - 1);}
        */

    public void restartGame() {
        leavePage();
        count = 0;
        enterPage();
        turnLabel.setText("Turn "+0);
        statusLabel.setText("Player: "+PlayerColor.BLUE.name());
        model.removeAllPieces();
        model.initPieces();
        view.removeAllPieces();
        view.initiateChessComponent(model);
        view.repaint();
        initialColor();
        undoing.clear();
        turn = 0;
    }

    public void rank() {
        JFrame jFrame=new JFrame();
        jFrame.setLocationRelativeTo(null); // Center the window.
        jFrame.setAlwaysOnTop(true);
        jFrame.setBounds(300,200,300,400);
        JPanel jPanel=new JPanel();
        int red=0;
        int blue=0;
        if (winner==PlayerColor.RED){
            red++;
        } else if (winner == PlayerColor.BLUE) {
            blue++;
        }
        JLabel jLabel=new JLabel("Rank");
        jLabel.setFont(new Font("Rockwell", Font.BOLD, 54));
        jLabel.setPreferredSize(new Dimension(200,100));
        jLabel.setHorizontalAlignment(JLabel.CENTER);
        jLabel.setVisible(true);
        JLabel jLabel1=new JLabel("Red:"+red);
        jLabel1.setPreferredSize(new Dimension(200,100));
        jLabel1.setLocation(100,150);
        jLabel1.setFont(new Font("Rockwell", Font.BOLD, 32));
        jLabel1.setVisible(true);
        JLabel jLabel2=new JLabel("Blue:"+blue);
        jLabel2.setPreferredSize(new Dimension(200,100));
        jLabel2.setLocation(100,250);
        jLabel2.setFont(new Font("Rockwell", Font.BOLD, 32));
        jLabel2.setVisible(true);
        jPanel.add(jLabel);
        jPanel.add(jLabel1);
        jPanel.add(jLabel2);
        jFrame.getContentPane().add(jPanel);
        jFrame.setVisible(true);
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

}
