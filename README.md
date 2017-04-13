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

To play TicTacToe in Bot Mode, pass `botmode` as command-line argument.

