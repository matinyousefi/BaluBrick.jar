package Logic.Game.Prizes;

import Logic.DataBase.Constants;
import Logic.Game.Bricks.Brick;

public class FunkyRocket extends Prize{
    public FunkyRocket(Brick brick) {
        super(brick);
    }

    @Override
    public void expire() {
        super.getGameState().getRocket().setSpeed(Constants.initialRocketSpeed);
        getGameState().removeOnGoingPrize(this);
    }

    @Override
    public void act() {
        super.getGameState().getRocket().setSpeed(-Constants.initialRocketSpeed);
        getGameState().addOnGoingPrize(this);
    }
}
