package com.nghiabui.kommon;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArgumentsTest {
	
	@Test
	public void when_OptionIsOneChar() {
		final Arguments arguments = new Arguments(new String[]{"-i", "hello", "-o"});
		assertEquals("[hello]", arguments.get('i').toString());
	}
	
	@Test
	public void when_OptionIsMultipleChar() {
		final Arguments arguments = new Arguments(new String[]{"-iap", "hello", "-o"});
		assertEquals("[hello]", arguments.get('i').toString());
		assertEquals("[hello]", arguments.get('a').toString());
		assertEquals("[hello]", arguments.get('p').toString());
	}
	
	@Test
	public void when_ValueIsMultipleString() {
		final Arguments arguments = new Arguments(new String[]{"-i", "hello", "world", "-o"});
		assertEquals("[hello, world]", arguments.get('i').toString());
	}
	
	@Test
	public void when_ValueIsNothing() {
		final Arguments arguments = new Arguments(new String[]{"-i", "-o", "world"});
		assertEquals("[]", arguments.get('i').toString());
	}
	
	@Test
	public void when_ValueIsTheLastPiece() {
		final Arguments arguments = new Arguments(new String[]{"-i", "hello"});
		assertEquals("[hello]", arguments.get('i').toString());
	}
	
	@Test
	public void when_ValueIsDouble() {
		final Arguments arguments = new Arguments(new String[]{"--iap", "hello"});
		assertEquals("[hello]", arguments.get("iap").toString());
	}
	
}
