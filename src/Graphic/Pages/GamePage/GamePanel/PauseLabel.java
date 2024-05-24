package Graphic.Pages.GamePage.GamePanel;

import Logic.DataBase.Constants;
import Logic.Pages.LogicalGamePage;

import javax.swing.*;

public class PauseLabel extends JLabel {

    public PauseLabel(LogicalGamePage logicalGamePage){
        this.setSize(Constants.pauseLabelDimension);
        this.setVisible(true);

        JButton resumeButton = new JButton();
        this.add(resumeButton);
        resumeButton.setFocusable(false);
        resumeButton.setSize(Constants.pauseLabelButtonDimension);
        resumeButton.setLocation(100, 100);
        resumeButton.setBorder(Constants.pauseLabelButtonBorder);
        resumeButton.setFont(Constants.pauseLabelButtonFont);
        resumeButton.setBackground(Constants.pauseLabelButtonColor);
        resumeButton.addActionListener(e -> logicalGamePage.unpause());
        resumeButton.setText("Play");

        JButton restart = new JButton();
        this.add(restart);
        restart.setFocusable(false);
        restart.setSize(Constants.pauseLabelButtonDimension);
        restart.setLocation(100, 220);
        restart.setBorder(Constants.pauseLabelButtonBorder);
        restart.setFont(Constants.pauseLabelButtonFont);
        restart.setBackground(Constants.pauseLabelButtonColor);
        restart.addActionListener(e -> logicalGamePage.restart());
        restart.setText("Restart");

        JButton saveButton = new JButton();
        this.add(saveButton);
        saveButton.setFocusable(false);
        saveButton.setSize(Constants.pauseLabelButtonDimension);
        saveButton.setLocation(100, 340);
        saveButton.setBorder(Constants.pauseLabelButtonBorder);
        saveButton.setFont(Constants.pauseLabelButtonFont);
        saveButton.setBackground(Constants.pauseLabelButtonColor);
        saveButton.addActionListener(e -> logicalGamePage.save());
        saveButton.setText("Save");

        JButton Quit = new JButton();
        this.add(Quit);
        Quit.setFocusable(false);
        Quit.setSize(Constants.pauseLabelButtonDimension);
        Quit.setLocation(100, 460);
        Quit.setBorder(Constants.pauseLabelButtonBorder);
        Quit.setFont(Constants.pauseLabelButtonFont);
        Quit.setBackground(Constants.pauseLabelButtonColor);
        Quit.addActionListener(e -> logicalGamePage.mainMenu());
        Quit.setText("Main Menu");
    }
}
