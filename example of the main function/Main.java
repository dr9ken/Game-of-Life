package com.company;

import GameOfLife.*;

public class Main {

    public static void main(String[] args) {
        GameOfLife game = new GameOfLife();

        Point[] myCells = new Point[10];

        game.go(0.15f);

        /*myCells[0] = new Point(10,12);
        myCells[1] = new Point(11,13);
        myCells[2] = new Point(11,14);
        myCells[3] = new Point(12,12);
        myCells[4] = new Point(12,13);

        myCells[5] = new Point(50,92);
        myCells[6] = new Point(51,93);
        myCells[7] = new Point(51,94);
        myCells[8] = new Point(52,92);
        myCells[9] = new Point(52,93);

        game.go(myCells);*/
    }

}