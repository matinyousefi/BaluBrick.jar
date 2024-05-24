package Graphic.Pages.HighScorePage;

import Graphic.Pages.FirstPage.FirstPage;
import Logic.DataBase.Constants;
import Logic.LogicalAgent;

import javax.swing.*;
import java.awt.*;

public class HighScorePage extends JFrame {
    public HighScorePage() {
        this.setTitle(Constants.gameName);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        this.setLayout(new BorderLayout());
        this.setBackground(Constants.highScorePageColor);
        this.setIconImage(Constants.logo.getImage());
        this.setLocationRelativeTo(null);
        JPanel backPanel = new JPanel();
        backPanel.setPreferredSize(Constants.backPanelDimension);
        this.add(backPanel, BorderLayout.NORTH);
        backPanel.setVisible(true);
        JButton backButton = new JButton(){
            @Override
            public void paint(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.drawImage(Constants.backImage, 0, 0, null);
            }
        };
        backButton.setPreferredSize(Constants.backImageDimension);
        backButton.setVisible(true);
        backButton.addActionListener(e -> {
            this.dispose();
            new FirstPage();
        });
        backPanel.setBackground(Constants.highScorePageColor);
        backPanel.add(backButton);
        JPanel panel = new JPanel();
        panel.setPreferredSize(Constants.highScorePanelDimension);
        panel.setBackground(Constants.highScorePageColor);
        panel.add(new JLabel(){
            {
                setFont(Constants.highScoresPageFont);
                setText(LogicalAgent.dataBaseAgent.getTop20AsText());
                setForeground(Constants.highScoresText);
            }
        });
        this.add(panel, BorderLayout.SOUTH);
        this.add(backPanel, BorderLayout.NORTH);
        this.pack();
        this.setLocationRelativeTo(null);
    }

}
