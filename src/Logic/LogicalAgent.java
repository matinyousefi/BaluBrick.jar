package Logic;

import Logic.DataBase.DataBaseAgent;
import Graphic.GraphicalAgent;

import Logic.Pages.*;

public class LogicalAgent {

    public static DataBaseAgent dataBaseAgent;

    public static void start(){
        dataBaseAgent = new DataBaseAgent();
        LogicalAgent.dataBaseAgent.setUsername(GraphicalAgent.getUsername());
        GraphicalAgent.newFirstPage();
    }

    public static void mainMenu() {
        GraphicalAgent.newFirstPage();
    }


    public static void newGame() {
        LogicalGamePage logicalGamePage = new LogicalGamePage();
        GraphicalAgent.newGamePage(logicalGamePage);
        logicalGamePage.pause();
    }
}
