package GameOfLife;

class Cell {
    private int neighbors = 0; // min 0

    private boolean isLife = false; // 0 - dead, 1 - life

    // the method that changes the indicator of life activity
    public void setLife(){
        isLife = !isLife;
    }

    // the method that increases/decreases the number of neighbors
    public void setNeighbors(Grid.NeighborsUpdate condition){
        if (condition == Grid.NeighborsUpdate.UP) neighbors++;
        else neighbors--;
    }

    // the method that returns the life indicator
    public boolean getLife(){
        return isLife;
    }

    // the method that returns the number of neighbors
    public int getNeighbors(){return neighbors;}
}
