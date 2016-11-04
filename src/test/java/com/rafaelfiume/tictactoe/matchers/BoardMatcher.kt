package com.rafaelfiume.tictactoe.matchers

import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher
import java.lang.System.lineSeparator

open class BoardMatcher(private val expectedBoard: String) : TypeSafeMatcher<String>() {

    override fun matchesSafely(resultBoard: String): Boolean = (expectedBoard == resultBoard)

    override fun describeTo(description: Description) {
        description.appendText("a board:${lineSeparator()}$expectedBoard")
    }

    override fun describeMismatchSafely(actual: String, mismatchDescription: Description) {
        mismatchDescription.appendText("board was:${lineSeparator()}$actual")
    }

    companion object {

        @JvmStatic fun showsABoardLike(board: String): BoardMatcher {
            return BoardMatcher(board)
        }
    }

}
