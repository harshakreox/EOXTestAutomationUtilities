package com.eoxvantage.utilities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class CurrentDateandTime {

	final static Locale myLocale = Locale.US;
	static LocalDate date1 = LocalDate.now();
	String alteredDate;

	/**
	 * @return theCurrentDate
	 */
	public static String date() {
		String date = date1.toString().formatted(DateTimeFormatter.ofPattern("mm dd yyyy", myLocale));
		System.out.println(date1);
		return date;
	}

	/**
	 * @return theCurrentTime
	 */
	public static LocalTime time() {

		LocalTime time1 = LocalTime.now();
		String time = time1.toString().formatted(DateTimeFormatter.ofPattern("hh_mm_ss"), time1);
		System.out.println(time1);

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
		LocalTime now = LocalTime.now();
		now.toString();
		System.out.println(dtf.format(now));
		return now;
	}

	/**
	 * @param yearsToBeSubtracted
	 * @return theFinalFormattedDate
	 */
	public String alterDateForYearsBefore(int yearsToBeSubtracted) {
		return alteredDate = date1.minusYears(yearsToBeSubtracted).toString()
				.formatted((DateTimeFormatter.ofPattern("mm-dd-yyyy", myLocale)));
	}

	/**
	 * @param monthsToBeSubtracted
	 * @return theFinalFormattedDate
	 */
	public String alterDateForMonthsBefore(int monthsToBeSubtracted) {
		return alteredDate = date1.minusMonths(monthsToBeSubtracted).toString()
				.formatted((DateTimeFormatter.ofPattern("mm-dd-yyyy", myLocale)));
	}

	/**
	 * @param monthsToBeAdded
	 * @return theFinalFormattedDate
	 */
	public String alterDateForMonthsAfter(int monthsToBeAdded) {
		return alteredDate = date1.plusMonths(monthsToBeAdded).toString()
				.formatted((DateTimeFormatter.ofPattern("mm-dd-yyyy", myLocale)));
	}

	/**
	 * @param yearsToBeAdded
	 * @return theFinalFormattedDate
	 */
	public String alterDateForYearsAfter(int yearsToBeAdded) {
		return alteredDate = date1.plusYears(yearsToBeAdded).toString()
				.formatted((DateTimeFormatter.ofPattern("mm-dd-yyyy", myLocale)));
	}
}
