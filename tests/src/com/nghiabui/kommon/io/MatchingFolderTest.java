package com.nghiabui.kommon.io;

import com.nghiabui.kommon.Path;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class MatchingFolderTest {

	private static final String BASE_FOLDER = "tests/res/WildcardFolderTest";

	@Test
	public void whenTailIsAFileNameOnly() {
		final Path baseFolder = new Path(BASE_FOLDER);
		final WildcardFolder wildcardFolder = new WildcardFolder(baseFolder);
		final MatchingFolder matchingFolder = new MatchingFolder(wildcardFolder);

		assertTrue(matchingFolder.matches("w?.cp?", baseFolder.combination("a/wc.cpz")));
		assertFalse(matchingFolder.matches("w?.cp?", baseFolder.combination("a/wcx.cpz")));
		assertTrue(matchingFolder.matches("*", baseFolder.combination("a/h.cpp")));
	}

	@Test
	public void whenTailIs_Not_AFileNameOnly() {
		final Path baseFolder = new Path(BASE_FOLDER);
		final WildcardFolder wildcardFolder = new WildcardFolder(baseFolder);
		final MatchingFolder matchingFolder = new MatchingFolder(wildcardFolder);

		assertTrue(matchingFolder.matches("./a/w?.cp?", baseFolder.combination("a/wc.cpz")));
		assertFalse(matchingFolder.matches("./a/w?.cp?", baseFolder.combination("a/wcx.cpz")));
		assertTrue(matchingFolder.matches("./a/*", baseFolder.combination("a/world.cpp")));
		assertTrue(matchingFolder.matches("a/../hello.*", baseFolder.combination("hello.txt")));
	}
	
	@Test
	public void whenTheFileNameHasNoExtension() {
		final Path baseFolder = new Path(BASE_FOLDER);
		final WildcardFolder wildcardFolder = new WildcardFolder(baseFolder);
		final MatchingFolder matchingFolder = new MatchingFolder(wildcardFolder);
		
		assertTrue(matchingFolder.matches("a/../a/world", baseFolder.combination("a/world.cpp")));
		assertTrue(matchingFolder.matches("world", baseFolder.combination("a/world.cpp")));
	}

	@Test
	public void test_Massive_TailsAndPaths() {
		final Path baseFolder = new Path(BASE_FOLDER);
		final WildcardFolder wildcardFolder = new WildcardFolder(baseFolder);
		final MatchingFolder matchingFolder = new MatchingFolder(wildcardFolder);

		final List<Path> matchedPaths = matchingFolder.matchedPaths(
			Arrays.asList("./a/w?.cp?", "./a/w*.cp?", "a/../hello.*"),
			Arrays.asList(
				baseFolder.combination("a/wc.cpz"),
				baseFolder.combination("a/world.cpp"),
				baseFolder.combination("hello.txt")
			)
		);

		assertEquals(3, matchedPaths.size());
	}

}
