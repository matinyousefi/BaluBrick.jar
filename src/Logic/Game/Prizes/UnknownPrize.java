package Logic.Game.Prizes;

import Logic.Game.Bricks.Brick;

import java.util.Objects;
import java.util.Random;

public class UnknownPrize extends Prize {
    private Prize prize;

    public UnknownPrize(Brick brick) {
        super(brick);
    }

    @Override
    public void expire() {
        prize.expire();
    }

    @Override
    public void act() {
        Random random = new Random();
        int typeInt = random.nextInt(7);
        switch (typeInt) {
            case 0:
                prize = new EnlargeRocket(getBrick());
                break;
            case 1:
                prize = new FireBall(getBrick());
                break;
            case 2:
                prize = new FunkyRocket(getBrick());
                break;
            case 3:
                prize = new LessenRocket(getBrick());
                break;
            case 4:
                prize = new MultipleBalls(getBrick());
                break;
            case 5:
                prize = new SpeedDown(getBrick());
                break;
            case 6:
                prize = new SpeedUp(getBrick());
                break;
        }
        prize.setGameState(getGameState());
        prize.act();
    }
}
