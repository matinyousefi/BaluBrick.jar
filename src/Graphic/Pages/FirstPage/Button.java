package Graphic.Pages.FirstPage;

import Logic.DataBase.Constants;

import javax.swing.*;

public class Button extends JButton {
    public Button(){
        this.setFocusable(false);
        this.setFont(Constants.buttonFont);
        this.setBackground(Constants.buttonColor);
        this.setBorder(Constants.buttonBorder);
        this.setPreferredSize(Constants.buttonDimension);
    }
}
