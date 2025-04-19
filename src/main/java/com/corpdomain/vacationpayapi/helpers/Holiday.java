package com.corpdomain.vacationpayapi.helpers;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public enum Holiday {
	NEW_YEAR_HOLIDAYS(1, new int[] { 1, 2, 3, 4, 5, 6, 8 }),
	CHRISTMAS(1, new int[] { 7 }),
	DEFENDER_OF_THE_FATHERLAND_DAY(2, new int[] { 23 }),
	INTERNATIONAL_FEMALE_DAY(3, new int[] { 8 }),
	SPRING_AND_LABOR_DAY(5, new int[] { 1 }),
	VICTORY_DAY(5, new int[] { 9 }),
	RUSSIA_DAY(6, new int[] { 12 }),
	NATIONAL_UNITY_DAY(11, new int[] { 4 });

	private int month;
	private int[] days;

	private Holiday(int month, int[] days) {
		this.month = month;
		this.days = days;
	}

	public IntStream getDays() {
		return Arrays.stream(this.days);
	}

	public int getMonth() {
		return month;
	}

	public Stream<LocalDate> getDates() {
		return Arrays.stream(this.days)
				.mapToObj(day -> LocalDate.of(LocalDate.now().getYear(), this.month, day));
	}
}