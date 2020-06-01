package GameOfLife;

import java.util.ArrayList;
import java.util.List;

class Grid {
    enum NeighborsUpdate {
        UP,
        DOWN
    }

    public static final int GRID_WIDTH = 100; // the width of grid
    public static final int GRID_HEIGHT = 100; // the height of grid
    private static Cell[][] grid = new Cell[GRID_WIDTH][GRID_HEIGHT]; // the grid of cells

    // the method that generates the initial grid of cells
    public static void generateGrid(float birthChance) {
        createCells();

        for (int i = 0; i < GRID_HEIGHT; i++) {
            for (int j = 0; j < GRID_WIDTH; j++) {
                if (Math.random() <= birthChance) {
                    grid[i][j].setLife();
                    neighborsUpdate(i, j, NeighborsUpdate.UP);
                }
            }
        }
    }

    // the method that creates the user's grid
    public static void createGrid(Point[] cells) {
        createCells();

        for(Point cell : cells) {
            grid[cell.I][cell.J].setLife();
            neighborsUpdate(cell.I, cell.J, NeighborsUpdate.UP);
        }
    }

    // the method that returns a list of cells to change
    public static List<Point> getChangeCells() {
        List<Point> changeCellList = new ArrayList<Point>();

        for (int i = 0; i < GRID_HEIGHT; i++) {
            for (int j = 0; j < GRID_WIDTH; j++) {
                if (grid[i][j].getLife()) {
                    if (grid[i][j].getNeighbors() < 2 || grid[i][j].getNeighbors() > 3) {
                        Point changeCell = new Point(i, j);
                        changeCellList.add(changeCell);
                    }
                } else {
                    if (grid[i][j].getNeighbors() == 3) {
                        Point changeCell = new Point(i, j);
                        changeCellList.add(changeCell);
                    }
                }
            }
        }
        return changeCellList;
    }

    // the method that returns a list of live cells
    public static List<Point> getLivingCells(){
        List<Point> livingCellList = new ArrayList<Point>();

        for (int i = 0; i < GRID_HEIGHT; i++) {
            for (int j = 0; j < GRID_WIDTH; j++) {
                if (grid[i][j].getLife()) {
                    Point livingCell = new Point(i, j);
                    livingCellList.add(livingCell);
                }
            }
        }

        return livingCellList;
    }

    // the method that updates the state of the cells
    public static void update(List<Point> changeCellList){
        for(Point changeCell : changeCellList) {
            grid[changeCell.I][changeCell.J].setLife();

            if (grid[changeCell.I][changeCell.J].getLife())
                neighborsUpdate(changeCell.I, changeCell.J, NeighborsUpdate.UP);
            else
                neighborsUpdate(changeCell.I, changeCell.J, NeighborsUpdate.DOWN);
        }
    }

    // the method that allocates memory for cells
    private static void createCells(){
        for (int i = 0; i < GRID_WIDTH; i++)
            for (int j = 0; j < GRID_HEIGHT; j++)
                grid[i][j] = new Cell();
    }

    // the method that updates the number of neighbors other cells when this cell is changed
    private static void neighborsUpdate(int i, int j, NeighborsUpdate condition) {
        Integer[] upBorder = new Integer[]{0}, downBorder = new Integer[]{0},
                leftBorder = new Integer[]{0}, rightBorder = new Integer[]{0};

        delimitation(i, j, upBorder, downBorder, leftBorder, rightBorder);

        setHorizontalBorder(j, upBorder[0], leftBorder[0], rightBorder[0], condition);
        setHorizontalBorder(j, downBorder[0], leftBorder[0], rightBorder[0], condition);

        setVerticalBorder(i, leftBorder[0], condition);
        setVerticalBorder(i, rightBorder[0], condition);
    }

    // the method of calculating the cell boundary
    private static void delimitation(int i, int j, Integer[] upBorder, Integer[] downBorder,
                                     Integer[] leftBorder, Integer[] rightBorder) {
        if (i - 1 >= 0) upBorder[0] = i - 1;
        else upBorder[0] = GRID_HEIGHT - 1;

        if (i + 1 != GRID_HEIGHT) downBorder[0] = i + 1;
        else downBorder[0] = 0;

        if (j - 1 >= 0) leftBorder[0] = j - 1;
        else leftBorder[0] = GRID_WIDTH - 1;

        if (j + 1 != GRID_WIDTH) rightBorder[0] = j + 1;
        else rightBorder[0] = 0;
    }

    // the method that changes the number of neighbors in the upper/lower neighbors
    private static void setHorizontalBorder(int middle, int horizontalBorder, int leftBorder,
                                            int rightBorder, NeighborsUpdate condition) {
        grid[horizontalBorder][leftBorder].setNeighbors(condition);
        grid[horizontalBorder][rightBorder].setNeighbors(condition);
        grid[horizontalBorder][middle].setNeighbors(condition);
    }

    // the method that changes the number of neighbors in the side neighbors
    private static void setVerticalBorder(int horizontalLine, int verticalLine,
                                          NeighborsUpdate condition) {
        grid[horizontalLine][verticalLine].setNeighbors(condition);
    }
}