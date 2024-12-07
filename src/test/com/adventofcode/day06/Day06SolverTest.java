package com.adventofcode.day06;

import static com.adventofcode.util.Util.getFileFromClasspath;
import static org.junit.jupiter.api.Assertions.*;

import com.adventofcode.util.Util;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Day06SolverTest {

	private Day06Solver solver;

	@BeforeEach
	void setUp() {
		solver = new Day06Solver(new ProblemReader());
	}

	@Test
	void testExample() throws IOException {
		long solution = solver.solve(Util.getFileFromClasspath(this, "example.txt"));
		assertEquals(41, solution);
	}

	@Test
	void answerProblem() throws IOException {
		long solution = solver.solve(getFileFromClasspath(this, "problem.txt"));
		System.out.println("Solution: " + solution);
	}
}