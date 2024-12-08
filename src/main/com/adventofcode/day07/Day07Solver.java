package com.adventofcode.day07;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Solution for
 * <a href="https://adventofcode.com/2024/day/7">day 7</a>
 */
public class Day07Solver {

	private final ProblemReader problemReader;
	private final boolean part2;

	public Day07Solver(ProblemReader problemReader, boolean part2) {
		this.problemReader = problemReader;
		this.part2 = part2;
	}

	public long solve(File problemFile) throws IOException {
		List<Equation> equations = problemReader.readProblemFile(problemFile, part2);

		long solution = 0;
		List<Future<Long>> futures = new LinkedList<>();
		try (ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {
			for (Equation equation : equations) {
				futures.add(executorService.submit(equation::correctValue));
			}
		}

		for (var future : futures) {
			try {
				solution += future.get();
			} catch (InterruptedException | ExecutionException e) {
				throw new RuntimeException(e);
			}
		}

		return solution;
	}
}
