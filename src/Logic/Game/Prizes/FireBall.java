package Logic.Game.Prizes;

import Logic.Game.Ball.Ball;
import Logic.Game.Bricks.Brick;

public class FireBall extends Prize {

    public FireBall(Brick brick) {
        super(brick);
    }

    @Override
    public void expire() {
        for (Ball ball :
                super.getGameState().getBalls()) {
            ball.setOnFire(false);
        }
        getGameState().removeOnGoingPrize(this);
    }

    @Override
    public void act() {
        for (Ball ball :
                super.getGameState().getBalls()) {
            if(!ball.isOnFire()) {
                ball.setOnFire(true);
            }
        }
        getGameState().addOnGoingPrize(this);
    }

}
