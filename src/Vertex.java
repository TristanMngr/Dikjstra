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
        this.shortestPath = 100000;
        this.weight = 1;
        this.previousId = 0;
    }

    public void calculateNewShortestPath(Vertex currentVertex) {
        int newShortestPath = currentVertex.shortestPath + this.weight;

        if ((newShortestPath < this.shortestPath) && this.weight != 100) {
            this.shortestPath = newShortestPath;
            this.previousId = currentVertex.getId();
        }
    }

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
