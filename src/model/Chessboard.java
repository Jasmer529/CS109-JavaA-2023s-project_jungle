package model;


import controller.GameController;
import view.ChessboardComponent;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * This class store the real chess information.
 * The Chessboard has 9*7 cells, and each cell has a position for chess
 */
public class Chessboard {
    private Cell[][] grid;

    public ArrayList<EveryStep> steps;

    public Chessboard() {
        this.grid = new Cell[Constant.CHESSBOARD_ROW_SIZE.getNum()][Constant.CHESSBOARD_COL_SIZE.getNum()];//19X19
        steps = new ArrayList<>();
        initGrid();
        initPieces();
    }

    public void initGrid() {
        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {
                grid[i][j] = new Cell();
            }
        }
    }
    public void removeAllPieces() {
        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {
                if(grid[i][j].getPiece()!=null){
                    grid[i][j].setPiece(null);
                }
            }
        }
    }
    public void initPieces() {
        grid[2][6].setPiece(new ChessPiece(PlayerColor.BLUE, "Elephant",8));
        grid[6][0].setPiece(new ChessPiece(PlayerColor.RED, "Elephant",8));
        grid[0][0].setPiece(new ChessPiece(PlayerColor.BLUE, "Lion",7));
        grid[8][6].setPiece(new ChessPiece(PlayerColor.RED, "Lion",7));
        grid[0][6].setPiece(new ChessPiece(PlayerColor.BLUE, "Tiger",6));
        grid[8][0].setPiece(new ChessPiece(PlayerColor.RED, "Tiger",6));
        grid[2][2].setPiece(new ChessPiece(PlayerColor.BLUE, "Leopard",5));
        grid[6][4].setPiece(new ChessPiece(PlayerColor.RED, "Leopard",5));
        grid[2][4].setPiece(new ChessPiece(PlayerColor.BLUE, "Wolf",4));
        grid[6][2].setPiece(new ChessPiece(PlayerColor.RED, "Wolf",4));
        grid[1][1].setPiece(new ChessPiece(PlayerColor.BLUE, "Dog",3));
        grid[7][5].setPiece(new ChessPiece(PlayerColor.RED, "Dog",3));
        grid[1][5].setPiece(new ChessPiece(PlayerColor.BLUE, "Cat",2));
        grid[7][1].setPiece(new ChessPiece(PlayerColor.RED, "Cat",2));
        grid[2][0].setPiece(new ChessPiece(PlayerColor.BLUE, "Mouse",1));
        grid[6][6].setPiece(new ChessPiece(PlayerColor.RED, "Mouse",1));
    }
    String[][] arr = new String[9][7];
    public String[][] NowArray(){
        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {
                if(grid[i][j].getPiece()==null){
                    arr[i][j]="0";
                }
                else {
                    if(grid[i][j].getPiece().getName().equals("Elephant")&&grid[i][j].getPiece().getOwner().equals(PlayerColor.BLUE)){
                        arr[i][j]="1";
                    }
                    if(grid[i][j].getPiece().getName().equals("Elephant")&&grid[i][j].getPiece().getOwner().equals(PlayerColor.RED)){
                        arr[i][j]="2";
                    }
                    if(grid[i][j].getPiece().getName().equals("Lion")&&grid[i][j].getPiece().getOwner().equals(PlayerColor.BLUE)){
                        arr[i][j]="3";
                    }
                    if(grid[i][j].getPiece().getName().equals("Lion")&&grid[i][j].getPiece().getOwner().equals(PlayerColor.RED)){
                        arr[i][j]="4";
                    }
                    if(grid[i][j].getPiece().getName().equals("Tiger")&&grid[i][j].getPiece().getOwner().equals(PlayerColor.BLUE)){
                        arr[i][j]="5";
                    }
                    if(grid[i][j].getPiece().getName().equals("Tiger")&&grid[i][j].getPiece().getOwner().equals(PlayerColor.RED)){
                        arr[i][j]="6";
                    }
                    if(grid[i][j].getPiece().getName().equals("Leopard")&&grid[i][j].getPiece().getOwner().equals(PlayerColor.BLUE)){
                        arr[i][j]="7";
                    }
                    if(grid[i][j].getPiece().getName().equals("Leopard")&&grid[i][j].getPiece().getOwner().equals(PlayerColor.RED)){
                        arr[i][j]="8";
                    }

                    if(grid[i][j].getPiece().getName().equals("Wolf")&&grid[i][j].getPiece().getOwner().equals(PlayerColor.BLUE)){
                        arr[i][j]="a";
                    }
                    if(grid[i][j].getPiece().getName().equals("Wolf")&&grid[i][j].getPiece().getOwner().equals(PlayerColor.RED)){
                        arr[i][j]="b";
                    }
                    if(grid[i][j].getPiece().getName().equals("Dog")&&grid[i][j].getPiece().getOwner().equals(PlayerColor.BLUE)){
                        arr[i][j]="c";
                    }
                    if(grid[i][j].getPiece().getName().equals("Dog")&&grid[i][j].getPiece().getOwner().equals(PlayerColor.RED)){
                        arr[i][j]="d";
                    }
                    if(grid[i][j].getPiece().getName().equals("Cat")&&grid[i][j].getPiece().getOwner().equals(PlayerColor.BLUE)){
                        arr[i][j]="e";
                    }
                    if(grid[i][j].getPiece().getName().equals("Cat")&&grid[i][j].getPiece().getOwner().equals(PlayerColor.RED)){
                        arr[i][j]="f";
                    }
                    if(grid[i][j].getPiece().getName().equals("Mouse")&&grid[i][j].getPiece().getOwner().equals(PlayerColor.BLUE)){
                        arr[i][j]="g";
                    }
                    if(grid[i][j].getPiece().getName().equals("Mouse")&&grid[i][j].getPiece().getOwner().equals(PlayerColor.RED)){
                        arr[i][j]="h";
                    }
                }

            }
        }
        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {
                System.out.println(arr[i][j]);
            }
        }
        return arr;
    }
    public void initPieces(List<String> Reading){
        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {
                if(Reading.get(i).charAt(j)=='1'){
                    grid[i][j].setPiece(new ChessPiece(PlayerColor.BLUE, "Elephant",8));
                }
                else if(Reading.get(i).charAt(j)=='2'){
                    grid[i][j].setPiece(new ChessPiece(PlayerColor.RED, "Elephant",8));
                }
                if(Reading.get(i).charAt(j)=='3'){
                    grid[i][j].setPiece(new ChessPiece(PlayerColor.BLUE, "Lion",7));
                }
                if(Reading.get(i).charAt(j)=='4'){
                    grid[i][j].setPiece(new ChessPiece(PlayerColor.RED, "Lion",7));
                }
                if(Reading.get(i).charAt(j)=='5'){
                    grid[i][j].setPiece(new ChessPiece(PlayerColor.BLUE, "Tiger",6));
                }
                if(Reading.get(i).charAt(j)=='6'){
                    grid[i][j].setPiece(new ChessPiece(PlayerColor.RED, "Tiger",6));
                }
                if(Reading.get(i).charAt(j)=='7'){
                    grid[i][j].setPiece(new ChessPiece(PlayerColor.BLUE, "Leopard",5));
                }
                if(Reading.get(i).charAt(j)=='8'){
                    grid[i][j].setPiece(new ChessPiece(PlayerColor.RED, "Leopard",5));
                }
                if(Reading.get(i).charAt(j)=='a'){
                    grid[i][j].setPiece(new ChessPiece(PlayerColor.BLUE, "Wolf",4));
                }
                if(Reading.get(i).charAt(j)=='b'){
                    grid[i][j].setPiece(new ChessPiece(PlayerColor.RED, "Wolf",4));
                }
                if(Reading.get(i).charAt(j)=='c'){
                    grid[i][j].setPiece(new ChessPiece(PlayerColor.BLUE, "Dog",3));
                }
                if(Reading.get(i).charAt(j)=='d'){
                    grid[i][j].setPiece(new ChessPiece(PlayerColor.RED, "Dog",3));
                }
                if(Reading.get(i).charAt(j)=='e'){
                    grid[i][j].setPiece(new ChessPiece(PlayerColor.BLUE, "Cat",2));
                }
                if(Reading.get(i).charAt(j)=='f'){
                    grid[i][j].setPiece(new ChessPiece(PlayerColor.RED, "Cat",2));
                }
                if(Reading.get(i).charAt(j)=='g'){
                    grid[i][j].setPiece(new ChessPiece(PlayerColor.BLUE, "Mouse",1));
                }
                if(Reading.get(i).charAt(j)=='h'){
                    grid[i][j].setPiece(new ChessPiece(PlayerColor.RED, "Mouse",1));
                }
            }
        }
    }
    public ChessPiece getChessPieceAt(ChessboardPoint point) {
        return getGridAt(point).getPiece();
    }

    public Cell getGridAt(ChessboardPoint point) {
        return grid[point.getRow()][point.getCol()];
    }

    public int calculateDistance(ChessboardPoint src, ChessboardPoint dest) {
        return Math.abs(src.getRow() - dest.getRow()) + Math.abs(src.getCol() - dest.getCol());
    }

    public ChessPiece removeChessPiece(ChessboardPoint point) {
        ChessPiece chessPiece = getChessPieceAt(point);
        getGridAt(point).removePiece();
        return chessPiece;
    }

    public void setChessPiece(ChessboardPoint point, ChessPiece chessPiece) {
        getGridAt(point).setPiece(chessPiece);
    }
    public void moveChessPiece(ChessboardPoint src, ChessboardPoint dest) {
        if (!isValidMove(src, dest)) {
            throw new IllegalArgumentException("Illegal chess move!");
        } else {
            playSound();
            setChessPiece(dest, removeChessPiece(src));
        }
    }

    public List<EveryStep> getValidSteps(PlayerColor color){
        List<EveryStep> availableSteps = new ArrayList<>();
        List<ChessboardPoint> availablePoints = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 7; j++) {
                ChessboardPoint point = new ChessboardPoint(i, j);
                if (getChessPieceAt(point) != null && getChessPieceAt(point).getOwner() == color) {
                    availablePoints.add(point);
                }
            }
        }

        for(ChessboardPoint point : availablePoints) {
            System.out.printf("%d %d",point.getRow(), point.getCol());
            System.out.println();
        }


        for (ChessboardPoint point : availablePoints) {
            List<ChessboardPoint> validMoves = new ArrayList<>();
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 7; j++) {

                    ChessboardPoint destPoint = new ChessboardPoint(i, j);
                    if(point.equals(destPoint)){
                        j++;
                    }
                    else if (isValidMove(point, destPoint)||isValidCapture(point,destPoint)) {
                        validMoves.add(destPoint);
                    }
                }
            }
            for (ChessboardPoint destPoint : validMoves) {
                availableSteps.add(recordStep(point, destPoint, color));
                System.out.printf("%d %d %d %d\n",point.getRow(),point.getCol(),destPoint.getRow(),destPoint.getCol());
            }
        }
        return availableSteps;
    }
    private void playSound() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("C:\\Users\\16422\\Desktop\\Jungle\\heiheihei\\Jungle\\Music\\move.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error playing sound.");
            ex.printStackTrace();
        }
    }

    public EveryStep recordStep(ChessboardPoint src, ChessboardPoint dest, PlayerColor currentPlayer){
        ChessPiece Alpha = getChessPieceAt(src);
        ChessPiece Omega = getChessPieceAt(dest);
        EveryStep step = new EveryStep(src, dest, Alpha, Omega, currentPlayer);
        return step;
    }
    public void everyone(EveryStep step){
        ChessboardPoint alpha = step.getSrc();
        ChessboardPoint omega = step.getDest();
        ChessPiece src = step.getAlpha();
        ChessPiece dest = step.getOmega();
        if (dest == null) {
            setChessPiece(alpha, src);
        } else {
            setChessPiece(omega, dest);
            setChessPiece(alpha, src);
        }
    }
    public void captureChessPiece(ChessboardPoint src, ChessboardPoint dest) {
        if (!isValidCapture(src, dest)) {
            throw new IllegalArgumentException("Illegal chess capture!");
        }
        else {
            removeChessPiece(dest);//把原位的去掉
            setChessPiece(dest,removeChessPiece(src));//在新位置上重set

        }
    }

    public Cell[][] getGrid() {
        return grid;
    }
    public PlayerColor getChessPieceOwner(ChessboardPoint point) {
            return getGridAt(point).getPiece().getOwner();

    }
    int a = 0;
    int b = 0;

    public boolean isValidMove(ChessboardPoint src, ChessboardPoint dest) {
        if (getChessPieceAt(src) == null || getChessPieceAt(dest) != null) {
            return false;
        }
        if(getChessPieceAt(src).getName().equals("Mouse")){
            if((src.getCol()==dest.getCol() && src.getRow()==dest.getRow()+1)
                    ||(src.getCol()==dest.getCol() && src.getRow()==dest.getRow()-1)
                    ||(src.getCol()==dest.getCol()+1 && src.getRow()==dest.getRow())
                    ||(src.getCol()==dest.getCol()-1 && src.getRow()==dest.getRow())){
                return true;
            }
        }
        //狮子和老虎可以跳河
        if((((dest.getCol()==0&&dest.getRow()==3)&&(src.getCol()==3&&src.getRow()==3)||
                (dest.getCol()==0&&dest.getRow()==4)&&(src.getCol()==3&&src.getRow()==4)||
                (dest.getCol()==0&&dest.getRow()==5)&&(src.getCol()==3&&src.getRow()==5))
        ||((dest.getCol()==6&&dest.getRow()==3)&&(src.getCol()==3&&src.getRow()==3)||
                (dest.getCol()==6&&dest.getRow()==4)&&(src.getCol()==3&&src.getRow()==4)||
                (dest.getCol()==6&&dest.getRow()==5)&&(src.getCol()==3&&src.getRow()==5)))
                ||(((dest.getCol()==3&&dest.getRow()==3)&&((src.getCol()==0&&src.getRow()==3)||(src.getCol()==6&&src.getRow()==3))
                ||(dest.getCol()==3&&dest.getRow()==4)&&((src.getCol()==0&&src.getRow()==4)||(src.getCol()==6&&src.getRow()==4))
                ||(dest.getCol()==3&&dest.getRow()==5)&&((src.getCol()==0&&src.getRow()==5)||(src.getCol()==6&&src.getRow()==5))))
                ||((dest.getCol()==1&&dest.getRow()==2)&&(src.getCol()==1&&src.getRow()==6)||
                (dest.getCol()==2&&dest.getRow()==2)&&(src.getCol()==2&&src.getRow()==6)||
                (dest.getCol()==5&&dest.getRow()==2)&&(src.getCol()==5&&src.getRow()==6)||
                (dest.getCol()==4&&dest.getRow()==2)&&(src.getCol()==4&&src.getRow()==6))
                ||((src.getCol()==1&&src.getRow()==2)&&(dest.getCol()==1&&dest.getRow()==6)||
                (src.getCol()==2&&src.getRow()==2)&&(dest.getCol()==2&&dest.getRow()==6)||
                (src.getCol()==5&&src.getRow()==2)&&(dest.getCol()==5&&dest.getRow()==6)||
                (src.getCol()==4&&src.getRow()==2)&&(dest.getCol()==4&&dest.getRow()==6))
        ){
            if(getChessPieceAt(src).getName().equals("Tiger")){
//
                if((grid[3][1].getPiece()!=null)&&((src.getCol()==0&&src.getRow()==3)||(src.getCol()==1&&src.getRow()==2)||(src.getCol()==1&&src.getRow()==6)||((src.getCol()==3&&src.getRow()==3)&&(dest.getCol()==0&&dest.getRow()==3)))){
                    return false;
                }
                if((grid[3][2].getPiece()!=null)&&((src.getCol()==0&&src.getRow()==3)||(src.getCol()==2&&src.getRow()==2)||(src.getCol()==2&&src.getRow()==6)||((src.getCol()==3&&src.getRow()==3)&&(dest.getCol()==0&&dest.getRow()==3)))){
                    return false;
                }
                if((grid[4][1].getPiece()!=null)&&((src.getCol()==0&&src.getRow()==4)||(src.getCol()==1&&src.getRow()==2)||(src.getCol()==1&&src.getRow()==6)||((src.getCol()==3&&src.getRow()==4)&&(dest.getCol()==0&&dest.getRow()==4)))){
                    return false;
                }
                if((grid[4][2].getPiece()!=null)&&((src.getCol()==0&&src.getRow()==4)||(src.getCol()==2&&src.getRow()==2)||(src.getCol()==2&&src.getRow()==6)||((src.getCol()==3&&src.getRow()==4)&&(dest.getCol()==0&&dest.getRow()==4)))){
                    return false;
                }
                if((grid[5][1].getPiece()!=null)&&((src.getCol()==0&&src.getRow()==5)||(src.getCol()==1&&src.getRow()==2)||(src.getCol()==1&&src.getRow()==6)||((src.getCol()==3&&src.getRow()==5)&&(dest.getCol()==0&&dest.getRow()==5)))){
                    return false;
                }
                if((grid[5][2].getPiece()!=null)&&((src.getCol()==0&&src.getRow()==5)||(src.getCol()==2&&src.getRow()==2)||(src.getCol()==2&&src.getRow()==6)||((src.getCol()==3&&src.getRow()==5)&&(dest.getCol()==0&&dest.getRow()==5)))){
                    return false;
                }
                //右边6个
                if((grid[3][4].getPiece()!=null)&&((src.getCol()==6&&src.getRow()==3)||(src.getCol()==4&&src.getRow()==2)||(src.getCol()==4&&src.getRow()==6)||((src.getCol()==3&&src.getRow()==3)&&(dest.getCol()==6&&dest.getRow()==3)))){
                    return false;
                }
                if((grid[3][5].getPiece()!=null)&&((src.getCol()==6&&src.getRow()==3)||(src.getCol()==5&&src.getRow()==2)||(src.getCol()==5&&src.getRow()==6)||((src.getCol()==3&&src.getRow()==3)&&(dest.getCol()==6&&dest.getRow()==3)))){
                    return false;
                }
                if((grid[4][4].getPiece()!=null)&&((src.getCol()==6&&src.getRow()==4)||(src.getCol()==4&&src.getRow()==2)||(src.getCol()==4&&src.getRow()==6)||((src.getCol()==3&&src.getRow()==4)&&(dest.getCol()==6&&dest.getRow()==4)))){
                    return false;
                }
                if((grid[4][5].getPiece()!=null)&&((src.getCol()==6&&src.getRow()==4)||(src.getCol()==5&&src.getRow()==2)||(src.getCol()==5&&src.getRow()==6)||((src.getCol()==3&&src.getRow()==4)&&(dest.getCol()==6&&dest.getRow()==4)))){
                    return false;
                }
                if((grid[5][4].getPiece()!=null)&&((src.getCol()==6&&src.getRow()==5)||(src.getCol()==4&&src.getRow()==2)||(src.getCol()==4&&src.getRow()==6)||((src.getCol()==3&&src.getRow()==5)&&(dest.getCol()==6&&dest.getRow()==5)))){
                    return false;
                }
                if((grid[5][5].getPiece()!=null)&&((src.getCol()==6&&src.getRow()==5)||(src.getCol()==5&&src.getRow()==2)||(src.getCol()==5&&src.getRow()==6)||((src.getCol()==3&&src.getRow()==5)&&(dest.getCol()==6&&dest.getRow()==5)))){
                    return false;
                }
                if(src.getRow()==dest.getRow() && (src.getCol()-dest.getCol()==3)||(src.getCol()-dest.getCol()==-3)){
                    return true;
                }
                if(src.getCol()==dest.getCol() && src.getRow()-dest.getRow()==4||(src.getRow()-dest.getRow()==-4)){
                    return true;
                }
            }
            if(getChessPieceAt(src).getName().equals("Lion")){
                if((grid[3][1].getPiece()!=null)&&((src.getCol()==0&&src.getRow()==3)||(src.getCol()==1&&src.getRow()==2)||(src.getCol()==1&&src.getRow()==6)||((src.getCol()==3&&src.getRow()==3)&&(dest.getCol()==0&&dest.getRow()==3)))){
                    return false;
                }
                if((grid[3][2].getPiece()!=null)&&((src.getCol()==0&&src.getRow()==3)||(src.getCol()==2&&src.getRow()==2)||(src.getCol()==2&&src.getRow()==6)||((src.getCol()==3&&src.getRow()==3)&&(dest.getCol()==0&&dest.getRow()==3)))){
                    return false;
                }
                if((grid[4][1].getPiece()!=null)&&((src.getCol()==0&&src.getRow()==4)||(src.getCol()==1&&src.getRow()==2)||(src.getCol()==1&&src.getRow()==6)||((src.getCol()==3&&src.getRow()==4)&&(dest.getCol()==0&&dest.getRow()==4)))){
                    return false;
                }
                if((grid[4][2].getPiece()!=null)&&((src.getCol()==0&&src.getRow()==4)||(src.getCol()==2&&src.getRow()==2)||(src.getCol()==2&&src.getRow()==6)||((src.getCol()==3&&src.getRow()==4)&&(dest.getCol()==0&&dest.getRow()==4)))){
                    return false;
                }
                if((grid[5][1].getPiece()!=null)&&((src.getCol()==0&&src.getRow()==5)||(src.getCol()==1&&src.getRow()==2)||(src.getCol()==1&&src.getRow()==6)||((src.getCol()==3&&src.getRow()==5)&&(dest.getCol()==0&&dest.getRow()==5)))){
                    return false;
                }
                if((grid[5][2].getPiece()!=null)&&((src.getCol()==0&&src.getRow()==5)||(src.getCol()==2&&src.getRow()==2)||(src.getCol()==2&&src.getRow()==6)||((src.getCol()==3&&src.getRow()==5)&&(dest.getCol()==0&&dest.getRow()==5)))){
                    return false;
                }
                //右边6个
                if((grid[3][4].getPiece()!=null)&&((src.getCol()==6&&src.getRow()==3)||(src.getCol()==4&&src.getRow()==2)||(src.getCol()==4&&src.getRow()==6)||((src.getCol()==3&&src.getRow()==3)&&(dest.getCol()==6&&dest.getRow()==3)))){
                    return false;
                }
                if((grid[3][5].getPiece()!=null)&&((src.getCol()==6&&src.getRow()==3)||(src.getCol()==5&&src.getRow()==2)||(src.getCol()==5&&src.getRow()==6)||((src.getCol()==3&&src.getRow()==3)&&(dest.getCol()==6&&dest.getRow()==3)))){
                    return false;
                }
                if((grid[4][4].getPiece()!=null)&&((src.getCol()==6&&src.getRow()==4)||(src.getCol()==4&&src.getRow()==2)||(src.getCol()==4&&src.getRow()==6)||((src.getCol()==3&&src.getRow()==4)&&(dest.getCol()==6&&dest.getRow()==4)))){
                    return false;
                }
                if((grid[4][5].getPiece()!=null)&&((src.getCol()==6&&src.getRow()==4)||(src.getCol()==5&&src.getRow()==2)||(src.getCol()==5&&src.getRow()==6)||((src.getCol()==3&&src.getRow()==4)&&(dest.getCol()==6&&dest.getRow()==4)))){
                    return false;
                }
                if((grid[5][4].getPiece()!=null)&&((src.getCol()==6&&src.getRow()==5)||(src.getCol()==4&&src.getRow()==2)||(src.getCol()==4&&src.getRow()==6)||((src.getCol()==3&&src.getRow()==5)&&(dest.getCol()==6&&dest.getRow()==5)))){
                    return false;
                }
                if((grid[5][5].getPiece()!=null)&&((src.getCol()==6&&src.getRow()==5)||(src.getCol()==5&&src.getRow()==2)||(src.getCol()==5&&src.getRow()==6)||((src.getCol()==3&&src.getRow()==5)&&(dest.getCol()==6&&dest.getRow()==5)))){
                    return false;
                }
                if(src.getRow()==dest.getRow() && (src.getCol()-dest.getCol()==3)||(src.getCol()-dest.getCol()==-3)){
                    return true;
                }
                if(src.getCol()==dest.getCol() && src.getRow()-dest.getRow()==4||(src.getRow()-dest.getRow()==-4)){
                    return true;
                }
            }
        }
        //其他棋子不能进入河流
        if(dest.getRow()==5 && (dest.getCol()==1 || dest.getCol()==2 || dest.getCol()==4 || dest.getCol()==5)){
            return false;
        }
        if(dest.getRow()==4 && (dest.getCol()==1 || dest.getCol()==2 || dest.getCol()==4 || dest.getCol()==5)){
            return false;
        }
        if(dest.getRow()==3 && (dest.getCol()==1 || dest.getCol()==2 || dest.getCol()==4 || dest.getCol()==5)){
            return false;
        }
        if(getChessPieceOwner(src).getColor().equals(Color.BLUE)){
            if(dest.getRow()==0 && dest.getCol()==3){
                return false;
            }
        }
        if(getChessPieceOwner(src).getColor().equals(Color.RED)){
            if(dest.getRow()==8 && dest.getCol()==3){
                return false;
            }
        }
        return calculateDistance(src, dest) == 1;
    }

//还有一个问题，别的动物吃老鼠不能进入河水啊//516已解决
    //还有一个问题，中间有老鼠不能跨河移动//515已解决
    //还有一个问题，老鼠不能到处乱跑啊 //516已解决
    //老鼠在河外为什么吃不了大象啊

    public boolean isValidCapture(ChessboardPoint src, ChessboardPoint dest) {
        a=dest.getRow();
        b=dest.getCol();
        ChessPiece small = getChessPieceAt(src);
        ChessPiece big = getChessPieceAt(dest);
        if(small!=null && big!=null){

            if(small.getOwner()==big.getOwner()){
                return false;
            }
            if((src.getCol()==dest.getCol() && src.getRow()==dest.getRow()+1)
                    ||(src.getCol()==dest.getCol() && src.getRow()==dest.getRow()-1)
                    ||(src.getCol()==dest.getCol()+1 && src.getRow()==dest.getRow())
                    ||(src.getCol()==dest.getCol()-1 && src.getRow()==dest.getRow())){
                if(big.getName().equals("Mouse")){
                    if((src.getRow()==3&&src.getCol()==0&&dest.getRow()==3&&dest.getCol()==1)||
                            (src.getRow()==4&&src.getCol()==0&&dest.getRow()==4&&dest.getCol()==1)||
                            (src.getRow()==5&&src.getCol()==0&&dest.getRow()==5&&dest.getCol()==1)||
                            (src.getRow()==3&&src.getCol()==3&&dest.getRow()==3&&dest.getCol()==2)||
                            (src.getRow()==4&&src.getCol()==3&&dest.getRow()==4&&dest.getCol()==2)||
                            (src.getRow()==5&&src.getCol()==3&&dest.getRow()==5&&dest.getCol()==2)||
                            (src.getRow()==3&&src.getCol()==3&&dest.getRow()==3&&dest.getCol()==4)||
                            (src.getRow()==4&&src.getCol()==3&&dest.getRow()==4&&dest.getCol()==4)||
                            (src.getRow()==5&&src.getCol()==3&&dest.getRow()==5&&dest.getCol()==4)||
                            (src.getRow()==3&&src.getCol()==6&&dest.getRow()==3&&dest.getCol()==5)||
                            (src.getRow()==4&&src.getCol()==6&&dest.getRow()==4&&dest.getCol()==5)||
                            (src.getRow()==5&&src.getCol()==6&&dest.getRow()==5&&dest.getCol()==5)||

                            (src.getRow()==2&&src.getCol()==1&&dest.getRow()==3&&dest.getCol()==1)||
                            (src.getRow()==2&&src.getCol()==2&&dest.getRow()==3&&dest.getCol()==2)||
                            (src.getRow()==6&&src.getCol()==1&&dest.getRow()==5&&dest.getCol()==1)||
                            (src.getRow()==6&&src.getCol()==2&&dest.getRow()==5&&dest.getCol()==2)||
                            (src.getRow()==2&&src.getCol()==4&&dest.getRow()==3&&dest.getCol()==4)||
                            (src.getRow()==2&&src.getCol()==5&&dest.getRow()==3&&dest.getCol()==5)||
                            (src.getRow()==6&&src.getCol()==4&&dest.getRow()==5&&dest.getCol()==4)||
                            (src.getRow()==6&&src.getCol()==5&&dest.getRow()==5&&dest.getCol()==5)
                    ){
                        return false;
                    }
                }
                if(small.getName().equals("Mouse")){
                    if((dest.getRow()==3&&dest.getCol()==0&&src.getRow()==3&&src.getCol()==1)||
                            (dest.getRow()==4&&dest.getCol()==0&&src.getRow()==4&&src.getCol()==1)||
                            (dest.getRow()==5&&dest.getCol()==0&&src.getRow()==5&&src.getCol()==1)||
                            (dest.getRow()==3&&dest.getCol()==3&&src.getRow()==3&&src.getCol()==2)||
                            (dest.getRow()==4&&dest.getCol()==3&&src.getRow()==4&&src.getCol()==2)||
                            (dest.getRow()==5&&dest.getCol()==3&&src.getRow()==5&&src.getCol()==2)||
                            (dest.getRow()==3&&dest.getCol()==3&&src.getRow()==3&&src.getCol()==4)||
                            (dest.getRow()==4&&dest.getCol()==3&&src.getRow()==4&&src.getCol()==4)||
                            (dest.getRow()==5&&dest.getCol()==3&&src.getRow()==5&&src.getCol()==4)||
                            (dest.getRow()==3&&dest.getCol()==6&&src.getRow()==3&&src.getCol()==5)||
                            (dest.getRow()==4&&dest.getCol()==6&&src.getRow()==4&&src.getCol()==5)||
                            (dest.getRow()==5&&dest.getCol()==6&&src.getRow()==5&&src.getCol()==5)||

                            (dest.getRow()==2&&dest.getCol()==1&&src.getRow()==3&&src.getCol()==1)||
                            (dest.getRow()==2&&dest.getCol()==2&&src.getRow()==3&&src.getCol()==2)||
                            (dest.getRow()==6&&dest.getCol()==1&&src.getRow()==5&&src.getCol()==1)||
                            (dest.getRow()==6&&dest.getCol()==2&&src.getRow()==5&&src.getCol()==2)||
                            (dest.getRow()==2&&dest.getCol()==4&&src.getRow()==3&&src.getCol()==4)||
                            (dest.getRow()==2&&dest.getCol()==5&&src.getRow()==3&&src.getCol()==5)||
                            (dest.getRow()==6&&dest.getCol()==4&&src.getRow()==5&&src.getCol()==4)||
                            (dest.getRow()==6&&dest.getCol()==5&&src.getRow()==5&&src.getCol()==5)
                    ){
                        return false;
                    }
                }
                //还有一个问题在这里 右边一六没办法吃
                if(small.canCapture(big)){
                    return small.canCapture(big);
                }
            }
            if((((dest.getCol()==0&&dest.getRow()==3)||(dest.getCol()==0&&dest.getRow()==4)||(dest.getCol()==0&&dest.getRow()==5))&&((src.getCol()==3&&src.getRow()==3)||(src.getCol()==3&&src.getRow()==4)||(src.getCol()==3&&src.getRow()==5)))
                    ||(((dest.getCol()==3&&dest.getRow()==3)||(dest.getCol()==3&&dest.getRow()==4)||(dest.getCol()==3&&dest.getRow()==5))&&((src.getCol()==6&&src.getRow()==3)||(src.getCol()==6&&src.getRow()==4)||(src.getCol()==6&&src.getRow()==5)))
                    ||(((dest.getCol()==3&&dest.getRow()==3)||(dest.getCol()==3&&dest.getRow()==4)||(dest.getCol()==3&&dest.getRow()==5))&&((src.getCol()==0&&src.getRow()==3)||(src.getCol()==0&&src.getRow()==4)||(src.getCol()==0&&src.getRow()==5)))
                    ||(((dest.getCol()==6&&dest.getRow()==3)||(dest.getCol()==6&&dest.getRow()==4)||(dest.getCol()==6&&dest.getRow()==5))&&((src.getCol()==3&&src.getRow()==3)||(src.getCol()==3&&src.getRow()==4)||(src.getCol()==3&&src.getRow()==5)))
                    ||(((dest.getCol()==1&&dest.getRow()==2)||(dest.getCol()==2&&dest.getRow()==2)||(dest.getCol()==4&&dest.getRow()==2)||(dest.getCol()==5&&dest.getRow()==2))&&((src.getCol()==1&&src.getRow()==6)||(src.getCol()==2&&src.getRow()==6)||(src.getCol()==4&&src.getRow()==6)||(src.getCol()==5&&src.getRow()==6)))
                    ||(((dest.getCol()==1&&dest.getRow()==6)||(dest.getCol()==2&&dest.getRow()==6)||(dest.getCol()==4&&dest.getRow()==6)||(dest.getCol()==5&&dest.getRow()==6))&&((src.getCol()==1&&src.getRow()==2)||(src.getCol()==2&&src.getRow()==2)||(src.getCol()==4&&src.getRow()==2)||(src.getCol()==5&&src.getRow()==2)))){
                if(small.getName().equals("Lion")){
                    if((src.getCol()==dest.getCol() && src.getRow()==dest.getRow()+4)
                            ||(src.getCol()==dest.getCol() && src.getRow()==dest.getRow()-4)
                            ||(src.getCol()==dest.getCol()+3 && src.getRow()==dest.getRow())
                            ||(src.getCol()==dest.getCol()-3 && src.getRow()==dest.getRow())){
                        //老鼠在河流里，狮子就过不去啦
                        //左边6个
                        System.out.println((grid[3][1].getPiece()!=null));
                        if((grid[3][1].getPiece()!=null)&&((src.getCol()==0&&src.getRow()==3)||(src.getCol()==1&&src.getRow()==2)||(src.getCol()==1&&src.getRow()==6)||((src.getCol()==3&&src.getRow()==3)&&(dest.getCol()==0&&dest.getRow()==3)))){
                            return false;
                        }
                        if((grid[3][2].getPiece()!=null)&&((src.getCol()==0&&src.getRow()==3)||(src.getCol()==2&&src.getRow()==2)||(src.getCol()==2&&src.getRow()==6)||((src.getCol()==3&&src.getRow()==3)&&(dest.getCol()==0&&dest.getRow()==3)))){
                            return false;
                        }
                        if((grid[4][1].getPiece()!=null)&&((src.getCol()==0&&src.getRow()==4)||(src.getCol()==1&&src.getRow()==2)||(src.getCol()==1&&src.getRow()==6)||((src.getCol()==3&&src.getRow()==4)&&(dest.getCol()==0&&dest.getRow()==4)))){
                            return false;
                        }
                        if((grid[4][2].getPiece()!=null)&&((src.getCol()==0&&src.getRow()==4)||(src.getCol()==2&&src.getRow()==2)||(src.getCol()==2&&src.getRow()==6)||((src.getCol()==3&&src.getRow()==4)&&(dest.getCol()==0&&dest.getRow()==4)))){
                            return false;
                        }
                        if((grid[5][1].getPiece()!=null)&&((src.getCol()==0&&src.getRow()==5)||(src.getCol()==1&&src.getRow()==2)||(src.getCol()==1&&src.getRow()==6)||((src.getCol()==3&&src.getRow()==5)&&(dest.getCol()==0&&dest.getRow()==5)))){
                            return false;
                        }
                        if((grid[5][2].getPiece()!=null)&&((src.getCol()==0&&src.getRow()==5)||(src.getCol()==2&&src.getRow()==2)||(src.getCol()==2&&src.getRow()==6)||((src.getCol()==3&&src.getRow()==5)&&(dest.getCol()==0&&dest.getRow()==5)))){
                            return false;
                        }
                        //右边6个
                        if((grid[3][4].getPiece()!=null)&&((src.getCol()==6&&src.getRow()==3)||(src.getCol()==4&&src.getRow()==2)||(src.getCol()==4&&src.getRow()==6)||((src.getCol()==3&&src.getRow()==3)&&(dest.getCol()==6&&dest.getRow()==3)))){
                            return false;
                        }
                        if((grid[3][5].getPiece()!=null)&&((src.getCol()==6&&src.getRow()==3)||(src.getCol()==5&&src.getRow()==2)||(src.getCol()==5&&src.getRow()==6)||((src.getCol()==3&&src.getRow()==3)&&(dest.getCol()==6&&dest.getRow()==3)))){
                            return false;
                        }
                        if((grid[4][4].getPiece()!=null)&&((src.getCol()==6&&src.getRow()==4)||(src.getCol()==4&&src.getRow()==2)||(src.getCol()==4&&src.getRow()==6)||((src.getCol()==3&&src.getRow()==4)&&(dest.getCol()==6&&dest.getRow()==4)))){
                            return false;
                        }
                        if((grid[4][5].getPiece()!=null)&&((src.getCol()==6&&src.getRow()==4)||(src.getCol()==5&&src.getRow()==2)||(src.getCol()==5&&src.getRow()==6)||((src.getCol()==3&&src.getRow()==4)&&(dest.getCol()==6&&dest.getRow()==4)))){
                            return false;
                        }
                        if((grid[5][4].getPiece()!=null)&&((src.getCol()==6&&src.getRow()==5)||(src.getCol()==4&&src.getRow()==2)||(src.getCol()==4&&src.getRow()==6)||((src.getCol()==3&&src.getRow()==5)&&(dest.getCol()==6&&dest.getRow()==5)))){
                            return false;
                        }
                        if((grid[5][5].getPiece()!=null)&&((src.getCol()==6&&src.getRow()==5)||(src.getCol()==5&&src.getRow()==2)||(src.getCol()==5&&src.getRow()==6)||((src.getCol()==3&&src.getRow()==5)&&(dest.getCol()==6&&dest.getRow()==5)))){
                            return false;
                        }
                    }
                    if(small.canCapture(big)) {

                        return small.canCapture(big);
                    }
                }
                if(small.getName().equals("Tiger")){
                    if((src.getCol()==dest.getCol() && src.getRow()==dest.getRow()+4)
                            ||(src.getCol()==dest.getCol() && src.getRow()==dest.getRow()-4)
                            ||(src.getCol()==dest.getCol()+3 && src.getRow()==dest.getRow())
                            ||(src.getCol()==dest.getCol()-3 && src.getRow()==dest.getRow())){
                        //老鼠在河流里，老虎就过不去啦
                        //左边6个
                        System.out.println((grid[3][1].getPiece()!=null));
                        if((grid[3][1].getPiece()!=null)&&((src.getCol()==0&&src.getRow()==3)||(src.getCol()==1&&src.getRow()==2)||(src.getCol()==1&&src.getRow()==6)||((src.getCol()==3&&src.getRow()==3)&&(dest.getCol()==0&&dest.getRow()==3)))){
                            return false;
                        }
                        if((grid[3][2].getPiece()!=null)&&((src.getCol()==0&&src.getRow()==3)||(src.getCol()==2&&src.getRow()==2)||(src.getCol()==2&&src.getRow()==6)||((src.getCol()==3&&src.getRow()==3)&&(dest.getCol()==0&&dest.getRow()==3)))){
                            return false;
                        }
                        if((grid[4][1].getPiece()!=null)&&((src.getCol()==0&&src.getRow()==4)||(src.getCol()==1&&src.getRow()==2)||(src.getCol()==1&&src.getRow()==6)||((src.getCol()==3&&src.getRow()==4)&&(dest.getCol()==0&&dest.getRow()==4)))){
                            return false;
                        }
                        if((grid[4][2].getPiece()!=null)&&((src.getCol()==0&&src.getRow()==4)||(src.getCol()==2&&src.getRow()==2)||(src.getCol()==2&&src.getRow()==6)||((src.getCol()==3&&src.getRow()==4)&&(dest.getCol()==0&&dest.getRow()==4)))){
                            return false;
                        }
                        if((grid[5][1].getPiece()!=null)&&((src.getCol()==0&&src.getRow()==5)||(src.getCol()==1&&src.getRow()==2)||(src.getCol()==1&&src.getRow()==6)||((src.getCol()==3&&src.getRow()==5)&&(dest.getCol()==0&&dest.getRow()==5)))){
                            return false;
                        }
                        if((grid[5][2].getPiece()!=null)&&((src.getCol()==0&&src.getRow()==5)||(src.getCol()==2&&src.getRow()==2)||(src.getCol()==2&&src.getRow()==6)||((src.getCol()==3&&src.getRow()==5)&&(dest.getCol()==0&&dest.getRow()==5)))){
                            return false;
                        }
                        //右边6个
                        if((grid[3][4].getPiece()!=null)&&((src.getCol()==6&&src.getRow()==3)||(src.getCol()==4&&src.getRow()==2)||(src.getCol()==4&&src.getRow()==6)||((src.getCol()==3&&src.getRow()==3)&&(dest.getCol()==6&&dest.getRow()==3)))){
                            return false;
                        }
                        if((grid[3][5].getPiece()!=null)&&((src.getCol()==6&&src.getRow()==3)||(src.getCol()==5&&src.getRow()==2)||(src.getCol()==5&&src.getRow()==6)||((src.getCol()==3&&src.getRow()==3)&&(dest.getCol()==6&&dest.getRow()==3)))){
                            return false;
                        }
                        if((grid[4][4].getPiece()!=null)&&((src.getCol()==6&&src.getRow()==4)||(src.getCol()==4&&src.getRow()==2)||(src.getCol()==4&&src.getRow()==6)||((src.getCol()==3&&src.getRow()==4)&&(dest.getCol()==6&&dest.getRow()==4)))){
                            return false;
                        }
                        if((grid[4][5].getPiece()!=null)&&((src.getCol()==6&&src.getRow()==4)||(src.getCol()==5&&src.getRow()==2)||(src.getCol()==5&&src.getRow()==6)||((src.getCol()==3&&src.getRow()==4)&&(dest.getCol()==6&&dest.getRow()==4)))){
                            return false;
                        }
                        if((grid[5][4].getPiece()!=null)&&((src.getCol()==6&&src.getRow()==5)||(src.getCol()==4&&src.getRow()==2)||(src.getCol()==4&&src.getRow()==6)||((src.getCol()==3&&src.getRow()==5)&&(dest.getCol()==6&&dest.getRow()==5)))){
                            return false;
                        }
                        if((grid[5][5].getPiece()!=null)&&((src.getCol()==6&&src.getRow()==5)||(src.getCol()==5&&src.getRow()==2)||(src.getCol()==5&&src.getRow()==6)||((src.getCol()==3&&src.getRow()==5)&&(dest.getCol()==6&&dest.getRow()==5)))){
                            return false;
                        }
                    }
                    if(small.canCapture(big)){
                        return small.canCapture(big);
                    }
                }
            }
        }


        return false;
    }
    public void TrapPunish(ChessboardPoint point) {
        if((getGridAt(point).getPiece().getOwner()==PlayerColor.RED)&&(point.getRow()==0)||(point.getRow()==1)){
            getGridAt(point).getPiece().setRank(0);
        }
        if((getGridAt(point).getPiece().getOwner()==PlayerColor.BLUE)&&(point.getRow()==7)||(point.getRow()==8)){
            getGridAt(point).getPiece().setRank(0);
        }

    }
}
