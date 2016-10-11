package com.nghiabui.kommon;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class QSorterTest {

	@Test
	public void sort() {
		final List<Double> list = Arrays.asList(5.5, -8.7, 100.0, -200.0, 0.0);
		QSorter.sort(list, (a, b) -> a < b);
		
		final List<Double> expected = Arrays.asList(100.0, 5.5, 0.0, -8.7, -200.0);
		assertEquals(expected, list);
	}
	
}
