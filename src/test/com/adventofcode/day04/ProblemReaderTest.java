package com.adventofcode.day04;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static com.adventofcode.day04.Letter.A;
import static com.adventofcode.day04.Letter.M;
import static com.adventofcode.day04.Letter.S;
import static com.adventofcode.day04.Letter.X;
import static org.junit.jupiter.api.Assertions.*;

class ProblemReaderTest {

    private ProblemReader reader = new ProblemReader(X);

    @Test
    void testStartingNodes() throws IOException {
        ProblemReader.Result result = reader.readProblemFile(new File(getClass().getResource("example.txt").getFile()));
        assertEquals(19, result.getStartingNodes().size());
    }

    @Test
    void testAdjacency0() throws IOException {
        ProblemReader.Result result = reader.readProblemFile(new File(getClass().getResource("example.txt").getFile()));
        Node node = result.getNodes().get(0);
        assertEquals(M, node.getLetter());
        assertEquals(M, node.getNode(0).getLetter());
        assertEquals(S, node.getNode(1).getLetter());
        assertEquals(M, node.getNode(2).getLetter());
        assertNull(node.getNode(3));
        assertNull(node.getNode(4));
        assertNull(node.getNode(5));
        assertNull(node.getNode(6));
        assertNull(node.getNode(7));
    }

    @Test
    void testAdjacencyLast() throws IOException {
        ProblemReader.Result result = reader.readProblemFile(new File(getClass().getResource("example.txt").getFile()));
        Node node = result.getNodes().get(result.getNodes().size() - 1);
        assertEquals(X, node.getLetter());
        assertNull(node.getNode(7));
        assertEquals(M, node.getNode(6).getLetter());
        assertEquals(M, node.getNode(5).getLetter());
        assertEquals(S, node.getNode(4).getLetter());
        assertNull(node.getNode(3));
        assertNull(node.getNode(2));
        assertNull(node.getNode(1));
        assertNull(node.getNode(0));
    }

    @Test
    void testAdjacency() throws IOException {
        ProblemReader.Result result = reader.readProblemFile(new File(getClass().getResource("example.txt").getFile()));
        Node node = result.getNodes().get(12);
        assertEquals(A, node.getLetter());
        assertEquals(M, node.getNode(0).getLetter());
        assertEquals(S, node.getNode(1).getLetter());
        assertEquals(X, node.getNode(2).getLetter());
        assertEquals(M, node.getNode(3).getLetter());
        assertEquals(S, node.getNode(4).getLetter());
        assertEquals(M, node.getNode(5).getLetter());
        assertEquals(M, node.getNode(6).getLetter());
        assertEquals(S, node.getNode(7).getLetter());
    }


    @Test
    void testStartingNodesProblem() throws IOException {
        ProblemReader.Result result = reader.readProblemFile(new File(getClass().getResource("problem.txt").getFile()));
        assertEquals(3634, result.getStartingNodes().size());
        assertEquals(19600, result.getNodes().size());
    }
}
