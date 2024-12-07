package com.adventofcode.day06;


import com.adventofcode.day06.ProblemReader.ProblemDefinition;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

/**
 * Solution for
 * <a href="https://adventofcode.com/2024/day/6">day 6</a>
 */
public class Day06Solver {

	private final Logger logger = Logger.getLogger(getClass().getName());


	private final ProblemReader problemReader;

	public Day06Solver(ProblemReader problemReader) {
		this.problemReader = problemReader;
	}


	public int solve(File problemFile) throws IOException {
		ProblemDefinition problemDefinition = problemReader.readProblemFile(problemFile);

		return solveInternal(problemDefinition).size();
	}

	private Set<Node> solveInternal(ProblemDefinition problemDefinition) {
		Set<Node> visitedNodes = new HashSet<>();
		Node node = problemDefinition.getStartingNode();
		int direction = problemDefinition.getStartingDirection();
		visitedNodes.add(node);
		Node previousNode = node;
		while ((node = node.getNode(direction)) != null) {
			if (node.isObstacle()) {
				// step back from obstacle and turn 90 deg right
				node = previousNode;
				direction = (direction + 2) % 8;
				continue;
			}

			visitedNodes.add(node);
			previousNode = node;
		}

		return visitedNodes;
	}

	public int solve2(File problemFile) throws IOException {
		ProblemDefinition problemDefinition = problemReader.readProblemFile(problemFile);
		Set<Node> visitedNodes = solveInternal(problemDefinition);

		logger.info("Nodes to process: " + visitedNodes.size());

		AtomicInteger validObstructionPositions = new AtomicInteger(0);
		List<Future<?>> futures = new LinkedList<>();
		try (ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {
			// we will place obstructions only on a path visited by the guard
			for (Node node : visitedNodes) {
				if (node == problemDefinition.getStartingNode() || node.isObstacle()) {
					continue;
				}
				futures.add(executorService.submit(() -> {
					validObstructionPositions.addAndGet(runPath(problemDefinition, node));
				}));
			}
		}

		for (Future<?> future : futures) {
			try {
				future.get();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			} catch (ExecutionException e) {
				throw new RuntimeException(e);
			}
		}
		return validObstructionPositions.intValue();
	}

	private int runPath(ProblemDefinition problemDefinition, Node newObstruction) {
		Map<Node, Set<Integer>> visitedNodes = new HashMap<>();
		Node node = problemDefinition.getStartingNode();
		int direction = problemDefinition.getStartingDirection();
		Node previousNode = node;
		while ((node = node.getNode(direction)) != null) {
			if (node.isObstacle() || node == newObstruction) {
				// step back from obstacle and turn 90 deg right
				node = previousNode;
				direction = (direction + 2) % 8;
				continue;
			}

			Set<Integer> visitedDirections =
				visitedNodes.computeIfAbsent(node, k -> new HashSet<>());
			if (visitedDirections.contains(direction)) {
				return 1;
			}
			visitedDirections.add(direction);
			previousNode = node;
		}

		return 0;
	}
}
