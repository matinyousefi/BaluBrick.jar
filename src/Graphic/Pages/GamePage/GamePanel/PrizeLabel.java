package Graphic.Pages.GamePage.GamePanel;

import Logic.DataBase.Constants;
import Logic.Game.Prizes.*;

import javax.swing.*;
import java.awt.*;

public class PrizeLabel extends JLabel {
    private Prize prize;

    public PrizeLabel(Prize prize){
        this.prize = prize;
        this.setSize(Constants.prizeDimension);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        if (prize instanceof FireBall){
            g2d.setColor(Constants.fireBallPrizeColor);
        } else if (prize instanceof MultipleBalls) {
            g2d.setColor(Constants.multipleBallsPrizeColor);
        } else if (prize instanceof SpeedDown) {
            g2d.setColor(Constants.speedDownPrizeColor);
        } else if (prize instanceof SpeedUp) {
            g2d.setColor(Constants.speedUpPrizeColor);
        }
        if(
                prize instanceof FireBall ||
                prize instanceof MultipleBalls ||
                prize instanceof SpeedUp ||
                prize instanceof SpeedDown
        ) {
            g2d.fillOval(0, 0, Constants.prizeSize, Constants.prizeSize);
            return;
        }

        if (prize instanceof EnlargeRocket) {
            g2d.setColor(Constants.enlargeRocketPrizeColor);
        } else if (prize instanceof FunkyRocket) {
            g2d.setColor(Constants.funkyRocketPrizeColor);
        } else if (prize instanceof LessenRocket) {
            g2d.setColor(Constants.lessenRocketPrizeColor);
        }
        if(
                        prize instanceof EnlargeRocket ||
                        prize instanceof FunkyRocket ||
                        prize instanceof LessenRocket
        ) {
            g2d.fillRect(0, 0, Constants.prizeSize, Constants.prizeSize);
            return;
        }

        if (prize instanceof UnknownPrize) {
            g2d.setColor(Constants.unknownPrizeColor2);
            g2d.fillRect(0, 0, Constants.prizeSize, Constants.prizeSize);
            g2d.setColor(Constants.unknownPrizeColor);
            g2d.fillOval(0, 0, Constants.prizeSize, Constants.prizeSize);
        }
    }
}

