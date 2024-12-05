package com.adventofcode.day05;

import static com.adventofcode.util.Util.getFileFromClasspath;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.adventofcode.util.Util;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Day05Solver2Test {

	private Day05Solver solver;

	@BeforeEach
	void setUp() {
		solver = new Day05Solver(new ProblemReader(), true);
	}

	@Test
	void testExample() throws IOException {
		int solution = solver.solve(Util.getFileFromClasspath(this, "example.txt"));
		assertEquals(123, solution);
	}

	@Test
	void answerProblem() throws IOException {
		int solution = solver.solve(getFileFromClasspath(this, "problem.txt"));
		System.out.println("Solution: " + solution);
	}
}