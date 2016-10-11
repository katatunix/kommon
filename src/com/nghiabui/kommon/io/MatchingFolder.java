package com.nghiabui.kommon.io;

import com.nghiabui.kommon.Path;
import com.nghiabui.kommon.Wildcard;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MatchingFolder {

	private final WildcardFolder wildcardFolder;

	public MatchingFolder(WildcardFolder wildcardFolder) {
		this.wildcardFolder = wildcardFolder;
	}

	public boolean matches(String tailWildcard, Path testPath) {
		if (!new Path(tailWildcard).hasParent()) {
			tailWildcard = wildcardFolder.appendExtensionWildcardIfHasNoExtension(tailWildcard);
			return new Wildcard(tailWildcard).matches(testPath.name());
		}
		return wildcardFolder.matchedFiles(tailWildcard).contains(testPath);
	}

	public boolean matches(Collection<String> tailWildcards, Path testPath) {
		for (String tailWildcard : tailWildcards) {
			if (matches(tailWildcard, testPath)) {
				return true;
			}
		}
		return false;
	}
	
	public List<Path> matchedPaths(String tailWildcard, Collection<Path> testPaths) {
		return testPaths.stream()
			.filter(testPath -> matches(tailWildcard, testPath))
			.collect(Collectors.toList());
	}

	public List<Path> matchedPaths(Collection<String> tailWildcards, Collection<Path> testPaths) {
		return testPaths.stream()
			.filter(testPath -> matches(tailWildcards, testPath))
			.collect(Collectors.toList());
	}

}
