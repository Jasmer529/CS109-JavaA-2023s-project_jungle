package controller;


import listener.GameListener;
import model.*;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.IntStream;

/**
 * Controller is the connection between model and view,
 * when a Controller receive a request from a view, the Controller
 * analyzes and then hands over to the model for processing
 * [in this demo the request methods are onPlayerClickCell() and onPlayerClickChessPiece()]
 *
*/

public class GameController extends Component implements GameListener {

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
    private Timer timer;
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

    public GameController(ChessboardComponent view, Chessboard model) {
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


//        AIPlayer ai = new AIPlayer(model);
//        EveryStep step = ai.generateMove(currentPlayer);
//
//        model.moveChessPiece(step.getSrc(),step.getDest());
//        //undoing.add(step);
//        view.setChessComponentAtGrid(step.getDest(), view.removeChessComponentAtGrid(step.getSrc()));
//        selectedPoint = null;
//        view.repaint();

       // System.out.printf("%d %d %d %d\n",step.getSrc().getRow(),step.getSrc().getCol(),step.getDest().getRow(),step.getDest().getCol());
    }

    public void initialColor() {
        currentPlayer = PlayerColor.BLUE;
    }
    public void showing(ChessboardPoint point) {
        int team = 0;
        flag:
        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {
                ChessboardPoint dest = new ChessboardPoint(i, j);
                if (model.isValidMove(point, dest)) {
                    team++;
                    view.setChessComponentAtGrid(dest, new Showing(dest, 1));
                    view.repaint();
                    if(team>4){
                        break flag;
                    }
                }
            }
        }
    }
    public void unshowing() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 7; j++) {
                ChessboardPoint dest = new ChessboardPoint(i, j);
                if (view.getGridComponentAt(dest).getName() != null) {
                        view.removeChessComponentAtGrid(dest);
                        model.removeChessPiece(dest);
                        view.repaint();
                        view.revalidate();
                }
            }
        }
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
                unshowing();
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
                unshowing();
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
                unshowing();
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
                unshowing();
                undoing.add(step);
                System.out.println(undoing.size());
                model.moveChessPiece(selectedPoint, point);
                view.setChessComponentAtGrid(point, view.removeChessComponentAtGrid(selectedPoint));
                selectedPoint = null;
                swapColor();
//                    leavePage();
//                    count = 0;
//                    enterPage();
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
        leavePage();
        count = 0;
        enterPage();
        if (selectedPoint == null) {
            if (model.getChessPieceOwner(point) != null) {
                if (model.getChessPieceOwner(point).equals(currentPlayer)) {
                    selectedPoint = point;
                    component.setSelected(true);
                    showing(selectedPoint);
                    component.repaint();
                }
            }
        } else if (selectedPoint.equals(point)) {
            unshowing();
            selectedPoint = null;
            component.setSelected(false);
            component.repaint();
        } else {
            if (CheckWin()) {
                alreadyWin(currentPlayer.name());
            }

            if (model.isValidMove(selectedPoint, point)){
                if (((point.getRow() == 0) || (point.getRow() == 8)) && (point.getCol() == 3)) {
                    unshowing();
                    model.moveChessPiece(selectedPoint, point);
                    EveryStep step = model.recordStep(selectedPoint, point, currentPlayer);
                    undoing.add(step);
                    view.setChessComponentAtGrid(point, view.removeChessComponentAtGrid(selectedPoint));
                    selectedPoint = null;
                    swapColor();
                    view.repaint();
                    WinDons();//到时候直接写一个游戏结束的页面，在Win01里，然后在这里执行后游戏结束
                }
                if (((point.getRow() == 0) && ((point.getCol() == 2) || (point.getCol() == 4)))||((point.getRow() == 1)&&(point.getCol() == 3))) {
                    unshowing();
                    model.moveChessPiece(selectedPoint, point);
                    EveryStep step = model.recordStep(selectedPoint, point, currentPlayer);
                    undoing.add(step);
                    view.setChessComponentAtGrid(point, view.removeChessComponentAtGrid(selectedPoint));
                    selectedPoint = null;

                    view.repaint();
                    if(currentPlayer == PlayerColor.RED){
                        model.TrapPunish(point);
                        System.out.println("rank is zero now!");
                    }
                    swapColor();
                }
                if (((point.getRow() == 8) && ((point.getCol() == 2) || (point.getCol() == 4)))||((point.getRow() == 7)&&(point.getCol() == 3))) {
                    unshowing();
                    model.moveChessPiece(selectedPoint, point);
                    EveryStep step = model.recordStep(selectedPoint, point, currentPlayer);
                    undoing.add(step);
                    view.setChessComponentAtGrid(point, view.removeChessComponentAtGrid(selectedPoint));
                    selectedPoint = null;
                    view.repaint();
                    if(currentPlayer == PlayerColor.BLUE){
                        model.TrapPunish(point);
                        System.out.println("rank is zero now!");
                    }
                    swapColor();

                }else {
                    unshowing();
                    EveryStep step = model.recordStep(selectedPoint, point, currentPlayer);
                    undoing.add(step);
                    leavePage();

                    model.moveChessPiece(selectedPoint, point);
                    view.setChessComponentAtGrid(point, view.removeChessComponentAtGrid(selectedPoint));
                    selectedPoint = null;
                    swapColor();
                    view.repaint();
                    enterPage();
                }

            } else {
                temp = model.isValidCapture(selectedPoint, point);
                System.out.println(selectedPoint);
                System.out.println(point);
                if (!temp) {
                    System.out.println("NOWAY");
                } else {
                    temp = true;
                    EveryStep step = model.recordStep(selectedPoint, point, currentPlayer);
                    //setMovableGridsHighlighted(point);
                    unshowing();
                    undoing.add(step);
                    model.captureChessPiece(selectedPoint, point);
                    view.removeChessComponentAtGrid(point);
                    view.setChessComponentAtGrid(point, view.removeChessComponentAtGrid(selectedPoint));
                    view.repaint();

                    selectedPoint = null;

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
    }

    private void WinDons() {
        winner = currentPlayer;
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
        System.out.println("撤回操作");
        turnLabel.setText("Turn "+turn);
    }
    public void Inferno() {
        int counter = undoing.size();
        IntStream.range(0, counter).forEach(i -> undo()); // 撤销之前的所有操作，并无法走之前走过的路，开启地狱模式
    }
    public void restartGame() {
        leavePage();
        count = 0;
        enterPage();
        turnLabel.setText("Turn " + 0);
        statusLabel.setText("Player: "+PlayerColor.BLUE.name());

        model.removeAllPieces();
        view.removeAllPieces();
        model.initPieces();
        view.initiateChessComponent(model);
        initialColor();
        undoing.clear();
        turn = 0;
        view.repaint();

    }


    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public void loadTurn(String path) {
        if(!path.contains("txt")){
            JOptionPane.showMessageDialog(null, "File Error",
                    "101 error", JOptionPane.ERROR_MESSAGE);
        }
        try {
            List<String> lines = Files.readAllLines(Path.of(path));
            List<Integer> integers = new ArrayList<>();
            for (String line : lines) {
                integers.add(Integer.valueOf(line));
            }
            turnLabel.setText("Turn "+ integers.get(0));
            setTurn(integers.get(0));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void loadGameFromFile(String path) {
        Boolean able = true;
        Boolean able2 = true;
        if(!path.contains("txt")){
            JOptionPane.showMessageDialog(null, "File Error",
                    "101 error", JOptionPane.ERROR_MESSAGE);
            able = false;
        } else {
            try {
                List<String> Reading = Files.readAllLines((Path.of(path)));

                for (String s: Reading) {
                    System.out.println(s);
                }
                    if(loadPlayer("C:\\Users\\16422\\Desktop\\Jungle\\heiheihei\\Jungle\\LastPlayer.txt")){
                        if(Reading.size()!=9){
                            JOptionPane.showMessageDialog(null, "File Error",
                                    "102 error", JOptionPane.ERROR_MESSAGE);
                            able2 = false;
                        }
                        for (int i = 0; i < Reading.size(); i++) {
                            if(Reading.get(i).length()!=7){
                                JOptionPane.showMessageDialog(null, "File Error",
                                        "102 error", JOptionPane.ERROR_MESSAGE);
                                able2 = false;
                                break;
                            }
                        }
                        flag:
                        for (int i = 0; i < Reading.size(); i++) {
                            for (int i1 = 0; i1 < Reading.get(i).length(); i1++) {
                                if(Reading.get(i).charAt(i1)=='0' ||
                                        Reading.get(i).charAt(i1)=='1' || Reading.get(i).charAt(i1)=='2' ||
                                        Reading.get(i).charAt(i1)=='3' || Reading.get(i).charAt(i1)=='4' ||
                                        Reading.get(i).charAt(i1)=='5' || Reading.get(i).charAt(i1)=='6' ||
                                        Reading.get(i).charAt(i1)=='7' || Reading.get(i).charAt(i1)=='8' ||
                                        Reading.get(i).charAt(i1)=='a' || Reading.get(i).charAt(i1)=='b' ||
                                        Reading.get(i).charAt(i1)=='c' || Reading.get(i).charAt(i1)=='d' ||
                                        Reading.get(i).charAt(i1)=='e' || Reading.get(i).charAt(i1)=='f' ||
                                        Reading.get(i).charAt(i1)=='g' || Reading.get(i).charAt(i1)=='h'
                                ){
                                    able = true;
                                }
                                else {
                                    JOptionPane.showMessageDialog(null, "File Error",
                                            "103 error", JOptionPane.ERROR_MESSAGE);
                                    able = false;
                                    break flag;
                                }
                            }
                        }

                        if(able&&able2){
                            leavePage();
                            count = 0;
                            enterPage();
                            loadTurn("C:\\Users\\16422\\Desktop\\Jungle\\heiheihei\\Jungle\\LastTurn.txt");
                            model.removeAllPieces();
                            model.initPieces(Reading);
                            view.removeAllPieces();
                            view.initiateReading(model);
                            view.repaint();
                        }
                    } else {
                    JOptionPane.showMessageDialog(null, "File Error",
                            "104 error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public boolean loadPlayer(String path) {
        if(!path.contains("txt")){
            JOptionPane.showMessageDialog(null, "File Error",
                    "101 error", JOptionPane.ERROR_MESSAGE);
        }
        try {
            List<String> Reading = Files.readAllLines((Path.of(path)));
            for (String s: Reading) {
                System.out.println(s);
            }
            if(Reading.size()==0){;
                return false;
            }
            else if(Reading.get(0).equals("BLUE")||Reading.get(0).equals("RED")){
                statusLabel.setText("Player: "+Reading.get(0));
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void saveTurn(int TURN){
        TURN = turn;
       // controller.Constant.setMoon(turn);
        FileWriter writer = null;
        try {
            File file = new File("LastTurn.txt");
            if(!file.exists()){
                file.createNewFile();
            }
            writer = new FileWriter(file);
           writer.write((Integer.toString(TURN)));
            writer.flush();
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(writer!=null){
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void savePlayer(String play){
        FileWriter writer = null;
        try {
            File file = new File("LastPlayer.txt");
            if(!file.exists()){
                file.createNewFile();
            }
            writer = new FileWriter(file);
            writer.write(play);
            writer.flush();
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(writer!=null){
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void saveFile(String[][] arr){
        setRecord(turn);
        FileWriter writer = null;
        saveTurn(turn);
        savePlayer(currentPlayer.name());
        try {
            File file = new File("LastGame.txt");
            if(!file.exists()){
                file.createNewFile();
            }
            writer = new FileWriter(file);
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 7; j++) {
                    writer.write(arr[i][j]);
                }
                writer.write("\n");
            }
            writer.flush();
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(writer!=null){
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
