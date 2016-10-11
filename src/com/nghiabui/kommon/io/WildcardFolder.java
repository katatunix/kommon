package com.nghiabui.kommon.io;

import com.nghiabui.kommon.Path;
import com.nghiabui.kommon.Wildcard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class WildcardFolder {

	private final Path baseFolder;

	public WildcardFolder(Path baseFolder) {
		this.baseFolder = baseFolder;
	}

	public List<Path> matchedFiles(Collection<String> tailWildcards) {
		final List<Path> paths = new ArrayList<>();
		for (String tailWildcard : tailWildcards) {
			paths.addAll(matchedFiles(tailWildcard));
		}
		return paths;
	}

	public List<Path> matchedFiles(String tailWildcard) {
		final Path focusFolder;
		String fileWildcard;

		final String tail = new Path(tailWildcard).value();
		final int separator = tail.lastIndexOf(Path.SEPARATOR);
		if (separator == -1) {
			focusFolder = baseFolder;
			fileWildcard = tail;
		} else {
			focusFolder = baseFolder.combination(tail.substring(0, separator));
			fileWildcard = tail.substring(separator + 1);
		}

		fileWildcard = appendExtensionWildcardIfHasNoExtension(fileWildcard);
		
		final Wildcard wc = new Wildcard(fileWildcard);

		return focusFolder.children().stream()
			.filter(file -> file.isFile() && wc.matches(file.name()))
			.collect(Collectors.toList());
	}
	
	public String appendExtensionWildcardIfHasNoExtension(String baseName) {
		return !baseName.contains(".") ? baseName + ".*" : baseName;
	}

}
