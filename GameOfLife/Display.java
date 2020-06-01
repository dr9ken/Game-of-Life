package GameOfLife;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.util.List;
import javax.swing.JFrame;

class Display extends JFrame {
    // the width and height of cells
    private final int CELL_WIDTH = 5;
    private final int CELL_HEIGHT = 5;

    // the width and height of the window
    private final int SCREEN_WIDTH = 500;
    private final int SCREEN_HEIGHT = 500;

    // the list of cells to draw
    List<Point> cellList;

    // method that initializes the frame
    public Display() {
        super("Game of Life");

        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        setLocation((screenSize.width/2) - (SCREEN_WIDTH / 2),
                (screenSize.height/2) - (SCREEN_HEIGHT / 2));

        setResizable(false);
        setUndecorated(true);
        setVisible(true);
    }

    // the method that gets a list of cells to draw
    public void takeCellsList(List<Point> cellsList){
        this.cellList = cellsList;
        cellsList.toString();
    }

    // the method that draws cells
    @Override
    public void repaint() {
        Graphics2D gridGraphics = (Graphics2D) this.getGraphics();

        gridGraphics.setPaint(Color.BLACK);

        gridGraphics.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

        gridGraphics.setPaint(Color.WHITE);
        for (Point cell : cellList) {
            gridGraphics.fillRect(cell.I * CELL_HEIGHT, cell.J * CELL_WIDTH, CELL_WIDTH, CELL_HEIGHT);
        }
    }
}