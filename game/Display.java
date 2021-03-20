package game;

import javax.swing.*;
import java.awt.*;
import java.util.List;

class Display extends JFrame {
    /** The list of cells alive */
    private List<Point> cellList;
    /** The width of the window */
    private final int SCREEN_WIDTH = 500;
    /** The height of the window */
    private final int SCREEN_HEIGHT = 500;

    /**
     * Initialize the frame
     */
    public Display() {
        super("Game of Life");
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        setLocation(
                (screenSize.width / 2) - (SCREEN_WIDTH / 2),
                (screenSize.height / 2) - (SCREEN_HEIGHT / 2)
        );
        setResizable(false);
        setUndecorated(true);
        setVisible(true);
    }

    /**
     * Taking new alive cells
     * @param cellsList - new list of alive cells
     */
    public void takeCellsList(List<Point> cellsList){
        this.cellList = cellsList;
    }

    // the method that draws cells
    @Override
    public void repaint() {
        Graphics2D graphics = (Graphics2D) this.getGraphics();
        graphics.setPaint(Color.BLACK);
        graphics.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        repaintAliveCells(graphics);
    }

    private void repaintAliveCells(Graphics2D graphics) {
        graphics.setPaint(Color.WHITE);
        for (Point cell : cellList) {
            int cellSize = 5;
            graphics.fillRect(cell.x * cellSize, cell.y * cellSize, cellSize, cellSize);
        }
    }
}