package Graphic.Pages.GamePage;

import Graphic.Pages.GamePage.GamePanel.GamePanel;
import Logic.DataBase.Constants;
import Logic.Game.*;
import Logic.Pages.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GraphicalGamePage extends JFrame {

    private GamePanel gamePanel;
    private StatusPanel statusPanel;


    public GraphicalGamePage(LogicalGamePage logicalGamePage){
        gamePanel = new GamePanel(logicalGamePage);
        statusPanel = new StatusPanel(logicalGamePage);
        this.add(gamePanel, BorderLayout.NORTH);
        this.add(statusPanel, BorderLayout.SOUTH);
        this.pack();
        this.setTitle(Constants.gameName);
        this.setResizable(false);
        this.setVisible(true);
        this.setLayout(null);
        this.setBackground(Color.WHITE);
        this.setIconImage(Logic.DataBase.Constants.logo.getImage());
        this.setLocationRelativeTo(null);
        this.addKeyListener(logicalGamePage.getKeyListener());
        logicalGamePage.setGraphicalGamePage(this);
        this.update(logicalGamePage.getGameState());
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
                logicalGamePage.quit();
            }
        });
    }

    public void update(GameState gameState){
        gamePanel.update(gameState);
        statusPanel.update(gameState);
    }

    public void pause() {
        gamePanel.pause();
    }

    public void unpause(){
        gamePanel.unpause();
    }
}
