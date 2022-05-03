package com.bankapp.bankappapi.Validations;

public class Validation {

	public static void nameValidation(String name) {
		if (name == null || name.trim() == "") {
			throw new Error("Name cannot be empty");
		}
	}

	public static void ageValidation(int age) {
		if (age <= 17 || age >= 100) {
			throw new Error("Age must greater than 18 & less than 100");
		}

	}

	public static void genderValidation(String gender) {
		if (gender.length() >= 10) {
			throw new Error("Enter Male or Female");
		}

	}

	public static void mobileNumberValidation(String mobile_Number) {
		if (mobile_Number.length() >= 14) {
			throw new Error("Enter a 10 digit mobile number");
		}

	}

	public static void passwordValidation(String password) {
		if (password.length() > 18 || password.length() < 8) {
			throw new Error("Password must be greater than 8 character and less than 18 character");
		}

	}

	public static void amountValidation(int amount) {
		if (amount < 500 || amount < 0) {
			throw new Error("Initial amount not less than $500");
		}

	}

}
