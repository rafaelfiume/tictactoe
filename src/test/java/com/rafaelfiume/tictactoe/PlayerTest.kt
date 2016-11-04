package com.rafaelfiume.tictactoe

import org.hamcrest.Matchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test

class PlayerTest {

    @Test
    fun playersReturnTheSymbolThatIdentifiesThem() {
        assertThat(Player.X.symbol(), `is`('X'))
        assertThat(Player.O.symbol(), `is`('O'))
    }

}
