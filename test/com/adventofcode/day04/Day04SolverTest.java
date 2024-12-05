package com.adventofcode.day04;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day04SolverTest {

    private ProblemReader reader = new ProblemReader();

    private Day04Solver solver = new Day04Solver(reader);

    @Test
    void testExample() throws IOException {
        int solution = solver.solve(new File(getClass().getResource("example.txt").getFile()));
        assertEquals(18, solution);
    }

    @Test
    void answerProblem() throws IOException {
        int solution = solver.solve(new File(getClass().getResource("problem.txt").getFile()));
        System.out.println("Solution: " + solution);
    }

}
