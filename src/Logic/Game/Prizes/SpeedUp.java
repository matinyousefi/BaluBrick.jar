package Logic.Game.Prizes;

import Logic.DataBase.Constants;
import Logic.Game.Ball.Ball;
import Logic.Game.Bricks.Brick;
import Logic.Game.Vector.Vector;

public class SpeedUp extends Prize {


    public SpeedUp(Brick brick) {
        super(brick);
    }


    @Override
    public void expire() {
        for (Ball ball :
                super.getGameState().getBalls()) {
            if(ball.isSpeedUp()) {
                Vector newSpeed = new Vector(
                        ball.getSpeedXSign() * Math.max((int) (ball.getSpeed().getX() * ball.getSpeedXSign() * Constants.ballSpeedDownConstant), 1),
                        ball.getSpeedYSign() * Math.max((int) (ball.getSpeed().getY() * ball.getSpeedYSign() * Constants.ballSpeedDownConstant), 1)
                );
                ball.setSpeed(newSpeed);
                ball.setSpeedUp(false);
            }
        }
        getGameState().removeOnGoingPrize(this);
    }

    @Override
    public void act() {
        for (Ball ball :
                super.getGameState().getBalls()) {
            if(!ball.isSpeedUp()) {
                Vector newSpeed = new Vector(
                        (int) (ball.getSpeed().getX() * Constants.ballSpeedUpConstant),
                        (int) (ball.getSpeed().getY() * Constants.ballSpeedUpConstant)
                        );
                ball.setSpeed(newSpeed);
                ball.setSpeedUp(true);
            }
        }
        getGameState().addOnGoingPrize(this);
    }
}
