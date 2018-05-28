import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Grid extends JPanel {
    public final static int MAX_NODES_START = 1;
    public final static int MAX_NODES       = 100;
    public final static int MAX_NODES_END   = 1;


    private int[][] grid = new int[Main.NUMBER_CASE_HEIGH][Main.NUMBER_CASE_WIDTH];
    private String message;
    private int    keyCode;
    private int    nodesStart, nodes, nodesEnd;
    private int sizeCaseHeigh, sizeCaseWidth;
    private Dijkstra dijkstra;


    public Grid() {
        this.sizeCaseHeigh = Main.GRID_HEIGH / Main.NUMBER_CASE_HEIGH;
        this.sizeCaseWidth = Main.GRID_WIDTH / Main.NUMBER_CASE_WIDTH;

        this.message = "A: start; Z: node; E: end; R: Reload; G: Generate";

        this.dijkstra = new Dijkstra();
        initGrid(dijkstra);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int    gridCase[] = getGridCase(e.getY(), e.getX());
                Vertex vertex     = dijkstra.searchVertexFromCoordinates(gridCase[0], gridCase[1], dijkstra.getUnvisitedVertexes());
                if (keyCode == KeyEvent.VK_A && nodesStart <= MAX_NODES_START) {
                    grid[gridCase[0]][gridCase[1]] = 1;

                    dijkstra.setStartVertex(vertex);
                    dijkstra.setCurrentVertex(vertex);
                    vertex.setShortestPath(0);
                    nodesStart++;
                }
                if (keyCode == KeyEvent.VK_Z && nodes <= MAX_NODES) {
                    grid[gridCase[0]][gridCase[1]] = 2;

                    vertex.setWeight(100);
                    nodes++;
                }
                if (keyCode == KeyEvent.VK_E && nodesEnd <= MAX_NODES_END) {
                    grid[gridCase[0]][gridCase[1]] = 3;

                    dijkstra.setEndVertex(vertex);

                    nodesEnd++;
                }
                repaint();
            }
        });

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
                    generateRandomGrid(dijkstra);
                }
                repaint();
            }
        });

        setFocusable(true);
    }


    public void runDjikstra(Dijkstra dijkstra) {
        dijkstra.run(this);
    }


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
                this.nodesStart = 1;
                this.nodes = 1;
                this.nodesEnd = 1;
            }
        }
        repaint();
    }

    public void generateRandomGrid(Dijkstra dijkstra) {
        dijkstra.reinitDijkstra();

        int id = 1;
        for (int i = 0; i < Main.NUMBER_CASE_HEIGH; i++) {
            for (int j = 0; j < Main.NUMBER_CASE_WIDTH; j++) {
                // create vertex
                Vertex newVertex = new Vertex(id, i, j);
                dijkstra.addUnvisitedVertex(newVertex);
                id++;

                // reinit grid

                int randomNumber = (int) ((Math.random() * 3) + 1);
                if (randomNumber == 2) {
                    this.grid[i][j] = randomNumber;
                    Vertex vertex = dijkstra.searchVertexFromCoordinates(i, j, dijkstra.getUnvisitedVertexes());
                    vertex.setWeight(100);
                } else {
                    this.grid[i][j] = 0;
                }

                this.nodesStart = 1;
                this.nodes = 1;
                this.nodesEnd = 1;
            }
        }
        repaint();
    }

    public int[] getGridCase(int posX, int posY) {
        int[] gridCase = new int[2];

        gridCase[0] = (posX / this.sizeCaseWidth);
        gridCase[1] = (posY / this.sizeCaseHeigh);

        return gridCase;
    }

    public void paintComponent(Graphics g) {
        for (int i = 0; i < Main.NUMBER_CASE_HEIGH; i++) {
            for (int j = 0; j < Main.NUMBER_CASE_WIDTH; j++) {
                g.setColor(Color.BLACK);
                g.drawRect(j * this.sizeCaseWidth, i * this.sizeCaseHeigh, this.sizeCaseHeigh, this.sizeCaseHeigh);

                if (grid[i][j] == 1) {
                    g.setColor(Color.GREEN);
                    g.fillRect(j * this.sizeCaseWidth, i * this.sizeCaseHeigh, this.sizeCaseHeigh, this.sizeCaseHeigh);
                }
                if (grid[i][j] == 2) {
                    g.setColor(Color.RED);
                    g.fillRect(j * this.sizeCaseWidth, i * this.sizeCaseHeigh, this.sizeCaseHeigh, this.sizeCaseHeigh);
                }
                if (grid[i][j] == 3) {
                    g.setColor(Color.ORANGE);
                    g.fillRect(j * this.sizeCaseWidth, i * this.sizeCaseHeigh, this.sizeCaseHeigh, this.sizeCaseHeigh);
                }
                if (grid[i][j] == 4) {
                    g.setColor(Color.DARK_GRAY);
                    g.fillRect(j * this.sizeCaseWidth, i * this.sizeCaseHeigh, this.sizeCaseHeigh, this.sizeCaseHeigh);
                }
                if (grid[i][j] == 5) {
                    g.setColor(Color.PINK);
                    g.fillRect(j * this.sizeCaseWidth, i * this.sizeCaseHeigh, this.sizeCaseHeigh, this.sizeCaseHeigh);
                }

                g.setColor(Color.BLACK);
                g.drawString(message, 25, 25);
            }
        }
    }

    public void setGrid(int posI, int posJ, int caseProperty) {
        this.grid[posI][posJ] = caseProperty;


    }

    public int getGridValue(int posI, int posJ) {
        return this.grid[posI][posJ];
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
