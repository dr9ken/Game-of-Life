package GameOfLife;

import java.security.InvalidParameterException;

public class GameOfLife {
    // the method that starts the game with grid generation
    public void go(float birthChance){
        if(birthChance < 0 || birthChance >1)
            throw new InvalidParameterException("The value does not lie on the segment [0;1]");

        Grid.generateGrid(birthChance);

        lifeCycle();
    }

    // the method that starts the game with the user's grid
    public void go(Point[] cells){
        Grid.createGrid(cells);

        lifeCycle();
    }

    // the method that repeats the life cycle of cells
    private void lifeCycle() {
        Display drawer = new Display();

        while (true) {
            drawer.takeCellsList(Grid.getLivingCells());

            drawer.repaint();

            Grid.update(Grid.getChangeCells());

            try {
                Thread.sleep(500);
            } catch (Exception exception){}
        }
    }
}