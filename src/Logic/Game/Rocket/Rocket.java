package Logic.Game.Rocket;

import Logic.DataBase.Constants;
import Logic.Game.Ball.Ball;
import Logic.Game.Vector.Vector;
import Logic.Game.Segments.HorizontalSegment;

public class Rocket {
    private int speed;
    private int x; // mainRocketTopLeftCornerX
    private double scale;
    private static final int rocketExtraWidth = Constants.rocketExtraWidth;
    private static final int rocketMainWidth = Constants.rocketMainWidth;

    public Rocket(){
        speed = Constants.initialRocketSpeed;
        scale = Constants.initialRocketScale;
        x = ((int) Constants.gamePanelDimension.getWidth() - getMainWidth()) / 2;
    }

    public int getX() {
        return x;
    }

    public int getSpeed() {
        return speed;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getRocketExtraWidth() {
        return (int) (rocketExtraWidth*scale);
    }

    public double getScale() {
        return scale;
    }
    
    public boolean act(Ball ball) {

        HorizontalSegment left = new HorizontalSegment(new Vector(x - getRocketExtraWidth(), Constants.rocketTopLeftCornerY),
                new Vector(x, Constants.rocketTopLeftCornerY));

        if (left.intersects(ball)) {
//            if (ball.getCenter().getY() == ball.nextCenter().getY()) return false;
            if (
                    (ball.getCenter().getY() + ball.getSpeedYSign() * ball.getRadius() - left.getVector1().getY()) *
                            (ball.nextCenter().getY() + ball.getSpeedYSign() * ball.getRadius() - left.getVector1().getY()) > 0
            ) return false;
            double coefficient = (double) (ball.getCenter().getX() - ball.nextCenter().getX())
                    / (ball.getCenter().getY() - ball.nextCenter().getY());
            int xOfIntersection = (int) ((left.getVector1().getY()-ball.getCenter().getY()) * coefficient + ball.getCenter().getX());
            double reflectCoefficient =
                    ((double) x + getRocketExtraWidth() / 2 - xOfIntersection)/
                            (getRocketExtraWidth() / 2);
            if(ball.getSpeedXSign()==1){
                int x0 = (int) (ball.getSpeed().getX()*reflectCoefficient);
                if(x0 == 0) x0 = 1;
                ball.setSpeed(new Vector(x0, -ball.getSpeedYSign()*pythagoreanFriend(ball, x0)));
            }
            else{
                int y = (int) (-ball.getSpeed().getY() * reflectCoefficient);
                if(y == 0) y = 1;
                ball.setSpeed(new Vector(pythagoreanFriend(ball, y)*ball.getSpeedXSign(), y));
            }
            return true;
        }
        HorizontalSegment right = new HorizontalSegment (
                new Vector(x + getMainWidth() , Constants.rocketTopLeftCornerY) ,
                new Vector(x + getRocketExtraWidth() + getMainWidth() , Constants.rocketTopLeftCornerY));
        if (right.intersects(ball)) {
            if (
                    (ball.getCenter().getY() + ball.getSpeedYSign() * ball.getRadius() - left.getVector1().getY()) *
                            (ball.nextCenter().getY() + ball.getSpeedYSign() * ball.getRadius() - left.getVector1().getY()) > 0
            ) return false;
            double coefficient = (double) (ball.getCenter().getX() - ball.nextCenter().getX())
                    / (ball.getCenter().getY() - ball.nextCenter().getY());
            int xOfIntersection = (int) ((left.getVector1().getY()-ball.getCenter().getY()) * coefficient + ball.getCenter().getX());
            double reflectCoefficient =
                    ((double) x + getMainWidth() + getRocketExtraWidth() / 2 - xOfIntersection)/
                            (getRocketExtraWidth() / 2);

            if(ball.getSpeedXSign()==1){
                int y = (int) (-ball.getSpeed().getY() * reflectCoefficient);
                if(y == 0) y = 1;
                ball.setSpeed(new Vector(pythagoreanFriend(ball, y)*ball.getSpeedXSign(), y));
            }
            else{
                int x0 = (int) (ball.getSpeed().getX()*reflectCoefficient);
                if(x0 == 0) x0 = 1;
                ball.setSpeed(new Vector(x0, -ball.getSpeedYSign()*pythagoreanFriend(ball, x0)));
            }
            return true;
        }
        return new HorizontalSegment(new Vector(x, Constants.rocketTopLeftCornerY),
                new Vector((int) (x + getMainWidth()), Constants.rocketTopLeftCornerY))
                .act(ball);
    }

    public int pythagoreanFriend(Ball ball, int x){
        int z = ball.getSpeed().getX()*ball.getSpeed().getX() + ball.getSpeed().getY()*ball.getSpeed().getY();
        double y2 = z  - x * x;
        return Math.max((int) Math.sqrt(y2), 1);
    }
    public int getMainWidth() {
        return (int) (rocketMainWidth * scale);
    }

    public void scaleBy(double d){
        scale *= d;
    }

    public int getSpeedSign() {
        if(speed < 0) return -1;
        else return 1;
    }
}
