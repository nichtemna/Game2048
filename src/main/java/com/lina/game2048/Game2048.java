package com.lina.game2048;

import com.lina.game.CellWithValue;
import com.lina.game.Direction;
import com.lina.game.Game;
import com.lina.game.GameBoard;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Game2048 implements Game {

    @NotNull
    private final GameBoard gameBoard;

    public Game2048() {
        gameBoard = new GameBoard2048();
    }

    @Override
    @Nullable
    public CellWithValue getCell(int x, int y) {
        return gameBoard.getCellOrNull(x, y);
    }

    @Override
    public boolean hasWon() {
        for (CellWithValue cell : gameBoard.getAllCells()) {
            if (cell.getValue() == 2048) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean hasLost() {
        for (CellWithValue cell : gameBoard.getAllCells()) {
            if (cell.getValue() == 0) {
                return false;
            }
        }

        return true;
    }

    @Override
    public void processMove(@NotNull Direction direction) {
        if (gameBoard.moveCells(direction)) {
            gameBoard.addNewValue();
        }
    }

}
