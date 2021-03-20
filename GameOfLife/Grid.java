package GameOfLife;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class Grid {
    /** The width of the grid */
    public final int GRID_WIDTH;
    /** The height of the grid */
    public final int GRID_HEIGHT;
    /** The grid of cells */
    private final Cell[][] GRID;

    public Grid() {
        this.GRID_WIDTH = 100;
        this.GRID_HEIGHT = 100;
        this.GRID = new Cell[GRID_WIDTH][GRID_HEIGHT];
    }

    public Grid(Point[] cells) {
        this();
        cellsOfGridInitialization();
        createGrid(cells);
    }

    /**
     * @param birthChance - the chance with which life will arise in a cell when generating a grid
     */
    public Grid(float birthChance) {
        this();
        cellsOfGridInitialization();
        generateGrid(birthChance);
    }

    private void cellsOfGridInitialization(){
        for (int i = 0; i < GRID_WIDTH; i++) {
            for (int j = 0; j < GRID_HEIGHT; j++) {
                GRID[i][j] = new Cell();
            }
        }
    }

    /**
     * Created the user's grid
     */
    private void createGrid(Point[] cells) {
        for (Point cell : cells) {
            GRID[cell.x][cell.y].setLifeStatus();
            increaseNeighborsNumberForNeighbors(cell.x, cell.y);
        }
    }

    /**
     * Generates the initial grid
     * @param birthChance - the chance with which life will arise in a cell when generating a grid
     */
    private void generateGrid(float birthChance) {
        for (int i = 0; i < GRID_HEIGHT; i++) {
            for (int j = 0; j < GRID_WIDTH; j++) {
                if (Math.random() <= birthChance) {
                    GRID[i][j].setLifeStatus();
                    increaseNeighborsNumberForNeighbors(i, j);
                }
            }
        }
    }

    /**
     * Increase the number of neighbors other cells when this cell is changed
     * @param i - row number of current cell
     * @param j - column number of current cell
     */
    private void increaseNeighborsNumberForNeighbors(int i, int j) {
        int[] borders = getBorders(i,j);
        // Parameters: top, bottom, left, center, right
        increaseNeighborsNumberForHorizontalNeighbors(borders[2], borders[3], borders[0], j, borders[1]);
        // Parameters: center, left, right
        increaseNeighborsNumberForVerticalNeighbors(i, borders[0], borders[1]);
    }

    /**
     * Reduce the number of neighbors other cells when this cell is changed
     * @param i - row number of current cell
     * @param j - column number of current cell
     */
    private void reduceNeighborsNumberForNeighbors(int i, int j) {
        int[] borders = getBorders(i,j);
        // Parameters: top, bottom, left, center, right
        reduceNeighborsNumberForHorizontalNeighbors(borders[2], borders[3], borders[0], j, borders[1]);
        // Parameters: center, left, right
        reduceNeighborsNumberForVerticalNeighbors(i, borders[0], borders[1]);
    }

    /**
     * Returns borders of the current cell
     * @param i - row number of current cell
     * @param j - column number of current cell
     * @return array of borders
     */
    private int[] getBorders(int i, int j) {
        return new int[]{
                j - 1 >= 0 ? j - 1 : GRID_WIDTH - 1,  // Left border
                j + 1 != GRID_WIDTH ? j + 1 : 0,      // Right border
                i - 1 >= 0 ? i - 1 : GRID_HEIGHT - 1, // Top border
                i + 1 != GRID_HEIGHT ? i + 1 : 0      // Bottom border
        };
    }

    /**
     * Increase the number of neighbors for the upper and lower neighbors
     * [ln][tb][rn]
     * [--][cn][--]
     * [ln][bb][rn]
     * @param tb - number of top border
     * @param bb - number of bottom border
     * @param ln - left neighbor
     * @param cn - central neighbor
     * @param rn - right neighbor
     */
    private void increaseNeighborsNumberForHorizontalNeighbors(int tb, int bb, int ln, int cn, int rn) {
        // Top neighbors
        GRID[tb][ln].setNeighborsNumber(GRID[tb][ln].getNeighborsNumber() + 1);
        GRID[tb][cn].setNeighborsNumber(GRID[tb][cn].getNeighborsNumber() + 1);
        GRID[tb][rn].setNeighborsNumber(GRID[tb][rn].getNeighborsNumber() + 1);
        // Bottom neighbors
        GRID[bb][ln].setNeighborsNumber(GRID[bb][ln].getNeighborsNumber() + 1);
        GRID[bb][cn].setNeighborsNumber(GRID[bb][cn].getNeighborsNumber() + 1);
        GRID[bb][rn].setNeighborsNumber(GRID[bb][rn].getNeighborsNumber() + 1);
    }

    /**
     * Increase the number of neighbors for the left and right neighbors
     * [--][--][--]
     * [ln][cb][rn]
     * [--][--][--]
     * @param cb - number of row of current cell
     * @param ln - left neighbor
     * @param rn - right neighbor
     */
    private void increaseNeighborsNumberForVerticalNeighbors(int cb, int ln, int rn) {
        // Left neighbor
        GRID[cb][ln].setNeighborsNumber(GRID[cb][ln].getNeighborsNumber() + 1);
        // Right neighbor
        GRID[cb][rn].setNeighborsNumber(GRID[cb][rn].getNeighborsNumber() + 1);
    }

    /**
     * Reduce the number of neighbors for the upper and lower neighbors
     * [ln][tb][rn]
     * [--][cn][--]
     * [ln][bb][rn]
     * @param tb - number of top border
     * @param bb - number of bottom border
     * @param ln - left neighbor
     * @param cn - central neighbor
     * @param rn - right neighbor
     */
    private void reduceNeighborsNumberForHorizontalNeighbors(int tb, int bb, int ln, int cn, int rn) {
        // Top neighbors
        GRID[tb][ln].setNeighborsNumber(GRID[tb][ln].getNeighborsNumber() - 1);
        GRID[tb][cn].setNeighborsNumber(GRID[tb][cn].getNeighborsNumber() - 1);
        GRID[tb][rn].setNeighborsNumber(GRID[tb][rn].getNeighborsNumber() - 1);
        // Bottom neighbors
        GRID[bb][ln].setNeighborsNumber(GRID[bb][ln].getNeighborsNumber() - 1);
        GRID[bb][cn].setNeighborsNumber(GRID[bb][cn].getNeighborsNumber() - 1);
        GRID[bb][rn].setNeighborsNumber(GRID[bb][rn].getNeighborsNumber() - 1);
    }

    /**
     * Reduce the number of neighbors for the left and right neighbors
     * [--][--][--]
     * [ln][cb][rn]
     * [--][--][--]
     * @param cb - number of row of current cell
     * @param ln - left neighbor
     * @param rn - right neighbor
     */
    private void reduceNeighborsNumberForVerticalNeighbors(int cb, int ln, int rn) {
        // Left neighbor
        GRID[cb][ln].setNeighborsNumber(GRID[cb][ln].getNeighborsNumber() - 1);
        // Right neighbor
        GRID[cb][rn].setNeighborsNumber(GRID[cb][rn].getNeighborsNumber() - 1);
    }

    /**
     * Returns a list of alive cells
     * @return alive cells
     */
    public List<Point> getAliveCells() {
        List<Point> aliveCell = new ArrayList<>();
        for (int i = 0; i < GRID_HEIGHT; i++) {
            for (int j = 0; j < GRID_WIDTH; j++) {
                if (GRID[i][j].isAlive()) {
                    aliveCell.add(new Point(i, j));
                }
            }
        }
        return aliveCell;
    }

    /**
     * The method that updates the state of the cells
     */
    public void update() {
        List<Point> changedCells = getCellsForChanging();
        for (Point changedCell : changedCells) {
            GRID[changedCell.x][changedCell.y].setLifeStatus();
            if (GRID[changedCell.x][changedCell.y].isAlive()) {
                increaseNeighborsNumberForNeighbors(changedCell.x, changedCell.y);
            } else {
                reduceNeighborsNumberForNeighbors(changedCell.x, changedCell.y);
            }
        }
    }

    /**
     * Returns a list of cells to change
     * @return cells for changing
     */
    private List<Point> getCellsForChanging() {
        List<Point> cellsForChanging = new ArrayList<>();
        for (int i = 0; i < GRID_HEIGHT; i++) {
            for (int j = 0; j < GRID_WIDTH; j++) {
                if (GRID[i][j].isAlive()) {
                    if (GRID[i][j].getNeighborsNumber() < 2 || GRID[i][j].getNeighborsNumber() > 3) {
                        cellsForChanging.add(new Point(i, j)); // Death from 'loneliness' or 'overpopulation'
                    }
                } else {
                    if (GRID[i][j].getNeighborsNumber() == 3) {
                        cellsForChanging.add(new Point(i, j)); // Nucleation of a new cell
                    }
                }
            }
        }
        return cellsForChanging;
    }

}