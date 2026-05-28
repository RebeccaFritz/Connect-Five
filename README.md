# Connect Five

A Java game-playing algorithm for Connect Five, developed as a final project for the Discrete Structures I at the University of Dallas. **Placed 1st in a class-wide bracketed tournament.**

## Overview

Connect Five is a two-player strategy game played on a 20×20 board. Players alternate placing pieces, and the goal varies by game mode:

- **Short Game** — first player to get five pieces in a row (horizontal, vertical, or diagonal) wins.
- **Long Game** — play continues until the board is full; the winner is whoever forms the most sets of five.

## How It Works

The algorithm uses a priority-based decision tree to select moves each turn. At a high level, it:

1. Takes any immediate win
2. Blocks any immediate opponent win
3. Builds toward unblockable threats (e.g., open-ended runs of 3 or 4)
4. Disrupts opponent threats before they become dangerous
5. Falls back to strategic positioning (open center, intersecting lines)

The long game extends this logic to longer sequences — detecting and building runs of 9-in-10, 13-in-15, and 18-in-20 in addition to standard 4-in-5 patterns.

## File Structure

| File | Description |
|---|---|
| `ConnectFivePlayer.java` | The algorithm — implements the `ConnectFiveInterface` interface |
| `ConnectFiveInterface.java` | Interface provided by the course instructors (Hochberg & Chastain) |

## Interface Methods

**`playShortGame(char[][] board, int player)`** — Returns the algorithm's chosen move for a short game.

**`playLongGame(char[][] board, int player)`** — Returns the algorithm's chosen move for a long game.

**`isShortGameOver(char[][] board)`** — Returns `1` (X wins), `2` (O wins), or `0` (no winner yet / draw).

**`isLongGameOver(char[][] board)`** — Returns `1` (X wins), `2` (O wins), or `0` (board not yet full).
