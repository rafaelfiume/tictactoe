package com.rafaelfiume.tictactoe.support

import com.rafaelfiume.tictactoe.Board
import com.rafaelfiume.tictactoe.BoardPosition.*
import com.rafaelfiume.tictactoe.Player
import org.mockito.BDDMockito

object TestingBoards {

    fun givenABoard(): Board = BDDMockito.mock(Board::class.java)

    fun whenPlayerWinsWithA_VerticalRow_InThe(board: Board) {
        board.markPositionIfAvailable(TOP_RIGHT, Player.X)
        board.markPositionIfAvailable(CENTER, Player.O)
        board.markPositionIfAvailable(MID_RIGHT, Player.X)
        board.markPositionIfAvailable(BOTTOM_CENTER, Player.O)
        board.markPositionIfAvailable(BOTTOM_RIGHT, Player.X)
    }

    fun whenPlayerWinsWithAn_HorizontalRow_InThe(board: Board) {
        board.markPositionIfAvailable(TOP_RIGHT, Player.X)
        board.markPositionIfAvailable(CENTER, Player.O)
        board.markPositionIfAvailable(TOP_LEFT, Player.X)
        board.markPositionIfAvailable(MID_LEFT, Player.O)
        board.markPositionIfAvailable(BOTTOM_RIGHT, Player.X)
        board.markPositionIfAvailable(MID_RIGHT, Player.O)
    }

    fun whenPlayerWinsWithA_DiagonalRow_InThe(board: Board) {
        board.markPositionIfAvailable(TOP_RIGHT, Player.X)
        board.markPositionIfAvailable(TOP_LEFT, Player.O)
        board.markPositionIfAvailable(CENTER, Player.X)
        board.markPositionIfAvailable(MID_LEFT, Player.O)
        board.markPositionIfAvailable(BOTTOM_LEFT, Player.X)
    }

}