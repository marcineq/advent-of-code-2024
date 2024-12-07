package com.adventofcode.day06;

import java.util.Set;

public class Node {

    private final boolean obstacle;

    private boolean newObstruction;

    /*
       zero value for node to right
       5 6 7
       4 T 0
       3 2 1
     */
    private final Node[] adjacentNodes = new Node[8];

	public Node(boolean obstacle) {
		this.obstacle = obstacle;
	}


	/**
     * Add a node at index position, while creating reverse relationship at the same time
     * @param node node to be added
     * @param index index in adjacency table, where 0 means a node to the right, calculating clocwise
     *        5 6 7
     *        4 T 0
     *        3 2 1
     */
    public void addNode(Node node, int index) {
        adjacentNodes[index] = node;
        node.adjacentNodes[(index + 4) % 8] = this;
    }

    /**
     * Get node at index position
     * @param index index in adjacency table, where 0 means a node to the right, calculating clocwise
     *              5 6 7
     *              4 T 0
     *              3 2 1
     * @return node at index
     */
    public Node getNode(int index) {
        return adjacentNodes[index];
    }

    public boolean isObstacle() {
        return obstacle;
    }

    public Node[] getAdjacentNodes() {
        return adjacentNodes;
    }

    public boolean isNewObstruction() {
        return newObstruction;
    }

    public void setNewObstruction(boolean newObstruction) {
        this.newObstruction = newObstruction;
    }

    @Override
    public String toString() {
        return "Node{" +
            "obstacle=" + obstacle +
            ", newObstruction=" + newObstruction +
            '}';
    }

    public String printMap(Node start, Set<Node> visitedNodes, Node newObstruction) {
        StringBuilder stringBuilder = new StringBuilder("\n");
        Node node = this;
        Node previousNode = null;
        int direction = 0;
        do {
            StringBuilder line = new StringBuilder();
            do {
                line.append(print(node, node == start, visitedNodes.contains(node),
                    node == newObstruction));
                previousNode = node;
                node = node.getNode(direction);
            } while (node != null);
            if (direction == 4) {
                line = line.reverse();
            }
            direction = (direction + 4) % 8;
            stringBuilder.append(line).append("\n");
            node = previousNode.getNode(2);
        } while (node != null);

        return stringBuilder.toString();
    }

    private String print(Node node, boolean isStart, boolean isVisited, boolean isNewObstruction) {
        String s = ".";
        if (node.isObstacle()) {
            s = "#";
        }
        if (node.isNewObstruction()) {
            s = "O";
        }
        if (isVisited) {
            s = "|";
        }
        if (isStart) {
            s = "^";
        }
        if (isNewObstruction) {
            s = "O";
        }
        return s;
    }
}
