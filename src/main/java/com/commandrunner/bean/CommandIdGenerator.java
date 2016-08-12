package com.commandrunner.bean;

import java.util.concurrent.atomic.AtomicLong;

public class CommandIdGenerator {
	private static AtomicLong counter = new AtomicLong(0);

	public static long nextId() {
		return counter.incrementAndGet();
	}
}
