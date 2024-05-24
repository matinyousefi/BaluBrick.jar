package Graphic.Pages.GamePage.GamePanel;

import Logic.DataBase.Constants;
import Logic.Game.Bricks.*;

import javax.swing.*;
import java.awt.*;

public class BrickLabel extends JLabel {
    private Brick brick;

    public BrickLabel(Brick brick){
        this.brick = brick;
        this.setSize(new Dimension(Constants.brickWidth, Constants.brickHeight));
    }

    @Override
    public void paint(Graphics g) {
//        if (brick instanceof Invisible) {
//            return;
//        }
        Graphics2D g2d = (Graphics2D) g;
        if (brick instanceof Wooden ){
            g2d.setColor(Constants.woodenBrickColor);
        } else if (brick instanceof Glass){
            g2d.setColor(Constants.glassBrickColor);
        } else if (brick instanceof Blinking) {
            g2d.setColor(Constants.blinkingBrickColor);
        }
        else if(brick instanceof Invisible) g2d.setColor(Color.CYAN);
        g2d.fillRect(1,1, Constants.brickWidth-2, Constants.brickHeight-2);
        if (brick instanceof Wooden) {
            g2d.setColor(Constants.woodenBrickMarginColor);
        } else if (brick instanceof Glass){
            g2d.setColor(Constants.glassBrickMarginColor);
        } else if (brick instanceof Blinking) {
            g2d.setColor(Constants.blinkingBrickMarginColor);
        }
        g2d.setStroke(new BasicStroke(Constants.brickBorderThickness));
        g2d.drawRect(1,1, Constants.brickWidth-2, Constants.brickHeight-2);
    }
}
