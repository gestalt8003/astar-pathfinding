package com.gestalt8003.astarpathfinding.gui;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {

    private ConfigPanel configPanel;
    private Visualization visualization;

    public GUI(Dimension size, String title) {
        // JFrame setup
        setSize(size);
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());

        // Init config panel
        configPanel = new ConfigPanel(new Dimension(getWidth()/4, getHeight()));
        add(configPanel, BorderLayout.EAST);

        // Init visualization
        visualization = new Visualization(new Dimension(3*getWidth()/4, getHeight()));
        add(visualization);

        // Make visible after done
        setVisible(true);
        visualization.initGraphics();
    }

    public ConfigPanel getConfigPanel() {
        return configPanel;
    }

    public Visualization getVisualization() {
        return visualization;
    }
}
