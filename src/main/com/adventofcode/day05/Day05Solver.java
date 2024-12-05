package com.adventofcode.day05;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Solution for
 * <a href="https://adventofcode.com/2024/day/5">day 5</a>
 */
public class Day05Solver {

	private final ProblemReader problemReader;


	private final boolean fixAndReportIncorrect;

	/**
	 *
	 * @param problemReader
	 * @param fixAndReportIncorrect false for part 1, true for part 2. When true only incorrect
	 *                                 tasks are counted towards solution, after fixing them.
	 *                                 When false - only the correct ones.
	 */
	public Day05Solver(ProblemReader problemReader, boolean fixAndReportIncorrect) {
		this.problemReader = problemReader;
		this.fixAndReportIncorrect = fixAndReportIncorrect;
	}

	public int solve(File problemFile) throws IOException {
		List<List<Page>> tasks = problemReader.readProblemFile(problemFile);

		int solution = 0;
		for (List<Page> task : tasks) {
			solution += solveTask(task);
		}

		return solution;
	}

	private int solveTask(List<Page> task) {
		boolean isCorrect = true;
		for (int i = 0; i < task.size() - 1; i++) {
			Page page = task.get(i);
			Page nextPage = task.get(i + 1);
			isCorrect = isPageCorrect(page, nextPage);
			if (!isCorrect && !fixAndReportIncorrect) {
				return 0;
			} else if (!isCorrect) {
				fixIncorrect(task);
				break;
			}
		}

		if (isCorrect && fixAndReportIncorrect) {
			return 0;
		}
		return task.get(task.size() / 2).getNumber();
	}

	private boolean isPageCorrect(Page page, Page nextPage) {
		return page.nextItemPresent(nextPage);
	}

	private void fixIncorrect(List<Page> task) {
		task.sort((o1, o2) -> {
			if (o1.nextItemPresent(o2)) {
				return -1;
			}

			if (o2.nextItemPresent(o1)) {
				return 1;
			}
			return 0;
		});
	}

}
