package com.chess.engine.player;

import com.chess.engine.board.Board;
import com.chess.engine.board.Move;

//Represents transition from one board to next when move happens
public class MoveTransition {
    private final Board transitionBoard;
    private final Move move;
    private final MoveStatus moveStatus;

    public MoveTransition(final Board transitionBoard,
                          final Move move,
                          final MoveStatus moveStatus) {
        this.transitionBoard = transitionBoard;
        this.move = move;
        this.moveStatus = moveStatus;
    }

    public MoveStatus getMoveStatus() {
        return this.moveStatus;
    }

    public Board getBoard() {return this.transitionBoard;}
}
