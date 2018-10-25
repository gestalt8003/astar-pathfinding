package com.gestalt8003.astarpathfinding.algorithm;

public class Node {

    private NodeType type;
    private Node parent;
    private int x, y;
    private int f, g, h;

    public Node(NodeType type, int x, int y) {
        this.type = type;
        this.x = x;
        this.y = y;
    }

    public NodeType getType() {
        return type;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }
}
