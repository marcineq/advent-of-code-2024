package com.adventofcode.day04;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.adventofcode.day04.Letter.A;
import static com.adventofcode.day04.Letter.M;
import static com.adventofcode.day04.Letter.S;
import static com.adventofcode.day04.Letter.X;

/**
 * Solution for
 * <a href="https://adventofcode.com/2024/day/4">day 4 part 1</a>
 */
public class Day04Solver {

    private static Map<Letter, Letter> STATES = Map.of(
        X, M,
        M, A,
        A, S);

    private static final int DIRECTIONS = 8;

    private static final Letter LAST_LETTER = S;

    private final ProblemReader problemReader;

    public Day04Solver(ProblemReader problemReader) {
        this.problemReader = problemReader;
        problemReader.setStartingLetter(X);
    }


    public int solve(File problemFile) throws IOException {
        List<Node> startingNodes = readProblemFile(problemFile);

        int wordsFound = 0;
        for (Node node : startingNodes) {
            for (int i = 0; i < DIRECTIONS; i++) {
                wordsFound += expand(node, i);
            }
        }
        return wordsFound;
    }

    private int expand(Node current, int direction) {
        Node candidate = current.getNode(direction);
        if (candidate != null && STATES.get(current.getLetter()) == candidate.getLetter()) {
            if (candidate.getLetter() == LAST_LETTER) {
                return 1;
            }
            return expand(candidate, direction);
        }

        return 0;
    }

    private List<Node> readProblemFile(File problemFile) throws IOException {
        return problemReader.readProblemFile(problemFile).getStartingNodes();
    }
}
