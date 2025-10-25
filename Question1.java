// Kaamiel Isaacs
// 4129581 
// Pracical 2 Term 4
// Question 1

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Question1 {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("graph1_input.txt"));
            int[][] adjacencyMatrix = createAdjacencyMatrix(reader);
            int numComponents = countConnectedComponents(adjacencyMatrix);

            System.out.println("Number of connected components: " + numComponents);
            System.out.println("Edge list:");
            printEdgeList(adjacencyMatrix);

        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }

    private static int[][] createAdjacencyMatrix(BufferedReader reader) throws IOException {
        List<String> lines = new ArrayList<>();
        String line;

        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }

        int numVertices = lines.size();
        int[][] adjacencyMatrix = new int[numVertices][numVertices];

        for (int i = 0; i < numVertices; i++) {
            String[] values = lines.get(i).split(" ");

            if (values.length != numVertices) {
                System.err.println("Invalid input format. Number of values in the row does not match the number of vertices.");
                System.exit(1);
            }

            for (int j = 0; j < numVertices; j++) {
                try {
                    adjacencyMatrix[i][j] = Integer.parseInt(values[j]);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid integer in input: " + values[j]);
                    System.exit(1);
                }
            }
        }
        return adjacencyMatrix;
    }

    private static void depthFirstSearch(int[][] adjacencyMatrix, boolean[] visited, int vertex) {
        visited[vertex] = true;
        int numVertices = adjacencyMatrix.length;

        for (int i = 0; i < numVertices; i++) {
            if (!visited[i] && adjacencyMatrix[vertex][i] > 0) {
                depthFirstSearch(adjacencyMatrix, visited, i);
            }
        }
    }

    private static int countConnectedComponents(int[][] adjacencyMatrix) {
        int vertices = adjacencyMatrix.length;
        boolean[] visited = new boolean[vertices];
        int numComponents = 0;

        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                numComponents++;
                depthFirstSearch(adjacencyMatrix, visited, i);
            }
        }

        return numComponents;
    }

    private static void printEdgeList(int[][] adjacencyMatrix) {
        int numVertices = adjacencyMatrix.length;

        for (int i = 0; i < numVertices; i++) {
            for (int j = i + 1; j < numVertices; j++) {
                if (adjacencyMatrix[i][j] > 0) {
                    System.out.println(i + " - " + j);
                }
            }
        }
    }
}

// References: https://www.geeksforgeeks.org/kruskals-minimum-spanning-tree-algorithm-greedy-algo-2/
// References: https://gist.github.com/02b91e2d182cc9b939d1