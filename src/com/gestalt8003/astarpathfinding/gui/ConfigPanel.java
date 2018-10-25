package com.gestalt8003.astarpathfinding.gui;

import com.gestalt8003.astarpathfinding.algorithm.Algorithm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ConfigPanel extends JPanel {

    private Algorithm algorithm;

    public ConfigPanel(Dimension size) {
        // Setup
        setSize(size);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        //setBackground(Color.BLACK);
        setForeground(Color.WHITE);
        //setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE), "A* Algorithm Control Panel", TitledBorder.CENTER, TitledBorder.TOP, null, Color.white));

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Title
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        add(new JLabel("A* Algorithm"), gbc);

        // Control buttons
        JButton runOrPause = new JButton("Start");
        runOrPause.addActionListener((ActionEvent e) -> {
            if(algorithm.isStarted()) {
                if(algorithm.isRunning()) {
                    algorithm.pause();
                    runOrPause.setText("Play");
                } else {
                    algorithm.play();
                    runOrPause.setText("Pause");
                }
            } else {
                algorithm.start();
                runOrPause.setText("Pause");
            }
        });

        JButton step = new JButton("Step");
        step.addActionListener((ActionEvent e) -> {
            algorithm.step();
        });

        JButton restart = new JButton("Restart");
        restart.addActionListener((ActionEvent e) -> {
            runOrPause.setText("Start");
            algorithm.restart();
        });

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(runOrPause, gbc);
        gbc.gridx = 1;
        add(step, gbc);
        gbc.gridx = 2;
        add(restart, gbc);
    }

    public void attach(Algorithm algorithm) {
        this.algorithm = algorithm;
    }
}
