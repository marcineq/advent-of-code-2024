package com.adventofcode.day04;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static com.adventofcode.day04.Letter.A;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Problem2ReaderTest {

    private ProblemReader reader = new ProblemReader(A);

    @Test
    void testStartingNodes() throws IOException {
        ProblemReader.Result result = reader.readProblemFile(new File(getClass().getResource("example.txt").getFile()));
        assertEquals(24, result.getStartingNodes().size());
    }
}
