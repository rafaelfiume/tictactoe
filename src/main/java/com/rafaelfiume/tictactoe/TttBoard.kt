package com.rafaelfiume.tictactoe

import com.rafaelfiume.tictactoe.BoardPosition.UNKNOWN
import java.lang.Character.isLetter

class TttBoard private constructor(private val grid: Array<CharArray>) : Board {

    constructor() : this(Array(3) { CharArray(3)}) // this(new char[3][3]);

    override fun markPositionIfAvailable(position: BoardPosition, player: Player): Boolean {
        if (position == UNKNOWN || cellIsMarkedAt(grid[position.row()][position.column()])) {
            return false
        }

        grid[position.row()][position.column()] = player.symbol()
        return true
    }

    override fun hasWinner() = hasVerticalWinner() || hasHorizontalWinner() || hasDiagonalWinner()

    override fun topLeft(): Char = grid[0][0]
    override fun topCenter(): Char = grid[0][1]
    override fun topRight(): Char = grid[0][2]
    override fun midLeft(): Char = grid[1][0]
    override fun center(): Char = grid[1][1]
    override fun midRight(): Char = grid[1][2]
    override fun bottomLeft(): Char = grid[2][0]
    override fun bottomCenter(): Char = grid[2][1]
    override fun bottomRight(): Char = grid[2][2]

    override fun snapshot() = TttBoard(cloneArray(grid))

    private fun hasVerticalWinner(): Boolean {
        (0..2).forEach { i ->
            if (cellIsMarkedAt(grid[0][i])
                    && grid[0][i] == grid[1][i]
                    && grid[1][i] == grid[2][i]) {
                return true
            }
        }
        return false
    }

    private fun hasHorizontalWinner(): Boolean {
        (0..2).forEach { i ->
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

    private fun cellIsMarkedAt(c: Char) = isLetter(c)

    private fun cloneArray(src: Array<CharArray>): Array<CharArray> {
        val length = src.size
        val target = Array(length) { CharArray(src[0].size) }
        for (i in 0..length - 1) {
            System.arraycopy(src[i], 0, target[i], 0, src[i].size)
        }
        return target
    }

}
