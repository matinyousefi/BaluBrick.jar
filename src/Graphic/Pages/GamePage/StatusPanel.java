package Graphic.Pages.GamePage;

import Logic.DataBase.DataBaseAgent;
import Logic.DataBase.Constants;
import Logic.Game.*;
import Logic.LogicalAgent;
import Logic.Pages.*;

import javax.swing.*;
import java.awt.*;

public class StatusPanel extends JPanel {

    private JLabel scoreLabel;
    private JLabel highScoreLabel;
    private JLabel lifeLabel;

    public StatusPanel(LogicalGamePage logicalGamePage) {
        this.setPreferredSize(Constants.statusPanelDimension);
        this.setBackground(Constants.statusPanelBackgroundColor);
        scoreLabel = new JLabel();
        this.add(scoreLabel, BorderLayout.EAST);
        scoreLabel.setPreferredSize(Constants.statusPanelLabelDimension);
        scoreLabel.setText("Score: " + logicalGamePage.getGameState().getScore());
        scoreLabel.setFont(Constants.statusPanelLabelFont);
        scoreLabel.setBackground(Constants.statusPanelBackgroundColor);
        scoreLabel.setForeground(Constants.scoreLabelFontColor);
        scoreLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        scoreLabel.setVerticalTextPosition(SwingConstants.CENTER);

        highScoreLabel = new JLabel();
        this.add(highScoreLabel, BorderLayout.CENTER);
        highScoreLabel.setPreferredSize(Constants.statusPanelLabelDimension);
        highScoreLabel.setText("Record: " + LogicalAgent.dataBaseAgent.getHighestScore());
        highScoreLabel.setFont(Constants.statusPanelLabelFont);
        highScoreLabel.setBackground(Constants.statusPanelBackgroundColor);
        highScoreLabel.setForeground(Constants.scoreLabelFontColor);
        highScoreLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        highScoreLabel.setVerticalTextPosition(SwingConstants.CENTER);

        lifeLabel = new JLabel();
        this.add(lifeLabel, BorderLayout.WEST);
        lifeLabel.setPreferredSize(Constants.statusPanelLifeDimension);
        lifeLabel.setText("3");
        lifeLabel.setIcon(Constants.ball);
        lifeLabel.setFont(Constants.statusPanelLabelFont);
        lifeLabel.setBackground(Constants.statusPanelBackgroundColor);
        lifeLabel.setForeground(Constants.scoreLabelFontColor);
        lifeLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
        lifeLabel.setVerticalTextPosition(SwingConstants.CENTER);
    }

    public void update(GameState gameState){
        highScoreLabel.setText("Record: " + LogicalAgent.dataBaseAgent.getHighestScore());
        scoreLabel.setText(" Score: " + gameState.getScore());
        lifeLabel.setText(String.valueOf(gameState.getLife()));
    }
}
