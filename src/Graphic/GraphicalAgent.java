package Graphic;

import Graphic.Pages.FirstPage.FirstPage;
import Graphic.Pages.GamePage.GraphicalGamePage;
import Graphic.UsernamePopUp.UsernamePopUp;
import Logic.DataBase.Constants;
import Logic.DataBase.DataBaseAgent;
import Logic.Game.GameState;
import Logic.LogicalAgent;
import Logic.Pages.*;

import javax.swing.*;

public class GraphicalAgent {

    public static GraphicalGamePage gamePage;

    public static String getUsername() {
        return UsernamePopUp.getUsername();
    }

    public static void newFirstPage() {
        new FirstPage();
    }

    public static void newGamePage(LogicalGamePage logicalGamePage) {
        gamePage = new GraphicalGamePage(logicalGamePage);
    }

    public static String saveGameName() {
        return (String) JOptionPane.showInputDialog(null,
                "Enter a name for this game:",
                Constants.gameName,
                JOptionPane.QUESTION_MESSAGE,
                Logic.DataBase.Constants.logo,
                null,
                null
        );
    }

    public static String loadGameName() {
        return (String) JOptionPane.showInputDialog(null,
                "Enter name:",
                "Load Game",
                JOptionPane.QUESTION_MESSAGE,
                Constants.logo,
                null,
                null
        );
    }


    public static void gameNotFound() {
        JOptionPane.showMessageDialog(null, "Game Not Found.", "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static boolean overwrite() {
        return JOptionPane.showConfirmDialog(null,
                "A game already exists with this name. Overwrite?",
                "Overwrite Warning",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE) == 0;
    }

}
