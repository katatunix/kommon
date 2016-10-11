package com.nghiabui.kommon;

import org.junit.Test;

import static org.junit.Assert.*;

public class PathTest {
	
	// value()

	@Test
	public void valueIsTheSameAsConstructed() {
		assertEquals("a/b/c", new Path("a/b/c").value());
		assertEquals("../a/../b/./hello.txt", new Path("../a/../b/./hello.txt").value());
		assertEquals(".", new Path(".").value());
		assertEquals("..", new Path("..").value());
	}

	@Test
	public void valueOfAnEmptyPath_IsADot() {
		assertEquals(".", new Path("").value());
		assertEquals(".", new Path().value());
	}

	@Test
	public void valueOfARelativePath_IsInRelativeForm() {
		assertEquals("hello/world", new Path("hello/world").value());
		assertEquals("../hello/world", new Path("../hello/world").value());
	}

	@Test
	public void valueOfAnAbsolutePath_IsInAbsoluteForm() {
		if (System.IS_UNIX) {
			assertEquals("/hello/world", new Path("/hello/world").value());
		} else {
			assertEquals("C:/hello/world", new Path("C:/hello/world").value());
		}
	}

	@Test
	public void valueHasNo_RedundantSlashes() {
		final Path path = new Path("a//b///c////");
		assertEquals("a/b/c", path.value());
	}

	@Test
	public void valueNeverContainBackwardSlashes() {
		final Path path = new Path("a\\b\\c");
		assertEquals("a/b/c", path.value());
	}

	@Test
	public void valueIsNotResolved() {
		final Path path = new Path("a/./b/c/../c");
		assertEquals("a/./b/c/../c", path.value());
	}
	
	// isAbsolute()

	@Test
	public void testIsAbsolute() {
		if (System.IS_UNIX) {
			assertTrue(new Path("/a").isAbsolute());
		} else {
			assertTrue(new Path("C:/a").isAbsolute());
		}
		assertFalse(new Path("a").isAbsolute());
	}
	
	// absolute()

	@Test
	public void absoluteOfARelativePath_IsInAbsoluteForm() {
		final String relPath = "../a/../b/c/d.txt";
		final String absPath = new Path(relPath).absolute();
		assertTrue(absPath.endsWith(relPath) && absPath.length() > relPath.length());
	}

	@Test
	public void absoluteNeverContainBackwardSlashes() {
		final Path path = new Path("/a\\b\\c");
		assertFalse(path.absolute().contains("\\"));
	}
	
	@Test
	public void absoluteIsNotResolved() {
		if (System.IS_UNIX) {
			assertEquals("/a/b/..", new Path("/a/b/..").absolute());
		} else {
			assertEquals("C:/a/b/..", new Path("C:/a/b/..").absolute());
		}
	}
	
	// canonical()
	
	@Test
	public void canonicalNeverContainBackwardSlashes() {
		final Path path = new Path("/a\\b\\c");
		assertFalse(path.canonical().contains("\\"));
		
	}
	
	@Test
	public void canonnicalIsResolved() {
		assertEquals(new Path("a").absolute(), new Path("a/b/c/../../").canonical());
	}
	
	@Test
	public void canonnicalCannotEndWithSlashes() {
		assertTrue(new Path("/a//").canonical().endsWith("a"));
	}
	
	// others

	@Test
	public void testChildren() {
		assertTrue(new Path(".").children().size() > 0);
	}

	@Test
	public void testExists() {
		assertTrue(new Path("tests/res/PathTest").exists());
		assertTrue(new Path("tests/res/PathTest/fileA.txt").exists());
		assertFalse(new Path("tests/res/PathTest/fileB.txt").exists());
	}

	@Test
	public void testName() {
		assertEquals("hello.txt", new Path("a/b/c/hello.txt").name());
		assertEquals("hello", new Path("a/b/c/hello/").name());
	}

	@Test
	public void testExtension() {
		assertEquals("txt", new Path("a/b/c/hello.txt").extension());
		assertEquals("", new Path("a/b/c/hello").extension());
		assertEquals("", new Path("a/b/c/hello/").extension());
	}

	@Test
	public void testBaseName() {
		assertEquals("hello", new Path("a/b/c/hello.txt").baseName());
		assertEquals("hello", new Path("a/b/c/hello").baseName());
	}

	@Test
	public void test_IsFolder_IsFile() {
		assertTrue(new Path(".").isFolder());
		assertFalse(new Path(".").isFile());
	}

	@Test
	public void testCombination() {
		assertEquals(new Path("a/b/c/d"), new Path("a/b/c").combination(new Path("d")));
	}

	@Test
	public void testEquals() {
		assertTrue(new Path("a/b/c/d").equals("a/b/c/../c/d"));
	}

	@Test
	public void testRelative() {
		assertEquals("c.cpp", new Path("a/b").relative("a/b/c.cpp").get());
		assertEquals("", new Path("a/b/c").relative("a/b/c").get());
		assertEquals("..", new Path("a/b/c").relative("a/b").get());
		assertEquals("../../..", new Path("a/b/c/d/e").relative("a/b").get());
		assertEquals("../../../hello/world.txt", new Path("a/b/c/d/e").relative("a/b/hello/world.txt").get());

		assertEquals("c.cpp", new Path("a/b").relative(new Path("a/b/c.cpp")).get());
	}

}
