package Logic.Game.Prizes;

import Logic.DataBase.Constants;
import Logic.Game.Bricks.Brick;
import Logic.Game.Rocket.Rocket;
import Logic.Game.*;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public abstract class Prize {

    private final Brick brick;
    private GameState gameState;
    private int x;
    private int y;
    private int timerPassedSinceActivated;


    public Prize(@Nullable Brick brick) {
        this.brick = brick;
    }

    public static Prize randomPrize(Brick brick) {
        Random random = new Random();
        int prizeType = random.nextInt(Constants.randomPrizeRange);
        switch (prizeType) {
            case 1:
                return new FireBall(brick);
            case 2:
                return new EnlargeRocket(brick);
            case 3:
                return new FunkyRocket(brick);
            case 4:
                return new LessenRocket(brick);
            case 5:
                return new MultipleBalls(brick);
            case 6:
                return new SpeedDown(brick);
            case 7:
                return new SpeedUp(brick);
            case 8:
                return new UnknownPrize(brick);
            default: // case 0:
                return null;
        }
    }

    public abstract void expire();

    public abstract void act();

    public void drop(){
        x = brick.getX() + Constants.brickWidth/2;
        y = brick.getY() + Constants.brickHeight;
        gameState.addPrize(this);
    }

    public boolean fall(){ // returns boolean so if necessary the prize gets removed from gameState
        int nextY = y + Constants.prizeFallingSpeed;
        if (nextY >= Constants.rocketTopLeftCornerY) {
            Rocket rocket = gameState.getRocket();
            if (this.x >= rocket.getX() - rocket.getRocketExtraWidth() &&
                    this.x <= rocket.getX() + rocket.getMainWidth() + rocket.getRocketExtraWidth()) {
                act();
            }
            return true;
        } else {
            y = nextY;
            return false;
        }
    }

    public void setGameState(Logic.Game.GameState gameState) {
        this.gameState = gameState;
    }

    protected GameState getGameState() {
        return gameState;
    }

    protected Brick getBrick() {
        return brick;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getTimerPassedSinceActivated() {
        return timerPassedSinceActivated;
    }

    public void setTimerPassedSinceActivated(int timerPassedSinceActivated) {
        this.timerPassedSinceActivated = timerPassedSinceActivated;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
