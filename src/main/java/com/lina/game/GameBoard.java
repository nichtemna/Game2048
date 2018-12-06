package com.lina.game;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface GameBoard {

    @Nullable
    CellWithValue getCellOrNull(int x, int y);

    @NotNull
    List<CellWithValue> getAllCells();

    boolean moveCells(@NotNull Direction direction);

    void addNewValue();

}
