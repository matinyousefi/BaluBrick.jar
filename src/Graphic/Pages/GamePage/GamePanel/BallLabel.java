package Graphic.Pages.GamePage.GamePanel;

import Logic.DataBase.Constants;

import javax.swing.*;
import java.awt.*;

public class BallLabel extends JLabel {
    private static Image ballImage = Constants.ballImage;

    public BallLabel(){
        this.setSize(Constants.ballDimension);
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(ballImage, Constants.ballMargin, Constants.ballMargin, null);
    }
}
