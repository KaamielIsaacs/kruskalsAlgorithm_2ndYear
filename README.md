CSC212: Practical 2 (Term 4) — Graph Theory Algorithms

This repository contains Java implementations of fundamental Graph Theory algorithms, developed as part of a practical assignment for CSC212.

The project focuses on two main problems:
	1.	Graph Connectivity – Determining connected components and generating the edge list.
	2.	Minimum Spanning Tree (MST) – Implementing Kruskal’s Algorithm using the Disjoint Set Union (DSU) structure.

⸻

1. Project Structure

All input files must be placed in the same directory as the Java source files before running the program.

File	Description	Language / Type
Question1.java	Determines connected components using DFS and generates an edge list from an adjacency matrix.	Java
graph1_input.txt	Input file (unweighted adjacency matrix) for Question1.java.	Text
Question2.java	Finds the Minimum Spanning Tree (MST) using Kruskal’s Algorithm and DSU.	Java
graph2_input.txt	Input file (weighted adjacency matrix with header labels) for Question2.java.	Text


⸻

2. Question 1 — Connected Components & Edge List

The Question1.java file processes an unweighted graph and identifies all connected components using the Depth-First Search (DFS) algorithm.

Implementation Details
	•	Input Reading: Reads the adjacency matrix from graph1_input.txt.
	•	DFS Traversal: Recursively explores all connected vertices.
	•	Component Counting: Each DFS call on an unvisited node represents a new connected component.
	•	Edge List Generation: Extracts all unique edges from the upper triangle of the adjacency matrix.

Example Output Format

Number of connected components: 2
Edge list:
1 - 2
2 - 3
4 - 5

⸻

3. Question 2 — Kruskal’s Algorithm (MST)

The Question2.java file processes a weighted graph to determine its Minimum Spanning Tree using Kruskal’s Algorithm.

Implementation Details
	•	Input Reading: Parses a weighted adjacency matrix with vertex labels from graph2_input.txt.
	•	Edge Extraction: Stores all valid edges (non-zero weights) with their respective labels.
	•	Sorting: Edges are sorted in ascending order based on their weights.
	•	Disjoint Set Union (DSU):
	•	find() implements Path Compression for efficient lookup.
	•	union() merges sets if the selected edge doesn’t form a cycle.
	•	Output: Displays each MST edge with its weight and the total MST cost.

Example Output Format

Edges in MST:
A - B : 2
A - D : 3
B - C : 4
Total cost of MST: 9

⸻

4. Compilation and Execution

Ensure all files are in the same folder, and Java is installed on your system.

Compile All Files

javac Question1.java Question2.java

Run Question 1

java Question1

Run Question 2

java Question2

⸻

5. Notes
	•	Input files must strictly follow the adjacency matrix format.
	•	Node labels (for Question2.java) must match the first row and column of the input file.
	•	Output is printed directly to the console.

⸻

6. Author & Course Information

Name: Kaamiel Isaacs
Student Number: 4129581
Course: CSC212 — Data Structures and Algorithms
Institution: University of the Western Cape
Year: 2023

⸻
