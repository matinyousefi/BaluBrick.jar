package Logic.Game.Bricks;

import Logic.Game.Ball.Ball;

public class Wooden extends Brick {

    public Wooden(int x, int y){
        super(x, y);
        this.setLife(2);
        this.setPrize();
        this.setScore(3);
    }

}

