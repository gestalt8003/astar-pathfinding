package com.gestalt8003.astarpathfinding.algorithm;

import com.gestalt8003.astarpathfinding.Config;
import com.gestalt8003.astarpathfinding.file.MapLoader;
import com.gestalt8003.astarpathfinding.gui.Visualization;

import javax.swing.*;
import java.util.ArrayList;

public class Algorithm implements Runnable {

    private Visualization visualization;

    // Loop
    private int speed = 5;
    private boolean quit = false;
    private boolean restarting = false;
    private boolean started = false;
    private boolean running = false;
    private boolean finishing = false;
    private boolean done = false;
    private long frameTimer = System.currentTimeMillis();

    // Algorithm
    private String currentNodeMapPath;
    private Node[][] nodes;
    private ArrayList<Node> open;
    private ArrayList<Node> closed;
    private Node start;
    private Node goal;

    public Algorithm(Visualization visualization) {
        this.visualization = visualization;

        start();
        running = false;
        new Thread(this).start();
    }

    public void start(String nodeMapPath) {
        nodes = MapLoader.loadMapFromFile(nodeMapPath);
        currentNodeMapPath = nodeMapPath;

        started = true;
        running = true;
    }

    public void start() {
        if(currentNodeMapPath != null) {
            nodes = MapLoader.loadMapFromFile(currentNodeMapPath);
        } else {
            nodes = MapLoader.loadDefaultMap();
        }

        started = true;
        running = true;
    }

    public void play() {
        running = true;
        System.out.println("Algorithm running.");
    }

    public void pause() {
        running = false;
        System.out.println("Algorithm paused.");
    }

    public void restart() {
        System.out.println("Algorithm restarting...");
        pause();
        restarting = true;
        started = false;
        done = false;
        start();
        running = false;
        System.out.println("Algorithm restarted.\n--------------------");
    }

    public void restart(String newNodeMapPath) {
        System.out.println("Algorithm restarting...");
        pause();
        restarting = true;
        started = false;
        done = false;
        start(newNodeMapPath);
        running = false;
        System.out.println("Algorithm restarted.\n--------------------");
    }

    @Override
    public void run() {
        while(!quit) {
            init();
            System.out.println("ALGORITHM READY.");
            while(!done) {
                if(!running) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    if (System.currentTimeMillis() - frameTimer > 1000 / speed) {
                        frameTimer = System.currentTimeMillis();
                        if(finishing) {
                            finish(goal);
                        } else {
                            step();
                        }
                    }
                }
                if(restarting) {
                    restarting = false;
                    break;
                }
            }
        }
    }

    private void init() {
        // Initialize open/closed lists
        open = new ArrayList<>();
        closed = new ArrayList<>();
        // Init start and goal point
        for (Node[] col : nodes) {
            for (Node node : col) {
                if (node.getType() == NodeType.START) {
                    start = node;
                } else if (node.getType() == NodeType.GOAL) {
                    goal = node;
                }
            }
        }
        // Add start point to open list
        start.setG(0);
        start.setH(Config.MOVE_COST * calcManhattanDistance(start, goal));
        start.setF(start.getG() + start.getH());
        open.add(start);
        visualization.drawNodes(nodes, open, closed);
    }

    private int calcManhattanDistance(Node node, Node goal) {
        return Math.abs(goal.getX() - node.getX()) + Math.abs(goal.getY() - node.getY());
    }

    public void step() {
        // LOGIC
        if(!open.isEmpty()) {
            // Get node from open list with lowest F cost
            Node current = null;
            Node lowest = null;
            ArrayList<Node> lowests = new ArrayList<>();
                // Find the lowest F cost
            for(Node node: open) {
                if(lowest == null || node.getF() < lowest.getF()) {
                    lowest = node;
                    lowests.add(lowest);
                }
            }
                // Find other nodes with the same F cost and compare their H costs
                // Lower H cost means closer to the goal
            for(Node node: open) {
                if(node.getF() == lowest.getF()) {
                    lowests.add(node);
                }
            }
            for(Node node: lowests) {
                if(current == null || node.getH() < current.getH()) {
                    current = node;
                }
            }
            // If we are looking at the goal node, finish the algorithm
            if(current == goal) {
                finish(goal);
            }
            // Move node to closed list
            open.remove(current);
            closed.add(current);
            // Find neighbors of node
            for(Node neighbor: findNeighbors(nodes, current)) {
                // Check if neighbor is closed
                if(!closed.contains(neighbor)) {
                    // Calculate neighbor's F cost
                    neighbor.setH(Config.MOVE_COST * calcManhattanDistance(neighbor, goal));
                    neighbor.setF(neighbor.getG() + neighbor.getH());
                    if(!open.contains(neighbor)) {
                        // If neighbor is not in open list, add it
                        open.add(neighbor);
                    } else {
                        // If neighbor is in open list, check if new path is less costly and update G cost if so
                        Node openNeighbor = open.get(open.indexOf(neighbor));
                        if(neighbor.getG() < openNeighbor.getG()) {
                            openNeighbor.setG(neighbor.getG());
                            openNeighbor.setParent(neighbor.getParent());
                        }
                    }
                }
            }
        } else {
            // Finish if nodes are exhausted
            finish(null);
        }
        // UPDATE VISUALIZATION
        visualization.drawNodes(nodes, open, closed);
    }

    private ArrayList<Node> findNeighbors(Node[][] map, Node current) {
        ArrayList<Node> neighbors = new ArrayList<>();
        System.out.println(current.getType() + ": (" + current.getX() + ", " + current.getY() + ")");
        for(int x = current.getX()-1; x <= current.getX()+1; x++) {
            for(int y = current.getY()-1; y <= current.getY()+1; y++) {
                if (!(x < 0 || y < 0 || x >= map.length || y >= map[0].length || !(x == current.getX() || y == current.getY()))) {
                    Node neighbor = map[x][y];
                    // Check if node is walkable or not
                    if (!(neighbor.getType() == NodeType.NOT_WALKABLE)) {
                        neighbors.add(neighbor);
                        neighbor.setG(current.getG() + Config.MOVE_COST);
                        neighbor.setParent(current);
                    }
                }
            }
        }
        return neighbors;
    }

    private void finish(Node goal) {
        finishing = true;
        if(goal == null) {
            System.out.println("No path to goal.");
            JOptionPane.showMessageDialog(visualization, "No path to goal.");
        } else {
            System.out.println("Path to goal found.");
        }
        ArrayList<Node> path = generatePath(goal);
        visualization.drawPath(nodes, path);
    }

    private ArrayList<Node> generatePath(Node goal) {
        ArrayList<Node> path = new ArrayList<>();
        Node parent = goal.getParent();
        path.add(goal);
        while(parent != null) {
            path.add(parent);
            parent = parent.getParent();
        }
        return path;
    }

    public boolean isRunning() {
        return running;
    }

    public boolean isStarted() {
        return started;
    }
}
