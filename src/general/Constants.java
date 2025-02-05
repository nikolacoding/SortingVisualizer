package general;

import java.awt.Dimension;

public final class Constants {

    // Window
    public static final Dimension WINDOW_DIMENSION = new Dimension(600, 200);
    public static final String WINDOW_TITLE = "Sorting Visualizer";

    // Menu
    public static final int NUM_ITEMS = 10; // obsolete
    public static final int MAX_NUM_ITEMS = 20;
    public static final String[] ALGORITHM_NAMES = {"Bubble-sort", "Selection-sort", "Insertion-sort", "Shell-sort", "Shaker-sort"};
    public static final String UP_ARROW = "↑";
    public static final String DOWN_ARROW = "↓";
    public static final float COLLECTION_FONT_SIZE = 20f;
    public static final float COLLECTION_FONT_SIZE_REDUCED = 15f;
    public static final int REDUCED_FONT_SIZE_THRESHOLD = 15;

    // General
    public static final long BASE_SIMULATION_SPEED = 1001;
    public static final long SPEED_UPDATE_INTERVAL = 50;
}
