package com.adventofcode.day07;

public enum Operator {
	ADD("+"), TIMES("*"), CONCAT("||");

	private final String symbol;

	Operator(String symbol) {
		this.symbol = symbol;
	}

	public static Operator fromSymbol(String symbol) {
		for (Operator operator : Operator.values()) {
			if (operator.symbol.equals(symbol)) {
				return operator;
			}
		}

		throw new IllegalArgumentException("No such operator for symbol " + symbol);
	}

	public String getSymbol() {
		return symbol;
	}
}
