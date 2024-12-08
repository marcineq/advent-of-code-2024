package com.adventofcode.day07;

import static com.adventofcode.day07.Operator.ADD;
import static com.adventofcode.day07.Operator.CONCAT;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Equation {

	private final long value;

	private final List<Long> substrates;

	private final boolean useConcatenation;

	private final Deque<Node> operations = new ArrayDeque<>();

	/**
	 * @param value            total value to be checked
	 * @param substrates       list of numbers to try
	 * @param useConcatenation is concatenation operator allowed
	 */
	public Equation(long value, List<Long> substrates, boolean useConcatenation) {
		this.value = value;
		this.substrates = substrates;
		this.useConcatenation = useConcatenation;
	}

	/**
	 * @return equation's value if the equation is correct, otherwise 0
	 */
	public long correctValue() {
		long currentValue = 0;
		currentValue = fillFromSubstrates(currentValue, 0);

		if (currentValue == value) {
			return value;
		}

		while (currentValue != value && !operations.isEmpty()) {
			Node node = operations.removeFirst();
			currentValue = reduceValue(node, currentValue);
			Operator[] toApply = getOperatorToApply(node.applied);
			if (toApply.length > 0) {
				List<Operator> applied = new ArrayList<>(node.applied);
				applied.add(toApply[0]);
				Node newNode = new Node(node.value, toApply[0], applied);
				operations.addFirst(newNode);
				currentValue = increaseValue(newNode, currentValue);
				currentValue = fillFromSubstrates(currentValue, operations.size());
			}
		}

		if (currentValue == value) {
//			if (operations.stream().anyMatch(node -> node.operator == CONCAT)) {
				System.out.println(value + ": " + operations.reversed().stream()
					.map(Node::toString)
					.collect(Collectors.joining(", ")));
//			}
			return value;
		}

		return 0;
	}

	private long fillFromSubstrates(long currentValue, int fromIndex) {
		for (long number : substrates.subList(fromIndex, substrates.size())) {
			operations.addFirst(new Node(number, ADD, List.of(ADD)));
			currentValue += number;
		}
		return currentValue;
	}

	private Operator[] getOperatorToApply(List<Operator> applied) {
		Set<Operator> values = Arrays.stream(Operator.values()).collect(
			Collectors.toSet());
		values.removeAll(applied);
		if (!useConcatenation) {
			values.remove(CONCAT);
		}
		return values.toArray(new Operator[0]);
	}

	private static long reduceValue(Node node, long currentValue) {
		return switch (node.operator) {
			case ADD -> currentValue - node.value;
			case TIMES -> currentValue / node.value;
			case CONCAT -> (currentValue - node.value) /
				(long) Math.pow(10, String.valueOf(node.value).length());
		};
	}

	private static long increaseValue(Node node, long currentValue) {
		return switch (node.operator) {
			case ADD -> currentValue + node.value;
			case TIMES -> currentValue * node.value;
			case CONCAT -> currentValue * (long) Math.pow(10, String.valueOf(node.value).length())
				+ node.value;
		};
	}

	private record Node(long value, Operator operator, List<Operator> applied) {

		@Override
		public String toString() {
			return "[" + operator.getSymbol() + " " + value + ']';
		}
	}
}
