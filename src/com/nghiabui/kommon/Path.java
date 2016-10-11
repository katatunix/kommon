package com.nghiabui.kommon;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Path {

	public final static char SEPARATOR = '/';

	private final File file;
	private String cachedCannonical = null;

	public Path() {
		this(".");
	}

	public Path(String value) {
		this(new File(
			value.isEmpty() ? "." : convertSeparator(value)
		));
	}

	public static String convertSeparator(String path) {
		return path.replace('\\', SEPARATOR);
	}

	private Path(File file) {
		this.file = file;
	}

	public String value() {
		return convertSeparator(file.getPath());
	}

	public boolean isAbsolute() {
		return file.isAbsolute();
	}

	public String absolute() {
		return convertSeparator(file.getAbsolutePath());
	}

	public String canonical() {
		if (cachedCannonical == null) {
			final String canon;
			try {
				canon = file.getCanonicalPath();
			} catch (Exception e) {
				throw new AppException("Could not understand path: \"" + value() + "\" it may be not existed");
			}
			cachedCannonical = convertSeparator(canon);
		}
		return cachedCannonical;
	}

	public boolean isFolder() {
		return file.isDirectory();
	}

	public boolean isFile() {
		return file.isFile();
	}

	public boolean exists() {
		return file.exists();
	}

	public String name() {
		return file.getName();
	}

	public String baseName() {
		final String name = name();
		int dotIndex = lastDotIndex(name);
		return dotIndex < 0 ? name : name.substring(0, dotIndex);
	}

	public String extension() {
		final String name = name();
		int dotIndex = lastDotIndex(name);
		return dotIndex < 0 ? "" : name.substring(dotIndex + 1);
	}

	private static int lastDotIndex(String s) {
		return s.lastIndexOf('.');
	}

	public Path parentFolder() {
		final String path = file.getParent();
		if (path != null) {
			return new Path(path);
		}
		final String val = value();
		if (val.equals(".")) return new Path("..");
		if (val.equals("..")) return new Path("../..");
		return new Path(".");
	}

	public boolean hasParent() {
		return file.getParent() != null;
	}

	public List<Path> children() {
		final String[] names = file.list();
		if (names == null) {
			throw new AppException("Could not get children of the path: " + absolute());
		}
		final List<Path> res = new ArrayList<>();
		for (String name : names) {
			res.add(this.combination(name));
		}
		return res;
	}

	public Path combination(Path next) {
		return combination(next.value());
	}

	public Path combination(String next) {
		next = convertSeparator(next);
		final File nextFile = new File(next);
		if (nextFile.isAbsolute()) {
			return new Path(nextFile);
		}
		return new Path(new File(file, next));
	}

	public Optional<String> relative(Path target) {
		return relative(target.value());
	}

	public Optional<String> relative(String target) {
		target = convertSeparator(target);
		final java.nio.file.Path sourceP = Paths.get(value());
		final java.nio.file.Path targetP = Paths.get(target);
		final java.nio.file.Path rel;
		try {
			rel = sourceP.relativize(targetP);
		} catch (Exception e) {
			return Optional.empty();
		}
		return Optional.of(convertSeparator(rel.toString()));
	}

	public int hashCode() {
		String myCanon = canonical();
		if (System.IS_WINDOWS) {
			myCanon = myCanon.toLowerCase();
		}
		return myCanon.hashCode();
	}

	public boolean equals(Object other) {
		return other instanceof Path && this.hashCode() == other.hashCode();
	}

	public boolean equals(String other) {
		return equals(new Path(other));
	}

	@Override
	public String toString() {
		return value();
	}

}
