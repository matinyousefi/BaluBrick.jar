package Logic.Pages;

import Graphic.GraphicalAgent;
import Graphic.Pages.GamePage.GraphicalGamePage;
import Logic.DataBase.Constants;
import Logic.Game.*;
import Logic.Game.Rocket.Rocket;
import Logic.LogicalAgent;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class LogicalGamePage {
    private final KeyListener keyListener;
    private final GameState gameState;
    private GraphicalGamePage graphicalGamePage;


    public LogicalGamePage(){
        gameState = new GameState();
        keyListener = buildKeyListener();
        gameState.setLogicalGamePage(this);
    }

    public LogicalGamePage(GameState gameState){
        this.gameState = gameState;
        keyListener = buildKeyListener();
        gameState.setLogicalGamePage(this);
    }

    private KeyListener buildKeyListener() {
        return new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            boolean leftIsDown;
            boolean rightIsDown;

            @Override
            public void keyPressed(KeyEvent e) {
                if (gameState.isPaused() && e.getKeyCode() != 27) {
                    return;
                }
                Rocket rocket = gameState.getRocket();
                switch (e.getKeyCode()) {
                    case 27: // esc
                        if(gameState.isPaused()){
                            unpause();
                        }
                        else {
                            pause();
                        }
                        break;
                    case 37:
                        if (rocket.getSpeedSign() == 1) {
                            leftIsDown = true;
                            if (rocket.getX() - rocket.getSpeed() < 0) {
                                rocket.setX(0);
                            } else {
                                if (leftIsDown) {
                                    rocket.setX(rocket.getX() - rocket.getSpeed());
                                    rocket.setSpeed(rocket.getSpeed() + rocket.getSpeedSign() * Constants.rocketSpeedIncreaseRate);
                                }
                            }

                        } else {
                            leftIsDown = true;
                            if (rocket.getX() + rocket.getSpeedSign() * rocket.getSpeed() > Constants.gamePanelWidth - rocket.getMainWidth()) {
                                rocket.setX((Constants.gamePanelWidth - rocket.getMainWidth()));
                            } else {
                                if (leftIsDown) {
                                    rocket.setX(rocket.getX() + rocket.getSpeedSign() *rocket.getSpeed());
                                    rocket.setSpeed(rocket.getSpeed() + rocket.getSpeedSign() * Constants.rocketSpeedIncreaseRate);
                                }
                            }
                        }

                        break;
                    case 39:
                        if (rocket.getSpeedSign() ==1 ) {
                            rightIsDown = true;
                            if (rocket.getX() + rocket.getSpeed() > Constants.gamePanelWidth - rocket.getMainWidth()) {
                                rocket.setX((Constants.gamePanelWidth - rocket.getMainWidth()));
                            } else {
                                if (rightIsDown) {
                                    rocket.setX(rocket.getX() + rocket.getSpeed());
                                    rocket.setSpeed(rocket.getSpeed() + rocket.getSpeedSign() * Constants.rocketSpeedIncreaseRate);
                                }
                            }
                        }
                        else {
                            rightIsDown = true;
                            if (rocket.getX() - rocket.getSpeedSign()*rocket.getSpeed() < 0) {
                                rocket.setX(0);
                            }
                            else {
                                if (rightIsDown) {
                                    rocket.setX(rocket.getX() - rocket.getSpeedSign()*rocket.getSpeed());
                                    rocket.setSpeed(rocket.getSpeed() + rocket.getSpeedSign()*Constants.rocketSpeedIncreaseRate);
                                }
                            }
                        }
                        break;
                }

                /*
                To reduce the number of tasks the following update is ignored
                 */
//                graphicalGamePage.update(gameState);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                Rocket rocket = gameState.getRocket();
                switch (e.getKeyCode()) {
                    case 37:
                        leftIsDown = false;
                        rocket.setSpeed(rocket.getSpeedSign()*Constants.initialRocketSpeed);
                        break;
                    case 39:
                        rightIsDown = true;
                        rocket.setSpeed(rocket.getSpeedSign()*Constants.initialRocketSpeed);
                        break;
                }
            }
        };
    }

    public void pause() {
        gameState.getTimer().stop();
        gameState.setPaused(true);
        graphicalGamePage.pause();
    }

    public void unpause() {
        gameState.getTimer().start();
        gameState.setPaused(false);
        graphicalGamePage.unpause();
    }

    public void setGraphicalGamePage(GraphicalGamePage graphicalGamePage) {
        this.graphicalGamePage = graphicalGamePage;
    }

    public KeyListener getKeyListener() {
        return keyListener;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void gameOver() {
        LogicalAgent.dataBaseAgent.saveScore(gameState.getScore());
        gameState.getTimer().stop();
        graphicalGamePage.dispose();
        System.gc();
        LogicalAgent.mainMenu();
    }

    public void restart() {
        LogicalAgent.dataBaseAgent.saveScore(gameState.getScore());
        gameState.getTimer().stop();
        graphicalGamePage.dispose();
        System.gc();
        LogicalAgent.newGame();
    }

    public void save() {
        LogicalAgent.dataBaseAgent.saveScore(gameState.getScore());
        String gameName = GraphicalAgent.saveGameName();
        if(gameName != null) {
            if(LogicalAgent.dataBaseAgent.gameExists(gameName)){
                if(GraphicalAgent.overwrite()){
                    LogicalAgent.dataBaseAgent.saveGame(gameState, gameName);
                } else {
                    save();
                }
            } else {
                LogicalAgent.dataBaseAgent.saveGame(gameState, gameName);
            }
        }
    }


    public void mainMenu() {
        LogicalAgent.dataBaseAgent.saveScore(gameState.getScore());
        System.gc();
        graphicalGamePage.dispose();
        LogicalAgent.mainMenu();
    }

    public void quit(){
        LogicalAgent.dataBaseAgent.saveScore(gameState.getScore());
        graphicalGamePage.dispose();
        System.exit(0);
    }

    public void update() {
        graphicalGamePage.update(gameState);
    }
}
