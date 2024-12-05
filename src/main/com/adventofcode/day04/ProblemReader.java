package com.adventofcode.day04;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProblemReader {

    private Letter startingLetter;

    public ProblemReader() {
    }

    ProblemReader(Letter startingLetter) {
        this.startingLetter = startingLetter;
    }

    public Result readProblemFile(File file) throws IOException {
        assert startingLetter != null;
        Result result = new Result();

        final int[] lineCounter = {0};
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            in.lines().forEach(line -> lineCounter[0] = mapLine(line, lineCounter[0], result));
            return result;
        }
    }

    private int mapLine(String line, int lineCounter, Result result) {
        for (int i = 0; i < line.length(); i++) {
            int position = lineCounter * line.length() + i;

            Node node = new Node(Letter.valueOf(line.substring(i, i + 1)));
            result.getNodes().add(node);
            if (node.getLetter() == startingLetter) {
                result.getStartingNodes().add(node);
            }

            // don't add left neighbours for 1st element
            if (i > 0) {
                addAdjacentNodeAtIndex(result, node, position - 1, 4);
                addAdjacentNodeAtIndex(result, node, position - line.length() - 1, 5);
            }
            addAdjacentNodeAtIndex(result, node, position - line.length(), 6);
            // don't add upper-right neighbour for last element
            if (i < line.length() - 1) {
                addAdjacentNodeAtIndex(result, node, position - line.length() + 1, 7);
            }
        }
        return lineCounter + 1;
    }

    private void addAdjacentNodeAtIndex(Result result, Node node, int adjacentNodePosition, int index) {
        if (adjacentNodePosition >= 0 && adjacentNodePosition < result.getNodes().size()) {
            node.addNode(result.getNodes().get(adjacentNodePosition), index);
        }
    }

    public void setStartingLetter(Letter startingLetter) {
        this.startingLetter = startingLetter;
    }

    public static class Result {

        private final List<Node> nodes = new ArrayList<>();

        private final List<Node> startingNodes = new ArrayList<>();

        public List<Node> getNodes() {
            return nodes;
        }

        public List<Node> getStartingNodes() {
            return startingNodes;
        }
    }
}
