package com.rafaelfiume.tictactoe

import org.junit.Test

import org.hamcrest.Matchers.`is`
import org.junit.Assert.assertThat

class PlayerTest {

    @Test
    fun playersReturnTheSymbolThatIdentifiesThem() {
        assertThat(Player.X.symbol(), `is`('X'))
        assertThat(Player.O.symbol(), `is`('O'))
    }

}
