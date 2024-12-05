package com.adventofcode.day04;

public class Node {

    private final Letter letter;

    /*
       zero value for node to right
       5 6 7
       4 T 0
       3 2 1
     */
    private final Node[] adjacentNodes = new Node[8];

    public Node(Letter letter) {
        this.letter = letter;
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

    public Letter getLetter() {
        return letter;
    }
}
