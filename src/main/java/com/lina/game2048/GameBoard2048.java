package com.lina.game2048;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.lina.game.CellWithValue;
import com.lina.game.Direction;
import com.lina.game.GameBoard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GameBoard2048 implements GameBoard {

    private final CellWithValue[][] cells = new CellWithValue[4][4];

    GameBoard2048() {
        initEmptyCells();

        addNewValue();
        addNewValue();
    }

    @Override
    @Nullable
    public CellWithValue getCellOrNull(int x, int y) {
        if (x > getWidth() - 1 || y > getWidth() - 1) {
            return null;
        }

        return cells[x][y];
    }

    @Override
    @NotNull
    public List<CellWithValue> getAllCells() {
        List<CellWithValue> allCells = new ArrayList<>();

        for (CellWithValue[] cell : cells) {
            allCells.addAll(Arrays.asList(cell).subList(0, cells.length));
        }

        return allCells;
    }

    @Override
    public void addNewValue() {
        List<CellWithValue> allEmptyCells = getAllEmptyCells();

        CellWithValue cell = allEmptyCells.get(new Random().nextInt(allEmptyCells.size()));

        cell.setValue(getRandomNumber());
    }

    @Override
    public boolean moveCells(@NotNull Direction direction) {
        if (getAllEmptyCells().size() == 0) {
            return false;
        }

        boolean hasMoved = false;

        for (int i = 0; i < cells.length; i++) {
            boolean moved = moveCellsInRowOrColumn(i, direction);

            if (!hasMoved) {
                hasMoved = moved;
            }
        }

        return hasMoved;
    }

    private void initEmptyCells() {
        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
    }

    private boolean moveCellsInRowOrColumn(int i, @NotNull Direction direction) {
        List<CellWithValue> rowOrColumn = getRowOrColumn(i, direction);

        boolean someCellsRemoved = removeEmptyCells(rowOrColumn);
        boolean someCellsMerged = mergeCells(rowOrColumn);

        //Once again remove empty cells left after merge.
        removeEmptyCells(rowOrColumn);

        return someCellsRemoved || someCellsMerged;
    }

    private boolean removeEmptyCells(@NotNull List<CellWithValue> rowOrColumn) {
        List<Integer> resultValues = new ArrayList<>();
        boolean someEmptyCellsWereRemoved = false;

        for (CellWithValue cell : rowOrColumn) {
            int value = cell.getValue();

            if (value != 0) {
                resultValues.add(value);
            }
        }

        for (int i = 0; i < rowOrColumn.size(); i++) {
            int value = i < resultValues.size() ? resultValues.get(i) : 0;

            if (value != rowOrColumn.get(i).getValue()) {
                someEmptyCellsWereRemoved = true;
            }

            rowOrColumn.get(i).setValue(value);
        }

        return someEmptyCellsWereRemoved;
    }

    private boolean mergeCells(@NotNull List<CellWithValue> rowOrColumn) {
        boolean hasMerged = false;

        for (int i = 0; i < rowOrColumn.size() - 1; i++) {
            CellWithValue cell = rowOrColumn.get(i);
            CellWithValue nextCell = rowOrColumn.get(i + 1);

            int value = cell.getValue();

            if (value == nextCell.getValue() && value != 0) {
                cell.setValue(value * 2);
                nextCell.setValue(0);

                hasMerged = true;
            }
        }

        return hasMerged;
    }

    @NotNull
    private List<CellWithValue> getRowOrColumn(int i, @NotNull Direction direction) {
        switch (direction) {
            case UP:
                return getColumnUp(i);
            case DOWN:
                return getColumnDown(i);
            case LEFT:
                return getRowLeft(i);
            default:
                return getRowRight(i);
        }
    }

    private List<CellWithValue> getRowLeft(int y) {
        List<CellWithValue> row = new ArrayList<>();

        for (int x = 0; x < getWidth(); x++) {
            CellWithValue cell = getCellOrNull(x, y);

            if (cell != null) {
                row.add(cells[x][y]);
            }
        }

        return row;
    }

    @NotNull
    private List<CellWithValue> getRowRight(int y) {
        List<CellWithValue> row = new ArrayList<>();

        for (int x = getWidth() - 1; x >= 0; x--) {
            CellWithValue cell = getCellOrNull(x, y);

            if (cell != null) {
                row.add(cells[x][y]);
            }
        }

        return row;
    }

    @NotNull
    private List<CellWithValue> getColumnUp(int x) {
        List<CellWithValue> column = new ArrayList<>();

        for (int y = 0; y < getWidth(); y++) {
            CellWithValue cell = getCellOrNull(x, y);

            if (cell != null) {
                column.add(cells[x][y]);
            }
        }

        return column;
    }

    @NotNull
    private List<CellWithValue> getColumnDown(int x) {
        List<CellWithValue> column = new ArrayList<>();

        for (int y = getWidth(); y >= 0; y--) {
            CellWithValue cell = getCellOrNull(x, y);

            if (cell != null) {
                column.add(cells[x][y]);
            }
        }

        return column;
    }

    @NotNull
    private List<CellWithValue> getAllEmptyCells() {
        List<CellWithValue> emptyCells = new ArrayList<>();

        for (CellWithValue cell : getAllCells()) {
            if (cell.getValue() == 0) {
                emptyCells.add(cell);
            }
        }

        return emptyCells;
    }

    private int getRandomNumber() {
        return new Random().nextInt(10) == 9 ? 4 : 2;
    }

    private int getWidth() {
        return cells.length;
    }

}
