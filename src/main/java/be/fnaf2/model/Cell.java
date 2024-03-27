package be.fnaf2.model;

public class Cell {
    private boolean isHit;
    private boolean hasShip;

    public Cell() {
        this.isHit = false;
        this.hasShip = false;
    }

    public boolean isHit() {
        return isHit;
    }

    public void setHit(boolean hit) {
        isHit = hit;
    }

    public boolean hasShip() {
        return hasShip;
    }

    public void setShip(boolean hasShip) {
        this.hasShip = hasShip;
    }

    public void updateState() {
        // Update the state of the cell
        this.isHit = true;
    }

    public boolean containsShip() {
        return hasShip;
    }
}