package com.gestalt8003.astarpathfinding.gui;

import com.gestalt8003.astarpathfinding.Config;
import com.gestalt8003.astarpathfinding.algorithm.Node;
import com.gestalt8003.astarpathfinding.algorithm.NodeType;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

public class Visualization extends Canvas {
    private BufferStrategy bs;
    private Graphics g;

    public Visualization(Dimension size) {
        setBackground(Color.BLACK);
        setSize(size);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
    }

    public void initGraphics() {
        System.out.println("CANVAS READY, SIZE: (" + getWidth() + ", " + getHeight() + ").");

        createBufferStrategy(2);
        bs = getBufferStrategy();
        g = bs.getDrawGraphics();
    }

    public void drawNodes(Node[][] map, ArrayList<Node> open, ArrayList<Node> closed) {
        // Get Graphics
        g = bs.getDrawGraphics();
        // Calculate Node width/height
        int width = (getWidth() - Config.NODE_MARGINS*map.length)/map.length;
        int height = (getHeight() - Config.NODE_MARGINS*map[0].length)/map[0].length;

        // Draw background
        g.setColor(Config.BACKGROUND_COLOR);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Draw map first
        for(int x = 0; x < map.length; x++) {
            for(int y = 0; y < map[x].length; y++) {
                Node node = map[x][y];
                if(node.getType() == NodeType.WALKABLE) {
                    g.setColor(Config.WALKABLE_COLOR);
                } else if(node.getType() == NodeType.NOT_WALKABLE) {
                    g.setColor(Config.NOT_WALKABLE_COLOR);
                } else if(node.getType() == NodeType.START) {
                    g.setColor(Config.START_COLOR);
                } else if(node.getType() == NodeType.GOAL) {
                    g.setColor(Config.GOAL_COLOR);
                }
                if(open.contains(node)) {
                    g.setColor(Config.OPEN_COLOR);
                } else if(closed.contains(node)) {
                    g.setColor(Config.CLOSED_COLOR);
                }
                g.fillRect(x*(width+Config.NODE_MARGINS), y*(height+Config.NODE_MARGINS), width, height);
            }
        }

        bs.show();
    }

    public void drawPath(Node[][] map, ArrayList<Node> path) {
        // Get Graphics
        g = bs.getDrawGraphics();
        // Calculate Node width/height
        int width = (getWidth() - Config.NODE_MARGINS*map.length)/map.length;
        int height = (getHeight() - Config.NODE_MARGINS*map[0].length)/map[0].length;

        // Draw background
        g.setColor(Config.BACKGROUND_COLOR);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Draw map first
        for(int x = 0; x < map.length; x++) {
            for(int y = 0; y < map[x].length; y++) {
                Node node = map[x][y];
                if(node.getType() == NodeType.WALKABLE) {
                    g.setColor(Config.WALKABLE_COLOR);
                } else if(node.getType() == NodeType.NOT_WALKABLE) {
                    g.setColor(Config.NOT_WALKABLE_COLOR);
                } else if(node.getType() == NodeType.START) {
                    g.setColor(Config.START_COLOR);
                } else if(node.getType() == NodeType.GOAL) {
                    g.setColor(Config.GOAL_COLOR);
                }
                g.fillRect(x*(width+Config.NODE_MARGINS), y*(height+Config.NODE_MARGINS), width, height);
            }
        }
        g.setColor(Config.PATH_COLOR);
        for(Node node: path) {
            g.fillRect(node.getX()*(width+Config.NODE_MARGINS), node.getY()*(height+Config.NODE_MARGINS), width, height);
        }

        bs.show();
    }
}
