package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.chess.engine.board.Move.*;

public class Pawn extends Piece{
    private final static int[] CANDIDATE_MOVE_COORDINATES = {8, 16};
    Pawn(int piecePosition, Alliance pieceAlliance) { super(piecePosition, pieceAlliance); }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {

        final List<Move> legalMoves = new ArrayList<>();

        for (final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATES) {
            final int candidateDestinationCoordinate = this.piecePosition + (this.getPieceAlliance().getDirection() *
                    currentCandidateOffset);

            if (!BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
                continue;
            }
            if (currentCandidateOffset == 8 && !board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                //TODO Temporary legalMoves addition must flush out
                legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
            }
            else if (currentCandidateOffset == 16 && this.isFirstMove() && (BoardUtils.SECOND_ROW[this.piecePosition] &&
                    this.getPieceAlliance().isBlack()) || (BoardUtils.SEVENTH_ROW[this.piecePosition] &&
                    this.getPieceAlliance().isWhite())) {

                final int behindCandidateDestinationCorrdinate = this.piecePosition + (this.pieceAlliance.getDirection() * 8);

                if (!board.getTile(behindCandidateDestinationCorrdinate).isTileOccupied() &&
                        !board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                    //TODO Temporary legalMoves addition must flush out
                    legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
                }

            }
        }
        return null;
    }
}
