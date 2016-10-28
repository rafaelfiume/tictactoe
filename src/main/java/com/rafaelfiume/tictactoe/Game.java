package com.rafaelfiume.tictactoe;


public interface Game {

    ////////////////////////
    //        Turn        //
    ////////////////////////

    Player currentPlayer();

    ////////////////////////
    //        Game        //
    ////////////////////////

    Game snapshot();

    boolean gameIsOn();

    boolean gameHasNotStarted();

    boolean hasWinner();

    boolean hasDraw();

    Player winner();

    ////////////////////////
    //        Board       //
    ////////////////////////

    void playerChooses(BoardPosition boardPosition);

    char topLeft();
    char topCenter();
    char topRight();
    char midLeft();
    char center();
    char midRight();
    char bottomLeft();
    char bottomCenter();
    char bottomRight();

}
