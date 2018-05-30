package dijkstra.model;

import dijkstra.Main;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
    private int id;
    private int shortestPath;
    private int previousId;
    private int weight;
    private int posI;
    private int posJ;


    public Vertex(int id, int posI, int posJ) {
        this.posI = posI;
        this.posJ = posJ;

        this.id = id;
        this.shortestPath = Integer.MAX_VALUE;
        this.weight = 1;
        this.previousId = 0;
    }

    /**
     * method to calculate the new shortest path form this.node to a node given
     * @param currentVertex
     */
    public void calculateNewShortestPath(Vertex currentVertex) {
        int newShortestPath = currentVertex.shortestPath + this.weight;

        if ((newShortestPath < this.shortestPath)) {
            this.shortestPath = newShortestPath;
            this.previousId = currentVertex.getId();
        }
    }

    /**
     * method to find all the neighbours nodes and avoid border
     * @param djikstra
     * @return
     */
    public List<Vertex> searchNeighbourVertexes(Dijkstra djikstra) {
        List<Vertex> vertexesList = new ArrayList<>();

        if (this.posJ + 1 <= Main.NUMBER_CASE_WIDTH - 1) {
            Vertex vertexRight = djikstra.searchVertexFromCoordinates(this.posI, this.posJ + 1, djikstra.getUnvisitedVertexes());
            if (vertexRight != null)
                vertexesList.add(vertexRight);
        }
        if (this.posJ - 1 >= 0) {
            Vertex vertexLeft = djikstra.searchVertexFromCoordinates(this.posI, this.posJ - 1, djikstra.getUnvisitedVertexes());
            if (vertexLeft != null)
                vertexesList.add(vertexLeft);
        }
        if (this.posI - 1 >= 0) {
            Vertex vertexUp = djikstra.searchVertexFromCoordinates(this.posI - 1, this.posJ, djikstra.getUnvisitedVertexes());
            if (vertexUp != null)
                vertexesList.add(vertexUp);
        }
        if (this.posI + 1 <= Main.NUMBER_CASE_HEIGH) {
            Vertex vertexDown = djikstra.searchVertexFromCoordinates(this.posI + 1, this.posJ, djikstra.getUnvisitedVertexes());
            if (vertexDown != null)
                vertexesList.add(vertexDown);
        }

        return vertexesList;
    }


    /**
     * method to reinit the this.node values
     */
    public void reinit() {
        this.shortestPath = Integer.MAX_VALUE;
        this.previousId = 0;
    }

    public int getId() {
        return id;
    }

    public int getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(int shortestPath) {
        this.shortestPath = shortestPath;
    }

    public int getPosI() {
        return posI;
    }

    public int getPosJ() {
        return posJ;
    }

    public int getPreviousId() {
        return previousId;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }
}
