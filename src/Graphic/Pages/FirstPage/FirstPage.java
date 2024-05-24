package Graphic.Pages.FirstPage;

import Logic.DataBase.Constants;
import Logic.LogicalAgent;
import Logic.Pages.LogicalFirstPage;

import javax.swing.*;

public class FirstPage extends JFrame {

    public FirstPage() {
        this.setSize(600,800);
        this.setTitle(Constants.gameName);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        this.setLayout(null);
        this.setBackground(Logic.DataBase.Constants.firstPageBackgroundColor);
        this.setIconImage(Logic.DataBase.Constants.logo.getImage());
        this.setLocationRelativeTo(null);

        LogicalFirstPage logicalFirstPage = new LogicalFirstPage();
        int x = 190;

        JLabel username = new JLabel();
        this.add(username);
        username.setText(LogicalAgent.dataBaseAgent.getUsername());
        username.setFont(Logic.DataBase.Constants.usernameFont);
        username.setForeground(Logic.DataBase.Constants.usernameColor);
        username.setBounds(x, 100, 200, 30);
        username.setPreferredSize(Logic.DataBase.Constants.buttonDimension);
        username.setHorizontalAlignment(SwingConstants.CENTER);
        username.setVerticalAlignment(SwingConstants.CENTER);

        JLabel highScore = new JLabel();
        this.add(highScore);
        highScore.setText("Highest Score: ");
        highScore.setFont(Logic.DataBase.Constants.highScoreFont);
        highScore.setForeground(Logic.DataBase.Constants.highScoreColor);
        highScore.setBounds(x,200,200, 30);
        highScore.setPreferredSize(Logic.DataBase.Constants.buttonDimension);
        highScore.setHorizontalAlignment(SwingConstants.CENTER);
        highScore.setVerticalAlignment(SwingConstants.TOP);

        JLabel highScoreValue = new JLabel();
        this.add(highScoreValue);
        highScoreValue.setText(String.valueOf(LogicalAgent.dataBaseAgent.getHighestScore()));
        highScoreValue.setFont(Logic.DataBase.Constants.highScoreValueFont);
        highScoreValue.setForeground(Logic.DataBase.Constants.highScoreValueColor);
        highScoreValue.setBounds(x,225,200, 80);
        highScore.setPreferredSize(Logic.DataBase.Constants.buttonDimension);
        highScoreValue.setHorizontalAlignment(SwingConstants.CENTER);
        highScoreValue.setVerticalAlignment(SwingConstants.CENTER);

        Button loadGameButton = new Button();
        this.add(loadGameButton);
        loadGameButton.addActionListener(e -> logicalFirstPage.loadGame(this));
        loadGameButton.setText("Load Game");
        loadGameButton.setBounds(x, 310, 200, 100);

        Button newGameButton = new Button();
        this.add(newGameButton);
        newGameButton.addActionListener(e -> logicalFirstPage.newGame(this));
        newGameButton.setText("New Game");
        newGameButton.setBounds(x, 420, 200, 100);

        Button highScoresButton = new Button();
        this.add(highScoresButton);
        highScoresButton.addActionListener(e -> logicalFirstPage.highScores(this));
        highScoresButton.setText("High Scores");
        highScoresButton.setBounds(x, 530, 200, 100);
    }
}
