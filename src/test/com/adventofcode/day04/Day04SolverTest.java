package com.adventofcode.day04;

import static com.adventofcode.util.Util.getFileFromClasspath;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import org.junit.jupiter.api.Test;

class Day04SolverTest {

    private ProblemReader reader = new ProblemReader();

    private Day04Solver solver = new Day04Solver(reader);

    @Test
    void testExample() throws IOException {
        int solution = solver.solve(getFileFromClasspath(this,"example.txt"));
        assertEquals(18, solution);
    }

    @Test
    void answerProblem() throws IOException {
        int solution = solver.solve(getFileFromClasspath(this,"problem.txt"));
        System.out.println("Solution: " + solution);
    }
}
