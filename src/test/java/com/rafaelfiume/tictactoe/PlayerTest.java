package com.rafaelfiume.tictactoe;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class PlayerTest {

    @Test
    public void playersReturnTheSymbolThatIdentifiesThem() {
        assertThat(Player.X.symbol(), is('X'));
        assertThat(Player.O.symbol(), is('O'));
    }

}
