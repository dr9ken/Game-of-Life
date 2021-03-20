package GameOfLife;

public class Main {
    public static void main(String[] args) {
        gameWithGospersGliderGun();
        //gameWithGliderCells();
        //gameWithGenerateCells();
    }

    private static void gameWithGenerateCells() {
        GameOfLife game = new GameOfLife(0.15f);
        game.go();
    }

    private static void gameWithGliderCells() {
        GameOfLife game = new GameOfLife(GameOfLife.getGlider());
        game.go();
    }

    private static void gameWithGospersGliderGun() {
        GameOfLife game = new GameOfLife(GameOfLife.getGospersGliderGun());
        game.go();
    }
}