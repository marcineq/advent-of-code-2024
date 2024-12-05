package com.adventofcode.day04;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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

    private static Set<Letter> VALID_NEXT_STATES = Set.of(M, S);

    private final ProblemReader problemReader;

    private List<Node> startingNodes = new ArrayList<>();

    public Day04Solver2(ProblemReader problemReader) {
        this.problemReader = problemReader;
        problemReader.setStartingLetter(A);
    }


    public int solve(File problemFile) throws IOException {
        readProblemFile(problemFile);

        int wordsFound = 0;
        for (Node node : startingNodes) {
            if (expand(node, node.getNode(1), 1, VALID_NEXT_STATES)
                && expand(node, node.getNode(3), 3, VALID_NEXT_STATES)) {
                wordsFound++;
            }

        }
        return wordsFound;
    }

    private boolean expand(Node current, Node candidate, int direction, Set<Letter> validNextLetters) {
        if (candidate != null && validNextLetters.isEmpty()) {
            return true;
        }
        if (candidate != null && validNextLetters.contains(candidate.getLetter())) {
            int newDirection = (direction + 4) % 8;
            return expand(current, current.getNode(newDirection), newDirection,
                validNextLetters.stream().filter(letter -> letter != candidate.getLetter())
                    .collect(Collectors.toSet()));
        }

        return false;
    }

    private void readProblemFile(File problemFile) throws IOException {
        ProblemReader.Result result = problemReader.readProblemFile(problemFile);
        startingNodes = result.getStartingNodes();
    }
}
