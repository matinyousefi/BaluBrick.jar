package Graphic.UsernamePopUp;

import Logic.DataBase.Constants;

import javax.swing.*;

public class UsernamePopUp {
    public static String getUsername() {
        String username = (String) JOptionPane.showInputDialog(null,
                "Enter name:",
                Constants.gameName,
                JOptionPane.QUESTION_MESSAGE,
                Logic.DataBase.Constants.logo,
                null,
                null
        );
        return username;
    }
}
