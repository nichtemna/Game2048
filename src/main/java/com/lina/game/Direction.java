package com.lina.game;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.event.KeyEvent;

public enum Direction {

    UP,
    DOWN,
    RIGHT,
    LEFT;

    @Nullable
    public static Direction getDirectionFromKeyEvent(@NotNull KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                return LEFT;
            case KeyEvent.VK_RIGHT:
                return RIGHT;
            case KeyEvent.VK_DOWN:
                return DOWN;
            case KeyEvent.VK_UP:
                return UP;
            default:
                return null;
        }
    }

}


