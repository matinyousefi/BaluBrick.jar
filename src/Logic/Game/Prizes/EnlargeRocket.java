package Logic.Game.Prizes;

import Logic.DataBase.Constants;
import Logic.Game.Bricks.Brick;

public class EnlargeRocket extends Prize {
    public EnlargeRocket(Brick brick) {
        super(brick);
    }

    @Override
    public void expire() {
        this.getGameState().getRocket().scaleBy(Constants.rocketScalingDownConstant);
        getGameState().removeOnGoingPrize(this);
    }

    @Override
    public void act() {
        this.getGameState().getRocket().scaleBy(Constants.rocketScalingUpConstant);
        getGameState().addOnGoingPrize(this);
    }
}
