package com.adventofcode.day06;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ProblemReader {

	// for test
	private ProblemDefinition problemDefinition;

	public ProblemDefinition readProblemFile(File file) throws IOException {
		ProblemDefinition result = new ProblemDefinition();
		this.problemDefinition = result;

		final int[] lineCounter = {0};
		try (BufferedReader in = new BufferedReader(new FileReader(file))) {
			in.lines().forEach(line -> lineCounter[0] = mapLine(line, lineCounter[0], result));
			return result;
		}
	}

	private int mapLine(String line, int lineCounter, ProblemDefinition result) {
		for (int i = 0; i < line.length(); i++) {
			int position = lineCounter * line.length() + i;

			String symbol = line.substring(i, i + 1);

			Node node = new Node("#".equals(symbol));
			result.getNodes().add(node);

			if ("^".equals(symbol)) {
				result.setStartingNode(node, 6);
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

	private void addAdjacentNodeAtIndex(ProblemDefinition result, Node node,
		int adjacentNodePosition, int index) {
		if (adjacentNodePosition >= 0 && adjacentNodePosition < result.getNodes().size()) {
			node.addNode(result.getNodes().get(adjacentNodePosition), index);
		}
	}

	public ProblemDefinition getProblemDefinition() {
		return problemDefinition;
	}

	public static class ProblemDefinition {

		private final List<Node> nodes = new ArrayList<>();

		private Node startingNode;

		private int startingDirection;

		public void setStartingNode(Node startingNode, int startingDirection) {
			this.startingNode = startingNode;
			this.startingDirection = startingDirection;
		}

		public int getStartingDirection() {
			return startingDirection;
		}

		public List<Node> getNodes() {
			return nodes;
		}

		public Node getStartingNode() {
			return startingNode;
		}

		public String printMap(Set<Node> visitedNodes, Node newObsstruction) {
			return nodes.get(0).printMap(startingNode, visitedNodes, newObsstruction);
		}
	}
}
