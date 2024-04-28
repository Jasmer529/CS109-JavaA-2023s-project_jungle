package controller;

import model.Chessboard;
import model.EveryStep;
import model.PlayerColor;

import java.util.List;

public class AIPlayer{
private Chessboard model;

    AIPlayer(Chessboard model) {
        this.model = model;
    }
    public EveryStep generateMove(PlayerColor color){

        List<EveryStep> steps = model.getValidSteps(color);
        model.getValidSteps(color).clear();
        if (steps.size()>0){
           return steps.get((int)(Math.random()* steps.size()));
        }
        return null;
    }
}