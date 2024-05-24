package Logic.Game.Bricks;

public class Invisible extends Brick {

    public Invisible(int x, int y) {
        super(x, y);
        this.setLife(1);
        this.setPrize();
        this.setScore(2);
    }

}
