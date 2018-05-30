package dijkstra.controller;

import dijkstra.Main;
import dijkstra.controller.Listener.CustomKeyListener;
import dijkstra.controller.Listener.CustomMouseListener;
import dijkstra.model.Dijkstra;
import dijkstra.model.Vertex;
import dijkstra.view.Paint;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Grid extends JPanel {
    public  int     nbPaths = Main.NB_PATHS;
    private int[][] grid    = new int[Main.NUMBER_CASE_HEIGH][Main.NUMBER_CASE_WIDTH];
    private String message;
    private int    keyCode;
    private int    sizeCaseHeigh, sizeCaseWidth;
    private Dijkstra dijkstra;
    private Color    randomColor[];

    private Paint paintComponent;

    private boolean  isStartNodeChoosen = false;
    private boolean  isEndNodeChoosen = false;

    private String startMessage = "A: start node / Z: wall node / E: end node / R: Reload grid / G: Generate paths / C: Change colors paths";


    /**
     * Constructor to init Listener
     */
    public Grid() {
        CustomMouseListener mouseListener = new CustomMouseListener(this);
        mouseListener.addMouseListener();

        CustomKeyListener keyListener = new CustomKeyListener(this);
        keyListener.addKeyListener();

        paintComponent = new Paint(this);

        
        this.sizeCaseHeigh = Main.GRID_HEIGH / Main.NUMBER_CASE_HEIGH;
        this.sizeCaseWidth = Main.GRID_WIDTH / Main.NUMBER_CASE_WIDTH;
        this.randomColor = generateRandomColor();

        this.message = startMessage;

        this.dijkstra = new Dijkstra();
        initGrid(dijkstra);


        setFocusable(true);
    }


    public void runDjikstra(Dijkstra dijkstra) {
        dijkstra.run(this, nbPaths);
    }


    /**
     * method to init the grid of the map
     *
     * @param dijkstra
     */
    public void initGrid(Dijkstra dijkstra) {
        dijkstra.reinitDijkstra();
        isStartNodeChoosen = false;
        isEndNodeChoosen = false;

        int id = 1;
        for (int i = 0; i < Main.NUMBER_CASE_HEIGH; i++) {
            for (int j = 0; j < Main.NUMBER_CASE_WIDTH; j++) {
                // create vertex
                Vertex newVertex = new Vertex(id, i, j);
                dijkstra.addUnvisitedVertex(newVertex);
                id++;

                // reinit grid

                this.grid[i][j] = 0;
            }
        }
        repaint();
    }


    /**
     * method to generate the grid + the wall (with weight 100)
     */
    public void generateRandomGrid() {
        dijkstra = new Dijkstra();

        int id = 1;
        for (int i = 0; i < Main.NUMBER_CASE_HEIGH; i++) {
            for (int j = 0; j < Main.NUMBER_CASE_WIDTH; j++) {
                // create vertex
                Vertex newVertex = new Vertex(id, i, j);
                dijkstra.addUnvisitedVertex(newVertex);
                id++;

                // 1/4 chance to have a wall
                int randomNumber = (int) ((Math.random() * 4) + 1);
                if (randomNumber == 2) {
                    this.grid[i][j] = randomNumber;
                    Vertex vertex = dijkstra.searchVertexFromCoordinates(i, j, dijkstra.getUnvisitedVertexes());
                    vertex.setWeight(100);
                } else {
                    this.grid[i][j] = 0;
                }
            }
        }
        repaint();
    }


    /**
     * method to return the case in posX, posY
     *
     * @param posX in pixels
     * @param posY in pixels
     * @return
     */
    public int[] getGridCase(int posX, int posY) {
        int[] gridCase = new int[2];

        gridCase[0] = (posX / this.sizeCaseWidth);
        gridCase[1] = (posY / this.sizeCaseHeigh);

        return gridCase;
    }


    /**
     * method to call the paint method from Paint
     * @param g
     */
    public void paintComponent(Graphics g) {
        paintComponent.paintGrid(g);
    }


    /**
     * method to generate Color for all paths
     *
     * @return
     */
    public Color[] generateRandomColor() {
        Color[] colors = new Color[nbPaths];
        for (int item = 0; item < nbPaths; item++) {
            Random r     = new Random();
            int    min   = 50;
            int    max   = 250;
            int    red   = r.nextInt(max - min) + min;
            int    green = r.nextInt(max - min) + min;
            int    blue  = r.nextInt(max - min) + min;
            colors[item] = new Color(red, green, blue);
        }
        return colors;
    }


    public void setGrid(int posI, int posJ, int caseProperty) {
        this.grid[posI][posJ] = caseProperty;


    }

    public int getGridValue(int posI, int posJ) {
        return this.grid[posI][posJ];
    }

    public int getNbPaths() {
        return nbPaths;
    }

    public void setNbPaths(int nbPaths) {
        this.nbPaths = nbPaths;
    }

    public int[][] getGrid() {
        return grid;
    }

    public void setGrid(int[][] grid) {
        this.grid = grid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(int keyCode) {
        this.keyCode = keyCode;
    }

    public int getSizeCaseHeigh() {
        return sizeCaseHeigh;
    }

    public void setSizeCaseHeigh(int sizeCaseHeigh) {
        this.sizeCaseHeigh = sizeCaseHeigh;
    }

    public int getSizeCaseWidth() {
        return sizeCaseWidth;
    }

    public void setSizeCaseWidth(int sizeCaseWidth) {
        this.sizeCaseWidth = sizeCaseWidth;
    }

    public Dijkstra getDijkstra() {
        return dijkstra;
    }

    public void setDijkstra(Dijkstra dijkstra) {
        this.dijkstra = dijkstra;
    }

    public Color[] getRandomColor() {
        return randomColor;
    }

    public void setRandomColor(Color[] randomColor) {
        this.randomColor = randomColor;
    }

    public boolean isStartNodeChoosen() {
        return isStartNodeChoosen;
    }

    public void setStartNodeChoosen(boolean startNodeChoosen) {
        isStartNodeChoosen = startNodeChoosen;
    }

    public boolean isEndNodeChoosen() {
        return isEndNodeChoosen;
    }

    public void setEndNodeChoosen(boolean endNodeChoosen) {
        isEndNodeChoosen = endNodeChoosen;
    }

    public String getStartMessage() {
        return startMessage;
    }

    public void setStartMessage(String startMessage) {
        this.startMessage = startMessage;
    }
}
