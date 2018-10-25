package com.gestalt8003.astarpathfinding;

import com.gestalt8003.astarpathfinding.algorithm.Algorithm;
import com.gestalt8003.astarpathfinding.gui.GUI;


public class AStarPathfinding {

    public static void main(String[] args) {
        System.out.println("A* Algorithm by gestalt8003.");

        GUI gui = new GUI(Config.WINDOW_SIZE, Config.WINDOW_TITLE);
        Algorithm algorithm = new Algorithm(gui.getVisualization());
        gui.getConfigPanel().attach(algorithm);
    }
}
