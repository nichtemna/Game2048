package com.lina.run;
import com.lina.game.CellWithValue;
import com.lina.game.Direction;
import com.lina.game.Game;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BoardPanel extends JPanel {

    private static final int BOARD_SIZE = 450;
    private static final int TILE_SIZE = 100;
    private static final int TILE_MARGIN = 10;
    private static final int ARC_RADIUS = 5;

    private static final String FONT = "Arial";

    private static final Color BG_COLOR = new Color(187, 173, 160);
    private static final Color GAME_END_BG_COLOR = new Color(255, 255, 255, 30);
    private static final Color GAME_END_TEXT_COLOR = new Color(78, 139, 202);

    @NotNull
    private final Game game;

    BoardPanel(@NotNull Game game) {
        this.game = game;

        initialize(game);
    }

    private void initialize(@NotNull Game game) {
        setSize(BOARD_SIZE, BOARD_SIZE);
        setFocusable(true);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(@NotNull KeyEvent keyEvent) {
                super.keyPressed(keyEvent);

                if (game.hasWon() || game.hasLost()) {
                    return;
                }

                Direction direction = Direction.getDirectionFromKeyEvent(keyEvent);

                if (direction != null) {
                    game.processMove(direction);
                    repaint();
                }
            }
        });
    }

    @Override
    public void paint(@NotNull Graphics graphics) {
        super.paint(graphics);

        graphics.setColor(BG_COLOR);
        graphics.fillRect(0, 0, BOARD_SIZE, BOARD_SIZE);

        drawTiles((Graphics2D) graphics);

        if (game.hasWon()) {
            drawGameMessage("You won!!!", graphics);
        } else if (game.hasLost()) {
            drawGameMessage("Game over!", graphics);
        }
    }

    private void drawTiles(@NotNull Graphics2D graphics) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                CellWithValue cell = game.getCell(i, j);

                if (cell != null) {
                    drawTile(graphics, cell);
                }
            }
        }
    }

    private void drawTile(@NotNull Graphics2D graphics, @Nullable CellWithValue cell) {
        drawTileBackground(graphics, cell);
        drawTileTitle(graphics, cell);
    }

    private void drawTileBackground(@NotNull Graphics2D graphics, @Nullable CellWithValue cell) {
        int x = getTileCoordinate(cell.getX());
        int y = getTileCoordinate(cell.getY());

        graphics.setColor(cell.getBackgroundColor());
        graphics.fillRoundRect(x, y, TILE_SIZE, TILE_SIZE, ARC_RADIUS, ARC_RADIUS);
    }

    private void drawTileTitle(@NotNull Graphics2D graphics, @NotNull CellWithValue cell) {
        String stringValue = cell.getStringValue();

        if (stringValue == null) {
            return;
        }

        Font font = new Font(FONT, Font.BOLD, cell.getFontSize());

        int x = getTextXCoordinate(cell.getX(), font, stringValue);
        int y = getTextYCoordinate(cell.getY(), graphics, font, stringValue);

        graphics.setColor(cell.getFontColor());
        graphics.setFont(font);
        graphics.drawString(stringValue, x, y);
    }

    private int getTileCoordinate(int x) {
        return x * TILE_SIZE + (x + 1) * TILE_MARGIN;
    }

    private int getTextXCoordinate(int x, Font font, @NotNull String string) {
        FontMetrics fm = getFontMetrics(font);
        int width = fm.stringWidth(string);

        return getTileCoordinate(x) + (TILE_SIZE - width) / 2;
    }

    private int getTextYCoordinate(int y, @NotNull Graphics2D graphics, @NotNull Font font, @NotNull String string) {
        FontMetrics fm = getFontMetrics(font);
        int height = (int) -fm.getLineMetrics(string, graphics).getBaselineOffsets()[2];

        return getTileCoordinate(y) + (TILE_SIZE + height) / 2;
    }

    private void drawGameMessage(@NotNull String message, @NotNull Graphics graphics) {
        graphics.setColor(GAME_END_BG_COLOR);
        graphics.fillRect(0, 0, getWidth(), getHeight());

        graphics.setColor(GAME_END_TEXT_COLOR);
        graphics.setFont(new Font(FONT, Font.BOLD, 48));

        graphics.drawString(message, 100, 250);
    }

}
