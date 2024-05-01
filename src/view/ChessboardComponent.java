package view;


import controller.GameController;
import model.*;
import view.Animal.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static model.Constant.CHESSBOARD_COL_SIZE;
import static model.Constant.CHESSBOARD_ROW_SIZE;

/**
 * This class represents the checkerboard component object on the panel
 */
public class ChessboardComponent extends JComponent {
    private final CellComponent[][] gridComponents = new CellComponent[CHESSBOARD_ROW_SIZE.getNum()][CHESSBOARD_COL_SIZE.getNum()];
    private final int CHESS_SIZE;
    private final Set<ChessboardPoint> riverCell = new HashSet<>();
    private final Set<ChessboardPoint> TrapCell = new HashSet<>();
    private final Set<ChessboardPoint> DensCell = new HashSet<>();
    private GameController gameController;
    private Graphics g;
    public Chessboard model ;
    private ImageIcon trap1;
    private ImageIcon trap2;
    private ImageIcon dens1;
    private ImageIcon dens2;

    public ChessboardComponent(int chessSize) {

        CHESS_SIZE = chessSize;
        int width = CHESS_SIZE * 7;
        int height = CHESS_SIZE * 9;
        trap1=new ImageIcon("D:\\bak\\谁是小卷怪\\CS109PJ\\background\\Trap.jpgg");
        enableEvents(AWTEvent.MOUSE_EVENT_MASK);// Allow mouse events to occur
        setLayout(null); // Use absolute layout.
        setSize(width, height);
        System.out.printf("chessboard width, height = [%d : %d], chess size = %d\n", width, height, CHESS_SIZE);

        initiateGridComponents(new Chessboard());
    }
//    public void showing(ChessboardPoint pot){
//        CellComponent cell;
//        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
//            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {
//                ChessboardPoint show = new ChessboardPoint(i,j);
//                if(model.isValidMove(pot, show)){
//                    cell = new CellComponent(Color.lightGray, calculatePoint(i, j), CHESS_SIZE);
//                    this.add(cell);
//                    gridComponents[i][j] = cell;
//                }
//            }
//        }
//    }
    /**
     * This method represents how to initiate ChessComponent
     * according to Chessboard information
     */
    public void initiateReading(Chessboard chessboard){
        Cell[][] grid = chessboard.getGrid();
        for (int i = 0; i < CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < CHESSBOARD_COL_SIZE.getNum(); j++) {
                if(grid[i][j].getPiece()!=null){
                    ChessPiece chessPiece = grid[i][j].getPiece();
                    if(chessPiece.getName().equals("Elephant")){
                        gridComponents[i][j].add(
                                new ElephantChessComponent(
                                        chessPiece.getOwner(),
                                        CHESS_SIZE));
                    }
                    if(chessPiece.getName().equals("Lion")){
                            gridComponents[i][j].add(
                                    new LionChessComponent(
                                            chessPiece.getOwner(),
                                            CHESS_SIZE));
                    }
                    if(chessPiece.getName().equals("Tiger")){
                        gridComponents[i][j].add(
                                new TigerChessComponent(
                                        chessPiece.getOwner(),
                                        CHESS_SIZE));
                    }
                    if(chessPiece.getName().equals("Leopard")){
                        gridComponents[i][j].add(
                                new LeopardChessComponent(
                                        chessPiece.getOwner(),
                                        CHESS_SIZE));
                    }
                    if(chessPiece.getName().equals("Wolf")){
                        gridComponents[i][j].add(
                                new WolfChessComponent(
                                        chessPiece.getOwner(),
                                        CHESS_SIZE));
                    }
                    if(chessPiece.getName().equals("Cat")){
                        gridComponents[i][j].add(
                                new CatChessComponent(
                                        chessPiece.getOwner(),
                                        CHESS_SIZE));
                    }
                    if(chessPiece.getName().equals("Dog")){
                        gridComponents[i][j].add(
                                new DogChessComponent(
                                        chessPiece.getOwner(),
                                        CHESS_SIZE));
                    }
                    if(chessPiece.getName().equals("Mouse")){
                        gridComponents[i][j].add(
                                new MouseChessComponent(
                                        chessPiece.getOwner(),
                                        CHESS_SIZE));
                    }
                }
            }
        }
    }
    public void initiateChessComponent(Chessboard chessboard) {
        Cell[][] grid = chessboard.getGrid();
        for (int i = 0; i < CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < CHESSBOARD_COL_SIZE.getNum(); j++) {
                // TODO: Implement the initialization checkerboard
                if((i==0 && j==0) || (i==8 && j==6)){
                    ChessPiece chessPiece = grid[i][j].getPiece();
                    if(chessPiece!=null){
                        gridComponents[i][j].add(
                                new LionChessComponent(
                                        chessPiece.getOwner(),
                                        CHESS_SIZE));
                    }
                }

                if ((i==2 && j==6) || (i==6 && j==0)) {
                    ChessPiece chessPiece = grid[i][j].getPiece();
                    if(chessPiece!=null){
                        gridComponents[i][j].add(
                                new ElephantChessComponent(
                                        chessPiece.getOwner(),
                                        CHESS_SIZE));
                    }
                }
                if ((i==0 && j==6) || (i==8 && j==0)) {
                    ChessPiece chessPiece = grid[i][j].getPiece();
                    if(chessPiece!=null){
                        gridComponents[i][j].add(
                                new TigerChessComponent(
                                        chessPiece.getOwner(),
                                        CHESS_SIZE));
                    }
                }
                if ((i==2 && j==2) || (i==6 && j==4)) {
                    ChessPiece chessPiece = grid[i][j].getPiece();
                    if(chessPiece!=null){
                        gridComponents[i][j].add(
                                new LeopardChessComponent(
                                        chessPiece.getOwner(),
                                        CHESS_SIZE));
                    }
                }
                if ((i==2 && j==4) || (i==6 && j==2)) {
                    ChessPiece chessPiece = grid[i][j].getPiece();
                    if(chessPiece!=null){
                        gridComponents[i][j].add(
                                new WolfChessComponent(
                                        chessPiece.getOwner(),
                                        CHESS_SIZE));
                    }
                }
                if ((i==1 && j==1) || (i==7 && j==5)) {
                    ChessPiece chessPiece = grid[i][j].getPiece();
                    if(chessPiece!=null){
                        gridComponents[i][j].add(
                                new DogChessComponent(
                                        chessPiece.getOwner(),
                                        CHESS_SIZE));
                    }
                }
                if ((i==1 && j==5) || (i==7 && j==1)) {
                    ChessPiece chessPiece = grid[i][j].getPiece();
                    if(chessPiece!=null){
                        gridComponents[i][j].add(
                                new CatChessComponent(
                                        chessPiece.getOwner(),
                                        CHESS_SIZE));
                    }
                }
                if ((i==2 && j==0) || (i==6 && j==6)) {
                    ChessPiece chessPiece = grid[i][j].getPiece();
                    if(chessPiece!=null){
                        gridComponents[i][j].add(
                                new MouseChessComponent(
                                        chessPiece.getOwner(),
                                        CHESS_SIZE));
                    }
                }
            }
        }
        System.out.println("初始化结束");
    }
    public void initiateGridComponents(Chessboard chessboard) {
        Cell[][] grid = chessboard.getGrid();
        riverCell.add(new ChessboardPoint(3,1));
        riverCell.add(new ChessboardPoint(3,2));
        riverCell.add(new ChessboardPoint(4,1));
        riverCell.add(new ChessboardPoint(4,2));
        riverCell.add(new ChessboardPoint(5,1));
        riverCell.add(new ChessboardPoint(5,2));

        riverCell.add(new ChessboardPoint(3,4));
        riverCell.add(new ChessboardPoint(3,5));
        riverCell.add(new ChessboardPoint(4,4));
        riverCell.add(new ChessboardPoint(4,5));
        riverCell.add(new ChessboardPoint(5,4));
        riverCell.add(new ChessboardPoint(5,5));

        DensCell.add(new ChessboardPoint(0,3));
        DensCell.add(new ChessboardPoint(8,3));

        TrapCell.add(new ChessboardPoint(0,2));
        TrapCell.add(new ChessboardPoint(0,4));
        TrapCell.add(new ChessboardPoint(1,3));
        TrapCell.add(new ChessboardPoint(8,2));
        TrapCell.add(new ChessboardPoint(8,4));
        TrapCell.add(new ChessboardPoint(7,3));

        for (int i = 0; i < CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < CHESSBOARD_COL_SIZE.getNum(); j++) {
                ChessboardPoint temp = new ChessboardPoint(i, j);
                ChessboardPoint denss = new ChessboardPoint(i, j);
                ChessboardPoint trap = new ChessboardPoint(i, j);
                CellComponent cell;
                if (riverCell.contains(temp)) {
                    cell = new CellComponent(Color.CYAN, calculatePoint(i, j), CHESS_SIZE);//河的颜色
                    this.add(cell);
                    gridComponents[i][j] = cell;
                }
                else if(DensCell.contains(denss)){
                    if(i==0 && j==3){
                        cell = new CellComponent(Color.CYAN, calculatePoint(i, j), CHESS_SIZE);//兽穴的颜色
                        this.add(cell);
                        gridComponents[i][j] = cell;
                    }
                    if(i==8 && j==3){
                        cell = new CellComponent(Color.RED, calculatePoint(i, j), CHESS_SIZE);//兽穴的颜色
                        this.add(cell);
                        gridComponents[i][j] = cell;
                    }
                }
                else if(TrapCell.contains(trap)){
                    if((i==0 && j==2)||(i==0 && j==4)||(i==1 && j==3)){
                        cell = new CellComponent(Color.BLUE,calculatePoint(i, j),CHESS_SIZE);//陷阱的颜色
                        this.add(cell);
                        gridComponents[i][j] = cell;
                    }
                    if((i==8 && j==2)||(i==8 && j==4)||(i==7 && j==3)){
                        cell = new CellComponent(Color.ORANGE, calculatePoint(i, j), CHESS_SIZE);//陷阱的颜色
                        this.add(cell);
                        gridComponents[i][j] = cell;
                    }
                }
                else {
                    cell = new CellComponent(Color.WHITE, calculatePoint(i, j), CHESS_SIZE);//背景的颜色
                    this.add(cell);
                    gridComponents[i][j] = cell;
                }
            }
        }
    }


    public void registerController(GameController gameController) {
        this.gameController = gameController;
        this.model=gameController.getModel();
    }

    public void setChessComponentAtGrid(ChessboardPoint point, ChessPieceComponent chess) {
        if(chess!=null){
            getGridComponentAt(point).add(chess);
        }
            getGridComponentAt(point).setName(chess.getName());
            getGridComponentAt(point).revalidate();
            getGridComponentAt(point).repaint();
           for (Component c : getGridComponentAt(point).getComponents()) {
               if (c instanceof CellComponent) {
                    c.revalidate();
                   c.repaint();
                }
            }
    }
   // ArrayList<ChessPieceComponent> chessPieceComponents = new ArrayList<>();
    public ChessPieceComponent removeChessComponentAtGrid(ChessboardPoint point) {
        if (getGridComponentAt(point).getComponents().length != 0) {
            ChessPieceComponent chess = (ChessPieceComponent) getGridComponentAt(point).getComponents()[0];
            getGridComponentAt(point).removeAll();
            getGridComponentAt(point).revalidate();
            chess.setSelected(false);
            //chessPieceComponents.add(chess);
            return chess;
        }
        else {
            return null;
        }
    }
//    public void setNewOne(Chessboard chessboard){
//        Cell[][] grid = chessboard.getGrid();
//        ChessPiece chessPiece = grid[getX()][getY()].getPiece();
//        if(chessPieceComponents.get(chessPieceComponents.size()).equals()){
//            gridComponents[getX()][getY()].add(
//                    new LionChessComponent(
//                            chessPiece.getOwner(),
//                            CHESS_SIZE));
//        }
//    }
//public ChessPieceComponent lastChessComponentAtGrid(Chessboard chessboard){
//        ChessboardPoint wing=new ChessboardPoint(chessboard.getA(),chessboard.getB());
//        if(chessboard.dest().equals(wing)){
//            ChessPieceComponent chess = chessPieceComponents.get(chessPieceComponents.size()-2);
//            System.out.println("怪");
//            return chess;
//        }
//        else {
//            ChessPieceComponent chess = chessPieceComponents.get(chessPieceComponents.size()-4);
//            return chess;
//        }
//   }
public CellComponent getGridComponentAt(ChessboardPoint point) {
        return gridComponents[point.getRow()][point.getCol()];
    }

    private ChessboardPoint getChessboardPoint(Point point) {
        System.out.println("[" + point.y/CHESS_SIZE +  ", " +point.x/CHESS_SIZE + "] Clicked");
        return new ChessboardPoint(point.y/CHESS_SIZE, point.x/CHESS_SIZE);
    }
    private Point calculatePoint(int row, int col) {
        return new Point(col * CHESS_SIZE, row * CHESS_SIZE);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    @Override
    protected void processMouseEvent(MouseEvent e) {
        if (e.getID() == MouseEvent.MOUSE_PRESSED) {
            JComponent clickedComponent = (JComponent) getComponentAt(e.getX(), e.getY());
            if (clickedComponent.getComponentCount() == 0) {
                System.out.print("None chess here and ");
                gameController.onPlayerClickCell(getChessboardPoint(e.getPoint()), (CellComponent) clickedComponent);
            } else {
                System.out.print("One chess here and ");
               // showing(getChessboardPoint(e.getPoint()));
                gameController.onPlayerClickChessPiece(getChessboardPoint(e.getPoint()), (ChessPieceComponent) clickedComponent.getComponents()[0]);
            }
        }
    }

    public void removeAllPieces(){
        for (int i = 0; i < CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < CHESSBOARD_COL_SIZE.getNum(); j++) {
                if(gridComponents[i][j].getComponents().length>0){
                    removeChessComponentAtGrid(new ChessboardPoint(i,j));
                }
            }
        }
    }
    public void everyone(EveryStep step) {
        ChessboardPoint src = step.getSrc();
        ChessboardPoint dest = step.getDest();
        ChessPiece Alpha = step.getAlpha();
        ChessPiece Omega = step.getOmega();
        if (Omega == null) {
            removeChessComponentAtGrid(dest);
        } else {
            removeChessComponentAtGrid(dest);
            if (Omega.getName().equals("Elephant")){
                setChessComponentAtGrid(
                        dest, new ElephantChessComponent(Omega.getOwner(), CHESS_SIZE));
            }if (Omega.getName().equals("Lion")){
                setChessComponentAtGrid(
                        dest, new LionChessComponent(Omega.getOwner(), CHESS_SIZE));
            }if (Omega.getName().equals("Tiger")){
                setChessComponentAtGrid(
                        dest, new TigerChessComponent(Omega.getOwner(), CHESS_SIZE));
            }if (Omega.getName().equals("Leopard")){
                setChessComponentAtGrid(
                        dest, new LeopardChessComponent(Omega.getOwner(), CHESS_SIZE));
            }if (Omega.getName().equals("Wolf")){
                setChessComponentAtGrid(
                        dest, new WolfChessComponent(Omega.getOwner(), CHESS_SIZE));
            }if (Omega.getName().equals("Dog")){
                setChessComponentAtGrid(
                        dest, new DogChessComponent(Omega.getOwner(), CHESS_SIZE));
            }if (Omega.getName().equals("Cat")){
                setChessComponentAtGrid(
                        dest, new CatChessComponent(Omega.getOwner(), CHESS_SIZE));
            }if (Omega.getName().equals("Mouse")){
                setChessComponentAtGrid(
                        dest, new MouseChessComponent(Omega.getOwner(), CHESS_SIZE));
            }
        }

        if (Alpha.getName().equals("Elephant")){
            setChessComponentAtGrid(
                    src, new ElephantChessComponent(Alpha.getOwner(), CHESS_SIZE));
        }if (Alpha.getName().equals("Lion")){
            setChessComponentAtGrid(
                    src, new LionChessComponent(Alpha.getOwner(), CHESS_SIZE));
        }if (Alpha.getName().equals("Tiger")){
            setChessComponentAtGrid(
                    src, new TigerChessComponent(Alpha.getOwner(), CHESS_SIZE));
        }if (Alpha.getName().equals("Leopard")){
            setChessComponentAtGrid(
                    src, new LeopardChessComponent(Alpha.getOwner(), CHESS_SIZE));
        }if (Alpha.getName().equals("Wolf")){
            setChessComponentAtGrid(
                    src, new WolfChessComponent(Alpha.getOwner(), CHESS_SIZE));
        }if (Alpha.getName().equals("Dog")){
            setChessComponentAtGrid(
                    src, new DogChessComponent(Alpha.getOwner(), CHESS_SIZE));
        }if (Alpha.getName().equals("Cat")){
            setChessComponentAtGrid(
                    src, new CatChessComponent(Alpha.getOwner(), CHESS_SIZE));
        }if (Alpha.getName().equals("Mouse")){
            setChessComponentAtGrid(
                    src, new MouseChessComponent(Alpha.getOwner(), CHESS_SIZE));
        }


    }


}

