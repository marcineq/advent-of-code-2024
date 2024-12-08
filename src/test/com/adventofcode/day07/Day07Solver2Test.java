package com.adventofcode.day07;

import static com.adventofcode.util.Util.getFileFromClasspath;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.adventofcode.util.Util;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Day07Solver2Test {

	private Day07Solver solver;

	@BeforeEach
	void setUp() {
		solver = new Day07Solver(new ProblemReader(), true);
	}

	@Test
	void testExample() throws IOException {
		long solution = solver.solve(Util.getFileFromClasspath(this, "example.txt"));
		assertEquals(11387, solution);
	}

	@Test
	void answerProblem() throws IOException {
		long solution = solver.solve(getFileFromClasspath(this, "problem.txt"));
		System.out.println("Solution: " + solution);
	}
}