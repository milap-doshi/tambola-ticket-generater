package com.milap.tambolaticketgenerator.model;

import java.util.List;
import java.util.Objects;

public class ValidationResult {

	private final int status;
	private final List<Integer> missedNumbers;

	public ValidationResult(int status, List<Integer> missedNumbers) {
		this.status = status;
		this.missedNumbers = missedNumbers;
	}

	public int getStatus() {
		return status;
	}

	public List<Integer> getMissedNumbers() {
		return missedNumbers;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ValidationResult)) return false;
		ValidationResult that = (ValidationResult) o;
		return getStatus() == that.getStatus() && Objects.equals(getMissedNumbers(), that.getMissedNumbers());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getStatus(), getMissedNumbers());
	}
}
