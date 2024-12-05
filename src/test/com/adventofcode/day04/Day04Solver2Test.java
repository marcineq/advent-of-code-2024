package com.adventofcode.day04;

import static com.adventofcode.util.Util.getFileFromClasspath;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import org.junit.jupiter.api.Test;

class Day04Solver2Test {

    private ProblemReader reader = new ProblemReader();

    private Day04Solver2 solver = new Day04Solver2(reader);

    @Test
    void testExample() throws IOException {
        int solution = solver.solve(getFileFromClasspath(this,"example.txt"));
        assertEquals(9, solution);
    }

    @Test
    void answerProblem() throws IOException {
        int solution = solver.solve(getFileFromClasspath(this,"problem.txt"));
        System.out.println("Solution: " + solution);
    }

}
