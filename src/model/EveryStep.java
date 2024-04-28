//package model;
//
//public class EveryStep {
//    public ChessboardPoint src;
//    public ChessboardPoint dest;
//    public PlayerColor color;
//    public ChessPiece captured;
//    public EveryStep(ChessboardPoint src, ChessboardPoint dest, PlayerColor color) {
//        this.src = src;
//        this.dest = dest;
//        this.color = color;
//        captured = null;
//    }
//    public EveryStep(ChessboardPoint src, ChessboardPoint dest, PlayerColor color, ChessPiece captured) {
//        this.src = src;
//        this.dest = dest;
//        this.color = color;
//        this.captured = captured;
//    }
//    public String toString() {
//        if (captured == null)
//            return (color == PlayerColor.BLUE ? "b " : "r ") +
//                    "(" + src.getRow() + "," + src.getCol() + ") " +
//                    "(" + dest.getRow() +"," + dest.getCol() + ") " +
//                    "null";
//        else
//            return (color == PlayerColor.BLUE ? "b " : "r ") +
//                    "(" + src.getRow() + "," + src.getCol() + ") " +
//                    "(" + dest.getRow() +"," + dest.getCol() + ") " +
//                    captured.getName();
//    }
//
//}
package model;

import java.io.Serializable;

public class EveryStep implements Serializable, Comparable<EveryStep> {

    private ChessPiece Alpha;
    private ChessPiece Omega;
    private PlayerColor currentPlayer;
    private int value;

    public PlayerColor getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(PlayerColor currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    //value不参与对象的序列化和反序列化，即不会被保存到文件或通过网络传输
    private ChessboardPoint src;
    private ChessboardPoint dest;
    public EveryStep(ChessboardPoint src, ChessboardPoint dest, ChessPiece Alpha, ChessPiece Omega, PlayerColor currentPlayer) {
        this.src = src;
        this.dest = dest;
        this.Alpha = Alpha;
        this.Omega = Omega;
        this.currentPlayer = currentPlayer;
    }

    public ChessPiece getAlpha() {
        return Alpha;
    }

    public ChessPiece getOmega() {
        return Omega;
    }

    public ChessboardPoint getSrc() {
        return src;
    }

    public ChessboardPoint getDest() {
        return dest;
    }

    public void setDest(ChessboardPoint dest) {
        this.dest = dest;
    }


    public int getValue() {
        return value;
    }

    @Override
    public int compareTo(EveryStep E) {
        return E.getValue() - this.getValue();
    }
}