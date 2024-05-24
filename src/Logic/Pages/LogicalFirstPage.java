package Logic.Pages;

import Graphic.GraphicalAgent;
import Graphic.Pages.HighScorePage.HighScorePage;
import Logic.Game.GameState;
import Logic.LogicalAgent;

import java.util.Objects;

public class LogicalFirstPage {

    public void loadGame(Graphic.Pages.FirstPage.FirstPage firstPage){
        String name;
        boolean gameExists;
        try {
            do {
                name = GraphicalAgent.loadGameName();
                gameExists = LogicalAgent.dataBaseAgent.gameExists(Objects.requireNonNull(name));
                if (!gameExists) GraphicalAgent.gameNotFound();
            } while (!gameExists);
            firstPage.dispose();
            GameState gameState = LogicalAgent.dataBaseAgent.loadGame(name);
            LogicalGamePage logicalGamePage = new LogicalGamePage(gameState);
            GraphicalAgent.newGamePage(logicalGamePage);
            logicalGamePage.pause();
        } catch (NullPointerException ignored){

        }
    }

    public void newGame(Graphic.Pages.FirstPage.FirstPage firstPage) {
        firstPage.dispose();
        LogicalAgent.newGame();
    }

    public void highScores(Graphic.Pages.FirstPage.FirstPage firstPage) {
        firstPage.dispose();
        new HighScorePage();
    }
}
