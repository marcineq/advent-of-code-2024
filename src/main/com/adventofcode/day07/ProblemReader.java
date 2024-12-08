package com.adventofcode.day07;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ProblemReader {

	public List<Equation> readProblemFile(File file, boolean part2) throws IOException {
		try (BufferedReader in = new BufferedReader(new FileReader(file))) {
			return in.lines()
				.map(line -> mapLine(line, part2))
				.toList();
		}
	}

	private Equation mapLine(String line, boolean part2) {
		String[] parts = line.split(":");
		long value = Long.parseLong(parts[0]);

		String[] numbers = parts[1].split(" ");
		List<Long> subs = Arrays.stream(numbers)
			.filter(s -> !s.isBlank())
			.map(Long::parseLong).toList();

		return new Equation(value, subs, part2);
	}


}
