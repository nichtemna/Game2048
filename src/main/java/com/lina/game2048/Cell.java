package com.lina.game2048;


import com.lina.game.CellWithValue;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

public class Cell implements CellWithValue {

    private final int x;

    private final int y;

    private int value;

    Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    @Nullable
    public String getStringValue() {
        return value == 0 ? null : String.valueOf(value);
    }

    @Override
    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public Color getBackgroundColor() {
        switch (value) {
            case 2:
                return new Color(0xeee4da);
            case 4:
                return new Color(0xede0c8);
            case 8:
                return new Color(0xf2b179);
            case 16:
                return new Color(0xf59563);
            case 32:
                return new Color(0xf67c5f);
            case 64:
                return new Color(0xf65e3b);
            case 128:
                return new Color(0xedcf72);
            case 256:
                return new Color(0xedcc61);
            case 512:
                return new Color(0xedc850);
            case 1024:
                return new Color(0xedc53f);
            case 2048:
                return new Color(0xedc22e);
            default:
                return new Color(0xcdc1b4);
        }
    }

    @Override
    public Color getFontColor() {
        return value < 16 ? new Color(0x776e65) : new Color(0xf9f6f2);
    }

    @Override
    public int getFontSize() {
        return value < 100 ? 36 : value < 1000 ? 32 : 24;
    }

}

