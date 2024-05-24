package Logic.Game.Bricks;

import Logic.Game.Ball.Ball;

import javax.swing.*;


public class Blinking extends Brick {

    private boolean isVisible;

    public Blinking(int x, int y) {
        super(x, y);
        this.setLife(1);
        this.setPrize();
        this.isVisible = true;
        this.setScore(2);
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    @Override
    public int act(Ball ball) {
        if(isVisible) {
            return super.act(ball);
        }
        else return 0;
    }

}
