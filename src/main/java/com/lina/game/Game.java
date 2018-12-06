package com.lina.game;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Game {

    @Nullable
    CellWithValue getCell(int x, int y);

    boolean hasWon();

    boolean hasLost();

    void processMove(@NotNull Direction direction);

}
