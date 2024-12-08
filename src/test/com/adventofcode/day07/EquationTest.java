package com.adventofcode.day07;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;

class EquationTest {

	private Equation equation;


	@Test
	void correctValue1() {
		equation = new Equation(190, List.of(10L, 19L), false);
		assertEquals(190, equation.correctValue());
	}

	@Test
	void correctValue2() {
		equation = new Equation(3267, List.of(81L, 40L, 27L), false);
		assertEquals(3267, equation.correctValue());
	}

	@Test
	void correctValue3() {
		equation = new Equation(292, List.of(11L, 6L, 16L, 20L), false);
		assertEquals(292, equation.correctValue());
	}

	@Test
	void correctValueFail() {
		equation = new Equation(21037, List.of(9L, 7L, 18L, 13L), false);
		assertEquals(0, equation.correctValue());
	}

	@Test
	void correctValue1Part2() {
		equation = new Equation(156, List.of(15L, 6L), true);
		assertEquals(156, equation.correctValue());
	}

	@Test
	void correctValue2Part2() {
		equation = new Equation(7290, List.of(6L, 8L, 6L, 15L), true);
		assertEquals(7290, equation.correctValue());
	}

	@Test
	void correctValue3Part2() {
		equation = new Equation(192, List.of(17L, 8L, 14L), true);
		assertEquals(192, equation.correctValue());
	}

	@Test
	void correctValueFailPart2() {
		equation = new Equation(21037, List.of(9L, 7L, 18L, 13L), true);
		assertEquals(0, equation.correctValue());
	}

	@Test
	void correctValue4Part2() {
		equation = new Equation(7040102834208L, List.of(1L, 1L, 7L, 3L, 350L, 4L, 7L, 2L, 3L, 6L,
			8L, 6L),
			true);
		assertEquals(7040102834208L, equation.correctValue());
	}

	@Test
	void correctValue5Part2() {
		long value = 2949251510726L;
		equation = new Equation(value, List.of(511L, 135L, 443L, 4L, 4L, 577L),
			true);
		assertEquals(value, equation.correctValue());
	}
}