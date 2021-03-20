package game;

class Cell {
    private int neighborsNumber;
    private boolean isAlive; // 0 is dead, 1 is alive

    public Cell() {
        this.isAlive = false;
        this.neighborsNumber = 0;
    }

    /**
     * Change the indicator of life activity
     */
    public void setLifeStatus(){
        isAlive = !isAlive;
    }

    /**
     * Setter for
     * @param neighborsNumber the neighbors number
     */
    public void setNeighborsNumber(int neighborsNumber){
        this.neighborsNumber = neighborsNumber;
    }

    /**
     * Returns the life status
     * @return value of life status
     */
    public boolean isAlive() {
        return isAlive;
    }

    /**
     * Returns the number of neighbors
     * @return number of neighbors
     */
    public int getNeighborsNumber() {
        return neighborsNumber;
    }
}
