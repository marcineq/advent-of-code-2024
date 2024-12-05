package com.adventofcode.day04;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.adventofcode.day04.Letter.A;
import static com.adventofcode.day04.Letter.M;
import static com.adventofcode.day04.Letter.S;

/**
 * Solution for
 * <a href="https://adventofcode.com/2024/day/4">day 4 part 2</a>
 */
public class Day04Solver2 {

    private static final Set<Letter> VALID_NEXT_STATES = Set.of(M, S);

    private final ProblemReader problemReader;

    public Day04Solver2(ProblemReader problemReader) {
        this.problemReader = problemReader;
        problemReader.setStartingLetter(A);
    }


    public int solve(File problemFile) throws IOException {
        List<Node> startingNodes = readProblemFile(problemFile);

        int wordsFound = 0;
        for (Node node : startingNodes) {
            if (expand(node, 1, VALID_NEXT_STATES) && expand(node, 3, VALID_NEXT_STATES)) {
                wordsFound++;
            }

        }
        return wordsFound;
    }

    private boolean expand(Node current, int direction, Set<Letter> validNextLetters) {
        Node candidate = current.getNode(direction);
        if (candidate != null && validNextLetters.isEmpty()) {
            return true;
        }
        if (candidate != null && validNextLetters.contains(candidate.getLetter())) {
            int newDirection = (direction + 4) % 8;
            return expand(current, newDirection,
                validNextLetters.stream().filter(letter -> letter != candidate.getLetter())
                    .collect(Collectors.toSet()));
        }

        return false;
    }

    private List<Node> readProblemFile(File problemFile) throws IOException {
        return problemReader.readProblemFile(problemFile).getStartingNodes();
    }
}
