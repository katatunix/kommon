package com.nghiabui.kommon.io;

import com.nghiabui.kommon.AppException;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;

public class FolderTest {

	public static final String TEST_FOLDER = "tests/res/FolderTest";

	@Test
	public void whenCreateFolderWithLongPath_ItIsFine() {
		final String PATH1 = TEST_FOLDER + "/" + RandomString.get();
		final String PATH2 = PATH1 + "/foo";
		final java.io.File javaFolder1 = new java.io.File(PATH1);
		final java.io.File javaFolder2 = new java.io.File(PATH2);

		assertFalse(javaFolder1.exists());
		assertFalse(javaFolder2.exists());

		final Folder folder2 = new Folder(PATH2);
		folder2.createIfNotExist();

		assertTrue(javaFolder1.exists());
		assertTrue(javaFolder2.exists());

		if (!javaFolder2.delete()) assertTrue(false);
		if (!javaFolder1.delete()) assertTrue(false);
	}

	@Test
	public void whenGetAllChildFilesOfValidFolder_ItIsFine() {
		createFilesForTesting(TEST_FOLDER);

		final Folder folder = new Folder(TEST_FOLDER);
		Collection<File> childFiles = folder.childFiles();
		assertTrue(childFiles.size() >= 10);
	}

	@Test(expected = AppException.class)
	public void whenGetAllChildFilesOfInnalidFolder_ExceptionIsThrown() {
		final Folder folder = new Folder(RandomString.get());
		folder.childFiles();
	}

	@Test
	public void whenFindExistingChildFile_ItIsFine() {
		createFilesForTesting(TEST_FOLDER);

		final Folder folder = new Folder(TEST_FOLDER);
		final File childFile = folder.findChildFile("7.txt");
		assertEquals("7.txt", childFile.name());
	}

	@Test(expected = AppException.class)
	public void whenFindNonExistingChildFile_ExceptionIsThrown() {
		createFilesForTesting(TEST_FOLDER);

		final Folder folder = new Folder(TEST_FOLDER);
		folder.findChildFile(RandomString.get());
	}

	private static void createFilesForTesting(String folderPath) {
		for (int i = 0; i < 10; ++i) {
			try (FileWriter writer = new FileWriter(folderPath + "/" + i + ".txt")) {
				writer.writeln("test " + i);
			}
		}
	}

}
