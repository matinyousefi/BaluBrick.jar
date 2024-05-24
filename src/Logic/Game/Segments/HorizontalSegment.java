package Logic.Game.Segments;

import Logic.Game.Ball.Ball;
import Logic.Game.Vector.Vector;

public class HorizontalSegment extends Segment{

    public HorizontalSegment(Vector Vector1, Vector Vector2) {
        super(Vector1, Vector2);
        if(Vector1.getY()!= Vector1.getY()) System.out.println("Ys do not match.");;
    }

    @Override
    public boolean act(Ball ball) {
        if(intersects(ball)){
            ball.getSpeed().setY(-ball.getSpeed().getY());
            return true;
        }
        return false;
    }

    @Override
    public boolean isVertical() {
        return false;
    }

    @Override
    public boolean isHorizontal() {
        return true;
    }

    @Override
    public boolean intersects(Ball ball) {
        if (ball.getCenter().getY() == ball.nextCenter().getY()) return false;
        if (
                (ball.getCenter().getY() + ball.getSpeedYSign() * ball.getRadius() - this.getVector1().getY()) *
                        (ball.nextCenter().getY() + ball.getSpeedYSign() * ball.getRadius() - this.getVector1().getY()) > 0
        ) return false;
        double coefficient = (double) (ball.getCenter().getX() - ball.nextCenter().getX())
                / (ball.getCenter().getY() - ball.nextCenter().getY());
        int xOfIntersection = (int) ((this.getVector1().getY()-ball.getCenter().getY()) * coefficient + ball.getCenter().getX());
        return
                xOfIntersection < Math.max(this.getVector1().getX(), this.getVector2().getX()) &&
                        xOfIntersection >= Math.min(this.getVector1().getX(), this.getVector2().getX());
    }
}
