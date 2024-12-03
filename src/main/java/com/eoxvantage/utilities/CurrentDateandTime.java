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
		 String date = date1.format(DateTimeFormatter.ofPattern("MM dd yyyy", myLocale)).toString();
		System.out.println(date1);
		return date;
	}

	/**
	 * @return theCurrentTime
	 */
	public static LocalTime time() {

		LocalTime time1 = LocalTime.now();
		String time = time1.format(DateTimeFormatter.ofPattern("hh_mm_ss"));
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
		return alteredDate = date1.minusYears(yearsToBeSubtracted)
				.format((DateTimeFormatter.ofPattern("mm-dd-yyyy", myLocale))).toString();
	}

	/**
	 * @param monthsToBeSubtracted
	 * @return theFinalFormattedDate
	 */
	public String alterDateForMonthsBefore(int monthsToBeSubtracted) {
		return alteredDate = date1.minusMonths(monthsToBeSubtracted)
				.format((DateTimeFormatter.ofPattern("mm-dd-yyyy", myLocale))).toString();
	}

	/**
	 * @param monthsToBeAdded
	 * @return theFinalFormattedDate
	 */
	public String alterDateForMonthsAfter(int monthsToBeAdded) {
		return alteredDate = date1.plusMonths(monthsToBeAdded)
				.format((DateTimeFormatter.ofPattern("mm-dd-yyyy", myLocale))).toString();
	}

	/**
	 * @param yearsToBeAdded
	 * @return theFinalFormattedDate
	 */
	public String alterDateForYearsAfter(int yearsToBeAdded) {
		return alteredDate = date1.plusYears(yearsToBeAdded)
				.format((DateTimeFormatter.ofPattern("mm-dd-yyyy", myLocale))).toString();
	}
}
