package Logic.Game.Prizes;

import Logic.Game.Ball.Ball;
import Logic.Game.Bricks.Brick;
import Logic.Game.Vector.Vector;

import java.util.ArrayList;

public class MultipleBalls extends Prize {

    public MultipleBalls(Brick brick) {
        super(brick);
    }

    @Override
    public void expire() {

    }

    @Override
    public void act() {
        /*
        Coincidence of ball1 and ball2 speed vectors is avoided
         */
        Ball.speedVectorsConstructor();
        ArrayList<Vector> speedVectors = Ball.getSpeedVectors();
        Vector speed = new Vector(speedVectors.get(0).getX(), speedVectors.get(0).getY());
        Ball.speedVectorsConstructor();
        speedVectors = Ball.getSpeedVectors();
        Vector speed2 = new Vector(-speedVectors.get(0).getX(), speedVectors.get(0).getY());

        Ball ball1 = new Ball();
        ball1.setSpeed(speed);

        Ball ball2 = new Ball();
        ball1.setSpeed(speed2);

        getGameState().addBall(ball1);
        getGameState().addBall(ball2);
    }
}
