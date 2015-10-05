package src.com.lhumphr2.chess.model;

import src.com.lhumphr2.chess.model.ChessPieces.ChessPiece;

/**
 * Created by lawrencehumphrey on 9/13/15.
 * Represents any arbitrary piece state.
 * i.e. where a piece was.
 */
public class State {
    Integer idx;
    ChessPiece piece;

    public State(Integer idx, ChessPiece piece) {
        this.idx = idx;
        this.piece = piece;
    }
}
