package com.adventofcode.util;

import java.io.File;

public final class Util {

	public static File getFileFromClasspath(Object caller, String file) {
		return new File(caller.getClass().getResource(file).getFile());
	}
}
