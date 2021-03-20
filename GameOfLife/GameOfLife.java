package GameOfLife;

import java.awt.*;
import java.security.InvalidParameterException;

public class GameOfLife {
    private final Grid GRID;

    public GameOfLife(float birthChance) {
        if (birthChance < 0 || birthChance > 1) {
            throw new InvalidParameterException("The value does not lie on the segment [0;1]");
        } else {
            this.GRID = new Grid(birthChance);
        }
    }

    public GameOfLife(Point[] cells) {
        if (cells == null || cells.length == 0) {
            throw new InvalidParameterException("The number of living cells cannot be null or equal to zero");
        } else {
            this.GRID = new Grid(cells);
        }
    }

    /**
     * Starts the game
     */
    public void go() {
        Display drawer = new Display();
        while (true) {
            drawer.takeCellsList(GRID.getAliveCells());
            drawer.repaint();
            GRID.update();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public static Point[] getGlider() {
        return new Point[] {
                new Point(10,12),
                new Point(11,13),
                new Point(11,14),
                new Point(12,12),
                new Point(12,13)
        };
    }

    public static Point[] getGospersGliderGun() {
        return new Point[]{
                new Point(35, 35),
                new Point(35, 36),
                new Point(36, 35),
                new Point(36,36),
                new Point(45,35),
                new Point(45,36),
                new Point(45,37),
                new Point(46,34),
                new Point(46,38),
                new Point(47,33),
                new Point(47,39),
                new Point(48,33),
                new Point(48,39),
                new Point(49,36),
                new Point(50,34),
                new Point(50,38),
                new Point(51,35),
                new Point(51,36),
                new Point(51,37),
                new Point(52,36),
                new Point(55,33),
                new Point(55,34),
                new Point(55,35),
                new Point(56,33),
                new Point(56,34),
                new Point(56,35),
                new Point(57,32),
                new Point(57,36),
                new Point(59, 31),
                new Point(59, 32),
                new Point(59, 36),
                new Point(59, 37),
                new Point(69, 33),
                new Point(69, 34),
                new Point(70, 33),
                new Point(70, 34),
        };
    }

}