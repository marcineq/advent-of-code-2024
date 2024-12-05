package com.adventofcode.day05;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Page {

	private final int number;

	private final Set<Page> nextItems = new HashSet<>();

	public Page(int number) {
		this.number = number;
	}

	public void addNextItem(Page item) {
		nextItems.add(item);
	}

	public boolean nextItemPresent(Page nextItem) {
		return nextItems.contains(nextItem);
	}

	public int getNumber() {
		return number;
	}

	public Set<Page> getNextItems() {
		return nextItems;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Page that = (Page) o;
		return number == that.number;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(number);
	}
}
