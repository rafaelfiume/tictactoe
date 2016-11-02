package com.rafaelfiume.tictactoe

import com.rafaelfiume.tictactoe.BoardPosition.UNKNOWN
import java.lang.Character.isLetter

class Board private constructor(private val grid: Array<CharArray>) : Game {

    private var currentPlayer = Player.X // this concerns turns
    private var currentTurn = 1 // this concerns turns

    private var gameStarted = false // is this concerning the board???

    constructor() : this(Array(3) { CharArray(3) }) // this(new char[3][3]);

    override fun playerChooses(boardPosition: BoardPosition) {
        val playerTurnEnded = markPositionIfAvailable(boardPosition) // this concerns turns

        if (playerTurnEnded) {
            this.currentPlayer = switchPlayerIfGameIsNotOver()
            this.currentTurn++
        }

        this.gameStarted = true
    }

    override fun snapshot(): Game {
        val snapshot = Board(cloneArray(grid))
        snapshot.currentPlayer = this.currentPlayer
        snapshot.currentTurn = this.currentTurn
        snapshot.gameStarted = this.gameStarted

        return snapshot
    }

    override fun gameHasNotStarted() = !gameStarted

    override fun gameIsOn() = !isGameOver()

    override fun hasWinner() = hasVerticalWinner() || hasHorizontalWinner() || hasDiagonalWinner()

    override fun hasDraw() = noMoreTurns() && !hasWinner()

    override fun winner(): Player = currentPlayer

    override fun currentPlayer(): Player = currentPlayer

    override fun topLeft(): Char = grid[0][0]
    override fun topCenter(): Char = grid[0][1]
    override fun topRight(): Char = grid[0][2]
    override fun midLeft(): Char = grid[1][0]
    override fun center(): Char = grid[1][1]
    override fun midRight(): Char = grid[1][2]
    override fun bottomLeft(): Char = grid[2][0]
    override fun bottomCenter(): Char = grid[2][1]
    override fun bottomRight(): Char = grid[2][2]
    internal fun currentTurn(): Int = currentTurn

    private fun isGameOver() = hasWinner() || hasDraw()

    private fun noMoreTurns() = currentTurn == 10

    private fun markPositionIfAvailable(position: BoardPosition): Boolean {
        if (position == UNKNOWN || cellIsMarkedAt(grid[position.row()][position.column()])) {
            return false
        }

        grid[position.row()][position.column()] = currentPlayer.symbol()
        return true
    }

    private fun switchPlayerIfGameIsNotOver(): Player {
        if (isGameOver()) return currentPlayer
        return if (currentPlayer == Player.X) Player.O else Player.X
    }

    private fun hasVerticalWinner(): Boolean {
        for (i in 0..2) {
            if (cellIsMarkedAt(grid[0][i])
                    && grid[0][i] == grid[1][i]
                    && grid[1][i] == grid[2][i]) {
                return true
            }
        }
        return false
    }

    private fun hasHorizontalWinner(): Boolean {
        for (i in 0..2) {
            if (cellIsMarkedAt(grid[i][0])
                    && grid[i][0] == grid[i][1]
                    && grid[i][1] == grid[i][2]) {
                return true
            }
        }
        return false
    }

    private fun hasDiagonalWinner(): Boolean {
        return cellIsMarkedAt(topLeft())
                && topLeft() == center()
                && center() == bottomRight()

                || cellIsMarkedAt(topRight())
                && topRight() == center()
                && center() == bottomLeft()
    }

    private fun cellIsMarkedAt(c: Char?) = isLetter(c!!)

    private fun cloneArray(src: Array<CharArray>): Array<CharArray> {
        val length = src.size
        val target = Array(length) { CharArray(src[0].size) }
        for (i in 0..length - 1) {
            System.arraycopy(src[i], 0, target[i], 0, src[i].size)
        }
        return target
    }

}
