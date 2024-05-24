package Graphic.Pages.GamePage.GamePanel;

import Logic.DataBase.Constants;
import Logic.Game.Rocket.Rocket;

import javax.swing.*;
import java.awt.*;

public class RocketLabel extends JLabel {

    private double scale;
    private int rocketHeight;
    private int rocketMainWidth;
    private int rocketExtraWidth;
    private Rocket rocket;

    public RocketLabel(Rocket rocket){
        this.rocket = rocket;
        this.scale = rocket.getScale();
        rocketHeight = Constants.rocketHeight;
        rocketMainWidth = (int) (Constants.rocketMainWidth * scale);
        rocketExtraWidth = (int) (Constants.rocketExtraWidth * scale);
        this.setSize(new Dimension(2*rocketExtraWidth + rocketMainWidth, rocketHeight));
    }

    public void paint(Graphics g){
        this.scale = rocket.getScale();
        rocketHeight = Constants.rocketHeight;
        rocketMainWidth = (int) (Constants.rocketMainWidth * scale);
        rocketExtraWidth = (int) (Constants.rocketExtraWidth * scale);
        this.setSize(new Dimension(2*rocketExtraWidth + rocketMainWidth, rocketHeight));
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Constants.rocketExtraColor);
        g2d.fillRect(0, 0, rocketExtraWidth, rocketHeight);
        g2d.fillRect(rocketExtraWidth + rocketMainWidth, 0, rocketExtraWidth, rocketHeight);
        g2d.setColor(Constants.rocketMainColor);
        g2d.fillRect(rocketExtraWidth, 0, rocketMainWidth, rocketHeight);
    }

}
