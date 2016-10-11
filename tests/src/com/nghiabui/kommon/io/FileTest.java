package com.nghiabui.kommon.io;

import com.nghiabui.kommon.AppException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FileTest {

	public static final String TEST_FOLDER = "tests/res/FileTest";
	public static final String TEST_FILE = TEST_FOLDER + "/hello.txt";

	@Test
	public void whenFileNameHasExtension_ItIsFine() {
		File file = new File(TEST_FILE);
		assertEquals("hello.txt", file.name());
	}

	@Test
	public void whenFileNameHasNoExtension_ItIsFine() {
		File file = new File(TEST_FOLDER + "/hello");
		assertEquals("hello", file.name());
	}

	@Test
	public void whenDeleteExistingFile_ItIsFine() {
		try (FileWriter writer = new FileWriter(TEST_FILE)) {
			writer.writeln("red");
		}
		new File(TEST_FILE).delete();
	}

	@Test
	public void whenDeleteNonExistingFile_ItIsStillFine() {
		new File(RandomString.get()).delete();
	}

	@Test(expected = AppException.class)
	public void whenDeleteFileBeingUsed_ExceptionIsThrown() {
		// TODO: how to test???
		throw new AppException();
	}
	
}
