package com.rafaelfiume.tictactoe;

import com.googlecode.yatspec.junit.Notes;
import com.googlecode.yatspec.junit.Row;
import com.googlecode.yatspec.junit.SpecRunner;
import com.googlecode.yatspec.junit.Table;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.rafaelfiume.tictactoe.BoardPosition.UNKNOWN;
import static java.lang.Integer.parseInt;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpecRunner.class)
@Notes("Board position is similar to a telephone dial:\n" +
        "" +
        " 1 | 2 | 3 \n" +
        "---+---+---\n" +
        " 4 | 5 | 6 \n" +
        "---+---+---\n" +
        " 7 | 8 | 9 \n"
)
public class BoardPositionTest {

    @Table({
            @Row({"1", "TOP_LEFT"}),
            @Row({"2", "TOP_CENTER"}),
            @Row({"3", "TOP_RIGHT"}),
            @Row({"4", "MID_LEFT"}),
            @Row({"5", "CENTER"}),
            @Row({"6", "MID_RIGHT"}),
            @Row({"7", "BOTTOM_LEFT"}),
            @Row({"8", "BOTTOM_CENTER"}),
            @Row({"9", "BOTTOM_RIGHT"})
    })
    @Test
    public void mapUserInputToCorrectBoardPosition(String number, String boardPosition) {
        assertThat(BoardPosition.Companion.of(number(number)), is(the(boardPosition)));
    }

    @Test
    public void throwsExceptionWhenPlayersInputIsUnknown() {
        assertThat(BoardPosition.Companion.of(11), is(UNKNOWN));
    }

    private BoardPosition the(String boardPosition) {
        return BoardPosition.valueOf(boardPosition);
    }

    private int number(String number) {
        return parseInt(number);
    }

}
