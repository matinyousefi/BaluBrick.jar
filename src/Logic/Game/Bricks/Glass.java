package Logic.Game.Bricks;

public class Glass extends Brick {
    public Glass(int x, int y) {
        super(x, y);
        this.setLife(1);
        this.setPrize();
        this.setScore(1);
    }
}
