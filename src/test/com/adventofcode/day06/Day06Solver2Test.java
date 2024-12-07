package com.adventofcode.day06;

import static com.adventofcode.util.Util.getFileFromClasspath;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.adventofcode.util.Util;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Day06Solver2Test {

	private Day06Solver solver;
	private ProblemReader problemReader;

	@BeforeEach
	void setUp() {
		problemReader = new ProblemReader();
		solver = new Day06Solver(problemReader);
	}

	@Test
	void testExample() throws IOException {
		long solution = solver.solve2(Util.getFileFromClasspath(this, "example.txt"));
		assertEquals(6, solution);
	}

	@Test
	void answerProblem() throws IOException {
		long solution = solver.solve2(getFileFromClasspath(this, "problem.txt"));
		System.out.println("Solution: " + solution);
	}
}