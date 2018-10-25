package com.gestalt8003.astarpathfinding.file;

import com.gestalt8003.astarpathfinding.algorithm.Node;
import com.gestalt8003.astarpathfinding.algorithm.NodeType;

import java.io.*;

public class MapLoader {

    public static final int EMPTY = 0;
    public static final int  WALL = 1;
    public static final int START = 2;
    public static final int  GOAL = 3;

    public static Node[][] loadMapFromFile(File file) { // TODO LOAD MAP FROM FILE
        Node[][] nodes = null;
        // Load file reader
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return nodes;
    }

    public static Node[][] loadMapFromFile(String path) {
        return loadMapFromFile(new File(path));
    }

    public static Node[][] loadDefaultMap() {
        Node[][] nodes = new Node[10][10];
        for(int x = 0; x < nodes.length; x++) {
            for(int y = 0; y < nodes[x].length; y++) {
                nodes[x][y] = new Node(NodeType.WALKABLE, x, y);
            }
        }
        nodes[1][1] = new Node(NodeType.START, 1, 1);
        nodes[8][8] = new Node(NodeType.GOAL, 8, 8);
        return nodes;
    }
}
