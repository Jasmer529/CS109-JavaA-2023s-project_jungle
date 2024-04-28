package model;


public class ChessPiece {
    // the owner of the chess
    private PlayerColor owner;

    // Elephant? Cat? Dog? ...
    private String name;
    private int rank;

    public void setOwner(PlayerColor owner) {
        this.owner = owner;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public ChessPiece(PlayerColor owner, String name, int rank) {
        this.owner = owner;
        this.name = name;
        this.rank = rank;
    }

    public boolean canCapture(ChessPiece target) {
        if (this.name.equals("Elephant") && target.name.equals("Mouse")){
            return false;
        }
        if (this.name.equals("Mouse") && target.name.equals("Elephant")){
            return true;
        }
        if (this.rank >= target.rank) {
            return true;
        }
       else {
            return false;
        }
    }

    public String getName() {
        return name;
    }

    public PlayerColor getOwner() {
        return owner;
    }
}
