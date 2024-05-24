package Logic.Game.Vector;

public class Vector {
    private int x;
    private int y;

    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double getTan() {
        return (double)x/y;
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

    public static Vector sum(Vector Vector1, Vector Vector2){
        return new Vector(Vector1.getX() + Vector2.getX(), Vector1.getY() + Vector2.getY());
    }
}
