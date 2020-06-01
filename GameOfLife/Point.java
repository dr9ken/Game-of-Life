package GameOfLife;

import java.security.InvalidParameterException;

public class Point {
    public final int I; // i-coordinate of cell
    public final int J; // j-coordinate of cell

    public Point(int i, int j) {
        if (i < 0 || i > 99)
            throw new InvalidParameterException("The i-value does not lie on the segment [0;99]");

        if (j < 0 || j > 99)
            throw new InvalidParameterException("The j-value does not lie on the segment [0;99]");

        this.I = i;
        this.J = j;
    }
}
