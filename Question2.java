// Kaamiel Isaacs
// 4129581 
// Pracical 2 Term 4
// Question 2

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Edge {

    int source;
    int destination;
    int weight;

    public Edge(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
}

public class Question2 {

    private static List<Edge> edges;
    private static String[] nodeLabels;

    public static void main(String[] args) {
        try {

            File file = new File("graph2_input.txt");
            int numVertices;
            // Read the header row to create the node label mapping
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                // Read the header row to create the node label mapping
                String header = reader.readLine();
                nodeLabels = header.split(" ");
                numVertices = nodeLabels.length;
                edges = new ArrayList<>();
                // Parse and populate the list of edges
                int row = 0;
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] values = line.split(" ");
                    for (int col = row + 1; col < numVertices; col++) {
                        int weight = Integer.parseInt(values[col]);
                        if (weight != 0) {
                            Edge edge = new Edge(row, col, weight);
                            edges.add(edge);
                        }
                    }
                    row++;
                }
            }

            // Sort the edges by weight in ascending order
            Collections.sort(edges, Comparator.comparingInt(e -> e.weight));

            List<Edge> minimumSpanningTree = kruskalsAlgorithm(numVertices);

            // Calculate the total weight of the MST
            int minCost = minimumSpanningTree.stream().mapToInt(edge -> edge.weight).sum();

            System.out.println("Minimum Cost of the Minimum Spanning Tree: " + minCost);
            printMSTEdges(minimumSpanningTree);

        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Invalid integer in input: " + e.getMessage());
        }
    }

    private static void printMSTEdges(List<Edge> minSpanningTree) {
        System.out.println("Edges in the Minimum Spanning Tree:");
        for (Edge edge : minSpanningTree) {
            System.out.println(nodeLabels[edge.source] + " -- " + nodeLabels[edge.destination] + " == " + edge.weight);
        }
    }

    static List<Edge> kruskalsAlgorithm(int numVertices) {
        List<Edge> minimumSpanningTree = new ArrayList<>();
        int[] parent = new int[numVertices];

        // Initialize the parent array
        for (int i = 0; i < numVertices; i++) {
            parent[i] = i;
        }

        int edgeCount = 0;
        int i = 0;

        // Apply Kruskal's algorithm
        while (edgeCount < numVertices - 1 && i < edges.size()) {
            Edge nextEdge = edges.get(i);
            int root1 = find(parent, nextEdge.source);
            int root2 = find(parent, nextEdge.destination);

            if (root1 != root2) {
                minimumSpanningTree.add(nextEdge);
                union(parent, root1, root2);
                edgeCount++;
            }
            i++;
        }

        return minimumSpanningTree;
    }

    static int find(int[] parent, int vertex) {
        if (parent[vertex] != vertex) {
            parent[vertex] = find(parent, parent[vertex]);
        }
        return parent[vertex];
    }

    static void union(int[] parent, int x, int y) {
        int rootX = find(parent, x);
        int rootY = find(parent, y);
        parent[rootX] = rootY;
    }

}

// References: https://www.geeksforgeeks.org/kruskals-minimum-spanning-tree-algorithm-greedy-algo-2/
// References: https://gist.github.com/02b91e2d182cc9b939d1
