package com.adventofcode.day05;

import static org.junit.jupiter.api.Assertions.*;

import com.adventofcode.util.Util;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProblemReaderTest {

	private ProblemReader problemReader;

	@BeforeEach
	void setUp() {
		problemReader = new ProblemReader();
	}

	@Test
	void testTaskList() throws IOException {
		List<List<Page>> problemList =
			problemReader.readProblemFile(Util.getFileFromClasspath(this, "example.txt"));

		assertEquals(75, problemList.get(0).get(0).getNumber());
		assertEquals(61, problemList.get(0).get(2).getNumber());
		assertEquals(13, problemList.get(2).get(2).getNumber());
	}

	@Test
	void testConstraints() throws IOException {
		List<List<Page>> problemList =
			problemReader.readProblemFile(Util.getFileFromClasspath(this, "example.txt"));

		Page item75 = problemList.get(0).get(0);
		assertEquals(75, item75.getNumber());
		assertEquals(5, item75.getNextItems().size());
		assertTrue(item75.getNextItems().contains(new Page(13)));
	}
}