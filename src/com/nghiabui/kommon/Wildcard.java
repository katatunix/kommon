package com.nghiabui.kommon;

public class Wildcard {

	private final String regex;

	public Wildcard(String wildcard) {
		regex = ("\\Q" + wildcard + "\\E").replace("*", "\\E.*\\Q").replace("?", "\\E.\\Q");
	}

	public boolean matches(String s) {
		return s.matches(regex);
	}

}
