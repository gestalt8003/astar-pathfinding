package com.gestalt8003.astarpathfinding;

import java.awt.*;

public class Config {
    public static final Dimension    WINDOW_SIZE = new Dimension(1280, 720);
    public static final String      WINDOW_TITLE = "A* Pathfinding";

    public static final int            MOVE_COST = 10;

    public static final int         NODE_MARGINS = 2;
    public static final Color NOT_WALKABLE_COLOR = Color.BLACK;
    public static final Color     WALKABLE_COLOR = Color.WHITE;
    public static final Color        START_COLOR = Color.MAGENTA;
    public static final Color         GOAL_COLOR = Color.GREEN;
    public static final Color   BACKGROUND_COLOR = Color.DARK_GRAY;
    public static final Color         OPEN_COLOR = Color.YELLOW;
    public static final Color       CLOSED_COLOR = Color.RED;
    public static final Color         PATH_COLOR = Color.CYAN;
}
