# Tic-Tac-Toe [![Build Status](https://travis-ci.org/rafaelfiume/tictactoe.svg?branch=master)](https://travis-ci.org/rafaelfiume/tictactoe) [![Apache 2.0 License](https://img.shields.io/badge/license-MIT-blue.svg)](https://github.com/rafaelfiume/tictactoe/blob/master/LICENSE)

A simple Tic-Tac-Toe console game implementation demonstrating design and TDD techniques.

* An Erlang version is available [here](https://github.com/rafaelfiume/tictactoerl).

The project specification is available [here](http://rafaelfiume.github.io/tictactoe), generated from the source code of the test classes
in the [acceptance](https://github.com/rafaelfiume/tictactoe/tree/master/src/test/java/com/rafaelfiume/tictactoe/console/acceptance) package, and automatically updated when pushing code into master.

## How to Play It

To mark a position in the grid, imagine a telephone dial:

    1 | 2 | 3
    ---+---+---
    4 | 5 | 6
    ---+---+---
    7 | 8 | 9

## Running the Application Locally

Build with:

    $mvn clean install

Run with ConsoleGameRunner class.

### BOT Mode

If you want the TicTacToe to run in Bot Mode: pass "botmode" in the first program argument.

## TODO List - or Stories Being Played ;)

### ~~Playing TicTacToe using a terminal (Parent Story)~~
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
