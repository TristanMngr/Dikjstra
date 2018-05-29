import javafx.scene.input.KeyCode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Grid extends JPanel {
    public  int     nbPaths = Main.NB_PATHS;
    private int[][] grid    = new int[Main.NUMBER_CASE_HEIGH][Main.NUMBER_CASE_WIDTH];
    private String message;
    private int    keyCode;
    private int    sizeCaseHeigh, sizeCaseWidth;
    private Dijkstra dijkstra;
    private Color    randomColor[];

    /**
     * Constructor to init Listener
     */
    public Grid() {
        this.sizeCaseHeigh = Main.GRID_HEIGH / Main.NUMBER_CASE_HEIGH;
        this.sizeCaseWidth = Main.GRID_WIDTH / Main.NUMBER_CASE_WIDTH;
        this.randomColor = generateRandomColor();

        this.message = "A: start; Z: node; E: end; R: Reload; G: Generate";

        this.dijkstra = new Dijkstra();
        initGrid(dijkstra);

        /**
         * Listener Mouse
         */
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int    gridCase[] = getGridCase(e.getY(), e.getX());
                Vertex vertex     = dijkstra.searchVertexFromCoordinates(gridCase[0], gridCase[1], dijkstra.getUnvisitedVertexes());
                if (keyCode == KeyEvent.VK_A) {
                    grid[gridCase[0]][gridCase[1]] = 1;

                    dijkstra.setStartVertex(vertex);
                    dijkstra.setCurrentVertex(vertex);
                    vertex.setShortestPath(0);
                }
                if (keyCode == KeyEvent.VK_Z) {
                    grid[gridCase[0]][gridCase[1]] = 2;

                    vertex.setWeight(100);
                }
                if (keyCode == KeyEvent.VK_E) {
                    grid[gridCase[0]][gridCase[1]] = 3;

                    dijkstra.setEndVertex(vertex);
                }
                repaint();
            }
        });

        /**
         * Listener Key
         */
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_A) {
                    message = "Choose Start Point";
                } else if (keyCode == KeyEvent.VK_Z) {
                    message = "Choose Node";
                } else if (keyCode == KeyEvent.VK_E) {
                    message = "Choose End";
                } else if (keyCode == KeyEvent.VK_R) {
                    initGrid(dijkstra);
                    message = "Grid reinit";
                } else if (keyCode == KeyEvent.VK_S) {
                    runDjikstra(dijkstra);
                } else if (keyCode == KeyEvent.VK_G) {
                    generateRandomGrid();
                } else if (keyCode == KeyEvent.VK_C) {
                    randomColor = generateRandomColor();
                }
                
                repaint();
            }
        });

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
     * Override method to paint the map and the path
     *
     * @param g
     */

    public void paintComponent(Graphics g) {
        for (int i = 0; i < Main.NUMBER_CASE_HEIGH; i++) {
            for (int j = 0; j < Main.NUMBER_CASE_WIDTH; j++) {
                g.setColor(Color.BLACK);
                g.drawRect(j * this.sizeCaseWidth, i * this.sizeCaseHeigh, this.sizeCaseHeigh, this.sizeCaseHeigh);

                // GREY
                if (grid[i][j] == 0) {
                    g.setColor(new Color(240, 240, 240));
                    g.fillRect(j * this.sizeCaseWidth, i * this.sizeCaseHeigh, this.sizeCaseHeigh, this.sizeCaseHeigh);
                }

                // GREEN
                if (grid[i][j] == 1) {
                    g.setColor(new Color(0, 100, 0));
                    g.fillRect(j * this.sizeCaseWidth, i * this.sizeCaseHeigh, this.sizeCaseHeigh, this.sizeCaseHeigh);
                }

                // RED
                if (grid[i][j] == 2) {
                    g.setColor(new Color(170, 0, 0));
                    g.fillRect(j * this.sizeCaseWidth, i * this.sizeCaseHeigh, this.sizeCaseHeigh, this.sizeCaseHeigh);
                }

                // BLUE
                if (grid[i][j] == 3) {
                    g.setColor(new Color(0, 100, 150));
                    g.fillRect(j * this.sizeCaseWidth, i * this.sizeCaseHeigh, this.sizeCaseHeigh, this.sizeCaseHeigh);
                }

                // DARK GREY
                if (grid[i][j] == 4) {
                    g.setColor(Color.DARK_GRAY);
                    g.fillRect(j * this.sizeCaseWidth, i * this.sizeCaseHeigh, this.sizeCaseHeigh, this.sizeCaseHeigh);
                }


                int nbCase = 4;

                for (int item = 0; item < nbPaths; item++) {
                    nbCase++;

                    if (grid[i][j] == nbCase) {
                        g.setColor(randomColor[item]);
                        g.fillRect(j * this.sizeCaseWidth, i * this.sizeCaseHeigh, this.sizeCaseHeigh, this.sizeCaseHeigh);
                    }
                }
                g.setColor(Color.BLACK);
                g.drawString(message, 25, 25);
            }
        }
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
}
