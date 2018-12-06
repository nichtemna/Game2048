package com.lina.game;

import java.awt.*;

public interface CellWithValue {

    int getX();

    int getY();

    int getValue();

    String getStringValue();

    void setValue(int value);

    Color getBackgroundColor();

    Color getFontColor();

    int getFontSize();

}
