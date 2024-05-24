package Logic.Game.Segments;

import Logic.Game.Ball.Ball;
import Logic.Game.Vector.Vector;


public abstract class Segment {

    private final Vector Vector1;
    private final Vector Vector2;

    public Segment(Vector Vector1, Vector Vector2) {
        this.Vector1 = Vector1;
        this.Vector2 = Vector2;
    }

    public abstract boolean act(Ball ball);

    public abstract boolean isVertical();

    public abstract boolean isHorizontal();

    public abstract boolean intersects(Ball ball);

    public Vector getVector1(){
        return Vector1;
    }

    protected Vector getVector2(){
        return Vector2;
    }

}
