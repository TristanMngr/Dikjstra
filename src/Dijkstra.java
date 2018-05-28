import java.util.ArrayList;
import java.util.List;

public class Dijkstra {
    private List<Vertex> visitedVertexes;
    private List<Vertex> unvisitedVertexes;
    private Vertex       currentVertex;

    private boolean run;
    private Vertex  endVertex;
    private Vertex  startVertex;
    private boolean findPath;

    public Dijkstra() {
        this.visitedVertexes = new ArrayList<>();
        this.unvisitedVertexes = new ArrayList<>();
    }

    public void run(Grid grid) {
        this.run = true;
        while (!unvisitedVertexes.isEmpty()) {
            this.currentVertex = this.searchVertexShortestPath(this.getUnvisitedVertexes());
            if (grid.getGridValue(this.currentVertex.getPosI(), this.currentVertex.getPosJ()) != 2) {
                grid.setGrid(this.currentVertex.getPosI(), this.currentVertex.getPosJ(), 4);
            }
            System.out.println("currentvertex>");
            System.out.println(this.currentVertex.getId());
            System.out.println("currentvertex<");
            List<Vertex> neighbourVertexes = this.currentVertex.searchNeighbourVertexes(this);

            for (int neighbour = 0; neighbour < neighbourVertexes.size(); neighbour++) {
                System.out.println("neighbour>");
                System.out.println(neighbourVertexes.get(neighbour).getId());
                System.out.println("neighbour<");
                neighbourVertexes.get(neighbour).calculateNewShortestPath(this.currentVertex);
            }

            this.swapUnvisitedToVisitedList(this.currentVertex);

            System.out.println("size>");
            System.out.println(this.unvisitedVertexes.size());
            System.out.println("size<");
        }

        findPath(endVertex, grid);

        if (this.findPath) {
            System.out.println("chemin trouvé");
            grid.setMessage("Chemin trouvé !");
        } else {
            System.out.println("fail");
            grid.setMessage("Gros Fail !");
        }
        this.run = false;
    }

    public void findPath(Vertex previous, Grid grid) {
        grid.setGrid(previous.getPosI(), previous.getPosJ(), 5);

        if (previous.getId() == startVertex.getId()) {
            this.findPath = true;
            return;
        }
        for (int vertex = 0; vertex < this.visitedVertexes.size(); vertex++) {
            if (previous.getPreviousId() == this.visitedVertexes.get(vertex).getId()) {
                findPath(this.visitedVertexes.get(vertex), grid);
                break;
            }
        }
        return;
    }


    public Vertex searchVertexFromCoordinates(int posI, int posJ, List<Vertex> listVertexes) {
        for (int item = 0; item < listVertexes.size(); item++) {
            if (posI == listVertexes.get(item).getPosI() && posJ == listVertexes.get(item).getPosJ()) {
                return listVertexes.get(item);
            }
        }
        return null;
    }

    public Vertex searchVertexShortestPath(List<Vertex> listVertexes) {
        int    shortestPath = 10000;
        Vertex vertex       = listVertexes.get(0);

        for (int item = 0; item < listVertexes.size(); item++) {
            if (listVertexes.get(item).getShortestPath() < shortestPath) {
                vertex = listVertexes.get(item);
                shortestPath = listVertexes.get(item).getShortestPath();
            }
        }
        return vertex;
    }

    public void swapUnvisitedToVisitedList(Vertex vertex) {
        int    vertexId = vertex.getId();
        Vertex vertexToMove;

        for (int item = 0; item < this.unvisitedVertexes.size(); item++) {
            vertexToMove = unvisitedVertexes.get(item);
            if (vertexToMove.getId() == vertexId) {
                this.unvisitedVertexes.remove(vertexToMove);
                this.visitedVertexes.add(vertexToMove);
                break;

            }
        }
    }

    public void addUnvisitedVertex(Vertex newVertex) {
        this.unvisitedVertexes.add(newVertex);
    }

    public void setCurrentVertex(Vertex currentVertex) {
        this.currentVertex = currentVertex;
    }

    public List<Vertex> getUnvisitedVertexes() {
        return this.unvisitedVertexes;
    }

    public void setEndVertex(Vertex endVertex) {
        this.endVertex = endVertex;
    }

    public void reinitDijkstra() {
        this.visitedVertexes = new ArrayList<>();
        this.unvisitedVertexes = new ArrayList<>();
        this.startVertex = null;
        this.endVertex = null;
        this.findPath = false;
        this.currentVertex = null;
    }

    public void setStartVertex(Vertex startVertex) {
        this.startVertex = startVertex;
    }

    public boolean isRun() {
        return run;
    }
}
