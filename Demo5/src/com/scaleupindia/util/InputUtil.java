package com.scaleupindia.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.scaleupindia.dto.OwnerDTO;
import com.scaleupindia.enums.Gender;
import com.scaleupindia.enums.PetType;

/**
 * @author abhishekvermaa10
 *
 */
public class InputUtil {
	private InputUtil() {

	}

	public static int acceptMenuOption(Scanner scanner) {
		System.out.println("Press 1 to add owners using automatic transaction.");
		System.out.println("Press 2 to add owners using manual transaction.");
		System.out.println("Press 3 to add owners using manual transaction and savepoint.");
		int menuOption = scanner.nextInt();
		if (menuOption == 1 || menuOption == 2 || menuOption == 3) {
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

	public static List<OwnerDTO> generateOwners() {
		List<OwnerDTO> ownerDTOList = new ArrayList<>();
		int count = 1;
		for (int i = 1; i <= 5; i++) {
			OwnerDTO ownerDTO = new OwnerDTO();
			ownerDTO.setId(count);
			ownerDTO.setFirstName("FirstName" + count);
			ownerDTO.setLastName("LastName" + count);
			ownerDTO.setGender(Gender.M);
			ownerDTO.setCity("City" + count);
			ownerDTO.setState("State" + count);
			if (i % 2 == 0) {
				ownerDTO.setMobileNumber("MobileEven");
				ownerDTO.setEmailId("EmailEven");
			} else {
				ownerDTO.setMobileNumber("MobileOdd");
				ownerDTO.setEmailId("EmailOdd");
			}
			ownerDTO.setPetId(count);
			ownerDTO.setPetName("PetName" + count);
			ownerDTO.setPetBirthDate(LocalDate.now());
			ownerDTO.setPetGender(Gender.M);
			ownerDTO.setPetType(PetType.BIRD);
			ownerDTOList.add(ownerDTO);
			count++;
		}
		return ownerDTOList;
	}
}
