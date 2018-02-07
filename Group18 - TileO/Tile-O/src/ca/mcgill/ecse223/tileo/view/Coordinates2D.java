package ca.mcgill.ecse223.tileo.view;

/**
 * Created by Alex on 2017-03-13.
 */
public class Coordinates2D {
    private int x;
    private int y;

    public Coordinates2D(int x, int y) {
        this.x = x;
        this.y = y;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinates2D)) return false;

        Coordinates2D that = (Coordinates2D) o;

        if (getX() != that.getX()) return false;
        return getY() == that.getY();
    }

    @Override
    public int hashCode() {
        int result = getX();
        result = 31 * result + getY();
        return result;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


}
