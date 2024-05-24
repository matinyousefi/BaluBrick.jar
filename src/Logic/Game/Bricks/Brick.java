package Logic.Game.Bricks;

import Logic.DataBase.Constants;
import Logic.Game.Ball.Ball;
import Logic.Game.Vector.Vector;
import Logic.Game.Prizes.Prize;
import Logic.Game.Segments.HorizontalSegment;
import Logic.Game.Segments.VerticalSegment;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Random;

public abstract class Brick {

    private static final int brickHeight = Constants.brickHeight;
    private static final int brickWidth = Constants.brickWidth;
    private int x;
    private int y;
    private boolean isActive;
    private int life;
    private Prize prize;
    private int score;

    public Brick(int x, int y) {
        this.x = x;
        this.y = y;
        this.isActive = true;
    }

    public static Brick randomBrick(int x, int y) {
        Random random = new Random();
        int brickType = random.nextInt(5);
        switch (brickType) {
            case 1:
                return new Blinking(x,y);
            case 2:
                return new Glass(x,y);
            case 3:
                return new Wooden(x, y);
            case 4:
                return new Invisible(x, y);
            default: // includes only the case brickType = 0;
                return null;
        }
    }

    public int act(Ball ball){
        Vector topLeft = new Vector(x, y);
        Vector topRight = new Vector(x + brickWidth, y);
        Vector bottomLeft = new Vector(x, y + brickHeight);
        Vector bottomRight = new Vector(x + brickWidth, y + brickHeight);
        VerticalSegment leftBound = new VerticalSegment(topLeft, bottomLeft);
        VerticalSegment rightBound = new VerticalSegment(topRight, bottomRight);
        HorizontalSegment upperBound = new HorizontalSegment(topLeft, topRight);
        HorizontalSegment lowerBound = new HorizontalSegment(bottomLeft, bottomRight);
        if (ball.isOnFire()) {
            if (
                    leftBound.intersects(ball)||
                    rightBound.intersects(ball)||
                    upperBound.intersects(ball)||
                    lowerBound.intersects(ball)
            ){
                life = 0;
                isActive = false;
                if (Objects.nonNull(prize)) {
                    prize.drop();
                }
                return score;
            }
        }
        if (isActive) {
            if (leftBound.act(ball)){
                life--;
                if (life == 0) {
                    isActive = false;
                    if (Objects.nonNull(prize)) {
                        prize.drop();
                    }
                    return score;
                }
            }
            else if (rightBound.act(ball)){
                life--;
                if (life == 0) {
                    isActive = false;
                    if (Objects.nonNull(prize)) {
                        prize.drop();
                    }
                    return score;
                }
            }
            else if (lowerBound.act(ball)){
                life--;
                if (life == 0) {
                    isActive = false;
                    if (Objects.nonNull(prize)) {
                        prize.drop();
                    }
                    return score;
                }
            }
            else if (upperBound.act(ball)){
                life--;
                if (life == 0) {
                    isActive = false;
                    if (Objects.nonNull(prize)) {
                        prize.drop();
                    }
                    return score;
                }
            }
        }
        return 0;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isActive(){
        return isActive;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public boolean fall(){
        y += Constants.brickHeight;
        return y >= Constants.rocketTopLeftCornerY;
    }

    public void setPrize() {
        this.prize = Prize.randomPrize(this);
    }

    protected void setScore(int i){
        this.score = i;
    }

    public Prize getPrize() {
        return prize;
    }

    public int getLife() {
        return life;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setActive(boolean active) {
        isActive = active;
    }


    public void setPrize(@Nullable Prize prize) {
        this.prize = prize;
    }

}
