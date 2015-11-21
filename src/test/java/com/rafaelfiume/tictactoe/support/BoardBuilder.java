package com.rafaelfiume.tictactoe.support;

import com.rafaelfiume.tictactoe.Board;
import com.rafaelfiume.tictactoe.BoardPosition;

public class BoardBuilder {

    private final Board board = new Board();

    public BoardBuilder withPlayerXChoosing(BoardPosition boardPosition) {
        board.playerChooses(boardPosition);
        return this;
    }

    public BoardBuilder withPlayerOChoosing(BoardPosition boardPosition) {
        board.playerChooses(boardPosition);
        return this;
    }

    public Board build() {
        return board;
    }

}
