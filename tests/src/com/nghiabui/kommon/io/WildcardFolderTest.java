package com.nghiabui.kommon.io;

import com.nghiabui.kommon.Path;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class WildcardFolderTest {

	private WildcardFolder create() {
		return new WildcardFolder(new Path("tests/res/WildcardFolderTest"));
	}

	@Test
	public void whenTailIsEmpty_NothingIsGenerated() {
		final WildcardFolder wildcardFolder = create();
		assertEquals(0, wildcardFolder.matchedFiles("").size());
	}

	@Test
	public void whenTailIsRelative() {
		final WildcardFolder wildcardFolder = create();

		List<Path> files = wildcardFolder.matchedFiles("./*.txt");
		assertEquals(1, files.size());
		assertEquals("hello.txt", files.get(0).name());

		files = wildcardFolder.matchedFiles(".//a/w*.cp?");
		assertEquals(2, files.size());
		assertEquals("wc.cpz", files.get(0).name());
		assertEquals("world.cpp", files.get(1).name());

		files = wildcardFolder.matchedFiles("./a/////b/////*");
		assertEquals(1, files.size());
	}

	@Test
	public void testMassiveGenFiles() {
		final WildcardFolder wildcardFolder = create();
		final List<Path> files = wildcardFolder.matchedFiles(
			Arrays.asList("./*.txt", ".//a/w*.cp?", "./a/////b/////*")
		);
		assertEquals(4, files.size());
	}
	
}
