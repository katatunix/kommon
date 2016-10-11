package com.nghiabui.kommon;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class WildcardTest {

	@Test
	public void testNoWildcardChar() {
		final Wildcard wc = new Wildcard("hello");
		assertTrue(wc.matches("hello"));
		assertFalse(wc.matches("1hello2"));
	}

	@Test
	public void testAsterisk() {
		final Wildcard wc = new Wildcard("a*d.wav");
		assertTrue(wc.matches("abxxxxxcd.wav"));
	}

	@Test
	public void testQuestionMark() {
		final Wildcard wc = new Wildcard("a?d.wav");
		assertTrue(wc.matches("acd.wav"));
		assertFalse(wc.matches("acxd.wav"));
	}

	@Test
	public void whenWildcardIsEmpty_MatchNothing() {
		final Wildcard wc = new Wildcard("");
		assertFalse(wc.matches("acd.wav"));
		assertFalse(wc.matches(" "));
		assertTrue(wc.matches(""));
	}

	@Test(expected = Exception.class)
	public void notAcceptBackwardSlashes() {
		final Wildcard wc = new Wildcard("\\EGL");
		wc.matches("MetalDisplay.cpp");
	}

}
