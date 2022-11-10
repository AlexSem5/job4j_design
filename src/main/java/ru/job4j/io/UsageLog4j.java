package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
	private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());
	
	public static void main(String[] args) {
		byte a = 3;
		short b = 5;
		int c = 7;
		long d = 17L;
		float e = 7.5f;
		double f = 7.7E7;
		boolean g = true;
		char h = 'a';
		LOG.warn("Primitive types: byte : {}, short : {}"
				  + ", int : {}, long : {}, float : {}, "
				  + "double : {}, boolean : {}, char : {}",
				a, b, c, d, e, f, g, h);
	}
}
