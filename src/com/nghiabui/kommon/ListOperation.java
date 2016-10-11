package com.nghiabui.kommon;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ListOperation {
	
	public static <T> List<T> op2list(Optional<T> op) {
		final List<T> result = new ArrayList<>(1);
		if (op.isPresent()) {
			result.add(op.get());
		}
		return result;
	}
	
}
