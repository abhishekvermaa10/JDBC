package com.scaleupindia.util;

import java.util.Arrays;
import java.util.Scanner;

import com.scaleupindia.enums.PetType;

/**
 * @author abhishekvermaa10
 *
 */
public class InputUtil {
	private InputUtil() {

	}

	public static int acceptMenuOption(Scanner scanner) {
		System.out.println("Press 1 to fetch owner details.");
		System.out.println("Press 2 to update pet details as per pet type.");
		int menuOption = scanner.nextInt();
		if (menuOption == 1 || menuOption == 2) {
			return menuOption;
		} else {
			return acceptMenuOption(scanner);
		}
	}

	public static boolean wantToContinue(Scanner scanner) {
		System.out.println("Press Y to continue and N to exit.");
		char choice = scanner.next().toUpperCase().charAt(0);
		return 'Y' == choice;
	}

	public static PetType acceptPetTypeToOperate(Scanner scanner) {
		System.out.println("Enter pet type:" + Arrays.asList(PetType.values()).toString());
		String petType = scanner.next().toUpperCase();
		return PetType.valueOf(petType);
	}
	
	public static boolean wantToUseCallable(Scanner scanner) {
		System.out.println("Press Y to use callable and N to use preparedStatement.");
		char choice = scanner.next().toUpperCase().charAt(0);
		return 'Y' == choice;
	}
}
