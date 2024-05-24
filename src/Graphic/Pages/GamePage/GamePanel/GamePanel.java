package Graphic.Pages.GamePage.GamePanel;

import Logic.DataBase.Constants;
import Logic.Game.Ball.Ball;
import Logic.Game.Bricks.Blinking;
import Logic.Game.Bricks.Brick;
import Logic.Game.*;
import Logic.Game.Prizes.Prize;
import Logic.Pages.*;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Iterator;

public class GamePanel extends JPanel {

    RocketLabel rocketLabel;
    HashMap<Ball, BallLabel> ballBallLabelHashMap;
    HashMap<Brick, BrickLabel> brickBrickLabelHashMap;
    HashMap<Prize, PrizeLabel> prizePrizeLabelHashMap;
    PauseLabel pauseLabel;


    public GamePanel(LogicalGamePage logicalGamePage){
        this.setPreferredSize(Constants.gamePanelDimension);
        this.setBackground(Constants.gamePanelColor);
        this.setLayout(null);

        this.rocketLabel = new RocketLabel(logicalGamePage.getGameState().getRocket());
        this.add(rocketLabel);

        this.ballBallLabelHashMap = new HashMap<>();
        this.brickBrickLabelHashMap = new HashMap<>();
        this.prizePrizeLabelHashMap = new HashMap<>();

        this.pauseLabel = new PauseLabel(logicalGamePage);
        this.add(pauseLabel);
        pauseLabel.setLocation(Constants.pauseLabelLocation);
        pauseLabel.setVisible(false);
    }

    public void update(GameState gameState){
        rocketLabel.setLocation(gameState.getRocket().getX() - gameState.getRocket().getRocketExtraWidth(),
                Constants.rocketTopLeftCornerY);
        rocketLabel.repaint();

        for (Ball ball :
                gameState.getBalls()) {
            BallLabel ballLabel;
            if(ballBallLabelHashMap.containsKey(ball)){
                ballLabel = ballBallLabelHashMap.get(ball);
            }else{
                ballLabel = new BallLabel();
                this.add(ballLabel);
                ballBallLabelHashMap.put(ball, ballLabel);
            }
            if(ball.isActive()) {
                ballLabel.setLocation(ball.getCenter().getX() - Constants.ballMargin - Constants.ballRadius,
                        ball.getCenter().getY() - Constants.ballMargin - Constants.ballRadius);
                ballLabel.repaint();
            }
        }

        Iterator<Ball> ballIterator = ballBallLabelHashMap.keySet().iterator();
        while (ballIterator.hasNext()) {
            Ball ball = ballIterator.next();
            if (!ball.isActive()) {
                ballBallLabelHashMap.get(ball).setVisible(false);
                ballIterator.remove();
            }
        }


        for (Brick brick :
                gameState.getBricks()) {
            BrickLabel brickLabel;
            if(brickBrickLabelHashMap.containsKey(brick)){
                 brickLabel= brickBrickLabelHashMap.get(brick);
            }else{
                brickLabel = new BrickLabel(brick);
                this.add(brickLabel);
                brickBrickLabelHashMap.put(brick, brickLabel);
            }
            if (!(brick instanceof Blinking)) {
                // although the common part of if and else can be extracted I have avoided doing so to
                // reduce expected number of tasks
                if (brick.isActive()) {
                    brickLabel.setLocation(brick.getX(), brick.getY());
                    brickLabel.repaint();
                }
            } else {
                if (brick.isActive()) {
                    brickLabel.setVisible(((Blinking) brick).isVisible());
                    brickLabel.setLocation(brick.getX(), brick.getY());
                    brickLabel.repaint();
                }
            }
        }

        Iterator<Brick> brickIterator = brickBrickLabelHashMap.keySet().iterator();
        while (brickIterator.hasNext()) {
            Brick brick = brickIterator.next();
            if (!brick.isActive()) {
                brickBrickLabelHashMap.get(brick).setVisible(false);
                brickIterator.remove();
            }
        }

        for (Prize prize :
                gameState.getPrizes()) {
            PrizeLabel prizeLabel;
            if(prizePrizeLabelHashMap.containsKey(prize)){
                prizeLabel = prizePrizeLabelHashMap.get(prize);
            }else{
                prizeLabel = new PrizeLabel(prize);
                this.add(prizeLabel);
                prizePrizeLabelHashMap.put(prize, prizeLabel);
            }
            prizeLabel.setLocation(prize.getX() - Constants.prizeSize/2,
                    prize.getY() - Constants.prizeSize/2);
            prizeLabel.repaint();
        }

        Iterator<Prize> prizeIterator = prizePrizeLabelHashMap.keySet().iterator();
        while (prizeIterator.hasNext()) {
            Prize prize = prizeIterator.next();
            if (!gameState.getPrizes().contains(prize)) {
                prizePrizeLabelHashMap.get(prize).setVisible(false);
                prizeIterator.remove();
            }
        }

    }

    public void pause() {
        pauseLabel.setVisible(true);
    }

    public void unpause(){
        pauseLabel.setVisible(false);
    }
}
