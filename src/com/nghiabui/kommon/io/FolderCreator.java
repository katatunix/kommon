package com.nghiabui.kommon.io;

import com.nghiabui.kommon.AppException;

import java.io.File;

public class FolderCreator {

	public static void create(String folder) {
		final File f = new File(folder);
		if (f.exists()) return;
		if (!f.mkdirs()) {
			throw new AppException("Could not create folder: " + folder);
		}
	}

}
