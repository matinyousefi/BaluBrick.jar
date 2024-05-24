package Logic.DataBase;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;

public interface Constants {
    ImageIcon logo = new ImageIcon("Resources\\logo.png");
    ImageIcon ball = new ImageIcon("Resources\\ball.png");
    ImageIcon back = new ImageIcon("Resources\\back.png");

    Image backImage = back.getImage();
    Image ballImage = ball.getImage();

    Dimension buttonDimension = new Dimension(400, 100);
    Dimension gamePanelDimension = new Dimension(600,800);
    Dimension ballDimension = new Dimension(30 + 2 * Constants.ballMargin, 30 + 2 * Constants.ballMargin);
    Dimension statusPanelDimension = new Dimension(600, 50);
    Dimension statusPanelLabelDimension = new Dimension(200, 35);
    Dimension statusPanelLifeDimension = new Dimension(100, 35);
    Dimension prizeDimension = new Dimension(Constants.prizeSize,Constants.prizeSize);
    Dimension pauseLabelDimension = new Dimension(400,600);
    Dimension pauseLabelButtonDimension = new Dimension(200,100);
    Dimension backPanelDimension = new Dimension(600, 60); // Must be compatible with back image
    Dimension highScorePanelDimension = new Dimension(600, 800);
    Dimension backImageDimension = new Dimension(50,50);

    Font buttonFont = new Font("Times New Roman", Font.PLAIN, 30);
    Font usernameFont = new Font("Times New Roman", Font.PLAIN, 30);
    Font highScoreFont = new Font("Times New Roman", Font.BOLD, 20);
    Font highScoreValueFont = new Font("Times New Roman", Font.BOLD, 40);
    Font statusPanelLabelFont = new Font("Times New Roman", Font.PLAIN, 30);
    Font pauseLabelButtonFont = new Font("Times New Roman", Font.PLAIN, 30);
    Font highScoresPageFont = new Font("Times New Roman", Font.PLAIN, 30);

    Color firstPageBackgroundColor = new Color(255,255,255);
    Color buttonColor = new Color(218, 206, 246);
    Color usernameColor = new Color(50, 4, 75);
    Color highScoreColor = new Color(50, 4, 75);
    Color highScoreValueColor = new Color(196, 10, 53);
    Color gamePanelColor = new Color(255, 255, 255);
    Color rocketMainColor = Color.BLACK;
    Color rocketExtraColor = new Color(37, 74, 196);
    Color statusPanelBackgroundColor = new Color(174, 176, 178, 190);
    Color scoreLabelFontColor = Color.BLACK;
    Color woodenBrickColor = new Color(243, 149, 9);
    Color woodenBrickMarginColor = new Color(222, 86, 21);
    Color glassBrickColor = new Color(212, 205, 205, 255);
    Color glassBrickMarginColor = new Color(0, 0, 0, 255);
    Color blinkingBrickColor = new Color(66, 255, 0);
    Color blinkingBrickMarginColor = new Color(10, 83, 0);
    Color fireBallPrizeColor = Color.RED;
    Color multipleBallsPrizeColor = Color.BLACK;
    Color speedDownPrizeColor = Color.CYAN;
    Color speedUpPrizeColor = Color.BLUE;
    Color enlargeRocketPrizeColor = Color.BLUE;
    Color lessenRocketPrizeColor = Color.CYAN;
    Color funkyRocketPrizeColor = Color.YELLOW;
    Color unknownPrizeColor = Color.MAGENTA;
    Color unknownPrizeColor2 = new Color(59, 4, 141);
    Color pauseLabelButtonColor = new Color(255, 170, 0);
    Color highScorePageColor = Color.WHITE;
    Color highScoresText = Color.BLACK;

    Border buttonBorder = BorderFactory.createLineBorder(new Color(10, 36, 68), 3, true);
    Border pauseLabelButtonBorder = BorderFactory.createLineBorder(new Color(0, 0, 0), 3, false);

    int rocketHeight = 20;
    int rocketMainWidth = 80;
    int rocketExtraWidth = 20;  // an even number
    int rocketTopLeftCornerY = 750;
    int initialRocketSpeed = 30;
    double initialRocketScale = 1;
    int rocketSpeedIncreaseRate = 5;
    double rocketScalingUpConstant = 1.25;
    double rocketScalingDownConstant = 0.8;

    float brickBorderThickness = 1;
    int brickHeight = 20;
    int brickWidth = 60;

    int gamePanelWidth = (int) gamePanelDimension.getWidth();
    int gamePanelHeight = (int) gamePanelDimension.getHeight();

    /*
    Setting blinkAfterThisManyTimers somewhat co-prime with newRowAfterThisManyTimers
    will make the blinking more random and fun.
     */
    int blinkAfterThisManyTimers = 1000;
    int newRowAfterThisManyTimers = 1234;
    int prizeExpireAfterThisManyTimers = 1000;
    int timerDelay = 10;

    double ballVelocityRange = 7.7; // Put it at least 6
    int speedComponentMin = 3;
    int ballMargin = 10;
    int ballRadius = 10;
    // the following must be each other's revers
    double ballSpeedUpConstant = 1.5;
    double ballSpeedDownConstant = (double) 2/3;
    // note that if ballSpeedDownConstant * speedComponentMin < 1 you might get a 0 speed component while speeding down

    int prizeFallingSpeed = 4;
    int prizeSize = 20;

    int randomPrizeRange = 18;

    String gameName = "balu";

    Point pauseLabelLocation = new java.awt.Point(100,75);

}
