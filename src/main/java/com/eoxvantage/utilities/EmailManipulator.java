package com.eoxvantage.utilities;

import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

public class EmailManipulator {

	Random rand = new Random();
	public static EmailManipulator em = new EmailManipulator();

	/**
	 * Here Email Prefix corresponds to the name the email begins with. For example
	 * if there is an email such as "test@eoxvanatge.com" Then the prefix would be
	 * "test". OrgName would be "@eoxvantage.com". This function is written in order
	 * to use the same email by adding a date to the email after the prefix, so that
	 * if that mail is sand boxed then all the mails will be redirected there
	 * specifically in case of E-signed mails, which normally are sent to the
	 * registered mail ID only.
	 * 
	 * @return theEmailRequired
	 */
	public static StringBuilder alterEmail() {
		String prefix = TestDataReader.getTestData("EmailPrefix");
		String op = "+";
		String suffix = TestDataReader.getTestData("OrgName");
		String uniqueNumber = em.randomNumberGenerator();

		StringBuilder email = new StringBuilder(prefix);
		email.append(op).append(uniqueNumber).append(suffix);
		return email;
	}

	/**
	 * Use this function if you want a random number to be generated between 1 to
	 * 100000 and if that number to be not repeated
	 * 
	 * @return randomNumber
	 */
	public String randomNumberGenerator() {
		Set<Integer> number = new LinkedHashSet<Integer>();
		while (number.size() < 1) {
			number.add(rand.nextInt(10000) + 1);
		}
		String Num = number.toString().replace("[", "").replace("]", "");
		return Num;

	}
}
