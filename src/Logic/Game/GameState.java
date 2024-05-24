package Logic.Game;

import Logic.DataBase.Constants;
import Logic.Game.Ball.Ball;
import Logic.Game.Bricks.Blinking;
import Logic.Game.Bricks.Brick;
import Logic.Game.Prizes.Prize;
import Logic.Game.Rocket.Rocket;
import Logic.Game.Segments.HorizontalSegment;
import Logic.Game.Segments.Segment;
import Logic.Game.Segments.VerticalSegment;
import Logic.Game.Vector.Vector;
import Logic.Pages.LogicalGamePage;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;

public class GameState {
    private final HashSet<Segment> frameBounds;
    private final HashSet<Ball> balls;
    private final HashSet<Brick> bricks;
    private final HashSet<Prize> prizes;
    private final HashSet<Prize> onGoingPrize;
    private final Rocket rocket;
    private int score;
    private int life;
    private LogicalGamePage logicalGamePage;
    private boolean paused;
    private String gameName;
    private Timer timer;
    private int timerCounter;

    public GameState(){
        rocket = new Rocket();
        bricks = new HashSet<>();
        prizes = new HashSet<>();
        onGoingPrize = new HashSet<>();
        score = 0;
        life = 3;
        ActionListener timerActionListener = e -> eachTimer();
        timer = new Timer(Constants.timerDelay, timerActionListener);
        timerCounter = 0;
        balls = new HashSet<>();
        Ball ball = new Ball();
        balls.add(ball);
        frameBounds = new HashSet<>();
        constructFrameBounds();
        eachNewRowTimer();
        eachNewRowTimer();
        eachNewRowTimer();
    }

    public GameState(int timerCounter, int score, int life,  Ball[] balls, Brick[] bricks, Prize[] prizes, Prize[] onGoingPrizes) {
        rocket = new Rocket();
        this.timerCounter = timerCounter;
        this.score = score;
        this.life = life;
        this.bricks = new HashSet<>(Arrays.asList(bricks));
        this.prizes = new HashSet<>(Arrays.asList(prizes));
        this.onGoingPrize = new HashSet<>(Arrays.asList(onGoingPrizes));
        this.balls = new HashSet<>(Arrays.asList(balls));
        ActionListener timerActionListener = e -> eachTimer();
        timer = new Timer(Constants.timerDelay, timerActionListener);
        frameBounds = new HashSet<>();
        constructFrameBounds();
        for (Prize prize :
                this.prizes) {
            prize.setGameState(this);
        }
        for (Prize prize :
              this.onGoingPrize) {
            prize.setGameState(this);
            prize.act();
        }
        for (Brick brick :
                bricks) {
            if (!Objects.isNull(brick.getPrize())) {
                brick.getPrize().setGameState(this);
            }
        }
    }

    private void constructFrameBounds() {
        Vector topLeft = new Vector(0, 0);
        Vector bottomLeft = new Vector(0, Constants.gamePanelHeight);
        Vector topRight = new Vector(Constants.gamePanelWidth, 0);
        Vector bottomRight = new Vector(Constants.gamePanelWidth, Constants.gamePanelHeight);
        VerticalSegment leftBound = new VerticalSegment(topLeft, bottomLeft);
        VerticalSegment rightBound = new VerticalSegment(topRight, bottomRight);
        HorizontalSegment upperBound = new HorizontalSegment(topLeft, topRight);
        HorizontalSegment lowerBound = new HorizontalSegment(bottomLeft, bottomRight){
            @Override
            public boolean act(Ball ball){
                if(intersects(ball)){
                    ball.setActive(false);
                    boolean atLeastOneActiveBall = false;
                    for (Ball ballIterator :
                            balls) {
                        atLeastOneActiveBall = ballIterator.isActive() || atLeastOneActiveBall;
                    }
                    if(!atLeastOneActiveBall){
                        life--;
                        if(life==0){
                            gameOver();
                        }
                        else {
                            Ball newBall = new Ball();
                            balls.add(newBall);
                        }
                    }
                }
                return false;
            }
        };
        frameBounds.add(leftBound);
        frameBounds.add(rightBound);
        frameBounds.add(upperBound);
        frameBounds.add(lowerBound);
    }

    private void eachTimer() {
        timerCounter++;
        if (timerCounter % Constants.blinkAfterThisManyTimers == 0) {
            eachBlinkTimer();
        }
        if (timerCounter % Constants.newRowAfterThisManyTimers == 0) {
            eachNewRowTimer();
        }
        Iterator<Prize> prizeIterator = onGoingPrize.iterator();
        while (prizeIterator.hasNext()) {
            Prize prize = prizeIterator.next();
            prize.setTimerPassedSinceActivated(
                    prize.getTimerPassedSinceActivated() + 1
            );
            if(prize.getTimerPassedSinceActivated() == Constants.prizeExpireAfterThisManyTimers){
                prizeIterator.remove();
                prize.expire();
            }
        }
        nextState();
        logicalGamePage.update();
    }

    public Timer getTimer() {
        return timer;
    }

    public void eachNewRowTimer() {
        for (Brick brick :
                bricks) {
            if(brick.fall()){   // shifts all the bricks down
                gameOver();
            }
        }
        for (int i = 0; i < 10; i++) {
            try {
                Brick newBrick = Objects.requireNonNull(Brick.randomBrick(i * Constants.brickWidth, 0));
                Prize newBrickPrize = Objects.requireNonNull(newBrick.getPrize());
                newBrickPrize.setGameState(this);
                bricks.add(newBrick);
            }
            catch (NullPointerException ignored){

            }
        }
    }

    private void gameOver(){
        logicalGamePage.gameOver();
    }

    public void eachBlinkTimer() {
        for (Brick brick :
                bricks) {
            if (brick instanceof Blinking) {
                ((Blinking) brick).setVisible(!((Blinking) brick).isVisible());
            }
        }
    }

    public Rocket getRocket() {
        return rocket;
    }

    public HashSet<Ball> getBalls() {
        return balls;
    }

    public HashSet<Brick> getBricks() {
        return bricks;
    }

    public void nextState() {
        Iterator<Ball> ballIterator = balls.iterator();
        while (ballIterator.hasNext()) {
            Ball ball = ballIterator.next();
            if(!ball.isActive()){
                ballIterator.remove();
            }
            Iterator<Brick> brickIterator = bricks.iterator();
            while (brickIterator.hasNext()) {
                Brick brick = brickIterator.next();
                int actInt = brick.act(ball);
                if (actInt != 0) {
                    score += actInt;
                    brickIterator.remove();
                    /*
                    to avoid concurrent modification exception
                     */
                    while(brickIterator.hasNext()){
                        brickIterator.next();
                    }
                    while(ballIterator.hasNext()){
                        ballIterator.next();
                    }
                    nextState();
                }
            }
            Iterator<Segment> segmentIterator = frameBounds.iterator();
            while(segmentIterator.hasNext()) {
                Segment segment = segmentIterator.next();
                if (segment.act(ball)) {
                    /*
                    to avoid concurrent modification exception
                     */
                    while (brickIterator.hasNext()){
                        brickIterator.next();
                    }
                    while (ballIterator.hasNext()){
                        ballIterator.next();
                    }
                    while (segmentIterator.hasNext()){
                        segmentIterator.next();
                    }
                    nextState();
                }
            }
            rocket.act(ball);
            ball.setCenter(ball.nextCenter());
        }

        prizes.removeIf(Prize::fall);

    }

    public void setLogicalGamePage(LogicalGamePage logicalGamePage) {
        this.logicalGamePage = logicalGamePage;
    }

    public int getScore() {
        return score;
    }

    public int getLife() {
        return life;
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public void addPrize(Prize prize) {
        prizes.add(prize);
    }

    public HashSet<Prize> getPrizes() {
        return prizes;
    }
    
    public void addOnGoingPrize(Prize prize){
        onGoingPrize.add(prize);
    }

    public void removeOnGoingPrize(Prize prize) {
        onGoingPrize.remove(prize);
    }

    public void addBall(Ball ball) {
        balls.add(ball);
    }

    public int getTimerCounter() {
        return timerCounter;
    }

    public HashSet<Prize> getOnGoingPrize() {
        return onGoingPrize;
    }
}
