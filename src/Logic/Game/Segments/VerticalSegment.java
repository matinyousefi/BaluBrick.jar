package Logic.Game.Segments;


import Logic.Game.Ball.Ball;
import Logic.Game.Vector.Vector;


public class VerticalSegment extends  Segment{

    public VerticalSegment(Vector Vector1, Vector Vector2) {
        super(Vector1, Vector2);
        if(Vector1.getX()!= Vector1.getX()) System.out.println("Xs do not match.");;
    }

    @Override
    public boolean act(Ball ball) {
        if(intersects(ball)){
            ball.getSpeed().setX(-ball.getSpeed().getX());
            return true;
        }
        return false;
    }

    @Override
    public boolean isVertical() {
        return true;
    }

    @Override
    public boolean isHorizontal() {
        return false;
    }

    @Override
    public boolean intersects(Ball ball) {
        if (ball.getCenter().getX() == ball.nextCenter().getX()) return false;
        if (
                (ball.getCenter().getX() + ball.getSpeedXSign() * ball.getRadius() - this.getVector1().getX()) *
                        (ball.nextCenter().getX() + ball.getSpeedXSign() * ball.getRadius() - this.getVector1().getX()) > 0
        ) return false;
        double coefficient = (double) (ball.getCenter().getY() - ball.nextCenter().getY())
                / (ball.getCenter().getX() - ball.nextCenter().getX());
        int yOfIntersection = (int) ((this.getVector1().getX()-ball.getCenter().getX()) * coefficient + ball.getCenter().getY());
        return
                yOfIntersection < Math.max(this.getVector1().getY(), this.getVector2().getY()) &&
                        yOfIntersection >= Math.min(this.getVector1().getY(), this.getVector2().getY());
    }
}
