package com.adventofcode.day05;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ProblemReader {

	public List<List<Page>> readProblemFile(File file) throws IOException {
		Map<String, Page> constraintMap = new LinkedHashMap<>();
		try (BufferedReader in = new BufferedReader(new FileReader(file))) {
			return in.lines()
				.map(line -> mapLine(line, constraintMap))
				.filter(list -> !list.isEmpty())
				.toList();
		}
	}

	private List<Page> mapLine(String line, Map<String, Page> constraintMap) {
		if (line.contains("|")) {
			return mapConstraintLine(line, constraintMap);
		}
		if (line.contains(",")) {
			return mapTaskLine(line, constraintMap);
		}
		return Collections.emptyList();
	}

	private List<Page> mapConstraintLine(String line, Map<String, Page> constraintMap) {
		String[] pages = line.split("\\|");
		Page first = constraintMap.computeIfAbsent(pages[0], k -> new Page(Integer.parseInt(k)));
		Page second = constraintMap.computeIfAbsent(pages[1], k -> new Page(Integer.parseInt(k)));
		first.addNextItem(second);
		return Collections.emptyList();
	}

	private List<Page> mapTaskLine(String line, Map<String, Page> constraintMap) {
		List<Page> result = new ArrayList<>(line.length());
		String[] numbers = line.split(",");
		for (String number : numbers) {
			result.add(constraintMap.get(number));
		}
		return result;
	}

}
