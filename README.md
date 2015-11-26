# Tic-Tac-Toe [![Build Status](https://travis-ci.org/rafaelfiume/tictactoe.svg?branch=master)](https://travis-ci.org/rafaelfiume/tictactoe)

To mark a position in the grid, imagine a telephone dial:

    1 | 2 | 3
    ---+---+---
    4 | 5 | 6
    ---+---+---
    7 | 8 | 9

## Running the application locally

Build with:

    $mvn clean install

Run with ConsoleGameRunner class.

## BOT mode

If you want the TicTacToe to run in Bot Mode: pass "botmode" in the first program argument.

## TODO List - or Stories Being Played ;)

### Playing TicTacToe using a terminal (Parent Story)
* ~~Board creation~~
* ~~Player X wins with vertical line~~
* ~~Player O wins with horizontal line~~
* ~~Player X wins with diagonal line~~
* ~~Game ends with a draw~~
* ~~Cross platform game (now working on Windows as well)~~
* ~~Play the game in BOT mode~~
* ~~Validate player input (player selects an already marked cell in the board)~~
* ~~Validate player input (player selects unknown position)~~

### Playing TicTacToe using a GUI (Parent Story)
* Implement GUI

### Implementing AI
* Play against computer AI
* Play online against remote opponent

### Non-functional Story
* App runs with a double click
