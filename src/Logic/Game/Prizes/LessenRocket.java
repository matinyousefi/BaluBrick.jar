package Logic.Game.Prizes;

import Logic.DataBase.Constants;
import Logic.Game.Bricks.Brick;

public class LessenRocket extends Prize {

    public LessenRocket(Brick brick) {
        super(brick);
    }

    @Override
    public void expire() {
        this.getGameState().getRocket().scaleBy(Constants.rocketScalingUpConstant);
        getGameState().removeOnGoingPrize(this);
    }

    @Override
    public void act() {
        this.getGameState().getRocket().scaleBy(Constants.rocketScalingDownConstant);
        getGameState().addOnGoingPrize(this);
    }
}
